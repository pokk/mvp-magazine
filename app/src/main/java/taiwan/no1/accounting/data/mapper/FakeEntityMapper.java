package taiwan.no1.accounting.data.mapper;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.accounting.data.entities.FakeEntity;
import taiwan.no1.accounting.domain.mapper.BaseEntityMapper;
import taiwan.no1.accounting.mvp.models.FakeModel;

/**
 * Mapper class used to transform between {@link FakeModel} (in the kotlin layer) and {@link FakeEntity}
 * (in the data layer).
 *
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

@Singleton
public class FakeEntityMapper implements BaseEntityMapper<FakeModel, FakeEntity> {
    @Inject
    FakeEntityMapper() {}

    @Override
    @NonNull
    public FakeEntity transformFrom(@NonNull FakeModel model) {
        return new FakeEntity(model.getName(), model.getAge(), model.getSex());
    }

    @Override
    @NonNull
    public FakeModel transformTo(@NonNull FakeEntity entity) {
        return new FakeModel(entity.getName(), entity.getAge(), entity.getSex());
    }
}
