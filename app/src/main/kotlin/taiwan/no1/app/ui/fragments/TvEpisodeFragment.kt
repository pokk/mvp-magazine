package taiwan.no1.app.ui.fragments

import android.os.Bundle
import android.support.annotation.LayoutRes
import taiwan.no1.app.R
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.fragment.TvEpisodeContract
import taiwan.no1.app.ui.BaseFragment
import javax.inject.Inject

/**
 *
 * @author  Jieyi
 * @since   3/6/17
 */
@PerFragment
class TvEpisodeFragment: BaseFragment(), TvEpisodeContract.View {
    //region Static initialization
    companion object Factory {
        // The key name of the fragment initialization parameters.
        private const val ARG_PARAM_TV_ID: String = "param_tv_id"
        private const val ARG_PARAM_SEASON_NUMBER: String = "param_season_number"
        private const val ARG_PARAM_EPISODE_NUMBER: String = "param_episode_number"
        private const val ARG_PARAM_TV_FROM_FRAGMENT: String = "param_from_fragment"

        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of [fragment] TvEpisodeFragment.
         */
        fun newInstance(id: String, seasonNumber: String, episodeNumber: String, from: Int): TvEpisodeFragment =
                TvEpisodeFragment().also {
                    it.arguments = Bundle().also {
                        it.putString(ARG_PARAM_TV_ID, id)
                        it.putString(ARG_PARAM_TV_ID, seasonNumber)
                        it.putString(ARG_PARAM_TV_ID, episodeNumber)
                        it.putInt(ARG_PARAM_TV_ID, from)
                    }
                }
    }
    //endregion

    @Inject
    lateinit var presenter: TvEpisodeContract.Presenter

    // Get the arguments from the bundle here.
    private val argId: String by lazy { this.arguments.getString(ARG_PARAM_TV_ID) }
    private val argSeasonNumber: String by lazy { this.arguments.getString(ARG_PARAM_SEASON_NUMBER) }
    private val argEpisodeNumber: String by lazy { this.arguments.getString(ARG_PARAM_EPISODE_NUMBER) }
    private val argFromFragment: Int by lazy { this.arguments.getInt(ARG_PARAM_TV_FROM_FRAGMENT) }

    //region Fragment lifecycle
    override fun onResume() {
        super.onResume()
        this.presenter.resume()
    }

    override fun onPause() {
        super.onPause()
        this.presenter.pause()
    }

    override fun onDestroy() {
        // After super.onDestroy() is executed, the presenter will be destroy. So the presenter should be
        // executed before super.onDestroy().
        this.presenter.destroy()
        super.onDestroy()
    }
    //endregion

    //region Initialization's order
    /**
     * Inject this fragment and [FragmentComponent].
     */
    override fun inject() {
        this.getComponent(FragmentComponent::class.java).inject(TvEpisodeFragment@ this)
    }

    /**
     * Set this fragment xml layout.
     *
     * @return [LayoutRes] xml layout.
     */
    @LayoutRes
    override fun inflateView(): Int = R.layout.fragment_tv_episode_detail

    /**
     * Set the presenter initialization in [onCreateView].
     */
    override fun initPresenter() {
        this.presenter.init(TvEpisodeFragment@ this)
    }

    /**
     * Initialization of this fragment. Set the listeners or view components' attributions.
     *
     * @param savedInstanceState the previous fragment data status after the system calls [onPause].
     */
    override fun init(savedInstanceState: Bundle?) {
    }
    //endregion
}
