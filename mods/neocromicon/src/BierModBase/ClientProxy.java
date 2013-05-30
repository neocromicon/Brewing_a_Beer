package mods.neocromicon.src.BierModBase;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import mods.neocromicon.src.BierFass.TileEntityBierFass;
import mods.neocromicon.src.BierFass.TileEntityBierFassRenderer;
import mods.neocromicon.src.GaerTank.ItemGaerTank;
import mods.neocromicon.src.GaerTank.TileEntityGaerTank;
import mods.neocromicon.src.GaerTank.TileEntityGaerTankRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.dedicated.PropertyManager;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent;

public class ClientProxy extends CommonProxy
{
    public PropertyManager propertyManagerObj;
    public static Minecraft mc = Minecraft.getMinecraft();
    private File testFile;

    public void renderInformation()
    {
        ClientRegistry.registerTileEntity(TileEntityBierFass.class, "BierFasstileEntity", new TileEntityBierFassRenderer());
        ClientRegistry.registerTileEntity(TileEntityGaerTank.class, "tileEntityGaerFassIdle", new TileEntityGaerTankRenderer());
        MinecraftForgeClient.registerItemRenderer(BierMod.BlockGaerTankID, new ItemGaerTank());
        KeyBindingRegistry.registerKeyBinding(new BierKeyHandler());
        MinecraftForge.EVENT_BUS.register(new ClientProxy());
        TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
        Properties var1;
        File var2;
        File var10000;
        Minecraft var10003;
        StringBuilder var10002;

        try
        {
            var1 = new Properties();
            var10000 = new File;
            var10002 = new StringBuilder();
            var10003 = mc;
            var10000.<init>(var10002.append(Minecraft.getMinecraftDir()).append("//config//").toString(), "Brewing_a_Beer_Update.cfg");
            var2 = var10000;
            var2.createNewFile();
            var1.load(new FileInputStream(var2));
            var1.setProperty("UpdateCheck", "true");
            var1.store(new FileOutputStream(var2), "BeerMod Update Checker");
        }
        catch (Exception var4)
        {
            var4.printStackTrace();
        }

        try
        {
            var1 = new Properties();
            var10000 = new File;
            var10002 = new StringBuilder();
            var10003 = mc;
            var10000.<init>(var10002.append(Minecraft.getMinecraftDir()).append("//config//").toString(), "Brewing_a_Beer_Crafting_Settings.cfg");
            var2 = var10000;

            if (var2.canRead())
            {
                return;
            }

            var2.createNewFile();
            var1.load(new FileInputStream(var2));
            var1.setProperty("CraftingDifficulty", "2");
            var1.store(new FileOutputStream(var2), "Brewing a Beer_Crafting Settings");
        }
        catch (Exception var3)
        {
            var3.printStackTrace();
        }
    }

    public static int getPropertyVolume(String var0)
    {
        try
        {
            Properties var1 = new Properties();
            StringBuilder var10000 = new StringBuilder();
            Minecraft var10001 = mc;
            String var2 = var10000.append(Minecraft.getMinecraftDir()).append("/config/Brewing_a_Beer_Crafting_Settings.cfg").toString();
            var1.load(new FileInputStream(var2));
            String var3 = var1.getProperty(var0);
            return Integer.parseInt(var3);
        }
        catch (Exception var4)
        {
            System.err.println("Could not detect Brewing_a_Beer_Crafting_Settings.cfg");
            var4.printStackTrace();
            return 0;
        }
    }

    @ForgeSubscribe
    public void onSound(SoundLoadEvent var1)
    {
        try
        {
            File var2 = new File(mc.mcDataDir, "resources/mod/biermod/zapffass.ogg");
            mc.installResource("sound3/biermod/zapffass.ogg", var2);
            File var3 = new File(mc.mcDataDir, "resources/mod/biermod/bierfassopen.ogg");
            mc.installResource("sound3/biermod/bierfassopen.ogg", var3);
        }
        catch (Exception var4)
        {
            System.err.println("Failed To Register Sound: " + var4.getMessage());
        }
    }

    @ForgeSubscribe
    public void onEntityUpdate(LivingEvent$LivingUpdateEvent var1)
    {
        EntityClientPlayerMP var2 = FMLClientHandler.instance().getClient().thePlayer;

        if (var1.entityLiving.isPotionActive(BierMod.DrunkEffekt))
        {
            var2.addPotionEffect(new PotionEffect(Potion.confusion.id, 100, 1));

            if (var1.entityLiving.worldObj.rand.nextInt(2) == 0)
            {
                DrunkEffect.DrunkLevelPlus();
                System.out.println(DrunkEffect.getDrunkLevel());
                var2.timeInPortal += 0.006666667F;

                if (var2.timeInPortal > 1.0F)
                {
                    var2.timeInPortal = 2.0F;
                }
            }
            else
            {
                if (var2.timeInPortal > 0.0F)
                {
                    var2.timeInPortal -= 0.01F;
                }

                if (var2.timeInPortal < 0.0F)
                {
                    var2.timeInPortal = 0.0F;
                }
            }

            if (DrunkEffect.getDrunkLevel() > 300)
            {
                PacketDispatcher.sendPacketToServer(PacketHandler.getPortPacket(2, 0, 500, 0));
            }

            if (DrunkEffect.getDrunkLevel() > 650)
            {
                PacketDispatcher.sendPacketToServer(PacketHandler.getPortPacket(3, 0, 0, 0));
            }
        }
    }
}
