package si.psrcek.clickableChatNames.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import si.psrcek.clickableChatNames.misc.InventoryRegistry;
import si.psrcek.clickableChatNames.misc.MiscRegistry;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;

public class UserCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command can only be executed in-game!");
			return true;
		}
		
		Player player = (Player) sender;
		Player selectedPlayer = null;
		
		if (args.length != 1) {
			player.sendMessage(ChatColor.RED + "Usage: " + command.getUsage());
			return true;
		}
		
		String selectedName = args[0];
		
		Plugin essPl = Bukkit.getServer().getPluginManager().getPlugin("Essentials");
		Essentials essentials = null;
		
		if (essPl != null) {
			essentials = (Essentials) essPl;
		}
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.getName().equals(selectedName)) {
				selectedPlayer = p;
				break;
			} else if (p.getCustomName() != null) {
				if (p.getCustomName().equals(selectedName)) {
					selectedPlayer = p;
					break;
				}
			} else if (essPl != null) {
				User user = new User(p, essentials);
				
				if (user != null) {
					String nickname = user.getNickname();
					
					if (nickname != null) {
						nickname = nickname.replaceAll("&.", "").replaceAll("§.", "");
						
						if (nickname.equals(selectedName)) {
							selectedPlayer = p;
							break;
						}
					}
				}
			}
			
		}
		
		if (selectedPlayer == null) {
			player.sendMessage(ChatColor.RED + selectedName + " is not a valid online player!");
			return true;
		}
		
		MiscRegistry.selectedPlayers.put(player, selectedPlayer);
		
		if (player.hasPermission("clickablechat.staff")) {
			player.openInventory(InventoryRegistry.staffInv);
		} else {
			player.openInventory(InventoryRegistry.playerInv);
		}
		
		return true;
	}

}
