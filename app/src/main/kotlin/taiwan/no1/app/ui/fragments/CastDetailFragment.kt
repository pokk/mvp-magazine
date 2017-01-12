package taiwan.no1.app.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.view.ViewStub
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hwangjr.rxbus.RxBus
import com.intrusoft.squint.DiagonalView
import com.touchin.constant.RxbusTag
import taiwan.no1.app.R
import taiwan.no1.app.api.config.MovieDBConfig
import taiwan.no1.app.constant.Constant
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.CastDetailContract
import taiwan.no1.app.mvp.models.CastDetailModel
import taiwan.no1.app.mvp.models.CreditsModel
import taiwan.no1.app.ui.BaseFragment
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.ui.adapter.itemdecorator.MovieHorizontalItemDecorator
import javax.inject.Inject
import kotlin.comparisons.compareBy

/**
 *
 * @author  Jieyi
 * @since   1/1/17
 */

@PerFragment
class CastDetailFragment: BaseFragment(), CastDetailContract.View {
    //region Static initialization
    companion object Factory {
        // The key name of the fragment initialization parameters.
        private val ARG_PARAM_CAST_ID: String = "param_cast_id"
        private val ARG_PARAM_FROM_ID: String = "param_movie_from_fragment"

        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of [fragment] CastDetailFragment.
         */
        fun newInstance(id: String, from: Int): CastDetailFragment = CastDetailFragment().apply {
            this.arguments = Bundle().apply {
                this.putString(ARG_PARAM_CAST_ID, id)
                this.putInt(ARG_PARAM_FROM_ID, from)
            }
        }
    }
    //endregion

    @Inject lateinit var presenter: CastDetailContract.Presenter

    //region View variables
    private val ivDropPoster by bindView<DiagonalView>(R.id.dv_drop_poster)
    private val ivPersonPoster by bindView<ImageView>(R.id.iv_person)
    private val tvName by bindView<TextView>(R.id.tv_name)
    private val tvJob by bindView<TextView>(R.id.tv_job)
    private val tvBio by bindView<TextView>(R.id.tv_bio)
    private val tvBirthday by bindView<TextView>(R.id.tv_birthday)
    private val tvBron by bindView<TextView>(R.id.tv_place_of_birth)
    private val tvHomepageOfTitle by bindView<TextView>(R.id.tv_title_homepage)
    private val tvHomepage by bindView<TextView>(R.id.tv_homepage)
    private val tvDeathdayOfTitle by bindView<TextView>(R.id.tv_title_deathday)
    private val tvDeathday by bindView<TextView>(R.id.tv_deathday)
    private val stubIntro by bindView<ViewStub>(R.id.stub_introduction)
    private val stubRelated by bindView<ViewStub>(R.id.stub_related)
    private val rvRelated by bindView<RecyclerView>(R.id.rv_related)
    //endregion

    // Get the arguments from the bundle here.
    private val argId: String by lazy { this.arguments.getString(ARG_PARAM_CAST_ID) }
    private val argFromFragment: Int by lazy { this.arguments.getInt(ARG_PARAM_FROM_ID) }

    //region Fragment lifecycle
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
     * Set the presenter initialization in [onCreateView].
     */
    override fun initPresenter() {
        this.presenter.init(CastDetailFragment@ this)
    }

    /**
     * Initialization of this fragment. Set the listeners or view components' attributions.
     *
     * @param savedInstanceState the previous fragment data status after the system calls [onPause].
     */
    override fun init(savedInstanceState: Bundle?) {
        this.presenter.requestCastDetail(this.argId.toInt())
    }
    //endregion

    //region View implementations
    override fun showCastDetail(castDetailModel: CastDetailModel) {
        val imageUrl = castDetailModel.images?.let { it.profiles?.let { if (it.size > 1) it[1].file_path else it[0].file_path } }

        Glide.with(this.context.applicationContext).
                load(MovieDBConfig.BASE_IMAGE_URL + imageUrl).
                fitCenter().
                diskCacheStrategy(DiskCacheStrategy.SOURCE).
                listener(this.clearDiagonalViewListener(this.ivDropPoster)).
                into(this.ivDropPoster)
        Glide.with(this.context.applicationContext).
                load(MovieDBConfig.BASE_IMAGE_URL + castDetailModel.profile_path).
                centerCrop().
                diskCacheStrategy(DiskCacheStrategy.SOURCE).
                into(this.ivPersonPoster)
        this.ivDropPoster.setOnClickListener {
            RxBus.get().post(RxbusTag.FRAGMENT_CHILD_NAVIGATOR, hashMapOf(
                    Pair(MovieListFragment.NAVIGATOR_ARG_FRAGMENT,
                            MovieGalleryFragment.newInstance(castDetailModel.images?.profiles)),
                    Pair(MovieListFragment.NAVIGATOR_ARG_TAG, argFromFragment)))
        }
        this.tvJob.apply {
            this.setBackgroundColor(Color.TRANSPARENT)
            this.text = Constant.Gender.values()[castDetailModel.gender].jobName
        }
        this.tvName.apply {
            this.setBackgroundColor(Color.TRANSPARENT)
            this.text = castDetailModel.name
        }

        // Inflate the introduction section.
        if (null != stubIntro.parent) {
            stubIntro.inflate()
            this.tvBio.text = castDetailModel.biography
            this.tvBirthday.text = castDetailModel.birthday
            this.tvBron.text = castDetailModel.place_of_birth
            if (TextUtils.isEmpty(castDetailModel.homepage) || null == castDetailModel.homepage) {
                this.tvHomepageOfTitle.visibility = View.GONE
                this.tvHomepage.visibility = View.GONE
            }
            else
                this.tvHomepage.text = castDetailModel.homepage
            if (TextUtils.isEmpty(castDetailModel.deathday) || null == castDetailModel.deathday) {
                this.tvDeathdayOfTitle.visibility = View.GONE
                this.tvDeathday.visibility = View.GONE
            }
            else
                this.tvDeathday.text = castDetailModel.deathday
        }
        else
            stubIntro.visibility = View.VISIBLE

        // Inflate the related movie section.
        if (null != stubRelated.parent)
            castDetailModel.combined_credits?.cast?.let {
                stubRelated.inflate()
                this.showCreditsMovies(it)
            }
        else
            stubRelated.visibility = View.VISIBLE
    }
    //endregion

    private fun showCreditsMovies(creditsMovieList: List<CreditsModel.CastBean>) {
        this.rvRelated.layoutManager = LinearLayoutManager(this.context,
                LinearLayoutManager.HORIZONTAL,
                false)
        this.rvRelated.adapter = CommonRecyclerAdapter(creditsMovieList.filter { it.media_type == "movie" }.
                sortedWith(compareBy({ it.release_date })).
                reversed(), this.argFromFragment)
        this.rvRelated.addItemDecoration(MovieHorizontalItemDecorator(20))
    }
}
