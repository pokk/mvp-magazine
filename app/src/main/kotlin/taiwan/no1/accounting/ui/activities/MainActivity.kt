package taiwan.no1.accounting.ui.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import taiwan.no1.accounting.R
import taiwan.no1.accounting.internal.di.HasComponent
import taiwan.no1.accounting.internal.di.components.FragmentComponent
import taiwan.no1.accounting.mvp.contracts.MainContract
import taiwan.no1.accounting.ui.BaseActivity
import taiwan.no1.accounting.ui.fragments.FirstFragment
import javax.inject.Inject

/**
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

class MainActivity: BaseActivity(), MainContract.View, HasComponent<FragmentComponent> {
    @Inject
    lateinit var presenter: MainContract.Presenter

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
            addFragment(R.id.fragmentContainer, getFragment(), false, null, null)
        }
    }

    fun getFragment(): Fragment {
        return when (-1) {
            R.layout.fragment_main -> {
                val fragment = FirstFragment.newInstance("")
                fragment
            }
            else -> FirstFragment.newInstance("")
        }
    }
}
