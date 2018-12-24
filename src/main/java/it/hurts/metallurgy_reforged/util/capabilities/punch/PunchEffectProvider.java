package it.hurts.metallurgy_reforged.util.capabilities.punch;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

/**
 * Mana provider
 *
 * This class is responsible for providing a capability.
 */
public class PunchEffectProvider implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(IPunchEffect.class)
    public static final Capability<IPunchEffect> PUNCH_EFFECT_CAP = null;

    private IPunchEffect instance = PUNCH_EFFECT_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability.equals(PUNCH_EFFECT_CAP);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability.equals(PUNCH_EFFECT_CAP) ? PUNCH_EFFECT_CAP.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return PUNCH_EFFECT_CAP.getStorage().writeNBT(PUNCH_EFFECT_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        PUNCH_EFFECT_CAP.getStorage().readNBT(PUNCH_EFFECT_CAP, this.instance, null, nbt);
    }
}
