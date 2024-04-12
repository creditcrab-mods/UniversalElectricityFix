package universalelectricity.core.electricity;


public class ElectricityPack implements Cloneable {

   public double amperes;
   public double voltage;


   public ElectricityPack(double amperes, double voltage) {
      this.amperes = amperes;
      this.voltage = voltage;
   }

   public ElectricityPack() {
      this(0.0D, 0.0D);
   }

   public static ElectricityPack getFromWatts(double watts, double voltage) {
      return new ElectricityPack(watts / voltage, voltage);
   }

   public double getWatts() {
      return getWatts(this.amperes, this.voltage);
   }

   public double getConductance() {
      return getConductance(this.amperes, this.voltage);
   }

   public double getResistance() {
      return getResistance(this.amperes, this.voltage);
   }

   public static double getJoules(double watts, double seconds) {
      return watts * seconds;
   }

   public static double getJoules(double amps, double voltage, double seconds) {
      return amps * voltage * seconds;
   }

   public static double getWattsFromJoules(double joules, double seconds) {
      return joules / seconds;
   }

   public static double getAmps(double watts, double voltage) {
      return watts / voltage;
   }

   public static double getAmps(double ampHours) {
      return ampHours * 3600.0D;
   }

   public static double getAmpsFromWattHours(double wattHours, double voltage) {
      return getWatts(wattHours) / voltage;
   }

   public static double getWattHoursFromAmpHours(double ampHours, double voltage) {
      return ampHours * voltage;
   }

   public static double getAmpHours(double amps) {
      return amps / 3600.0D;
   }

   public static double getWatts(double amps, double voltage) {
      return amps * voltage;
   }

   public static double getWatts(double wattHours) {
      return wattHours * 3600.0D;
   }

   public static double getWattHours(double watts) {
      return watts / 3600.0D;
   }

   public static double getWattHours(double amps, double voltage) {
      return getWattHours(getWatts(amps, voltage));
   }

   public static double getResistance(double amps, double voltage) {
      return voltage / amps;
   }

   public static double getConductance(double amps, double voltage) {
      return amps / voltage;
   }

   public String toString() {
      return "ElectricityPack [Amps:" + this.amperes + " Volts:" + this.voltage + "]";
   }

   public ElectricityPack clone() {
      return new ElectricityPack(this.amperes, this.voltage);
   }

   public boolean isEqual(ElectricityPack electricityPack) {
      return this.amperes == electricityPack.amperes && this.voltage == electricityPack.voltage;
   }
}
