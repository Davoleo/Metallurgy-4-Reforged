package it.hurts.metallurgy_reforged.entity.customMob;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.entity.customMob.leprechaun.EntityLeprechaun;
import it.hurts.metallurgy_reforged.entity.customMob.leprechaun.RenderLeprechaun;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModEntities {

    public static void init() {
        // Every entity in our mod has an ID (local to this mod)
        int id = 1;
//        TODO IDK, help me.
        EntityRegistry.registerModEntity(new ResourceLocation(Metallurgy.MODID,"leprechaun"),
                EntityLeprechaun.class, "Leprechaun", id++, Metallurgy.instance, 64,
                3, true, 0xffffff, 0xffffff);

        // We want our mob to spawn in Plains and ice plains biomes. If you don't add this then it will not spawn automatically
        // but you can of course still make it spawn manually
        EntityRegistry.addSpawn(EntityLeprechaun.class, 100, 3, 5,
                EnumCreatureType.MONSTER, Biomes.PLAINS, Biomes.ICE_PLAINS);

        // This is the loot table for our mob
        LootTableList.register(EntityLeprechaun.LOOT);
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntityLeprechaun.class, RenderLeprechaun.FACTORY);
    }

}
