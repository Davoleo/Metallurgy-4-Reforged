/*
 * -------------------------------------------------------------------------------------------------------
 * Class: PacketManager
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.network;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.network.server.PacketEditPlayerLevel;
import it.hurts.metallurgy_reforged.network.server.PacketMovePlayer;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketManager {

	//Metallurgy Network Channel
	public static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(Metallurgy.MODID);

	//PostInit: Register Packets
	public static void init()
	{
		//Client2Server
		network.registerMessage(PacketMovePlayer.Handler.class, PacketMovePlayer.class, 2, Side.SERVER);
		network.registerMessage(PacketEditPlayerLevel.Handler.class, PacketEditPlayerLevel.class, 3, Side.SERVER);
	}

}
