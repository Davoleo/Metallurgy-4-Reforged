package it.hurts.metallurgy_reforged.entity.customMob.leprechaun;

import net.minecraft.entity.ai.EntityAIAttackMelee;

public class EntityAILeprechaunAttack extends EntityAIAttackMelee {
    private int raiseArmTicks;
    private EntityLeprechaun leprechaun;

    public EntityAILeprechaunAttack(EntityLeprechaun creature, double speedIn, boolean useLongMemory) {
        super(creature, speedIn, useLongMemory);
        leprechaun = creature;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        super.startExecuting();
        this.raiseArmTicks = 0;
    }

    /**
     * Resets the task
     */
    @Override
    public void resetTask() {
        super.resetTask();
        this.leprechaun.setArmsRaised(false);
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask() {
        super.updateTask();
        ++this.raiseArmTicks;

        if (this.raiseArmTicks >= 5 && this.attackTick < 10) {
            this.leprechaun.setArmsRaised(true);
        } else {
            this.leprechaun.setArmsRaised(false);
        }
    }
}
