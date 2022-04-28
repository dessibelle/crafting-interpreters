package com.craftinginterpreters.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Lox {
  private static final Interpreter interpreter = new Interpreter();
  static boolean hadError = false;
  static boolean hadRuntimeError = false;

  private static void run(String source, boolean printExpressionResults) {
    Scanner scanner = new Scanner(source);
    List<Token> tokens = scanner.scanTokens();

    // System.out.println("Tokens: " + tokens.toString());

    Parser parser = new Parser(tokens);
    List<Stmt> statements = parser.parse();

    // Stop if there was a syntax error.
    if (hadError) return;

    if (printExpressionResults) {
      for (int i = 0; i < statements.size(); i++) {
        Stmt statement = statements.get(i);
        if (statement instanceof Stmt.Expression) {
          Stmt.Print printStatement = new Stmt.Print(((Stmt.Expression)statement).expression);
          statements.set(i, printStatement);
        }
      }
    }

    // for (Stmt stmt : statements) {
    //   // if (stmt instanceof Stmt.Expression) {
    //   //   System.out.println("AST: " + new AstPrinter().print(stmt.expression));
    //   // }
    //   System.out.println(stmt.accept(interpreter));
    // }
    interpreter.interpret(statements);
  }

  private static void run(String source) {
    run(source, false);
  }

  public static void runFile(String path) throws IOException {
    byte[] bytes = Files.readAllBytes(Paths.get(path));
    run(new String(bytes, Charset.defaultCharset()));

    // Indicate an error in the exit code.
    if (hadError) System.exit(65);
    if (hadRuntimeError) System.exit(70);
  }

  private static void runPrompt() throws IOException {
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(input);

    for (;;) { 
      System.out.print("> ");
      String line = reader.readLine();
      if (line == null) break;
      run(line, true);
      hadError = false;
    }
  }

  public static void main(String[] args) throws IOException {
    if (args.length > 1) {
      System.out.println("Usage: jlox [script]");
      System.exit(64);
    } else if (args.length == 1) {
      runFile(args[0]);
    } else {
      runPrompt();
    }
  }

  private static void report(int line, String where, String message) {
    System.err.println("[line " + line + "] Error" + where + ": " + message);
    hadError = true;
  }

  static void error(int line, String message) {
    report(line, "", message);
  }

  static void error(Token token, String message) {
    if (token.type == TokenType.EOF) {
      report(token.line, " at end", message);
    } else {
      report(token.line, " at '" + token.lexeme + "'", message);
    }
  }

  static void runtimeError(RuntimeError error) {
    System.err.println(error.getMessage() + "\n[line " + error.token.line + "]");
    hadRuntimeError = true;
  }
}
