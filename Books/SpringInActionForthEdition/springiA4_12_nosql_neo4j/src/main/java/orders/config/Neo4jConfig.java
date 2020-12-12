package orders.config;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
//import org.springframework.data.neo4j.core.GraphDatabase;
//import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;

@Configuration
@EnableNeo4jRepositories(basePackages = "orders.db")//启用Repository自动生成功能
public class Neo4jConfig extends Neo4jConfiguration {

	public Neo4jConfig() {
		//设置模型的基础包
		setBasePackage("orders");//本例中将查找Order和Item
	}

	@Bean(destroyMethod="shutdown")
	public GraphDatabaseService graphDatabaseService() {	
		/*
		 * 配置嵌入式数据库
		 * 在Neo4j中，嵌入式数据库不要与内存数据库相混淆。
		 * 在这里，“嵌入式”指的是数据库引擎与应用运行在同一个JVM中，作为应用的一部分，
		 * 而不是独立的服务器。数据依然会持久化到文件系统中（在本例中，也就是“/tmp/graphdb”中）。
		 */
		return new GraphDatabaseFactory()
				.newEmbeddedDatabase("/tmp/graphdb");
	}
	
	//远程连接Neo4j服务器，使用RESTful API来访问
//	@Bean(destroyMethod="shutdown")
//	public GraphDatabase graphDatabaseService(Environment env) {	
//		return new SpringRestGraphDatabase(
//				"http://graphdbserver:7474/db/data/",
//				env.getProperty("db.username"),
//				env.getProperty("db.password"));
//	}
}