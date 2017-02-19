package taiwan.no1.app.ui.activities

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import android.widget.RelativeLayout
import butterknife.bindView
import com.hwangjr.rxbus.RxBus
import com.hwangjr.rxbus.annotation.Subscribe
import com.hwangjr.rxbus.annotation.Tag
import com.roughike.bottombar.BottomBar
import com.touchin.constant.RxbusTag
import taiwan.no1.app.R
import taiwan.no1.app.internal.di.HasComponent
import taiwan.no1.app.internal.di.annotations.PerActivity
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.MainContract
import taiwan.no1.app.ui.BaseActivity
import taiwan.no1.app.ui.fragments.ActressMainFragment
import taiwan.no1.app.ui.fragments.IMainFragment
import taiwan.no1.app.ui.fragments.MovieMainFragment
import taiwan.no1.app.ui.fragments.TvMainFragment
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_FRAGMENT
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_SHARED_ELEMENTS
import taiwan.no1.app.ui.fragments.ViewPagerMainCtrlFragment.Factory.NAVIGATOR_ARG_TAG
import taiwan.no1.app.utilies.AppLog
import taiwan.no1.app.utilies.FragmentUtils
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
    private val rlMainContainer by bindView<RelativeLayout>(R.id.rl_main_container)
    private val bottombarMenu by bindView<BottomBar>(R.id.bb_menu)
    //endregion

    private var currentTag: String = ""

    //region Activity life cycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RxBus.get().register(MainActivity@ this)
        this.getComponent().inject(MainActivity@ this)
        this.presenter.init(MainActivity@ this)
        this.initFragment(savedInstanceState)
    }

    override fun onDestroy() {
        RxBus.get().unregister(MainActivity@ this)

        super.onDestroy()
    }
    //endregion

    override fun getFragmentComponent(obj: Any?): FragmentComponent = super.provideFragmentComponent(obj)

    override fun onBackPressed() {
        this.getCurrentPresentFragment().let {
            it.childFragmentManager?.let {
                // Pop back from current presenter fragment.
                if (!FragmentUtils.popFragment(it))
                    super.onBackPressed()
            }
        } ?: super.onBackPressed()
    }

    /**
     * Initialization of fragments.
     */
    private fun initFragment(savedInstanceState: Bundle?) {
        //apply background bitmap if we have one
        FragmentUtils.popAllFragment(this.supportFragmentManager)
        // FIXME: 2/19/17 Add too many fragments when change the tab. 這邊觀念怪怪的！怎麼push跟pop
        this.bottombarMenu.setOnTabSelectListener {
            AppLog.w(this.supportFragmentManager.backStackEntryCount)
            FragmentUtils.addFragment(this.supportFragmentManager, R.id.rl_main_container, when (it) {
                R.id.tab_movies -> MovieMainFragment.newInstance().apply { currentTag = this.javaClass.name }
                R.id.tab_tv_dramas -> TvMainFragment.newInstance().apply { currentTag = this.javaClass.name }
                R.id.tab_people -> ActressMainFragment.newInstance().apply { currentTag = this.javaClass.name }
                else -> MovieMainFragment.newInstance().apply { currentTag = this.javaClass.name }
            }, false)
        }
    }

    /**
     * Get the current present fragment as according to the bottom bar.
     *
     * @return [Fragment]
     */
    private fun getCurrentPresentFragment(): Fragment = (FragmentUtils.findFragmentByTag(this.supportFragmentManager,
            this.currentTag) as IMainFragment).getCurrentDisplayFragment()

    //region RxBus
    @Subscribe(tags = arrayOf(Tag(RxbusTag.FRAGMENT_CHILD_NAVIGATOR)))
    fun navigateFragment(mapArgs: HashMap<String, Any>) {
        val presentFragment: Fragment = this.getCurrentPresentFragment()
        val fragment: Fragment = mapArgs[NAVIGATOR_ARG_FRAGMENT] as Fragment
        val tag: Int = mapArgs[NAVIGATOR_ARG_TAG] as Int
        val shareElements: HashMap<View, String>? = mapArgs[NAVIGATOR_ARG_SHARED_ELEMENTS] as? HashMap<View, String>

        this.supportFragmentManager.fragments.forEachIndexed { i, fragment ->
            AppLog.d(i, fragment)
            fragment.fragmentManager.fragments.forEachIndexed { i, fragment ->
                AppLog.w(i, fragment)
            }
        }

        AppLog.w(tag)

        // FIXME: 2/19/17 Tag is difference from present fragment.
        // To avoid the same fragment but different hash code's fragment add the fragment.
        if (tag == presentFragment.hashCode()) {
            val fragmentManager: FragmentManager
            @IdRes val container: Int
            // Different fragments are different mechanism to show the views.
            if (presentFragment is ActressMainFragment) {
                fragmentManager = presentFragment.fragmentManager
                container = R.id.rl_main_container
            }
            else {
                fragmentManager = presentFragment.childFragmentManager
                container = R.id.main_container
            }
            // Do the transaction a fragment.
            FragmentUtils.addFragment(fragmentManager, container, fragment, true, shareElements)
        }
    }
    //endregion
}
