//AVAILABLE FROM 0.2.3 AND ON

import mods.metallurgyreforged.Crusher;
import mods.metallurgyreforged.Alloyer;
import mods.metallurgyreforged.SublimationChamber;  // Available from 0.3.0
//import integration classes you need ^^^



//Adds a new Crusher recipe
//Crusher.addRecipe(IIngrendient input, IItemStack output);
Crusher.addRecipe(<minecraft:planks:*>, <metallurgy:oureclase_dust>);

//Recipe Experience Support
//Crusher.addRecipe(IIngrendient input, IItemStack output, float experience);
Crusher.addRecipe(<metallurgy:manganese_block>, <minecraft:gravel>, 0.5);


//Removes an existing recipe from the Crusher recipes list
//Crusher.removeRecipe(IItemStack output)
Crusher.removeRecipe(<metallurgy:adamantine_dust>);



//Adds a new Alloyer recipe
//Alloyer.addRecipe(IIngrendient input1, IIngredient input2, IItemStack output);
Alloyer.addRecipe(<minecraft:planks:*>, <minecraft:planks:*>, <metallurgy:oureclase_block>);

//Recipe Experience Support
//Alloyer.addRecipe(IIngrendient input1, IIngredient input2, IItemStack output, float experience);
Alloyer.addRecipe(<metallurgy:inolashite_ingot>, <ore:ingotOrichalcum>, <minecraft:bed>, 1.0F);


//Removes an existing recipe from the Alloyer recipes list (the parameter should have the right count for the recipe to be removed correctly)
//Alloyer.removeRecipe(IItemStack output)
Alloyer.removeRecipe(<metallurgy:angmallen_ingot> * 2);
Alloyer.removeRecipe(<metallurgy:tartarite_ingot>);



//AVAILABLE from 0.3.0 and on
//Adds a new Sublimation Recipe to the Sublimation Chamber   -
//SublimationChamber.addRecipe(IItemStack input, IPotion output, int tickDuration, int amplifier);
SublimationChamber.addRecipe(<metallurgy:tar> * 32, <potion:minecraft:slowness>, 300, 1);

//Removes a Sublimation Recipe from the Sublimation Chamber
//SublimationChamber.removeRecipe(IPotion output);
SublimationChamber.removeRecipe(<potion:minecraft:strength>);