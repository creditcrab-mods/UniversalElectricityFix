package universalelectricity.core;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import java.io.File;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.config.Configuration;
import universalelectricity.api.CompatibilityType;
import universalelectricity.compat.CompatHandler;
import universalelectricity.core.proxy.CommonProxy;

@Mod(modid = "universalelectricity", name = "Universal Electricity", version = UniversalElectricity.VERSION)
public class UniversalElectricity {

   public static final String VERSION = "{VERSION}";
   public static final Configuration CONFIGURATION = new Configuration(new File(Loader.instance().getConfigDir(), "UniversalElectricity.cfg"));
   public static double UE_IC2_RATIO = CompatibilityType.INDUSTRIALCRAFT.reciprocal_ratio;
   public static double UE_RF_RATIO = CompatibilityType.REDSTONE_FLUX.reciprocal_ratio;
   public static boolean isVoltageSensitive = true;
   public static boolean isNetworkActive = true;
   public static boolean ic2Compat = false;
   public static final Material machine = new Material(MapColor.ironColor);
   @SidedProxy(modId = "universalelectricity", serverSide = "universalelectricity.core.proxy.CommonProxy", clientSide = "universalelectricity.core.proxy.ClientProxy")
   public static CommonProxy proxy;

   @Mod.EventHandler
   public void preInit(FMLPreInitializationEvent e) {
      CONFIGURATION.load();
      isVoltageSensitive = CONFIGURATION.get("Compatiblity", "Is Voltage Sensitive", isVoltageSensitive).getBoolean(isVoltageSensitive);
      isNetworkActive = CONFIGURATION.get("Compatiblity", "Is Network Active", isNetworkActive).getBoolean(isNetworkActive);
      ic2Compat = CONFIGURATION.get("Compatiblity", "Compatiblity with IC2 tiles and items", ic2Compat).getBoolean(ic2Compat);
      CONFIGURATION.save();
   }

   @Mod.EventHandler
   public void init(FMLInitializationEvent e) {
      CompatHandler.initCompatHandlers();
      proxy.init();
   }

   public static boolean isIC2CompatActive() {
      return ic2Compat && Loader.isModLoaded("IC2");
   }

}
