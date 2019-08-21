package com.testerkit.uia.handlers.dump;

import com.testerkit.common.exceptions.UIAException;
import com.testerkit.uia.requests.IRequest;
import com.testerkit.uia.requests.http.AppiumResponse;
import com.testerkit.common.enums.WDStatus;
import com.testerkit.common.log.Logger;
import com.testerkit.common.utils.ReflectionUtil;

import com.testerkit.uia.utils.dumps.XMLHierarchy;


/**
 * Get page source. Return as string of XML doc
 */
public class SourceNode extends SourceEvent {

    public SourceNode(String mappedUri) {
        super(mappedUri);
    }

    protected AppiumResponse executeDumpEvent(IRequest request) {
        try {
            ReflectionUtil.clearAccessibilityCache();

            String xmlString = XMLHierarchy.getRawXMLHierarchyStr();
            return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, xmlString);

        } catch (UIAException e) {
            Logger.error("Exception while performing dump SourceNode action: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        }

    }
}
