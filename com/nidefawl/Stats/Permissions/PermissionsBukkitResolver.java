package com.nidefawl.Stats.Permissions;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PermissionsBukkitResolver implements PermissionsResolver {

	@Override
	public boolean permission(CommandSender sender, String permCmd) {
		if (sender.isOp()) return true;
		if(!(sender instanceof Player)) return false;
		return sender.hasPermission(permCmd);
	}

	@Override
	public String getGroup(Player player) {
		return "";
	}

	@Override
	public boolean inGroup(Player player, String group) {
		return false;
	}

	@Override
	public boolean load() {
		return true;
	}

	@Override
	public void reloadPerms() {
	}

}
