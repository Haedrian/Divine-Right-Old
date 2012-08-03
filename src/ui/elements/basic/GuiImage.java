package ui.elements.basic;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import ui.elements.GuiWidget;
import ui.resources.ImageLoader;

/**
 * A simple wrapper class to render a Slick image in the GUI.
 * This element is not supposed to have children.
 * @author Blay09
 *
 */
public class GuiImage extends GuiWidget {

	private Image image;
	
	/**
	 * Creates a new GUI element for the given image, adjusting the widget's size automatically.
	 * @param newImage
	 */
	public GuiImage(Image newImage) {
		setImage(newImage);
		setSizeToImage();
	}
	
	/**
	 * Looks the image name up and creates a new GUI element for it, adjusting the widget's size automatically.
	 * @param newGraphicName
	 */
	public GuiImage(String newGraphicName) {
		setImage(newGraphicName);
		setSizeToImage();
	}
	
	/**
	 * Looks the image name up and updates this widget's image.
	 * @param newGraphicName
	 */
	public void setImage(String newGraphicName) {
		image = ImageLoader.getInstance().getImage(newGraphicName);
	}
	
	/**
	 * Updates this widget's image to the new one.
	 * @param newImage
	 */
	public void setImage(Image newImage) {
		image = newImage;
	}
	
	/**
	 * Sets this widget's size to it's image size.
	 */
	public void setSizeToImage() {
		if(image == null) {
			setSize(0, 0);
			return;
		}
		setSize(image.getWidth(), image.getHeight());
	}
	
	/**
	 * Render the image to the screen if the element is visible.
	 */
	@Override
	public void render(GameContainer gc, Graphics g) {
		if(!isVisible()) {
			return;
		}
		image.draw(getAbsoluteX(), getAbsoluteY(), getWidth(), getHeight());
	}
}
