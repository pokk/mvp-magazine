package taiwan.no1.accounting.data.mapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.accounting.data.entities.FakeEntity;
import taiwan.no1.accounting.mvp.models.FakeModel;

/**
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

@Singleton
public class FakeMapper {
    @Inject
    FakeMapper() {}

    public FakeEntity transform(FakeModel model) {
        return new FakeEntity(model.getName(), model.getAge(), model.getSex());
    }
}
