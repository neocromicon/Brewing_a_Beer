package mods.neocromicon.src.BierModBase;

import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.potion.Potion;
import net.minecraft.src.ModLoader;

public class DrunkEffect extends Potion
{
    boolean sprite;
    private static int DrunkLevel = 0;

    public DrunkEffect(int var1, boolean var2, int var3, String var4)
    {
        super(var1, var2, var3);
        this.setPotionName(var4);
        this.setEffectiveness(0.25D);
    }

    public DrunkEffect(int var1, boolean var2, int var3, boolean var4, String var5)
    {
        super(var1, var2, var3);
        this.sprite = var4;
    }

    /**
     * Returns the index for the icon to display when the potion is active.
     */
    public int getStatusIconIndex()
    {
        RenderEngine var1 = ModLoader.getMinecraftInstance().renderEngine;
        var1.bindTexture("/mods/" + BierMod.modID + "/textures/items/DrunkSymbol.png");
        return super.getStatusIconIndex();
    }

    public static void DrunkLevelPlus()
    {
        ++DrunkLevel;
    }

    public static void DrunkLevelMinus()
    {
        --DrunkLevel;
    }

    public static int getDrunkLevel()
    {
        return DrunkLevel;
    }

    public static void setDrunkLevel(int var0)
    {
        DrunkLevel = var0;
    }

    /**
     * Set the potion name.
     */    
    public DrunkEffect setPotionName(String name)
    {
      return (DrunkEffect)super.setPotionName(name);
    }
}
