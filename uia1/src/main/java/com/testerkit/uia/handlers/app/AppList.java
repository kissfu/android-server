package com.testerkit.uia.handlers.app;


import com.testerkit.common.constants.ConstantResult;
import com.testerkit.common.enums.WDStatus;
import com.testerkit.common.json.ResponseJson;
import com.testerkit.common.utils.GsonUtil;
import com.testerkit.uia.BaseContext;

import java.util.List;

public class AppList extends AppEvent {

    public AppList(String mappedUri) {
        super(mappedUri);
    }


    @Override
    protected ResponseJson executePressEvent() {
        ResponseJson resp = new ResponseJson();
        try {
            List<com.testerkit.common.model.AppInfo> apps = BaseContext.getInstance().getDevice().getAppList();
            resp.setValue(GsonUtil.gsonString(apps));
            resp.setKey("执行成功！");
            resp.setStatus(WDStatus.SUCCESS.code());
        } catch (Exception e) {
            resp.setKey(ConstantResult.EXCEPTION_DEVICE);
            resp.setValue(e.getMessage());

        }


        return resp;
    }
}
