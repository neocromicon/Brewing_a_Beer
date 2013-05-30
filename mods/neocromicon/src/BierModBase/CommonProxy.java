package mods.neocromicon.src.BierModBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import net.minecraft.logging.ILogAgent;
import net.minecraft.server.dedicated.PropertyManager;

public class CommonProxy
{
    public static String textures = "/BierModTextures/Bier.png";
    public static String texturesZapfFass = "/BierModTextures/ZapfHahn/zapfhahn.png";
    public static String texturesGasExtraktor = "/BierModTextures/GasExtraktor/GasExtraktor.png";
    public PropertyManager propertyManagerObj;

    public void renderInformation()
    {
        Properties var1;
        File var2;

        try
        {
            var1 = new Properties();
            var2 = new File("config/Brewing_a_Beer_Update_Server.cfg");
            this.propertyManagerObj = new PropertyManager(new File("config/Brewing_a_Beer_Update_Server.cfg"), (ILogAgent)null);
            var2.mkdir();
            var1.load(new FileInputStream(var2));
            var1.setProperty("UpdateCheck", "true");
            var1.store(new FileOutputStream(var2), "BeerMod Update Checker Server");
        }
        catch (Exception var4)
        {
            var4.printStackTrace();
        }

        try
        {
            var1 = new Properties();
            var2 = new File("config/Brewing_a_Beer_Server_Crafting_Settings.cfg");

            if (var2.canRead())
            {
                return;
            }

            System.out.println("Creating Brewing_a_Beer_Server_Crafting_Settings.cfg");
            this.propertyManagerObj = new PropertyManager(new File("config/Brewing_a_Beer_Server_Crafting_Settings.cfg"), (ILogAgent)null);
            var2.mkdir();
            var1.load(new FileInputStream(var2));
            var1.setProperty("CraftingDifficulty", "2");
            var1.store(new FileOutputStream(var2), "Brewing a Beer Server_Crafting Settings.cfg");
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
            String var2 = "config/Brewing_a_Beer_Server_Crafting_Settings.cfg";
            var1.load(new FileInputStream(var2));
            String var3 = var1.getProperty(var0);
            return Integer.parseInt(var3);
        }
        catch (Exception var4)
        {
            System.err.println("Could not detect Brewing_a_Beer_Server_Crafting_Settings.cfg");
            var4.printStackTrace();
            return 0;
        }
    }
}
