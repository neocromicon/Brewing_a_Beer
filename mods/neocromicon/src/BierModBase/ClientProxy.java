package mods.neocromicon.src.BierModBase;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.logging.Logger;

import mods.neocromicon.src.BierFass.TileEntityBierFass;
import mods.neocromicon.src.BierFass.TileEntityBierFassRenderer;
import mods.neocromicon.src.GaerTank.ItemGaerTank;
import mods.neocromicon.src.GaerTank.TileEntityGaerTank;
import mods.neocromicon.src.GaerTank.TileEntityGaerTankRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.logging.ILogAgent;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.dedicated.PropertyManager;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class ClientProxy extends CommonProxy implements ILogAgent
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

        try
		{
		Properties props = new Properties();
		File ini = new File(mc.getMinecraftDir(), "config/Brewing_a_Beer_Update.cfg");			
		propertyManagerObj = new PropertyManager(new File(mc.getMinecraftDir(), "config/Brewing_a_Beer_Update.cfg"), this);
		ini.mkdir();
		props.load(new FileInputStream(ini));
		props.setProperty("UpdateCheck", "true");
		props.store(new FileOutputStream(ini), "BeerMod Update Checker");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
    	
    	try
    	{
    	Properties props = new Properties();		
		File ini = new File(mc.getMinecraftDir(), "config/Brewing_a_Beer_Crafting_Settings.cfg");
		if (ini.canRead())
		{
			return;
		}
		else
		{				
			propertyManagerObj = new PropertyManager(new File(mc.getMinecraftDir(), "config/Brewing_a_Beer_Crafting_Settings.cfg"), this);
			ini.mkdir();
			props.load(new FileInputStream(ini));
			props.setProperty("CraftingDifficulty", "2");
			props.store(new FileOutputStream(ini), "Brewing a Beer_Crafting Settings");
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static int getPropertyVolume(String store)
    {
        try
        {
            Properties properties = new Properties();
            String store2 = (new StringBuilder()).append(mc.getMinecraftDir()).append("/config/Brewing_a_Beer_Crafting_Settings.cfg").toString();
            properties.load(new FileInputStream(store2));
            String store1 = properties.getProperty(store);
            return Integer.parseInt(store1);
        }
        catch(Exception exception)
        {
            System.err.println("Could not detect Brewing_a_Beer_Crafting_Settings.cfg");
            exception.printStackTrace();
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
    public void onEntityUpdate(LivingUpdateEvent var1)
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

	@Override
	public void logInfo(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@SideOnly(Side.SERVER)
	public Logger getServerLogger() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logWarning(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logWarningFormatted(String s, Object... var2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logWarningException(String s, Throwable throwable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logSevere(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logSevereException(String s, Throwable throwable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void logFine(String s) {
		// TODO Auto-generated method stub
		
	}
}
