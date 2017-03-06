package taiwan.no1.app.mvp.presenters.fragment;

import org.jetbrains.annotations.NotNull;

import taiwan.no1.app.mvp.contracts.fragment.TvSeasonContract;

/**
 * @author Jieyi
 * @since 3/6/17
 */

public class TvSeasonPresenter extends BasePresenter<TvSeasonContract.View> implements TvSeasonContract.Presenter {
    @Override
    public void init(@NotNull TvSeasonContract.View view) {
        super.init(view);
    }
}
