import java.util.ArrayList;
import java.util.regex.*;

public class Java-Repl 
{
	public ArrayList<String> orders;
	public ArrayList<String> methods;
	public ArrayList<String> imports;
	
	void add(String entry){
		entry.matches(" *import .*;");
		if (entry.matches(" *import .*;"))
			imports.add(entry);
		//something, a word, a space, a word, open paren, 
		//any number of inputs, close paren, white space/newlines,
		//curly brace, stuff, curlybrace at end.
		else if (entry.matches(".*?[a-zA-Z0-9]* [a-zA-Z0-9]*\\(.*\\)[ \n\t]*\\{.*\\}"))
			methods.add(entry);
		else
			orders.add(entry);
	}
	String file(){
		String base = "";
		base = combine(imports, base);
		base = base.concat(" public class REPL{");
		base = combine(methods, base);
		base = base.concat("String runner(){");
		base = combine(orders, base);
		//check if method is void
		base = base.concat("return "+orders.get(orders.size()) + ".toString()");
		return base;
	}
	private String combine(ArrayList<String> adder, String base){
		Object[] hold = adder.toArray();
		for (Object anImport : hold)
			base = base.concat(anImport.toString());
		return base;
	}

	public static void main(String[] args){
		System.out.println("dog".contains("d"));
	}
}