package system.runnable;

import generators.GlobalMapGenerator;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: Yury
 * Date: 09/07/12
 * Time: 12:19
 */
public class YuryTest {
    public static void main(String[] args) {
      try{ 
    	System.out.println("Generating World");
    	GlobalMapGenerator worldmap = new GlobalMapGenerator(1000);
    	System.out.println("Writing File");
        FileWriter fstream = new FileWriter("out.txt");
        BufferedWriter out = new BufferedWriter(fstream);
        for (int i = 0; i < 1000; i++){
        	out.write("\n");
        	for (int j = 0; j < 1000; j++){
        		int elev = worldmap.getGlobalMapItem(i, j).getElevation();
        		if (elev >= 60) {out.write("^");}
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



