package taiwan.no1.app.ui.customize

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import taiwan.no1.app.R

/**
 * Created by jieyi on 2017/01/25.
 */

class StarScoreView: View {
    private val scorePaint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            color = Color.YELLOW
        }
    }
    private var scoreBitmap: Bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.ic_star_border)
        set(value) {
            field = value
        }
    private var mWidth: Int = 0
    private var mHeight: Int = 0
    private var scoreNumber: Int = 5

    constructor(context: Context?): super(context)
    constructor(context: Context?, attrs: AttributeSet?): super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int): super(context,
            attrs,
            defStyleAttr)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int): super(context,
            attrs,
            defStyleAttr,
            defStyleRes)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode: Int = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize: Int = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode: Int = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize: Int = MeasureSpec.getSize(heightMeasureSpec)

        val desiredWidth: Int = this.scoreBitmap.width * this.scoreNumber
        val desiredHeight: Int = this.scoreBitmap.height

        // Measure width.
        val settingWidth = when (widthMode) {
        // Must be this size.
            MeasureSpec.EXACTLY -> widthSize
        //Can't be bigger than...
            MeasureSpec.AT_MOST -> Math.min(desiredWidth, widthSize)
        //Be whatever you want.
            else -> desiredWidth
        }

        // Measure height.
        val settingHeight = when (heightMode) {
        // Must be this size.
            MeasureSpec.EXACTLY -> heightSize
        //Can't be bigger than...
            MeasureSpec.AT_MOST -> Math.min(desiredHeight, heightSize)
        //Be whatever you want.
            else -> desiredHeight
        }

        setMeasuredDimension(settingWidth, settingHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // TODO: 2017/01/25 Finish this star score view. See the result in the [fragment_movie_detail.xml].
//        (0..this.scoreNumber - 1).forEach {
//            canvas.drawBitmap(this.scoreBitmap, 0f + this.scoreBitmap.width * it, 0f, this.scorePaint)
//        }
        canvas.drawBitmap(this.scoreBitmap, 0f, 0f, this.scorePaint)
        this.scorePaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        this.scorePaint.color = Color.RED
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), this.scorePaint)
    }
}