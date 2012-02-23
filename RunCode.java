import javax.tools.*;
import java.util.*;
import java.io.*;
import org.apache.commons.io.*;

public class RunCode{
	String className;
	String sourceCode;
	public RunCode(String sc){
		className = "REPL";
		sourceCode = sc;
	}
	public byte[] compile() throws IOException{
		//@author caffeine-coma	// @website http://stackoverflow.com/questions/2130039/javacompiler-from-jdk-1-6-how-to-write-class-bytes-directly-to-byte-array
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

		List<JavaSourceFromString> unitsToCompile = new ArrayList<JavaSourceFromString>() 
			{{ 
				 add(new JavaSourceFromString(className, sourceCode)); 
			}};

		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		compiler.getTask(null, fileManager, null, null, null, unitsToCompile).call();
		fileManager.close();    

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		FileInputStream fis = new FileInputStream(className + ".class");
		IOUtils.copy(fis, bos);

		return bos.toByteArray();
	}
	public void run(){
		REPL.main(new String[0]);
	}
	public void comprun() throws IOException{
		compile();
		run();
	}
}
