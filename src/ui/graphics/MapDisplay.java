package ui.graphics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import managers.GUICommunicationManager;

import objects.common.Coordinate;
import objects.common.GUIObject;
import objects.common.enums.MapOverlay;
import objects.common.enums.MapType;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import ui.common.DivineRightUI;
import ui.common.Viewport;

/**
 * Handles the rendering of the map for both, global and local map.
 * @author Blay09
 *
 */
public class MapDisplay {

	private static final int REORDER_TIME = 100;

	private DivineRightUI parentGame;
	private Camera camera;
	private List<DisplayItem> displayList;
	private Comparator<DisplayItem> displayComp;
	private HashMap<Long, Avatar> actorList;
	private int reorderTime;
	private boolean dirtyOrder;

	public MapDisplay(DivineRightUI newGame) {
		parentGame = newGame;
		camera = parentGame.getCamera();
		
		displayList = new ArrayList<DisplayItem>();
		displayComp = new Comparator<DisplayItem>() {
			@Override
			public int compare(DisplayItem first, DisplayItem second) {
				return 0;
			}
		};
		actorList = new HashMap<Long, Avatar>();
	}
	
	/**
	 * Reorders the display list in an interval set in REORDER_TIME.
	 * Also updates the currently visible items.
	 * @param gc
	 * @param delta
	 */
	public void update(GameContainer gc, int delta) {
		reorderTime += delta;
		if(reorderTime >= REORDER_TIME) {
			reorder();
			reorderTime = 0;
		}
		for(int i = 0; i < displayList.size(); i++) {
			displayList.get(i).update(gc, delta);
		}
	}
	
	/**
	 * Renders all items that are currently visible.
	 * @param gc
	 * @param g
	 */
	public void render(GameContainer gc, Graphics g) {
		camera.applyOffset(g);
		for(int i = 0; i < displayList.size(); i++) {
			displayList.get(i).render(gc, g);
		}
		camera.resetOffset(g);
	}
	
	/**
	 * Forces a reordering of the display list at the next update call.
	 */
	public void markDirty() {
		dirtyOrder = true;
	}
	
	private void reorder() {
		if(camera.viewportChanged()) {
			displayList.clear();
			Viewport newViewport = camera.getViewport();
			MapType mapType = camera.getMapType();
			Coordinate coord = new Coordinate();
			for(int z = newViewport.getStartZ(); z <= newViewport.getEndZ(); z++) {
				for(int y = newViewport.getStartY(); y <= newViewport.getEndY(); y++) {
					for(int x = newViewport.getStartX(); x < newViewport.getEndX(); x++) {
						coord.setX(x);
						coord.setY(y);
						coord.setZ(z);
						GUIObject currentObj = GUICommunicationManager.GetGUIObject(coord, MapOverlay.DEFAULT, mapType);
						if(currentObj != null) {
							
						}
					}
				}
			}
			dirtyOrder = true;
		}
		if(dirtyOrder) {
			Collections.sort(displayList, displayComp);
		}
	}
}
