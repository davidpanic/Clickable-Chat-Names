package si.psrcek.clickableChatNames.adapters;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedChatComponent;

import com.redstoner.MCJSONAPI.actions.ClickAction;
import com.redstoner.MCJSONAPI.arrays.MCText;
import com.redstoner.MCJSONAPI.objects.ClickEvent;

public class ClickableChatPacketAdapter extends PacketAdapter {

	public ClickableChatPacketAdapter(Plugin plugin, ListenerPriority listenerPriority, PacketType type) {
		super(plugin, listenerPriority, type);
	}
	
	@Override
	public void onPacketSending(PacketEvent event) {
		// get old message
		PacketContainer packet = event.getPacket();
		String message = packet.getStrings().read(0);
		
		// cancel the old packet
		event.setCancelled(true);
		
		// make a new packet
		PacketContainer chat = new PacketContainer(PacketType.Play.Server.CHAT);
		chat.getChatComponents().write(0, WrappedChatComponent.fromJson(formatChatMessage(message)));
		
		// try and send the new packet
		try {
			ProtocolLibrary.getProtocolManager().sendServerPacket(event.getPlayer(), chat);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	private String formatChatMessage(String oldMessage) {
		if (oldMessage.contains("→")) {
			String name = ChatColor.stripColor(oldMessage.split("→")[0]).trim();
			
			MCText newMessage = new MCText(oldMessage);
			newMessage.assign(new ClickEvent(ClickAction.RUN_COMMAND, "/user " + name), 0);
			
			return newMessage.toString();
		} else {
			return oldMessage;
		}
	}
	
//	private String stripColors(String toStrip) {
//		return toStrip.replaceAll("§.", "");
//	}
}
