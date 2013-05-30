package mods.neocromicon.src.BierModBase;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;

public class DamageSourceBeer extends DamageSource
{
    protected ItemStack damageSourceItem;
    public String damageTypeBeer = "Stay safe drinking water, ";

    public DamageSourceBeer(String var1)
    {
        super(var1);
    }

    public ItemStack getItem()
    {
        return this.damageSourceItem;
    }

    /**
     * Returns the message to be displayed on player death.
     */
    public String getDeathMessage(EntityLiving var1)
    {
        return StatCollector.translateToLocalFormatted(this.damageTypeBeer + var1.getEntityName(), new Object[] {var1.getEntityName()});
    }

    public static DamageSource causeItemDamage(EntityPlayer var0)
    {
        return new DamageSourceBeer("item");
    }
}
