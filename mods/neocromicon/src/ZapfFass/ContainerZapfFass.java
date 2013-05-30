package mods.neocromicon.src.ZapfFass;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.neocromicon.src.BierModBase.BierMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerZapfFass extends Container
{
    protected TileEntityZapfFass tile_entity;
    private int lastCookTime = 0;
    private int lastCookTime2 = 0;
    private int lastBurnTime = 0;
    private int lastItemBurnTime = 0;

    public ContainerZapfFass(TileEntityZapfFass var1, InventoryPlayer var2)
    {
        this.tile_entity = var1;
        this.addSlotToContainer(new Slot(var1, 0, 66, 17));
        this.addSlotToContainer(new Slot(var1, 1, 56, 53));
        this.addSlotToContainer(new Slot(var1, 3, 46, 17));
        this.addSlotToContainer(new SlotZapfFass(var2.player, var1, 2, 116, 35));
        int var3;

        for (var3 = 0; var3 < 3; ++var3)
        {
            for (int var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new Slot(var2, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3)
        {
            this.addSlotToContainer(new Slot(var2, var3, 8 + var3 * 18, 142));
        }
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int var1 = 0; var1 < this.crafters.size(); ++var1)
        {
            ICrafting var2 = (ICrafting)this.crafters.get(var1);

            if (this.lastCookTime != this.tile_entity.braublockCookTime)
            {
                var2.sendProgressBarUpdate(this, 0, this.tile_entity.braublockCookTime);
            }

            if (this.lastCookTime2 != this.tile_entity.braublockCookTime)
            {
                var2.sendProgressBarUpdate(this, 3, this.tile_entity.braublockCookTime);
            }

            if (this.lastBurnTime != this.tile_entity.braublockBurnTime)
            {
                var2.sendProgressBarUpdate(this, 1, this.tile_entity.braublockBurnTime);
            }

            if (this.lastItemBurnTime != this.tile_entity.ZapfAnlagecurrentItemBurnTime)
            {
                var2.sendProgressBarUpdate(this, 2, this.tile_entity.ZapfAnlagecurrentItemBurnTime);
            }
        }

        this.lastCookTime = this.tile_entity.braublockCookTime;
        this.lastBurnTime = this.tile_entity.braublockBurnTime;
        this.lastItemBurnTime = this.tile_entity.ZapfAnlagecurrentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int var1, int var2)
    {
        if (var1 == 0)
        {
            this.tile_entity.braublockCookTime = var2;
        }

        if (var1 == 3)
        {
            this.tile_entity.braublockCookTime = var2;
        }

        if (var1 == 1)
        {
            this.tile_entity.braublockBurnTime = var2;
        }

        if (var1 == 2)
        {
            this.tile_entity.ZapfAnlagecurrentItemBurnTime = var2;
        }
    }

    public boolean canInteractWith(EntityPlayer var1)
    {
        return this.tile_entity.isUseableByPlayer(var1);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer var1, int var2)
    {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(var2);

        if (var4 != null && var4.getHasStack())
        {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if (var2 == 2)
            {
                if (!this.mergeItemStack(var5, 3, 39, true))
                {
                    return null;
                }

                var4.onSlotChange(var5, var3);
            }
            else if (var2 != 1 && var2 != 0)
            {
                if (RecipesZapfFass.smelting().getSmeltingResult(var5) != null)
                {
                    ItemStack var6 = var4.getStack();
                    int var7 = var6.getItem().itemID;

                    if (var7 == BierMod.LeeresBierGlas.itemID && !this.mergeItemStack(var5, 0, 1, false))
                    {
                        return null;
                    }

                    if (var7 == BierMod.LeeresWeinGlas.itemID && !this.mergeItemStack(var5, 0, 1, false))
                    {
                        return null;
                    }

                    if (var7 == BierMod.SchwarzBierExtrakt.itemID && !this.mergeItemStack(var5, 2, 3, false))
                    {
                        return null;
                    }

                    if (var7 == BierMod.PilsBierExtrakt.itemID && !this.mergeItemStack(var5, 2, 3, false))
                    {
                        return null;
                    }

                    if (var7 == BierMod.WeinExtrakt.itemID && !this.mergeItemStack(var5, 2, 3, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityZapfFass.isItemFuel(var5))
                {
                    if (!this.mergeItemStack(var5, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (var2 >= 3 && var2 < 30)
                {
                    if (!this.mergeItemStack(var5, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (var2 >= 30 && var2 < 39 && !this.mergeItemStack(var5, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(var5, 3, 39, false))
            {
                return null;
            }

            if (var5.stackSize == 0)
            {
                var4.putStack((ItemStack)null);
            }
            else
            {
                var4.onSlotChanged();
            }

            if (var5.stackSize == var3.stackSize)
            {
                return null;
            }

            var4.onPickupFromSlot(var1, var5);
        }

        return var3;
    }
}
