package taiwan.no1.app.ui.customize

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

/**
 * A [RecyclerView] includes pulling up and loading more data.
 *
 * @author  Jieyi
 * @since   2/7/17
 */

class LoadMoreRecyclerView: RecyclerView {
    //region Member Variables
    private var onBottomListener: OnBottomListener? = null
    //endregion

    interface OnBottomListener {
        fun onBottom()
    }

    //region Constructors
    constructor(context: Context): this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int): super(context, attrs, defStyle)
    //endregion

    init {
        this.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                this@LoadMoreRecyclerView.onBottomListener?.let {
                    // TODO: 2017/02/07 Add the states of loading, completed, failure, ....
                    // TODO: 2017/02/07 Add animations of pulling up.
                    if (isOnBottom())
                        it.onBottom()
                }
            }
        })
    }

    fun setOnBottomListener(onBottomListener: OnBottomListener) {
        this.onBottomListener = onBottomListener
    }

    private fun isOnBottom() = this.computeVerticalScrollExtent() + this.computeVerticalScrollOffset() >= this.computeVerticalScrollRange()
}
