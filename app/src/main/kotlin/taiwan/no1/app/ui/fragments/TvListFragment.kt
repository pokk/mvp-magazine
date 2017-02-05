package taiwan.no1.app.ui.fragments

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import butterknife.bindView
import com.jakewharton.rxbinding.support.v7.widget.scrollEvents
import taiwan.no1.app.R
import taiwan.no1.app.data.source.CloudDataStore
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.TvListContract
import taiwan.no1.app.mvp.models.TvBriefModel
import taiwan.no1.app.ui.BaseFragment
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.ui.adapter.itemdecorator.GridSpacingItemDecorator
import java.util.*
import javax.inject.Inject

/**
 *
 * @author  Jieyi
 * @since   1/7/17
 */
@PerFragment
class TvListFragment: BaseFragment(), TvListContract.View {
    //region Static initialization
    companion object Factory {
        // The key name of the fragment initialization parameters.
        private const val ARG_PARAM_CATEGORY: String = "param_tv_category"
        // The key name of the fragment restore the status parameters. 
        private const val ARG_PARAM_INSTANCE_TVS: String = "param_instance_tvs"

        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of [fragment] TVListFragment.
         */
        fun newInstance(category: CloudDataStore.Tvs): TvListFragment = TvListFragment().apply {
            this.arguments = Bundle().apply {
                this.putSerializable(ARG_PARAM_CATEGORY, category)
            }
        }
    }
    //endregion

    @Inject
    lateinit var presenter: TvListContract.Presenter

    //region View variables
    private val rvTvs by bindView<RecyclerView>(R.id.rv_tv_list)
    //endregion

    private var tvList: ArrayList<TvBriefModel>? = null
    private var maxPageIndex: Int = 1
    private var pageIndex: Int = 1
    private var loading: Boolean = true

    // Get the arguments from the bundle here.
    private val argTvCategory: CloudDataStore.Tvs by lazy {
        this.arguments.getSerializable(ARG_PARAM_CATEGORY) as CloudDataStore.Tvs
    }

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

        outState.putParcelableArrayList(ARG_PARAM_INSTANCE_TVS, this.tvList)
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
        this.getComponent(FragmentComponent::class.java, null).inject(TVListFragment@ this)
    }

    /**
     * Set this fragment xml layout.
     *
     * @return [LayoutRes] xml layout.
     */
    @LayoutRes
    override fun inflateView(): Int = R.layout.fragment_tv_list

    /**
     * Set the presenter initialization in [onCreateView].
     */
    override fun initPresenter() {
        this.presenter.init(TVListFragment@ this)
    }

    /**
     * Initialization of this fragment. Set the listeners or view components' attributions.
     *
     * @param savedInstanceState the previous fragment data status after the system calls [onPause].
     */
    override fun init(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            this.tvList = savedInstanceState.getParcelableArrayList(ARG_PARAM_INSTANCE_TVS)
        }

        if (null != this.tvList) {
            (this.rvTvs.adapter as CommonRecyclerAdapter).models = this.tvList!!
        }
        else {
            this.rvTvs.layoutManager = StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL).apply {
                rvTvs.scrollEvents().subscribe {
                    if (0 < it.dy()) {
                        val visibleItemCount: Int = this.childCount
                        val totalItemCount: Int = this.itemCount
                        val pastVisibleItems: Int = this.findFirstVisibleItemPositions(null)[0]

                        if (loading && visibleItemCount + pastVisibleItems >= totalItemCount && 0 < totalItemCount) {
                            loading = false
                            // TODO: 2017/01/10 Limit the max page. 
                            presenter.requestListTvs(argTvCategory, pageIndex++)
                        }
                    }
                }
            }
            this.rvTvs.setHasFixedSize(true)
            this.rvTvs.addItemDecoration(GridSpacingItemDecorator(2, 10, false))
            // Just give a empty adapter.
            this.rvTvs.adapter = CommonRecyclerAdapter(Collections.emptyList(), this.hashCode())

            // Request the movie data.
            this.argTvCategory.let { this.presenter.requestListTvs(it, pageIndex++) }
        }
    }
    //endregion

    //region View implementations
    override fun obtainTvBriefList(tvList: List<TvBriefModel>) {
        this.tvList = ArrayList(if (null == this.tvList || this.tvList!!.isEmpty())
            tvList
        else
            this.tvList!! + tvList)

        // Because the view pager will load the fragment first, if we just set the data directly, views won't
        // be showed. To avoid it, the adapter will be reset.
        this.tvList?.let { (this.rvTvs.adapter as CommonRecyclerAdapter).addItem(it) }
        // Switch on loading new movie page.
        this.loading = true
    }
    //endregion
}
