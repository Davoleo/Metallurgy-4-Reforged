/**
 * 
 */
package it.hurts.metallurgy_5.tileentity;

import javax.annotation.Nullable;

import it.hurts.metallurgy_5.block.BlockAlloyer;
import it.hurts.metallurgy_5.container.ContainerAlloyer;
import it.hurts.metallurgy_5.util.recipe.BlockAlloyerRecipes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-5
 * Date   : 22 set 2018
 * Time   : 11:04:58
 *
 ***************************/
public class TileEntityAlloyer extends TileEntityLockable implements ITickable {

//	Creiamo una lista di null (Inventario) di grandezza 3
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
	
	private String customName;   		//CustomName sarebbe la modifica del nome dell'oggetto in caso il player rinominasse il blocco
	private ItemStack alloyer = ItemStack.EMPTY;  
	
	private int burnTime;		
    private int currentBurnTime;
    private int alloyingTime;		
    private int totalAlloyingTime = 200;
    
    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
    {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
        else return false;
    }
    
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.inventory;
        return super.getCapability(capability, facing);
    }
    
    public int getSizeInventory() {
    	
    	return this.inventory.size();
    	
    }
    
//    Controlliamo se l'inventario è vuoto
    public boolean isEmpty() {
    	for (ItemStack stack : this.inventory)
    		if(!stack.isEmpty())
    			return false;
    	return true;
    }
    
//    Crush getStackInSlot
    public ItemStack getStackInSlot(int index){
        return this.inventory.get(index);
    }
    
    public ItemStack decrStackSize(int index, int count){
    	
        return ItemStackHelper.getAndSplit(this.inventory, index, count);
    }

    public ItemStack removeStackFromSlot(int index){
    	
        return ItemStackHelper.getAndRemove(this.inventory, index);
    }
    
    public void setInventorySlotContents(int index, ItemStack stack){
        ItemStack itemstack = this.inventory.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack); //non l'ho capito
        this.inventory.set(index, stack);

        if(stack.getCount() > this.getInventoryStackLimit()) //Raccogliamo la quantità e controlliamo lo stack limit
        {
            stack.setCount(this.getInventoryStackLimit());
        }

        if(index == 0 && flag)	//Non l'ho capito
        {
            this.totalAlloyingTime = this.getAlloyingTime(stack); //Impostiamo il tempo di cottura
            this.alloyingTime = 0;
            this.markDirty();
        }
    }
    
//  Controlla se ha un nome customizzato
    public String getName() {
        return this.hasCustomName() ? this.customName : "container.alloyer";
    }
    
//    non l'ho capito
    public boolean hasCustomName(){	
        return this.customName != null && !this.customName.isEmpty();
    }
    
//    Imposta il nome Personalizzato in caso ci sia
    public void setCustomName(String customName){
        this.customName = customName;
    }
    
    public static void registerFixesFurnace(DataFixer fixer) {
    	fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntityFurnace.class, new String[] {"Items"}));
    }
    
//    In caso ci siano traduzioni(?)
    @Override
    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(this.getName())
                : new TextComponentTranslation(this.getName());
    }
    
//    Metodo per leggere l'NBT
    @Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);	//Non ricordo cosa fosse il compound
        this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound,this.inventory);
        this.burnTime = compound.getInteger("burn_time");	//Otteniamo il tempo di cottura ricevuto dal Fuel
        this.alloyingTime = compound.getInteger("alloying_time");//Impostiamo quanto tempo ci vuole per ottenere un alloy
        this.totalAlloyingTime = compound.getInteger("total_alloying_time"); //Impostiamo quanto è il massimo tempo di cottura
        this.currentBurnTime = getItemBurnTime(this.inventory.get(1)); //Impostiamo su tick, il tempo di cottura

//        In caso ci sia un customName
        if(compound.hasKey("CustomName", 8)) 
            this.setCustomName(compound.getString("CustomName"));

    }
    
//    Scriviamo l'NBT
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound); //Ripeto che non lo ricordo
        compound.setInteger("burn_time", (short)this.burnTime); //Impostiamo il BurnTime ma con meno bit possibili(lo compressiamo)
        compound.setInteger("alloying_time", (short)this.alloyingTime);//Impostiamo il AlloyingTime ma con meno bit possibili(lo compressiamo)
        compound.setInteger("total_alloying_time", (short)this.totalAlloyingTime);//Impostiamo il AlloyingTime totale ma con meno bit possibili(lo compressiamo)
        ItemStackHelper.saveAllItems(compound, this.inventory);

        if(this.hasCustomName())
            compound.setString("custom_name", this.customName);

        return compound;

    }
    
//    Non ha bisogno di spiegazioni
    public int getInventoryStackLimit() {
    	return 64;
    }
    
//    I don't remember
    public boolean isBurning() {
    	return this.burnTime > 0;
    }
    
//    Imposta la freccetta della cottura
    @SideOnly(Side.CLIENT)
    public static boolean isBurning(TileEntityAlloyer te) {
    	return te.getField(0) > 0 ;
    }
    
    public void update() {
    	boolean flag = this.isBurning();
        boolean flag1 = false;

        //Fuel Usage
        if (this.isBurning())
        {
            --this.burnTime;
        }

        if (!this.world.isRemote)
        {

            ItemStack input = inventory.get(0);
            ItemStack input1 = inventory.get(1);
            ItemStack fuel = this.inventory.get(2);

            if (this.isBurning() || !fuel.isEmpty() && !this.inventory.get(0).isEmpty() && !this.inventory.get(1).isEmpty())
            {
                if (!this.isBurning() && this.canLiquefy())
                {
                    this.burnTime = getItemBurnTime(fuel);
                    this.currentBurnTime = burnTime;

                    if (this.isBurning())
                    {
                        flag1 = true;

                        if (!fuel.isEmpty())
                        {
                            Item item = fuel.getItem();
                            fuel.shrink(1);

                            if (fuel.isEmpty())
                            {
                                ItemStack item1 = item.getContainerItem(fuel);
                                this.inventory.set(1, item1);
                            }
                        }
                    }

                }

                if (this.isBurning() && this.canLiquefy())
                {
                    ++alloyingTime;

                    if (alloyingTime == totalAlloyingTime)
                    {
                    	alloyingTime = 0;
                        totalAlloyingTime = this.getAlloyingTime(this.inventory.get(0));
                        this.alloyItem();
                        flag1 = true;
                    }
                } else
                {
                    this.alloyingTime = 0;
                }
            } else if (!this.isBurning() && this.alloyingTime > 0)
            {
                this.alloyingTime = MathHelper.clamp(this.alloyingTime - 2, 0, this.totalAlloyingTime);
            }

            if (flag != this.isBurning())
            {
                flag1 = true;
                BlockAlloyer.setState(this.isBurning(), this.world, this.pos);
            }
        }

        if (flag1)
        {
            this.markDirty();
        }
    }
    
    private boolean canLiquefy() {
    	if((this.inventory.get(0).isEmpty())||(this.inventory.get(1).isEmpty()))
    			return false;
    	else {
    		ItemStack result = BlockAlloyerRecipes.getInstance().getAlloyingResult(this.inventory.get(0), this.inventory.get(1));
    		if(result.isEmpty())
    			return false;
    		else {
    			ItemStack output = this.inventory.get(2);
    			int limit = output.getCount() + result.getCount();
    			
    			if(output.isEmpty())
    				return true;
    			else 
    				if(!output.isItemEqual(result))
    					return false;
    				else
    					if (limit <= this.getInventoryStackLimit() && limit <= output.getMaxStackSize())
    						return true;
    					else
    						return limit <= 64 && limit <= output.getMaxStackSize();
    		}
    	}
    }
    
    public void alloyItem() {
    	if(this.canLiquefy()) {
    		ItemStack input = this.inventory.get(0);
    		ItemStack input1 = this.inventory.get(1);
    		ItemStack recipeResult = BlockAlloyerRecipes.getInstance().getAlloyingResult(input, input1);
    		ItemStack output = this.inventory.get(2);
    		
    		if(output.isEmpty())
    			this.inventory.set(2, recipeResult.copy());
    		else
    			if(output.getItem() == recipeResult.getItem())
    				output.grow(recipeResult.getCount());
    		
    		if(input.getItem() == Item.getItemFromBlock(Blocks.SPONGE) && input.getMetadata() == 1 && !((ItemStack)this.inventory.get(1)).isEmpty() && ((ItemStack)this.inventory.get(1)).getItem() == Items.BUCKET)
    			this.inventory.set(1, new ItemStack(Items.WATER_BUCKET));
    		input.shrink(1);
    	}
    }
    
    public static int getItemBurnTime(ItemStack fuel)
    {
        if(fuel.isEmpty())
            return 0;
        else
        {
            Item item = fuel.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR)
            {
                Block block = Block.getBlockFromItem(item);

                if(block == Blocks.WOODEN_SLAB)
                    return 150;
                if(block.getDefaultState().getMaterial() == Material.WOOD)
                    return 300;
                if(block == Blocks.COAL_BLOCK)
                    return 16000;
            }

            if (item instanceof ItemTool && "WOOD".equals(((ItemTool) item).getToolMaterialName()))
                return 200;
            if (item instanceof ItemSword && "WOOD".equals(((ItemSword) item).getToolMaterialName()))
                return 200;
            if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe) item).getMaterialName()))
                return 200;
            if (item == Items.STICK)
                return 100;
            if (item == Items.COAL)
                return  1600;
            if (item == Items.LAVA_BUCKET)
                return 20000;
            if (item == Item.getItemFromBlock(Blocks.SAPLING))
                return 100;
            if (item == Items.BLAZE_ROD)
                return 2400;

            return ForgeEventFactory.getItemBurnTime(fuel);
        }
    }
    
    public int getAlloyingTime(ItemStack stack) {
    	return 140;
    }
    
    public static boolean isItemFuel(ItemStack fuel){
        return getItemBurnTime(fuel) > 0;
    }
    
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        if(this.world.getTileEntity(this.pos) != this)
            return false;
        else
            return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }

    public void openInventory(EntityPlayer player)
    {}

    public void closeInventory(EntityPlayer player)
    {}

//    Credo vada modificato
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if(index == 2)
            return false;
        else if (index != 1)
            return true;
        else
        {
            ItemStack stack1 = this.inventory.get(1);
            return isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack) && stack1.getItem() != Items.BUCKET;
        }
    }
    
    public String getGuiID() {
    	return "minecraft:alloyer";
    }
    
    public Container createContainer(InventoryPlayer inventory, EntityPlayer player) {
    	return new ContainerAlloyer(inventory, this);
    }
    
//    Non ho capito un cazzo d'ora in poi
    public int getField(int id){
        switch (id){
            case 0:
                return this.burnTime;
            case 1:
                return this.currentBurnTime;
            case 2:
                return this.alloyingTime;
            case 3:
                return this.totalAlloyingTime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value){
        switch (id){
            case 0:
                this.burnTime = value;
                break;
            case 1:
                this.currentBurnTime = value;
                break;
            case 2:
                this.alloyingTime = value;
                break;
            case 3:
                this.totalAlloyingTime = value;
        }
    }

    public int getFieldCount(){
        return 4;
    }
    
    public void clear(){
        this.inventory.clear();
    }
}
