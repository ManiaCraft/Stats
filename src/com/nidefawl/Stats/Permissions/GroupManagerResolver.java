package com.nidefawl.Stats.Permissions;

import java.util.logging.Logger;
import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.data.Group;
import org.anjocaido.groupmanager.data.User;
//import org.anjocaido.groupmanager.dataholder.OverloadedWorldHolder;
//import org.anjocaido.groupmanager.dataholder.worlds.WorldsHolder;
//import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;
//import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
//import org.bukkit.plugin.PluginManager;

public class GroupManagerResolver
  implements PermissionsResolver
{
  public static final Logger log = Logger.getLogger("Minecraft");
  Plugin plugin = null;
  private GroupManager perms = null;

  public GroupManagerResolver(Plugin plugin) {
    this.plugin = plugin;
  }

  public boolean load()
  {
    if (this.perms == null) {
      Plugin checkPlugin = this.plugin.getServer().getPluginManager().getPlugin("GroupManager");
      if ((checkPlugin != null) && (checkPlugin.isEnabled()) && ((checkPlugin instanceof GroupManager)))
        this.perms = ((GroupManager)checkPlugin);
      else
        return false;
    }
    return true;
  }

  public boolean permission(CommandSender sender, String permCmd)
  {
    if (sender.isOp()) return true;
    if (!(sender instanceof Player)) return false;
    if (!load())
      return false;
    Player player = (Player)sender;
    return this.perms.getWorldsHolder().getWorldData(player).getPermissionsHandler().has(player, permCmd);
  }

  public boolean inGroup(Player player, String group)
  {
    if (!load())
      return false;
    Group g = this.perms.getWorldsHolder().getWorldData(player).getGroup(group);
    if (g == null) return false;
    User u = this.perms.getWorldsHolder().getWorldData(player).getUser(player.getName());
    if (u == null) return false;
    return (u.containsSubGroup(g)) || (u.getGroup().equals(g));
  }

  public void reloadPerms()
  {
  }

  public String getGroup(Player player)
  {
    User u = this.perms.getWorldsHolder().getWorldData(player).getUser(player.getName());
    if (u == null) return "";
    return u.getGroupName();
  }
}