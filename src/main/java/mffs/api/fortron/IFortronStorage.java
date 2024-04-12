package mffs.api.fortron;

public interface IFortronStorage
{
    void setFortronEnergy(final int p0);
    
    int getFortronEnergy();
    
    int getFortronCapacity();
    
    int requestFortron(final int p0, final boolean p1);
    
    int provideFortron(final int p0, final boolean p1);
}
