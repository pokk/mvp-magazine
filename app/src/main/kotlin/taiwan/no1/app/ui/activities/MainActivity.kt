package taiwan.no1.app.ui.activities

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.SparseArray
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
import taiwan.no1.app.utilies.FragmentUtils
import java.util.*
import javax.inject.Inject

/**
 * @author  Jieyi
 * @since   12/6/16
 */

@PerActivity
class MainActivity: BaseActivity(), MainContract.View, HasComponent<FragmentComponent> {
    @Inject
    lateinit var presenter: MainContract.Presenter

    //region View variables
    private val rlMainContainer by bindView<RelativeLayout>(R.id.rl_main_container)
    private val bottombarMenu by bindView<BottomBar>(R.id.bb_menu)
    //endregion

    lateinit var currentTag: String
    private val fragments: SparseArray<Fragment> = SparseArray<Fragment>().apply {
        this.put(R.id.tab_movies, MovieMainFragment.newInstance())
        this.put(R.id.tab_tv_dramas, TvMainFragment.newInstance())
        this.put(R.id.tab_people, ActressMainFragment.newInstance())
    }

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
        if (null == savedInstanceState) {
            FragmentUtils.addFragment(this.supportFragmentManager, R.id.rl_main_container,
                    fragments[R.id.tab_movies].apply { this@MainActivity.currentTag = this.javaClass.name })
        }

        var isFirst = true  // Avoid when the [BottomBarMenu] first init, it will add fragment twice times. 
        // When rotating the screen or fragment recreate, current tag need to be re-set.
        this.supportFragmentManager.fragments?.get(0)?.let { this@MainActivity.currentTag = it.javaClass.name }

        this.bottombarMenu.setOnTabSelectListener {
            // TODO: 2/21/17 Here will waste memory. Becz of repeating creating and removing every single switching.
            if (!isFirst) {
                // Clear all fragments from the fragment manager.
                FragmentUtils.removeRecursiveFragment(this.supportFragmentManager)
                // Add a new fragment category.
                FragmentUtils.addFragment(this.supportFragmentManager, R.id.rl_main_container,
                        this.fragments[it].apply { this@MainActivity.currentTag = this.javaClass.name }, false)
            }
            isFirst = false
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
