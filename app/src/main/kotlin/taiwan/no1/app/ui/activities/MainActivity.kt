package taiwan.no1.app.ui.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.view.View
import butterknife.bindView
import com.gigamole.navigationtabstrip.NavigationTabStrip
import com.roughike.bottombar.BottomBar
import taiwan.no1.app.R
import taiwan.no1.app.data.repositiry.DataRepository
import taiwan.no1.app.internal.di.HasComponent
import taiwan.no1.app.internal.di.annotations.PerActivity
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.MainContract
import taiwan.no1.app.ui.BaseActivity
import taiwan.no1.app.ui.adapter.MovieViewPager
import taiwan.no1.app.ui.fragments.MovieListFragment
import java.util.*
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
    private val bottombarMenu by bindView<BottomBar>(R.id.bb_menu)
    private val includeContainer by bindView<View>(R.id.include_container)
    private val vpContainer by bindView<ViewPager>(R.id.vp_container)
    private val ntsTabMenu by bindView<NavigationTabStrip>(R.id.nts_center)
    //endregion

    private val fragmentList: List<Fragment> = ArrayList(arrayListOf(
            MovieListFragment.newInstance(DataRepository.Movies.POPULAR),
            MovieListFragment.newInstance(DataRepository.Movies.NOW_PLAYING),
            MovieListFragment.newInstance(DataRepository.Movies.TOP_RATED),
            MovieListFragment.newInstance(DataRepository.Movies.UP_COMING)))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.getComponent().inject(MainActivity@ this)
        this.presenter.init(MainActivity@ this)

        initFragment(savedInstanceState)
    }

    override fun getFragmentComponent(obj: Any?): FragmentComponent = super.provideFragmentComponent(obj)

    fun initFragment(savedInstanceState: Bundle?) {
        //apply background bitmap if we have one

        if (savedInstanceState == null) {
//            addFragment(R.id.fragment_container, MoviePopularFragment.newInstance(), false, null, null)
        }

        this.vpContainer.adapter = MovieViewPager(this.context(),
                this.supportFragmentManager,
                this.fragmentList)
        this.ntsTabMenu.setViewPager(this.vpContainer, 0)
//        this.vpContainer.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
//            override fun onPageScrollStateChanged(state: Int) {
//                AppLog.w(state)
//            }
//
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//                AppLog.w(position)
//            }
//
//            override fun onPageSelected(position: Int) {
//                AppLog.w(position)
//            }
//        })
    }

    override fun onBackPressed() {
        val currentFragment: Fragment = this.fragmentList[this.vpContainer.currentItem]
        val currentChildFragmentManager: FragmentManager = currentFragment.childFragmentManager

        // Pop back from current presenter fragment.
        if (0 < currentChildFragmentManager.backStackEntryCount) {
            currentChildFragmentManager.popBackStack()
        }
        else
            super.onBackPressed()
    }
}
