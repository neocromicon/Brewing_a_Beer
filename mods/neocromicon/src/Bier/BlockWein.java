package mods.neocromicon.src.Bier;

import java.util.ArrayList;
import java.util.Random;
import mods.neocromicon.src.BierModBase.BierMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockWein extends BlockFlower
{
    protected Icon[] iconBuffer = new Icon[7];

    public BlockWein(int var1)
    {
        super(var1);
        this.setTickRandomly(true);
        this.setHardness(0.3F);
        this.setResistance(3.0F);
        this.setBlockBounds(0.4F, 0.0F, 0.4F, 0.6F, 2.0F, 0.6F);
        this.disableStats();
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
        if (var1.isAirBlock(var2, var3 + 1, var4))
        {
            var1.setBlock(var2, var3 + 1, var4, BierMod.BlockWeinExt.blockID);
            var1.notifyBlockChange(var2, var3 + 1, var4, 0);
        }
    }

    public ArrayList getBlockDropped(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        ArrayList var7 = new ArrayList();

        if (var5 == 5)
        {
            var7.add(new ItemStack(BierMod.ItemSamenWein, 1));
            var7.add(new ItemStack(BierMod.ItemWeinTrauben, 3));
        }

        return var7;
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        return AxisAlignedBB.getAABBPool().getAABB((double)var2 + this.minX, (double)var3 + this.minY, (double)var4 + this.minZ, (double)var2 + this.maxX, (double)var3 + this.maxY, (double)var4 + this.maxZ);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        ItemStack var10 = var5.inventory.getCurrentItem();

        if (var10 != null && var10.itemID == Item.dyePowder.itemID && var10.getItemDamage() == 15)
        {
            var1.setBlockMetadataWithNotify(var2, var3, var4, 5, 2);
            var1.setBlockMetadataWithNotify(var2, var3 + 1, var4, 5, 2);
            --var10.stackSize;
            var1.notifyBlockChange(var2, var3, var4, this.blockID);
            var1.notifyBlockChange(var2, var3 + 1, var4, this.blockID);
        }

        super.onBlockActivated(var1, var2, var3, var4, var5, var6, var7, var8, var9);
        return true;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return var1 == 3 ? BierMod.ItemHopfen.itemID : -1;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random var1)
    {
        return 2;
    }

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World var1, int var2, int var3, int var4)
    {
        return BierMod.ItemSamenWein.itemID;
    }

    /**
     * Gets passed in the blockID of the block below and supposed to return true if its allowed to grow on the type of
     * blockID passed in. Args: blockID
     */
    protected boolean canThisPlantGrowOnThisBlockID(int var1)
    {
        return var1 == Block.grass.blockID || var1 == Block.tilledField.blockID;
    }

    /**
     * Called right before the block is destroyed by a player.  Args: world, x, y, z, metaData
     */
    public void onBlockDestroyedByPlayer(World var1, int var2, int var3, int var4, int var5)
    {
        var1.markBlockForUpdate(var2, var3, var4);
        var1.destroyBlock(var2, var3, var4, false);
        super.onBlockDestroyedByPlayer(var1, var2, var3, var4, var5);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        super.updateTick(var1, var2, var3, var4, var5);
        int var6 = var1.getBlockMetadata(var2, var3, var4);

        if (var1.getBlockLightValue(var2, var3 + 1, var4) >= 9)
        {
            if (var1.isAirBlock(var2, var3 + 1, var4))
            {
                var1.setBlock(var2, var3 + 1, var4, BierMod.BlockWeinExt.blockID);
                var1.notifyBlockChange(var2, var3 + 1, var4, var6);
            }

            int var7 = var1.getBlockId(var2, var3 + 1, var4);

            if (var7 == BierMod.BlockWeinExt.blockID)
            {
                var1.setBlockMetadataWithNotify(var2, var3 + 1, var4, var6, 2);
                var1.markBlockForUpdate(var2, var3 + 1, var4);
            }
        }

        if (var6 < 5)
        {
            float var8 = this.getGrowthRate(var1, var2, var3, var4);

            if (var5.nextInt((int)(25.0F / var8) + 1) == 0)
            {
                ++var6;
                var1.setBlockMetadataWithNotify(var2, var3, var4, var6, 2);
                var1.setBlockMetadataWithNotify(var2, var3 + 1, var4, var6, 2);
            }
        }
    }

    private float getGrowthRate(World var1, int var2, int var3, int var4)
    {
        float var5 = 1.0F;
        int var6 = var1.getBlockId(var2, var3, var4 - 1);
        int var7 = var1.getBlockId(var2, var3, var4 + 1);
        int var8 = var1.getBlockId(var2 - 1, var3, var4);
        int var9 = var1.getBlockId(var2 + 1, var3, var4);
        int var10 = var1.getBlockId(var2 - 1, var3, var4 - 1);
        int var11 = var1.getBlockId(var2 + 1, var3, var4 - 1);
        int var12 = var1.getBlockId(var2 + 1, var3, var4 + 1);
        int var13 = var1.getBlockId(var2 - 1, var3, var4 + 1);
        boolean var14 = var8 == this.blockID || var9 == this.blockID;
        boolean var15 = var6 == this.blockID || var7 == this.blockID;
        boolean var16 = var10 == this.blockID || var11 == this.blockID || var12 == this.blockID || var13 == this.blockID;

        for (int var17 = var2 - 1; var17 <= var2 + 1; ++var17)
        {
            for (int var18 = var4 - 1; var18 <= var4 + 1; ++var18)
            {
                int var19 = var1.getBlockId(var17, var3 - 1, var18);
                float var20 = 0.0F;

                if (var19 == Block.grass.blockID || var19 == Block.tilledField.blockID)
                {
                    var20 = 1.0F;

                    if (var1.getBlockMetadata(var17, var3 - 1, var18) > 0)
                    {
                        var20 = 3.0F;
                    }
                }

                if (var17 != var2 || var18 != var4)
                {
                    var20 /= 4.0F;
                }

                var5 += var20;
            }
        }

        if (var16 || var14 && var15)
        {
            var5 /= 2.0F;
        }

        return var5;
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
        this.iconBuffer[1] = var1.registerIcon(BierMod.modID + ":Wein/PlantWein1");
        this.iconBuffer[2] = var1.registerIcon(BierMod.modID + ":Wein/PlantWein2");
        this.iconBuffer[3] = var1.registerIcon(BierMod.modID + ":Wein/PlantWein3");
        this.iconBuffer[4] = var1.registerIcon(BierMod.modID + ":Wein/PlantWein3");
        this.iconBuffer[5] = var1.registerIcon(BierMod.modID + ":Wein/PlantWein3");
    }
}
