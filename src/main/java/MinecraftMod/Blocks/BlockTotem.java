package MinecraftMod.Blocks;

import MinecraftMod.Main;
import MinecraftMod.Blocks.tileentity.TileEntityTotem;
import MinecraftMod.init.ModBlocks;
import MinecraftMod.init.ModItems;
import MinecraftMod.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTotem extends Block {
	
	public BlockTotem(String name)
	{
		super(Material.WOOD);
		setUnlocalizedName(name);
		setRegistryName(name);
//		setCreativeTab(Main.TUTORIAL);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(name));
		
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		if(!worldIn.isRemote)
		{
			playerIn.openGui(Main.instance, Reference.GUI_TOTEM, worldIn, pos.getX(), pos.getY(), pos.getZ()); 
		}
		return true;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		
		TileEntityTotem tileentity = (TileEntityTotem)worldIn.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(worldIn, pos, tileentity);
		super.breakBlock(worldIn, pos, state);
		
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos,IBlockState state, EntityLivingBase placer, ItemStack stack) {
		
		if(stack.hasDisplayName()) {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			
			if(tileentity instanceof TileEntityTotem) {
				((TileEntityChest)tileentity).setCustomName(stack.getDisplayName());
			}
		}
	}
	
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityTotem();
		
	}
	
	public EnumBlockRenderType getRenderTye(IBlockState state) {
		
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}
	
	public  boolean isFullBlock(IBlockState state) {
		return false;
	}
	
	public  boolean isFullCube(IBlockState state) {
		return false;
	}
	
	public  boolean isOpaqueCube(IBlockState state) {
		return false;
	}


}
