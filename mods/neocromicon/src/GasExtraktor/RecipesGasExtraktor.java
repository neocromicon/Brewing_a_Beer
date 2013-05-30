package mods.neocromicon.src.GasExtraktor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.item.ItemStack;

public class RecipesGasExtraktor
{
    private static final RecipesGasExtraktor smeltingBase = new RecipesGasExtraktor();
    private Map smeltingListGasExtraktor = new HashMap();
    private Map metaSmeltingListGasExtraktor = new HashMap();

    public static final RecipesGasExtraktor smelting()
    {
        return smeltingBase;
    }

    public void addSmelting(int var1, ItemStack var2)
    {
        this.smeltingListGasExtraktor.put(Integer.valueOf(var1), var2);
    }

    public ItemStack getSmeltingResult(int var1)
    {
        return (ItemStack)this.smeltingListGasExtraktor.get(Integer.valueOf(var1));
    }

    public Map getSmeltingList()
    {
        return this.smeltingListGasExtraktor;
    }

    public void addSmelting(int var1, int var2, ItemStack var3)
    {
        this.metaSmeltingListGasExtraktor.put(Arrays.asList(new Integer[] {Integer.valueOf(var1), Integer.valueOf(var2)}), var3);
    }

    public ItemStack getSmeltingResult(ItemStack var1)
    {
        if (var1 == null)
        {
            return null;
        }
        else
        {
            ItemStack var2 = (ItemStack)this.metaSmeltingListGasExtraktor.get(Arrays.asList(new Integer[] {Integer.valueOf(var1.itemID), Integer.valueOf(var1.getItemDamage())}));
            return var2 != null ? var2 : (ItemStack)this.smeltingListGasExtraktor.get(Integer.valueOf(var1.itemID));
        }
    }
}
