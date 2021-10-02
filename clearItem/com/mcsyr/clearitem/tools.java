package com.mcsyr.clearitem;

import java.util.Date;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.Plugin;

public class tools {
  public static void Scheduler() {
    if (Main.ClearItemTime.intValue() != 0)
      Bukkit.getScheduler().runTaskTimerAsynchronously((Plugin)Main.plugin, new Runnable() {
            public void run() {
              Main.time = Integer.valueOf(Main.time.intValue() + 1);
              if (Main.ClearItemTime.intValue() - Main.time.intValue() == 60) {
                Bukkit.getServer().broadcastMessage(Main.ClearItemMessageClearPre.replace("%time%", "60"));
              } else if (Main.ClearItemTime.intValue() - Main.time.intValue() == 10) {
                Bukkit.getServer().broadcastMessage(Main.ClearItemMessageClearPre.replace("%time%", "10"));
              } else if (Main.ClearItemTime.intValue() - Main.time.intValue() <= 0) {
                Bukkit.getServer().broadcastMessage(Main.ClearItemMessageClearStart);
                tools.clearWorld();
                Main.time = Integer.valueOf(0);
                Main.DustbinClearFrequency = Integer.valueOf(Main.DustbinClearFrequency.intValue() + 1);
                if (Main.PublicDustbinEnable.booleanValue() && Main.DustbinClearFrequency.intValue() % Main.PublicDustbinClearInterval.intValue() == 0) {
                  Dustbin.ClearDustbin();
                  Bukkit.getServer().broadcastMessage(Main.PublicDustbinMessageClear);
                } 
              } 
              tools.CheckPlayerDropLock();
            }
          }200L, 20L); 
  }
  
  public static void CheckPlayerDropLock() {
    long date = (new Date()).getTime();
    long playerDate = 0L;
    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
      if (!((Boolean)Main.PlayerDropLock.get(player)).booleanValue()) {
        playerDate = ((Date)Main.PlayerDropLockTime.get(player)).getTime();
        if (date - playerDate > Main.DropTime.intValue()) {
          Main.PlayerDropLock.put(player, Boolean.valueOf(true));
          player.sendMessage(Main.DropMessageOpen);
        } 
      } 
    } 
  }
  
  public static void clearWorld() {
    int index = 0;
    for (World world : Bukkit.getWorlds()) {
      final int finalIndex = ++index;
      Bukkit.getScheduler().runTaskLaterAsynchronously((Plugin)Main.plugin, new Runnable() {
            public void run() {
              tools.clearWorldItem(world, Boolean.valueOf((finalIndex == Bukkit.getWorlds().size())));
            }
          }10L * index);
    } 
  }
  
  public static void clearWorldItem(World world, Boolean isDustbin) {
    int count = 0;
    int DustbinCount = 0;
    List<Entity> Entities = world.getEntities();
    for (Entity ent : Entities) {
      if (ent instanceof Item) {
        Item item = (Item)ent;
        if (!Main.ClearItemWhiteList.contains(item.getItemStack().getType().name())) {
          if (Dustbin.addItem(item.getItemStack()).booleanValue())
            DustbinCount++; 
          count++;
          ent.remove();
        } 
        continue;
      } 
      if (ent instanceof org.bukkit.entity.ItemFrame && Main.ClearItemItemFrame.booleanValue()) {
        count++;
        ent.remove();
        continue;
      } 
      if (ent instanceof org.bukkit.entity.Boat && Main.ClearItemBoat.booleanValue()) {
        count++;
        ent.remove();
        continue;
      } 
      if (ent instanceof org.bukkit.entity.ExperienceOrb && Main.ClearItemExpBall.booleanValue()) {
        count++;
        ent.remove();
        continue;
      } 
      if (ent instanceof org.bukkit.entity.FallingBlock && Main.ClearItemFallingBlock.booleanValue()) {
        count++;
        ent.remove();
        continue;
      } 
      if (ent instanceof org.bukkit.entity.Painting && Main.ClearItemPainting.booleanValue()) {
        count++;
        ent.remove();
        continue;
      } 
      if (ent instanceof org.bukkit.entity.Minecart && Main.ClearItemMinecart.booleanValue()) {
        count++;
        ent.remove();
        continue;
      } 
      if (ent instanceof org.bukkit.entity.Arrow && Main.ClearItemArrow.booleanValue()) {
        count++;
        ent.remove();
        continue;
      } 
      if (ent instanceof org.bukkit.entity.Snowball && Main.ClearItemSnowball.booleanValue()) {
        count++;
        ent.remove();
      } 
    } 
    Main.DustbinCount += DustbinCount;
    Main.WasteTotal = Integer.valueOf(Main.WasteTotal.intValue() + count);
    if (!Main.CleaningTipsEnable.booleanValue() && count > 0)
      Bukkit.getServer().broadcastMessage(Main.ClearItemMessageClearWorld
          .replaceAll("%world%", IncludeWorldAlias(world.getName()))
          .replaceAll("%count%", String.valueOf(count))); 
    if (isDustbin.booleanValue()) {
      if (Main.PublicDustbinEnable.booleanValue())
        Bukkit.getServer().broadcastMessage(Main.PublicDustbinMessageReminder
            .replace("%amount%", String.valueOf(Main.DustbinCount))); 
      if (Main.CleaningTipsEnable.booleanValue())
        Bukkit.getServer().broadcastMessage(Main.ClearItemMessageClear.replaceAll("%count%", String.valueOf(Main.WasteTotal))); 
      Main.DustbinCount = 0;
      Main.WasteTotal = Integer.valueOf(0);
    } 
  }
  
  public static String IncludeWorldAlias(String name) {
    if (Main.Config.getString("CleaningTips.WorldAlias." + name) == null)
      return name; 
    return Main.Config.getString("CleaningTips.WorldAlias." + name);
  }
  
  public static void clearEntityItem(Entity ent) {
    if (ent instanceof Item) {
      Item item = (Item)ent;
      if (!Main.ClearItemWhiteList.contains(item.getItemStack().getType().name()))
        ent.remove(); 
    } else if (ent instanceof org.bukkit.entity.ItemFrame && Main.ClearItemItemFrame.booleanValue()) {
      ent.remove();
    } else if (ent instanceof org.bukkit.entity.Boat && Main.ClearItemBoat.booleanValue()) {
      ent.remove();
    } else if (ent instanceof org.bukkit.entity.ExperienceOrb && Main.ClearItemExpBall.booleanValue()) {
      ent.remove();
    } else if (ent instanceof org.bukkit.entity.FallingBlock && Main.ClearItemFallingBlock.booleanValue()) {
      ent.remove();
    } else if (ent instanceof org.bukkit.entity.Painting && Main.ClearItemPainting.booleanValue()) {
      ent.remove();
    } else if (ent instanceof org.bukkit.entity.Minecart && Main.ClearItemMinecart.booleanValue()) {
      ent.remove();
    } else if (ent instanceof org.bukkit.entity.Arrow && Main.ClearItemArrow.booleanValue()) {
      ent.remove();
    } else if (ent instanceof org.bukkit.entity.Snowball && Main.ClearItemSnowball.booleanValue()) {
      ent.remove();
    } 
  }
  
  public static boolean isIncludedString(List<String> list, String string) {
    if (string == null)
      return false; 
    for (String listString : list) {
      if (string.contains(listString))
        return true; 
    } 
    return false;
  }
  
  public static void TraversePlayer() {
    for (Player player : Bukkit.getServer().getOnlinePlayers())
      initPlayerData(player); 
  }
  
  public static void initPlayerData(Player player) {
    Main.PlayerDropLock.putIfAbsent(player, Boolean.valueOf(true));
    Main.PlayerDropLockTime.putIfAbsent(player, new Date());
    Main.PlayerPrivateDustbin.putIfAbsent(player, Bukkit.createInventory((InventoryHolder)player, Main.PrivateDustbinSize.intValue(), Main.PrivateDustbinName));
  }
}
