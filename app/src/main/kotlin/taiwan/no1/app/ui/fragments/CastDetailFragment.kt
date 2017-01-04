package taiwan.no1.app.ui.fragments

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewStub
import android.widget.ImageView
import butterknife.bindView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.intrusoft.squint.DiagonalView
import kotlinx.android.synthetic.main.fragment_cast_detail.*
import taiwan.no1.app.R
import taiwan.no1.app.api.config.MovieDBConfig
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.CastDetailContract
import taiwan.no1.app.mvp.models.CastDetailModel
import taiwan.no1.app.mvp.models.CreditsModel
import taiwan.no1.app.ui.BaseFragment
import taiwan.no1.app.ui.adapter.CastRelatedMovieAdapter
import taiwan.no1.app.ui.itemdecorator.MovieHorizontalItemDecorator
import javax.inject.Inject

/**
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   1/1/17
 */

@PerFragment
class CastDetailFragment: BaseFragment(), CastDetailContract.View {
    companion object Factory {
        // The key name of the fragment initialization parameters.
        private val ARG_PARAM_ID: String = "param_cast_id"

        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of [fragment] CastDetailFragment.
         */
        fun newInstance(id: String): CastDetailFragment {
            val fragment: CastDetailFragment = CastDetailFragment()
            val bundle: Bundle = Bundle()
            bundle.putString(ARG_PARAM_ID, id)
            fragment.arguments = bundle

            return fragment
        }
    }

    @Inject
    lateinit var presenter: CastDetailContract.Presenter

    private val ivDropPoster by bindView<DiagonalView>(R.id.dv_poster)
    private val ivPersonPoster by bindView<ImageView>(R.id.iv_person)
    private val stubRelated by bindView<ViewStub>(R.id.stub_related)
    private val rvRelated by bindView<RecyclerView>(R.id.rv_related)

    // The fragment initialization parameters.
    private var argId: String? = null

    //region Fragment lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the arguments from the bundle here.
        this.argId = arguments?.getString(CastDetailFragment.ARG_PARAM_ID)
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
        this.getComponent(FragmentComponent::class.java, null).inject(CastDetailFragment@ this)
    }

    /**
     * Set this fragment xml layout.
     *
     * @return [LayoutRes] xml layout.
     */
    @LayoutRes
    override fun inflateView(): Int = R.layout.fragment_cast_detail

    /**
     * Set the presenter initialization.
     */
    override fun initPresenter() {
        this.presenter.init(CastDetailFragment@ this)
    }

    /**
     * Initialization of this fragment. Set the listeners or view components' attributions.
     */
    override fun init() {
        this.presenter.requestCastDetail(this.argId!!.toInt())
    }
    //endregion

    override fun showCastDetail(castDetailModel: CastDetailModel) {
        Glide.with(this.context.applicationContext).
                load(MovieDBConfig.BASAE_IMAGE_URL + castDetailModel.profile_path).
                fitCenter().
                diskCacheStrategy(DiskCacheStrategy.ALL).
                into(this.ivPersonPoster)
        Glide.with(this.context.applicationContext).
                load(MovieDBConfig.BASAE_IMAGE_URL + castDetailModel.images?.profiles!![1].file_path).
                fitCenter().
                diskCacheStrategy(DiskCacheStrategy.ALL).
                into(this.dv_poster)
        // Inflate the crew section.
        if (null != stubRelated.parent)
            castDetailModel.combined_credits?.cast?.let {
                stubRelated.inflate()
                this.showCreditsMovies(it)
            }
        else
            stubRelated.visibility = View.VISIBLE
    }

    private fun showCreditsMovies(creditsMovieList: List<CreditsModel.CastBean>) {
        this.rvRelated.layoutManager = LinearLayoutManager(this.context,
                LinearLayoutManager.HORIZONTAL,
                false)
        this.rvRelated.adapter = CastRelatedMovieAdapter(this.context, creditsMovieList)
        this.rvRelated.addItemDecoration(MovieHorizontalItemDecorator(20))
    }
}
