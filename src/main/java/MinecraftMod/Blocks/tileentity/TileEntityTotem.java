package MinecraftMod.Blocks.tileentity;

import MinecraftMod.Blocks.container.ContainerTotem;
import MinecraftMod.util.Reference;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityTotem extends TileEntityLockableLoot implements ITickable {

	private NonNullList<ItemStack> chestContents = NonNullList.<ItemStack>withSize(72,ItemStack.EMPTY);
	public int numPlayerUsing, ticksSinceSync;
	public float lidAngle, prevLidAngle;
	
	public int getSizeInventory() {
		return 72;
	}
	
	public int getInventoryStackLimit() {
		return 64;
	}
	
	public boolean isEmpty() {
		
		for(ItemStack stack : this.chestContents) {
			if(!stack.isEmpty()) return false;
		}
		
		return true;
	
	}
	
	public String getName() {
		return this.hasCustomName() ? this.customName : "container.Totem";
	}
	
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.chestContents = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
	
		if(!this.checkLootAndRead(compound)) ItemStackHelper.loadAllItems(compound, chestContents);
		if(compound.hasKey("CutomName", 8)) this.customName = compound.getString("CustomName");

	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		
		if(!this.checkLootAndWrite(compound)) ItemStackHelper.saveAllItems(compound, chestContents);
		if(compound.hasKey("CutomName", 8)) compound.setString("CustomName", this.customName);
	
		return compound;
	}
	
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerTotem(playerInventory, this, playerIn);
	}
	
	public String getGuiID() {
		return Reference.MOD_ID + ":Totem";
	}
	
	protected NonNullList<ItemStack> getItems(){
		return this.chestContents;
	}
	
	public void update()
    {
        if (!this.world.isRemote && this.numPlayerUsing != 0 && (this.ticksSinceSync + pos.getX() + pos.getY() + pos.getZ()) % 200 == 0)
        {
            this.numPlayerUsing = 0;
            float f = 5.0F;
 
            for (EntityPlayer entityplayer : this.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB((double)((float)pos.getX() - 5.0F), (double)((float)pos.getY() - 5.0F), (double)((float)pos.getZ() - 5.0F), (double)((float)(pos.getX() + 1) + 5.0F), (double)((float)(pos.getY() + 1) + 5.0F), (double)((float)(pos.getZ() + 1) + 5.0F))))
            {
                if (entityplayer.openContainer instanceof ContainerTotem)
                {
                    if (((ContainerTotem)entityplayer.openContainer).getChestInventory() == this)
                    {
                        ++this.numPlayerUsing;
                    }
                }
            }
        }
       
        this.prevLidAngle = this.lidAngle;
        float f1 = 0.1F;
 
        if (this.numPlayerUsing > 0 && this.lidAngle == 0.0F)
        {
            double d1 = (double)pos.getX() + 0.5D;
            double d2 = (double)pos.getZ() + 0.5D;
            this.world.playSound((EntityPlayer)null, d1, (double)pos.getY() + 0.5D, d2, SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
        }
 
        if (this.numPlayerUsing == 0 && this.lidAngle > 0.0F || this.numPlayerUsing > 0 && this.lidAngle < 1.0F)
        {
            float f2 = this.lidAngle;
 
            if (this.numPlayerUsing > 0)
            {
                this.lidAngle += 0.1F;
            }
            else
            {
                this.lidAngle -= 0.1F;
            }
 
            if (this.lidAngle > 1.0F)
            {
                this.lidAngle = 1.0F;
            }
 
            float f3 = 0.5F;
 
            if (this.lidAngle < 0.5F && f2 >= 0.5F)
            {
                double d3 = (double)pos.getX() + 0.5D;
                double d0 = (double)pos.getZ() + 0.5D;
                this.world.playSound((EntityPlayer)null, d3, (double)pos.getY() + 0.5D, d0, SoundEvents.BLOCK_IRON_TRAPDOOR_CLOSE, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
            }
 
            if (this.lidAngle < 0.0F)
            {
                this.lidAngle = 0.0F;
            }
        }      
    }
	
	@Override
	public void openInventory(EntityPlayer player)
	{
		++this.numPlayerUsing;
		this.world.addBlockEvent(pos, this.getBlockType(), 1, this.numPlayerUsing);
		this.world.notifyNeighborsOfStateChange(pos, this.getBlockType(), false);
	}
	
	@Override
	public void closeInventory(EntityPlayer player) 
	{
		--this.numPlayerUsing;
		this.world.addBlockEvent(pos, this.getBlockType(), 1, this.numPlayerUsing);
		this.world.notifyNeighborsOfStateChange(pos, this.getBlockType(), false);
	}

	@Override
	public void tick() {
		
	}
	
}
