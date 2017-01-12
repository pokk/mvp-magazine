package taiwan.no1.app.ui.activities

import android.os.Bundle
import android.widget.RelativeLayout
import butterknife.bindView
import com.roughike.bottombar.BottomBar
import taiwan.no1.app.R
import taiwan.no1.app.internal.di.HasComponent
import taiwan.no1.app.internal.di.annotations.PerActivity
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.MainContract
import taiwan.no1.app.ui.BaseActivity
import taiwan.no1.app.ui.fragments.ActressMainFragment
import taiwan.no1.app.ui.fragments.MovieMainFragment
import taiwan.no1.app.ui.fragments.TvListFragment
import taiwan.no1.app.utilies.AppLog
import javax.inject.Inject

/**
 * @author Jieyi
 * @since 12/6/16
 */

@PerActivity
class MainActivity: BaseActivity(), MainContract.View, HasComponent<FragmentComponent> {
    @Inject
    lateinit var presenter: MainContract.Presenter

    //region View variables
    private val rlMainContainer by bindView<RelativeLayout>(R.id.rl_main_container)
    private val bottombarMenu by bindView<BottomBar>(R.id.bb_menu)
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.getComponent().inject(MainActivity@ this)
        this.presenter.init(MainActivity@ this)
        this.initFragment(savedInstanceState)
    }

    override fun getFragmentComponent(obj: Any?): FragmentComponent = super.provideFragmentComponent(obj)

    override fun onBackPressed() {
        (this.findFragmentByTag(MovieMainFragment::class.java.name) as MovieMainFragment).
                getCurrentDisplayFragment().childFragmentManager?.let {
            // Pop back from current presenter fragment.
            if (0 < it.backStackEntryCount) {
                it.popBackStack()
            }
            else
                super.onBackPressed()
        }
    }

    /**
     * Initialization of fragments.
     */
    private fun initFragment(savedInstanceState: Bundle?) {
        //apply background bitmap if we have one

        if (savedInstanceState == null) {
//            addFragment(R.id.fragment_container, MoviePopularFragment.newInstance(), false, null, null)
        }

        this.popAllFragment()
        this.bottombarMenu.setOnTabSelectListener {
            when (it) {
                R.id.tab_movies ->
                    this.addFragment(R.id.rl_main_container, MovieMainFragment.newInstance(), false)
                R.id.tab_tv_dramas ->
                    this.addFragment(R.id.rl_main_container, TvListFragment.newInstance(), false)
                R.id.tab_people ->
                    this.addFragment(R.id.rl_main_container, ActressMainFragment.newInstance(), false)
                else ->
                    AppLog.i()
            }
        }
    }
}
