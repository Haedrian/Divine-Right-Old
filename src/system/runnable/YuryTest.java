package system.runnable;

import generators.GeneratorTest;
import generators.GlobalMapGenerator;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: Yury
 * Date: 09/07/12
 * Time: 12:19
 */
public class YuryTest {
	public static String newline = System.getProperty("line.separator");

    public static void main(String[] args) {
      try{ 
    	System.out.println("Generating World");
    	GlobalMapGenerator worldmap = new GlobalMapGenerator(100);
    	System.out.println("Writing File");
        FileWriter fstream = new FileWriter("out.txt");
        BufferedWriter out = new BufferedWriter(fstream);
        for (int i = 0; i < 100; i++){
        	out.write(newline);
        	for (int j = 0; j < 100; j++){
        		int elev = worldmap.getGlobalMapItem(i, j).getElevation();
        		System.out.println(elev);
        		if (elev >= 80) {out.write("^");}
        		else if (elev >= 50) {out.write("n");}
        		else if (elev >= 25) {out.write("O");}
        		else if (elev >= 1) {out.write("*");}
        		else if (elev <= 0) {out.write("~");}
        	}
        		
        	}
        out.close();
        System.out.println("File Done");
           }catch (Exception e){//Catch exception if any
        	   System.err.println("Error: " + e.getMessage());
        	   e.printStackTrace();
           }
        }
    
   
}



