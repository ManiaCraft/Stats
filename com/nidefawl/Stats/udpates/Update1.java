package com.nidefawl.Stats.udpates;

import com.nidefawl.Stats.Stats;
import java.io.File;

public class Update1
{
  public static void execute(Stats plugin)
  {
    File oldLoc = new File("items.txt");
    if (oldLoc.exists()) {
      Stats.LogInfo("Moving items.txt to " + plugin.getDataFolder().getPath() + "/items.txt");
      oldLoc.renameTo(new File(plugin.getDataFolder(), "items.txt"));
    }
  }
}