default: build

com/craftinginterpreters/lox/Lox.class:
	javac -cp . com/craftinginterpreters/lox/Lox.java

.PHONY: build
build: com/craftinginterpreters/lox/Lox.class

.PHONY: repl
repl: build
	java com.craftinginterpreters.lox.Lox