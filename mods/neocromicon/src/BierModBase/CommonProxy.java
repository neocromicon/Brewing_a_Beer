package mods.neocromicon.src.BierModBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.logging.Logger;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.Minecraft;
import net.minecraft.logging.ILogAgent;
import net.minecraft.server.dedicated.PropertyManager;

public class CommonProxy
{
    public PropertyManager propertyManagerObj;

    public void renderInformation()
    {
    	try
		{
		Properties props = new Properties();
		File ini = new File("Brewing_a_Beer_Update.cfg");
		ini.createNewFile();
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
		File ini = new File("Brewing_a_Beer_Server_Crafting_Settings.cfg");
		if (ini.canRead())
		{
			return;
		}
		else
		{				
			ini.createNewFile();
			props.load(new FileInputStream(ini));
			props.setProperty("CraftingDifficulty", "2");
			props.store(new FileOutputStream(ini), "Brewing a Beer_Crafting Settings Server");
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

    public static int getPropertyVolume(String var0)
    {
        try
        {
            Properties var1 = new Properties();
            String var2 = "Brewing_a_Beer_Server_Crafting_Settings.cfg";
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
