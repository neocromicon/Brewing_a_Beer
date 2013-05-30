package mods.neocromicon.src.Bier;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.neocromicon.src.BierModBase.BierMod;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class BlockGerste extends BlockCrops
{
    @SideOnly(Side.CLIENT)
    private Icon[] iconArray;

    public BlockGerste(int var1)
    {
        super(var1);
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        if (var2 < 0 || var2 > 7)
        {
            var2 = 7;
        }

        return this.iconArray[var2];
    }

    /**
     * Generate a seed ItemStack for this crop.
     */
    protected int getSeedItem()
    {
        return BierMod.ItemSamenGerste.itemID;
    }

    /**
     * Generate a crop produce ItemStack for this crop.
     */
    protected int getCropItem()
    {
        return BierMod.ItemGerste.itemID;
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.iconArray = new Icon[8];

        for (int var2 = 0; var2 < this.iconArray.length; ++var2)
        {
            this.iconArray[var2] = var1.registerIcon("Brewing_a_Beer:Gerste/PlantGerste" + var2);
        }
    }
}
