package taiwan.no1.accounting.ui.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import taiwan.no1.accounting.R
import taiwan.no1.accounting.internal.di.HasComponent
import taiwan.no1.accounting.internal.di.components.UseCaseComponent
import taiwan.no1.accounting.ui.BaseActivity
import taiwan.no1.accounting.ui.fragments.MainFragment

class MainActivity: BaseActivity(), HasComponent<UseCaseComponent> {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment(savedInstanceState)
    }

    override fun getComponent(obj: Any?): UseCaseComponent =
            UseCaseComponent.Initializer.init(getApplicationComponent(), this)

    fun initFragment(savedInstanceState: Bundle?) {
        //apply background bitmap if we have one

        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, getFragment(), false, null, null)
        }
    }

    fun getFragment(): Fragment {
        return when (-1) {
            R.layout.fragment_main -> {
                val fragment = MainFragment.newInstance("")
                fragment
            }
            else -> MainFragment.newInstance("")
        }
    }
}
