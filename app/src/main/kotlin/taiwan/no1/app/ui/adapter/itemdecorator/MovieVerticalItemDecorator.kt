package taiwan.no1.app.ui.adapter.itemdecorator

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 *
 * @author  Jieyi
 * @since   12/30/16
 */

class MovieVerticalItemDecorator(val topBottom: Int, val leftRight: Int = topBottom): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position: Int = parent.getChildAdapterPosition(view)
        val childrenCount: Int = parent.childCount

        if (0 == position)
            outRect.set(leftRight, topBottom, leftRight, topBottom / 2)
        else if (childrenCount - 1 != position)
            outRect.set(leftRight, topBottom / 2, leftRight, topBottom)
        else
            outRect.set(leftRight, topBottom / 2, leftRight, topBottom / 2)
    }
}