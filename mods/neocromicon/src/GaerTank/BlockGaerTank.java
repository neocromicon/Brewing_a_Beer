package mods.neocromicon.src.GaerTank;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import mods.neocromicon.src.BierModBase.BierMod;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockGaerTank extends BlockContainer
{
    private static boolean keepGaerTankInventory = false;
    private final boolean isActive;
    private Random gearTankRand = new Random();

    public BlockGaerTank(int var1, int var2, boolean var3)
    {
        super(var1, Material.iron);
        this.isActive = var3;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2.6F, 1.0F);
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon(BierMod.modID + ":GaerTank/gaerTank");
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random var1)
    {
        return 1;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return -1;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        super.onBlockAdded(var1, var2, var3, var4);
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5, ItemStack var6)
    {
        int var7 = MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        byte var8 = 0;

        if (var7 == 0)
        {
            var8 = 4;
        }

        if (var7 == 1)
        {
            var8 = 3;
        }

        if (var7 == 2)
        {
            var8 = 2;
        }

        if (var7 == 3)
        {
            var8 = 1;
        }

        var1.setBlockMetadataWithNotify(var2, var3, var4, var8, 0);

        if (!this.canBlockStay(var1, var2, var3, var4))
        {
            var1.setBlock(var2, var3, var4, 0);
        }

        if (var6.hasDisplayName())
        {
            ((TileEntityGaerTank)var1.getBlockTileEntity(var2, var3, var4)).func_94129_a(var6.getDisplayName());
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
            var5.openGui(BierMod.instance, 12, var1, var2, var3, var4);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        if (!keepGaerTankInventory)
        {
            TileEntityGaerTank var7 = (TileEntityGaerTank)var1.getBlockTileEntity(var2, var3, var4);

            if (var7 != null)
            {
                for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8)
                {
                    ItemStack var9 = var7.getStackInSlot(var8);

                    if (var9 != null)
                    {
                        float var10 = this.gearTankRand.nextFloat() * 0.8F + 0.1F;
                        float var11 = this.gearTankRand.nextFloat() * 0.8F + 0.1F;
                        float var12 = this.gearTankRand.nextFloat() * 0.8F + 0.1F;

                        while (var9.stackSize > 0)
                        {
                            int var13 = this.gearTankRand.nextInt(21) + 10;

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
                            var14.motionX = (double)((float)this.gearTankRand.nextGaussian() * var15);
                            var14.motionY = (double)((float)this.gearTankRand.nextGaussian() * var15 + 0.2F);
                            var14.motionZ = (double)((float)this.gearTankRand.nextGaussian() * var15);
                            var1.spawnEntityInWorld(var14);
                        }
                    }
                }
            }
        }

        super.breakBlock(var1, var2, var3, var4, var5, var6);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new TileEntityGaerTank();
    }

    public boolean hasTileEntity(int var1)
    {
        return true;
    }
}
