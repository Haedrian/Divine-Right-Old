package objects.mapItems;

import java.util.List;

import objects.common.Action;
import objects.common.MapItem;
import objects.common.Message;
import objects.common.enums.ActionType;
import objects.common.enums.MessageType;

public class Llama extends MapItem 

{
	public Llama()
	{
		//Set everything
		setGraphic("llama");
		setName("Llama");
		setWalkable(false);
		
	}
	
	@Override
	public Message use()
	{
		Message message = new Message();
		message.setMessageContents("You stroke the llama. No that's not a euphamism");
		message.setType(MessageType.TOASTER);
		
		return message;
	}

	@Override
	public Message performAction(Action action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActionType> showSupportedActions() {
		// TODO Auto-generated method stub
		return null;
	}

}
