package mods.neocromicon.src.ZapfFass;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import mods.neocromicon.src.BierModBase.BierMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityZapfFass extends TileEntity implements IInventory
{
    private ItemStack[] braublockItemStacks = new ItemStack[4];
    public int braublockBurnTime = 0;
    public int ZapfAnlagecurrentItemBurnTime = 0;
    public int braublockCookTime = 0;
    protected Random rand;
    private String field_94130_e;

    public void func_94129_a(String var1)
    {
        this.field_94130_e = var1;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.braublockItemStacks.length;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int var1)
    {
        return this.braublockItemStacks[var1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int var1, int var2)
    {
        if (this.braublockItemStacks[var1] != null)
        {
            ItemStack var3;

            if (this.braublockItemStacks[var1].stackSize <= var2)
            {
                var3 = this.braublockItemStacks[var1];
                this.braublockItemStacks[var1] = null;
                return var3;
            }
            else
            {
                var3 = this.braublockItemStacks[var1].splitStack(var2);

                if (this.braublockItemStacks[var1].stackSize == 0)
                {
                    this.braublockItemStacks[var1] = null;
                }

                return var3;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int var1)
    {
        if (this.braublockItemStacks[var1] != null)
        {
            ItemStack var2 = this.braublockItemStacks[var1];
            this.braublockItemStacks[var1] = null;
            return var2;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int var1, ItemStack var2)
    {
        this.braublockItemStacks[var1] = var2;

        if (var2 != null && var2.stackSize > this.getInventoryStackLimit())
        {
            var2.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "container.zapffass";
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);
        NBTTagList var2 = var1.getTagList("ZapfAnlageItems");
        this.braublockItemStacks = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            byte var5 = var4.getByte("ZapfAnlageSlot");

            if (var5 >= 0 && var5 < this.braublockItemStacks.length)
            {
                this.braublockItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }

        this.braublockBurnTime = var1.getShort("ZapfAnlageBurnTime");
        this.braublockCookTime = var1.getShort("ZapfAnlageCookTime");
        this.ZapfAnlagecurrentItemBurnTime = getItemBurnTime(this.braublockItemStacks[1]);
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        var1.setShort("ZapfAnlageBurnTime", (short)this.braublockBurnTime);
        var1.setShort("ZapfAnlageCookTime", (short)this.braublockCookTime);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.braublockItemStacks.length; ++var3)
        {
            if (this.braublockItemStacks[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("ZapfAnlageSlot", (byte)var3);
                this.braublockItemStacks[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        var1.setTag("ZapfAnlageItems", var2);
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int var1)
    {
        return this.braublockCookTime * var1 / 120;
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int var1)
    {
        if (this.ZapfAnlagecurrentItemBurnTime == 0)
        {
            this.ZapfAnlagecurrentItemBurnTime = 120;
        }

        return this.braublockBurnTime * var1 / this.ZapfAnlagecurrentItemBurnTime;
    }

    public boolean isBurning()
    {
        return this.braublockBurnTime > 0;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        boolean var1 = this.braublockBurnTime > 0;
        boolean var2 = false;
        boolean var3 = false;

        if (BierMod.soundEnable.endsWith("true") && this.braublockCookTime == 1)
        {
            this.worldObj.playSoundEffect((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.0D, "biermod.zapffass", 1.0F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
        }

        if (this.braublockBurnTime > 0)
        {
            --this.braublockBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.braublockBurnTime == 0 && this.canSmelt())
            {
                this.ZapfAnlagecurrentItemBurnTime = this.braublockBurnTime = getItemBurnTime(this.braublockItemStacks[1]);

                if (this.braublockBurnTime > 0)
                {
                    var2 = true;

                    if (this.braublockItemStacks[1] != null)
                    {
                        --this.braublockItemStacks[1].stackSize;

                        if (this.braublockItemStacks[1].stackSize == 0)
                        {
                            this.braublockItemStacks[1] = this.braublockItemStacks[1].getItem().getContainerItemStack(this.braublockItemStacks[1]);
                        }
                    }
                }
            }

            if (this.isBurning() && this.canSmelt())
            {
                ++this.braublockCookTime;

                if (this.braublockCookTime == 120)
                {
                    this.braublockCookTime = 0;
                    this.smeltItem();
                    var2 = true;
                }
            }
            else
            {
                this.braublockCookTime = 0;
            }

            if (var1 != this.braublockBurnTime > 0)
            {
                var2 = true;
                BlockZapfFass.updateFurnaceBlockState(this.braublockBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (var2)
        {
            this.onInventoryChanged();
        }
    }

    private boolean canSmelt()
    {
        if (this.braublockItemStacks[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack var1 = RecipesZapfFass.smelting().getSmeltingResult(this.braublockItemStacks[3]);

            if (var1 == null)
            {
                return false;
            }
            else if (this.braublockItemStacks[2] == null)
            {
                return true;
            }
            else if (!this.braublockItemStacks[2].isItemEqual(var1))
            {
                return false;
            }
            else
            {
                int var2 = this.braublockItemStacks[2].stackSize + var1.stackSize;
                return var2 <= this.getInventoryStackLimit() && var2 <= var1.getMaxStackSize();
            }
        }
    }

    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack var1 = RecipesZapfFass.smelting().getSmeltingResult(this.braublockItemStacks[3]);

            if (this.braublockItemStacks[2] == null)
            {
                this.braublockItemStacks[2] = var1.copy();
            }
            else if (this.braublockItemStacks[2].isItemEqual(var1))
            {
                this.braublockItemStacks[2].stackSize += var1.stackSize;
            }

            if (this.braublockItemStacks[0].stackSize <= 0)
            {
                this.braublockItemStacks[0] = new ItemStack(this.braublockItemStacks[0].getItem().setFull3D());
            }

            if (this.braublockItemStacks[3].stackSize <= 0)
            {
                this.braublockItemStacks[3] = new ItemStack(this.braublockItemStacks[3].getItem().setFull3D());
                --this.braublockItemStacks[0].stackSize;
                --this.braublockItemStacks[3].stackSize;
            }
            else
            {
                --this.braublockItemStacks[0].stackSize;
                --this.braublockItemStacks[3].stackSize;
            }

            if (this.braublockItemStacks[0].stackSize <= 0)
            {
                this.braublockItemStacks[0] = null;
            }

            if (this.braublockItemStacks[3].stackSize <= 0)
            {
                this.braublockItemStacks[3] = null;
            }
        }
    }

    public static int getItemBurnTime(ItemStack var0)
    {
        if (var0 == null)
        {
            return 0;
        }
        else
        {
            int var1 = var0.getItem().itemID;
            Item var2 = var0.getItem();
            return var1 == BierMod.GasAmpulleVoll.itemID ? 2000 : GameRegistry.getFuelValue(var0);
        }
    }

    public static boolean isItemFuel(ItemStack var0)
    {
        return getItemBurnTime(var0) > 0;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer var1)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : var1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest() {}

    public void closeChest() {}

    public int getStartInventorySide(ForgeDirection var1)
    {
        return var1 == ForgeDirection.DOWN ? 1 : (var1 == ForgeDirection.UP ? 0 : 2);
    }

    public int getSizeInventorySide(ForgeDirection var1)
    {
        return 1;
    }

    /**
     * If this returns false, the inventory name will be used as an unlocalized name, and translated into the player's
     * language. Otherwise it will be used directly.
     */
    public boolean isInvNameLocalized()
    {
        return false;
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isStackValidForSlot(int var1, ItemStack var2)
    {
        return false;
    }
}
