import com.gjxaiou.pojo.Airport;
import com.gjxaiou.service.impl.AirportServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-09-23-18:56
 */
public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        AirportServiceImpl airportServiceBean = applicationContext.getBean("airportService",
                AirportServiceImpl.class);
        List<Airport> airportList = airportServiceBean.show();
        System.out.println(airportList);
    }
}
