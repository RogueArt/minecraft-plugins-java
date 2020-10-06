package me.rogueart.changeteam;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {

	public Inventory inv;
		this.getServer().getPluginManager().registerEvents(this, this);
	@Override
	public void onEnable() {
		createInv();
	}

	@Override
	public void onDisable() {
		
	}

	// Command
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("changeteam")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("*Console has changed team*");
				return true;
			}
			Player player = (Player) sender;
			// open the GUI
		}
		return false;
	}
	
	// Event listener for clicking material in GUI
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if ((!event.getInventory().equals(inv)) 
		|| (event.getCurrentItem() == null)
		|| (event.getCurrentItem().getItemMeta() == null)
		|| (event.getCurrentItem().getItemMeta().getDisplayName() == null))
			return;
		
		event.setCancelled(true);
		
		Player player = (Player) event.getWhoClicked();
		if (event.getSlot() == 0) {
			ItemStack[] armor = player.getEquipment().getArmorContents();
		}
	}
	
	// Helper method for changing armor color
	public ItemStack[] changeColor(ItemStack[] a, Color color) {
		for (ItemStack item: a) {
			try {
				Material mat = item.getType();
				if (mat == Material.LEATHER_BOOTS || mat == Material.LEATHER_CHESTPLATE 
				|| mat == Material.LEATHER_LEGGINGS || mat == Material.LEATHER_HELMET) {
					LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
					meta.setColor(color);
					item.setItemMeta(meta);
				}
			}
			catch (Exception e) {
				// do nothing
			}
		}
		return null;
	}
	
	// Method creates inventory at start of server launch
	public void createInv() {
		inv = Bukkit.createInventory(null, 9, ChatColor.GOLD + "" + ChatColor.BOLD + "Select Team");
		ItemStack item = new ItemStack(Material.BLUE_CONCRETE);
		ItemMeta meta = item.getItemMeta();
		
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "Click to join team!");
		meta.setLore(lore);
		
		// Add in teams 
		Object[][] arr = {{ChatColor.DARK_BLUE + "BLUE TEAM", Material.BLUE_CONCRETE}, 
						{ChatColor.DARK_RED + "RED TEAM", Material.RED_CONCRETE},
						{ChatColor.DARK_GREEN + "GREEN TEAM", Material.GREEN_CONCRETE},
						{ChatColor.GOLD + "ORANGE TEAM", Material.ORANGE_CONCRETE},
						{ChatColor.DARK_PURPLE + "PURPLE TEAM", Material.PURPLE_CONCRETE},
						{ChatColor.AQUA + "CYAN TEAM", Material.CYAN_CONCRETE},
						{ChatColor.DARK_GRAY + "BLACK TEAM", Material.BLACK_CONCRETE}};
		
		for (int x = 0; x < 7; x++) {
			item.setType((Material) arr[x][1]);
			meta.setDisplayName((String) arr[x][0]);
			item.setItemMeta(meta);
			inv.setItem(x, item);
		}
		
		// Close button
		item.setType(Material.BARRIER);
		meta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Close Menu");
		lore.clear();
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(8, item);
	}
}
