package taiwan.no1.app.ui.customize

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.AttributeSet
import android.widget.ImageView


/**
 *
 * @author  Jieyi
 * @since   1/4/17
 */

class CircleImageView: ImageView {
    private val imagePaint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.GREEN
            style = Paint.Style.FILL
        }
    }
    private var imgBitmap: Bitmap? = null

    constructor(context: Context): this(context, null)
    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): this(context,
            attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int): super(context,
            attrs, defStyleAttr, defStyleRes)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        imagePaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawCircle(width / 2f, height / 2f, Math.min(width / 2f, height / 2f), imagePaint)
    }

    override fun setImageBitmap(bm: Bitmap?) {
        super.setImageBitmap(bm)
        initializeBitmap()
    }

    override fun setImageDrawable(drawable: Drawable?) {
        super.setImageDrawable(drawable)
        initializeBitmap()
    }

    override fun setImageURI(uri: Uri?) {
        super.setImageURI(uri)
        initializeBitmap()
    }

    override fun setImageResource(resId: Int) {
        super.setImageResource(resId)
        initializeBitmap()
    }

    private fun initializeBitmap() {
        this.imgBitmap = drawable?.let {
            Bitmap.createBitmap(it.intrinsicWidth, it.intrinsicHeight, Bitmap.Config.ARGB_8888).apply {
                Canvas(this).apply {
                    it.setBounds(0, 0, this.width, this.height)
                    it.draw(this)
                }
            }
        }
    }
}