/*
 * -------------------------------------------------------------------------------------------------------
 * Class: PacketManager
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.network;

import it.hurts.metallurgy_reforged.network.client.PacketSetGauntletSlot;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketManager {
//	Creaiamo un "canale" per la mod
	public static final SimpleNetworkWrapper packetReq = NetworkRegistry.INSTANCE.newSimpleChannel("metallurgy");

//	Registriamo i pacchetti che verranno inizializzati nel postInit (Metallurgy)
	public static void init()
	{
		packetReq.registerMessage(PacketSetGauntletSlot.Handler.class, PacketSetGauntletSlot.class, 1, Side.CLIENT);
	}
}
