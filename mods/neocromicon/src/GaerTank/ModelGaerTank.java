package mods.neocromicon.src.GaerTank;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGaerTank extends ModelBase
{
    ModelRenderer Unten;
    ModelRenderer Mitte;
    ModelRenderer ScheibeUnten;
    ModelRenderer ScheibeUnten1;
    ModelRenderer ScheibeUnten2;
    ModelRenderer ScheibeUnten3;
    ModelRenderer ScheibeUnten4;
    ModelRenderer ScheibeUnten5;
    ModelRenderer ScheibeOben;
    ModelRenderer Stelze1;
    ModelRenderer Stelze2;
    ModelRenderer Stelze3;
    ModelRenderer Stelze4;
    ModelRenderer Zulauf1;
    ModelRenderer Zulauf2;
    ModelRenderer Zulauf3;
    ModelRenderer Zulauf4;

    public ModelGaerTank()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.Unten = new ModelRenderer(this, 79, 32);
        this.Unten.addBox(0.0F, 0.0F, 0.0F, 12, 16, 12);
        this.Unten.setRotationPoint(-6.0F, 1.0F, -6.0F);
        this.Unten.setTextureSize(128, 64);
        this.Unten.mirror = true;
        this.setRotation(this.Unten, 0.0F, 0.0F, 0.0F);
        this.Mitte = new ModelRenderer(this, 79, 0);
        this.Mitte.addBox(0.0F, 0.0F, 0.0F, 12, 16, 12);
        this.Mitte.setRotationPoint(-6.0F, -15.0F, -6.0F);
        this.Mitte.setTextureSize(128, 64);
        this.Mitte.mirror = true;
        this.setRotation(this.Mitte, 0.0F, 0.0F, 0.0F);
        this.ScheibeUnten = new ModelRenderer(this, 38, 35);
        this.ScheibeUnten.addBox(0.0F, 0.0F, 0.0F, 10, 1, 10);
        this.ScheibeUnten.setRotationPoint(-5.0F, 17.0F, -5.0F);
        this.ScheibeUnten.setTextureSize(128, 64);
        this.ScheibeUnten.mirror = true;
        this.setRotation(this.ScheibeUnten, 0.0F, 0.0F, 0.0F);
        this.ScheibeUnten1 = new ModelRenderer(this, 46, 25);
        this.ScheibeUnten1.addBox(0.0F, 0.0F, 0.0F, 8, 1, 8);
        this.ScheibeUnten1.setRotationPoint(-4.0F, 18.0F, -4.0F);
        this.ScheibeUnten1.setTextureSize(128, 64);
        this.ScheibeUnten1.mirror = true;
        this.setRotation(this.ScheibeUnten1, 0.0F, 0.0F, 0.0F);
        this.ScheibeUnten2 = new ModelRenderer(this, 54, 17);
        this.ScheibeUnten2.addBox(0.0F, 0.0F, 0.0F, 6, 1, 6);
        this.ScheibeUnten2.setRotationPoint(-3.0F, 19.0F, -3.0F);
        this.ScheibeUnten2.setTextureSize(128, 64);
        this.ScheibeUnten2.mirror = true;
        this.setRotation(this.ScheibeUnten2, 0.0F, 0.0F, 0.0F);
        this.ScheibeUnten3 = new ModelRenderer(this, 61, 11);
        this.ScheibeUnten3.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
        this.ScheibeUnten3.setRotationPoint(-2.0F, 20.0F, -2.0F);
        this.ScheibeUnten3.setTextureSize(128, 64);
        this.ScheibeUnten3.mirror = true;
        this.setRotation(this.ScheibeUnten3, 0.0F, 0.0F, 0.0F);
        this.ScheibeUnten4 = new ModelRenderer(this, 69, 7);
        this.ScheibeUnten4.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2);
        this.ScheibeUnten4.setRotationPoint(-1.0F, 21.0F, -1.0F);
        this.ScheibeUnten4.setTextureSize(128, 64);
        this.ScheibeUnten4.mirror = true;
        this.setRotation(this.ScheibeUnten4, 0.0F, 0.0F, 0.0F);
        this.ScheibeUnten5 = new ModelRenderer(this, 69, 3);
        this.ScheibeUnten5.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2);
        this.ScheibeUnten5.setRotationPoint(-1.0F, 22.0F, -1.0F);
        this.ScheibeUnten5.setTextureSize(128, 64);
        this.ScheibeUnten5.mirror = true;
        this.setRotation(this.ScheibeUnten5, 0.0F, 0.0F, 0.0F);
        this.ScheibeOben = new ModelRenderer(this, 38, 47);
        this.ScheibeOben.addBox(0.0F, 0.0F, 0.0F, 10, 3, 10);
        this.ScheibeOben.setRotationPoint(-5.0F, -18.0F, -5.0F);
        this.ScheibeOben.setTextureSize(128, 64);
        this.ScheibeOben.mirror = true;
        this.setRotation(this.ScheibeOben, 0.0F, 0.0F, 0.0F);
        this.Stelze1 = new ModelRenderer(this, 0, 55);
        this.Stelze1.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1);
        this.Stelze1.setRotationPoint(5.0F, 17.0F, -6.0F);
        this.Stelze1.setTextureSize(128, 64);
        this.Stelze1.mirror = true;
        this.setRotation(this.Stelze1, 0.0F, 0.0F, 0.0F);
        this.Stelze2 = new ModelRenderer(this, 5, 55);
        this.Stelze2.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1);
        this.Stelze2.setRotationPoint(5.0F, 17.0F, 5.0F);
        this.Stelze2.setTextureSize(128, 64);
        this.Stelze2.mirror = true;
        this.setRotation(this.Stelze2, 0.0F, 0.0F, 0.0F);
        this.Stelze3 = new ModelRenderer(this, 10, 55);
        this.Stelze3.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1);
        this.Stelze3.setRotationPoint(-6.0F, 17.0F, -6.0F);
        this.Stelze3.setTextureSize(128, 64);
        this.Stelze3.mirror = true;
        this.setRotation(this.Stelze3, 0.0F, 0.0F, 0.0F);
        this.Stelze4 = new ModelRenderer(this, 15, 55);
        this.Stelze4.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1);
        this.Stelze4.setRotationPoint(-6.0F, 17.0F, 5.0F);
        this.Stelze4.setTextureSize(128, 64);
        this.Stelze4.mirror = true;
        this.setRotation(this.Stelze4, 0.0F, 0.0F, 0.0F);
        this.Zulauf1 = new ModelRenderer(this, 5, 23);
        this.Zulauf1.addBox(0.0F, 0.0F, 0.0F, 2, 30, 1);
        this.Zulauf1.setRotationPoint(-1.0F, -17.0F, 7.0F);
        this.Zulauf1.setTextureSize(128, 64);
        this.Zulauf1.mirror = true;
        this.setRotation(this.Zulauf1, 0.0F, 0.0F, 0.0F);
        this.Zulauf2 = new ModelRenderer(this, 0, 43);
        this.Zulauf2.addBox(0.0F, 0.0F, 0.0F, 2, 10, 1);
        this.Zulauf2.setRotationPoint(-1.0F, 13.0F, 7.0F);
        this.Zulauf2.setTextureSize(128, 64);
        this.Zulauf2.mirror = true;
        this.setRotation(this.Zulauf2, 0.0F, 0.0F, 0.0F);
        this.Zulauf3 = new ModelRenderer(this, 0, 0);
        this.Zulauf3.addBox(0.0F, 0.0F, 0.0F, 2, 1, 6);
        this.Zulauf3.setRotationPoint(-1.0F, 22.0F, 1.0F);
        this.Zulauf3.setTextureSize(128, 64);
        this.Zulauf3.mirror = true;
        this.setRotation(this.Zulauf3, 0.0F, 0.0F, 0.0F);
        this.Zulauf4 = new ModelRenderer(this, 0, 8);
        this.Zulauf4.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2);
        this.Zulauf4.setRotationPoint(-1.0F, -17.0F, 5.0F);
        this.Zulauf4.setTextureSize(128, 64);
        this.Zulauf4.mirror = true;
        this.setRotation(this.Zulauf4, 0.0F, 0.0F, 0.0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        super.render(var1, var2, var3, var4, var5, var6, var7);
        this.setRotationAngles(var2, var3, var4, var5, var6, var7);
        this.Unten.render(var7);
        this.Mitte.render(var7);
        this.ScheibeUnten.render(var7);
        this.ScheibeUnten1.render(var7);
        this.ScheibeUnten2.render(var7);
        this.ScheibeUnten3.render(var7);
        this.ScheibeUnten4.render(var7);
        this.ScheibeUnten5.render(var7);
        this.ScheibeOben.render(var7);
        this.Stelze1.render(var7);
        this.Stelze2.render(var7);
        this.Stelze3.render(var7);
        this.Stelze4.render(var7);
        this.Zulauf1.render(var7);
        this.Zulauf2.render(var7);
        this.Zulauf3.render(var7);
        this.Zulauf4.render(var7);
    }

    public void renderModel(float var1)
    {
        this.Unten.render(var1);
        this.Mitte.render(var1);
        this.ScheibeUnten.render(var1);
        this.ScheibeUnten1.render(var1);
        this.ScheibeUnten2.render(var1);
        this.ScheibeUnten3.render(var1);
        this.ScheibeUnten4.render(var1);
        this.ScheibeUnten5.render(var1);
        this.ScheibeOben.render(var1);
        this.Stelze1.render(var1);
        this.Stelze2.render(var1);
        this.Stelze3.render(var1);
        this.Stelze4.render(var1);
        this.Zulauf1.render(var1);
        this.Zulauf2.render(var1);
        this.Zulauf3.render(var1);
        this.Zulauf4.render(var1);
    }

    private void setRotation(ModelRenderer var1, float var2, float var3, float var4)
    {
        var1.rotateAngleX = var2;
        var1.rotateAngleY = var3;
        var1.rotateAngleZ = var4;
    }

    public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6)
    {
        super.setRotationAngles(var1, var2, var3, var4, var5, var6, (Entity)null);
    }
}
