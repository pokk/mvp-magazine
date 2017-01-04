package taiwan.no1.app.data.mapper;

import android.support.annotation.NonNull;

import com.innahema.collections.query.queriables.Queryable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import taiwan.no1.app.data.entities.CreditsEntity;
import taiwan.no1.app.domain.mapper.IBeanMapper;
import taiwan.no1.app.mvp.models.CreditsModel;

/**
 * @author jieyi
 * @version 0.0.1
 * @since 2017/01/04
 */

@Singleton
public class CreditsMapper implements IBeanMapper<CreditsModel, CreditsEntity> {

    @Inject
    public CreditsMapper() {
    }

    @NonNull
    @Override
    @Deprecated
    public CreditsEntity transformFrom(@NonNull CreditsModel model) {
        throw new Error("No-op");
    }

    @NonNull
    @Override
    public CreditsModel transformTo(@NonNull CreditsEntity entity) {
        List<CreditsModel.CastBean> castBeen = Queryable.from(entity.getCast())
                                                        .map(data -> new CreditsModel.CastBean(data.isAdult(),
                                                                                               data.getCharacter(),
                                                                                               data.getCredit_id(),
                                                                                               data.getId(),
                                                                                               data.getOriginal_title(),
                                                                                               data.getPoster_path(),
                                                                                               data.getRelease_date(),
                                                                                               data.getTitle(),
                                                                                               data.getMedia_type(),
                                                                                               data.getEpisode_count(),
                                                                                               data.getFirst_air_date(),
                                                                                               data.getName(),
                                                                                               data.getOriginal_name()))
                                                        .toList();
        List<CreditsModel.CrewBean> crewBeen = Queryable.from(entity.getCrew())
                                                        .map(data -> new CreditsModel.CrewBean(data.isAdult(),
                                                                                               data.getCredit_id(),
                                                                                               data.getDepartment(),
                                                                                               data.getId(),
                                                                                               data.getJob(),
                                                                                               data.getOriginal_title(),
                                                                                               data.getPoster_path(),
                                                                                               data.getRelease_date(),
                                                                                               data.getTitle(),
                                                                                               data.getMedia_type()))
                                                        .toList();

        return new CreditsModel(castBeen, crewBeen);
    }
}
