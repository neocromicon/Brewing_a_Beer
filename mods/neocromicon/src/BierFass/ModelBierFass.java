package mods.neocromicon.src.BierFass;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBierFass extends ModelBase
{
    ModelRenderer KleinRechtsUnten;
    ModelRenderer BackUnten;
    ModelRenderer BackMitte;
    ModelRenderer GrossLinks;
    ModelRenderer KleinRechtsOben;
    ModelRenderer KleinLinksUnten;
    ModelRenderer KleinLinksOben;
    ModelRenderer GrossUnten;
    ModelRenderer GrossRechts;
    ModelRenderer FrontMitte;
    ModelRenderer GrossOben;
    ModelRenderer BackOben;
    ModelRenderer FrontUnten;
    ModelRenderer FrontOben;

    public ModelBierFass()
    {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.KleinRechtsUnten = new ModelRenderer(this, 42, 0);
        this.KleinRechtsUnten.addBox(0.0F, 0.0F, 0.0F, 2, 2, 16);
        this.KleinRechtsUnten.setRotationPoint(4.0F, 20.0F, -8.0F);
        this.KleinRechtsUnten.setTextureSize(128, 64);
        this.KleinRechtsUnten.mirror = true;
        this.setRotation(this.KleinRechtsUnten, 0.0F, 0.0F, 0.0F);
        this.BackUnten = new ModelRenderer(this, 0, 4);
        this.BackUnten.addBox(0.0F, 0.0F, 0.0F, 8, 2, 1);
        this.BackUnten.setRotationPoint(-4.0F, 20.0F, 6.0F);
        this.BackUnten.setTextureSize(128, 64);
        this.BackUnten.mirror = true;
        this.setRotation(this.BackUnten, 0.0F, 0.0F, 0.0F);
        this.BackMitte = new ModelRenderer(this, 0, 9);
        this.BackMitte.addBox(0.0F, 0.0F, 0.0F, 12, 6, 1);
        this.BackMitte.setRotationPoint(-6.0F, 14.0F, 6.0F);
        this.BackMitte.setTextureSize(128, 64);
        this.BackMitte.mirror = true;
        this.setRotation(this.BackMitte, 0.0F, 0.0F, 0.0F);
        this.GrossLinks = new ModelRenderer(this, 54, 38);
        this.GrossLinks.addBox(0.0F, 0.0F, 0.0F, 2, 6, 16);
        this.GrossLinks.setRotationPoint(-8.0F, 14.0F, -8.0F);
        this.GrossLinks.setTextureSize(128, 64);
        this.GrossLinks.mirror = true;
        this.setRotation(this.GrossLinks, 0.0F, 0.0F, 0.0F);
        this.KleinRechtsOben = new ModelRenderer(this, 91, 19);
        this.KleinRechtsOben.addBox(0.0F, 0.0F, 0.0F, 2, 2, 16);
        this.KleinRechtsOben.setRotationPoint(4.0F, 12.0F, -8.0F);
        this.KleinRechtsOben.setTextureSize(128, 64);
        this.KleinRechtsOben.mirror = true;
        this.setRotation(this.KleinRechtsOben, 0.0F, 0.0F, 0.0F);
        this.KleinLinksUnten = new ModelRenderer(this, 0, 25);
        this.KleinLinksUnten.addBox(0.0F, 0.0F, 0.0F, 2, 2, 16);
        this.KleinLinksUnten.setRotationPoint(-6.0F, 20.0F, -8.0F);
        this.KleinLinksUnten.setTextureSize(128, 64);
        this.KleinLinksUnten.mirror = true;
        this.setRotation(this.KleinLinksUnten, 0.0F, 0.0F, 0.0F);
        this.KleinLinksOben = new ModelRenderer(this, 54, 19);
        this.KleinLinksOben.addBox(-2.0F, 0.0F, 0.0F, 2, 2, 16);
        this.KleinLinksOben.setRotationPoint(-4.0F, 12.0F, -8.0F);
        this.KleinLinksOben.setTextureSize(128, 64);
        this.KleinLinksOben.mirror = true;
        this.setRotation(this.KleinLinksOben, 0.0F, 0.0F, 0.0F);
        this.GrossUnten = new ModelRenderer(this, 0, 44);
        this.GrossUnten.addBox(0.0F, 0.0F, 0.0F, 8, 2, 16);
        this.GrossUnten.setRotationPoint(-4.0F, 22.0F, -8.0F);
        this.GrossUnten.setTextureSize(128, 64);
        this.GrossUnten.mirror = true;
        this.setRotation(this.GrossUnten, 0.0F, 0.0F, 0.0F);
        this.GrossRechts = new ModelRenderer(this, 91, 38);
        this.GrossRechts.addBox(0.0F, 0.0F, 0.0F, 2, 6, 16);
        this.GrossRechts.setRotationPoint(6.0F, 14.0F, -8.0F);
        this.GrossRechts.setTextureSize(128, 64);
        this.GrossRechts.mirror = true;
        this.setRotation(this.GrossRechts, 0.0F, 0.0F, 0.0F);
        this.FrontMitte = new ModelRenderer(this, 0, 17);
        this.FrontMitte.addBox(0.0F, 0.0F, 0.0F, 12, 6, 1);
        this.FrontMitte.setRotationPoint(-6.0F, 14.0F, -7.0F);
        this.FrontMitte.setTextureSize(128, 64);
        this.FrontMitte.mirror = true;
        this.setRotation(this.FrontMitte, 0.0F, 0.0F, 0.0F);
        this.GrossOben = new ModelRenderer(this, 79, 0);
        this.GrossOben.addBox(0.0F, 0.0F, 0.0F, 8, 2, 16);
        this.GrossOben.setRotationPoint(-4.0F, 10.0F, -8.0F);
        this.GrossOben.setTextureSize(128, 64);
        this.GrossOben.mirror = true;
        this.setRotation(this.GrossOben, 0.0F, 0.0F, 0.0F);
        this.BackOben = new ModelRenderer(this, 0, 0);
        this.BackOben.addBox(0.0F, 0.0F, 0.0F, 8, 2, 1);
        this.BackOben.setRotationPoint(-4.0F, 12.0F, 6.0F);
        this.BackOben.setTextureSize(128, 64);
        this.BackOben.mirror = true;
        this.setRotation(this.BackOben, 0.0F, 0.0F, 0.0F);
        this.FrontUnten = new ModelRenderer(this, 23, 4);
        this.FrontUnten.addBox(0.0F, 0.0F, 0.0F, 8, 2, 1);
        this.FrontUnten.setRotationPoint(-4.0F, 20.0F, -7.0F);
        this.FrontUnten.setTextureSize(128, 64);
        this.FrontUnten.mirror = true;
        this.setRotation(this.FrontUnten, 0.0F, 0.0F, 0.0F);
        this.FrontOben = new ModelRenderer(this, 23, 0);
        this.FrontOben.addBox(0.0F, 0.0F, 0.0F, 8, 2, 1);
        this.FrontOben.setRotationPoint(-4.0F, 12.0F, -7.0F);
        this.FrontOben.setTextureSize(128, 64);
        this.FrontOben.mirror = true;
        this.setRotation(this.FrontOben, 0.0F, 0.0F, 0.0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        super.render(var1, var2, var3, var4, var5, var6, var7);
        this.setRotationAngles(var2, var3, var4, var5, var6, var7);
        this.KleinRechtsUnten.render(var7);
        this.BackUnten.render(var7);
        this.BackMitte.render(var7);
        this.GrossLinks.render(var7);
        this.KleinRechtsOben.render(var7);
        this.KleinLinksUnten.render(var7);
        this.KleinLinksOben.render(var7);
        this.GrossUnten.render(var7);
        this.GrossRechts.render(var7);
        this.FrontMitte.render(var7);
        this.GrossOben.render(var7);
        this.BackOben.render(var7);
        this.FrontUnten.render(var7);
        this.FrontOben.render(var7);
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

    public void renderModel(float var1)
    {
        this.KleinRechtsUnten.render(var1);
        this.BackUnten.render(var1);
        this.BackMitte.render(var1);
        this.GrossLinks.render(var1);
        this.KleinRechtsOben.render(var1);
        this.KleinLinksUnten.render(var1);
        this.KleinLinksOben.render(var1);
        this.GrossUnten.render(var1);
        this.GrossRechts.render(var1);
        this.FrontMitte.render(var1);
        this.GrossOben.render(var1);
        this.BackOben.render(var1);
        this.FrontUnten.render(var1);
        this.FrontOben.render(var1);
    }
}
