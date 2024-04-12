package universalelectricity.api.energy;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagLong;

/**
 * Can be used internally for IEnergyInterface blocks. This is optional and should be used for
 * ease of use purposes.
 * 
 * @author Calclavia, Based on Thermal Expansion
 * 
 */
public class EnergyStorageHandler
{
	protected double energy;
	protected double capacity;
	protected double maxReceive;
	protected double maxExtract;

	/**
	 * A cache of the last energy stored through extract and receive.
	 */
	protected double lastEnergy;

	public EnergyStorageHandler()
	{
		this(0);
	}

	public EnergyStorageHandler(double capacity)
	{
		this(capacity, capacity, capacity);
	}

	public EnergyStorageHandler(double capacity, double maxTransfer)
	{
		this(capacity, maxTransfer, maxTransfer);
	}

	public EnergyStorageHandler(double capacity, double maxReceive, double maxExtract)
	{
		this.capacity = capacity;
		this.maxReceive = maxReceive;
		this.maxExtract = maxExtract;
	}

	public EnergyStorageHandler readFromNBT(NBTTagCompound nbt)
    {
        NBTBase energyTag = nbt.getTag("energy");
        if (energyTag instanceof NBTTagDouble)
        {
            this.energy = ((NBTTagDouble) energyTag).func_150286_g();
        }
        else if (energyTag instanceof NBTTagFloat)
        {
            this.energy = ((NBTTagFloat) energyTag).func_150288_h();
        }
        else if (energyTag instanceof NBTTagInt)
        {
            this.energy = ((NBTTagInt) energyTag).func_150287_d();
        }
        else if (energyTag instanceof NBTTagLong)
        {
            this.energy = ((NBTTagLong) energyTag).func_150291_c();
        }
        return this;
    }

	public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	{
		nbt.setDouble("energy", this.getEnergy());
		return nbt;
	}

	public void setCapacity(double capacity)
	{
		this.capacity = capacity;

		if (getEnergy() > capacity)
		{
			energy = capacity;
		}
	}

	public void setMaxTransfer(double maxTransfer)
	{
		setMaxReceive(maxTransfer);
		setMaxExtract(maxTransfer);
	}

	public void setMaxReceive(double maxReceive)
	{
		this.maxReceive = maxReceive;
	}

	public void setMaxExtract(double maxExtract)
	{
		this.maxExtract = maxExtract;
	}

	public double getMaxReceive()
	{
		return maxReceive;
	}

	public double getMaxExtract()
	{
		return maxExtract;
	}

	/**
	 * This function is included to allow for server -> client sync. Do not call this externally to
	 * the containing Tile Entity, as not all IEnergyHandlers are
	 * guaranteed to have it.
	 * 
	 * @param energy
	 */
	public void setEnergy(double energy)
	{
		this.energy = energy;

		if (this.getEnergy() > this.getEnergyCapacity())
		{
			this.energy = this.getEnergyCapacity();
		}
		else if (this.getEnergy() < 0)
		{
			this.energy = 0;
		}
	}

	/**
	 * This function is included to allow the containing tile to directly and efficiently modify the
	 * energy contained in the EnergyStorage. Do not rely on this
	 * externally, as not all IEnergyHandlers are guaranteed to have it.
	 * 
	 * @param energy
	 */
	public void modifyEnergyStored(double energy)
	{
		this.setEnergy(this.getEmptySpace() + energy);

		if (this.getEnergy() > this.getEnergyCapacity())
		{
			this.setEnergy(this.getEnergyCapacity());
		}
		else if (this.getEnergy() < 0)
		{
			this.setEnergy(0);
		}
	}

	public double receiveEnergy(double receive, boolean doReceive)
	{
		double energyReceived = Math.min(this.getEnergyCapacity() - this.getEnergy(), Math.min(this.getMaxReceive(), receive));

		if (doReceive)
		{
			this.lastEnergy = this.getEnergy();
			this.setEnergy(this.getEnergy() + energyReceived);
		}
		return energyReceived;
	}

	public double receiveEnergy(boolean doReceive)
	{
		return this.receiveEnergy(this.getMaxReceive(), doReceive);
	}

	public double receiveEnergy()
	{
		return this.receiveEnergy(true);
	}

	public double extractEnergy(double extract, boolean doExtract)
	{
		double energyExtracted = Math.min(this.getEnergy(), Math.min(this.getMaxExtract(), extract));

		if (doExtract)
		{
			this.lastEnergy = this.getEnergy();
			this.setEnergy(this.getEnergy() - energyExtracted);
		}
		return energyExtracted;
	}

	public double extractEnergy(boolean doExtract)
	{
		return this.extractEnergy(this.getMaxExtract(), doExtract);
	}

	public double extractEnergy()
	{
		return this.extractEnergy(true);
	}

	public boolean checkReceive(double receive)
	{
		return this.receiveEnergy(receive, false) >= receive;
	}

	public boolean checkReceive()
	{
		return this.checkReceive(this.getMaxReceive());
	}

	public boolean checkExtract(double extract)
	{
		return this.extractEnergy(extract, false) >= extract;
	}

	public boolean checkExtract()
	{
		return this.checkExtract(this.getMaxExtract());
	}

	public boolean isFull()
	{
		return this.getEnergy() >= this.getEnergyCapacity();
	}

	public boolean isEmpty()
	{
		return this.getEnergy() == 0;
	}

	public double getLastEnergy()
	{
		return this.lastEnergy;
	}

	/**
	 * @return True if the last energy state and the current one are either in an
	 * "empty or not empty" change state.
	 */
	public boolean didEnergyStateChange()
	{
		return (this.getLastEnergy() == 0 && this.getEnergy() > 0) || (this.getLastEnergy() > 0 && this.getEnergy() == 0);
	}

	/**
	 * Returns the amount of energy this storage can further store.
	 */
	public double getEmptySpace()
	{
		return this.getEnergyCapacity() - this.getEnergy();
	}

	public double getEnergy()
	{
		return this.energy;
	}

	public double getEnergyCapacity()
	{
		return this.capacity;
	}

	@Override
	public String toString()
	{
		return this.getClass().getSimpleName() + "[" + this.getEnergy() + "/" + this.getEnergyCapacity() + "]";
	}
}
