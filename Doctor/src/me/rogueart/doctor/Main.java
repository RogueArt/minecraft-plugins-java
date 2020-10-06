package me.rogueart.doctor;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		this.getCommand("Doctor").setExecutor(new Heal());
		this.getCommand("Feed").setExecutor(new Feed());
	}
	
	@Override
	public void onDisable() {
		
	}

	
}
