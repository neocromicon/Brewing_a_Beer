package mods.neocromicon.src.BierModBase;

import cpw.mods.fml.common.network.IGuiHandler;
import mods.neocromicon.src.GaerTank.ContainerGaerTank;
import mods.neocromicon.src.GaerTank.GuiGaerTank;
import mods.neocromicon.src.GaerTank.TileEntityGaerTank;
import mods.neocromicon.src.GasExtraktor.ContainerGasExtraktor;
import mods.neocromicon.src.GasExtraktor.GuiGasExtraktor;
import mods.neocromicon.src.GasExtraktor.TileEntityGasExtraktor;
import mods.neocromicon.src.ZapfFass.ContainerZapfFass;
import mods.neocromicon.src.ZapfFass.GuiZapfFass;
import mods.neocromicon.src.ZapfFass.TileEntityZapfFass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{
    public void registerRenderInformation() {}

    public Object getServerGuiElement(int var1, EntityPlayer var2, World var3, int var4, int var5, int var6)
    {
        TileEntity var7;

        if (var1 == 10)
        {
            var7 = var3.getBlockTileEntity(var4, var5, var6);
            return new ContainerZapfFass((TileEntityZapfFass)var7, var2.inventory);
        }
        else if (var1 == 11)
        {
            var7 = var3.getBlockTileEntity(var4, var5, var6);
            return new ContainerGasExtraktor((TileEntityGasExtraktor)var7, var2.inventory);
        }
        else if (var1 == 12)
        {
            var7 = var3.getBlockTileEntity(var4, var5, var6);
            return new ContainerGaerTank((TileEntityGaerTank)var7, var2.inventory);
        }
        else
        {
            if (var1 == 13)
            {
                System.out.println("OpenContainer");
            }

            return null;
        }
    }

    public Object getClientGuiElement(int var1, EntityPlayer var2, World var3, int var4, int var5, int var6)
    {
        TileEntity var7;

        if (var1 == 10)
        {
            var7 = var3.getBlockTileEntity(var4, var5, var6);
            return new GuiZapfFass(var2.inventory, (TileEntityZapfFass)var7);
        }
        else if (var1 == 11)
        {
            var7 = var3.getBlockTileEntity(var4, var5, var6);
            return new GuiGasExtraktor(var2.inventory, (TileEntityGasExtraktor)var7);
        }
        else if (var1 == 12)
        {
            var7 = var3.getBlockTileEntity(var4, var5, var6);
            return new GuiGaerTank(var2.inventory, (TileEntityGaerTank)var7);
        }
        else
        {
            return null;
        }
    }

    public World getClientWorld()
    {
        return null;
    }
}
