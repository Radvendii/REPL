#The Win Monkeys
all: test

comp:
	javac *.java;

test: comp
	test $(cat testRes) = $(java test) && echo "passed test.java" || echo "Failed test.java -- CHECK CODE (or possibly update testRes)"

clean:
	rm -rf *.class
