package mods.neocromicon.src.BierFass;

import java.util.Random;
import mods.neocromicon.src.BierModBase.BierMod;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileEntityBierFassRenderer extends TileEntitySpecialRenderer
{
    private ModelBierFass bierfass = new ModelBierFass();
    private Random random = new Random();

    public void renderAModelAt(TileEntityBierFass var1, double var2, double var4, double var6, float var8)
    {
        Block var9 = var1.getBlockType();
        int var10 = var1.getBlockMetadata();

        if (var9 != null && var10 == 0)
        {
            ((BlockBierFass)var9).setDefaultDirection(var1.worldObj, var1.xCoord, var1.yCoord, var1.zCoord);
        }

        short var11 = 0;

        if (var10 == 2)
        {
            var11 = 180;
        }

        if (var10 == 3)
        {
            var11 = 0;
        }

        if (var10 == 4)
        {
            var11 = 90;
        }

        if (var10 == 5)
        {
            var11 = -90;
        }

        this.bindTextureByName("/mods/" + BierMod.modID + "/textures/blocks/BierFass/bierFass.png");
        GL11.glPushMatrix();
        GL11.glTranslatef((float)var2 + 0.5F, (float)var4 + 1.5F, (float)var6 + 0.5F);
        GL11.glRotatef((float)var11, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        this.bierfass.renderModel(0.0625F);
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8)
    {
        this.renderAModelAt((TileEntityBierFass)var1, var2, var4, var6, var8);
    }
}
