package it.hurts.metallurgy_reforged.effect.weapon;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.LivingEventHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public class AmordrineWeaponEffect extends BaseMetallurgyEffect {


	private LivingEventHandler<LivingHurtEvent> ATTACK_MOB = new LivingEventHandler<>(this::onMobAttacked, LivingHurtEvent.class);

	public AmordrineWeaponEffect()
	{
		super(ModMetals.AMORDRINE);
	}

	@Override
	public EnumEffectCategory getCategory()
	{
		return EnumEffectCategory.WEAPON;
	}

	@Override
	public LivingEventHandler<? extends LivingEvent>[] getEvents()
	{
		return new LivingEventHandler[]{ATTACK_MOB};
	}

	/**
	 * in this case of LivingHurtEvent, the attacker should have the armor, not the mob which is attacked
	 */
	@Override
	public EntityLivingBase getEquipUserFromEvent(Event event)
	{
		if (event instanceof LivingHurtEvent)
		{
			LivingHurtEvent hurtEvent = (LivingHurtEvent) event;
			Entity attacker = hurtEvent.getSource().getImmediateSource();
			if (attacker instanceof EntityLivingBase)
			{

				return (EntityLivingBase) attacker;
			}
		}
		return super.getEquipUserFromEvent(event);
	}

	private void onMobAttacked(LivingHurtEvent event)
	{

		float percentage = 1F - event.getEntityLiving().getHealth() / event.getEntityLiving().getMaxHealth();
		float originalAMount = event.getAmount();
		event.setAmount(originalAMount + originalAMount * percentage * 2);

	}

}
