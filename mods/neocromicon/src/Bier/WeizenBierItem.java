package mods.neocromicon.src.Bier;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.neocromicon.src.BierModBase.BierMod;
import mods.neocromicon.src.BierModBase.PacketHandler;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class WeizenBierItem extends ItemFood
{
    public String Image;
    public final int field_35430_a;
    private final int healAmount;
    private final float saturationModifier;
    private final boolean isWolfsFavoriteMeat;
    private boolean alwaysEdible;
    private int potionId;
    private int potionDuration;
    private int potionAmplifier;
    private float potionEffectProbability;

    public WeizenBierItem(int var1, int var2, int var3, float var4, boolean var5, String var6)
    {
        super(var1, var2, (float)var3, var5);
        this.Image = var6;
        this.field_35430_a = 32;
        this.healAmount = var3;
        this.isWolfsFavoriteMeat = var5;
        this.saturationModifier = var4;
        this.maxStackSize = 8;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister var1)
    {
        this.itemIcon = var1.registerIcon(this.Image);
    }

    public ItemStack onEaten(ItemStack var1, World var2, EntityPlayer var3)
    {
        --var1.stackSize;
        var3.getFoodStats().addStats(this);
        var2.playSoundAtEntity(var3, "random.burp", 0.5F, var2.rand.nextFloat() * 0.1F + 0.9F);
        Side var4 = FMLCommonHandler.instance().getEffectiveSide();

        if (var4 == Side.SERVER)
        {
            EntityPlayerMP var5 = (EntityPlayerMP)var3;
            PacketDispatcher.sendPacketToPlayer(PacketHandler.getPortPacket(1, 0, 350, 0), (Player)var5);
        }
        else if (var4 == Side.CLIENT)
        {
            EntityClientPlayerMP var6 = (EntityClientPlayerMP)var3;
            PacketDispatcher.sendPacketToServer(PacketHandler.getPortPacket(1, 350, 500, 0));
        }
        else
        {
            System.out.println("FTW Bukkit Support? You a genius!!! :D And no the Drunklevel dont work");
        }

        if (var1.stackSize <= 0)
        {
            return new ItemStack(BierMod.LeeresBierGlas);
        }
        else
        {
            var3.inventory.addItemStackToInventory(new ItemStack(BierMod.LeeresBierGlas));
            return var1;
        }
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack var1)
    {
        return EnumAction.drink;
    }

    public int getHealAmount()
    {
        return this.healAmount;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Return an item rarity from EnumRarity
     */
    public EnumRarity getRarity(ItemStack var1)
    {
        return EnumRarity.uncommon;
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack var1)
    {
        return var1.getItemDamage() > 0;
    }
}
