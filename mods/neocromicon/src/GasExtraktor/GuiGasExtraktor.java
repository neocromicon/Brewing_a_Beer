package mods.neocromicon.src.GasExtraktor;

import mods.neocromicon.src.BierModBase.BierMod;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiGasExtraktor extends GuiContainer
{
    private TileEntityGasExtraktor GasExtraktorInventory;

    public GuiGasExtraktor(InventoryPlayer var1, TileEntityGasExtraktor var2)
    {
        super(new ContainerGasExtraktor(var2, var1));
        this.GasExtraktorInventory = var2;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int var1, int var2)
    {
        this.fontRenderer.drawString(BierMod.GasExtraktorIdle.getLocalizedName(), 60, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/mods/" + BierMod.modID + "/textures/blocks/GasExtraktor/gasExtraktorGui.png");
        int var4 = (this.width - this.xSize) / 2;
        int var5 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
        int var6;

        if (this.GasExtraktorInventory.isBurning())
        {
            var6 = this.GasExtraktorInventory.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(var4 + 56, var5 + 36 + 12 - var6, 176, 12 - var6, 14, var6 + 2);
        }

        var6 = this.GasExtraktorInventory.getCookProgressScaled(24);
        this.drawTexturedModalRect(var4 + 79, var5 + 34, 176, 14, var6 + 1, 16);
    }
}
