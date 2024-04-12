package mffs.api;

public interface ICache
{
    Object getCache(final String p0);
    
    void clearCache(final String p0);
    
    void clearCache();
}
