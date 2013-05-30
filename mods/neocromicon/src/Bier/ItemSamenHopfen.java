package mods.neocromicon.src.Bier;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

public class ItemSamenHopfen extends Item implements IPlantable
{
    private int blockType;
    private int soilBlockID;
    public String Image;

    public ItemSamenHopfen(int var1, int var2, int var3, String var4)
    {
        super(var1);
        this.Image = var4;
        this.blockType = var2;
        this.soilBlockID = var3;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister var1)
    {
        this.itemIcon = var1.registerIcon(this.Image);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        if (var7 != 1)
        {
            return false;
        }
        else if (var2.canPlayerEdit(var4, var5, var6, var7, var1) && var2.canPlayerEdit(var4, var5 + 1, var6, var7, var1))
        {
            int var11 = var3.getBlockId(var4, var5, var6);
            Block var12 = Block.blocksList[var11];

            if (var12 != null && var12.canSustainPlant(var3, var4, var5, var6, ForgeDirection.UP, this) && var3.isAirBlock(var4, var5 + 1, var6))
            {
                var3.setBlock(var4, var5 + 1, var6, this.blockType);
                --var1.stackSize;
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public EnumPlantType getPlantType(World var1, int var2, int var3, int var4)
    {
        return this.blockType == Block.netherStalk.blockID ? EnumPlantType.Nether : EnumPlantType.Crop;
    }

    public int getPlantID(World var1, int var2, int var3, int var4)
    {
        return this.blockType;
    }

    public int getPlantMetadata(World var1, int var2, int var3, int var4)
    {
        return 0;
    }
}
