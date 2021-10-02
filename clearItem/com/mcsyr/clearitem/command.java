package com.mcsyr.clearitem;

import java.util.Date;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class command implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (sender instanceof Player) {
      if (args.length == 1) {
        Player player = (Player)sender;
        if (args[0].equalsIgnoreCase("open")) {
          if (Main.PublicDustbinEnable.booleanValue()) {
            ((Player)sender).openInventory(Dustbin.Dustbin);
            sender.sendMessage("+ Main.PublicDustbinName);
          } else {
            sender.sendMessage(Main.PublicDustbinName + ");
          } 
          return true;
        } 
        if (args[0].equalsIgnoreCase("drop")) {
          if (Main.DropEnable.booleanValue()) {
            if (((Boolean)Main.PlayerDropLock.get(player)).booleanValue()) {
              Main.PlayerDropLock.put(player, Boolean.valueOf(false));
              Main.PlayerDropLockTime.put(player, new Date());
              player.sendMessage(Main.DropMessageClose);
            } else {
              Main.PlayerDropLock.put(player, Boolean.valueOf(true));
              player.sendMessage(Main.DropMessageOpen);
            } 
          } else {
            sender.sendMessage(");
          } 
          return true;
        } 
        if (args[0].equalsIgnoreCase("discard")) {
          if (Main.PrivateDustbinEnable.booleanValue()) {
            player.openInventory(Main.PlayerPrivateDustbin.get(player));
            sender.sendMessage("+ Main.PrivateDustbinName);
          } else {
            sender.sendMessage(Main.PrivateDustbinName + ");
          } 
          return true;
        } 
        if (sender.isOp()) {
          if (args[0].equalsIgnoreCase("reload")) {
            Main.loadConfig();
            sender.sendMessage(");
            return true;
          } 
          if (args[0].equalsIgnoreCase("type")) {
            sender.sendMessage("" + player.getItemInHand().getType().name());
            return true;
          } 
        } 
      } 
      showHelp(sender);
    } else if (sender.isOp()) {
      if (args.length == 1 && 
        args[0].equalsIgnoreCase("reload")) {
        Main.loadConfig();
        sender.sendMessage(");
        return true;
      } 
      showHelp(sender);
    } 
    return false;
  }
  
  private void showHelp(CommandSender sender) {
    sender.sendMessage("+ Main.PublicDustbinName + ");
    sender.sendMessage(");
    sender.sendMessage(Main.PublicDustbinName + " );
    sender.sendMessage(Main.PublicDustbinName + " open");
    sender.sendMessage(");
    sender.sendMessage(Main.PublicDustbinName + " );
    sender.sendMessage(Main.PublicDustbinName + " discard");
    sender.sendMessage(");
    sender.sendMessage(Main.PublicDustbinName + " );
    sender.sendMessage(Main.PublicDustbinName + " drop");
    sender.sendMessage(");
    if (sender.isOp()) {
      sender.sendMessage(Main.PublicDustbinName + " );
      sender.sendMessage(Main.PublicDustbinName + " type");
      sender.sendMessage(");
      sender.sendMessage(Main.PublicDustbinName + " );
      sender.sendMessage(Main.PublicDustbinName + " reload");
      sender.sendMessage(");
    } 
    sender.sendMessage("+ Main.PublicDustbinName + ");
  }
}
