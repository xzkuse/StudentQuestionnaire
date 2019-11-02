package com.mallcloud.utils.weight.chart;

/**
 * @author xzk
 * @data 2019/4/12
 * @email xiezhengkun@beyondsoft.com
 * @remark  折线图的变量
 */
public class DrawChartParams {

    //背景线的颜色
    private int backLineColor;
    //背景线的宽度
    private int backLineWidth;
    //左竖边的字大小
    private int rightTextSize;
    //左竖边的字颜色
    private int rightTextColor;
    //左边的距离
    private int rightWidth;
    //底部字的大小
    private int bottomTextSize;
    //底部字的颜色
    private int bottomTextColor;
    //底部字的距离头顶的距离
    private int bottomTextTopMargin;
    //底部线距离底部的距离
    private int bottomMargin;
    //主体折线的颜色
    private int lineColor;
    //主体折线的宽度
    private int lineWidth;
    //空心圆的颜色
    private int hollowCircleColor;
    //空心圆的半径
    private int hollowCircleRadius;
    //实心圆的半径
    private int solidCircleRadius;
    //空心圆的颜色
    private int solidCircleColor;

    //最大值显示的背景图
    private int maxBackBitmap;
    //最大值显示的背景图宽度  由于宽度是根据字体的宽度决定的,所以此属性废弃
//    private int maxBackBitmapWidth;
    //最大值显示的背景图高度
    private int maxBackBitmapHeight;
    //最大值显示的背景图底部的距离
    private int maxBackBitmapBottom;

    //最大值显示的字体颜色
    private int maxTextColor;
    //最大值显示的字体大小
    private int maxTextSize;
    //最大值显示的字体距离底部的高度
    private int maxTextBottom;

    //折线是否开启模糊
    private boolean isOpenBlurMask;
    //折线的模糊宽度
    private int blurMaskWidth;
    //最上面的线,距离顶部的位置
    private int topSpace;
    //最大值图形的内边距
    private int bitmapPadding;
    //底部列的校址示线段高度
    private int minLineHeight;

    //点击范围精度  距离，增加切换显示点的有效范围
    private int clickDistance;


    public void commonLineColor(int colorInt){
        lineColor=colorInt;
        hollowCircleColor=colorInt;
        solidCircleColor=colorInt;
        maxTextColor=colorInt;
    }

    public int getBackLineColor() {
        return backLineColor;
    }

    public void setBackLineColor(int backLineColor) {
        this.backLineColor = backLineColor;
    }

    public int getBackLineWidth() {
        return backLineWidth;
    }

    public void setBackLineWidth(int backLineWidth) {
        this.backLineWidth = backLineWidth;
    }

    public int getRightTextSize() {
        return rightTextSize;
    }

    public void setRightTextSize(int rightTextSize) {
        this.rightTextSize = rightTextSize;
    }

    public int getRightTextColor() {
        return rightTextColor;
    }

    public void setRightTextColor(int rightTextColor) {
        this.rightTextColor = rightTextColor;
    }

    public int getRightWidth() {
        return rightWidth;
    }

    public void setRightWidth(int rightWidth) {
        this.rightWidth = rightWidth;
    }

    public int getBottomTextSize() {
        return bottomTextSize;
    }

    public void setBottomTextSize(int bottomTextSize) {
        this.bottomTextSize = bottomTextSize;
    }

    public int getBottomTextColor() {
        return bottomTextColor;
    }

    public void setBottomTextColor(int bottomTextColor) {
        this.bottomTextColor = bottomTextColor;
    }

    public int getBottomTextTopMargin() {
        return bottomTextTopMargin;
    }

    public void setBottomTextTopMargin(int bottomTextTopMargin) {
        this.bottomTextTopMargin = bottomTextTopMargin;
    }

    public int getBottomMargin() {
        return bottomMargin;
    }

    public void setBottomMargin(int bottomMargin) {
        this.bottomMargin = bottomMargin;
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public int getHollowCircleColor() {
        return hollowCircleColor;
    }

    public void setHollowCircleColor(int hollowCircleColor) {
        this.hollowCircleColor = hollowCircleColor;
    }

    public int getHollowCircleRadius() {
        return hollowCircleRadius;
    }

    public void setHollowCircleRadius(int hollowCircleRadius) {
        this.hollowCircleRadius = hollowCircleRadius;
    }

    public int getSolidCircleRadius() {
        return solidCircleRadius;
    }

    public void setSolidCircleRadius(int solidCircleRadius) {
        this.solidCircleRadius = solidCircleRadius;
    }

    public int getSolidCircleColor() {
        return solidCircleColor;
    }

    public void setSolidCircleColor(int solidCircleColor) {
        this.solidCircleColor = solidCircleColor;
    }

    public int getMaxBackBitmap() {
        return maxBackBitmap;
    }

    public void setMaxBackBitmap(int maxBackBitmap) {
        this.maxBackBitmap = maxBackBitmap;
    }

    public int getMaxBackBitmapHeight() {
        return maxBackBitmapHeight;
    }

    public void setMaxBackBitmapHeight(int maxBackBitmapHeight) {
        this.maxBackBitmapHeight = maxBackBitmapHeight;
    }

    public int getMaxBackBitmapBottom() {
        return maxBackBitmapBottom;
    }

    public void setMaxBackBitmapBottom(int maxBackBitmapBottom) {
        this.maxBackBitmapBottom = maxBackBitmapBottom;
    }

    public int getMaxTextColor() {
        return maxTextColor;
    }

    public void setMaxTextColor(int maxTextColor) {
        this.maxTextColor = maxTextColor;
    }

    public int getMaxTextSize() {
        return maxTextSize;
    }

    public void setMaxTextSize(int maxTextSize) {
        this.maxTextSize = maxTextSize;
    }

    public int getMaxTextBottom() {
        return maxTextBottom;
    }

    public void setMaxTextBottom(int maxTextBottom) {
        this.maxTextBottom = maxTextBottom;
    }

    public boolean isOpenBlurMask() {
        return isOpenBlurMask;
    }

    public void setOpenBlurMask(boolean openBlurMask) {
        isOpenBlurMask = openBlurMask;
    }

    public int getBlurMaskWidth() {
        return blurMaskWidth;
    }

    public void setBlurMaskWidth(int blurMaskWidth) {
        this.blurMaskWidth = blurMaskWidth;
    }

    public int getTopSpace() {
        return topSpace;
    }

    public void setTopSpace(int topSpace) {
        this.topSpace = topSpace;
    }

    public int getBitmapPadding() {
        return bitmapPadding;
    }

    public void setBitmapPadding(int bitmapPadding) {
        this.bitmapPadding = bitmapPadding;
    }

    public int getMinLineHeight() {
        return minLineHeight;
    }

    public void setMinLineHeight(int minLineHeight) {
        this.minLineHeight = minLineHeight;
    }

    public int getClickDistance() {
        return clickDistance;
    }

    public void setClickDistance(int clickDistance) {
        this.clickDistance = clickDistance;
    }
}
