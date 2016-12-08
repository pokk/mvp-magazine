package taiwan.no1.accounting.domain.mapper;

/**
 * Interface that transform between data layer and kotlin layer.
 *
 * @author jieyi
 * @version 0.0.1
 * @since 2016/12/08
 */

public interface BaseEntityMapper<M, E> {
    /**
     * Interaction of transforming from kotlin layer {@link M} model data to data layer {@link E} entity data.
     *
     * @param model kotlin layer model data.
     * @return data layer entity data.
     */
    E transformFrom(M model);

    /**
     * Interaction of transforming from data layer {@link E} entity data to kotlin layer {@link M} model data.
     *
     * @param entity data layer entity data.
     * @return kotlin layer model data.
     */
    M transformTo(E entity);
}
