package si.psrcek.clickableChatNames.adapters;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.redstoner.MCJSONAPI.JSONArray;
import com.redstoner.MCJSONAPI.JSONProperty;
import com.redstoner.MCJSONAPI.actions.ClickAction;
import com.redstoner.MCJSONAPI.exceptions.MalformedJSONException;
import com.redstoner.MCJSONAPI.objects.ClickEvent;
import com.redstoner.MCJSONAPI.properties.JSONObject;
import com.redstoner.MCJSONAPI.properties.JSONString;

public class ClickableChatPacketAdapter extends PacketAdapter {

	public ClickableChatPacketAdapter(Plugin plugin,
			ListenerPriority listenerPriority, PacketType type) {
		super(plugin, listenerPriority, type);
	}

	@Override
	public void onPacketSending(PacketEvent event) {
		if (event.getPacketType() == PacketType.Play.Server.CHAT) {
			// get the packet
			PacketContainer packet = event.getPacket();
			
			// get the message
			String message = packet.getChatComponents().read(0).getJson();
			String newMessage = null;
			
			try {
				newMessage = formatChatMessage(message);
			} catch (MalformedJSONException e) {
				e.printStackTrace();
			}
			
			if (newMessage != null) {
				
				// set the message
				packet.getChatComponents().write(0, WrappedChatComponent.fromJson(newMessage));
			} else {
				Bukkit.getLogger().info("Converted message: §4null");
			}
		}
	}

	private String formatChatMessage(String message) throws MalformedJSONException {
		JSONObject oldMessage = new JSONObject(message);
		JSONArray extra = (JSONArray) oldMessage.get("extra");
		
		if (extra != null) {
			JSONObject arrow = null;
			
			int chatStart = 0;
			int nameEnd = 0;
			
			for (int i = 1; i < extra.size(); i++) {
				if (extra.get(i) instanceof JSONString) {
					if (((JSONString) extra.get(i)).equals(new JSONString(" "))) {
						arrow = (JSONObject) extra.get(i+1);
						chatStart = i+2;
						nameEnd = i;
						break;
					}
				}
			}
			
			if (chatStart != 0 && nameEnd != 0 && isArrow(arrow)) {
				JSONArray name = new JSONArray();
				JSONArray chat = new JSONArray();
				
				for (int i = 0; i < nameEnd; i++) {
					JSONProperty p = extra.get(i);
					
					if (p instanceof JSONObject) {
						name.add(p);
						
					} else if (p instanceof JSONString) {
						JSONObject tempObj = new JSONObject();
						tempObj.set("text", p);
						name.add(tempObj);
					}
				}
				
				String unColoredName = "";
				
				for (int i = 0; i < name.size(); i++) {
					unColoredName = unColoredName + ((JSONObject) name.get(i)).get("text");
				}
				
				unColoredName = unColoredName.replaceAll("\"", "").trim();
				
				JSONArray name2 = new JSONArray();
				ClickEvent ce = new ClickEvent(ClickAction.RUN_COMMAND, "/user " + unColoredName);
				
				for (int i = 0; i < name.size(); i++) {
					JSONObject text = (JSONObject) name.get(i);
					text.set("clickEvent", ce);
					name2.add(text);
				}
				
				for (int i = chatStart; i < extra.size(); i++) {
					JSONProperty p = extra.get(i);
					
					chat.add(p);
				}
				
				JSONArray newMsg = new JSONArray();
				newMsg.addAll(name2);
				newMsg.add(new JSONString(" "));
				newMsg.add(arrow);
				newMsg.addAll(chat);
				
				return newMsg.toString();
			}
		}
		return message;
	}
	
	private boolean isArrow(JSONObject arrow) {
		if (arrow == null) {
			return false;
		}
		
		if (arrow.get("color") instanceof JSONString) {
			if (((JSONString) arrow.get("color")).equals(new JSONString("gray"))) {
				if (arrow.get("text") instanceof JSONString) {
					if (((JSONString) arrow.get("text")).equals(new JSONString("→ "))) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
