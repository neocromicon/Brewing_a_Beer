package mods.neocromicon.src.BierModBase;

import java.util.EnumSet;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class BierKeyHandler extends KeyHandler {

	   static KeyBinding myBinding = new KeyBinding("Beer settings", Keyboard.KEY_MINUS);
	   public EntityPlayer player;

	public BierKeyHandler()
	{
		super(new KeyBinding[]{myBinding}, new boolean[]{true});
	}
	
	@Override
	public String getLabel() {

		return "BierKeyBinding";
	}

	@Override
    public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {		
		FMLClientHandler.instance().getClient().displayGuiScreen(new GuiBeerMenu(player));
    }

    @Override
    public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {

    }

    @Override
    public EnumSet<TickType> ticks() {
            return EnumSet.of(TickType.CLIENT);
            //I am unsure if any different TickTypes have any different effects.
    }
}
