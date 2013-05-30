package mods.neocromicon.src.ZapfFass;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import mods.neocromicon.src.BierModBase.BierMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockZapfFass extends BlockContainer
{
    private Random braublockRand = new Random();
    private final boolean isActive;
    @SideOnly(Side.CLIENT)
    Icon[] icons;
    private static boolean keepBrauBlockInventory = false;

    public BlockZapfFass(int var1, boolean var2)
    {
        super(var1, Material.wood);
        this.isActive = var2;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return BierMod.ZapfFassIdle.blockID;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        super.onBlockAdded(var1, var2, var3, var4);
        this.setDefaultDirection(var1, var2, var3, var4);
    }

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World var1, int var2, int var3, int var4)
    {
        return BierMod.ZapfFassIdle.blockID;
    }

    private void setDefaultDirection(World var1, int var2, int var3, int var4)
    {
        if (!var1.isRemote)
        {
            int var5 = var1.getBlockId(var2, var3, var4 - 1);
            int var6 = var1.getBlockId(var2, var3, var4 + 1);
            int var7 = var1.getBlockId(var2 - 1, var3, var4);
            int var8 = var1.getBlockId(var2 + 1, var3, var4);
            byte var9 = 3;

            if (Block.opaqueCubeLookup[var5] && !Block.opaqueCubeLookup[var6])
            {
                var9 = 3;
            }

            if (Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var5])
            {
                var9 = 2;
            }

            if (Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var8])
            {
                var9 = 5;
            }

            if (Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var7])
            {
                var9 = 4;
            }

            var1.setBlockMetadataWithNotify(var2, var3, var4, var9, 0);
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        if (var5 == 1)
        {
            return this.blockIcon;
        }
        else
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);
            return var6 > 6 ? (var5 != var6 - 5 ? this.icons[0] : this.icons[2]) : (var5 != var6 ? this.icons[0] : this.icons[1]);
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return var1 == 3 ? this.icons[1] : (var1 == 1 ? this.blockIcon : this.icons[0]);
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon(BierMod.modID + ":ZapfFass/zapfFassTop");
        this.icons = new Icon[3];
        this.icons[0] = var1.registerIcon(BierMod.modID + ":ZapfFass/zapfFassSide");
        this.icons[1] = var1.registerIcon(this.isActive ? BierMod.modID + ":ZapfFass/zapfFassFrontOn" : BierMod.modID + ":ZapfFass/zapfFassFront");
        this.icons[2] = var1.registerIcon(BierMod.modID + ":ZapfFass/zapfFassTop");
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (this.isActive)
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);
            float var7 = (float)var2 + 0.5F;
            float var8 = (float)var3 + 0.7F + var5.nextFloat() * 6.0F / 16.0F;
            float var9 = (float)var4 + 0.5F;
            float var10 = 0.52F;
            float var11 = var5.nextFloat() * 0.6F - 0.3F;

            if (var6 == 4)
            {
                var1.spawnParticle("smoke", (double)(var7 - var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
                var1.spawnParticle("reddust", (double)(var7 - var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
                var1.spawnParticle("smoke", (double)(var7 + var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
            }
            else if (var6 == 5)
            {
                var1.spawnParticle("smoke", (double)(var7 + var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
                var1.spawnParticle("reddust", (double)(var7 + var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
                var1.spawnParticle("smoke", (double)(var7 + var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
            }
            else if (var6 == 2)
            {
                var1.spawnParticle("smoke", (double)(var7 + var11), (double)var8, (double)(var9 - var10), 0.0D, 0.0D, 0.0D);
                var1.spawnParticle("reddust", (double)(var7 + var11), (double)var8, (double)(var9 - var10), 0.0D, 0.0D, 0.0D);
                var1.spawnParticle("smoke", (double)(var7 + var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
            }
            else if (var6 == 3)
            {
                var1.spawnParticle("smoke", (double)(var7 + var11), (double)var8, (double)(var9 + var10), 0.0D, 0.0D, 0.0D);
                var1.spawnParticle("reddust", (double)(var7 + var11), (double)var8, (double)(var9 + var10), 0.0D, 0.0D, 0.0D);
                var1.spawnParticle("smoke", (double)(var7 + var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        TileEntity var10 = var1.getBlockTileEntity(var2, var3, var4);

        if (var10 != null && !var5.isSneaking())
        {
            var5.openGui(BierMod.instance, 10, var1, var2, var3, var4);
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void updateFurnaceBlockState(boolean var0, World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        TileEntity var6 = var1.getBlockTileEntity(var2, var3, var4);
        keepBrauBlockInventory = true;

        if (var0)
        {
            var1.setBlock(var2, var3, var4, BierMod.ZapfFassActive.blockID);
        }
        else
        {
            var1.setBlock(var2, var3, var4, BierMod.ZapfFassIdle.blockID);
        }

        keepBrauBlockInventory = false;
        var1.setBlockMetadataWithNotify(var2, var3, var4, var5, 0);

        if (var6 != null)
        {
            var6.validate();
            var1.setBlockTileEntity(var2, var3, var4, var6);
        }
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new TileEntityZapfFass();
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5, ItemStack var6)
    {
        int var7 = MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (var7 == 0)
        {
            var1.setBlockMetadataWithNotify(var2, var3, var4, 2, 0);
        }

        if (var7 == 1)
        {
            var1.setBlockMetadataWithNotify(var2, var3, var4, 5, 0);
        }

        if (var7 == 2)
        {
            var1.setBlockMetadataWithNotify(var2, var3, var4, 3, 0);
        }

        if (var7 == 3)
        {
            var1.setBlockMetadataWithNotify(var2, var3, var4, 4, 0);
        }

        if (var6.hasDisplayName())
        {
            ((TileEntityZapfFass)var1.getBlockTileEntity(var2, var3, var4)).func_94129_a(var6.getDisplayName());
        }
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        if (!keepBrauBlockInventory)
        {
            TileEntityZapfFass var7 = (TileEntityZapfFass)var1.getBlockTileEntity(var2, var3, var4);

            if (var7 != null)
            {
                for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8)
                {
                    ItemStack var9 = var7.getStackInSlot(var8);

                    if (var9 != null)
                    {
                        float var10 = this.braublockRand.nextFloat() * 0.8F + 0.1F;
                        float var11 = this.braublockRand.nextFloat() * 0.8F + 0.1F;
                        float var12 = this.braublockRand.nextFloat() * 0.8F + 0.1F;

                        while (var9.stackSize > 0)
                        {
                            int var13 = this.braublockRand.nextInt(21) + 10;

                            if (var13 > var9.stackSize)
                            {
                                var13 = var9.stackSize;
                            }

                            var9.stackSize -= var13;
                            EntityItem var14 = new EntityItem(var1, (double)((float)var2 + var10), (double)((float)var3 + var11), (double)((float)var4 + var12), new ItemStack(var9.itemID, var13, var9.getItemDamage()));

                            if (var9.hasTagCompound())
                            {
                                var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                            }

                            float var15 = 0.05F;
                            var14.motionX = (double)((float)this.braublockRand.nextGaussian() * var15);
                            var14.motionY = (double)((float)this.braublockRand.nextGaussian() * var15 + 0.2F);
                            var14.motionZ = (double)((float)this.braublockRand.nextGaussian() * var15);
                            var1.spawnEntityInWorld(var14);
                        }
                    }
                }
            }
        }

        super.breakBlock(var1, var2, var3, var4, var5, var6);
    }

    /**
     * If this returns true, then comparators facing away from this block will use the value from
     * getComparatorInputOverride instead of the actual redstone signal strength.
     */
    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    /**
     * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
     * strength when this block inputs to a comparator.
     */
    public int getComparatorInputOverride(World var1, int var2, int var3, int var4, int var5)
    {
        return Container.calcRedstoneFromInventory((IInventory)var1.getBlockTileEntity(var2, var3, var4));
    }
}
