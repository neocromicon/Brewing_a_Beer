package mods.neocromicon.src.BierModBase;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Properties;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.StringTranslate;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiBeerMenu extends GuiScreen
{
    public final int xSizeOfTexture = 176;
    public final int ySizeOfTexture = 88;

    public GuiBeerMenu(EntityPlayer var1) {}

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int var1, int var2, float var3)
    {
        this.drawDefaultBackground();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/mods/" + BierMod.modID + "/textures/guiBeerMenu.png");
        int var4 = (this.width - 176) / 2;
        int var5 = (this.height - 88) / 2;
        this.drawTexturedModalRect(var4, var5, 0, 0, 176, 88);
        StringTranslate var6 = StringTranslate.getInstance();
        this.drawCenteredString(this.fontRenderer, "Crafting " + var6.translateKey("options.title") + " Brewing a Beer", this.width / 2, var5 + 7, 16777215);
        super.drawScreen(var1, var2, var3);
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        this.buttonList.clear();
        int var1 = (this.width - 176) / 2;
        int var2 = (this.height - 88) / 2;
        StringTranslate var3 = StringTranslate.getInstance();
        this.buttonList.add(new GuiButton(0, var1 + 12, var2 + 20, 150, 20, var3.translateKey("options.difficulty.easy")));
        this.buttonList.add(new GuiButton(1, var1 + 12, var2 + 41, 150, 20, var3.translateKey("options.difficulty.hard")));
        this.buttonList.add(new GuiButton(2, var1 + 12, var2 + 62, 150, 20, var3.translateKey("gui.done")));
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    public void actionPerformed(GuiButton var1)
    {
        Properties var2;
        File var3;
        File var10000;
        Minecraft var10002;

        switch (var1.id)
        {
            case 0:
                try
                {
                    var2 = new Properties();
                    var10000 = new File(Minecraft.getMinecraftDir(), "config/Brewing_a_Beer_Crafting_Settings.cfg");
                    var10002 = this.mc;
                    var3 = var10000;
                    var3.mkdir();
                    var2.load(new FileInputStream(var3));
                    var2.setProperty("CraftingDifficulty", "1");
                    var2.store(new FileOutputStream(var3), "Brewing a Beer_Crafting Settings");
                    RemoveRecipe(new ItemStack(BierMod.GasAmpulleLeer, 1));
                    RemoveRecipe(new ItemStack(BierMod.GasExtraktorIdle, 1));
                    RemoveRecipe(new ItemStack(BierMod.BlockGaerTank, 1));
                    GameRegistry.addRecipe(new ItemStack(BierMod.GasAmpulleLeer, 1), new Object[] {"III", "IOI", "III", 'I', Item.ingotIron, 'O', Block.obsidian});
                    GameRegistry.addRecipe(new ItemStack(BierMod.GasExtraktorIdle, 1), new Object[] {"HHH", "IOI", "HHH", 'H', Block.planks, 'I', Item.ingotIron, 'O', Block.obsidian});
                    GameRegistry.addRecipe(new ItemStack(BierMod.BlockGaerTank, 1), new Object[] {"III", "IOI", "IOI", 'I', Item.ingotIron, 'O', Block.obsidian});
                    this.mc.thePlayer.closeScreen();
                }
                catch (Exception var5)
                {
                    var5.printStackTrace();
                }

                break;

            case 1:
                try
                {
                    var2 = new Properties();
                    var10000 = new File(Minecraft.getMinecraftDir(), "config/Brewing_a_Beer_Crafting_Settings.cfg");
                    var10002 = this.mc;
                    var3 = var10000;
                    var3.mkdir();
                    var2.load(new FileInputStream(var3));
                    var2.setProperty("CraftingDifficulty", "2");
                    var2.store(new FileOutputStream(var3), "Brewing a Beer_Crafting Settings");
                    RemoveRecipe(new ItemStack(BierMod.GasAmpulleLeer, 1));
                    RemoveRecipe(new ItemStack(BierMod.GasExtraktorIdle, 1));
                    RemoveRecipe(new ItemStack(BierMod.BlockGaerTank, 1));
                    GameRegistry.addRecipe(new ItemStack(BierMod.GasAmpulleLeer, 1), new Object[] {"III", "EOE", "EEE", 'I', Item.ingotIron, 'E', Block.blockIron, 'O', Block.obsidian});
                    GameRegistry.addRecipe(new ItemStack(BierMod.GasExtraktorIdle, 1), new Object[] {"HHH", "IEI", "HHH", 'H', Block.planks, 'I', Item.ingotIron, 'E', Block.blockIron});
                    GameRegistry.addRecipe(new ItemStack(BierMod.BlockGaerTank, 1), new Object[] {"EIE", "EOE", "EOE", 'I', Item.ingotIron, 'E', Block.blockIron, 'O', Block.obsidian});
                    this.mc.thePlayer.closeScreen();
                }
                catch (Exception var4)
                {
                    var4.printStackTrace();
                }

                break;

            case 2:
                this.mc.thePlayer.closeScreen();
        }
    }

    private static void RemoveRecipe(ItemStack var0)
    {
        ItemStack var1 = null;
        ArrayList var2 = (ArrayList)CraftingManager.getInstance().getRecipeList();

        for (int var3 = 0; var3 < var2.size(); ++var3)
        {
            IRecipe var4 = (IRecipe)var2.get(var3);

            if (var4 instanceof ShapedRecipes)
            {
                ShapedRecipes var5 = (ShapedRecipes)var4;
                var1 = var5.getRecipeOutput();
            }

            if (var4 instanceof ShapelessRecipes)
            {
                ShapelessRecipes var6 = (ShapelessRecipes)var4;
                var1 = var6.getRecipeOutput();
            }

            if (ItemStack.areItemStacksEqual(var0, var1))
            {
                var2.remove(var3);
            }
        }
    }
}
