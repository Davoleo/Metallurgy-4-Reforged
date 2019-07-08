import mods.metallurgyreforged.Crusher;
import mods.metallurgyreforged.Alloyer;
//import integration classes you need ^^^



//Adds a new Crusher recipe
//Crusher.addRecipe(IIngrendient input, IItemStack output);
Crusher.addRecipe(<minecraft:planks:*>, <metallurgy:oureclase_dust>);

//Removes an existing recipe from the Crusher recipes list
//Crusher.removeRecipe(IItemStack output)
Crusher.removeRecipe(<metallurgy:adamantine_dust>);



//Adds a new Alloyer recipe
//Alloyer.addRecipe(IIngrendient input1, IIngredient input2, IItemStack output);
Alloyer.addRecipe(<minecraft:planks:*>, <minecraft:planks:*>, <metallurgy:oureclase_block>);

//Removes an existing recipe from the Alloyer recipes list
//Alloyer.removeRecipe(IItemStack output)
Alloyer.removeRecipe(<metallurgy:tartarite_ingot>);