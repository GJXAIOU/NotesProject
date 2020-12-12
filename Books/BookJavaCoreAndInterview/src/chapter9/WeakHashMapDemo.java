import java.util.HashMap;  
import java.util.Iterator;  
import java.util.Map;  
import java.util.WeakHashMap;    
public class WeakHashMapDemo {  
    public static void main(String[] args) throws Exception {  
        String a = new String("a");  
        String b = new String("b");  
        Map weakmap = new WeakHashMap();  
        Map map = new HashMap();  
        map.put(a, "aaa");  
        map.put(b, "bbb");            
        weakmap.put(a, "aaa");  
        weakmap.put(b, "bbb");            
        map.remove(a);            
        a=null;  
        b=null;            
        System.gc();  
        Iterator i = map.entrySet().iterator();  
        while (i.hasNext()) {  
            Map.Entry en = (Map.Entry)i.next();  
            System.out.println("map:"+en.getKey()+":"+en.getValue());  
        }    
        Iterator j = weakmap.entrySet().iterator();  
        while (j.hasNext()) {  
            Map.Entry en = (Map.Entry)j.next();  
            System.out.println("weakmap:"+en.getKey()+":"+en.getValue());                
        }  
    }  
}
