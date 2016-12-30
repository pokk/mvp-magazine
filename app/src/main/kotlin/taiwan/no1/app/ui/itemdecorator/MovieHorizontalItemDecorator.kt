package taiwan.no1.app.ui.itemdecorator

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   12/30/16
 */

class MovieHorizontalItemDecorator: RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position: Int = parent.getChildAdapterPosition(view)
        val childrenCount: Int = parent.childCount

        if (childrenCount == position) {
            outRect.set(20, 0, 20, 0)
        }
        else {
            outRect.set(0, 0, 20, 0)
        }
    }
}