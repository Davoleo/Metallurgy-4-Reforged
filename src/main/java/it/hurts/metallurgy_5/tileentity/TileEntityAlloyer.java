package it.hurts.metallurgy_5.tileentity;

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
import net.minecraft.item.*;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-5
 * Date   : 22 set 2018
 * Time   : 11:04:58
 *
 * Reworked by Davoleo
 ***************************/
public class TileEntityAlloyer extends TileEntityLockable implements ITickable {

	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);

	protected boolean hasBeenAlloying;

	private String customName;
	
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
    
    @SuppressWarnings("unchecked")
	@Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.inventory;
        return super.getCapability(capability, facing);
    }
    
    public int getSizeInventory() {
    	
    	return this.inventory.size();
    	
    }

    //Returns true if the inventory is empty
    public boolean isEmpty() {
    	for (ItemStack stack : this.inventory)
    		if(!stack.isEmpty())
    			return false;
    	return true;
    }

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
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.inventory.set(index, stack);

        if(stack.getCount() > this.getInventoryStackLimit()) //Raccogliamo la quantit� e controlliamo lo stack limit
        {
            stack.setCount(this.getInventoryStackLimit());
        }

        //Gathers information about the item you put in
        if(index == 0 || index == 1 && flag)
        {
            this.totalAlloyingTime = this.getAlloyingTime(stack);
            this.alloyingTime = 0;
            this.markDirty();
        }
    }
    
    //Returns the name of the Tile Entity
    public String getName() {
        return this.hasCustomName() ? this.customName : "container.alloyer";
    }
    
    //Returns true if the tile entity has a custom name
    public boolean hasCustomName(){	
        return this.customName != null && !this.customName.isEmpty();
    }
    
    //customName setter
    public void setCustomName(String customName){
        this.customName = customName;
    }
    
    public static void registerFixesFurnace(DataFixer fixer) {
    	fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntityFurnace.class, "Items"));
    }

    //If the name is not custom it trasnlates the name
    //If it is, returns directly the name
    @Override
    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(this.getName())
                : new TextComponentTranslation(this.getName());
    }
    
    //Reads data from the NBT Tag
    @Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound,this.inventory);
        this.burnTime = compound.getInteger("burn_time");
        this.alloyingTime = compound.getInteger("alloying_time");
        this.totalAlloyingTime = compound.getInteger("total_alloying_time");
        this.currentBurnTime = getItemBurnTime(this.inventory.get(1));

        if(compound.hasKey("CustomName", 8)) 
            this.setCustomName(compound.getString("CustomName"));

    }
    
    //Writes data to the NBT Tag
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

    //Returns the Stack Limit as an int
    public int getInventoryStackLimit() {
    	return 64;
    }

    //Returns true if the fuel is burning
    public boolean isBurning() {
    	return this.burnTime > 0;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(TileEntityAlloyer te) {
    	return te.getField(0) > 0 ;
    }
    
    public void update() {
        {
            boolean flag = this.isBurning();
            boolean flag1 = false;

            if (this.isBurning())
            {
                --this.burnTime;
            }

            if (!this.world.isRemote)
            {
                ItemStack itemstack = this.inventory.get(2);

                if (this.isBurning() || !itemstack.isEmpty() && !(this.inventory.get(0)).isEmpty() && !(this.inventory.get(1).isEmpty()))
                {
                    if (!this.isBurning() && this.canAlloy())
                    {
                        this.burnTime = getItemBurnTime(itemstack);
                        this.currentBurnTime = this.burnTime;

                        if (this.isBurning())
                        {
                            flag1 = true;

                            if (!itemstack.isEmpty())
                            {
                                Item item = itemstack.getItem();
                                itemstack.shrink(1);

                                if (itemstack.isEmpty())
                                {
                                    ItemStack item1 = item.getContainerItem(itemstack);
                                    this.inventory.set(2, item1);
                                }
                            }
                        }
                    }

                    if (this.isBurning() && this.canAlloy())
                    {
                        ++this.alloyingTime;

                        if (this.alloyingTime == this.totalAlloyingTime)
                        {
                            this.alloyingTime = 0;
                            this.totalAlloyingTime = this.getAlloyingTime(this.inventory.get(0));
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
    }

    private boolean canAlloy() {

        ItemStack result = BlockAlloyerRecipes.getInstance().getAlloyResult(this.inventory.get(0), this.inventory.get(1));

        if(result.isEmpty() || this.inventory.get(0).getCount() < BlockAlloyerRecipes.getInstance().getItemQuantity(result, this.inventory.get(0)) ||
                this.inventory.get(1).getCount() < BlockAlloyerRecipes.getInstance().getItemQuantity(result, this.inventory.get(1)))
            return false;
        else
        {
            ItemStack output = this.inventory.get(3);
            if(output.isEmpty())
                return true;
            else if (!output.isItemEqual(result) || !ItemStack.areItemStackTagsEqual(output, result))
                return false;

            int res = output.getCount() + result.getCount();
            return res <= getInventoryStackLimit() && res <= output.getMaxStackSize();
        }
    }
    
    public void alloyItem() {

        if(this.canAlloy())
    	{
    	    ItemStack input1 = this.inventory.get(0);
    	    ItemStack input2 = this.inventory.get(1);

    	    ItemStack result = BlockAlloyerRecipes.getInstance().getAlloyResult(input1, input2);
    	    ItemStack output = this.inventory.get(3);

    	    if(output.isEmpty())
    	        this.inventory.set(3, result.copy());
    	    else if (output.isItemEqual(result) && ItemStack.areItemStackTagsEqual(output, result))
    	        output.grow(result.getCount());

    	    input1.shrink(BlockAlloyerRecipes.getInstance().getItemQuantity(result, input1));
    	    input2.shrink(BlockAlloyerRecipes.getInstance().getItemQuantity(result, input2));
        }
    }

    public static int getItemBurnTime(ItemStack stack)
    {
        if (stack.isEmpty())
            return 0;
        else
        {
            int burnTime = net.minecraftforge.event.ForgeEventFactory.getItemBurnTime(stack);
            if (burnTime >= 0) return burnTime;
            Item item = stack.getItem();

            if (item == Item.getItemFromBlock(Blocks.WOODEN_SLAB))
                return 150;
            else if (item == Item.getItemFromBlock(Blocks.WOOL))
                return 100;
            else if (item == Item.getItemFromBlock(Blocks.CARPET))
                return 67;
            else if (item == Item.getItemFromBlock(Blocks.LADDER))
                return 300;
            else if (item == Item.getItemFromBlock(Blocks.WOODEN_BUTTON))
                return 100;
            else if (Block.getBlockFromItem(item).getDefaultState().getMaterial() == Material.WOOD)
                return 300;
            else if (item == Item.getItemFromBlock(Blocks.COAL_BLOCK))
                return 16000;
            else if (item instanceof ItemTool && "WOOD".equals(((ItemTool) item).getToolMaterialName()))
                return 200;
            else if (item instanceof ItemSword && "WOOD".equals(((ItemSword) item).getToolMaterialName()))
                return 200;
            else if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe) item).getMaterialName()))
                return 200;
            else if (item == Items.STICK)
                return 100;
            else if (item != Items.BOW && item != Items.FISHING_ROD)
            {
                if (item == Items.SIGN)
                    return 200;
                else if (item == Items.COAL)
                    return 1600;
                else if (item == Items.LAVA_BUCKET)
                    return 20000;
                else if (item != Item.getItemFromBlock(Blocks.SAPLING) && item != Items.BOWL)
                {
                    if (item == Items.BLAZE_ROD)
                        return 2400;
                    else if (item instanceof ItemDoor && item != Items.IRON_DOOR)
                        return 200;
                    else
                        return item instanceof ItemBoat ? 400 : 0;
                } else
                    return 100;
            } else
                return 300;
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


    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if(index == 3)
            return false;
        else if (index != 2)
            return true;
        else
        {
            ItemStack stack1 = this.inventory.get(2);
            return isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack) && stack1.getItem() != Items.BUCKET;
        }
    }
    
    public String getGuiID()
    {
    	return "minecraft:alloyer";
    }
    
    public Container createContainer(InventoryPlayer inventory, EntityPlayer player)
    {
    	return new ContainerAlloyer(inventory, this);
    }

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