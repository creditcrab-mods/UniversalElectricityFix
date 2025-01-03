package universalelectricity.core;

import universalelectricity.Tags;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import java.io.File;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.config.Configuration;
import org.lwjgl.Sys;
import universalelectricity.api.CompatibilityType;
import universalelectricity.compat.CompatHandler;
import universalelectricity.core.proxy.CommonProxy;

@Mod(modid = "universalelectricity", name = "Universal Electricity", version = Tags.VERSION)
public class UniversalElectricity {

   public static final String VERSION = Tags.VERSION;

   public static final String compatCategory = "Compatibility";
   public static final Configuration CONFIGURATION = new Configuration(new File(Loader.instance().getConfigDir(), "UniversalElectricity.cfg"));
   public static double UE_IC2_RATIO = CompatibilityType.INDUSTRIALCRAFT.reciprocal_ratio;
   public static double UE_RF_RATIO = CompatibilityType.REDSTONE_FLUX.reciprocal_ratio;
   public static boolean isVoltageSensitive = true;
   public static boolean isNetworkActive = true;
   public static boolean ic2Compat = true;

   public static boolean rfCompat = true;
   public static final Material machine = new Material(MapColor.ironColor);
   @SidedProxy(modId = "universalelectricity", serverSide = "universalelectricity.core.proxy.CommonProxy", clientSide = "universalelectricity.core.proxy.ClientProxy")
   public static CommonProxy proxy;

   @Mod.EventHandler
   public void preInit(FMLPreInitializationEvent e) {
      CONFIGURATION.load();
      isVoltageSensitive = CONFIGURATION.get(compatCategory, "Is Voltage Sensitive", isVoltageSensitive).getBoolean(isVoltageSensitive);
      isNetworkActive = CONFIGURATION.get(compatCategory, "Is Network Active", isNetworkActive).getBoolean(isNetworkActive);
      ic2Compat = CONFIGURATION.get(compatCategory, "Compatiblity with IC2 tiles and items", ic2Compat).getBoolean(ic2Compat);
      rfCompat = CONFIGURATION.get(compatCategory,"Compatibility with Redstone Flux",rfCompat).getBoolean(rfCompat);
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
   public static boolean isRFCompatActive(){
       return rfCompat && Loader.isModLoaded("CoFHCore");
   }

}
