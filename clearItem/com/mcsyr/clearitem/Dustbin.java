package com.mcsyr.clearitem;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Dustbin {
  public static Inventory Dustbin = Bukkit.createInventory(null, Main.PublicDustbinSize.intValue(), Main.PublicDustbinName);
  
  public static Boolean addItem(ItemStack itemStack) {
    Main.DustbinLock = Boolean.valueOf(true);
    if (tools.isIncludedString(Main.PublicDustbinWhiteListName, itemStack.getItemMeta().getDisplayName())) {
      Main.DustbinLock = Boolean.valueOf(false);
      return Boolean.valueOf(false);
    } 
    ItemStack[] contents = Dustbin.getContents();
    for (int i = 0; i < contents.length; i++) {
      if (contents[i] == null) {
        Dustbin.setItem(i, itemStack);
        Main.DustbinLock = Boolean.valueOf(false);
        return Boolean.valueOf(true);
      } 
      if (contents[i].isSimilar(itemStack)) {
        int remainingQuantity = contents[i].getMaxStackSize() - contents[i].getAmount();
        int itemStackAmount = itemStack.getAmount();
        if (remainingQuantity > 0)
          if (itemStackAmount > remainingQuantity) {
            itemStack.setAmount(itemStackAmount - remainingQuantity);
            contents[i].setAmount(contents[i].getAmount() + remainingQuantity);
            Dustbin.setItem(i, contents[i]);
          } else {
            contents[i].setAmount(contents[i].getAmount() + itemStackAmount);
            Dustbin.setItem(i, contents[i]);
            Main.DustbinLock = Boolean.valueOf(false);
            return Boolean.valueOf(true);
          }  
      } 
    } 
    Main.DustbinLock = Boolean.valueOf(false);
    return Boolean.valueOf(false);
  }
  
  public static void ClearDustbin() {
    Dustbin.clear();
  }
}
