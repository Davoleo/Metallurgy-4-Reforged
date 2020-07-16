package it.hurts.metallurgy_reforged.handler;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelCubeEtherium extends ModelBase
{
    private ModelRenderer face1;
    private ModelRenderer face2;
    private ModelRenderer face3;
    private ModelRenderer face4;
    private ModelRenderer face5;
    private ModelRenderer face6;

    ModelCubeEtherium()
    {
        this.textureWidth = 16;
        this.textureHeight = 16;
        this.face1 = new ModelRenderer(this, 0, 0);
        this.face1.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.face1.addBox(-8.0F, -8.0F, 8.0F, 16, 16, 0, 0.0F);
        this.face2 = new ModelRenderer(this, 0, 0);
        this.face2.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.face2.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 0, 0.0F);
        this.face3 = new ModelRenderer(this, 0, 0);
        this.face3.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.face3.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 0, 0.0F);
        this.setRotateAngle(face3, 0.0F, 1.5707963267948966F, 0.0F);
        this.face4 = new ModelRenderer(this, 0, 0);
        this.face4.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.face4.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 0, 0.0F);
        this.setRotateAngle(face4, 0.0F, -1.5707963267948966F, 0.0F);
        this.face5 = new ModelRenderer(this, 0, 0);
        this.face5.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.face5.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 0, 0.0F);
        this.setRotateAngle(face5, -1.5707963267948966F, 0.0F, 0.0F);
        this.face6 = new ModelRenderer(this, 0, 0);
        this.face6.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.face6.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 0, 0.0F);
        this.setRotateAngle(face6, 1.5707963267948966F, 0.0F, 0.0F);
    }

    public void render()
    {
        float scale = 1F;

        this.face1.render(scale);
        this.face2.render(scale);
        this.face3.render(scale);
        this.face4.render(scale);
        this.face5.render(scale);
        this.face6.render(scale);
    }

    private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

}
