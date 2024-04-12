package mffs.api.fortron;

import java.util.Set;

public interface IFortronCapacitor
{
    Set<IFortronFrequency> getLinkedDevices();
    
    int getTransmissionRange();
    
    int getTransmissionRate();
}
