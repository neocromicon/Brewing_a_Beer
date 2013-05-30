package mods.neocromicon.src.BierFass;

import cpw.mods.fml.client.FMLClientHandler;
import mods.neocromicon.src.BierModBase.BierMod;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBierFass extends TileEntity implements IInventory
{
    private ItemStack[] drawerContents = new ItemStack[36];
    public boolean adjacentDrawerChecked = false;
    public TileEntityBierFass adjacentChestZNeg;
    public TileEntityBierFass adjacentChestXPos;
    public TileEntityBierFass adjacentChestXNeg;
    public TileEntityBierFass adjacentChestZPos;
    public float lidAngle;
    public float prevLidAngle;
    public int numUsingPlayers;
    private int ticksSinceSync;
    public boolean openUpperDrawer = true;
    private int drawerTimer = 0;
    private boolean startDrawerTimer = false;

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 9;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int var1)
    {
        return this.drawerContents[var1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int var1, int var2)
    {
        if (this.drawerContents[var1] != null)
        {
            ItemStack var3;

            if (this.drawerContents[var1].stackSize <= var2)
            {
                var3 = this.drawerContents[var1];
                this.drawerContents[var1] = null;
                this.onInventoryChanged();
                return var3;
            }
            else
            {
                var3 = this.drawerContents[var1].splitStack(var2);

                if (this.drawerContents[var1].stackSize == 0)
                {
                    this.drawerContents[var1] = null;
                }

                this.onInventoryChanged();
                return var3;
            }
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
        this.drawerContents[var1] = var2;

        if (var2 != null && var2.stackSize > this.getInventoryStackLimit())
        {
            var2.stackSize = this.getInventoryStackLimit();
        }

        this.onInventoryChanged();
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "Beer barrel";
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);
        NBTTagList var2 = var1.getTagList("Items");
        this.drawerContents = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            int var5 = var4.getByte("Slot") & 255;

            if (var5 >= 0 && var5 < this.drawerContents.length)
            {
                this.drawerContents[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.drawerContents.length; ++var3)
        {
            if (this.drawerContents[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.drawerContents[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        var1.setTag("Items", var2);
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer var1)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : var1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    /**
     * Causes the TileEntity to reset all it's cached values for it's container block, blockID, metaData and in the case
     * of chests, the adjcacent chest check
     */
    public void updateContainingBlockInfo()
    {
        super.updateContainingBlockInfo();
        this.adjacentDrawerChecked = false;
    }

    public void checkForAdjacentChests()
    {
        if (!this.adjacentDrawerChecked)
        {
            this.adjacentDrawerChecked = true;
            this.adjacentChestZNeg = null;
            this.adjacentChestXPos = null;
            this.adjacentChestXNeg = null;
            this.adjacentChestZPos = null;

            if (this.worldObj.getBlockId(this.xCoord - 1, this.yCoord, this.zCoord) == BierMod.BlockBierFass.blockID)
            {
                this.adjacentChestXNeg = (TileEntityBierFass)this.worldObj.getBlockTileEntity(this.xCoord - 1, this.yCoord, this.zCoord);
            }

            if (this.worldObj.getBlockId(this.xCoord + 1, this.yCoord, this.zCoord) == BierMod.BlockBierFass.blockID)
            {
                this.adjacentChestXPos = (TileEntityBierFass)this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord);
            }

            if (this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord - 1) == BierMod.BlockBierFass.blockID)
            {
                this.adjacentChestZNeg = (TileEntityBierFass)this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
            }

            if (this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord + 1) == BierMod.BlockBierFass.blockID)
            {
                this.adjacentChestZPos = (TileEntityBierFass)this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord);
            }

            if (this.adjacentChestZNeg != null)
            {
                this.adjacentChestZNeg.updateContainingBlockInfo();
            }

            if (this.adjacentChestZPos != null)
            {
                this.adjacentChestZPos.updateContainingBlockInfo();
            }

            if (this.adjacentChestXPos != null)
            {
                this.adjacentChestXPos.updateContainingBlockInfo();
            }

            if (this.adjacentChestXNeg != null)
            {
                this.adjacentChestXNeg.updateContainingBlockInfo();
            }
        }
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        super.updateEntity();
        this.checkForAdjacentChests();

        if (++this.ticksSinceSync % 20 * 4 == 0)
        {
            this.worldObj.playAuxSFX(this.xCoord, this.yCoord, this.zCoord, 1, this.numUsingPlayers);
        }

        this.prevLidAngle = this.lidAngle;
        float var1 = 0.1F;
        double var2;
        double var4;

        if (this.numUsingPlayers > 0 && this.lidAngle == 0.0F && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null)
        {
            var2 = (double)this.xCoord + 0.5D;
            var4 = (double)this.zCoord + 0.5D;

            if (this.adjacentChestZPos != null)
            {
                var4 += 0.5D;
            }

            if (this.adjacentChestXPos != null)
            {
                var2 += 0.5D;
            }

            this.worldObj.playSoundEffect(var2, (double)this.yCoord + 0.5D, var4, "biermod.bierfassopen", 1.0F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
        }

        if (this.numUsingPlayers == 0 && this.lidAngle > 0.0F || this.numUsingPlayers > 0 && this.lidAngle < 1.0F)
        {
            if (this.numUsingPlayers > 0)
            {
                this.lidAngle += var1;
            }
            else
            {
                this.lidAngle -= var1;
            }

            if (this.lidAngle > 1.0F)
            {
                this.lidAngle = 1.0F;
            }

            if (this.lidAngle < 0.0F)
            {
                this.lidAngle = 0.0F;

                if (this.adjacentChestZNeg == null && this.adjacentChestXNeg == null)
                {
                    var2 = (double)this.xCoord + 0.5D;
                    var4 = (double)this.zCoord + 0.5D;

                    if (this.adjacentChestZPos != null)
                    {
                        var4 += 0.5D;
                    }

                    if (this.adjacentChestXPos != null)
                    {
                        var2 += 0.5D;
                    }

                    this.drawerTimer = 0;
                    this.startDrawerTimer = true;
                }
            }
        }

        if (this.startDrawerTimer)
        {
            if (this.drawerTimer < 5)
            {
                ++this.drawerTimer;
            }
            else
            {
                this.openUpperDrawer = !this.openUpperDrawer;
                this.drawerTimer = 0;
                this.startDrawerTimer = false;
            }
        }
    }

    public void onTileEntityPowered(int var1, int var2)
    {
        if (var1 == 1)
        {
            this.numUsingPlayers = var2;
        }
    }

    public void openChest()
    {
        ++this.numUsingPlayers;
        this.worldObj.playAuxSFX(this.xCoord, this.yCoord, this.zCoord, 1, this.numUsingPlayers);
    }

    public void closeChest()
    {
        for (int var1 = 0; var1 < 9; ++var1)
        {
            ItemStack var2 = this.drawerContents[var1];
            this.drawerContents[var1] = null;
            EntityClientPlayerMP var3 = FMLClientHandler.instance().getClient().thePlayer;
            var3.dropPlayerItem(var2);
        }

        --this.numUsingPlayers;
        this.worldObj.playAuxSFX(this.xCoord, this.yCoord, this.zCoord, 1, this.numUsingPlayers);
    }

    /**
     * invalidates a tile entity
     */
    public void invalidate()
    {
        this.updateContainingBlockInfo();
        this.checkForAdjacentChests();
        super.invalidate();
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int var1)
    {
        ItemStack var2 = this.drawerContents[var1];
        this.drawerContents[var1] = null;
        return var2;
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
