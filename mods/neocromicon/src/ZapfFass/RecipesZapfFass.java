package mods.neocromicon.src.ZapfFass;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.item.ItemStack;

public class RecipesZapfFass
{
    private static final RecipesZapfFass smeltingBase = new RecipesZapfFass();
    private Map smeltingList = new HashMap();
    private Map metaSmeltingList = new HashMap();

    public static final RecipesZapfFass smelting()
    {
        return smeltingBase;
    }

    public void addSmelting1(int var1, int var2, ItemStack var3)
    {
        this.smeltingList.put(Integer.valueOf(var1), var3);
        this.smeltingList.put(Integer.valueOf(var2), var3);
    }

    public ItemStack getSmeltingResult(int var1, int var2)
    {
        this.smeltingList.get(Integer.valueOf(var1));
        return null;
    }

    public Map getSmeltingList()
    {
        return this.smeltingList;
    }

    public void addSmelting(int var1, int var2, ItemStack var3)
    {
        this.metaSmeltingList.put(Arrays.asList(new Integer[] {Integer.valueOf(var1), Integer.valueOf(var2)}), var3);
    }

    public ItemStack getSmeltingResult(ItemStack var1)
    {
        if (var1 == null)
        {
            return null;
        }
        else
        {
            ItemStack var2 = (ItemStack)this.metaSmeltingList.get(Arrays.asList(new Integer[] {Integer.valueOf(var1.itemID), Integer.valueOf(var1.getItemDamage())}));
            return var2 != null ? var2 : (ItemStack)this.smeltingList.get(Integer.valueOf(var1.itemID));
        }
    }
}
