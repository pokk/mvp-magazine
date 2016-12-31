package taiwan.no1.app.ui.customize

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.MotionEvent
import taiwan.no1.app.utilies.AppLog

/**
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   12/31/16
 */

class RollingRecyclerView: RecyclerView {
    constructor(context: Context?): super(context)
    constructor(context: Context?, attrs: AttributeSet?): super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int): super(context, attrs, defStyle)

    override fun onTouchEvent(e: MotionEvent?): Boolean {
        AppLog.w()
        return super.onTouchEvent(e)
    }

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        AppLog.w()
        return super.onInterceptTouchEvent(e)
    }
}