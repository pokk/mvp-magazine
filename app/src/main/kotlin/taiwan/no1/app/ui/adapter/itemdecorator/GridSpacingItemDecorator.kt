package taiwan.no1.app.ui.adapter.itemdecorator

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View


/**
 *
 * @author  Jieyi
 * @since   12/31/16
 */

class GridSpacingItemDecorator(private val spanCount: Int, private val spacing: Int, private val includeEdge: Boolean):
        RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        // item position.
        val position = parent.getChildAdapterPosition(view)
        // item column.
        val column = position.rem(spanCount)

        if (includeEdge) {
            // spacing - column * ((1f / spanCount) * spacing).
            outRect.left = spacing - column * spacing / spanCount
            // (column + 1) * ((1f / spanCount) * spacing).
            outRect.right = (column + 1) * spacing / spanCount
            // top edge.
            if (position < spanCount)
                outRect.top = spacing
            // item bottom.
            outRect.bottom = spacing
        }
        else {
            // column * ((1f / spanCount) * spacing).
            outRect.left = column * spacing / spanCount
            // spacing - (column + 1) * ((1f / spanCount) * spacing).
            outRect.right = spacing - (column + 1) * spacing / spanCount
            // item top.
            if (position >= spanCount)
                outRect.top = spacing
        }
    }
}