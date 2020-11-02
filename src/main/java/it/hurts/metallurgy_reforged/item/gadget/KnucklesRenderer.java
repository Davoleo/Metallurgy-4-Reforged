package it.hurts.metallurgy_reforged.item.gadget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class KnucklesRenderer extends TileEntityItemStackRenderer
{

    public static ItemCameraTransforms.TransformType type = null;

    @Override
    public void renderByItem(@Nonnull ItemStack itemStackIn)
    {
        ModelBrassKnuckles model = new ModelBrassKnuckles(Minecraft.getMinecraft(), type);
        model.render(itemStackIn);
    }

}
