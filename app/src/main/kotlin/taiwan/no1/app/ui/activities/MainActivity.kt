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
import taiwan.no1.app.ui.fragments.TvListFragment
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

    private var currentTag: String = ""
    //endregion

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
                if (0 < it.backStackEntryCount)
                    it.popBackStack()
                else
                    super.onBackPressed()
            }
        } ?: super.onBackPressed()
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
            this.addFragment(R.id.rl_main_container, when (it) {
                R.id.tab_movies -> MovieMainFragment.newInstance().apply { currentTag = this.javaClass.name }
                R.id.tab_tv_dramas -> TvListFragment.newInstance().apply { currentTag = this.javaClass.name }
                R.id.tab_people -> ActressMainFragment.newInstance().apply { currentTag = this.javaClass.name }
                else -> MovieMainFragment.newInstance().apply { currentTag = this.javaClass.name }
            }, false)
        }
    }

    /**
     * Get the current present fragment as according to the bottom bar.
     *
     * @return
     */
    private fun getCurrentPresentFragment(): Fragment =
            (this.findFragmentByTag(this.currentTag) as IMainFragment).getCurrentDisplayFragment()


    //region RxBus
    @Subscribe(tags = arrayOf(Tag(RxbusTag.FRAGMENT_CHILD_NAVIGATOR)))
    fun navigateFragment(mapArgs: HashMap<String, Any>) {
        val presentFragment: Fragment = this.getCurrentPresentFragment()
        val fragment: Fragment = mapArgs[MovieMainFragment.NAVIGATOR_ARG_FRAGMENT] as Fragment
        val tag: Int = mapArgs[MovieMainFragment.NAVIGATOR_ARG_TAG] as Int
        val shareElements: HashMap<View, String>? = mapArgs[MovieMainFragment.NAVIGATOR_ARG_SHARED_ELEMENTS] as? HashMap<View, String>

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
            fragmentManager.beginTransaction().apply {
                this.replace(container, fragment, fragment.javaClass.name)
                this.addToBackStack(fragment.javaClass.name)
                shareElements?.forEach { addSharedElement(it.key, it.value) }
            }.commit()
        }
    }
    //endregion
}
