package top.golabe.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class CircleProgressView extends View {
    private Context mContext;
    private int mProgressMax;
    private int mProgressMin;
    private int mProgressBarColor;
    private int mProgressBgColor;
    private int mProgressTextColor;
    private int mProgress;
    private float mProgressTextSize;
    private float mProgressBarWidth;


    private Paint mPaintBg;
    private Paint mPaintText;
    private Paint mPaintProgressBar;
    private int mWidth;
    private int mHeight;
    private int mRadius;
    private Point mCircleCenterPoint;
    private RectF mRectF=new RectF();
    private int mTotalProgress;

    public CircleProgressView(Context context) {
        this(context,null);
    }

    public CircleProgressView(Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        initAttrs(attrs);
        init();
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs!=null){
            TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.CircleProgressView);
            mProgress=a.getInt(R.styleable.CircleProgressView_progress,0);
            mProgressMax=a.getInt(R.styleable.CircleProgressView_progress_max,100);
            mProgressMin=a.getInt(R.styleable.CircleProgressView_progress_min,0);
            mProgressTextSize=sp2px(a.getDimension(R.styleable.CircleProgressView_progress_text_size,14));
            mProgressBarWidth=dp2px(a.getDimension(R.styleable.CircleProgressView_progress_bar_width,2));
            mProgressBarColor=a.getColor(R.styleable.CircleProgressView_progress_bar_color,Color.WHITE);
            mProgressBgColor=a.getColor(R.styleable.CircleProgressView_progress_bg_color,Color.BLUE);
            mProgressTextColor=a.getColor(R.styleable.CircleProgressView_progress_text_color,Color.WHITE);
            checkProgress(mProgress);
            a.recycle();
        }

    }
    private void init() {
        mPaintBg=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBg.setColor(mProgressBgColor);
        mPaintBg.setStyle(Paint.Style.FILL);

        mPaintText=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintText.setColor(mProgressTextColor);
        mPaintText.setTextSize(mProgressTextSize);

        mPaintProgressBar=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintProgressBar.setColor(mProgressBarColor);
        mPaintProgressBar.setStyle(Paint.Style.STROKE);
        mPaintProgressBar.setStrokeCap(Paint.Cap.ROUND);
        mPaintProgressBar.setStrokeWidth(mProgressBarWidth);
        mTotalProgress = Math.abs(mProgressMax - mProgressMin);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthModel = MeasureSpec.getMode(widthMeasureSpec);
        int heightModel = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int size = Math.min(width, height);
        if (widthModel==MeasureSpec.EXACTLY||heightModel==MeasureSpec.EXACTLY){
            setMeasuredDimension(size,size);
        }else if (widthModel==MeasureSpec.AT_MOST||heightModel==MeasureSpec.AT_MOST){
            setMeasuredDimension(dp2px(48),dp2px(48));
        }

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mRadius = w/2-w/10;
        mCircleCenterPoint = new Point(mWidth / 2, mHeight / 2);
        mRectF.set(mWidth/8,mHeight/8,mWidth-mWidth/8,mHeight-mHeight/8);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBg(canvas);
        drawText(canvas);
        canvas.save();
        canvas.rotate(-90,mCircleCenterPoint.x,mCircleCenterPoint.y);
        drawProgressBar(canvas);

    }

    private void drawProgressBar(Canvas canvas) {

        canvas.drawArc(mRectF,0, (float) ((360.0/mTotalProgress)*mProgress),false,mPaintProgressBar);
    }

    private void drawText(Canvas canvas) {
        String progress=mProgress+"%";
        float x = getWidth() / 2 - mPaintText.measureText(progress)/2;
        Paint.FontMetrics fm = mPaintText.getFontMetrics();
        float y = getHeight() / 2 - fm.descent + (fm.bottom - fm.top) / 2;
        canvas.drawText(progress,x,y,mPaintText);
    }

    public int getProgressMax() {
        return mProgressMax;
    }

    public void setProgressMax(int mProgressMax) {
        this.mProgressMax = mProgressMax;
        invalidate();
    }

    public int getProgressMin() {
        return mProgressMin;
    }

    public void setProgressMin(int mProgressMin) {
        this.mProgressMin = mProgressMin;
        invalidate();
    }

    public int getProgressBarColor() {
        return mProgressBarColor;
    }

    public void setProgressBarColor(int mProgressBarColor) {
        this.mProgressBarColor = mProgressBarColor;
        invalidate();
    }

    public int getProgressBgColor() {
        return mProgressBgColor;
    }

    public void setProgressBgColor(int mProgressBgColor) {
        this.mProgressBgColor = mProgressBgColor;
        invalidate();
    }

    public int getProgressTextColor() {
        return mProgressTextColor;
    }

    public void setProgressTextColor(int mProgressTextColor) {
        this.mProgressTextColor = mProgressTextColor;
        invalidate();
    }

    public int getProgress() {
        return mProgress;
    }

    public void setProgress(int progress) {
      checkProgress(progress);
        invalidate();
    }

    private void checkProgress(int progress) {
        if (progress<0){
            this.mProgress=0;
        }else if (progress>100){
            this.mProgress=100;
        }else {
            this.mProgress = progress;
        }
    }

    public float getProgressTextSize() {
        return mProgressTextSize;
    }

    public void setProgressTextSize(float mProgressTextSize) {
        this.mProgressTextSize = mProgressTextSize;
        invalidate();
    }

    public float getProgressBarWidth() {
        return mProgressBarWidth;
    }

    public void setProgressBarWidth(float mProgressBarWidth) {
        this.mProgressBarWidth = mProgressBarWidth;
        invalidate();
    }


    private void drawBg(Canvas canvas) {
        canvas.drawCircle(mCircleCenterPoint.x,mCircleCenterPoint.y,mRadius,mPaintBg);
    }

    private   int sp2px( float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, mContext.getResources().getDisplayMetrics());
    }
    private   int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, mContext.getResources().getDisplayMetrics());
    }

}
