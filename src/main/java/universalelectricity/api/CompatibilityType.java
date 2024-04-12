package universalelectricity.api;

import cpw.mods.fml.common.Loader;

/**
 * ORDER OF MAGNITUDE:
 * A coal in Universal Electricity (based on an estimate in real life) is worth 4MJ.
 * A fission reactor should make around 4-9GW.
 * A fusion reactor would go into the tera-watts.
 * Reika's conversion: IC2[22512], BC[56280], RF[5628]
 * @author Calclavia
 */
public enum CompatibilityType
{
	REDSTONE_FLUX("universalelectricity", "RedstoneFlux", "Redstone Flux", "RF", 2.5),
	INDUSTRIALCRAFT("IC2", "IndustrialCraft", "Electrical Unit", "EU", 10),
	GREGTECH("gregtech", "GregTech", "Electrical Unit", "EU", 10),
	HMB("hbm", "HBM", "HE", "HE", 10),
	RAILCRAFT("railcraft", "Railcraft", "Charge", "C", 10),
	MAGNETICRAFT("Magneticraft", "Magneticraft", "Watts", "W", 0.25),
	APPENG("appliedenergistics2", "Applied Energistics 2", "Applied Energy", "AE", 5),
	BLUETRICITY("universalelectricity", "Bluetricity", "Bluetricity", "BT", 17.5),
	FACTORIZATION("factorization", "Factorization", "Charge", "C", 0.4375),
	ULTRATECH("UltraTech", "Ultratech", "Quantum Power", "QP", 50),
	ELN("Eln", "Electrical Age", "Watts", "W", 10.0/3.0),
	HEXCRAFT("hexcraft", "HEXCraft", "HEX", "HEX", 0.78125),
	GALACTICRAFT("galacticraftcore", "Galacticraft", "Galacticraft Joule", "gJ", 1.525);


	public final String modID;
	public final String moduleName;
	public final String fullUnit;
	public final String unit;

	/**
	 * Multiply UE energy by this ratio to convert it to the forgien ratio.
	 */
	public double ratio;

	/**
	 * Multiply the forgien energy by this ratio to convert it into UE energy.
	 */
	public double reciprocal_ratio;

	/**
	 * The Universal Electricity Loader will change this value to indicate if the module is
	 * loaded or not.
	 */
	public boolean isModuleEnabled;

	/**
	 * @param modID - The Forge mod ID.
	 * @param moduleName - The name of the module, used for config and ASM
	 * @param fullUnit - The unit used
	 * @param unit - The unit short form used
	 * @param ratio - How much UE energy equates to the forgien energy?
	 */
	CompatibilityType(String modID, String moduleName, String fullUnit, String unit, double ratio)
	{
		this.modID = modID;
		this.moduleName = moduleName;
		this.fullUnit = fullUnit;
		this.unit = unit;
		this.ratio = 1.0 / ratio;
		this.reciprocal_ratio = ratio;
	}

	public boolean isLoaded()
	{
		return Loader.isModLoaded(this.modID);
	}

	public static CompatibilityType get(String moduleName)
	{
		for (CompatibilityType type : values())
		{
			if (moduleName.equals(type.moduleName))
			{
				return type;
			}
		}

		return null;
	}

	public double fromJoules(double joules) {
		return this.ratio * joules;
	}

	public double toJoules(double energy) {
		return this.reciprocal_ratio * energy;
	}

}
