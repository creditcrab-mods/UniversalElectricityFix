package icbm.api.explosion;

public interface IExplosive {
    int getID();

    String getUnlocalizedName();

    String getExplosiveName();

    String getGrenadeName();

    String getMissileName();

    String getMinecartName();

    float getRadius();

    int getTier();

    double getEnergy();
}
