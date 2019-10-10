package com.testerkit.common.model;

public class PaddingInfo {
    // 是一个比例的值
    public float left = 1;
    public float top = 1;
    public float right = 1;
    public float bottom = 1;

    public PaddingInfo(float left, float top, float right, float bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaddingInfo r = (PaddingInfo) o;
        return left == r.left && top == r.top && right == r.right && bottom == r.bottom;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append("PaddingInfo("); sb.append(left); sb.append(", ");
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



}
