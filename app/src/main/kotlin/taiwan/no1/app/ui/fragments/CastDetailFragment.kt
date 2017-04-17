package taiwan.no1.app.ui.fragments

import android.graphics.Bitmap
import android.graphics.Color.TRANSPARENT
import android.os.Build
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearLayoutManager.HORIZONTAL
import android.support.v7.widget.RecyclerView
import android.transition.TransitionInflater
import android.view.View
import android.view.ViewStub
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.intrusoft.squint.DiagonalView
import taiwan.no1.app.App
import taiwan.no1.app.R
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.fragment.CastDetailContract
import taiwan.no1.app.mvp.models.CreditsInFilmModel
import taiwan.no1.app.mvp.models.IVisitable
import taiwan.no1.app.ui.BaseFragment
import taiwan.no1.app.ui.adapter.CommonRecyclerAdapter
import taiwan.no1.app.ui.adapter.itemdecorator.MovieHorizontalItemDecorator
import taiwan.no1.app.utilies.ImageLoader.IImageLoader
import javax.inject.Inject

/**
 * Present the assigned cast information, photos, articles.
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                TransitionInflater.from(App.getAppContext()).inflateTransition(R.transition.change_image_transform).let {
                    this.sharedElementEnterTransition = it
                    this.sharedElementReturnTransition = it
                }
            }
            this.arguments = Bundle().also {
                it.putString(ARG_PARAM_CAST_ID, id)
                it.putInt(ARG_PARAM_FROM_ID, from)
            }
        }
    }
    //endregion

    @Inject
    lateinit var presenter: CastDetailContract.Presenter
    @Inject
    lateinit var imageLoader: IImageLoader

    //region View variables
    private val ivDropPoster by bindView<DiagonalView>(R.id.dv_backdrop)
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

    //region Local variables
    // Get the arguments from the bundle here.
    private val argId: String by lazy { this.arguments.getString(ARG_PARAM_CAST_ID) }
    private val argFromFragment: Int by lazy { this.arguments.getInt(ARG_PARAM_FROM_ID) }
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
        this.getComponent(FragmentComponent::class.java).inject(CastDetailFragment@ this)
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
        this.showLoading()

        this.presenter.requestCastDetail(this.argId.toInt())
    }
    //endregion

    //region View implementations
    override fun showCastPoster(posterUri: String) {
        this.imageLoader.display(posterUri, listener = object: BitmapImageViewTarget(this.ivDropPoster) {
            override fun onResourceReady(resource: Bitmap, glideAnimation: GlideAnimation<in Bitmap>) {
                this@CastDetailFragment.ivDropPoster.solidColor = TRANSPARENT
                super.onResourceReady(resource, glideAnimation)
                this@CastDetailFragment.ivDropPoster.setOnClickListener {
                    this@CastDetailFragment.presenter.enterToGallery(this@CastDetailFragment.argFromFragment)
                }
            }
        })
    }

    override fun showCastProfilePic(proPicUri: String) {
        this.imageLoader.display(proPicUri, this.ivPersonPoster, isFitCenter = false)
    }

    override fun showCastBase(gender: String, name: String) {
        this.tvJob.also {
            it.setBackgroundColor(TRANSPARENT)
            it.text = gender
        }
        this.tvName.also {
            it.setBackgroundColor(TRANSPARENT)
            it.text = name
        }
    }

    override fun showCastDetail(bio: String, birthday: String, bron: String, homepage: String, deathday: String) {
        // Inflate the introduction section.
        this.showViewStub(this.stubIntro, {
            this.showInfo(bio, this.tvBioOfTitle, this.tvBio)
            this.showInfo(birthday, this.tvBirthdayOfTitle, this.tvBirthday)
            this.showInfo(bron, this.tvBronOfTitle, this.tvBron)
            this.showInfo(homepage, this.tvHomepageOfTitle, this.tvHomepage)
            this.showInfo(deathday, this.tvDeathdayOfTitle, this.tvDeathday)
        })
    }

    override fun showRelatedMovie(casts: List<CreditsInFilmModel.CastInFilmBean>) {
        // Inflate the related movie section.
        this.showViewStub(this.stubRelated, { this.showCardItems(this.rvRelated, casts) })
    }
    //endregion

    private fun <T: IVisitable> showCardItems(recyclerView: RecyclerView, list: List<T>) {
        recyclerView.also {
            it.layoutManager = LinearLayoutManager(this.context, HORIZONTAL, false)
            it.adapter = CommonRecyclerAdapter(list, argFromFragment)
            it.addItemDecoration(MovieHorizontalItemDecorator(30))
        }
    }

    private fun showInfo(info: String, title: TextView, content: TextView) {
        if (info.isNullOrEmpty()) {
            title.visibility = View.GONE
            content.visibility = View.GONE
        }
        else
            content.text = info
    }
}
