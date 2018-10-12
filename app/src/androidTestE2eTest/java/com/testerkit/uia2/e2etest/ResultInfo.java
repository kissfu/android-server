package com.testerkit.uia2.e2etest;



/**
 * Created by able on 2017/7/3.
 */
public class ResultInfo {

    //region privates
    private StateResultEnum stateResult = StateResultEnum.SUCCESS;
    private String title = "";
    private String detail = "";

    //endregion

    //region properties

    public StateResultEnum getStateResult() {
        return stateResult;
    }

    public void setStateResult(StateResultEnum stateResult) {
        this.stateResult = stateResult;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    //endregion


    public ResultInfo() {
    }

    public ResultInfo(StateResultEnum stateResult, String title, String detail) {
        this.stateResult = stateResult;
        this.title = title;
        this.detail = detail;
    }
}
