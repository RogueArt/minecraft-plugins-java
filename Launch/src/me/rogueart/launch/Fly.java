package me.rogueart.launch;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("launch") || label.equalsIgnoreCase("lch")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("*console goes flying*");
				return true;
			}
			Player player = (Player) sender;
			if (args.length == 0) {
				player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Zooooom!");
				player.setVelocity(player.getLocation().getDirection().multiply(2).setY(2));
				return true;
			}
			if (isNum(args[0])) {
				player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Zooooom!");
				player.setVelocity(player.getLocation().getDirection().multiply(Integer.parseInt(args[0])).setY(2));
			}
			else {
				player.sendMessage(ChatColor.DARK_RED + "Usage: /launch <integer value>");
			}
			return true;
		}
		return false;
	}
	
	public boolean isNum(String num) {
		try {
			Integer.parseInt(num);
			return true;
		} 
		catch (Exception e) {
			return false;
		}
	}
}
