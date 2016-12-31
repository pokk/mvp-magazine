package taiwan.no1.app.ui.customize

import android.content.Context
import android.support.v4.widget.NestedScrollView
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import taiwan.no1.app.utilies.AppLog

/**
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   12/31/16
 */

class RollingNestedScrollView: NestedScrollView {
    private var downX: Int = 0
    private var downY: Int = 0
    private var mTouchSlop: Int = 0

    constructor(context: Context): super(context) {
        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context,
            attrs,
            defStyleAttr) {
        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        AppLog.w(ev.action)
        return super.onInterceptTouchEvent(ev)
    }
}