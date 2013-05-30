package mods.neocromicon.src.GaerTank;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerGaerTank extends Container
{
    private TileEntityGaerTank GasExtraktor;
    private int lastCookTime = 0;
    private int lastBurnTime = 0;
    private int lastItemBurnTime = 0;

    public ContainerGaerTank(TileEntityGaerTank var1, InventoryPlayer var2)
    {
        this.GasExtraktor = var1;
        this.addSlotToContainer(new Slot(var1, 0, 56, 17));
        this.addSlotToContainer(new Slot(var1, 1, 56, 53));
        this.addSlotToContainer(new SlotGaerTank(var2.player, var1, 2, 116, 35));
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
        TileEntityGaerTank var10001;

        for (int var1 = 0; var1 < this.crafters.size(); ++var1)
        {
            ICrafting var2 = (ICrafting)this.crafters.get(var1);
            var10001 = this.GasExtraktor;

            if (this.lastCookTime != TileEntityGaerTank.gaertankCookTime)
            {
                TileEntityGaerTank var10003 = this.GasExtraktor;
                var2.sendProgressBarUpdate(this, 0, TileEntityGaerTank.gaertankCookTime);
            }

            if (this.lastBurnTime != this.GasExtraktor.gaertankBurnTime)
            {
                var2.sendProgressBarUpdate(this, 1, this.GasExtraktor.gaertankBurnTime);
            }

            if (this.lastItemBurnTime != this.GasExtraktor.gaertankcurrentItemBurnTime)
            {
                var2.sendProgressBarUpdate(this, 2, this.GasExtraktor.gaertankcurrentItemBurnTime);
            }
        }

        var10001 = this.GasExtraktor;
        this.lastCookTime = TileEntityGaerTank.gaertankCookTime;
        this.lastBurnTime = this.GasExtraktor.gaertankBurnTime;
        this.lastItemBurnTime = this.GasExtraktor.gaertankcurrentItemBurnTime;
    }

    public static int myRandom(int var0, int var1)
    {
        return (int)(Math.random() * (double)(var1 - var0) + (double)var0);
    }

    public void updateProgressBar(int var1, int var2)
    {
        if (var1 == 0)
        {
            TileEntityGaerTank var10000 = this.GasExtraktor;
            TileEntityGaerTank.gaertankCookTime = var2;
        }

        if (var1 == 1)
        {
            this.GasExtraktor.gaertankBurnTime = var2;
        }

        if (var1 == 2)
        {
            this.GasExtraktor.gaertankcurrentItemBurnTime = var2;
        }
    }

    public boolean canInteractWith(EntityPlayer var1)
    {
        return this.GasExtraktor.isUseableByPlayer(var1);
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
                if (RecipesGaerTank.smelting().getSmeltingResult(var5) != null)
                {
                    if (!this.mergeItemStack(var5, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityGaerTank.isItemFuel(var5))
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
