package mods.neocromicon.src.BierModBase;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;

public final class TabBeerCreative extends CreativeTabs
{
    public TabBeerCreative(int var1, String var2)
    {
        super(var1, var2);
    }

    @SideOnly(Side.CLIENT)

    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex()
    {
        return BierMod.LeeresBierGlas.itemID;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Gets the translated Label.
     */
    public String getTranslatedTabLabel()
    {
        return "Brewing a Beer";
    }
}
