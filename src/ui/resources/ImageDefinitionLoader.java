package ui.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Loads extra values for image files like on-map-offsets and animation data.
 * @author Blay09
 *
 */
public class ImageDefinitionLoader {
	
	private static final String IMAGE_DEF_PATH = "res/def/images.xml";
	
	private HashMap<String, ImageDefinition> table;
	
	/**
	 * Initializes the loader and reads the data from the definition xml.
	 */
	public ImageDefinitionLoader() {
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream(IMAGE_DEF_PATH);
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
			docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(is);
			doc.getDocumentElement().normalize();
			load(doc);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ImageDefinition getDefinition(String name) {
		return table.get(name);
	}
	
	private void load(Document doc) {
		NodeList listItems = doc.getElementsByTagName("image");
		int totalItems = listItems.getLength();
		for(int resourceIdx = 0; resourceIdx < totalItems; resourceIdx++){
        	Node resourceNode = listItems.item(resourceIdx);
        	if(resourceNode.getNodeType() == Node.ELEMENT_NODE){
        		Element resourceElement = (Element)resourceNode;
        		String imageName = resourceElement.getAttribute("name");
        		if(imageName == null) {
        			continue;
        		}
        		ImageDefinition newDef = new ImageDefinition();
        		newDef.setOffsetX(getAttributeAsInt(resourceElement, "offsetX", 0));
        		newDef.setOffsetY(getAttributeAsInt(resourceElement, "offsetY", 0));
        		newDef.setFrameWidth(getAttributeAsInt(resourceElement, "frameWidth", 0));
        		newDef.setFrameHeight(getAttributeAsInt(resourceElement, "frameHeight", 0));
        		table.put(imageName, newDef);
        	}
        }
	}
	
	private int getAttributeAsInt(Element element, String attribName, int defaultVal) {
		String ret = element.getAttribute(attribName);
		if(ret == null || ret.isEmpty()) {
			return defaultVal;
		}
		int iRet;
		try {
			iRet = Integer.parseInt(ret);
		} catch(NumberFormatException e) {
			return defaultVal;
		}
		return iRet;
	}
}
