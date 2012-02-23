import java.util.ArrayList;
public class StringHolder 
{
	public ArrayList<String> orders;
	public ArrayList<String> methods;
	public ArrayList<String> imports;
	void add(String entry){
		if (entry.contains("import"))
			imports.add(entry);
		else if (entry.contains("The non regex string to identify methods")) 
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

