package com.testerkit.common.utils;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class XmlUtil {
    /**
     * 根据xpath查找node
     *
     * @param xmlSource 文档对象
     * @param xpath
     * @return 返回查找到的多个simple xpath
     */
    public static List<String> findByXpath(String xmlSource, String xpath) {
        List<String> results = new ArrayList<String>();
        if (StringUtil.isNullOrEmpty(xmlSource) || StringUtil.isNullOrEmpty(xpath)) {
            return results;
        }
        XPathFactory factory = XPathFactory.newInstance();
        XPath xp = factory.newXPath();
        ByteArrayInputStream is = new ByteArrayInputStream(xmlSource.getBytes());
        InputSource doc = new InputSource(new InputStreamReader(is));
        try {
            //String nxpath = XPathParser.parse(xpath);
            //Log.i(Utils.tag, "search by xpath:" + nxpath);
            NodeList nodelist = (NodeList) xp.evaluate(xpath, doc, XPathConstants.NODESET);
            XPathExpression xpe = xp.compile(       "@xpath");
            if (nodelist.getLength() == 0) {
                return results;
            }
            for (int i = 0; i < nodelist.getLength(); i++) {
                Object result = xpe.evaluate(nodelist.item(i), XPathConstants.STRING);
                if (result == null || StringUtil.isNullOrEmpty(result.toString())) {
                    continue;
                }
                results.add(result.toString());
            }
            return results;
        } catch (XPathExpressionException e) {
            //Logger.error(e.getLocalizedMessage(), e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                //Logger.error(e.getMessage(), e);
            }
        }
        return results;
    }
}
