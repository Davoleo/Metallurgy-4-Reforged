/*==============================================================================
 = Class: AIPierOwnerHurt
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.entity.ai;

import it.hurts.metallurgy_reforged.entity.EntityPierKnight;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;

public class AIPierOwnerHurt extends EntityAITarget {

    EntityPierKnight pierKnight;
    EntityLivingBase attacker;
    private int timestamp;

    public AIPierOwnerHurt(EntityPierKnight pier)
    {
        super(pier, false);
        this.pierKnight = pier;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        EntityLivingBase entitylivingbase = (EntityLivingBase) this.pierKnight.getOwner();

        if (entitylivingbase == null)
        {
            return false;
        }
        else
        {
            this.attacker = entitylivingbase.getRevengeTarget();
            int i = entitylivingbase.getRevengeTimer();
            return i != this.timestamp && this.isSuitableTarget(this.attacker, false);
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.pierKnight.setAttackTarget(this.attacker);
        EntityLivingBase entitylivingbase = (EntityLivingBase) this.pierKnight.getOwner();

        if (entitylivingbase != null)
        {
            this.timestamp = entitylivingbase.getRevengeTimer();
        }

        super.startExecuting();
    }

}
