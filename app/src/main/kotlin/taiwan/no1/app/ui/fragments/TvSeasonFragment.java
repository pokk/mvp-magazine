package taiwan.no1.app.ui.fragments;

import android.os.Bundle;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

import taiwan.no1.app.R;
import taiwan.no1.app.internal.di.components.FragmentComponent;
import taiwan.no1.app.mvp.contracts.fragment.TvSeasonContract;
import taiwan.no1.app.ui.BaseFragment;

/**
 * @author Jieyi
 * @since 3/6/17
 */

public class TvSeasonFragment extends BaseFragment implements TvSeasonContract.View {
    private static final String ARG_PARAM_TV_ID = "param_tv_id";
    private static final String ARG_PARAM_SEASON_NUMBER = "param_tv_season_number";
    private static final String ARG_PARAM_TV_TITLE = "param_tv_title";
    private static final String ARG_PARAM_FROM_ID = "param_tv_from_fragment";

    @Inject TvSeasonContract.Presenter presenter;
    private String id;
    private String tvTitle;
    private String seasonNumber;
    private int argFromFragment;

    public TvSeasonFragment() {
    }

    public static TvSeasonFragment newInstance(@NotNull final String id, @NotNull final String seasonNumber,
                                               @NotNull final String tvTitle, final int from) {
        TvSeasonFragment fragment = new TvSeasonFragment();
        Bundle bundle = new Bundle();

        bundle.putString(ARG_PARAM_TV_ID, id);
        bundle.putString(ARG_PARAM_TV_TITLE, tvTitle);
        bundle.putString(ARG_PARAM_SEASON_NUMBER, seasonNumber);
        bundle.putInt(ARG_PARAM_FROM_ID, from);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.presenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.presenter.pause();
    }

    @Override
    public void onDestroy() {
        this.presenter.destroy();
        super.onDestroy();
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        this.id = this.getArguments().getString(ARG_PARAM_TV_ID);
        this.tvTitle = this.getArguments().getString(ARG_PARAM_TV_TITLE);
        this.seasonNumber = this.getArguments().getString(ARG_PARAM_SEASON_NUMBER);
        this.argFromFragment = this.getArguments().getInt(ARG_PARAM_FROM_ID);
    }

    @Override
    protected void inject() {
        this.getComponent(FragmentComponent.class).inject(this);
    }

    @Override
    protected int inflateView() {
        return R.layout.fragment_tv_season_detail;
    }

    @Override
    protected void initPresenter() {
        this.presenter.init(this);
    }
}
