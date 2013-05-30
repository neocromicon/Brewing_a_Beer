package mods.neocromicon.src.GaerTank;

import cpw.mods.fml.client.FMLClientHandler;
import mods.neocromicon.src.BierModBase.BierMod;
import mods.neocromicon.src.GaerTank.ItemGaerTank$1;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer$ItemRenderType;
import net.minecraftforge.client.IItemRenderer$ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class ItemGaerTank implements IItemRenderer
{
    private ModelGaerTank modelGaerTank = new ModelGaerTank();

    public boolean handleRenderType(ItemStack var1, IItemRenderer$ItemRenderType var2)
    {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer$ItemRenderType var1, ItemStack var2, IItemRenderer$ItemRendererHelper var3)
    {
        return true;
    }

    public void renderItem(IItemRenderer$ItemRenderType var1, ItemStack var2, Object ... var3)
    {
        switch (ItemGaerTank$1.$SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[var1.ordinal()])
        {
            case 1:
                this.renderTutBox(0.0F, 0.0F, 0.0F, -0.55F);
                return;

            case 2:
                this.renderTutBox(0.5F, 1.0F, 1.0F, -0.7F);
                return;

            case 3:
                this.renderTutBox(0.0F, 0.0F, 0.0F, -0.55F);
                return;

            default:
        }
    }

    private void renderTutBox(float var1, float var2, float var3, float var4)
    {
        float var5 = 0.0625F;
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glTranslatef(var1, var2, var3);
        GL11.glScalef(0.55F, var4, var4);
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/" + BierMod.modID + "/textures/blocks/GaerTank/gaerTankAus.png");
        this.modelGaerTank.renderModel(var5);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}
