package com.testerkit.common.model;

public class RectInfo {
    public int left;
    public int top;
    public int right;
    public int bottom;

    public RectInfo(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RectInfo r = (RectInfo) o;
        return left == r.left && top == r.top && right == r.right && bottom == r.bottom;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append("Rect("); sb.append(left); sb.append(", ");
        sb.append(top); sb.append(" - "); sb.append(right);
        sb.append(", "); sb.append(bottom); sb.append(")");
        return sb.toString();
    }

    /**
     * Return a string representation of the rectangle in a compact form.
     */
    public String toShortString() {
        return toShortString(new StringBuilder(32));
    }

    /**
     * Return a string representation of the rectangle in a compact form.
     * @hide
     */
    public String toShortString(StringBuilder sb) {
        sb.setLength(0);
        sb.append('['); sb.append(left); sb.append(',');
        sb.append(top); sb.append("]["); sb.append(right);
        sb.append(','); sb.append(bottom); sb.append(']');
        return sb.toString();
    }

    /**
     * Returns true if the rectangle is empty (left >= right or top >= bottom)
     */
    public final boolean isEmpty() {
        return left >= right || top >= bottom;
    }

    /**
     * @return the rectangle's width. This does not check for a valid rectangle
     * (i.e. left <= right) so the result may be negative.
     */
    public final int width() {
        return right - left;
    }

    /**
     * @return the rectangle's height. This does not check for a valid rectangle
     * (i.e. top <= bottom) so the result may be negative.
     */
    public final int height() {
        return bottom - top;
    }

    /**
     * @return the horizontal center of the rectangle. If the computed value
     *         is fractional, this method returns the largest integer that is
     *         less than the computed value.
     */
    public final int centerX() {
        return (left + right) >> 1;
    }

    /**
     * @return the vertical center of the rectangle. If the computed value
     *         is fractional, this method returns the largest integer that is
     *         less than the computed value.
     */
    public final int centerY() {
        return (top + bottom) >> 1;
    }
}
