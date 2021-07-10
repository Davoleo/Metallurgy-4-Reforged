/*==============================================================================
 = Class: TileEntityChamber
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.tileentity;

import com.google.common.collect.Lists;
import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.block.BlockChamber;
import it.hurts.metallurgy_reforged.container.ContainerNull;
import it.hurts.metallurgy_reforged.network.PacketManager;
import it.hurts.metallurgy_reforged.network.client.PacketStartStopAmbienceSound;
import it.hurts.metallurgy_reforged.recipe.SublimationRecipes;
import it.hurts.metallurgy_reforged.sound.ModSounds;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class TileEntityChamber extends TileEntityLockable implements ITickable, ISidedInventory {

	public static final int METAL_SLOT = 0;
	public static final int FUEL_SLOT = 1;

	private static final int[] SLOTS_TOP = new int[]{METAL_SLOT};
	private static final int[] SLOTS_BOTTOM = new int[]{FUEL_SLOT};
	private static final int[] SLOTS_SIDES = new int[]{FUEL_SLOT};

	private final IItemHandler handlerTop = new SidedInvWrapper(this, EnumFacing.UP);
	private final IItemHandler handlerBottom = new SidedInvWrapper(this, EnumFacing.DOWN);
	private final IItemHandler handlerSide = new SidedInvWrapper(this, EnumFacing.WEST);

	private NonNullList<ItemStack> inventory = NonNullList.withSize(2, ItemStack.EMPTY);

	private String chamberCustomName;

	public int fuelTime = 0;
	public int activeTime = 0;
	public PotionEffect potionEffect = null;

	public List<UUID> affectedPlayers = Lists.newArrayList();

	@Override
	public int getSizeInventory()
	{
		return this.inventory.size();
	}

	@Override
	public boolean isEmpty()
	{
		for (ItemStack stack : inventory)
			if (!stack.isEmpty())
				return false;
		return true;
	}

	@Nonnull
	@Override
	public ItemStack getStackInSlot(int index)
	{
		return this.inventory.get(index);
	}

	@Nonnull
	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		return ItemStackHelper.getAndSplit(this.inventory, index, count);
	}

	@Nonnull
	@Override
	public ItemStack removeStackFromSlot(int index)
	{
		return ItemStackHelper.getAndRemove(this.inventory, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		ItemStack itemstack = this.inventory.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.inventory.set(index, stack);

		if (stack.getCount() > this.getInventoryStackLimit())
		{
			stack.setCount(this.getInventoryStackLimit());
		}

		if (index == METAL_SLOT && !flag)
			this.markDirty();
	}

	public void setPotionEffect(PotionEffect effect)
	{
		this.potionEffect = effect;
		this.updateBlock();
	}

	public void updateBlock()
	{
		IBlockState state = world.getBlockState(pos);
		world.notifyBlockUpdate(pos, state, state, 4);
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(@Nonnull EntityPlayer player)
	{
		return this.world.getTileEntity(this.pos) == this;
	}

	@Override
	public void openInventory(@Nonnull EntityPlayer player)
	{
		//No-Gui
	}

	@Override
	public void closeInventory(@Nonnull EntityPlayer player)
	{
		//No-Gui
	}

	@Override
	public boolean isItemValidForSlot(int index, @Nonnull ItemStack stack)
	{
		if (index == FUEL_SLOT)
		{
			return TileEntityFurnace.isItemFuel(stack);
		}
		else
		{
			ItemStack itemstack = this.inventory.get(METAL_SLOT);
			SublimationRecipes recipes = SublimationRecipes.getInstance();
			int recipeAmount = recipes.getSublimationBlockAmount(stack);
			return itemstack.getCount() < recipeAmount;
		}
	}

	@Override
	public int getField(int id)
	{
		return 0;
	}

	@Override
	public void setField(int id, int value)
	{
		//We don't need it (For now)
	}

	@Override
	public int getFieldCount()
	{
		return 0;
	}

	@Override
	public void clear()
	{
		this.inventory.clear();
	}

	@Nonnull
	@Override
	public String getName()
	{
		return this.hasCustomName() ? this.chamberCustomName : "container.chamber";
	}

	@Override
	public boolean hasCustomName()
	{
		return this.chamberCustomName != null && !this.chamberCustomName.isEmpty();
	}

	//	public void setChamberCustomName(String chamberCustomName)
	//	{
	//		this.chamberCustomName = chamberCustomName;
	//	}

	@Nonnull
	@Override
	public Container createContainer(@Nonnull InventoryPlayer playerInventory, @Nonnull EntityPlayer playerIn)
	{
		return new ContainerNull();
	}

	@Nonnull
	@Override
	public String getGuiID()
	{
		return Metallurgy.MODID + ":chamber";
	}

	@Nonnull
	@Override
	public int[] getSlotsForFace(@Nonnull EnumFacing side)
	{
		if (side == EnumFacing.DOWN)
		{
			return SLOTS_BOTTOM;
		}
		else
		{
			return side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES;
		}
	}

	@Override
	public boolean canInsertItem(int index, @Nonnull ItemStack itemStackIn, @Nonnull EnumFacing direction)
	{
		return this.isItemValidForSlot(index, itemStackIn);
	}

	@Override
	public boolean canExtractItem(int index, @Nonnull ItemStack stack, @Nonnull EnumFacing direction)
	{
		return index == FUEL_SLOT;
	}

	@Override
	public void update()
	{
		ItemStack METAL_STACK = getStackInSlot(METAL_SLOT);
		ItemStack FUEL_STACK = getStackInSlot(FUEL_SLOT);

		PotionEffect potionEffect = this.potionEffect;
		if (potionEffect == null)
			potionEffect = SublimationRecipes.getInstance().getSublimationResult(METAL_STACK);

		if (this.fuelTime > 0)
			this.fuelTime--;

		int fuelValue = TileEntityFurnace.getItemBurnTime(FUEL_STACK);


		if (this.fuelTime > 0)
		{
			if (potionEffect != null)
			{
				//First time kickstart
				if (!this.hasEffectStarted())
				{
					this.activeTime = 1;
					this.setState(true);
					this.setPotionEffect(potionEffect);
					world.playSound(null, pos, ModSounds.SUBLIMATION_CHAMBER_WINDUP, SoundCategory.BLOCKS, 1F, 1F);
				}
				else
				{
					//Resume after fuel pause
					if (!getState())
					{
						this.setState(true);
						world.playSound(null, pos, ModSounds.SUBLIMATION_CHAMBER_WINDUP, SoundCategory.BLOCKS, 1F, 1F);
					}

					if (this.activeTime < potionEffect.getDuration())
					{
						this.activeTime++;

						if (!world.isRemote)
						{
							if (fuelTime > 0 && (activeTime == 30 || activeTime % 300 == 30))
							{
								PacketManager.network.sendToAllAround(
										new PacketStartStopAmbienceSound(ModSounds.SUBLIMATION_CHAMBER_AMBIENCE, SoundCategory.BLOCKS, pos),
										new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 64)
								);
							}
						}

						int range = 6;

						AxisAlignedBB axisAlignedBB = new AxisAlignedBB(pos, pos).grow(range);

						List<EntityPlayer> entityPlayers = world.getEntitiesWithinAABB(EntityPlayer.class, axisAlignedBB);
						for (EntityPlayer player : entityPlayers)
						{
							if (!player.isPotionActive(potionEffect.getPotion()))
							{
								if (!world.isRemote)
									player.addPotionEffect(new PotionEffect(potionEffect.getPotion(), potionEffect.getDuration() - this.activeTime));

								UUID uuid = player.getUniqueID();
								if (!this.affectedPlayers.contains(uuid))
									this.affectedPlayers.add(uuid);
							}
						}

					}
					else
					{
						setInventorySlotContents(METAL_SLOT, ItemStack.EMPTY);
						this.setPotionEffect(null);
						this.setState(false);
						this.activeTime = 0;
					}
				}
			}
		}
		else if (fuelValue > 0 && potionEffect != null)
		{
			Item item = FUEL_STACK.getItem();

			FUEL_STACK.shrink(1);
			this.fuelTime = fuelValue;

			if (FUEL_STACK.isEmpty())
			{
				ItemStack item1 = item.getContainerItem(FUEL_STACK);
				this.setInventorySlotContents(FUEL_SLOT, item1);
			}
		}
		else
		{
			if (!world.isRemote)
				PacketManager.network.sendToAllAround(
						new PacketStartStopAmbienceSound(pos),
						new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 64)
				);

			if (!affectedPlayers.isEmpty())
				this.clearEffect();

			setState(false);
		}
	}


	public boolean hasEffectStarted()
	{
		return this.activeTime > 0;
	}

	public boolean getState()
	{
		return this.world.getBlockState(pos).getValue(BlockChamber.ACTIVE);
	}

	public void setState(boolean active)
	{
		this.world.setBlockState(pos, world.getBlockState(pos).withProperty(BlockChamber.ACTIVE, active));
	}

	@Nonnull
	@Override
	public NBTTagCompound writeToNBT(@Nonnull NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		this.writeChamberToNBT(compound);

		NBTTagList nbttaglist = new NBTTagList();

		for (UUID affectedPlayer : this.affectedPlayers)
		{
			NBTTagCompound nbttagcompound = new NBTTagCompound();
			nbttagcompound.setUniqueId("playerUUID", affectedPlayer);
			nbttaglist.appendTag(nbttagcompound);
		}

		if (!nbttaglist.isEmpty())
		{
			compound.setTag("affectedPlayers", nbttaglist);
		}

		return compound;
	}

	public void writeChamberToNBT(NBTTagCompound compound)
	{
		compound.setInteger("activeTime", this.activeTime);
		compound.setInteger("fuelTime", this.fuelTime);
		if (this.potionEffect != null)
			this.potionEffect.writeCustomPotionEffectToNBT(compound);

		//WOW :D
		ItemStackHelper.saveAllItems(compound, this.inventory);

		if (this.hasCustomName())
			compound.setString("CustomName", this.chamberCustomName);

	}

	@Override
	public void readFromNBT(@Nonnull NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.readChamberFromNBT(compound);

		NBTTagList nbttaglist = compound.getTagList("affectedPlayers", 10);

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
			this.affectedPlayers.add(nbttagcompound.getUniqueId("playerUUID"));
		}
	}

	public void readChamberFromNBT(NBTTagCompound compound)
	{
		this.inventory = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);

		this.fuelTime = compound.getInteger("fuelTime");
		this.activeTime = compound.getInteger("activeTime");

		this.potionEffect = null;
		if (compound.hasKey("Id"))
			this.potionEffect = PotionEffect.readCustomPotionEffectFromNBT(compound);

		if (compound.hasKey("CustomName", 8))
		{
			this.chamberCustomName = compound.getString("CustomName");
		}
	}

	public void clearEffect()
	{
		if (!world.isRemote && this.potionEffect != null)
		{
			for (WorldServer worldServer : Objects.requireNonNull(world.getMinecraftServer()).worlds)
			{
				for (UUID uuid : this.affectedPlayers)
				{
					EntityPlayer player = worldServer.getPlayerEntityByUUID(uuid);
					if (player != null)
						player.removePotionEffect(this.potionEffect.getPotion());
				}
			}
		}
		affectedPlayers.clear();
	}

	@Override
	public boolean shouldRefresh(@Nonnull World world, @Nonnull BlockPos pos, IBlockState oldState, IBlockState newState)
	{
		return oldState.getBlock() != newState.getBlock();
	}


	@SuppressWarnings("unchecked")
	@Override
	@Nullable
	public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
	{
		if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			if (facing == EnumFacing.DOWN)
				return (T) handlerBottom;
			else if (facing == EnumFacing.UP)
				return (T) handlerTop;
			else
				return (T) handlerSide;
		return super.getCapability(capability, facing);
	}

	//Get a new packet (on the server to send on the client) after notifyBlockupdate is called
	//Calls getUpdateTag to enrich this packet with custom informatio
	@Override
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		return new SPacketUpdateTileEntity(this.pos, 1, this.getUpdateTag());
	}

	//Enrich packet with TE data
	@Override
	@Nonnull
	public NBTTagCompound getUpdateTag()
	{
		NBTTagCompound compound = new NBTTagCompound();
		this.writeToNBT(compound);
		return compound;
	}

	//On the client the the data is unwrapped and parsed
	@Override
	public void onDataPacket(@Nonnull NetworkManager net, SPacketUpdateTileEntity pkt)
	{
		NBTTagCompound tag = pkt.getNbtCompound();
		readChamberFromNBT(tag);
	}

}
