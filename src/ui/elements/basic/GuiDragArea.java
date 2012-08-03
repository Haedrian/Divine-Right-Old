package ui.elements.basic;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import ui.elements.GuiWidget;

/**
 * Grants ability to drag it's parent around.
 * @author Blay09
 *
 */
public class GuiDragArea extends GuiWidget {
	
	private boolean dragging;
	private int dragStartX;
	private int dragStartY;

	/**
	 * Creates a new drag area, automatically making it visible.
	 */
	public GuiDragArea() {
		setVisible(true);
	}
	
	/**
	 * Updates the parent's position in case the mouse was moved while dragging it.
	 */
	@Override
	public void update(GameContainer gc, int delta) {
		if(dragging) {
			int newX = Math.max(0, gc.getInput().getMouseX() - dragStartX);
			int newY = Math.max(0, gc.getInput().getMouseY() - dragStartY);
			newX = Math.min(gc.getWidth() - getParent().getWidth(), newX);
			newY = Math.min(gc.getHeight() - getParent().getHeight(), newY);
			getParent().setX(newX);
			getParent().setY(newY);
		}
	}
	
	/**
	 * Start the dragging process when the left mouse button is pressed.
	 */
	@Override
	public boolean mousePressed(int mouseButton, int mouseX, int mouseY) {
		if(mouseButton == Input.MOUSE_LEFT_BUTTON) {
			startDragging(mouseX, mouseY);
			super.mousePressed(mouseButton, mouseX, mouseY);
			return true;
		}
		return false;
	}
	
	/**
	 * Stop the dragging process when the left mouse button is released.
	 */
	@Override
	public boolean mouseReleased(int mouseButton, int mouseX, int mouseY) {
		if(mouseButton == Input.MOUSE_LEFT_BUTTON) {
			stopDragging();
			return true;
		}
		return false;
	}
	
	private void startDragging(int startX, int startY) {
		if(dragging) {
			return;
		}
		dragging = true;
		dragStartX = startX - getParent().getX();
		dragStartY = startY - getParent().getY();
	}
	
	private void stopDragging() {
		if(!dragging) {
			return;
		}
		dragging = false;
	}
}
