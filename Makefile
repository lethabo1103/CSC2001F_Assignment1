# Compiler
JAVAC = javac
# All Java source files in the directory
SRC = $(wildcard *.java)
# Corresponding class files
CLASSES = $(SRC:.java=.class)

# compile all Java files
all: $(CLASSES)

# Pattern rule to compile .java into .class
%.class: %.java
	$(JAVAC) $<

# Clean target: remove all .class files
clean:
	rm -f *.class

.PHONY: all clean