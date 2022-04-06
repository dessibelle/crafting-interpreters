default: build

com/craftinginterpreters/lox/Lox.class:
	javac -cp . com/craftinginterpreters/lox/Lox.java

com/craftinginterpreters/lox/AstPrinter.class:
	javac -cp . com/craftinginterpreters/lox/AstPrinter.java

com/craftinginterpreters/tool/GenerateAst.class:
	javac -cp . com/craftinginterpreters/tool/GenerateAst.java

.PHONY: clean
clean:
	find . -name '*.class' -exec rm {} \;

.PHONY: build
build: com/craftinginterpreters/lox/Lox.class com/craftinginterpreters/lox/AstPrinter.class  com/craftinginterpreters/tool/GenerateAst.class

.PHONY: rebuild
rebuild: clean build

.PHONY: repl
repl: rebuild
	java com.craftinginterpreters.lox.Lox

.PHONY: generate_ast
generate_ast: rebuild
	java com.craftinginterpreters.tool.GenerateAst com/craftinginterpreters/lox