package it.hurts.metallurgy_5.item;

import net.minecraftforge.oredict.OreDictionary;

/***************************
*
* Author : ItHurtsLikeHell
* Project: Metallurgy-5
* Date   : 28 ago 2018
* Time   : 18:24:07
*
***************************/

public class ItemOre extends ItemBase implements ItemOreDict {

		private String oreName;

		public ItemOre(String name, String oreName) {
			super(name);

			this.oreName = oreName;
		}

		@Override
		public void initOreDict() {
			OreDictionary.registerOre(oreName, this);
		}
	
}
