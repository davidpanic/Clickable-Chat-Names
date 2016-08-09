package si.psrcek.clickableChatNames.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		
		// this is gonna be used to detect gui clicks.
		
		player.sendMessage(ChatColor.GREEN + "You clicked an inventory!");
	}

}
