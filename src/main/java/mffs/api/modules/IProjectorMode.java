package mffs.api.modules;

import java.util.Set;
import mffs.api.IFieldInteraction;
import mffs.api.IProjector;
import universalelectricity.core.vector.Vector3;

public interface IProjectorMode {
    Set<Vector3> getExteriorPoints(final IFieldInteraction p0);

    Set<Vector3> getInteriorPoints(final IFieldInteraction p0);

    boolean isInField(final IFieldInteraction p0, final Vector3 p1);

    void render(final IProjector p0, final double p1, final double p2,
            final double p3, final float p4, final long p5);
}
