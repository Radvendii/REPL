/*
 * Compiles source code into a .class file and then runs it.
 *
*/



import javax.tools.*;
import java.util.*;
import java.io.*;
import org.apache.commons.io.*;

public class RunCode{
	String sourceCode;
	static final String CLASS = "REPL";

	public RunCode(String sc){
		sourceCode = sc;
	}
	public byte[] compile() throws IOException{
		//taken from the internet:
		//@author caffeine-coma	// @website http://stackoverflow.com/questions/2130039/javacompiler-from-jdk-1-6-how-to-write-class-bytes-directly-to-byte-array
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

		List<JavaSourceFromString> unitsToCompile = new ArrayList<JavaSourceFromString>() 
			{{ 
				 add(new JavaSourceFromString(CLASS, sourceCode)); 
			}};

		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		compiler.getTask(null, fileManager, null, null, null, unitsToCompile).call();
		fileManager.close();    

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		FileInputStream fis = new FileInputStream(CLASS + ".class");
		IOUtils.copy(fis, bos);

		return bos.toByteArray();
	}
	/*runs REPL.main(), the file created by this.compile()*/
	public void run(){
		REPL.main(new String[0]); //main needs to be called with a String array, so initialize an empty one
	}
	/* compile and run in one step */
	public void comprun() throws IOException{
		compile();
		run();
	}
}
