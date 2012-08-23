package ui.elements;

import objects.common.enums.MessageType;
import objects.common.messages.Message;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * The main class for the UI elements. 
 * Takes care of all kinds of windows, like the inventory for example.
 * @author Blay09
 *
 */
public class GUI {
	
	private static GUI instance;
	
	private WidgetLogMessageBox messageLog;
	
	private GuiWidget rootNode;
	private GuiWidget focusWidget;
	private MoveableGuiWidget foregroundWidget;
	private boolean mouseInside;
	
	private GUI() {
	}
	
	/**
	 * Initialize all of the GUI's elements.
	 * @param gc
	 */
	public void init(GameContainer gc) {
		rootNode = new GuiWidget();
		rootNode.setSize(gc.getWidth(), gc.getHeight());
		rootNode.show();
		
		messageLog = new WidgetLogMessageBox(gc);
		messageLog.setPosition(5, gc.getHeight() - messageLog.getHeight() - 5);
		messageLog.show();
		rootNode.addChild(messageLog);
	}
	
	/**
	 * Passes the update call on to all GUI elements.
	 * @param gc
	 * @param g
	 */
	public void update(GameContainer gc, int delta) {
		mouseInside = isInside(gc.getInput().getMouseX(), gc.getInput().getMouseY());
		rootNode.update(gc, delta);
	}
	
	/**
	 * Passes the render call on to all GUI elements.
	 * @param gc
	 * @param g
	 */
	public void render(GameContainer gc, Graphics g) {
		rootNode.render(gc, g);
	}
	
	/**
	 * Returns the singleton instance of this class.
	 * @return
	 */
	public static GUI getInstance() {
		if(instance == null) {
			instance = new GUI();
		}
		return instance;
	}
	
	/**
	 * Sets the keyboard input focus.
	 * @param newWidget
	 */
	public void setFocus(GuiWidget newWidget) {
		focusWidget = newWidget;
	}
	
	/**
	 * Returns the current keyboard input focus.
	 * @return
	 */
	public GuiWidget getFocus() {
		return focusWidget;
	}
	
	/**
	 * Returns the moveable widget that is currently active.
	 * @return
	 */
	public MoveableGuiWidget getForegroundWidget() {
		return foregroundWidget;
	}

	/**
	 * Sets the foreground widget. Only works for direct children of the root node.
	 * @param moveableGuiWidget
	 */
	public void setForegroundWidget(MoveableGuiWidget moveableGuiWidget) {
		if(moveableGuiWidget == null) {
			foregroundWidget = null;
			return;
		}
		if(moveableGuiWidget.getParent() != rootNode) {
			System.out.println("Can't set a nested child as foreground widget.");
			return;
		}
		rootNode.removeChild(moveableGuiWidget);
		rootNode.addChild(moveableGuiWidget);
		foregroundWidget = moveableGuiWidget;
	}
	
	/**
	 * Passes the key press event on to the current focus widget.
	 * @param keyCode
	 * @param unicode
	 * @return true if the event was accepted and handled
	 */
	public boolean keyPressed(int keyCode, char unicode) {
		if(focusWidget == null) {
			return false;
		}
		return focusWidget.keyPressed(keyCode, unicode);
	}
	
	/**
	 * Passes the key release event on to the current focus widget.
	 * @param keyCode
	 * @param unicode
	 * @return true if the event was accepted and handled
	 */
	public boolean keyReleased(int keyCode, char unicode) {
		if(focusWidget == null) {
			return false;
		}
		return focusWidget.keyReleased(keyCode, unicode);
	}
	
	/**
	 * Passes the mouse press event on to the widget at the current mouse position.
	 * @param mouseButton
	 * @param mouseX
	 * @param mouseY
	 * @return true if the event was accepted and handled
	 */
	public boolean mousePressed(int mouseButton, int mouseX, int mouseY) {
		GuiWidget target = rootNode.getWidgetAt(mouseX, mouseY);
		if(target != null) {
			return target.mousePressed(mouseButton, mouseX, mouseY);
		}
		return false;
	}
	
	/**
	 * Passes the mouse release event on to all widgets.
	 * @param mouseButton
	 * @param mouseX
	 * @param mouseY
	 * @return true if the event was accepted and handled
	 */
	public boolean mouseReleased(int mouseButton, int mouseX, int mouseY) {
		return rootNode.mouseReleased(mouseButton, mouseX, mouseY);
	}
	
	/**
	 * Passes the mouse click event on to the widget at the current mouse position.
	 * @param mouseButton
	 * @param mouseX
	 * @param mouseY
	 * @param clickCount
	 * @return true if the event was accepted and handled
	 */
	public boolean mouseClicked(int mouseButton, int mouseX, int mouseY, int clickCount) {
		GuiWidget target = rootNode.getWidgetAt(mouseX, mouseY);
		if(target != null) {
			return target.mouseClicked(mouseButton, mouseX, mouseY, clickCount);
		}
		return false;
	}
	
	/**
	 * Passes the mouse wheel move event on to the widget at the current mouse position.
	 * @param delta
	 * @param mouseX
	 * @param mouseY
	 * @return true if the event was accepted and handled
	 */
	public boolean mouseWheelMoved(int delta, int mouseX, int mouseY) {
		return rootNode.mouseWheelMoved(delta, mouseX, mouseY);
	}
	
	/**
	 * Returns true if the mouse is currently above a GUI element.
	 * @return
	 */
	public boolean isMouseInside() {
		return mouseInside;
	}
	
	/**
	 * Adds the message to the requested message renderer widget.
	 * @param message
	 */
	public void addMessage(Message message) {
		MessageType mt = message.getType();
		switch(mt) {
		case EMPTY:
			return;
		case TOAST:
			break;
		case LOG:
			messageLog.addMessage(message);
			break;
		case MODAL:
			break;
		case CHOICE:
			break;
		}
	}
	
	private boolean isInside(int mouseX, int mouseY) {
		GuiWidget widget = rootNode.getWidgetAt(mouseX, mouseY);
		if(widget == rootNode || widget == null) {
			return false;
		}
		return true;
	}

}
