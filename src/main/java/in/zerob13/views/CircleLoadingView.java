package in.zerob13.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import in.zerob13.circleloadingview.R;

/**
 * This code is copyright (c) 2015 juhuiwan.cn
 * Created by zerob13 on 10/29/15.
 */
public class CircleLoadingView extends View {
    private Bitmap mOriginBitmap;
    private Bitmap mResult;
    private Paint mGrayPaint;
    private Paint mArcPaint;
    private Paint mRingPaint;
    private Paint mCirclePaint;
    private Paint mNormalPaint;
    private RectF mRectF;
    private int mRingRadius;
    private int mCircleRadius;
    private float mCircleRadiusMax;
    private float mCircleStep;
    private float mCenterX;
    private float mCenterY;
    private float mAnimationDuration;
    private int mCircleStrokeSize;
    private int mPercent;
    private int mAnimationState;


    public CircleLoadingView(Context context) {
        this(context, null);
    }

    public CircleLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ColorMatrix cm = new ColorMatrix();
        cm.setScale(0.382f, 0.382f, 0.382f, 1f);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        mGrayPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mGrayPaint.setColorFilter(f);
        mArcPaint = new Paint((Paint.ANTI_ALIAS_FLAG));
        mRingPaint = new Paint((Paint.ANTI_ALIAS_FLAG));
        mCirclePaint = new Paint((Paint.ANTI_ALIAS_FLAG));
        mNormalPaint = new Paint((Paint.ANTI_ALIAS_FLAG));
        mAnimationState = 2;
        final TypedArray typedArray = context
                .obtainStyledAttributes(attrs, R.styleable.CircleLoadingView);
        try {
            Drawable drawable = typedArray
                    .getDrawable(R.styleable.CircleLoadingView_cl_src);
            mCircleRadius = typedArray
                    .getDimensionPixelSize(R.styleable.CircleLoadingView_cl_circleRadius, -1);
            mAnimationDuration = typedArray
                    .getFloat(R.styleable.CircleLoadingView_cl_fillAnimationDuration, 800);
            mCircleStrokeSize = typedArray
                    .getDimensionPixelSize(R.styleable.CircleLoadingView_cl_circleStrokeSize, -1);
            if (drawable instanceof BitmapDrawable) {
                mOriginBitmap = ((BitmapDrawable) drawable).getBitmap();
                initRect();
            }
        } finally {
            typedArray.recycle();
        }
    }

    public void setImageBitmap(Bitmap bm) {
        this.mOriginBitmap = bm;
        initRect();
    }

    private void initRect() {
        int w = this.getMeasuredWidth();
        int h = this.getMeasuredHeight();
        if (w == 0 || h == 0) {
            return;
        }
        if (w > 0 && h > 0) {

            if (mCircleRadius < 0) {
                mCircleRadius = mRingRadius = w / 4;
            } else {
                mRingRadius = mCircleRadius;
            }
            mCenterX = w / 2f;
            mCenterY = h / 2f;
            mResult = Bitmap.createScaledBitmap(mOriginBitmap, w, h, true);
            mRectF = new RectF((w / 2 - mCircleRadius), (h / 2 - mCircleRadius),
                    w / 2 + mCircleRadius,
                    h / 2 + mCircleRadius);
            mRingPaint.setStyle(Paint.Style.STROKE);
            if (mCircleStrokeSize < 0) {
                mCircleStrokeSize = w / 36;
            }
            mRingPaint.setStrokeWidth(mCircleStrokeSize);
            mCircleRadiusMax = (float) Math.sqrt(w * w + h * h) / 2f;
            mCircleStep = (mCircleRadiusMax - mCircleRadius) / (mAnimationDuration / 25);
            Matrix m = new Matrix();
            RectF src = new RectF(0, 0, mOriginBitmap.getWidth(), mOriginBitmap.getHeight());
            RectF dst = new RectF(0, 0, this.getWidth(), this.getHeight());
            m.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER);
            Shader shader = new BitmapShader(mOriginBitmap, Shader.TileMode.CLAMP,
                    Shader.TileMode.CLAMP);
            shader.setLocalMatrix(m);
            mArcPaint.setShader(shader);
            mRingPaint.setShader(shader);
            mCirclePaint.setShader(shader);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mOriginBitmap == null) {
            return;
        }
        int percent = mPercent;
        if (percent == 0) {
            int saveCount = canvas.save();
            canvas.drawBitmap(mResult, 0, 0, mGrayPaint);
            canvas.restoreToCount(saveCount);
        } else if (percent < 100) {
            int saveCount = canvas.save();
            canvas.drawBitmap(mResult, 0, 0, mGrayPaint);
            canvas.drawCircle(mCenterX, mCenterY, mRingRadius, mRingPaint);
            canvas.drawArc(mRectF, -90, (float) ((percent / 100.0) * 360.0), true, mArcPaint);
            if (mAnimationState == 2) {
                mAnimationState = 0;
            }
            canvas.restoreToCount(saveCount);
        } else if (percent == 100 && mAnimationState == 0) {
            int saveCount = canvas.save();
            canvas.drawBitmap(mResult, 0, 0, mGrayPaint);
            canvas.drawCircle(mCenterX, mCenterY, mCircleRadius, mCirclePaint);
            mCircleRadius += mCircleStep;
            mAnimationState = 1;
            postInvalidateDelayed(100);
            canvas.restoreToCount(saveCount);
        } else if (mAnimationState == 1) {
            int saveCount = canvas.save();
            canvas.drawBitmap(mResult, 0, 0, mGrayPaint);
            canvas.drawCircle(mCenterX, mCenterY, mCircleRadius, mCirclePaint);
            mCircleRadius += mCircleStep;
            if (mCircleRadius >= mCircleRadiusMax) {
                mAnimationState = 2;
                mCircleRadius = mRingRadius;
            } else {
                postInvalidateDelayed(25);
            }
            canvas.restoreToCount(saveCount);

        } else {
            int saveCount = canvas.save();
            canvas.drawBitmap(mResult, 0, 0, mNormalPaint);
            canvas.restoreToCount(saveCount);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw || h != oldh) {
            if (mResult != null) {
                mResult = null;
            }
            if (mOriginBitmap != null) {
                initRect();
            }
        }
    }

    public void setPercent(int percent) {
        this.mPercent = percent;
        postInvalidate();
    }
}

