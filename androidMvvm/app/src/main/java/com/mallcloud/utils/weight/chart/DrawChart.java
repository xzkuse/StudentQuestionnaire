package com.mallcloud.utils.weight.chart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.ColorRes;
import com.mallcloud.androidmvvm.R;
import com.mallcloud.utils.weight.logs.LogTag;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xzk
 * @data 2019/4/11
 * @email xiezhengkun@beyondsoft.com
 * @remark  折线图view
 */
public class DrawChart extends View {

    private List<DrawPoint> plist;

    /**
     * 用getx  getY  不用getRawX   getRawY
     * @param x
     * @param y
     */
    private void drawPoint(int x,int y){
        if(plist==null){
            return;
        }
        int index=maxPoi;
        double distance=getDistance(x,y,plist.get(index).point);
        for (int i = 0; i < plist.size(); i++) {
            if(i!=maxPoi){
                DrawPoint drawPoint = plist.get(i);
                Point point = drawPoint.getPoint();
                //距离本点最近的策略
                double a=getDistance(x,y,point);
                //plist设置isMax来进行绘制
                if(distance>a){
                    index=i;
                    distance=a;
                }
            }
        }
        LogTag.d("最近点为："+index+";;距离："+distance);
        if(index != maxPoi&& distance<params.getClickDistance()){
            plist.get(maxPoi).isMax=false;
            maxPoi=index;
            plist.get(maxPoi).isMax=true;
            postInvalidate();
        }

    }
    double getDistance(int x, int y, Point p){
        int i = (x - p.x) * (x - p.x) + (y - p.y) * (y - p.y);
        double sqrt = Math.sqrt(i);
        return sqrt;
    }

    List<Double> points = new ArrayList<>();
    List<String> dateArr = new ArrayList<>();
    private Bitmap bitmap;
    DrawChartParams params = new DrawChartParams();
    //目前整个数据的最大值是多少
    private Double max = 0.0;
    //最大数值在集合的什么位置
    private int maxPoi = 0;

    public void setdateArr(List<String> dateArr) {
        if(this.dateArr==null){
            this.dateArr=new ArrayList<>();
        }
        this.dateArr.clear();
        this.dateArr.addAll(dateArr);
    }
    public void setPoints(List<Double> points) {
        if(this.points==null){
            this.points=new ArrayList<>();
        }
        this.points.clear();
        this.points.addAll(points);
    }
    public void setLineColor(String color){
        params.commonLineColor(Color.parseColor(color));
    }
    public void setLineColor(@ColorRes int color){
        params.commonLineColor(getResources().getColor(color));
    }

    public DrawChart(Context context) {
        super(context);
    }

    public DrawChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context, attrs);
    }

    public DrawChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context, attrs);
    }

    private void initData(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DrawChart);
        int backLineColor = typedArray.getColor(R.styleable.DrawChart_backLineColor, Color.parseColor("#ECECEC"));
        float backLineWidth = typedArray.getDimension(R.styleable.DrawChart_backLineWidth, 4);
        float rightTextSize = typedArray.getDimension(R.styleable.DrawChart_rightTextSize, 32);
        int rightTextColor = typedArray.getColor(R.styleable.DrawChart_rightTextColor, Color.parseColor("#ECECEC"));
        float rightWidth = typedArray.getDimension(R.styleable.DrawChart_rightWidth, 60);
        float bottomTextSize = typedArray.getDimension(R.styleable.DrawChart_bottomTextSize, 32);
        int bottomTextColor = typedArray.getColor(R.styleable.DrawChart_bottomTextColor, Color.parseColor("#B4B4B4"));
        float bottomTextTopMargin = typedArray.getDimension(R.styleable.DrawChart_bottomTextTopMargin, 35);
        float bottomMargin = typedArray.getDimension(R.styleable.DrawChart_bottomMargin, 90);
        int lineColor = typedArray.getColor(R.styleable.DrawChart_lineColor, Color.parseColor("#2E88F6"));
        float lineWidth = typedArray.getDimension(R.styleable.DrawChart_lineWidth, 4);
        int hollowCircleColor = typedArray.getColor(R.styleable.DrawChart_hollowCircleColor,
                                                    Color.parseColor("#2E88F6"));
        float hollowCircleRadius = typedArray.getDimension(R.styleable.DrawChart_hollowCircleRadius, 6);
        float solidCircleRadius = typedArray.getDimension(R.styleable.DrawChart_solidCircleRadius, 10);
        int solidCircleColor = typedArray.getColor(R.styleable.DrawChart_solidCircleColor, Color.parseColor("#2E88F6"));
        int maxBackBitmap = typedArray.getResourceId(R.styleable.DrawChart_maxBackBitmap, R.mipmap.icon_max_back);
        float maxBackBitmapHeight = typedArray.getDimension(R.styleable.DrawChart_maxBackBitmapHeight, 75);
        float maxBackBitmapBottom = typedArray.getDimension(R.styleable.DrawChart_maxBackBitmapBottom, 10);
        int maxTextColor = typedArray.getColor(R.styleable.DrawChart_maxTextColor, Color.parseColor("#2E88F6"));
        float maxTextSize = typedArray.getDimension(R.styleable.DrawChart_maxTextSize, 35);
        float maxTextBottom = typedArray.getDimension(R.styleable.DrawChart_maxTextBottom, 40);
        boolean isOpenBlurMask = typedArray.getBoolean(R.styleable.DrawChart_isOpenBlurMask, true);
        float blurMaskWidth = typedArray.getDimension(R.styleable.DrawChart_blurMaskWidth, 6);
        float topSpace = typedArray.getDimension(R.styleable.DrawChart_topSpace, 80);
        float bitmapPadding = typedArray.getDimension(R.styleable.DrawChart_bitmapPadding, 20);
        float minLineHeight = typedArray.getDimension(R.styleable.DrawChart_minLineHeight, 10);
        float clickDistance = typedArray.getDimension(R.styleable.DrawChart_clickDistance, 100);
        params.setBackLineColor(backLineColor);
        params.setBackLineWidth((int) backLineWidth);
        params.setRightWidth((int) rightWidth);
        params.setTopSpace((int) topSpace);
        params.setBottomTextColor(bottomTextColor);
        params.setBottomTextSize((int) bottomTextSize);
        params.setRightTextSize((int) rightTextSize);
        params.setBottomTextTopMargin((int) bottomTextTopMargin);
        params.setRightTextColor(rightTextColor);
        params.setMaxBackBitmap(maxBackBitmap);
        params.setBottomMargin((int) bottomMargin);
        params.setLineWidth((int) lineWidth);
        params.setLineColor(lineColor);
        params.setOpenBlurMask(isOpenBlurMask);
        params.setBlurMaskWidth((int) blurMaskWidth);
        params.setHollowCircleColor(hollowCircleColor);
        params.setHollowCircleRadius((int) hollowCircleRadius);
        params.setSolidCircleColor(solidCircleColor);
        params.setSolidCircleRadius((int) solidCircleRadius);
        params.setMaxTextSize((int) maxTextSize);
        params.setMaxTextColor(maxTextColor);
        params.setBitmapPadding((int) bitmapPadding);
        params.setMinLineHeight((int) minLineHeight);

        params.setMaxBackBitmapHeight((int) maxBackBitmapHeight);
        params.setMaxBackBitmapBottom((int) maxBackBitmapBottom);
        params.setMaxTextBottom((int) maxTextBottom);
        params.setClickDistance((int)clickDistance);

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        bitmap = BitmapFactory.decodeResource(getResources(), params.getMaxBackBitmap());
        LogTag.d("bitmap:" + bitmap.getWidth());
        typedArray.recycle();
    }

    /**
     * 空心点,实心最高点,最高点值提示,点连线
     */
    private void transformPoint(int offsetX, int offsetY) {
        plist = new ArrayList<>(points.size());
        for (int i = 0; i < points.size(); i++) {
            Double aDouble = points.get(i);
            if (max < aDouble) {
                //这里是将最高点位标出来,用来放置
                max = aDouble;
                maxPoi = i;
            }
        }
        //进1位,变成整数位
        BigDecimal decimal = new BigDecimal(max);
        LogTag.d("大叔:"+decimal);
//        decimal.setScale(1, RoundingMode.HALF_UP);
        String value = decimal.toString();
        int indexOf = value.indexOf(".");
        if(indexOf>0){
            value = value.substring(0,indexOf );
        }
        double pow = Math.pow(10, value.length() - 1);
        double parseDouble = (Integer.parseInt(value.substring(0, 1)) + 1) * pow;
        double pointBili = (offsetY * 4) / parseDouble;
        for (int i = 0; i < points.size(); i++) {
            Double aDouble = points.get(i);
            DrawPoint point = new DrawPoint();
            double v = pointBili * aDouble;
            point.setPoint((params.getRightWidth() + offsetX / 2 + offsetX * i),
                           (int) ((params.getTopSpace() + offsetY * 4 - ((v)))));
            plist.add(point);
        }
        plist.get(maxPoi).setMax(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(dateArr==null ||dateArr.isEmpty()){
            return;
        }
        if(points==null || points.isEmpty()){
            return;
        }
        int measuredWidth = getMeasuredWidth();
        int height = getMeasuredHeight();

        //获得内部栏位区域  底部距离   顶部距离
        height = height - params.getBottomMargin() - params.getTopSpace();
        //4个空格
        int offsetY = height / 4;
        int offsetXText = (measuredWidth - params.getRightWidth()) / dateArr.size();
        int offsetXPoint = (measuredWidth - params.getRightWidth()) / points.size();

        drawback(canvas, offsetY, offsetXText);
        transformPoint(offsetXPoint, offsetY);
        drawLines(canvas);
    }

    /**
     * 按照坐标点,绘制普通点和最高点
     * 并且实现点和点之间的连线
     */
    private void drawLines(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(params.getLineWidth());
        paint.setColor(params.getLineColor());
        paint.setStyle(Paint.Style.STROKE); //绘制空心圆
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);

        //荧光线  模糊的线
        if (params.isOpenBlurMask()) {
            BlurMaskFilter maskFilter = new BlurMaskFilter(params.getBlurMaskWidth(), BlurMaskFilter.Blur.SOLID);
            paint.setMaskFilter(maskFilter);
        }
        //画线
        for (int i = 0; i < plist.size() - 1; i++) {
            Point pointStart = plist.get(i).getPoint();
            Point pointEnd = plist.get(i + 1).getPoint();

            canvas.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y, paint);
        }

        //空心圆设置
        paint.setColor(params.getHollowCircleColor());
        paint.setStrokeWidth(params.getLineWidth());
        paint.setMaskFilter(null);

        Paint paintMax = new Paint();
        paintMax.setColor(params.getSolidCircleColor());
        paintMax.setStrokeWidth(params.getLineWidth());
        paintMax.setStyle(Paint.Style.FILL);
        paintMax.setAntiAlias(true);

        //绘制实心白圆  避免引起的视觉差
        Paint paintWhite = new Paint();
        paintWhite.setColor(Color.parseColor("#FFFFFF"));
        paintWhite.setStyle(Paint.Style.FILL);
        paintWhite.setAntiAlias(true);
        //画点和最高点   采用内部一个白背景的方式 避免移动线的起始点导致线的路径怪异,空心圆内有路径线引起的视觉差异
        for (int i = 0; i < plist.size(); i++) {
            DrawPoint point = plist.get(i);
            Point pointStart = point.getPoint();
            if (point.isMax()) {
                canvas.drawCircle(pointStart.x, pointStart.y, params.getSolidCircleRadius(), paintMax);
            } else {
                canvas.drawCircle(pointStart.x, pointStart.y, params.getHollowCircleRadius(), paintWhite);
                canvas.drawCircle(pointStart.x, pointStart.y, params.getHollowCircleRadius(), paint);
            }
        }

        //绘制最高点的背景图和数字属性
        DrawPoint point = plist.get(maxPoi);
        paintWhite.setColor(params.getMaxTextColor());
        String strMaxValue = new BigDecimal(points.get(maxPoi)).setScale(2, RoundingMode.HALF_DOWN).toString();
        paintWhite.setTextSize(params.getMaxTextSize());
        paintWhite.setStrokeWidth(params.getLineWidth());

        Rect rect = new Rect();
        paintWhite.getTextBounds(strMaxValue, 0, strMaxValue.length(), rect);
        float valueWidth = paintWhite.measureText(strMaxValue);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) (valueWidth + params.getBitmapPadding() * 2),
                                                        params.getMaxBackBitmapHeight(), true);
        canvas.drawBitmap(scaledBitmap, point.getPoint().x - (scaledBitmap.getWidth() / 2),
                          point.getPoint().y - (scaledBitmap.getHeight() + params.getMaxBackBitmapBottom()), paint);

        canvas.drawText(strMaxValue, point.getPoint().x - (valueWidth / 2),
                        point.getPoint().y - (params.getMaxTextBottom()), paintWhite);
    }

    //背景横线,底部节点字,左侧0
    private void drawback(Canvas canvas, int offsetY, int offsetX) {
        //左上角 0
        Paint paintline = new Paint();
        paintline.setColor(params.getBackLineColor());
        paintline.setStrokeWidth(params.getBackLineWidth());
        for (int i = 0; i < 5; i++) {
            canvas.drawLine(params.getRightWidth(), (params.getTopSpace() + offsetY * i), getMeasuredWidth(),
                            (params.getTopSpace() + offsetY * i), paintline);
        }

        Paint paintZI = new Paint();
        paintZI.setColor(params.getRightTextColor());
        paintZI.setTextSize(params.getRightTextSize());
        paintZI.setStrokeWidth(params.getBackLineWidth());
        Rect rect = new Rect();
        paintZI.getTextBounds("0", 0, "0".length(), rect);
        canvas.drawText("0", params.getRightWidth() / 2, getMeasuredHeight() - params.getBottomMargin(), paintZI);

        paintZI.setColor(params.getBottomTextColor());
        paintZI.setTextSize(params.getBottomTextSize());
        for (int i = 0; i < 7; i++) {
            //小线段
            canvas.drawLine((params.getRightWidth() + offsetX / 2 + offsetX * i), (params.getTopSpace() + offsetY * 4),
                            (params.getRightWidth() + offsetX / 2 + offsetX * i),
                            (params.getTopSpace() + offsetY * 4 - params.getMinLineHeight()), paintline);
            String num = dateArr.get(i);
            float textWidth = paintZI.measureText(num);
            //底部栏位的文字
            canvas.drawText(num, (params.getRightWidth() + offsetX / 2 + offsetX * i) - textWidth / 2,
                            (params.getTopSpace() + offsetY * 4 + params.getBottomTextTopMargin()), paintZI);
        }

    }

    public static class DrawPoint {

        private Point point;
        private boolean isMax;

        public Point getPoint() {
            return point;
        }

        public void setPoint(Point point) {
            this.point = point;
        }

        public boolean isMax() {
            return isMax;
        }

        public void setMax(boolean max) {
            isMax = max;
        }

        public void setPoint(int x, int y) {
            this.point = new Point(x, y);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                LogTag.d("ACTION_UP 位置:" + event.getX() + "--" + event.getY());
                drawPoint(new BigDecimal(event.getX()).intValue(), new BigDecimal(event.getY()).intValue());
                break;
            case MotionEvent.ACTION_CANCEL:
                //焦点被外部scrollview 抢走时触发
                LogTag.d("ACTION_CANCEL 位置:" + event.getX() + "--" + event.getY());
                LogTag.d("ACTION_CANCEL 位置:" + event.getRawX() + "--" + event.getRawY());
                drawPoint(new BigDecimal(event.getX()).intValue(), new BigDecimal(event.getY()).intValue());
                break;
        }
        return true;
    }


}
