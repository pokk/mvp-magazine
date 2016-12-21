package taiwan.no1.accounting.ui.fragments

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.widget.Button
import android.widget.TextView
import butterknife.bindView
import com.hwangjr.rxbus.RxBus
import taiwan.no1.accounting.R
import taiwan.no1.accounting.internal.di.annotations.PerFragment
import taiwan.no1.accounting.internal.di.components.FragmentComponent
import taiwan.no1.accounting.mvp.contracts.FirstContract
import taiwan.no1.accounting.ui.BaseFragment
import javax.inject.Inject

/**
 * @author Jieyi Wu
 * @version 0.0.1
 * @since 12/6/16
 */

@PerFragment
class FirstFragment: BaseFragment(), FirstContract.View {
    companion object Factory {
        // The key name of the fragment initialization parameters.
        private val ARG_PARAM_: String = "param_"

        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of fragment BlankFragment.
         */
        fun newInstance(arg1: String): FirstFragment {
            val fragment: FirstFragment = FirstFragment()
            val bundle: Bundle = Bundle()
            bundle.putString(ARG_PARAM_, arg1)
            fragment.arguments = bundle

            return fragment
        }
    }

    @Inject
    lateinit var presenter: FirstContract.Presenter

    private val tvShow by bindView<TextView>(R.id.tv_show)
    private val btnTest by bindView<Button>(R.id.btn_test)

    // The fragment initialization parameters.
    private var arg1: String? = null

    //region Fragment lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the arguments from the bundle here.
        this.arg1 = arguments?.getString(FirstFragment.ARG_PARAM_)
    }

    override fun onResume() {
        super.onResume()
        this.presenter.resume()
    }

    override fun onPause() {
        super.onPause()
        this.presenter.pause()
    }

    override fun onDestroy() {
        // After super.onDestroy() is executed, the presenter will be destroy. So the presenter should be
        // executed before super.onDestroy().
        this.presenter.destroy()
        super.onDestroy()
    }
    //endregion

    //region Initialization's order
    /**
     * Inject this fragment and [FragmentComponent].
     */
    override fun inject() {
        this.getComponent(FragmentComponent::class.java, null).inject(FirstFragment@ this)
    }

    /**
     * Set this fragment xml layout.
     *
     * @return [LayoutRes] xml layout.
     */
    @LayoutRes
    override fun inflateView(): Int = R.layout.fragment_main

    /**
     * Set the presenter initialization.
     */
    override fun initPresenter() {
        this.presenter.init(FirstFragment@ this)
    }

    /**
     * Initialization of this fragment. Set the listeners or view components' attributions.
     */
    override fun init() {
        this.btnTest.setOnClickListener { RxBus.get().post("test") }

        this.tvShow.text = "Hello World!!"
    }
    //endregion
}
