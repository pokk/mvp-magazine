package taiwan.no1.app.ui.activities

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.transition.TransitionInflater
import android.util.SparseArray
import android.view.View
import android.widget.RelativeLayout
import com.devrapid.kotlinknifer.addFragment
import com.devrapid.kotlinknifer.logd
import com.devrapid.kotlinknifer.logi
import com.devrapid.kotlinknifer.logw
import com.devrapid.kotlinknifer.popFragment
import com.devrapid.kotlinknifer.removeRecursiveFragment
import com.hwangjr.rxbus.RxBus
import com.hwangjr.rxbus.annotation.Subscribe
import com.hwangjr.rxbus.annotation.Tag
import com.roughike.bottombar.BottomBar
import com.touchin.constant.RxbusTag
import kotlinx.android.synthetic.main.activity_main.btn
import kotterknife.bindView
import taiwan.no1.app.App
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
import javax.inject.Inject

/**
 * @author  Jieyi
 * @since   12/6/16
 */

@PerActivity
class MainActivity : BaseActivity(), MainContract.View, HasComponent<FragmentComponent> {
    @Inject
    lateinit var presenter: MainContract.Presenter

    //region View variables
    private val rlMainContainer by bindView<RelativeLayout>(R.id.rl_main_container)
    private val bbMenu by bindView<BottomBar>(R.id.bb_menu)
    //endregion

    lateinit var currentTag: String
    private var isFirst: Boolean = true  // Avoid when the [BottomBarMenu] first init, it will add fragment twice times.
    private val fragments: SparseArray<Fragment> = SparseArray<Fragment>().also {
        it.put(R.id.tab_movies, MovieMainFragment.newInstance())
        it.put(R.id.tab_tv_dramas, TvMainFragment.newInstance())
        it.put(R.id.tab_people, ActressMainFragment.newInstance())
    }

    //region Activity life cycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RxBus.get().register(MainActivity@ this)
        this.getComponent().inject(MainActivity@ this)
        this.presenter.init(MainActivity@ this)
        this.initFragment(savedInstanceState)

        logw(this.fragments)

        /***
         *              supportFragmentManager.fragment
         *                                      supportFragmentManager.fragment(inside fragment list)
         *                                                                      supportFragmentManager.fragment(inside fragment list).
         *                                                                      childFragmentManager.fragments
         *
         *  Parent              Child                   GrandChild(only one)
         *
         *             ———  MovieMainFragment   --- Each of MovieListFragment   --- Deeper GrandChildren
         *           ∕
         * Activity ——————  TvMainFragment      --- Each of TvListFragment      --- Deeper GrandChildren
         *           ﹨
         *             ———  PeopleMainFragment                                  --- Deep GrandChildren
         *
         * When we switch to other pager, the grandchild will be destroyed and create a new grandchild.
         *
         * ** Test code and this fragment concept.
         *
         * // FIXME: 3/24/17 Current problem is that after rotating, the xxListFragment will gone. Cause Deeper Fragments couldn't push to the stack lately.
         */
        // NOTE: 3/26/17 DEBUG MODE.
        btn.setOnClickListener {
            logd("====================================")
            logi(this)
            logw(this.supportFragmentManager.fragments)
            logd(this.getCurrentPresentFragment())
//            FragmentUtils.showAllFragments(this.getCurrentPresentFragment().fragmentManager)
        }
    }

    override fun onResume() {
        super.onResume()
        // FIXED: 4/26/17 Obtain the container size for setting the loading size.
        this.rlMainContainer.viewTreeObserver.addOnGlobalLayoutListener {
            App.containerHeight = this.rlMainContainer.measuredHeight
            App.containerWidth = this.rlMainContainer.measuredWidth
        }

        // FIXED: 3/3/17 Avoiding rotating the screen, the bottom bar's listener will reset the fragment stack. It causes
        // FIXED: when we rotate, the view always goes to list view.
        this.isFirst = false
    }

    override fun onDestroy() {
        RxBus.get().unregister(MainActivity@ this)

        super.onDestroy()
    }
    //endregion

    override fun getFragmentComponent(): FragmentComponent = super.provideFragmentComponent()

    override fun onBackPressed() {
        this.getCurrentPresentFragment().let {
            it.childFragmentManager?.let {
                // Pop back from current presenter fragment.
                if (!it.popFragment())
                    super.onBackPressed()
            }
        } ?: super.onBackPressed()
    }

    /**
     * Initialization of fragments.
     */
    private fun initFragment(savedInstanceState: Bundle?) {
        if (null == savedInstanceState) {
            this.supportFragmentManager.addFragment(R.id.rl_main_container, fragments[R.id.tab_movies].also {
                this.currentTag = it.javaClass.name
            })
        }

        // When rotating the screen or fragment recreate, current tag need to be re-set. And avoiding the currentTag
        // isn't set when back button is pressed.
//        this.supportFragmentManager.fragments?.get(0)?.let { this.currentTag = it.javaClass.name }

        this.bbMenu.setOnTabSelectListener {
            // TODO: 2/21/17 Here will waste memory. Becz of repeating creating and removing every single switching.
            if (!isFirst) {
                // Clear all fragments from the fragment manager.
                this.supportFragmentManager.removeRecursiveFragment()
                // Add a new fragment category.
                this.supportFragmentManager.addFragment(R.id.rl_main_container, this.fragments[it].also {
                    this.currentTag = it.javaClass.name
                }, false)
            }
        }
    }

    /**
     * Get the current present fragment as according to the bottom bar.
     *
     * @return [Fragment]
     */
    private fun getCurrentPresentFragment(): Fragment = (this.supportFragmentManager.findFragmentByTag(this.currentTag) as IMainFragment).
        getCurrentDisplayFragment()

    //region RxBus
    @Subscribe(tags = arrayOf(Tag(RxbusTag.FRAGMENT_CHILD_NAVIGATOR)))
    fun navigateFragment(mapArgs: HashMap<String, Any>) {
        val presentFragment: Fragment = this.getCurrentPresentFragment()
        val fragment: Fragment = mapArgs[NAVIGATOR_ARG_FRAGMENT] as Fragment
        val tag: Int = mapArgs[NAVIGATOR_ARG_TAG] as Int
        val shareElements: HashMap<View, String>? = mapArgs[NAVIGATOR_ARG_SHARED_ELEMENTS] as? HashMap<View, String>

        // FIXME: 3/3/17 When actress view is rotated, the fragment manager will be null.

        // TODO: 4/17/17 Add the shared elements effective, buy now it's no effective yet.
        presentFragment.sharedElementReturnTransition = TransitionInflater.from(App.getAppContext()).inflateTransition(R.transition.default_transition)
        presentFragment.exitTransition = TransitionInflater.from(App.getAppContext()).inflateTransition(android.R.transition.no_transition)
        fragment.sharedElementEnterTransition = TransitionInflater.from(App.getAppContext()).inflateTransition(R.transition.default_transition)
        fragment.enterTransition = TransitionInflater.from(App.getAppContext()).inflateTransition(android.R.transition.no_transition)

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
            fragmentManager.addFragment(container, fragment, true, shareElements)
        }
    }
    //endregion
}
