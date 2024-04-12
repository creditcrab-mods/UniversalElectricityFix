package universalelectricity.core.vector;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import universalelectricity.api.vector.IVector3;

public class Vector3 implements Cloneable, IVector3 {

   public double x;
   public double y;
   public double z;


   public Vector3() {
      this(0.0D, 0.0D, 0.0D);
   }

   public Vector3(double x, double y, double z) {
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public Vector3(Entity par1) {
      this.x = par1.posX;
      this.y = par1.posY;
      this.z = par1.posZ;
   }

   public Vector3(TileEntity par1) {
      this.x = (double)par1.xCoord;
      this.y = (double)par1.yCoord;
      this.z = (double)par1.zCoord;
   }

   public Vector3(Vec3 par1) {
      this.x = par1.xCoord;
      this.y = par1.yCoord;
      this.z = par1.zCoord;
   }

   public Vector3(MovingObjectPosition par1) {
      this.x = (double)par1.blockX;
      this.y = (double)par1.blockY;
      this.z = (double)par1.blockZ;
   }

   public Vector3(ChunkCoordinates par1) {
      this.x = (double)par1.posX;
      this.y = (double)par1.posY;
      this.z = (double)par1.posZ;
   }

   public Vector3(ForgeDirection direction) {
      this.x = (double)direction.offsetX;
      this.y = (double)direction.offsetY;
      this.z = (double)direction.offsetZ;
   }

   public Vector3(double yaw, double pitch)
	{
		this(new EulerAngle(yaw, pitch));
	}

   public Vector3(IVector3 vec3) {
      this.x = vec3.x();
      this.y = vec3.y();
      this.z = vec3.z();
   }

   public int intX() {
      return (int)Math.floor(this.x);
   }

   public int intY() {
      return (int)Math.floor(this.y);
   }

   public int intZ() {
      return (int)Math.floor(this.z);
   }

   public Vector3 clone() {
      return new Vector3(this.x, this.y, this.z);
   }

   public Block getBlock(IBlockAccess world) {
      return world.getBlock(this.intX(), this.intY(), this.intZ());
   }

   public int getBlockMetadata(IBlockAccess world) {
      return world.getBlockMetadata(this.intX(), this.intY(), this.intZ());
   }

   public TileEntity getTileEntity(IBlockAccess world) {
      return world.getTileEntity(this.intX(), this.intY(), this.intZ());
   }

   public boolean setBlock(World world, Block block, int metadata, int notify) {
      return world.setBlock(this.intX(), this.intY(), this.intZ(), block, metadata, notify);
   }

   public boolean setBlock(World world, Block block, int metadata) {
      return this.setBlock(world, block, metadata, 3);
   }

   public boolean setBlock(World world, Block block) {
      return this.setBlock(world, block, 0);
   }

   public Vector2 toVector2() {
      return new Vector2(this.x, this.z);
   }

   public Vec3 toVec3() {
      return Vec3.createVectorHelper(this.x, this.y, this.z);
   }

   public double getMagnitude() {
      return Math.sqrt(this.getMagnitudeSquared());
   }

   public double getMagnitudeSquared() {
      return this.x * this.x + this.y * this.y + this.z * this.z;
   }

   public Vector3 normalize() {
      double d = this.getMagnitude();
      if(d != 0.0D) {
         this.multiply(1.0D / d);
      }

      return this;
   }

   public static double distance(Vector3 par1, Vector3 par2) {
      double var2 = par1.x - par2.x;
      double var4 = par1.y - par2.y;
      double var6 = par1.z - par2.z;
      return (double)MathHelper.sqrt_double(var2 * var2 + var4 * var4 + var6 * var6);
   }

   public double distanceTo(Vector3 vector3) {
      double var2 = vector3.x - this.x;
      double var4 = vector3.y - this.y;
      double var6 = vector3.z - this.z;
      return (double)MathHelper.sqrt_double(var2 * var2 + var4 * var4 + var6 * var6);
   }

   public Vector3 add(Vector3 par1) {
      this.x += par1.x;
      this.y += par1.y;
      this.z += par1.z;
      return this;
   }

   public Vector3 add(double par1) {
      this.x += par1;
      this.y += par1;
      this.z += par1;
      return this;
   }

   public Vector3 subtract(Vector3 amount) {
      this.x -= amount.x;
      this.y -= amount.y;
      this.z -= amount.z;
      return this;
   }

   public Vector3 invert() {
      this.multiply(-1.0D);
      return this;
   }

   public Vector3 multiply(double amount) {
      this.x *= amount;
      this.y *= amount;
      this.z *= amount;
      return this;
   }

   public Vector3 multiply(Vector3 vec) {
      this.x *= vec.x;
      this.y *= vec.y;
      this.z *= vec.z;
      return this;
   }

   public static Vector3 subtract(Vector3 par1, Vector3 par2) {
      return new Vector3(par1.x - par2.x, par1.y - par2.y, par1.z - par2.z);
   }

   public static Vector3 add(Vector3 par1, Vector3 par2) {
      return new Vector3(par1.x + par2.x, par1.y + par2.y, par1.z + par2.z);
   }

   public static Vector3 add(Vector3 par1, double par2) {
      return new Vector3(par1.x + par2, par1.y + par2, par1.z + par2);
   }

   public static Vector3 multiply(Vector3 vec1, Vector3 vec2) {
      return new Vector3(vec1.x * vec2.x, vec1.y * vec2.y, vec1.z * vec2.z);
   }

   public static Vector3 multiply(Vector3 vec1, double vec2) {
      return new Vector3(vec1.x * vec2, vec1.y * vec2, vec1.z * vec2);
   }

   public Vector3 round() {
      return new Vector3((double)Math.round(this.x), (double)Math.round(this.y), (double)Math.round(this.z));
   }

   public Vector3 ceil() {
      return new Vector3(Math.ceil(this.x), Math.ceil(this.y), Math.ceil(this.z));
   }

   public Vector3 floor() {
      return new Vector3(Math.floor(this.x), Math.floor(this.y), Math.floor(this.z));
   }

   public List<Entity> getEntitiesWithin(World worldObj, Class<? extends Entity> par1Class) {
      return (List<Entity>) worldObj.getEntitiesWithinAABB(par1Class, AxisAlignedBB.getBoundingBox((double)this.intX(), (double)this.intY(), (double)this.intZ(), (double)(this.intX() + 1), (double)(this.intY() + 1), (double)(this.intZ() + 1)));
   }

   public Vector3 modifyPositionFromSide(ForgeDirection side, double amount) {
      switch(side.ordinal()) {
      case 0:
         this.y -= amount;
         break;
      case 1:
         this.y += amount;
         break;
      case 2:
         this.z -= amount;
         break;
      case 3:
         this.z += amount;
         break;
      case 4:
         this.x -= amount;
         break;
      case 5:
         this.x += amount;
      }

      return this;
   }

   public Vector3 modifyPositionFromSide(ForgeDirection side) {
      this.modifyPositionFromSide(side, 1.0D);
      return this;
   }

   public static Vector3 readFromNBT(NBTTagCompound nbtCompound) {
      Vector3 tempVector = new Vector3();
      tempVector.x = nbtCompound.getDouble("x");
      tempVector.y = nbtCompound.getDouble("y");
      tempVector.z = nbtCompound.getDouble("z");
      return tempVector;
   }

   public NBTTagCompound writeToNBT(NBTTagCompound par1NBTTagCompound) {
      par1NBTTagCompound.setDouble("x", this.x);
      par1NBTTagCompound.setDouble("y", this.y);
      par1NBTTagCompound.setDouble("z", this.z);
      return par1NBTTagCompound;
   }

   public int hashCode() {
      return ("X:" + this.x + "Y:" + this.y + "Z:" + this.z).hashCode();
   }

   public boolean equals(Object o) {
      if(!(o instanceof Vector3)) {
         return false;
      } else {
         Vector3 vector3 = (Vector3)o;
         return this.x == vector3.x && this.y == vector3.y && this.z == vector3.z;
      }
   }

   public String toString() {
      return "Vector3 [" + this.x + "," + this.y + "," + this.z + "]";
   }

   @Override
   public double x() {
      return this.x;
   }

   @Override
   public double y() {
      return this.y;
   }

   @Override
   public double z() {
      return this.z;
   }

   public EulerAngle toAngle()
	{
		return new EulerAngle(Math.toDegrees(Math.atan2(x, z)), Math.toDegrees(-Math.atan2(y, Math.hypot(z, x))));
	}

	public EulerAngle toAngle(IVector3 target)
	{
		return clone().difference(target).toAngle();
	}

   public static Vector3 UP()
	{
		return new Vector3(0, 1, 0);
	}

	public static Vector3 DOWN()
	{
		return new Vector3(0, -1, 0);
	}

	public static Vector3 NORTH()
	{
		return new Vector3(0, 0, -1);
	}

	public static Vector3 SOUTH()
	{
		return new Vector3(0, 0, 1);
	}

	public static Vector3 WEST()
	{
		return new Vector3(-1, 0, 0);
	}

	public static Vector3 EAST()
	{
		return new Vector3(1, 0, 0);
	}

	public static Vector3 ZERO()
	{
		return new Vector3(0, 0, 0);
	}

	public static Vector3 CENTER()
	{
		return new Vector3(0.5, 0.5, 0.5);
	}

   public Vector3 translate(ForgeDirection side, double amount)
	{
		return this.translate(new Vector3(side).scale(amount));
	}

	public Vector3 translate(ForgeDirection side)
	{
		return this.translate(side, 1);
	}

   public Vector3 translate(IVector3 addition)
	{
		this.x += addition.x();
		this.y += addition.y();
		this.z += addition.z();
		return this;
	}

	public Vector3 translate(double x, double y, double z)
	{
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	public Vector3 translate(double addition)
	{
		this.x += addition;
		this.y += addition;
		this.z += addition;
		return this;
	}

	public static Vector3 translate(Vector3 first, IVector3 second)
	{
		return first.clone().translate(second);
	}

	public static Vector3 translate(Vector3 translate, double addition)
	{
		return translate.clone().translate(addition);
	}

   public Vector3 scale(double amount)
	{
		this.x *= amount;
		this.y *= amount;
		this.z *= amount;
		return this;
	}

	public Vector3 scale(double x, double y, double z)
	{
		this.x *= x;
		this.y *= y;
		this.z *= z;
		return this;
	}

	public Vector3 scale(Vector3 amount)
	{
		this.x *= amount.x;
		this.y *= amount.y;
		this.z *= amount.z;
		return this;
	}

	public static Vector3 scale(Vector3 vec, double amount)
	{
		return vec.scale(amount);
	}

	public static Vector3 scale(Vector3 vec, Vector3 amount)
	{
		return vec.scale(amount);
	}

   public Vector3 difference(IVector3 amount)
	{
		return this.translate(-amount.x(), -amount.y(), -amount.z());
	}

	public Vector3 difference(double amount)
	{
		return this.translate(-amount);
	}

	public Vector3 difference(double x, double y, double z)
	{
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}

   /**
	 * RayTrace Code, retrieved from MachineMuse.
	 *
	 * @author MachineMuse
	 */
	public MovingObjectPosition rayTrace(World world, float rotationYaw, float rotationPitch, boolean collisionFlag, double reachDistance)
	{
		Vector3 lookVector = new Vector3(rotationYaw, rotationPitch);
		Vector3 reachPoint = this.clone().translate(lookVector.clone().scale(reachDistance));
		return rayTrace(world, reachPoint, collisionFlag);
	}

	public MovingObjectPosition rayTrace(World world, Vector3 reachPoint, boolean collisionFlag)
	{
		MovingObjectPosition pickedBlock = this.rayTraceBlocks(world, reachPoint.clone(), collisionFlag);
		MovingObjectPosition pickedEntity = this.rayTraceEntities(world, reachPoint.clone());

		if (pickedBlock == null)
		{
			return pickedEntity;
		}
		else if (pickedEntity == null)
		{
			return pickedBlock;
		}
		else
		{
			double dBlock = this.distanceTo(new Vector3(pickedBlock.hitVec));
			double dEntity = this.distanceTo(new Vector3(pickedEntity.hitVec));

			if (dEntity < dBlock)
			{
				return pickedEntity;
			}
			else
			{
				return pickedBlock;
			}
		}
	}

	public MovingObjectPosition rayTrace(World world, boolean collisionFlag, double reachDistance)
	{
		return rayTrace(world, 0, 0, collisionFlag, reachDistance);
	}

	public MovingObjectPosition rayTraceBlocks(World world, float rotationYaw, float rotationPitch, boolean collisionFlag, double reachDistance)
	{
		Vector3 lookVector = new Vector3(rotationYaw, rotationPitch);
		Vector3 reachPoint = this.clone().translate(lookVector.clone().scale(reachDistance));
		return rayTraceBlocks(world, reachPoint, collisionFlag);
	}

	public MovingObjectPosition rayTraceBlocks(World world, Vector3 vec, boolean collisionFlag)
	{
		return world.rayTraceBlocks(this.toVec3(), vec.toVec3(), collisionFlag);
	}

	@Deprecated
	public MovingObjectPosition rayTraceEntities(World world, float rotationYaw, float rotationPitch, boolean collisionFlag, double reachDistance)
	{
		return this.rayTraceEntities(world, rotationYaw, rotationPitch, reachDistance);
	}

	public MovingObjectPosition rayTraceEntities(World world, float rotationYaw, float rotationPitch, double reachDistance)
	{
		return this.rayTraceEntities(world, new Vector3(rotationYaw, rotationPitch).scale(reachDistance));
	}

	/**
	 * Does an entity raytrace.
	 *
	 * @param world - The world object.
	 * @param target - The rotation in terms of Vector3. Convert using
	 * getDeltaPositionFromRotation()
	 * @return The target hit.
	 */
	public MovingObjectPosition rayTraceEntities(World world, Vector3 target)
	{
		MovingObjectPosition pickedEntity = null;
		Vec3 startingPosition = toVec3();
		Vec3 look = target.toVec3();
		double reachDistance = distanceTo(target);
		//Vec3 reachPoint = Vec3.createVectorHelper(startingPosition.xCoord + look.xCoord * reachDistance, startingPosition.yCoord + look.yCoord * reachDistance, startingPosition.zCoord + look.zCoord * reachDistance);

		double checkBorder = 1.1 * reachDistance;
		AxisAlignedBB boxToScan = AxisAlignedBB.getBoundingBox(-checkBorder, -checkBorder, -checkBorder, checkBorder, checkBorder, checkBorder).offset(this.x, this.y, this.z);

		@SuppressWarnings("unchecked")
		List<Entity> entitiesInBounds = world.getEntitiesWithinAABBExcludingEntity(null, boxToScan);
		double closestEntity = reachDistance;

		if (entitiesInBounds == null || entitiesInBounds.isEmpty())
		{
			return null;
		}
		for (Entity possibleHits : entitiesInBounds)
		{
			if (possibleHits != null && possibleHits.canBeCollidedWith() && possibleHits.boundingBox != null)
			{
				float border = possibleHits.getCollisionBorderSize();
				AxisAlignedBB aabb = possibleHits.boundingBox.expand(border, border, border);
				MovingObjectPosition hitMOP = aabb.calculateIntercept(startingPosition, target.toVec3());

				if (hitMOP != null)
				{
					if (aabb.isVecInside(startingPosition))
					{
						if (0.0D < closestEntity || closestEntity == 0.0D)
						{
							pickedEntity = new MovingObjectPosition(possibleHits);
							if (pickedEntity != null)
							{
								pickedEntity.hitVec = hitMOP.hitVec;
								closestEntity = 0.0D;
							}
						}
					}
					else
					{
						double distance = startingPosition.distanceTo(hitMOP.hitVec);

						if (distance < closestEntity || closestEntity == 0.0D)
						{
							pickedEntity = new MovingObjectPosition(possibleHits);
							pickedEntity.hitVec = hitMOP.hitVec;
							closestEntity = distance;
						}
					}
				}
			}
		}
		return pickedEntity;
	}

	public MovingObjectPosition rayTraceEntities(World world, Entity target)
	{
		return rayTraceEntities(world, new Vector3(target));
	}

   public Vector3 rotate(float angle, Vector3 axis)
	{
		return translateMatrix(getRotationMatrix(angle), this);
	}

	public double[] getRotationMatrix(float angle)
	{
		double[] matrix = new double[16];
		Vector3 axis = this.clone().normalize();
		double x = axis.x;
		double y = axis.y;
		double z = axis.z;
		angle *= 0.0174532925D;
		float cos = (float) Math.cos(angle);
		float ocos = 1.0F - cos;
		float sin = (float) Math.sin(angle);
		matrix[0] = (x * x * ocos + cos);
		matrix[1] = (y * x * ocos + z * sin);
		matrix[2] = (x * z * ocos - y * sin);
		matrix[4] = (x * y * ocos - z * sin);
		matrix[5] = (y * y * ocos + cos);
		matrix[6] = (y * z * ocos + x * sin);
		matrix[8] = (x * z * ocos + y * sin);
		matrix[9] = (y * z * ocos - x * sin);
		matrix[10] = (z * z * ocos + cos);
		matrix[15] = 1.0F;
		return matrix;
	}

	public static Vector3 translateMatrix(double[] matrix, Vector3 translation)
	{
		double x = translation.x * matrix[0] + translation.y * matrix[1] + translation.z * matrix[2] + matrix[3];
		double y = translation.x * matrix[4] + translation.y * matrix[5] + translation.z * matrix[6] + matrix[7];
		double z = translation.x * matrix[8] + translation.y * matrix[9] + translation.z * matrix[10] + matrix[11];
		translation.x = x;
		translation.y = y;
		translation.z = z;
		return translation;
	}

	@Deprecated
	public void rotate(double yaw, double pitch, double roll)
	{
		rotate(new EulerAngle(yaw, roll));
	}

	/** Rotates this Vector by a yaw, pitch and roll value. */
	public void rotate(EulerAngle angle)
	{
		double yawRadians = angle.yawRadians();
		double pitchRadians = angle.pitchRadians();
		double rollRadians = angle.rollRadians();

		double x = this.x;
		double y = this.y;
		double z = this.z;

		this.x = x * Math.cos(yawRadians) * Math.cos(pitchRadians) + z * (Math.cos(yawRadians) * Math.sin(pitchRadians) * Math.sin(rollRadians) - Math.sin(yawRadians) * Math.cos(rollRadians)) + y * (Math.cos(yawRadians) * Math.sin(pitchRadians) * Math.cos(rollRadians) + Math.sin(yawRadians) * Math.sin(rollRadians));
		this.z = x * Math.sin(yawRadians) * Math.cos(pitchRadians) + z * (Math.sin(yawRadians) * Math.sin(pitchRadians) * Math.sin(rollRadians) + Math.cos(yawRadians) * Math.cos(rollRadians)) + y * (Math.sin(yawRadians) * Math.sin(pitchRadians) * Math.cos(rollRadians) - Math.cos(yawRadians) * Math.sin(rollRadians));
		this.y = -x * Math.sin(pitchRadians) + z * Math.cos(pitchRadians) * Math.sin(rollRadians) + y * Math.cos(pitchRadians) * Math.cos(rollRadians);
	}

	/** Rotates a point by a yaw and pitch around the anchor 0,0 by a specific angle. */
	public void rotate(double yaw, double pitch)
	{
		rotate(new EulerAngle(yaw, pitch));
	}

	public void rotate(double yaw)
	{
		double yawRadians = Math.toRadians(yaw);

		double x = this.x;
		double z = this.z;

		if (yaw != 0)
		{
			this.x = x * Math.cos(yawRadians) - z * Math.sin(yawRadians);
			this.z = x * Math.sin(yawRadians) + z * Math.cos(yawRadians);
		}
	}

	public Vector3 rotate(Quaternion rotator)
	{
		rotator.rotate(this);
		return this;
	}

}
