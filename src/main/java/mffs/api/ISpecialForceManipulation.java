package mffs.api;

public interface ISpecialForceManipulation
{
    boolean preMove(final int p0, final int p1, final int p2);
    
    void move(final int p0, final int p1, final int p2);
    
    void postMove();
}
