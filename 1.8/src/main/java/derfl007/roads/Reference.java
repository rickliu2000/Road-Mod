package derfl007.roads;

import net.minecraft.block.Block;
import net.minecraft.util.AxisAlignedBB;

public class Reference {
	public static final String MOD_ID = "roads";
	public static final String MOD_NAME = "Road Mod";
	public static final String VERSION = "1.0.2";
	public static final String ACCEPTED_VERSIONS = "[1.8,1.8.9]";

	public static final String CLIENT_PROXY_CLASS = "derfl007.roads.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "derfl007.roads.proxy.CommonProxy";
	
	public static void setBlockBounds(Block block, AxisAlignedBB axisAlignedBB) {
		float maxX = (float) axisAlignedBB.maxX;
		float maxY = (float) axisAlignedBB.maxY;
		float maxZ = (float) axisAlignedBB.maxZ;
		float minX = (float) axisAlignedBB.minX;
		float minY = (float) axisAlignedBB.minY;
		float minZ = (float) axisAlignedBB.minZ;
		block.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
	}

	public static final AxisAlignedBB ROAD_BLOCK_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.82D, 1.0D);
	
	public static final AxisAlignedBB SC_INNER_WEST_AABB = new AxisAlignedBB(0.75D, 1.0D, 0.75D, 1.0D, 1.0625D, 1.0D);
	public static final AxisAlignedBB SC_INNER_EAST_AABB = new AxisAlignedBB(0.0D, 1.0D, 0.0D, 0.25D, 1.0625D, 0.25D);
	public static final AxisAlignedBB SC_INNER_SOUTH_AABB = new AxisAlignedBB(0.75D, 1.0D, 0.0D, 1.0D, 1.0625D, 0.25D);
	public static final AxisAlignedBB SC_INNER_NORTH_AABB = new AxisAlignedBB(0.0D, 1.0D, 0.75D, 0.25D, 1.0625D, 1.0D);
	public static final AxisAlignedBB SC_STRAIGHT_WEST_AABB = new AxisAlignedBB(0.0D, 1.0D, 0.0D, 0.25D, 1.0625D, 1);
	public static final AxisAlignedBB SC_STRAIGHT_EAST_AABB = new AxisAlignedBB(0.75D, 1.0D, 0.0D, 1.0D, 1.0625D, 1.0D);
	public static final AxisAlignedBB SC_STRAIGHT_SOUTH_AABB = new AxisAlignedBB(0.0D, 1.0D, 0.0D, 1.0D, 1.0625D,
			0.25D);
	public static final AxisAlignedBB SC_STRAIGHT_NORTH_AABB = new AxisAlignedBB(0.0D, 1.0D, 0.75D, 1.0D, 1.0625D,
			1.0D);
}
