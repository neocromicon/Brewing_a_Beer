package mods.neocromicon.src.GasExtraktor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGasExtraktor extends TileEntity implements IInventory
{
    private ItemStack[] gasextraktorItemStacks = new ItemStack[3];
    public int gasextraktorBurnTime = 0;
    public int gasextraktorcurrentItemBurnTime = 0;
    public int gasextraktorCookTime = 0;
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
        return this.gasextraktorItemStacks.length;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int var1)
    {
        return this.gasextraktorItemStacks[var1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int var1, int var2)
    {
        if (this.gasextraktorItemStacks[var1] != null)
        {
            ItemStack var3;

            if (this.gasextraktorItemStacks[var1].stackSize <= var2)
            {
                var3 = this.gasextraktorItemStacks[var1];
                this.gasextraktorItemStacks[var1] = null;
                return var3;
            }
            else
            {
                var3 = this.gasextraktorItemStacks[var1].splitStack(var2);

                if (this.gasextraktorItemStacks[var1].stackSize == 0)
                {
                    this.gasextraktorItemStacks[var1] = null;
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
        if (this.gasextraktorItemStacks[var1] != null)
        {
            ItemStack var2 = this.gasextraktorItemStacks[var1];
            this.gasextraktorItemStacks[var1] = null;
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
        this.gasextraktorItemStacks[var1] = var2;

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
        return "container.gasextraktor";
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);
        NBTTagList var2 = var1.getTagList("GasExtraktorItems");
        this.gasextraktorItemStacks = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            byte var5 = var4.getByte("GasExtraktorSlot");

            if (var5 >= 0 && var5 < this.gasextraktorItemStacks.length)
            {
                this.gasextraktorItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }

        this.gasextraktorBurnTime = var1.getShort("GasExtraktorBurnTime");
        this.gasextraktorCookTime = var1.getShort("GasExtraktorCookTime");
        this.gasextraktorcurrentItemBurnTime = getItemBurnTime(this.gasextraktorItemStacks[1]);
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        var1.setShort("GasExtraktorBurnTime", (short)this.gasextraktorBurnTime);
        var1.setShort("GasExtraktorCookTime", (short)this.gasextraktorCookTime);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.gasextraktorItemStacks.length; ++var3)
        {
            if (this.gasextraktorItemStacks[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("GasExtraktorSlot", (byte)var3);
                this.gasextraktorItemStacks[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        var1.setTag("GasExtraktorItems", var2);
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
        return this.gasextraktorCookTime * var1 / 720;
    }

    public int getBurnTimeRemainingScaled(int var1)
    {
        if (this.gasextraktorcurrentItemBurnTime == 0)
        {
            this.gasextraktorcurrentItemBurnTime = 720;
        }

        return this.gasextraktorBurnTime * var1 / this.gasextraktorcurrentItemBurnTime;
    }

    public boolean isBurning()
    {
        return this.gasextraktorBurnTime > 0;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        boolean var1 = this.gasextraktorBurnTime > 0;
        boolean var2 = false;
        boolean var3 = false;

        if (this.gasextraktorBurnTime > 0)
        {
            --this.gasextraktorBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.gasextraktorBurnTime == 0 && this.canSmelt())
            {
                this.gasextraktorcurrentItemBurnTime = this.gasextraktorBurnTime = getItemBurnTime(this.gasextraktorItemStacks[1]);

                if (this.gasextraktorBurnTime > 0)
                {
                    var2 = true;

                    if (this.gasextraktorItemStacks[1] != null)
                    {
                        if (this.gasextraktorItemStacks[1].stackSize == 0)
                        {
                            this.gasextraktorItemStacks[1] = new ItemStack(this.gasextraktorItemStacks[1].getItem().setFull3D());
                        }
                        else
                        {
                            --this.gasextraktorItemStacks[1].stackSize;
                        }

                        if (this.gasextraktorItemStacks[1].stackSize == 0)
                        {
                            this.gasextraktorItemStacks[1] = null;
                        }
                    }
                }
            }

            if (this.isBurning() && this.canSmelt())
            {
                ++this.gasextraktorCookTime;

                if (this.gasextraktorCookTime == 720)
                {
                    this.gasextraktorCookTime = 0;
                    this.smeltItem();
                    var2 = true;
                }
            }
            else
            {
                this.gasextraktorCookTime = 0;
            }

            if (var1 != this.gasextraktorBurnTime > 0)
            {
                var2 = true;
                BlockGasExtraktor.updateFurnaceBlockState(this.gasextraktorBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (var2)
        {
            this.onInventoryChanged();
        }
    }

    private boolean canSmelt()
    {
        if (this.gasextraktorItemStacks[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack var1 = RecipesGasExtraktor.smelting().getSmeltingResult(this.gasextraktorItemStacks[0]);

            if (var1 == null)
            {
                return false;
            }
            else if (this.gasextraktorItemStacks[2] == null)
            {
                return true;
            }
            else if (!this.gasextraktorItemStacks[2].isItemEqual(var1))
            {
                return false;
            }
            else
            {
                int var2 = this.gasextraktorItemStacks[2].stackSize + var1.stackSize;
                return var2 <= this.getInventoryStackLimit() && var2 <= var1.getMaxStackSize();
            }
        }
    }

    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack var1 = RecipesGasExtraktor.smelting().getSmeltingResult(this.gasextraktorItemStacks[0]);

            if (this.gasextraktorItemStacks[2] == null)
            {
                this.gasextraktorItemStacks[2] = var1.copy();
            }
            else if (this.gasextraktorItemStacks[2].isItemEqual(var1))
            {
                this.gasextraktorItemStacks[2].stackSize += var1.stackSize;
            }

            if (this.gasextraktorItemStacks[0].stackSize <= 0)
            {
                this.gasextraktorItemStacks[0] = new ItemStack(this.gasextraktorItemStacks[0].getItem().setFull3D());
            }
            else
            {
                --this.gasextraktorItemStacks[0].stackSize;
            }

            if (this.gasextraktorItemStacks[0].stackSize <= 0)
            {
                this.gasextraktorItemStacks[0] = null;
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
            int var2 = var0.stackSize;

            if (var1 == Item.rottenFlesh.itemID && var2 >= 12)
            {
                var0.stackSize -= 11;
                return 720;
            }
            else if (var1 == Item.potato.itemID)
            {
                if (var2 >= 24)
                {
                    var0.stackSize -= 23;
                    return 720;
                }
                else
                {
                    return 0;
                }
            }
            else
            {
                return 0;
            }
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
