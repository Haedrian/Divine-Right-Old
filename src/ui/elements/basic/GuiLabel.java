package ui.elements.basic;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import ui.elements.GuiWidget;

/**
 * A simple element to display text in the GUI.
 * This widget is not supposed to have any children.
 * @author Blay09
 *
 */
public class GuiLabel extends GuiWidget {

	private Font font;
	private Color color;
	private String text;
	
	/**
	 * Creates a new GuiLabel with the given values, measuring it's size automatically.
	 * @param newText
	 * @param newColor
	 * @param newFont
	 */
	public GuiLabel(String newText, Color newColor, Font newFont) {
		text = newText;
		color = newColor;
		font = newFont;
		setSizeToLabel();
	}

	/**
	 * Sets the text of this label to the given value.
	 * @param newText
	 */
	public void setText(String newText) {
		text = newText;
	}
	
	/**
	 * Sets the color of this label to the given value.
	 * @param newColor
	 */
	public void setColor(Color newColor) {
		color = newColor;
	}
	
	/**
	 * Updates the size of the widget to fit to the text.
	 */
	public void setSizeToLabel() {
		setWidth(font.getWidth(text));
		setHeight(font.getHeight(text));
	}
	
	/**
	 * Renders the text to the screen, if the element is visible.
	 */
	@Override
	public void render(GameContainer gc, Graphics g) {
		if(!isVisible()) {
			return;
		}
		font.drawString(getAbsoluteX(), getAbsoluteY(), text, color);
	}
}
