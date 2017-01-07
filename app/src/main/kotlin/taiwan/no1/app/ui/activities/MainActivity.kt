package taiwan.no1.app.ui.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import butterknife.bindView
import com.gigamole.navigationtabstrip.NavigationTabStrip
import com.roughike.bottombar.BottomBar
import taiwan.no1.app.R
import taiwan.no1.app.internal.di.HasComponent
import taiwan.no1.app.internal.di.annotations.PerActivity
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.MainContract
import taiwan.no1.app.ui.BaseActivity
import taiwan.no1.app.ui.adapter.MovieViewPager
import taiwan.no1.app.ui.fragments.MovieNowPlayingFragment
import taiwan.no1.app.ui.fragments.MoviePopularFragment
import taiwan.no1.app.ui.fragments.MovieTopRatedFragment
import taiwan.no1.app.ui.fragments.MovieUpComingFragment
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

    private val bottombarMenu by bindView<BottomBar>(R.id.bb_menu)
    private val vpContainer by bindView<ViewPager>(R.id.vp_container)
    private val ntsTabMenu by bindView<NavigationTabStrip>(R.id.nts_center)


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
        val fragmentList: List<Fragment> = ArrayList(arrayListOf(MoviePopularFragment.newInstance(),
                MovieNowPlayingFragment.newInstance(),
                MovieTopRatedFragment.newInstance(),
                MovieUpComingFragment.newInstance()))

        this.vpContainer.adapter = MovieViewPager(this.context(), this.supportFragmentManager, fragmentList)
        this.ntsTabMenu.setViewPager(this.vpContainer, 0)
    }
}
