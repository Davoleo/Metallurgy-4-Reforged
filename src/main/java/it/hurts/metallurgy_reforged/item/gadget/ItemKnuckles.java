package it.hurts.metallurgy_reforged.item.gadget;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import it.hurts.metallurgy_reforged.item.ItemExtra;
import it.hurts.metallurgy_reforged.render.BrassKnucklesTEISR;
import it.hurts.metallurgy_reforged.util.Constants;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemKnuckles extends ItemExtra {

    public ItemKnuckles(String name) {
        super(name, MetallurgyTabs.tabSpecial, "gadget");

        setMaxStackSize(1);
        setMaxDamage(250);
        this.setTileEntityItemStackRenderer(new BrassKnucklesTEISR());
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(@Nonnull ItemStack stack)
    {
        return super.hasEffect(stack) || stack.hasTagCompound() && stack.getTagCompound().getBoolean("hasEffect");
    }

    @Override
    public boolean canApplyAtEnchantingTable(@Nonnull ItemStack stack, @Nonnull Enchantment enchantment)
    {
        List<Enchantment> list = Lists.newArrayList(Constants.GAUNTLET_ENCHANTMENTS);

        return list.contains(enchantment);
    }

    @Nonnull
    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(@Nonnull EntityEquipmentSlot slot, @Nonnull ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);

        if (slot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Knuckles Damage modifier", 1D, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Knuckles Speed modifier", 16D, 0));
        }
        return multimap;
    }

    @Override
    public boolean hitEntity(@Nonnull ItemStack stack, @Nonnull EntityLivingBase target, @Nonnull EntityLivingBase attacker)
    {
        target.motionX *= 0.25;
        target.motionZ *= 0.25;
        target.hurtTime = 10;
        target.hurtResistantTime = 13;
        stack.damageItem(1, attacker);
        return true;
    }
}
