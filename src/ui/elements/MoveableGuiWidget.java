package ui.elements;

import ui.elements.basic.GuiDragArea;

/**
 * An extended GuiWidget class which already implements support for non-static elements.
 * @author Blay09
 *
 */
public abstract class MoveableGuiWidget extends GuiWidget {
	
	private GuiDragArea dragArea;
	
	/**
	 * Initializes the drag area of this widget.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void setupDragArea(int x, int y, int width, int height) {
		dragArea = new GuiDragArea();
		dragArea.setPosition(x, y);
		dragArea.setSize(width, height);
		addChild(dragArea);
	}
	
	/**
	 * Move this widget to the foreground as soon as it it clicked.
	 */
	@Override
	public boolean mousePressed(int mouseButton, int mouseX, int mouseY) {
		if(GUI.getInstance().getForegroundWidget() != this) {
			GUI.getInstance().setForegroundWidget(this);
			return true;
		}
		return false;
	}
	
	/**
	 * If made visible, automatically put it to the foreground.
	 * If hidden, remove it from the foreground.
	 */
	@Override
	public void setVisible(boolean newVisible) {
		super.setVisible(newVisible);
		if(newVisible) {
			GUI.getInstance().setForegroundWidget(this);
		} else if(GUI.getInstance().getForegroundWidget() == this) {
			GUI.getInstance().setForegroundWidget(null);
		}
	}
}
