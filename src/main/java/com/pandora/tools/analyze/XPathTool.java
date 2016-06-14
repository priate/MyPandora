package com.pandora.tools.analyze;

import java.util.ArrayList;
import java.util.List;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

public class XPathTool {

	/**
	 * 根据网页内容和Xpath截取数据
	 * @param content	网页内容
	 * @param xpath		Xpath
	 * @param valueType	获取属性(null为文本数据 其他属性需指定)
	 * @return
	 */
	public static List<String> analyzerByXpath(String content, String xpath, String valueType){
		List<String> xList = new ArrayList<String>();
		HtmlCleaner htmlCleaner = new HtmlCleaner();
		TagNode tagNode = htmlCleaner.clean(content);
		try {
			Object [] objects = tagNode.evaluateXPath(xpath);
			for(Object object:objects){
				TagNode partNode = (TagNode) object;
				if(valueType==null)
					xList.add(partNode.getText().toString());
				else
					xList.add(partNode.getAttributeByName(valueType));
			}
		} catch (XPatherException e) {
			e.printStackTrace();
		}
		return xList;
	}
}
