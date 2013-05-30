package mods.neocromicon.src.BierModBase;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import java.util.EnumSet;

public class ServerTickHandler implements ITickHandler
{
    public static UpdateChecker updateNotifier;

    public void tickStart(EnumSet var1, Object ... var2) {}

    public void tickEnd(EnumSet var1, Object ... var2)
    {
        if (var1.equals(EnumSet.of(TickType.SERVER)))
        {
            this.onRenderTick();
        }
    }

    public void onRenderTick()
    {
        updateNotifier = new UpdateChecker(this, (ClientTickHandler)null);
        updateNotifier.attemptServer();
    }

    public EnumSet ticks()
    {
        return EnumSet.of(TickType.SERVER);
    }

    public String getLabel()
    {
        return null;
    }
}
