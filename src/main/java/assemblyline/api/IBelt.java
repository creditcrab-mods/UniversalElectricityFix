package assemblyline.api;

import java.util.List;
import net.minecraft.entity.Entity;

public interface IBelt {
    public List getAffectedEntities();

    public void IgnoreEntity(Entity var1);
}

