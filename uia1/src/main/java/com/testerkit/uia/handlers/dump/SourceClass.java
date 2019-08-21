package com.testerkit.uia.handlers.dump;

import com.testerkit.common.exceptions.UIAException;
import com.testerkit.uia.handlers.request.SafeRequestHandler;
import com.testerkit.uia.requests.IRequest;
import com.testerkit.uia.requests.http.AppiumResponse;
import com.testerkit.uia.requests.http.IHttpRequest;
import com.testerkit.common.enums.WDStatus;
import com.testerkit.common.log.Logger;
import com.testerkit.common.utils.ReflectionUtil;
import com.testerkit.uia.utils.dumps.XMLHierarchy;

import org.w3c.dom.Document;

import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


/**
 * Get page source. Return as string of XML doc
 */
public class SourceClass extends SourceEvent {

    public SourceClass(String mappedUri) {
        super(mappedUri);
    }

    protected AppiumResponse executeDumpEvent(IRequest request) {
        try {
            ReflectionUtil.clearAccessibilityCache();

            final Document doc = (Document) XMLHierarchy.getFormattedXMLDoc();
            final TransformerFactory tf = TransformerFactory.newInstance();
            final StringWriter writer = new StringWriter();
            Transformer transformer = tf.newTransformer();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String xmlString = writer.getBuffer().toString();
            return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, xmlString);

        } catch (final TransformerConfigurationException e) {
            Logger.error("Unable to handle the request:" + e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, "Something went terribly wrong while converting xml document to string:" + e);
        } catch (final TransformerException e) {
            Logger.error("Unable to handle the request:" + e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, "Could not parse xml hierarchy to string: " + e);
        } catch (UIAException e) {
            Logger.error("Exception while performing dump SourceClass action: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        }

    }
}
