package mods.neocromicon.src.ZapfFass;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.neocromicon.src.BierModBase.BierMod;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class GasAmpulleVollItem extends Item
{
    public String Image;

    public GasAmpulleVollItem(int var1, String var2)
    {
        super(var1);
        this.Image = var2;
        this.setMaxStackSize(1);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister var1)
    {
        this.itemIcon = var1.registerIcon(BierMod.modID + ":" + this.Image);
    }
}
