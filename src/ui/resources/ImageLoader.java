package ui.resources;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Uses Slick to load images when requested and stores them in a map along with their names for further usage.
 * @author Blay09
 * 
 */
public class ImageLoader {
	
	private static ImageLoader instance;
	private Map<String, Image> loadedImages;
	
	private ImageLoader() {
		loadedImages = new HashMap<String, Image>();
	}
	
	/**
	 * Returns the image with the given name. If the image has not been loaded yet, it will be done automatically.
	 * @param imageName
	 * @return
	 */
	public Image getImage(String imageName) {
		if(!loadedImages.containsKey(imageName)) {
			try {
				String imagePath = "res/gfx/" + imageName + ".png";
				System.out.println("Loading image " + imageName + " at " + imagePath + "...");
				Image newImage = new Image(imagePath);
				loadedImages.put(imageName, newImage);
				return newImage;
			} catch (SlickException e) {
				System.out.println("Could not load image " + imageName + ": " + e.getMessage());
				return null;
			}
		}
		return loadedImages.get(imageName);
	}
	
	/**
	 * Returns the singleton instance of this class.
	 * @return
	 */
	public static ImageLoader getInstance() {
		if(instance == null) {
			instance = new ImageLoader();
		}
		return instance;
	}
}
