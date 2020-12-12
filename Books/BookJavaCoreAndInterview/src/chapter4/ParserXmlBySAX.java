import java.io.IOException;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.xml.parsers.*;


public class ParserXmlBySAX extends DefaultHandler{
	// ��¼��ǰ�������Ľڵ���
	private String tagName; 

	// ����������
	public static void main(String[] argv) {
		String uri = "src/book.xml";
		try {
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			ParserXmlBySAX myParser = new ParserXmlBySAX();
			SAXParser parser = parserFactory.newSAXParser();
			parser.parse(uri, myParser);
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (SAXException ex) {
			ex.printStackTrace();
		} catch (ParserConfigurationException ex) {
			ex.printStackTrace();
		} catch (FactoryConfigurationError ex) {
			ex.printStackTrace();
		}		
	}
	
	// �����ĵ�β
	public void endDocument() throws SAXException {
		System.out.println("endDocument");
	}
	// ������β
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		tagName = null;
	}
	// �����ĵ�����ʼ��
	public void startDocument() throws SAXException {
		System.out.println("startDocument");		
	}
	// ������ͷ
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		
		if ("book".equals(name)) { // ��һ���û�
            for (int i = 0; i < attributes.getLength(); i++) {
                System.out.println("attribute name is��" + attributes.getLocalName(i)
                        + "  attribute value��" + attributes.getValue(i)); 
            }            
        }
		tagName = name; 
	}

	public void characters(char[] ch, int start, int length)  
            throws SAXException {  
        if(this.tagName!=null){  
            String val=new String(ch,start,length);            
            
            if("name".equals(tagName))
            {
                System.out.println("name is:" + val);
            }
            if("price".equals(tagName))
            {
                System.out.println("price is:" + val);
            }
            if("memo".equals(tagName))
            {
                System.out.println("memo is:" + val);
            }
        }  
    }
}
