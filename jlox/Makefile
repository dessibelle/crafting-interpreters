default: build

com/craftinginterpreters/lox/Lox.class:
	javac -cp . com/craftinginterpreters/lox/Lox.java

com/craftinginterpreters/tool/GenerateAst.class:
	javac -cp . com/craftinginterpreters/tool/GenerateAst.java

.PHONY: build
build: com/craftinginterpreters/lox/Lox.class com/craftinginterpreters/tool/GenerateAst.class

.PHONY: repl
repl: build
	java com.craftinginterpreters.lox.Lox

.PHONY: generate_ast
generate_ast: build
	java com.craftinginterpreters.tool.GenerateAst com/craftinginterpreters/lox