import java.util.*;
/**
 * <code>StringHolder</code> takes in strings from the input loop and
 * returns the proper file. Use add to put a string in, and toFile to 
 * get the string to be turned into a file.
 * @author Keller Scholl keller.scholl@gmail.com
 * @since 29/3/12
 */
public class StringHolder
{	
	//methodIndicator should be a short string that would not appear at the beginning of a line in valid Java code
	public static final methodIndicator = "}";
	//Standard declaration of the ArrayLists that will contain the individual strings,
	//divided by type, in the order that they were entered.
	public ArrayList<String> orders;
	public ArrayList<String> methods;
	public ArrayList<String> imports;
	public String lastEntry = "none";
	//Default Constructor
	public StringHolder(){
		orders = new ArrayList<String>();
		methods = new ArrayList<String>();
		imports = new ArrayList<String>();
	}
	/**
	 * Takes in a string that should be added.
	 * @param entry The string that is to be added to the file
	 */
	void add(String entry){
		entry.matches(" *import .*;"); //What is this for?
		if (entry.matches(" *import .*;")){
			imports.add(entry);
			lastEntry = "import";
		}
		else if (entry.matches(methodIndicator + ".*")){
			methods.add(entry.substring(1));
			lastEntry = "method";
		}
		else
			orders.add(entry);
			lastEntry = "order";
	}
	/**
	 * Returns the updated String that should be compiled
	 * @return the new string that should be compiled into a file.
	 */
	String toFile(){
		String base = "";
		base = combine(imports, base);
		base = base.concat(" public class " + RunCode.CLASS + "{\n");
		base = combine(methods, base);
		base = base.concat("public static void main(String[] args){\n");
		base = combine(orders.subList(0, (orders.size()-2)>0 ? orders.size()-2 : 0), base); //all of the orders except the last one (none if there is only one
		//still get error about void methods
		if (lastEntry.equals("order")) base = base.concat("System.out.println( "+orders.get(orders.size()-1).substring(0,orders.get(orders.size()-1).length()-1)+");\n");
							//Print the last command with the last character (the ;) removed
		base = base.concat("}\n}");
		return base;
	}
	static private String combine(List<String> adder, String base){
		Object[] hold = adder.toArray();
		for (Object anImport : hold)
			base = base.concat(anImport.toString() + "\n");
		return base;
	}
}
