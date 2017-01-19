package taiwan.no1.app.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewStub
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.target.Target
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
import taiwan.no1.app.mvp.models.IVisitable
import taiwan.no1.app.ui.BaseFragment
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.ui.adapter.itemdecorator.MovieHorizontalItemDecorator
import taiwan.no1.app.ui.listeners.GlideCustomRequestListener
import taiwan.no1.app.utilies.ImageLoader.IImageLoader
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

    @Inject
    lateinit var presenter: CastDetailContract.Presenter
    @Inject
    lateinit var imageLoader: IImageLoader

    //region View variables
    private val ivDropPoster by bindView<DiagonalView>(R.id.dv_drop_poster)
    private val ivPersonPoster by bindView<ImageView>(R.id.iv_person)
    private val tvName by bindView<TextView>(R.id.tv_name)
    private val tvJob by bindView<TextView>(R.id.tv_job)
    private val tvBioOfTitle by bindView<TextView>(R.id.tv_title_bio)
    private val tvBio by bindView<TextView>(R.id.tv_bio)
    private val tvBirthdayOfTitle by bindView<TextView>(R.id.tv_title_birthday)
    private val tvBirthday by bindView<TextView>(R.id.tv_birthday)
    private val tvBronOfTitle by bindView<TextView>(R.id.tv_title_place_of_birth)
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

        this.imageLoader.display(MovieDBConfig.BASE_IMAGE_URL + imageUrl,
                this.ivDropPoster, object: GlideCustomRequestListener() {
            override fun onResourceReady(resource: GlideDrawable,
                                         model: String,
                                         target: Target<GlideDrawable>,
                                         isFromMemoryCache: Boolean,
                                         isFirstResource: Boolean): Boolean {
                ivDropPoster.solidColor = Color.TRANSPARENT
                return false
            }
        })
        this.imageLoader.display(MovieDBConfig.BASE_IMAGE_URL + castDetailModel.profile_path,
                this.ivPersonPoster, null, false)
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
        this.showViewStub(this.stubIntro, {
            this.showInfo(castDetailModel.biography, this.tvBioOfTitle, this.tvBio)
            this.showInfo(castDetailModel.birthday, this.tvBirthdayOfTitle, this.tvBirthday)
            this.showInfo(castDetailModel.place_of_birth, this.tvBronOfTitle, this.tvBron)
            this.showInfo(castDetailModel.homepage, this.tvHomepageOfTitle, this.tvHomepage)
            this.showInfo(castDetailModel.deathday, this.tvDeathdayOfTitle, this.tvDeathday)
        })

        // Inflate the related movie section.
        this.showViewStub(this.stubRelated, {
            castDetailModel.combined_credits?.cast?.let {
                this.showCardItems(this.rvRelated, it.filter { it.media_type == "movie" }.
                        sortedWith(compareBy({ it.release_date })).
                        reversed())
            }
        })
    }
    //endregion

    private fun <T: IVisitable> showCardItems(recyclerView: RecyclerView, list: List<T>) {
        recyclerView.apply {
            this.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = CommonRecyclerAdapter(list, argFromFragment)
            this.addItemDecoration(MovieHorizontalItemDecorator(30))
        }
    }

    private fun showInfo(info: String?, title: TextView, content: TextView) {
        if (info.isNullOrEmpty()) {
            title.visibility = View.GONE
            content.visibility = View.GONE
        }
        else
            content.text = info
    }
}
