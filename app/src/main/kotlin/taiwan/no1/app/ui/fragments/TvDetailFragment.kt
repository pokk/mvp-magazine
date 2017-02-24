package taiwan.no1.app.ui.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.BitmapImageViewTarget
import taiwan.no1.app.R
import taiwan.no1.app.internal.di.annotations.PerFragment
import taiwan.no1.app.internal.di.components.FragmentComponent
import taiwan.no1.app.mvp.contracts.fragment.TvDetailContract
import taiwan.no1.app.ui.BaseFragment
import taiwan.no1.app.ui.adapter.BackdropPagerAdapter
import taiwan.no1.app.utilies.ImageLoader.IImageLoader
import javax.inject.Inject

/**
 *
 * @author  Jieyi
 * @since   2/12/17
 */
@PerFragment
class TvDetailFragment: BaseFragment(), TvDetailContract.View {
    //region Static initialization
    companion object Factory {
        // The key name of the fragment initialization parameters.
        private const val ARG_PARAM_TV_ID: String = "param_tv_id"
        private const val ARG_PARAM_FROM_ID: String = "param_tv_from_fragment"

        /**
         * Use this factory method to create a new instance of this fragment using the provided parameters.
         *
         * @return A new instance of [fragment] TvDetailFragment.
         */
        fun newInstance(id: String, from: Int): TvDetailFragment = TvDetailFragment().apply {
            this.arguments = Bundle().apply {
                this.putString(ARG_PARAM_TV_ID, id)
                this.putInt(ARG_PARAM_FROM_ID, from)
            }
        }
    }
    //endregion

    @Inject
    lateinit var presenter: TvDetailContract.Presenter
    @Inject
    lateinit var imageLoader: IImageLoader

    private val vpDropPoster by bindView<ViewPager>(R.id.vp_drop_poster)
    private val tvTitle by bindView<TextView>(R.id.tv_title)
    private val tvStatus by bindView<TextView>(R.id.tv_status)
    private val tvVoteRate by bindView<TextView>(R.id.tv_vote)
    private val tvLastAirDate by bindView<TextView>(R.id.tv_last_air_date)

    // Get the arguments from the bundle here.
    private val id: String by lazy { this.arguments.getString(ARG_PARAM_TV_ID) }
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
        this.getComponent(FragmentComponent::class.java).inject(TvDetailFragment@ this)
    }

    /**
     * Set this fragment xml layout.
     *
     * @return [LayoutRes] xml layout.
     */
    @LayoutRes
    override fun inflateView(): Int = R.layout.fragment_tv_detail

    /**
     * Set the presenter initialization in [onCreateView].
     */
    override fun initPresenter() {
        this.presenter.init(TvDetailFragment@ this)
    }

    /**
     * Initialization of this fragment. Set the listeners or view components' attributions.
     *
     * @param savedInstanceState the previous fragment data status after the system calls [onPause].
     */
    override fun init(savedInstanceState: Bundle?) {
        this.presenter.requestListTvs(this.id.toInt())
    }
    //endregion

    override fun showTvBackdrops(viewList: List<View>) {
        this.vpDropPoster.adapter = BackdropPagerAdapter(viewList)
    }

    override fun showTvSingleBackdrop(uri: String, imageview: ImageView) {
        this.imageLoader.display(uri, listener = object: BitmapImageViewTarget(imageview) {
            override fun onResourceReady(resource: Bitmap, glideAnimation: GlideAnimation<in Bitmap>) {
                this@TvDetailFragment.presenter.onResourceFinished(imageview, this@TvDetailFragment.argFromFragment)
                super.onResourceReady(resource, glideAnimation)
            }
        }, isFitCenter = false)
    }

    override fun showTvBriefInfo(title: String, status: String, rate: String, lastAirDate: String) {
        this.tvTitle.text = title
        this.tvStatus.text = status
        this.tvVoteRate.text = rate
        this.tvLastAirDate.text = lastAirDate
    }
}
