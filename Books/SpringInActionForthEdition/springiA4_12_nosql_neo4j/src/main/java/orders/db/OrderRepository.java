package orders.db;

import java.util.List;

import orders.Order;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface OrderRepository extends GraphRepository<Order> {
	
	List<Order> findByCustomer(String customer);
	
	List<Order> findByCustomerLike(String customer);

	List<Order> findByCustomerAndType(String customer, String type);

	List<Order> getByType(String type);
		
	//自定义查询，Cypher查询
//	@Query("match (o:Order)-[:HAS_ITEMS]->(i:Item) "
//			+ "where i.product='Spring in Action' return o ")
//	List<Order> findSiAOrders();
	
	/*
	 * 混合查询：
	 * 当Spring Data Neo4j为Repository接口生成实现的时候，它还会查找名字
	 * 与接口相同，并且添加了Impl后缀的一个类。如果这个类存在的话，Spring Data Neo4j将会
	 * 把它的方法与Spring Data Neo4j所生成的方法合并在一起。
	 * 如同Spring Data Jpa。
	 * 
	 * 同样，Impl后缀只是默认的做法，可在配置@EnableMongoRepositories时指定后缀
	 */

}
