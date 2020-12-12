package orders;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity//节点
public class Order {
	
	@GraphId//图id，必须是Long型
	private Long id;
	
	private String customer;
	
	private String type;
	
	@RelatedTo(type="HAS_ITEMS")//与条目之间的关联关系
	private Set<Item> items = new LinkedHashSet<Item>();

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public Collection<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public Long getId() {
		return id;
	}
	
	/**
	 * @NodeEntity 将Java类型声明为节点实体
	 * @RelationshipEntity 将Java类型声明为关联关系实体
	 * @StartNode 将某个属性声明为关联关系实体的开始节点
	 * @EndNode 将某个属性声明为关联关系实体的结束节点
	 * @Fetch 将实体的属性声明为立即加载
	 * @GraphId 将某个属性设置为实体的ID域（这个域的类型必须是 Long）
	 * @GraphProperty 明确声明某个属性
	 * @GraphTraversal 声明某个属性会自动提供一个iterable元素，这个元素是图遍历所构建的
	 * @Indexed 声明某个属性应该被索引
	 * @Labels 为@NodeEntity声明标签
	 * @Query 声明某个属性会自动提供一个iterable元素，这个元素是执行给定的Cypher查询所构建的
	 * @QueryResult 声明某个Java或接口能够持有查询的结果
	 * @RelatedTo 通过某个属性，声明当前的 @NodeEntity 与另外一个@NodeEntity之 间的关联关系
	 * @RelatedToVia 在 @NodeEntity 上声明某个属性，指定其引用该节点所属的某一个 @RelationshipEntity
	 * @RelationshipType 将某个域声明为关联实体类型
	 * @ResultColumn 在带有@QueryResult注解的类型上，将某个属性声明为获取查询结果集中的某个特定列
	 */
	
}
