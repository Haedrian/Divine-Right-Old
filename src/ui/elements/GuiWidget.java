package ui.elements;

import java.util.Properties;
import java.util.Vector;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * The basic class for GUI elements.
 * Every element has to inherit this class and add it's items via addChild
 * or override the render function as necessary.
 * @author Blay09
 *
 */
public class GuiWidget {
	
	private GuiWidget parent;
	private Vector<GuiWidget> children;
	private int relX;
	private int relY;
	private int absX;
	private int absY;
	private int width;
	private int height;
	private boolean visible;
	private boolean dirtyAbs;
	
	/**
	 * Creates a new, empty widget.
	 */
	public GuiWidget() {
		children = new Vector<GuiWidget>();
	}
	
	/**
	 * Returns the widget's parent or null if it does not have one.
	 * @return
	 */
	public GuiWidget getParent() {
		return parent;
	}
	
	/**
	 * Returns true if this widget has a parent.
	 * @return
	 */
	public boolean hasParent() {
		return (parent != null);
	}
	
	/**
	 * Sets the size of this widget.
	 * @param newWidth
	 * @param newHeight
	 */
	public void setSize(int newWidth, int newHeight) {
		width = newWidth;
		height = newHeight;
	}
	
	/**
	 * Adds a new child to this widget and also sets the new child's parent.
	 * @param newChild
	 */
	public void addChild(GuiWidget newChild) {
		children.add(newChild);
		if(newChild.parent != null) {
			newChild.parent.removeChild(newChild);
		}
		newChild.parent = this;
	}
	
	/**
	 * Removes a child from this widget and resets it's parent to null.
	 * @param remChild
	 */
	public void removeChild(GuiWidget remChild) {
		children.remove(remChild);
		remChild.parent = null;
	}
	
	/**
	 * Returns true if this widget is visible (being rendered and updated).
	 * @return
	 */
	public boolean isVisible() {
		return visible;
	}
	
	/**
	 * Sets this widget's visible flag to true.
	 */
	public void show() {
		setVisible(true);
	}
	
	/**
	 * Sets this widget's visible flag to false.
	 */
	public void hide() {
		setVisible(false);
	}
	
	/**
	 * Sets this widget's visible flag to the given value.
	 * @param newVisible
	 */
	public void setVisible(boolean newVisible) {
		visible = newVisible;
	}
	
	/**
	 * Returns the relative x position for this widget.
	 * @return
	 */
	public int getX() {
		return relX;
	}
	
	/**
	 * Returns the relative y position for this widget.
	 * @return
	 */
	public int getY() {
		return relY;
	}
	
	/**
	 * Returns the absolute x position for this widget.
	 * @return
	 */
	public int getAbsoluteX() {
		recalcAbsolute();
		return absX;
	}
	
	/**
	 * Returns the absolute y position for this widget.
	 * @return
	 */
	public int getAbsoluteY() {
		recalcAbsolute();
		return absY;
	}
	
	/**
	 * Recalculates the absolute coordinates if needed.
	 */
	private void recalcAbsolute() {
		if(!dirtyAbs) {
			return;
		}
		
		if(hasParent()) {
			absX = parent.getAbsoluteX() + relX;
			absY = parent.getAbsoluteY() + relY;
		} else {
			absX = relX;
			absY = relY;
		}
		dirtyAbs = false;
		
		for(int i = 0; i < children.size(); i++) {
			children.get(i).makeDirty();
		}
	}
	
	/**
	 * Tells this widget and it's children to update their absolute positions.
	 */
	public void makeDirty() {
		dirtyAbs = true;
		for(GuiWidget child : children) {
			child.makeDirty();
		}
	}
	
	/**
	 * Sets this widget's width to the given value.
	 * @param newWidth
	 */
	public void setWidth(int newWidth) {
		width = newWidth;
	}
	
	/**
	 * Sets this widget's height to the given value.
	 * @param newWidth
	 */
	public void setHeight(int newHeight) {
		height = newHeight;
	}
	
	/**
	 * Sets this widget's relative position.
	 * @param newX
	 * @param newY
	 */
	public void setPosition(int newX, int newY) {
		setX(newX);
		setY(newY);
	}
	
	/**
	 * Sets this widget's relative x position.
	 * @param newX
	 */
	public void setX(int newX) {
		relX = newX;
		makeDirty();
	}
	
	/**
	 * Sets this widget's relative  yposition.
	 * @param newY
	 */
	public void setY(int newY) {
		relY = newY;
		makeDirty();
	}
	
	/**
	 * Returns true if the given coordinates are inside this widget.
	 * @param checkX
	 * @param checkY
	 * @return
	 */
	public boolean isInside(int checkX, int checkY) {
		if(!visible) {
			return false;
		}
		recalcAbsolute();
		if(checkX > absX && checkX < absX + width) {
			if(checkY > absY && checkY < absY + height) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the width of this widget.
	 * @return
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Returns the height of this widget.
	 * @return
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Passes the save function on to it's children.
	 * Override this function if your widget should save values to the properties file.
	 * Don't forget to call super.save(prop) in the end!
	 * @param prop
	 */
	public void save(Properties prop) {
		for(GuiWidget widget : children) {
			widget.save(prop);
		}
	}
	
	/**
	 * Passes the load function on to it's children while taking care of NumberFormatExceptions automatically.
	 * Override this function if your widget should load values from the properties file.
	 * Don't forget to call super.load(prop) in the end!
	 * @param prop
	 */
	public void load(Properties prop) {
		for(GuiWidget widget : children) {
			try {
				widget.load(prop);
			} catch (NumberFormatException e) {
				System.out.println("Could not load gui element: " + widget.toString());
			}
		}
	}
	
	/**
	 * Passes the mouseClick function on to it's parent.
	 * Override this function if your widget has to react to this event.
	 * Do NOT call it's super function if the event has been handled!
	 * @param mouseButton
	 * @param mouseX
	 * @param mouseY
	 * @param clickCount
	 * @return true if the event was accepted and handled
	 */
	public boolean mouseClicked(int mouseButton, int mouseX, int mouseY, int clickCount) {
		if(parent != null) {
			return parent.mouseClicked(mouseButton, mouseX, mouseY, clickCount);
		}
		return false;
	}
	
	/**
	 * Passes the mouseWheelMoved function on to it's children.
	 * Override this function if your widget has to react to this event.
	 * Don't forget to call it's super function in the end!
	 * @param delta
	 * @param mouseX
	 * @param mouseY
	 * @return true if the event was accepted and handled
	 */
	public boolean mouseWheelMoved(int delta, int mouseX, int mouseY) {
		for(int i = children.size() - 1; i >= 0; i--) {
			if(children.get(i).mouseWheelMoved(delta, mouseX, mouseY)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Passes the mousePressed function on to it's parent.
	 * Override this function if your widget has to react to this event.
	 * Do NOT call it's super function if the event has been handled!
	 * @param delta
	 * @param mouseX
	 * @param mouseY
	 * @return true if the event was accepted and handled
	 */
	public boolean mousePressed(int mouseButton, int mouseX, int mouseY) {
		if(parent != null) {
			return parent.mousePressed(mouseButton, mouseX, mouseY);
		}
		return false;
	}
	
	/**
	 * Passes the mouseReleased function on to it's children.
	 * Override this function if your widget has to react to this event.
	 * Don't forget to call it's super function in the end!
	 * @param delta
	 * @param mouseX
	 * @param mouseY
	 * @return true if the event was accepted and handled
	 */
	public boolean mouseReleased(int mouseButton, int mouseX, int mouseY) {
		for(int i = children.size() - 1; i >= 0; i--) {
			children.get(i).mouseReleased(mouseButton, mouseX, mouseY);
		}
		return false;
	}
	
	/**
	 * Passes the keyPressed function on to it's parent.
	 * Override this function if your widget has to react to this event.
	 * Do NOT call it's super function if the event has been handled!
	 * Also, this will only be called when this widget or one of it's children is the keyboard input focus.
	 * @param keyCode
	 * @param unicode
	 * @return
	 */
	public boolean keyPressed(int keyCode, char unicode) {
		if(parent != null) {
			return parent.keyPressed(keyCode, unicode);
		}
		return false;
	}
	
	/**
	 * Passes the keyReleased function on to it's parent.
	 * Override this function if your widget has to react to this event.
	 * Do NOT call it's super function if the event has been handled!
	 * Also, this will only be called when this widget or one of it's children is the keyboard input focus.
	 * @param keyCode
	 * @param unicode
	 * @return
	 */
	public boolean keyReleased(int keyCode, char unicode) {
		if(parent != null) {
			return parent.keyReleased(keyCode, unicode);
		}
		return false;
	}
	
	/**
	 * Returns true if this children is a proud father of children.
	 * @return
	 */
	public boolean hasChildren() {
		return (!children.isEmpty());
	}
	
	/**
	 * Returns the widget at the given position.
	 * @param checkX
	 * @param checkY
	 * @return
	 */
	public GuiWidget getWidgetAt(int checkX, int checkY) {
		GuiWidget foundChild = getChildAt(checkX, checkY);
		if(foundChild != null) {
			return foundChild.getWidgetAt(checkX, checkY);
		}
		return this;
	}

	private GuiWidget getChildAt(int checkX, int checkY) {
		if(!isVisible()) {
			return null;
		}
		for(int i = children.size() - 1; i >= 0; i--) {
			GuiWidget child = children.get(i);
			if(child.isVisible() && child.isInside(checkX, checkY)) {
				return child;
			}
		}
		return null;
	}
	
	/**
	 * Updates this widget's children.
	 * Override it if you need to update something in your widget, but don't forget to call super.update() and make sure to check for visibility.
	 * @param gc
	 * @param delta
	 */
	public void update(GameContainer gc, int delta) {
		if(!isVisible()) {
			return;
		}
		if(hasChildren()) {
			for(int i = 0; i < children.size(); i++) {
				children.get(i).update(gc, delta);
			}
		}
	}
	
	/**
	 * Renders this widget's children.
	 * Override it if you need to render something manually, but don't forget to call super.render() and make sure to check for visibility.
	 * @param gc
	 * @param delta
	 */
	public void render(GameContainer gc, Graphics g) {
		if(!isVisible()) {
			return;
		}
		if(hasChildren()) {
			for(int i = 0; i < children.size(); i++) {
				children.get(i).render(gc, g);
			}
		}
	}
	
	/**
	 * Removes this widget from it's parent.
	 */
	public void remove() {
		if(parent != null) {
			parent.removeChild(this);
		}
	}

	/**
	 * Removes all children from this widget, resetting their parent to null.
	 */
	public void removeAllChildren() {
		for(int i = 0; i < children.size(); i++) {
			children.get(i).parent = null;
		}
		children.clear();
	}
}
