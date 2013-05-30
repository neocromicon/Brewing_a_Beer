package mods.neocromicon.src.GaerTank;

import mods.neocromicon.src.BierModBase.BierMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGaerTank extends TileEntity implements IInventory
{
    private ItemStack[] gaertankItemStacks = new ItemStack[3];
    public int gaertankBurnTime = 0;
    public int gaertankcurrentItemBurnTime = 0;
    public static int gaertankCookTime = 0;
    private String field_94130_e;
    private static int styleGaerTank;

    public void func_94129_a(String var1)
    {
        this.field_94130_e = var1;
    }

    public int getStyleGaerTank()
    {
        return styleGaerTank;
    }

    public static void setStyleGaerTank(int var0)
    {
        styleGaerTank = var0;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.gaertankItemStacks.length;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int var1)
    {
        return this.gaertankItemStacks[var1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int var1, int var2)
    {
        if (this.gaertankItemStacks[var1] != null)
        {
            ItemStack var3;

            if (this.gaertankItemStacks[var1].stackSize <= var2)
            {
                var3 = this.gaertankItemStacks[var1];
                this.gaertankItemStacks[var1] = null;
                return var3;
            }
            else
            {
                var3 = this.gaertankItemStacks[var1].splitStack(var2);

                if (this.gaertankItemStacks[var1].stackSize == 0)
                {
                    this.gaertankItemStacks[var1] = null;
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
        if (this.gaertankItemStacks[var1] != null)
        {
            ItemStack var2 = this.gaertankItemStacks[var1];
            this.gaertankItemStacks[var1] = null;
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
        this.gaertankItemStacks[var1] = var2;

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
        return "container.gaerfass";
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);
        NBTTagList var2 = var1.getTagList("GaerTankItems");
        this.gaertankItemStacks = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            byte var5 = var4.getByte("SlotGaerTank");

            if (var5 >= 0 && var5 < this.gaertankItemStacks.length)
            {
                this.gaertankItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }

        this.gaertankBurnTime = var1.getShort("GaerTankBurnTime");
        gaertankCookTime = var1.getShort("GaerTankCookTime");
        this.gaertankcurrentItemBurnTime = getItemBurnTime(this.gaertankItemStacks[1]);
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        var1.setShort("GaerTankBurnTime", (short)this.gaertankBurnTime);
        var1.setShort("GaerTankCookTime", (short)gaertankCookTime);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.gaertankItemStacks.length; ++var3)
        {
            if (this.gaertankItemStacks[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("SlotGaerTank", (byte)var3);
                this.gaertankItemStacks[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        var1.setTag("GaerTankItems", var2);
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    public int getCookProgressScaled(int var1)
    {
        return gaertankCookTime * var1 / 10000;
    }

    public int getBurnTimeRemainingScaled(int var1)
    {
        if (this.gaertankcurrentItemBurnTime == 0)
        {
            this.gaertankcurrentItemBurnTime = 10000;
        }

        return this.gaertankBurnTime * var1 / this.gaertankcurrentItemBurnTime;
    }

    public boolean isBurning()
    {
        return this.gaertankBurnTime > 0;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        boolean var1 = this.gaertankBurnTime > 0;
        boolean var2 = false;
        boolean var3 = false;

        if (this.gaertankBurnTime > 0)
        {
            --this.gaertankBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.gaertankBurnTime == 0 && this.canSmelt())
            {
                this.gaertankcurrentItemBurnTime = this.gaertankBurnTime = getItemBurnTime(this.gaertankItemStacks[1]);

                if (gaertankCookTime > 0)
                {
                    setStyleGaerTank(0);
                }
                else
                {
                    setStyleGaerTank(1);
                }

                if (this.gaertankBurnTime > 0)
                {
                    var2 = true;

                    if (this.gaertankItemStacks[1] != null)
                    {
                        if (this.gaertankItemStacks[1].stackSize == 0)
                        {
                            this.gaertankItemStacks[1] = new ItemStack(this.gaertankItemStacks[1].getItem().setFull3D());
                        }
                        else
                        {
                            --this.gaertankItemStacks[1].stackSize;
                        }

                        if (this.gaertankItemStacks[1].stackSize == 0)
                        {
                            this.gaertankItemStacks[1] = null;
                        }
                    }
                }
            }

            if (this.isBurning() && this.canSmelt())
            {
                System.out.println("AN TILE ENTITY");
                ++gaertankCookTime;

                if (gaertankCookTime == 10000)
                {
                    gaertankCookTime = 0;
                    this.smeltItem();
                    var2 = true;
                }
            }
            else
            {
                gaertankCookTime = 0;
            }

            if (var1 != this.gaertankBurnTime > 0)
            {
                var2 = true;
            }
        }

        if (var2)
        {
            this.onInventoryChanged();
        }
    }

    private boolean canSmelt()
    {
        if (this.gaertankItemStacks[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack var1 = RecipesGaerTank.smelting().getSmeltingResult(this.gaertankItemStacks[0]);

            if (var1 == null)
            {
                return false;
            }
            else if (this.gaertankItemStacks[2] == null)
            {
                return true;
            }
            else if (!this.gaertankItemStacks[2].isItemEqual(var1))
            {
                return false;
            }
            else
            {
                int var2 = this.gaertankItemStacks[2].stackSize + var1.stackSize;
                return var2 <= this.getInventoryStackLimit() && var2 <= var1.getMaxStackSize();
            }
        }
    }

    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack var1 = RecipesGaerTank.smelting().getSmeltingResult(this.gaertankItemStacks[0]);

            if (this.gaertankItemStacks[2] == null)
            {
                this.gaertankItemStacks[2] = var1.copy();
            }
            else if (this.gaertankItemStacks[2].isItemEqual(var1))
            {
                this.gaertankItemStacks[2].stackSize += var1.stackSize;
            }

            if (this.gaertankItemStacks[0].stackSize <= 0)
            {
                this.gaertankItemStacks[0] = new ItemStack(this.gaertankItemStacks[0].getItem().setFull3D());
            }
            else
            {
                --this.gaertankItemStacks[0].stackSize;
            }

            if (this.gaertankItemStacks[0].stackSize <= 0)
            {
                this.gaertankItemStacks[0] = null;
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
            return var1 == BierMod.LeeresFass.itemID ? 10000 : 0;
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
