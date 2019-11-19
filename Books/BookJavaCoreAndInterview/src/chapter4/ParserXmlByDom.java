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
        //创建DOM工厂
		DocumentBuilderFactory domFactory=DocumentBuilderFactory.newInstance();
		InputStream input = null;
        try {
            //通过DOM工厂获得DOM解析器
            DocumentBuilder domBuilder=domFactory.newDocumentBuilder();
            //把XML文档转化为输入流
            input=new FileInputStream("src/book.xml");            
            //解析XML文档的输入流，得到一个Document
            Document doc=domBuilder.parse(input);
            //得到XML文档的根节点，只有根节点是org.w3c.dom.Element类型
            Element root=doc.getDocumentElement();
            // 得到子节点
            NodeList books = root.getChildNodes();
            
            if(books!=null){
                for(int i=0;i<books.getLength();i++){
                    Node book=books.item(i);
                    //如果是属性                      
                    if(book.getNodeType()==Node.ELEMENT_NODE){
                        String id=book.getAttributes().getNamedItem("id").getNodeValue();
                        System.out.println("id is:" + id);
                        //遍历book下的子节点
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
