package it.hurts.metallurgy_reforged.tileentity;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import it.hurts.metallurgy_reforged.block.BlockCrusher;
import it.hurts.metallurgy_reforged.container.ContainerCrusher;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.recipe.BlockAlloyerRecipes;
import it.hurts.metallurgy_reforged.recipe.BlockCrusherRecipes;
import it.hurts.metallurgy_reforged.recipe.BlockSublimationRecipes;
import it.hurts.metallurgy_reforged.tileentity.TileEntityCrusher.SlotEnum;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public class TileEntityChamber extends TileEntityLockable implements ITickable, ISidedInventory{
	
	private static final int[] slotsTop = SlotEnum.INPUT_SLOT.slots();
    private static final int[] slotsSides = SlotEnum.FUEL_SLOT.slots();
    
    private IItemHandler handlerTop = new SidedInvWrapper(this, EnumFacing.UP);
    private IItemHandler handlerSide = new SidedInvWrapper(this, EnumFacing.WEST);
    private NonNullList<ItemStack> inventory = NonNullList.withSize(5, ItemStack.EMPTY);
    
    private String customName;
    
    private static final int SUBLIMATION_TIME = 71940;
    private int actionTime;
    private int currentActionTime;
    private int sublimationTime;
    private int totalSublimationTime = 72000;
    
    @SuppressWarnings("rawtypes")
	private List playerList;
    private int totalEffectTime = 16 * (20 * 60);
    
    public static void registerFixesFurnace(DataFixer fixer) {
        fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntityFurnace.class, "Items"));
    }
    
    private static int getItemActionTime(ItemStack fuel) {
    	
        if (fuel.isEmpty())
            return 0;
        else {
            Item item = fuel.getItem();

            if (Block.getBlockFromItem(item) == ModMetals.VULCANITE.getBlock())
            	return 72000;
            else
            	return 0;
        }
    }
    
    public static boolean isItemFuel(ItemStack fuel) {
        return getItemActionTime(fuel) > 0;
    }
    
    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else
                return (T) handlerSide;
        return super.getCapability(capability, facing);
    }
    
    @Override
    public int getSizeInventory() {
        return this.inventory.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : this.inventory) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    @Nonnull
    public ItemStack getStackInSlot(int index) {
        return this.inventory.get(index);
    }

    @Override
    @Nonnull
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.inventory, index, count);
    }
    
    @Override
    @Nonnull
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.inventory, index);
    }

    @Override
    public void setInventorySlotContents(int index, @Nonnull ItemStack stack) {
        ItemStack itemstack = this.inventory.get(index);
        boolean flag = stack.isEmpty() || !stack.isItemEqual(itemstack) || !ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.inventory.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }

        if (SlotEnum.INPUT_SLOT.contains(index) && flag) {
            this.totalSublimationTime = this.getSublimationTime(stack);
            this.sublimationTime = 0;
            this.markDirty();
        }
    }
    
    @Override
    public boolean hasCustomName() {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }
    
    @Override
    @Nonnull
    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(this.getName())
                : new TextComponentTranslation(this.getName());
    }
    
    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    public boolean isActive() {
        return this.actionTime > 0;
    }
    
    private int getSublimationTime(ItemStack stack) {
        return SUBLIMATION_TIME;
    }
    
    @Override
    public boolean isUsableByPlayer(@Nonnull EntityPlayer player) {
        if (this.world.getTileEntity(this.pos) != this)
            return false;
        else
            return player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(@Nonnull EntityPlayer player) {
    }

    @Override
    public void closeInventory(@Nonnull EntityPlayer player) {
    }

//  TODO modificare
    @Override
    public boolean isItemValidForSlot(int index, @Nonnull ItemStack stack) {
    	if (SlotEnum.FUEL_SLOT.contains(index)) {
            ItemStack stack1 = inventory.get(index);
            
            return isItemFuel(stack) && stack1.getItem() != Items.BUCKET;
            
        } else if (SlotEnum.INPUT_SLOT.contains(index))
            return BlockSublimationRecipes.getInstance().isSublimationBlock(stack);
           else
            return false;
    }
    
    @Override
    @Nonnull
    public Container createContainer(@Nonnull InventoryPlayer inventory, @Nonnull EntityPlayer player) {
        return new ContainerCrusher(inventory, this);
    }

    @Override
    public int getField(int id) {
        switch (id) {
            case 0:
                return actionTime;
            case 1:
                return currentActionTime;
            case 2:
                return sublimationTime;
            case 3:
                return totalSublimationTime;
            default:
                return 0;
        }
    }
    
    @Override
    public void setField(int id, int value) {
        switch (id) {
            case 0:
            	actionTime = value;
            break;
            
            case 1:
                currentActionTime = value;
            break;
            
            case 2:
                sublimationTime = value;
            break;
            
            case 3:
                totalSublimationTime = value;
            break;
        }
    }
    
    @Override
    public int getFieldCount() {
        return 4;
    }

    @Override
    public void clear() {
        this.inventory.clear();
    }
    
    @Override
    @Nonnull
    public int[] getSlotsForFace(@Nonnull EnumFacing side) {
        return side == EnumFacing.UP ? slotsTop : slotsSides;
    }

    @Override
    public boolean canInsertItem(int index, @Nonnull ItemStack itemStackIn, @Nonnull EnumFacing direction) {
        return isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, @Nonnull ItemStack stack, @Nonnull EnumFacing direction) {
        return false;
    }

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGuiID() {
		return null;
	}
	
	private boolean canSublime() {
		ItemStack input = inventory.get(0);
		Potion result = BlockSublimationRecipes.getInstance().getSublimationResult(input);
		
		BlockPos position = this.getPos();
		double range = 16;
		
		if(input.isEmpty() || result == null) {
			return false;
		} else {
			if(world.isAnyPlayerWithinRangeAt(position.getX(), position.getY(), position.getZ(), range))
				return true;
			return false;
		}
	}
	
	private boolean canAddEffects() {
		BlockPos position = this.getPos();
		double range = 16;
		
		AxisAlignedBB box = new AxisAlignedBB(position.getX() - range, position.getY() -range, position.getZ() -range, 
				position.getX() + range, position.getY() + range, position.getZ() + range);
	     
	     
        playerList = this.world.getEntitiesWithinAABB(EntityPlayer.class, box);
        
         if(!playerList.isEmpty())
        	 return true;
         else
        	 return false;
	}
	

	@Override
	public void update() {
		boolean active = this.isActive();
		boolean flag = false;
		
		if(isActive())
			--sublimationTime;
		
		if (!this.world.isRemote) {
			ItemStack input = this.inventory.get(0);
			ItemStack fuel = new ItemStack (ModMetals.VULCANITE.getBlock(), 3);
			
			if(isActive() || !input.isEmpty() && !(this.inventory.get(0)).isEmpty() && !(this.inventory.get(1).isEmpty())) {
				if(!isActive() && canSublime()) {
					 actionTime = getItemActionTime(fuel);
	                    currentActionTime = actionTime;

	                    if (isActive()) {
	                        flag = true;

	                        if (!fuel.isEmpty()) {
	                            Item item = fuel.getItem();
	                            fuel.shrink(1);

	                            if (fuel.isEmpty()) {
	                                ItemStack item1 = item.getContainerItem(fuel);
	                                this.inventory.set(1, item1);
	                            }
	                        }
	                    }
	                    
	                    if (isActive() && canSublime()) {
	                        ++sublimationTime;

	                        if (sublimationTime == totalSublimationTime) {
	                        	sublimationTime = 0;
	                            totalSublimationTime = getSublimationTime(this.inventory.get(0));
	                            
//	                            Va Modificato assolutamente
	                            if(canAddEffects())
		                            for(Object o : playerList) {
		                            	EntityPlayer p = (EntityPlayer) o;
		                            	
		                            	p.addPotionEffect(new PotionEffect(BlockSublimationRecipes.getInstance().getSublimationResult(input), totalEffectTime, 0, false, false));
		                            }
	                            
	                            if (canSublime())
	                            	input.shrink(1);
	                            
	                            flag = true;
	                        }
	                    } else {
	                    	sublimationTime = 0;
	                    }
				}else if (!isActive() && sublimationTime > 0) {
					 sublimationTime = MathHelper.clamp(sublimationTime - 2, 0, totalSublimationTime);
				}
				
				if (active != isActive()) {
	                flag = true;
	                BlockCrusher.setState(isActive(), this.world, this.pos);
	            }
			}
			
		}
		
		if (flag)
            this.markDirty();
		
	}
	
	public enum SlotEnum {
        INPUT_SLOT(0), FUEL_SLOT(1);

        private final int[] slots;
        private Set<Integer> slotSet;

        SlotEnum(int... slots) {
            this.slots = slots;
            slotSet = Arrays.stream(slots).boxed().collect(Collectors.toSet());
        }

        public int[] slots() {
            return Arrays.copyOf(slots, slots.length);
        }

        public boolean contains(int i) {
            return slotSet.contains(i);
        }
    }

}
