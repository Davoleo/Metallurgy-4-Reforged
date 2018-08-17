package hurts.metallurgy_5.proxy;

import hurts.metallurgy_5.Metallurgy_5;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy{
	//Implementato poiché client side
    //Registrazione dei rendering degli oggetti
    @Override
    public void registerItemRenderer(Item item, int meta, String id){
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Metallurgy_5.MODID + ":" + id, "inventory"));
    }
    
}
