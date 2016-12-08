package taiwan.no1.accounting.ui.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import retrofit2.Retrofit
import taiwan.no1.accounting.R
import taiwan.no1.accounting.internal.di.HasComponent
import taiwan.no1.accounting.internal.di.components.DaggerUseCaseComponent
import taiwan.no1.accounting.internal.di.components.UseCaseComponent
import taiwan.no1.accounting.internal.di.modules.UseCaseModule
import taiwan.no1.accounting.ui.BaseActivity
import taiwan.no1.accounting.ui.fragments.MainFragment
import javax.inject.Inject

class MainActivity: BaseActivity(), HasComponent<UseCaseComponent> {
    @Inject
    lateinit var retrofit: Retrofit
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment(savedInstanceState)
    }

    override fun getComponent(obj: Any?): UseCaseComponent {
        return DaggerUseCaseComponent.builder().
                appComponent(getApplicationComponent()).
                useCaseModule(if (null == obj) UseCaseModule() else UseCaseModule(obj as String)).
                build()
    }

    fun initFragment(savedInstanceState: Bundle?) {
        //apply background bitmap if we have one
//        if (intent.hasExtra("bitmap_id")) {
//            fragmentBackground.background = BitmapDrawable(resources, BitmapUtil.fetchBitmapFromIntent(intent))
//        }

        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, getFragment(), false, null, null)
        }
    }

    fun getFragment(): Fragment {
//        var fragmentResourceId = intent.getIntExtra(Navigator.FRAGMENT_RESOURCE_ID, R.layout.fragment_playone_list)

        return when (-1) {
            R.layout.fragment_main -> {
                val fragment = MainFragment.newInstance()
                fragment
            }
            else -> MainFragment.newInstance()
        }
    }
}
