/*==============================================================================
 = Class: PacketManager
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.network;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.network.client.*;
import it.hurts.metallurgy_reforged.network.server.PacketAmordrineJump;
import it.hurts.metallurgy_reforged.network.server.PacketKrikEditPlayerLevel;
import it.hurts.metallurgy_reforged.network.server.PacketMovePlayer;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketManager {

	//Metallurgy Network Channel
	public static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(Metallurgy.MODID);
	private static int id = 0;

	//PostInit: Register Packets
	public static void init()
	{
		//Client2Server
		network.registerMessage(PacketMovePlayer.Handler.class, PacketMovePlayer.class, id++, Side.SERVER);
		network.registerMessage(PacketKrikEditPlayerLevel.Handler.class, PacketKrikEditPlayerLevel.class, id++, Side.SERVER);
		network.registerMessage(PacketAmordrineJump.Handler.class, PacketAmordrineJump.class, id++, Side.SERVER);

        //Server2Client
        network.registerMessage(PacketStartStopAmbienceSound.Handler.class, PacketStartStopAmbienceSound.class, id++, Side.CLIENT);
		network.registerMessage(PacketSpawnVanillaParticles.Handler.class, PacketSpawnVanillaParticles.class, id++, Side.CLIENT);
		network.registerMessage(PacketAdamantineRenderDeathProtection.Handler.class, PacketAdamantineRenderDeathProtection.class, id++, Side.CLIENT);
		network.registerMessage(PacketSpawnOreParticles.Handler.class, PacketSpawnOreParticles.class, id++, Side.CLIENT);
		network.registerMessage(PacketAttachEmitter.Handler.class, PacketAttachEmitter.class, id++, Side.CLIENT);
		network.registerMessage(PacketSyncEffectBundle.Handler.class, PacketSyncEffectBundle.class, id++, Side.CLIENT);
		network.registerMessage(PacketSanguiniteEntityState.Handler.class, PacketSanguiniteEntityState.class, id++, Side.CLIENT);
	}

}
