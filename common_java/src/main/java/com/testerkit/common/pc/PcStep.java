package com.testerkit.common.pc;

import com.testerkit.common.json.NodeJson;
import com.testerkit.common.json.PointJson;

/**
 * @atuthor able
 */
public class PcStep {
    private int keyCode;
    private String script;
    private PcPoint point;
    private PointJson offsetPoint;

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public PcPoint getPoint() {
        return point;
    }

    public void setPoint(PcPoint point) {
        this.point = point;
    }

    public PointJson getOffsetPoint() {
        return offsetPoint;
    }

    public void setOffsetPoint(PointJson offsetPoint) {
        this.offsetPoint = offsetPoint;
    }
}
