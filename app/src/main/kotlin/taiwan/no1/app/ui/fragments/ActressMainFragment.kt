package taiwan.no1.app.ui.fragments

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.StaggeredGridLayoutManager
import butterknife.bindView
import taiwan.no1.app.R
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.ActressMainContract
import taiwan.no1.app.mvp.models.CastListResModel
import taiwan.no1.app.mvp.models.CastListResModel.CastBriefBean
import taiwan.no1.app.ui.BaseFragment
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.ui.adapter.itemdecorator.GridSpacingItemDecorator
import taiwan.no1.app.ui.customize.LoadMoreRecyclerView
import java.util.*
import javax.inject.Inject

/**
 *
 * @author  Jieyi
 * @since   1/12/17
 */
@PerFragment
class ActressMainFragment: BaseFragment(), ActressMainContract.View, IMainFragment {
    //region Static initialization
    companion object Factory {
        // The key name of the fragment initialization parameters.
        // The key name of the fragment restore the status parameters. 
        private const val ARG_PARAM_INSTANCE_CASTS: String = "param_instance_casts"

        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of [fragment] ActressMainFragment.
         */
        fun newInstance(): ActressMainFragment = ActressMainFragment().apply {
            this.arguments = Bundle().apply {}
        }
    }
    //endregion

    @Inject
    lateinit var presenter: ActressMainContract.Presenter

    //region View variables
    private val rvCasts by bindView<LoadMoreRecyclerView>(R.id.rv_cast_list)
    //endregion

    //region Local variables
    private var castList: ArrayList<CastBriefBean>? = null
    private var pageIndex: Int = 1
    private var loading: Boolean = true

    // Get the arguments from the bundle here.
    //endregion

    //region Fragment lifecycle
    override fun onResume() {
        super.onResume()
        this.presenter.resume()
    }

    override fun onPause() {
        super.onPause()
        this.presenter.pause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelableArrayList(ARG_PARAM_INSTANCE_CASTS, this.castList)
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
        this.getComponent(FragmentComponent::class.java, null).inject(ActressMainFragment@ this)
    }

    /**
     * Set this fragment xml layout.
     *
     * @return [LayoutRes] xml layout.
     */
    @LayoutRes
    override fun inflateView(): Int = R.layout.fragment_actresses

    /**
     * Set the presenter initialization in [onCreateView].
     */
    override fun initPresenter() {
        this.presenter.init(ActressMainFragment@ this)
    }

    /**
     * Initialization of this fragment. Set the listeners or view components' attributions.
     *
     * @param savedInstanceState the previous fragment data status after the system calls [onPause].
     */
    override fun init(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            this.castList = savedInstanceState.getParcelableArrayList(ARG_PARAM_INSTANCE_CASTS)
        }
        if (null != this.castList) {
            (this.rvCasts.adapter as CommonRecyclerAdapter).models = this.castList!!
        }
        else {
            // FIXME: 2/3/17 The images are small than original size.
            this.rvCasts.let {
                it.layoutManager = StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL)
                it.setHasFixedSize(true)
                it.addItemDecoration(GridSpacingItemDecorator(2, 10, false))
                // Just give a empty adapter.
                it.adapter = CommonRecyclerAdapter(Collections.emptyList(), this.hashCode())
                it.setOnBottomListener(object: LoadMoreRecyclerView.OnBottomListener {
                    override fun onBottom() {
                        presenter.requestListCasts(pageIndex++)
                    }
                })
            }
            // Request the movie data.
            this.presenter.requestListCasts(pageIndex++)
        }
    }
    //endregion

    /**
     * Get the [Fragment] which is displaying now.
     *
     * @return current display [Fragment].
     */
    override fun getCurrentDisplayFragment(): Fragment = this

    //region View implementations
    override fun obtainCastBriefList(castList: List<CastListResModel.CastBriefBean>) {
        this.castList = ArrayList(if (null == this.castList || this.castList!!.isEmpty())
            castList
        else
            this.castList!! + castList)

        // Because the view pager will load the fragment first, if we just set the data directly, views won't
        // be showed. To avoid it, the adapter will be reset.
        this.castList?.let { (this.rvCasts.adapter as CommonRecyclerAdapter).addItem(it) }
        // Switch on loading new cast page.
        this.loading = true
    }
    //endregion
}
