package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.CommonEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.CommonModel;

/**
 * Mapper class used to transform between {@link CommonModel.BaseBean} (in the kotlin layer) and
 * {@link CommonEntity.BaseBean} (in the data layer).
 *
 * @author Jieyi
 * @since 12/28/16
 */

@Singleton
public class BaseBeanMapper implements IBeanMapper<CommonModel.BaseBean, CommonEntity.BaseBean> {
    @Inject
    public BaseBeanMapper() {
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    @Deprecated
    public CommonEntity.BaseBean transformFrom(@NonNull CommonModel.BaseBean model) {
        throw new Error("No-op");
    }

    /**
     * Implement {@inheritDoc}
     */
    @NonNull
    @Override
    public CommonModel.BaseBean transformTo(@NonNull CommonEntity.BaseBean entity) {
        // We may not use all of information, then we will remove some redundant information.
        return new CommonModel.BaseBean(entity.getId(), entity.getName());
    }
}
