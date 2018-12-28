package com.testerkit.uia.handlers.dump;

import com.testerkit.uia.exceptions.UIAException;
import com.testerkit.uia.requests.IRequest;
import com.testerkit.uia.requests.http.AppiumResponse;
import com.testerkit.uia.servers.WDStatus;
import com.testerkit.common.log.Logger;
import com.testerkit.uia.utils.ReflectionUtils;
import com.testerkit.uia.utils.dumps.AccessibilityNodeInfoDumper;
import com.testerkit.uia.utils.dumps.XMLHierarchy;

import org.w3c.dom.Document;

import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

//import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
//import io.appium.uiautomator2.handler.request.SafeRequestHandler;
//import io.appium.uiautomator2.http.AppiumResponse;
//import io.appium.uiautomator2.http.IHttpRequest;
//import io.appium.uiautomator2.server.WDStatus;
//import io.appium.uiautomator2.utils.Logger;
//import io.appium.uiautomator2.utils.ReflectionUtils;
//import io.appium.uiautomator2.utils.XMLHierarchy;

/**
 * Get page source. Return as string of XML doc
 */
public class SourceNode extends SourceEvent {

    public SourceNode(String mappedUri) {
        super(mappedUri);
    }

    protected AppiumResponse executeDumpEvent(IRequest request) {
        try {
            ReflectionUtils.clearAccessibilityCache();

            String xmlString = XMLHierarchy.getRawXMLHierarchyStr();
            return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, xmlString);

        } catch (UIAException e) {
            Logger.error("Exception while performing dump SourceNode action: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        }

    }
}
