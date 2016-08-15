package si.psrcek.clickableChatNames.misc;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

public class MiscRegistry {
	public static Map<Player, Player> selectedPlayers;
	public static Map<Player, String> banReason;
	
	public static ProtocolManager protocolManager;
	
	public static void init() {
		selectedPlayers = new HashMap<Player, Player>();
		banReason = new HashMap<Player, String>();
		
		protocolManager = ProtocolLibrary.getProtocolManager();
	}
}
