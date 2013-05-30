package mods.neocromicon.src.BierModBase;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import java.util.EnumSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class ClientTickHandler implements ITickHandler
{
    public static UpdateChecker updateNotifier;

    public void tickStart(EnumSet var1, Object ... var2) {}

    public void tickEnd(EnumSet var1, Object ... var2)
    {
        if (var1.equals(EnumSet.of(TickType.RENDER)))
        {
            this.onRenderTick();
        }
        else if (var1.equals(EnumSet.of(TickType.CLIENT)))
        {
            GuiScreen var3 = Minecraft.getMinecraft().currentScreen;

            if (var3 != null)
            {
                this.onTickInGUI(var3);
            }
            else
            {
                this.onTickInGame();
            }
        }
    }

    public EnumSet ticks()
    {
        return EnumSet.of(TickType.RENDER, TickType.CLIENT);
    }

    public String getLabel()
    {
        return null;
    }

    public void onRenderTick() {}

    public void onTickInGUI(GuiScreen var1) {}

    public void onTickInGame()
    {
        updateNotifier = new UpdateChecker((ServerTickHandler)null, this);
        updateNotifier.attemptClient();
    }
}
