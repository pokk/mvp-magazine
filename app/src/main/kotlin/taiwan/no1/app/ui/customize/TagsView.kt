package taiwan.no1.app.ui.customize

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 *
 * @author  Jieyi
 * @since   1/26/17
 */

class TagsView: View {
    private val paint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.BLACK
            style = Paint.Style.STROKE
            textSize = 50f
        }
    }

    var tagList: List<String> = listOf("Fantasy", "Action")

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet?): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int): super(context,
            attrs,
            defStyleAttr,
            defStyleRes)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    // TODO: 1/26/17 Do the tags text.
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var prevStart: Float = 0f
        var prevEnd: Float = 0f
        (0..tagList.size - 1).forEach {
            prevEnd = it * this.paint.measureText(this.tagList[it])
//            canvas.drawText(this.tagList[it], prevStart, 50f, this.paint)
//            canvas.drawRoundRect(it*paint.measureText(this.tagList[it]), 0f, )
            prevStart = prevEnd
        }
    }
}