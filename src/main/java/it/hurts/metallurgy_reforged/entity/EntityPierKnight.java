/*==============================================================================
 = Class: EntityPierKnight
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.entity;

import com.google.common.base.Optional;
import com.google.common.collect.Streams;
import it.hurts.metallurgy_reforged.effect.MetallurgyEffects;
import it.hurts.metallurgy_reforged.entity.ai.AIPierKnightFollow;
import it.hurts.metallurgy_reforged.entity.ai.AIPierOwnerAttack;
import it.hurts.metallurgy_reforged.entity.ai.AIPierOwnerWasHurt;
import it.hurts.metallurgy_reforged.item.armor.ItemArmorBase;
import it.hurts.metallurgy_reforged.item.tool.EnumTools;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.network.PacketManager;
import it.hurts.metallurgy_reforged.network.client.PacketSpawnVanillaParticles;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

@SuppressWarnings("Guava")
public class EntityPierKnight extends EntityCreature implements IEntityOwnable {

    private static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.createKey(EntityPierKnight.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    // ;)))
    private static final DataParameter<Byte> THICKNESS = EntityDataManager.createKey(EntityPierKnight.class, DataSerializers.BYTE);
    protected static final DataParameter<Boolean> IS_PUTIN = EntityDataManager.createKey(EntityPierKnight.class, DataSerializers.BOOLEAN);
    private int timeUntilDeath = 20;

    protected int vanishTime = 40;

    public EntityPierKnight(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 1.8F);
        //this.setCustomNameTag("PierKnight");
    }

    public EntityPierKnight(World worldIn, EntityLivingBase owner, EntityLivingBase attacker, byte thickness)
    {
        //Call the generic constructor
        this(worldIn);
        this.dataManager.set(OWNER_UNIQUE_ID, Optional.of(owner.getUniqueID()));
        this.setAttackTarget(attacker);
        this.timeUntilDeath = 20 * 30;
        this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(ModMetals.DAMASCUS_STEEL.getTool(EnumTools.SWORD)));
        setThickness(thickness);

        @SuppressWarnings("UnstableApiUsage")
        boolean isPutin = Streams.stream(owner.getArmorInventoryList())
                .anyMatch(item -> item.getDisplayName().toLowerCase().contains("putin"));
        this.dataManager.set(IS_PUTIN, isPutin);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(OWNER_UNIQUE_ID, Optional.absent());
        this.dataManager.register(THICKNESS, (byte) 1);
        this.dataManager.register(IS_PUTIN, false);
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(6, new AIPierKnightFollow(this));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.tasks.addTask(8, new EntityAIWander(this, 1.0D));

        this.targetTasks.addTask(1, new AIPierOwnerWasHurt(this));
        this.targetTasks.addTask(2, new AIPierOwnerAttack(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(dataManager.get(THICKNESS) * 2);
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(@Nonnull DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        setThickness(dataManager.get(THICKNESS));
        setHeldItem(EnumHand.MAIN_HAND, new ItemStack(ModMetals.DAMASCUS_STEEL.getTool(EnumTools.SWORD)));
        return super.onInitialSpawn(difficulty, livingdata);
    }

    @Override
    public boolean attackEntityAsMob(@Nonnull Entity entityIn)
    {
        float f = (float) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue();
        int i = 0;

        //System.out.println(f);

        if (entityIn instanceof EntityLivingBase)
        {
            f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((EntityLivingBase) entityIn).getCreatureAttribute());
            i += EnchantmentHelper.getKnockbackModifier(this);
        }

        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);

        if (flag)
        {
            if (i > 0)
            {
                ((EntityLivingBase) entityIn).knockBack(this, (float) i * 0.5F, MathHelper.sin(this.rotationYaw * 0.017453292F), -MathHelper.cos(this.rotationYaw * 0.017453292F));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }

            int j = EnchantmentHelper.getFireAspectModifier(this);

            if (j > 0)
            {
                entityIn.setFire(j * 4);
            }

            if (entityIn instanceof EntityPlayer)
            {
                EntityPlayer entityplayer = (EntityPlayer) entityIn;
                ItemStack itemstack = this.getHeldItemMainhand();
                ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : ItemStack.EMPTY;

                if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem().canDisableShield(itemstack, itemstack1, entityplayer, this) && itemstack1.getItem().isShield(itemstack1, entityplayer))
                {
                    float f1 = 0.25F + (float) EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

                    if (this.rand.nextFloat() < f1)
                    {
                        entityplayer.getCooldownTracker().setCooldown(itemstack1.getItem(), 100);
                        this.world.setEntityState(entityplayer, (byte) 30);
                    }
                }
            }

            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }

    @Nonnull
    @Override
    public EnumHandSide getPrimaryHand()
    {
        return EnumHandSide.RIGHT;
    }

    @Nullable
    @Override
    public EntityItem entityDropItem(@Nonnull ItemStack stack, float offsetY)
    {
        return null;
    }

    @Override
    protected boolean canBeRidden(@Nonnull Entity entityIn)
    {
        return false;
    }


    @Override
    protected void onDeathUpdate()
    {
        ++this.deathTime;

        if (world.isRemote)
        {
            playVanishParticle(this, false);
            playVanishParticle(getOwner(), true);
        }

        if (this.deathTime >= 40)
        {
            this.setDead();
        }
    }

    @Override
    public boolean isAIDisabled()
    {
        return this.vanishTime > 0 || super.isAIDisabled();
    }

    @Override
    public boolean canBeLeashedTo(@Nonnull EntityPlayer player)
    {
        return false;
    }

    @Override
    public void onDeath(@Nonnull DamageSource cause)
    {
        super.onDeath(cause);
        EntityLivingBase owner = (EntityLivingBase) getOwner();
        if (owner == null)
            return;

        if (owner.getEntityData().getBoolean("has_pier"))
        {
            owner.getEntityData().setBoolean("has_pier", false);
            //Set the armor on cooldown
            if (owner instanceof EntityPlayer)
                //noinspection ConstantConditions
                for (ItemArmorBase armorItem : ModMetals.DAMASCUS_STEEL.getArmorSet())
                    ((EntityPlayer) owner).getCooldownTracker().setCooldown(armorItem, 200);
        }
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        this.updateArmSwingProgress();

        //If the time is up (it means the council has decided pier should die)
        if (!world.isRemote)
        {
            if (this.vanishTime == 0 && this.getAttackTarget() == null)
                timeUntilDeath--;


            boolean canPierExist = MetallurgyEffects.DAMASCUS_STEEL_ARMOR_EFFECT.canBeApplied((EntityLivingBase) getOwner());
            if (timeUntilDeath <= 0 || !canPierExist)
            {
                this.attackEntityFrom(DamageSource.GENERIC, Integer.MAX_VALUE);
            }
        }

        if (vanishTime > 0)
        {
            if (world.isRemote)
            {
                playVanishParticle(this, true);
                playVanishParticle(getOwner(), false);
            }

            vanishTime--;
        }

    }

    @SideOnly(Side.CLIENT)
    public void playVanishParticle(Entity entity, boolean in)
    {
        if (entity == null)
            return;

        for (int i = 0; i < 8; ++i)
        {
            double particleX = entity.posX + (this.rand.nextDouble() - 0.5D) * (double) entity.width;
            double particleY = entity.posY + this.rand.nextDouble() * (double) entity.height;
            double particleZ = entity.posZ + (this.rand.nextDouble() - 0.5D) * (double) entity.width;

            Vec3d center = entity.getEntityBoundingBox().getCenter();
            Vec3d particleVec = new Vec3d(particleX, particleY, particleZ);

            Vec3d motion;
            if (in)
                motion = center.subtract(particleVec).scale(0.1D);
            else
                motion = particleVec.subtract(center).scale(0.2D);

            PacketSpawnVanillaParticles packet = new PacketSpawnVanillaParticles(EnumParticleTypes.SMOKE_NORMAL.getParticleID(), (float) particleX, (float) particleY, (float) particleZ, (float) motion.x, (float) motion.y, (float) motion.z);

            NetworkRegistry.TargetPoint point = new NetworkRegistry.TargetPoint(world.provider.getDimension(), particleX, particleY, particleZ, 64D);
            PacketManager.network.sendToAllTracking(packet, point);
            //   world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, particleX, particleY, particleZ, motion.x, motion.y, motion.z);
        }
    }


    @Override
    public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn)
    {
        timeUntilDeath = 600;
        super.setAttackTarget(entitylivingbaseIn);
    }

    @Override
    protected boolean canDespawn()
    {
        return false;
    }

    public void setThickness(byte thickness)
    {
        this.dataManager.set(THICKNESS, thickness);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10 * thickness);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2 * thickness);
    }

    @Nullable
    @Override
    public UUID getOwnerId()
    {
        return dataManager.get(OWNER_UNIQUE_ID).orNull();
    }

    @Nullable
    @Override
    public Entity getOwner()
    {
        try
        {
            UUID uuid = this.getOwnerId();
            return uuid == null || world.isRemote ? null : ((WorldServer) this.world).getEntityFromUuid(uuid);
        }
        catch (IllegalArgumentException e)
        {
            return null;
        }
    }

    @Override
    public void readEntityFromNBT(@Nonnull NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        //if something goes wrong with the 8 here, that's because I have no idea why I put it here
        if (compound.hasKey("OwnerUUID", 8))
        {
            Optional<UUID> thing = Optional.of(UUID.fromString(compound.getString("OwnerUUID")));
            dataManager.set(OWNER_UNIQUE_ID, thing);
        }

        setThickness(compound.getByte("PierThickness"));

        if (compound.hasKey("PierLifespan"))
            timeUntilDeath = compound.getInteger("PierLifespan");

        dataManager.set(IS_PUTIN, compound.getBoolean("WidePutin"));

        this.vanishTime = compound.getInteger("VanishTime");
    }

    @Override
    public void writeEntityToNBT(@Nonnull NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);


        if (this.getOwnerId() != null)
            compound.setString("OwnerUUID", this.getOwnerId().toString());

        compound.setByte("PierThickness", this.dataManager.get(THICKNESS));

        compound.setInteger("PierLifespan", timeUntilDeath);

        compound.setBoolean("WidePutin", dataManager.get(IS_PUTIN));

        compound.setInteger("VanishTime", this.vanishTime);
    }

}
