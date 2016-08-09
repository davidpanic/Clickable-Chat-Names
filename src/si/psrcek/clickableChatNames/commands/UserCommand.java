package si.psrcek.clickableChatNames.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import si.psrcek.clickableChatNames.misc.InventoryRegistry;

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
		
		String selectedName = ChatColor.stripColor(args[0]);
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.getName().contains(selectedName)|| p.getCustomName().contains(selectedName)) {
				selectedPlayer = p;
				break;
			}
		}
		
		if (selectedPlayer == null) {
			player.sendMessage(ChatColor.RED + selectedName + " is not a valid online player!");
			return true;
		}
		
		if (player.hasPermission("clickablechat.staff")) {
			player.openInventory(InventoryRegistry.staffInv);
		} else {
			player.openInventory(InventoryRegistry.playerInv);
		}
		
		return true;
	}

}
