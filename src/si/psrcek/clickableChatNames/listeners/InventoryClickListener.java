package si.psrcek.clickableChatNames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import si.psrcek.clickableChatNames.Main;
import si.psrcek.clickableChatNames.misc.InventoryRegistry;

public class InventoryClickListener implements Listener {
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (e.getInventory().equals(InventoryRegistry.playerInv) || e.getInventory().equals(InventoryRegistry.staffInv)) {
			e.setCancelled(true);
			
			ItemStack clickedItem = e.getCurrentItem();
			
			Player player = (Player) e.getWhoClicked();
			String selectedName = Main.selectedPlayers.get(player).getName();
			
			String cmd = null;
			
			if (clickedItem.equals(InventoryRegistry.website)) {
				player.sendMessage(ChatColor.AQUA + "The player's website account: " + ChatColor.YELLOW + "website url here");
			}
			else if (clickedItem.equals(InventoryRegistry.plot))    cmd = "p h";
			else if (clickedItem.equals(InventoryRegistry.tpa))     cmd = "tpa";
			else if (clickedItem.equals(InventoryRegistry.tpaHere)) cmd = "tpahere";
			else if (clickedItem.equals(InventoryRegistry.tp))      cmd = "tp";
			else if (clickedItem.equals(InventoryRegistry.tpo))     cmd = "tpo";
			else if (clickedItem.equals(InventoryRegistry.kick))    cmd = "kick";
			else if (clickedItem.equals(InventoryRegistry.ban))     cmd = "ban";
			else if (clickedItem.equals(InventoryRegistry.promote)) cmd = "promote";
			else if (clickedItem.equals(InventoryRegistry.demote))  cmd = "demote";
			
			if (cmd != null) {
				Bukkit.dispatchCommand(player, cmd + " " + selectedName);
			}
			
			player.closeInventory();
			Main.selectedPlayers.remove(player);
		}
	}
}
