package it.hurts.metallurgy_reforged.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class BrassKnucklesTEISR extends TileEntityItemStackRenderer
{

    public static ItemCameraTransforms.TransformType type = null;

    @Override
    public void renderByItem(@Nonnull ItemStack itemStackIn)
    {
        BrassKnucklesModel model = new BrassKnucklesModel(Minecraft.getMinecraft(), type);
        model.render(itemStackIn);
    }

}
