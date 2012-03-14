/*Main java file for the project, gives a prompt and initiates the REPL*/
public class Shell
{
 private final String prompt = ">";
 
  public static void main(String[] args)
 {
   StringHolder comms = new StringHolder(); // Initializes StringHolder
   while(true)
   {
     String in = getStringFromShell(prompt);
     comms.add(in);
     RunCode run = new RunCode(comms.toFile());
         System.out.println(comms.toFile()); //DEBUG
                  run.comprun();
   }
 }
 
  //get String or simply enter from shell
 public static String getStringFromShell(String prompt)
 {
   try
   {
     System.out.print(prompt);
     return new BufferedReader(new InputStreamReader(System.in)).readLine();   
   }
   catch (IOException e)
   {
     e.printStackTrace();
   }
 return null ;
 }
}
