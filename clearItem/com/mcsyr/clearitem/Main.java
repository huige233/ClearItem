package com.mcsyr.clearitem;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
  public static Main plugin;
  
  public static FileConfiguration Config;
  
  public static Boolean DustbinLock = Boolean.valueOf(false);
  
  public static int DustbinCount = 0;
  
  public static Boolean PublicDustbinEnable;
  
  public static String PublicDustbinName;
  
  public static Integer PublicDustbinSize;
  
  public static Integer PublicDustbinClearInterval;
  
  public static List<String> PublicDustbinWhiteListName;
  
  public static String PublicDustbinMessageReminder;
  
  public static String PublicDustbinMessageClear;
  
  public static Boolean PrivateDustbinEnable;
  
  public static String PrivateDustbinName;
  
  public static Integer PrivateDustbinSize;
  
  public static List<String> PrivateDustbinWhiteListName;
  
  public static List<String> PrivateDustbinWhiteListLore;
  
  public static String PrivateDustbinMessageClear;
  
  public static Boolean DropEnable;
  
  public static Integer DropTime;
  
  public static String DropMessageOpen;
  
  public static String DropMessageClose;
  
  public static String DropMessageDiscardInOpen;
  
  public static Integer ClearItemTime;
  
  public static List<String> ClearItemWhiteList;
  
  public static Integer ClearItemChunkMaxItems;
  
  public static Boolean ClearItemItemFrame;
  
  public static Boolean ClearItemBoat;
  
  public static Boolean ClearItemExpBall;
  
  public static Boolean ClearItemFallingBlock;
  
  public static Boolean ClearItemPainting;
  
  public static Boolean ClearItemMinecart;
  
  public static Boolean ClearItemArrow;
  
  public static Boolean ClearItemSnowball;
  
  public static String ClearItemMessageClearPre;
  
  public static String ClearItemMessageClearStart;
  
  public static String ClearItemMessageClear;
  
  public static String ClearItemMessageClearWorld;
  
  public static String ClearItemMessageClearChunkMaxItems;
  
  public static Boolean CleaningTipsEnable;
  
  public static Integer time = Integer.valueOf(0);
  
  public static Integer WasteTotal = Integer.valueOf(0);
  
  public static Integer DustbinClearFrequency = Integer.valueOf(0);
  
  public static Map<Player, Boolean> PlayerDropLock = new HashMap<>();
  
  public static Map<Player, Date> PlayerDropLockTime = new HashMap<>();
  
  public static Map<Player, Inventory> PlayerPrivateDustbin = new HashMap<>();
  
  public void onEnable() {
    (plugin = this).saveDefaultConfig();
    Bukkit.getServer().getConsoleSender().sendMessage(");
    Bukkit.getServer().getConsoleSender().sendMessage("By QQ2032704270");
    Bukkit.getPluginManager().registerEvents(new Event(), (Plugin)this);
    Bukkit.getPluginCommand("clearitem").setExecutor(new command());
    loadConfig();
    tools.Scheduler();
    tools.TraversePlayer();
  }
  
  public static void loadConfig() {
    plugin.reloadConfig();
    Config = plugin.getConfig();
    PublicDustbinEnable = Boolean.valueOf(Config.getBoolean("PublicDustbin.Enable"));
    PublicDustbinName = Config.getString("PublicDustbin.Name").replaceAll("&", ");
    PublicDustbinSize = Integer.valueOf(Config.getInt("PublicDustbin.Size"));
    PublicDustbinClearInterval = Integer.valueOf(Config.getInt("PublicDustbin.ClearInterval"));
    PublicDustbinWhiteListName = Config.getStringList("PublicDustbin.WhiteListName");
    PublicDustbinMessageReminder = Config.getString("PublicDustbin.Message.Reminder").replaceAll("&", ");
    PublicDustbinMessageClear = Config.getString("PublicDustbin.Message.Clear").replaceAll("&", ");
    PrivateDustbinEnable = Boolean.valueOf(Config.getBoolean("PrivateDustbin.Enable"));
    PrivateDustbinName = Config.getString("PrivateDustbin.Name").replaceAll("&", ");
    PrivateDustbinSize = Integer.valueOf(Config.getInt("PrivateDustbin.Size"));
    PrivateDustbinWhiteListName = Config.getStringList("PrivateDustbin.WhiteListName");
    PrivateDustbinWhiteListLore = Config.getStringList("PrivateDustbin.WhiteListLore");
    PrivateDustbinMessageClear = Config.getString("PrivateDustbin.Message.Clear").replaceAll("&", ");
    DropEnable = Boolean.valueOf(Config.getBoolean("Drop.Enable"));
    DropTime = Integer.valueOf(Config.getInt("Drop.Time") * 1000);
    DropMessageOpen = Config.getString("Drop.Message.Open").replaceAll("&", ");
    DropMessageClose = Config.getString("Drop.Message.Close").replaceAll("&", ");
    DropMessageDiscardInOpen = Config.getString("Drop.Message.DiscardInOpen").replaceAll("&", ");
    ClearItemTime = Integer.valueOf(Config.getInt("ClearItem.Time"));
    ClearItemChunkMaxItems = Integer.valueOf(Config.getInt("ClearItem.ChunkMaxItems"));
    ClearItemWhiteList = Config.getStringList("ClearItem.WhiteList");
    ClearItemItemFrame = Boolean.valueOf(Config.getBoolean("ClearItem.ItemFrame"));
    ClearItemBoat = Boolean.valueOf(Config.getBoolean("ClearItem.Boat"));
    ClearItemExpBall = Boolean.valueOf(Config.getBoolean("ClearItem.ExpBall"));
    ClearItemFallingBlock = Boolean.valueOf(Config.getBoolean("ClearItem.FallingBlock"));
    ClearItemPainting = Boolean.valueOf(Config.getBoolean("ClearItem.Painting"));
    ClearItemMinecart = Boolean.valueOf(Config.getBoolean("ClearItem.Minecart"));
    ClearItemArrow = Boolean.valueOf(Config.getBoolean("ClearItem.Arrow"));
    ClearItemSnowball = Boolean.valueOf(Config.getBoolean("ClearItem.Snowball"));
    ClearItemMessageClearPre = Config.getString("ClearItem.Message.ClearPre").replaceAll("&", ");
    ClearItemMessageClearStart = Config.getString("ClearItem.Message.ClearStart").replaceAll("&", ");
    ClearItemMessageClear = Config.getString("ClearItem.Message.Clear").replaceAll("&", ");
    ClearItemMessageClearWorld = Config.getString("ClearItem.Message.ClearWorld").replaceAll("&", ");
    ClearItemMessageClearChunkMaxItems = Config.getString("ClearItem.Message.ClearChunkMaxItems").replaceAll("&", ");
    CleaningTipsEnable = Boolean.valueOf(Config.getBoolean("CleaningTips.Enable"));
  }
}
