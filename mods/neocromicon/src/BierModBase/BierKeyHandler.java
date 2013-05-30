package mods.neocromicon.src.BierModBase;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry$KeyHandler;
import cpw.mods.fml.common.TickType;
import java.util.EnumSet;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;

public class BierKeyHandler extends KeyBindingRegistry$KeyHandler
{
    static KeyBinding myBinding = new KeyBinding("Beer settings", 12);
    public EntityPlayer player;

    public BierKeyHandler()
    {
        super(new KeyBinding[] {myBinding}, new boolean[] {true});
    }

    public String getLabel()
    {
        return "BierKeyBinding";
    }

    public void keyDown(EnumSet var1, KeyBinding var2, boolean var3, boolean var4)
    {
        FMLClientHandler.instance().getClient().displayGuiScreen(new GuiBeerMenu(this.player));
    }

    public void keyUp(EnumSet var1, KeyBinding var2, boolean var3) {}

    public EnumSet ticks()
    {
        return EnumSet.of(TickType.CLIENT);
    }
}
