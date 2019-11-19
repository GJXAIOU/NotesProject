package orders.db;

import java.util.List;

import orders.Order;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

public interface OrderRepository extends MongoRepository<Order, String> {
	
	List<Order> findByCustomer(String customer);
	
	List<Order> findByCustomerLike(String customer);

	List<Order> findByCustomerAndType(String customer, String type);

	List<Order> getByType(String type);
		
	//自定义查询，json格式
	@Query("{customer:'Chuck Wagon'}")
	List<Order> findChucksOrders();
	
	/*
	 * 混合查询：
	 * 当Spring Data MongoDB为Repository接口生成实现的时候，它还会查找名字
	 * 与接口相同，并且添加了Impl后缀的一个类。如果这个类存在的话，Spring Data MongoDB将会
	 * 把它的方法与Spring Data MongoDB所生成的方法合并在一起。
	 * 如同Spring Data Jpa。
	 * 
	 * 同样，Impl后缀只是默认的做法，可在配置@EnableMongoRepositories时指定后缀
	 */

}
