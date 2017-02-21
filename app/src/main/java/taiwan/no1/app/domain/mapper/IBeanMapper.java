package taiwan.no1.app.domain.mapper;

import android.support.annotation.NonNull;

/**
 * Interface that transform between data layer and kotlin layer.
 *
 * @author Jieyi
 * @since 12/8/16
 */

public interface IBeanMapper<M, E> {
    /**
     * Interaction of transforming from kotlin layer {@link M} model data to data layer {@link E} entity data.
     *
     * @param model kotlin layer model data.
     * @return data layer entity data.
     */
    @NonNull
    E transformFrom(@NonNull final M model);

    /**
     * Interaction of transforming from data layer {@link E} entity data to kotlin layer {@link M} model data.
     *
     * @param entity data layer entity data.
     * @return kotlin layer model data.
     */
    @NonNull
    M transformTo(@NonNull final E entity);
}
