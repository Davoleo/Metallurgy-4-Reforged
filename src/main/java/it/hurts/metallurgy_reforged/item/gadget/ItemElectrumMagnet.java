/*==============================================================================
 = Class: ItemElectrumMagnet
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.item.gadget;

import com.google.common.base.Predicate;
import it.hurts.metallurgy_reforged.item.ItemExtra;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemElectrumMagnet extends ItemExtra {

    private enum Status {
        DISABLED,
        METAL_ONLY,
        ENABLED;

        public static Status byOrdinal(int id) {
            return Status.values()[id];
        }
    }

    private static final int RADIUS = 6;
    private Status status;

    public ItemElectrumMagnet() {
        super("electrum_magnet", MetallurgyTabs.tabSpecial, "gadget");
        this.status = Status.DISABLED;

        this.addPropertyOverride(new ResourceLocation("active"), (stack, worldIn, entityIn) -> {
            if (stack.getTagCompound() != null)
                return stack.getTagCompound().getInteger("status") > 0 ? 1F : 0F;
            else
                return 0F;
        });
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn) {
        if (stack.getTagCompound() != null) {
            Status status = Status.byOrdinal(stack.getTagCompound().getInteger("status"));

            tooltip.add("§eAttracts dropped items on the ground (Has 3 different modes)");
            String statusString = Utils.localizeEscapingCustomSequences("tooltip.metallurgy.electrum_magnet_mode") + " ";

            switch (status) {
                case DISABLED:
                    statusString += Utils.localizeEscapingCustomSequences("tooltip.metallurgy.electrum_magnet_disabled");
                    break;
                case METAL_ONLY:
                    statusString += Utils.localizeEscapingCustomSequences("tooltip.metallurgy.electrum_magnet_metal_only");
                    break;
                case ENABLED:
                    statusString += Utils.localizeEscapingCustomSequences("tooltip.metallurgy.electrum_magnet_enabled");
                    break;
            }

            tooltip.add(statusString);
        }
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return stack.getTagCompound() != null && stack.getTagCompound().getInteger("status") > 1;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        ItemStack magnet = playerIn.getHeldItem(handIn);
        if (playerIn.isSneaking() && magnet.getTagCompound() != null) {
            NBTTagCompound compound = magnet.getTagCompound();
            //If there's no status integer the method returns 0 which is the index of the DISABLED status
            int status = compound.getInteger("status");

            if (status < 2)
                compound.setInteger("status", ++status);
            else {
                status = 0;
                compound.setInteger("status", 0);
            }

            playerIn.world.playSound(null, playerIn.getPosition(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.2F, 0.3F + (status / 2F) * 0.6F);
            magnet.setTagCompound(compound);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @SuppressWarnings("Guava")
    @Override
    public void onUpdate(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull Entity entityIn, int itemSlot, boolean isSelected) {
        if (stack.getTagCompound() == null)
            stack.setTagCompound(new NBTTagCompound());

        Status status = Status.byOrdinal(stack.getTagCompound().getInteger("status"));

        AxisAlignedBB box = new AxisAlignedBB(
                entityIn.posX - RADIUS, entityIn.posY - RADIUS, entityIn.posZ - RADIUS,
                entityIn.posX + RADIUS, entityIn.posY + RADIUS, entityIn.posZ + RADIUS
        );

        if (status.ordinal() > 0) {
            List<EntityItem> entItems;
            Predicate<EntityItem> predicate = null;

            if (status == Status.METAL_ONLY)
                predicate = entityItem -> ItemUtils.getMetalFromItem(entityItem.getItem().getItem()) != null || isMetal(entityItem.getItem());

            entItems = worldIn.getEntitiesWithinAABB(EntityItem.class, box, predicate);

            if (!worldIn.isRemote) {
                entItems.forEach(item -> {
                    //Item : 10 3 10 | 0 3 0
                    //Player : 0 3 0 | -10 3 -10
                    //Vec = -1 0 -1  | 1 0 1
                    Vec3d itemToPlayerVec = item.getPositionVector().subtractReverse(new Vec3d(entityIn.posX, entityIn.posY, entityIn.posZ)).normalize();
                    item.setNoPickupDelay();

                    item.velocityChanged = true;
                    item.motionX = itemToPlayerVec.x * 0.4;
                    item.motionY = itemToPlayerVec.y * 0.4;
                    item.motionZ = itemToPlayerVec.z * 0.4;
                });
            }
        }
    }

    boolean isMetal(@Nonnull ItemStack input)
    {
        if (input.isEmpty())
            return false;

        int[] ids = OreDictionary.getOreIDs(input);

        for (int id : ids)
        {
            String ore = OreDictionary.getOreName(id);
            return ore.contains("ingot") || ore.contains("dust") || ore.contains("ore");
        }

        return false;
    }
}
