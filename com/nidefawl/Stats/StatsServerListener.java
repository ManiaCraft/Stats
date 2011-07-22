package com.nidefawl.Stats;

import com.nidefawl.Stats.Permissions.GroupManagerResolver;
import com.nidefawl.Stats.Permissions.NijiPermissionsResolver;
import com.nidefawl.Stats.Permissions.defaultResolver;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.ServerListener;
import org.bukkit.plugin.Plugin;
//import org.bukkit.plugin.PluginDescriptionFile;

public class StatsServerListener extends ServerListener
{
  Stats stats = null;

  public StatsServerListener(Stats plugin) { this.stats = plugin; }

  public void onPluginEnable(PluginEnableEvent event)
  {
    Plugin plugin = event.getPlugin();
    String name = plugin.getDescription().getName();
    if (name.equals("GroupManager"))
      this.stats.setPerms(new GroupManagerResolver(plugin));
    else if (name.equals("Permissions"))
      this.stats.setPerms(new NijiPermissionsResolver(plugin));
  }

  public void onPluginDisable(PluginDisableEvent event)
  {
    Plugin plugin = event.getPlugin();
    String name = plugin.getDescription().getName();
    if (name.equals("GroupManager"))
      this.stats.setPerms(new defaultResolver());
    else if (name.equals("Permissions"))
      this.stats.setPerms(new defaultResolver());
  }
}