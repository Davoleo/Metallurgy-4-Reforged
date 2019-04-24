package it.hurts.metallurgy_reforged.integration.mods.tic.trait;

import it.hurts.metallurgy_reforged.config.ToolEffectsConfig;
import it.hurts.metallurgy_reforged.item.tool.ItemSwordBase;
import it.hurts.metallurgy_reforged.item.tool.ModTools;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.tools.SwordCore;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import javax.annotation.Nullable;

public class TraitOpistognathus extends AbstractTrait implements ITrait{

    public TraitOpistognathus()
    {
        super("opistognathus_trait", TextFormatting.DARK_AQUA);
    }

    @Override
    public void miningSpeed(ItemStack tool, PlayerEvent.BreakSpeed event) {
        if(event.getEntity().isInWater())
            event.setNewSpeed(event.getOriginalSpeed() * 3);
        else if(!event.getEntity().isInWater())
            event.setNewSpeed(event.getOriginalSpeed());

        System.out.println(event.getNewSpeed());
        System.out.println(event.getOriginalSpeed());
    }

    @Override
    public void register(String name, @Nullable String tooltip) {
        Utils.localize(String.format(LOC_Name, name));
        if (tooltip != null)
            Utils.localize(String.format(LOC_Name, tooltip));
    }

//    @Override
//    public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
//        boolean isActived = false;
//        if(entity instanceof EntityPlayer){
//            EntityPlayer player = (EntityPlayer) entity;
//            IAttributeInstance attackSpeedInstance = player.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED);
//
//            if(tool.getItem() instanceof SwordCore && isSelected && player.isInWater() && attackSpeedInstance.getAttributeValue() != attackSpeedInstance.getBaseValue() * 2 && !isActived){
//                tool.addAttributeModifier("generic.attackSpeed",
//                        new AttributeModifier("generic.attackSpeed", attackSpeedInstance.getBaseValue() * 2, 0),
//                        EntityEquipmentSlot.MAINHAND);
//                isActived = true;
//            }else{
//                if(!player.isInWater() && attackSpeedInstance.getBaseValue() == attackSpeedInstance.getBaseValue() * 2){
//                    tool.addAttributeModifier("generic.attackSpeed",
//                            new AttributeModifier("generic.attackSpeed", attackSpeedInstance.getBaseValue(), 0),
//                            EntityEquipmentSlot.MAINHAND);
//                }
//            }
//
//            System.out.println("AttributeBaseValue " + attackSpeedInstance.getBaseValue());
//            System.out.println("AttributeValue " + attackSpeedInstance.getAttributeValue());
//        }
//    }
}
