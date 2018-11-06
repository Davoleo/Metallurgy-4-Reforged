package it.hurts.metallurgy_5.util.handler;

import it.hurts.metallurgy_5.Metallurgy_5;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class CommonTickHandler {

    public CommonTickHandler()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void tick(TickEvent.ClientTickEvent event)
    {
        switch (event.phase)
        {
            case START:
                break;
            case END:
                break;
        }
    }

     @SubscribeEvent
    public void tick(TickEvent.ServerTickEvent event)
     {
         switch (event.phase)
         {
             case START:
                 break;
             case END:
                 Metallurgy_5.ticker++;
         }
     }

}
