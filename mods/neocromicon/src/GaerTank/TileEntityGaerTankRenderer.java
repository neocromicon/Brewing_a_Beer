package mods.neocromicon.src.GaerTank;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.neocromicon.src.BierModBase.BierMod;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TileEntityGaerTankRenderer extends TileEntitySpecialRenderer
{
    private ModelGaerTank fassModel = new ModelGaerTank();

    public void renderAModelAt(TileEntityGaerTank var1, double var2, double var4, double var6, float var8)
    {
        int var9 = var1.getBlockMetadata();
        boolean var10 = false;

        if (var9 == 2)
        {
            var10 = true;
        }

        if (var9 == 3)
        {
            var10 = false;
        }

        if (var9 == 4)
        {
            var10 = true;
        }

        if (var9 == 5)
        {
            var10 = true;
        }

        int var11 = var1.getStyleGaerTank();
        float var12 = 0.0625F;
        int var13 = var1.blockMetadata;
        this.renderGaerTankGrafik(var11, var12);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)var2 + 0.5F, (float)var4 + 1.5F, (float)var6 + 0.5F);

        if (var13 != 1 && var13 != 3)
        {
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        }
        else
        {
            GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
        }

        GL11.glScalef(1.0F, -1.0F, -1.0F);
        this.fassModel.renderModel(var12);
        GL11.glPopMatrix();
    }

    private void renderGaerTankGrafik(int var1, float var2)
    {
        switch (var1)
        {
            case 0:
                this.bindTextureByName("/mods/" + BierMod.modID + "/textures/blocks/GaerTank/gaerTankAus.png");
                break;

            case 1:
                this.bindTextureByName("/mods/" + BierMod.modID + "/textures/blocks/GaerTank/gaerTankAn.png");
        }
    }

    public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8)
    {
        this.renderAModelAt((TileEntityGaerTank)var1, var2, var4, var6, var8);
    }
}
