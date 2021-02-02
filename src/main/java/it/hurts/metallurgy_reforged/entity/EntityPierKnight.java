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
import it.hurts.metallurgy_reforged.entity.ai.AIPierKnightFollow;
import it.hurts.metallurgy_reforged.entity.ai.AIPierOwnerHurt;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

@SuppressWarnings("Guava")
public class EntityPierKnight extends EntityCreature implements IEntityOwnable {

    protected static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.createKey(EntityPierKnight.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    // ;)))
    private int thickness = 1;
    // TODO: 02/02/2021 Pier Timer before death

    public EntityPierKnight(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 1.8F);
        this.experienceValue = 8;
    }

    public EntityPierKnight(World worldIn, EntityLivingBase owner, EntityLivingBase attacker, int thickness)
    {
        //Call the generic constructor
        this(worldIn);
        this.dataManager.set(OWNER_UNIQUE_ID, Optional.of(owner.getUniqueID()));
        setAttackTarget(attacker);
        this.thickness = thickness;
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(OWNER_UNIQUE_ID, Optional.absent());
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

        this.targetTasks.addTask(1, new AIPierKnightFollow(this));
        this.targetTasks.addTask(2, new AIPierOwnerHurt(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
        super.initEntityAI();
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10 * thickness);
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(@Nonnull DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        setHeldItem(EnumHand.MAIN_HAND, new ItemStack(ModMetals.DAMASCUS_STEEL.getTool(EnumTools.SWORD)));
        return super.onInitialSpawn(difficulty, livingdata);
    }

    @Nonnull
    @Override
    public EnumHandSide getPrimaryHand()
    {
        return EnumHandSide.RIGHT;
    }

    @Override
    protected boolean canBeRidden(@Nonnull Entity entityIn)
    {
        return false;
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
        // TODO: 02/02/2021 remove data about pier from the owner
    }

    @Override
    public void onUpdate()
    {
        // TODO: 02/02/2021 implement timer decreasing here
        super.onUpdate();
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

        thickness = compound.getInteger("PierThickness");
    }

    @Override
    public void writeEntityToNBT(@Nonnull NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);

        if (this.getOwnerId() == null)
            compound.setString("OwnerUUID", "");
        else
            compound.setString("OwnerUUID", this.getOwnerId().toString());

        compound.setInteger("PierThickness", thickness);
    }
}
