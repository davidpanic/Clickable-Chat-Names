package si.psrcek.clickableChatNames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import si.psrcek.clickableChatNames.misc.InventoryRegistry;
import si.psrcek.clickableChatNames.misc.MiscRegistry;

public class InventoryClickListener implements Listener {
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (e.getInventory().equals(InventoryRegistry.playerInv) || e.getInventory().equals(InventoryRegistry.staffInv)) {
			e.setCancelled(true);
			
			ItemStack clickedItem = e.getCurrentItem();
			
			Player player = (Player) e.getWhoClicked();
			String selectedName = MiscRegistry.selectedPlayers.get(player).getName();
			
			String cmd = null;
			boolean shouldClose = true;
			
			if (clickedItem.equals(InventoryRegistry.website)) {
				player.sendMessage(ChatColor.AQUA + "The player's website account: " + ChatColor.YELLOW + "website url here");
			}
			else if (clickedItem.equals(InventoryRegistry.plot))    cmd = "p h";
			else if (clickedItem.equals(InventoryRegistry.tpa))     cmd = "tpa";
			else if (clickedItem.equals(InventoryRegistry.tpaHere)) cmd = "tpahere";
			else if (clickedItem.equals(InventoryRegistry.tp))      cmd = "tp";
			else if (clickedItem.equals(InventoryRegistry.tpo))     cmd = "tpo";
			else if (clickedItem.equals(InventoryRegistry.promote)) cmd = "promote";
			else if (clickedItem.equals(InventoryRegistry.demote))  cmd = "demote";
			
			else if (clickedItem.equals(InventoryRegistry.kick)) {
				shouldClose = false;
				player.openInventory(InventoryRegistry.kickInv);
				
			} else if (clickedItem.equals(InventoryRegistry.ban)) {
				shouldClose = false;
				player.openInventory(InventoryRegistry.banInv);
				
			} else if (clickedItem.equals(InventoryRegistry.blankSlot)) {
				shouldClose = false;
				
			}
			
			
			if (cmd != null) {
				Bukkit.dispatchCommand(player, cmd + " " + selectedName);
			}
			
			if (shouldClose) {
				player.closeInventory();
				MiscRegistry.selectedPlayers.remove(player);
			}
		} else if (e.getInventory().equals(InventoryRegistry.kickInv) || e.getInventory().equals(InventoryRegistry.banInv)) {
			e.setCancelled(true);
			
			ItemStack clickedItem = e.getCurrentItem();
			
			Player player = (Player) e.getWhoClicked();
			String selectedName = MiscRegistry.selectedPlayers.get(player).getName();
			
			String cmd = null;
			boolean shouldClose = true;
			boolean ban = false;
			
			if (clickedItem.equals(InventoryRegistry.kickCaps))             cmd = "caps";
			else if (clickedItem.equals(InventoryRegistry.kickSpam))        cmd = "spam";
			else if (clickedItem.equals(InventoryRegistry.kickAdvertising)) cmd = "advertising";
			else if (clickedItem.equals(InventoryRegistry.kickEntitySpam))  cmd = "entity spam";
			else if (clickedItem.equals(InventoryRegistry.banCaps)) {
				MiscRegistry.banReason.put(player, "caps");
				ban = true;
				
			} else if (clickedItem.equals(InventoryRegistry.banSpam)) {
				MiscRegistry.banReason.put(player, "spam");
				ban = true;
				
			} else if (clickedItem.equals(InventoryRegistry.banAdvertising)) {
				MiscRegistry.banReason.put(player, "advertising");
				ban = true;
				
			} else if (clickedItem.equals(InventoryRegistry.banEntitySpam)) {
				MiscRegistry.banReason.put(player, "entity spam");
				ban = true;
				
			} else if (clickedItem.equals(InventoryRegistry.banExploitingBugs)) {
				MiscRegistry.banReason.put(player, "exploiting bugs");
				ban = true;
				
			} else if (clickedItem.equals(InventoryRegistry.banCheating)) {
				MiscRegistry.banReason.put(player, "cheating in sv");
				ban = true;
				
			} else if (clickedItem.equals(InventoryRegistry.banServerCrash)) {
				MiscRegistry.banReason.put(player, "server crash");
				ban = true;
				
			} else if (clickedItem.equals(InventoryRegistry.banGriefing)) {
				MiscRegistry.banReason.put(player, "griefing");
				ban = true;
				
			} else if (clickedItem.equals(InventoryRegistry.blankSlot)) {
				shouldClose = false;
				
			}
			
			if (ban) {
				shouldClose = false;
				player.openInventory(InventoryRegistry.banTimeInv);
			} else if (cmd != null) {
				Bukkit.dispatchCommand(player, "kick " + selectedName + " " + cmd);
			}
			
			if (shouldClose) {
				player.closeInventory();
				MiscRegistry.selectedPlayers.remove(player);
			}
		} else if (e.getInventory().equals(InventoryRegistry.banTimeInv)) {
			e.setCancelled(true);
			
			ItemStack clickedItem = e.getCurrentItem();
			
			Player player = (Player) e.getWhoClicked();
			String time = null;
			
			boolean ban = true;
			
			if (clickedItem.equals(InventoryRegistry.time1h))        time = "1h";
			else if (clickedItem.equals(InventoryRegistry.time3h))   time = "3h";
			else if (clickedItem.equals(InventoryRegistry.time5h))   time = "5h";
			else if (clickedItem.equals(InventoryRegistry.time12h))  time = "12h";
			else if (clickedItem.equals(InventoryRegistry.time1d))   time = "1d";
			else if (clickedItem.equals(InventoryRegistry.time3d))   time = "3d";
			else if (clickedItem.equals(InventoryRegistry.time1w))   time = "1w";
			else if (clickedItem.equals(InventoryRegistry.time1mo))  time = "30d";
			else if (clickedItem.equals(InventoryRegistry.timePerm)) time = "perm";
			else if (clickedItem.equals(InventoryRegistry.blankSlot)) ban = false;
			
			if (ban) {
				String selectedName = MiscRegistry.selectedPlayers.get(player).getName();
				String banReason = MiscRegistry.banReason.get(player);
				
				if (time.equals("perm")) {
					Bukkit.dispatchCommand(player, "ban " + selectedName + " " + banReason);
				} else {
					Bukkit.dispatchCommand(player, "tempban " + selectedName + " " + time + "1s " + banReason);
				}
				
				player.closeInventory();
				MiscRegistry.selectedPlayers.remove(player);
				MiscRegistry.banReason.remove(player);
			}
		}
	}
}
