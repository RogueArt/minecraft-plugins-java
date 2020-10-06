package me.rogueart.trident;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {
	
	public List<String> list = new ArrayList<String>();
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(new TridentEvents(), this);
	}
	
	@Override
	public void onDisable() {}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("trident")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("*Console gets a trident*");
				return true;
			}
			Player player = (Player) sender;
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bYou have received the &c&lAncient Trident&b!"));
			giveItem(player);
		}
		return false;
	}
	
	public void giveItem(Player player) {
		ItemStack item = new ItemStack(Material.TRIDENT);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		
		// Lore block
		meta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Ancient Trident");
		lore.add("");
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7(Right Click) &a&oSpawn minions"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7(Left Click) &a&oShoot explosives"));
		meta.setLore(lore);
		
		// Enchant block
		meta.addEnchant(Enchantment.LOYALTY, 10, true);
		meta.addEnchant(Enchantment.DURABILITY, 10, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		
		item.setItemMeta(meta);
		
		player.getInventory().addItem(item);
	}
	
}