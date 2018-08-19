package hurts.metallurgy_5.util;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class EventHandler {

	
	
	@SubscribeEvent
	public static void checkEntity(PlayerTickEvent event) {		
		int radius=16;
		
//		Vec3d pos, min, max;
		
//		pos = new Vec3d(event.player.posX, event.player.posY, event.player.posZ); 
//		min = pos.subtract(radius, radius, radius);
//		max = pos.addVector(radius, radius, radius);
		
		double xM = event.player.posX + radius, yM = event.player.posY + radius, zM = event.player.posZ + radius;
		double xm = event.player.posX - radius, ym = event.player.posY - radius, zm = event.player.posZ - radius;
		
		List <Entity> list;
		
		list = event.player.getEntityWorld().getEntitiesWithinAABBExcludingEntity(event.player,new AxisAlignedBB(xM, yM, zM, xm, ym, zm));
		
		Entity a[] = new Entity [list.size()];
		for(int i=0; i<list.size();i++) {
			list.toArray(a);
			a[i].setGlowing(true);
		}

	}
	
	
}
