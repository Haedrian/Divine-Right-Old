package system.runnable;

import generators.GlobalMapGenerator;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.imageio.ImageIO;


public class ByroTest {
    public static void main(String[] args) {
      int worldsize = 1000;
  	System.out.println("Generating World");
  	GlobalMapGenerator worldmap = new GlobalMapGenerator(10000, worldsize);
      try{ 
    	System.out.println("Writing File");
    	int width = 1000;
        int height = 1000;
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bi.createGraphics();
        g2.setColor(Color.white);
        g2.fillRect(0,0, width, height);
        for (int i = 0; i < worldsize; i++){
        	for (int j = 0; j < worldsize; j++){
        		int elev = worldmap.getGlobalMapItem(i, j).getElevation();
        		if (elev <= 0){g2.setColor(new Color(0,0,250 + elev));}
        		else if (elev >0 && elev < 80) {g2.setColor(new Color(0,elev,0));}
        		else if (elev >= 80) {g2.setColor(new Color (elev,elev,elev));}
        		g2.fillRect(i*(1000/worldsize), j*(1000/worldsize), 1000/worldsize, 1000/worldsize);
        	}
        	     
        }
        File f = new File("roughmap.png");		
        ImageIO.write(bi, "png", f);	       
        System.out.println("File Done");
           }catch (Exception e){//Catch exception if any
        	   System.err.println("Error: " + e.getMessage());
        	   e.printStackTrace();
           }
    	System.out.println("Smoothing World");
        worldmap.smoothMap(3);
          try{
        	System.out.println("Writing File");
        	int width = 1000;
            int height = 1000;
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = bi.createGraphics();
            g2.setColor(Color.white);
            g2.fillRect(0,0, width, height);
            for (int i = 0; i < worldsize; i++){
            	for (int j = 0; j < worldsize; j++){
            		int elev = worldmap.getGlobalMapItem(i, j).getElevation();
            		if (elev <= 0){g2.setColor(new Color(0,0,250 + elev));}
            		else if (elev >0 && elev < 80) {g2.setColor(new Color(0,elev,0));}
            		else if (elev >= 80) {g2.setColor(new Color (elev,elev,elev));}
            		g2.fillRect(i*(1000/worldsize), j*(1000/worldsize), 1000/worldsize, 1000/worldsize);
            	}
            	     
            }
            File f = new File("smoothmap.png");		
            ImageIO.write(bi, "png", f);	       
            System.out.println("File Done");
               }catch (Exception e){//Catch exception if any
            	   System.err.println("Error: " + e.getMessage());
            	   e.printStackTrace();
               }
            
      try{ 
      	System.out.println("Writing Text File");
          FileWriter fstream = new FileWriter("outnumber.csv");
          BufferedWriter out = new BufferedWriter(fstream);
          for (int i = 0; i < worldsize; i++){
          	out.write("\n");
          	for (int j = 0; j < worldsize; j++){
          		int elev = worldmap.getGlobalMapItem(i, j).getElevation();
          		out.write(elev+",");
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



