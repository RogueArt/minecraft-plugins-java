package me.rogueart.doctor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Feed implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("feed")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("*feeds console some food*");
				return true;
			}
			Player player = (Player) sender;
			if (!player.hasPermission("feed.use")) {
				sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this commmand.");
			}
			if (args.length == 0) {
				TextComponent message = new TextComponent("Are you hungry?");
				message.setColor(ChatColor.GREEN);
				message.setBold(true);
				message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/feed me"));
				message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
						new ComponentBuilder("Click here to be fed!").color(ChatColor.RED).italic(true).create()));
				player.spigot().sendMessage(message);
				return true;
			}
			if (args[0].equalsIgnoreCase("me")) {
				player.setFoodLevel(20);
				player.sendMessage("You've been fed!");
				return true;
			}
		}
		return false;
	}

}
