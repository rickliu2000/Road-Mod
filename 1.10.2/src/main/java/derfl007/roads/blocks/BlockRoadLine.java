package derfl007.roads.blocks;

import derfl007.roads.Reference;
import derfl007.roads.Roads;
import derfl007.roads.init.RoadBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockRoadLine extends Block {
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");

	public BlockRoadLine() {
		super(Material.CLOTH);
		setRegistryName("BlockRoadLine");
		setUnlocalizedName("road_line");
		setDefaultState(this.blockState.getBaseState().withProperty(NORTH, Boolean.valueOf(false))
				.withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false))
				.withProperty(WEST, Boolean.valueOf(false)));
		setCreativeTab(Roads.ROADS_TAB);
        setHardness(1.5F);
        setResistance(10F);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return Reference.ROAD_BLOCK_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
		Block block = iblockstate.getBlock();

		for (connectableBlocks b : connectableBlocks.values()) {
			if (b.block().equals(block)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	public static enum connectableBlocks {
		ROAD_LINE(RoadBlocks.road_line), //
		ROAD_LINE_SLOPE(RoadBlocks.road_line_slope), //
		ROAD_LINE_MERGE(RoadBlocks.road_line_merge), //
		ROAD_LINE_DIAGONAL(RoadBlocks.road_line_diagonal), //
		ROAD_WHITE_QUARTER(RoadBlocks.road_white_quarter), //
		ROAD_WHITE_HALF(RoadBlocks.road_white_half), //
		// ROAD_EXCL_ZONE(RoadBlocks.road_excl_zone), //
		// ROAD_EXCL_ZONE_CORNER_IN(RoadBlocks.road_excl_zone_corner_in), //
		// ROAD_EXCL_ZONE_CORNER_OUT(RoadBlocks.road_excl_zone_corner_out), //
		// ROAD_EXCL_ZONE_DIAGONAL_IN(RoadBlocks.road_excl_zone_diagonal_in), //
		// ROAD_EXCL_ZONE_DIAGONAL_OUT(RoadBlocks.road_excl_zone_diagonal_out),
		// //
		// ROAD_EXCL_ZONE_LINE(RoadBlocks.road_excl_zone_line), //
		ROAD_EXCL_ZONE_SPLIT_IN_L(RoadBlocks.road_excl_zone_split_in_l), //
		ROAD_EXCL_ZONE_SPLIT_IN_R(RoadBlocks.road_excl_zone_split_in_r), //
		ROAD_EXCL_ZONE_SPLIT_OUT_L(RoadBlocks.road_excl_zone_split_out_l), //
		ROAD_EXCL_ZONE_SPLIT_OUT_R(RoadBlocks.road_excl_zone_split_out_r); //

		private final Block block;

		connectableBlocks(Block block) {
			this.block = block;
		}

		private Block block() {
			return block;
		}

		public String getName() {
			return block().toString();
		}
	}

	/**
	 * Get the actual Block state of this Block at the given position. This
	 * applies properties not visible in the metadata, such as fence
	 * connections.
	 */
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(NORTH, Boolean.valueOf(this.canConnectTo(worldIn, pos.north())))
				.withProperty(EAST, Boolean.valueOf(this.canConnectTo(worldIn, pos.east())))
				.withProperty(SOUTH, Boolean.valueOf(this.canConnectTo(worldIn, pos.south())))
				.withProperty(WEST, Boolean.valueOf(this.canConnectTo(worldIn, pos.west())));
	}

	/**
	 * Returns the blockstate with the given rotation from the passed
	 * blockstate. If inapplicable, returns the passed blockstate.
	 */
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		switch (rot) {
		case CLOCKWISE_180:
			return state.withProperty(NORTH, state.getValue(SOUTH)).withProperty(EAST, state.getValue(WEST))
					.withProperty(SOUTH, state.getValue(NORTH)).withProperty(WEST, state.getValue(EAST));
		case COUNTERCLOCKWISE_90:
			return state.withProperty(NORTH, state.getValue(EAST)).withProperty(EAST, state.getValue(SOUTH))
					.withProperty(SOUTH, state.getValue(WEST)).withProperty(WEST, state.getValue(NORTH));
		case CLOCKWISE_90:
			return state.withProperty(NORTH, state.getValue(WEST)).withProperty(EAST, state.getValue(NORTH))
					.withProperty(SOUTH, state.getValue(EAST)).withProperty(WEST, state.getValue(SOUTH));
		default:
			return state;
		}
	}

	/**
	 * Returns the blockstate with the given mirror of the passed blockstate. If
	 * inapplicable, returns the passed blockstate.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		switch (mirrorIn) {
		case LEFT_RIGHT:
			return state.withProperty(NORTH, state.getValue(SOUTH)).withProperty(SOUTH, state.getValue(NORTH));
		case FRONT_BACK:
			return state.withProperty(EAST, state.getValue(WEST)).withProperty(WEST, state.getValue(EAST));
		default:
			return super.withMirror(state, mirrorIn);
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { NORTH, EAST, WEST, SOUTH });
	}
}
