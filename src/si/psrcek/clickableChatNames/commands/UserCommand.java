package si.psrcek.clickableChatNames.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UserCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command can only be executed in-game!");
			return true;
		}
		
		Player player = (Player) sender;
		
		player.sendMessage(ChatColor.BLUE + "You tried to view " + args[0] + "'s options, but this is not implemented yet!");
		
		return true;
	}

}
