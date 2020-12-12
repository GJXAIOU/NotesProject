import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ParserXmlByDom {

	public static void main(String[] args) {
        //����DOM����
		DocumentBuilderFactory domFactory=DocumentBuilderFactory.newInstance();
		InputStream input = null;
        try {
            //ͨ��DOM�������DOM������
            DocumentBuilder domBuilder=domFactory.newDocumentBuilder();
            //��XML�ĵ�ת��Ϊ������
            input=new FileInputStream("src/book.xml");            
            //����XML�ĵ������������õ�һ��Document
            Document doc=domBuilder.parse(input);
            //�õ�XML�ĵ��ĸ��ڵ㣬ֻ�и��ڵ���org.w3c.dom.Element����
            Element root=doc.getDocumentElement();
            // �õ��ӽڵ�
            NodeList books = root.getChildNodes();
            
            if(books!=null){
                for(int i=0;i<books.getLength();i++){
                    Node book=books.item(i);
                    //���������                      
                    if(book.getNodeType()==Node.ELEMENT_NODE){
                        String id=book.getAttributes().getNamedItem("id").getNodeValue();
                        System.out.println("id is:" + id);
                        //����book�µ��ӽڵ�
                        for(Node node=book.getFirstChild();node!=null;node=node.getNextSibling()){
                            if(node.getNodeType()==Node.ELEMENT_NODE){
                                if(node.getNodeName().equals("name")){
                                    String name=node.getFirstChild().getNodeValue();
                                    System.out.println("name is:" + name);                                    
                                }
                                if(node.getNodeName().equals("price")){
                                    String price=node.getFirstChild().getNodeValue();
                                    System.out.println("price is:" + price);
                                }
                                if(node.getNodeName().equals("memo")){
                                    String memo=node.getFirstChild().getNodeValue();
                                    System.out.println("memo is:" + memo);
                                }
                            }
                        }
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {			
			e.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}
        finally
        {
        	try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }


	}

}
