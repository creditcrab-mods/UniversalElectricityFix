package mffs.api;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.block.Block;

public class Blacklist
{
    public static final Set<Block> stabilizationBlacklist;
    public static final Set<Block> disintegrationBlacklist;
    public static final Set<Block> forceManipulationBlacklist;
    
    static {
        stabilizationBlacklist = new HashSet<>();
        disintegrationBlacklist = new HashSet<>();
        forceManipulationBlacklist = new HashSet<>();
    }
}
