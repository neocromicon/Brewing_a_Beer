package mods.neocromicon.src.BierModBase;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;

public class UpdateChecker extends Thread
{
    private ServerTickHandler modServer;
    private ClientTickHandler modClient;
    private static Minecraft mc;

    UpdateChecker(ServerTickHandler var1, ClientTickHandler var2)
    {
        this.modServer = var1;
        this.modClient = var2;
    }

    @SideOnly(Side.SERVER)
    public void attemptServer()
    {
        String var1 = getPropertyVolumeServer("UpdateCheck");

        if (var1.endsWith("true"))
        {
            this.startServer();

            try
            {
                Properties var2 = new Properties();
                File var3 = new File("config/Brewing_a_Beer_Update_Server.cfg");
                var3.mkdir();
                var2.load(new FileInputStream(var3));
                var2.setProperty("UpdateCheck", "false");
                var2.store(new FileOutputStream(var3), "BeerMod Update CheckerServer");
                var3.exists();
            }
            catch (Exception var4)
            {
                var4.printStackTrace();
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void attemptClient()
    {
        String var1 = getPropertyVolumeClient("UpdateCheck");

        if (var1.endsWith("true"))
        {
            this.startClient();

            try
            {
                Properties var2 = new Properties();
                File var10000 = new File(Minecraft.getMinecraftDir(), "config/Brewing_a_Beer_Update.cfg");
                Minecraft var10002 = mc;
                File var3 = var10000;
                var3.mkdir();
                var2.load(new FileInputStream(var3));
                var2.setProperty("UpdateCheck", "false");
                var2.store(new FileOutputStream(var3), "BeerMod Update Checker");
            }
            catch (Exception var4)
            {
                var4.printStackTrace();
            }
        }
    }

    @SideOnly(Side.SERVER)
    public void startServer()
    {
        try
        {
            URL var1 = new URL("http://46.38.239.84/neo/updater/BeerModUpdate");
            URL var2 = new URL("http://46.38.239.84/neo/updater/BeerModUpdateInfo");
            BufferedReader var3 = new BufferedReader(new InputStreamReader(var1.openStream()));
            BufferedReader var4 = new BufferedReader(new InputStreamReader(var2.openStream()));
            String var6 = var4.readLine();
            String var7 = BierMod.modVersion;
            Logger var8 = Logger.getLogger("Minecraft");
            String var5;

            while ((var5 = var3.readLine()) != null)
            {
                if (var5.endsWith(var7))
                {
                    var8.info("[Brew a Beer] Your version is UpToDate: v" + var5);
                }
                else
                {
                    var8.info("[Brew a Beer] A new version is available: v" + var5);
                    var8.info("[Brew a Beer] New Features of v" + var5 + ": " + var6);
                    var8.info("[Brew a Beer] Your version is: v" + var7);
                }
            }

            var3.close();
        }
        catch (Exception var9)
        {
            ;
        }
    }

    @SideOnly(Side.CLIENT)
    public void startClient()
    {
        try
        {
            URL var1 = new URL("http://46.38.239.84/neo/updater/BeerModUpdate");
            URL var2 = new URL("http://46.38.239.84/neo/updater/BeerModUpdateInfo");
            BufferedReader var3 = new BufferedReader(new InputStreamReader(var1.openStream()));
            BufferedReader var4 = new BufferedReader(new InputStreamReader(var2.openStream()));
            String var6 = var4.readLine();
            String var7 = BierMod.modVersion;
            EntityClientPlayerMP var8 = FMLClientHandler.instance().getClient().thePlayer;
            String var5;

            while ((var5 = var3.readLine()) != null)
            {
                if (var5.endsWith(var7))
                {
                    FMLLog.info("[Brew a Beer] Your version is UpToDate: v" + var5, new Object[0]);
                }
                else
                {
                    FMLLog.info("[Brew a Beer] A new version is available: v" + var5, new Object[0]);
                    var8.addChatMessage("\u00a72[Brew a Beer]\u00a7r A new version is available: v" + var5);
                    var8.addChatMessage("\u00a72[Brew a Beer]\u00a7r New Features of v" + var5 + ": " + var6);
                    var8.addChatMessage("\u00a72[Brew a Beer]\u00a7r Your version is: v" + var7);
                }
            }

            var3.close();
        }
        catch (Exception var9)
        {
            ;
        }
    }

    private static String getPropertyVolumeClient(String var0)
    {
        try
        {
            Properties var1 = new Properties();
            StringBuilder var10000 = new StringBuilder();
            StringBuilder var10001 = new StringBuilder();
            Minecraft var10002 = mc;
            String var2 = var10000.append(var10001.append(Minecraft.getMinecraftDir()).append("//config//Brewing_a_Beer_Update.cfg").toString()).toString();
            var1.load(new FileInputStream(var2));
            String var3 = var1.getProperty(var0);
            return var3;
        }
        catch (Exception var4)
        {
            System.err.println("Could not detect Brewing_a_Beer_Update.cfg");
            var4.printStackTrace();
            return null;
        }
    }

    private static String getPropertyVolumeServer(String var0)
    {
        try
        {
            Properties var1 = new Properties();
            StringBuilder var10000 = new StringBuilder();
            StringBuilder var10001 = new StringBuilder();
            Minecraft var10002 = mc;
            String var2 = var10000.append(var10001.append(Minecraft.getMinecraftDir()).append("//config//Brewing_a_Beer_Update_Server.cfg").toString()).toString();
            var1.load(new FileInputStream(var2));
            String var3 = var1.getProperty(var0);
            return var3;
        }
        catch (Exception var4)
        {
            System.err.println("Could not detect Brewing_a_Beer_Update_Server.cfg");
            var4.printStackTrace();
            return null;
        }
    }
}
