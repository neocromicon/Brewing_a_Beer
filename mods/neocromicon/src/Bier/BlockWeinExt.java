package mods.neocromicon.src.Bier;

import java.util.Random;
import mods.neocromicon.src.BierModBase.BierMod;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockWeinExt extends BlockFlower
{
    protected Icon[] iconBuffer = new Icon[11];

    public BlockWeinExt(int var1)
    {
        super(var1);
        this.setTickRandomly(true);
        float var2 = 0.5F;
        this.setHardness(0.3F);
        this.setResistance(3.0F);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.disableStats();
    }

    /**
     * Gets passed in the blockID of the block below and supposed to return true if its allowed to grow on the type of
     * blockID passed in. Args: blockID
     */
    protected boolean canThisPlantGrowOnThisBlockID(int var1)
    {
        return var1 == BierMod.BlockWein.blockID;
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return this.iconBuffer[var2];
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3 - 1, var4);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var5, 2);
        var1.markBlockForUpdate(var2, var3, var4);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return -1;
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return 10;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random var1)
    {
        return -1;
    }

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World var1, int var2, int var3, int var4)
    {
        return -1;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 1;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.iconBuffer[0] = var1.registerIcon(BierMod.modID + ":Wein/PlantWein0");
        this.iconBuffer[1] = var1.registerIcon(BierMod.modID + ":Wein/PlantWein0");
        this.iconBuffer[2] = var1.registerIcon(BierMod.modID + ":Wein/PlantWein0");
        this.iconBuffer[3] = var1.registerIcon(BierMod.modID + ":Wein/PlantWein0");
        this.iconBuffer[4] = var1.registerIcon(BierMod.modID + ":Wein/PlantWein4");
        this.iconBuffer[5] = var1.registerIcon(BierMod.modID + ":Wein/PlantWein5");
    }
}
