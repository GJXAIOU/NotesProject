package orders.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories(basePackages = "orders.db")//启用Repository自动生成功能
//@PropertySource("classpath:mongodb.properties")
public class MongoConfig extends AbstractMongoConfiguration {
	
//	@Autowired
//	private Environment env;

	@Override
	protected String getDatabaseName() {
		return "OrdersDB";
	}
	
	//默认端口27017
	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient();
		//创建MongoDB凭证
//		MongoCredential credential = 
//				MongoCredential.createMongoCRCredential(
//						env.getProperty("mongo.username"), 
//						"OrdersDB", 
//						env.getProperty("mongo.password").toCharArray());
//		//创建MongoClient，
//		return new MongoClient(new ServerAddress("localhost", 37017), 
//				Arrays.asList(credential));
	}
	
	/*
	 * 继承AbstractMongoConfiguration后，将会隐式地创建MongoTemplate bean
	 * 因为mongo()会创建MongoClient实例并抛出Exception，故可不使用MongoFactoryBean
	 */
	//不必担心MongoClient的UnknownHostException
//	@Bean
//	public MongoFactoryBean mongo() {
//		MongoFactoryBean mongo = new MongoFactoryBean();
//		mongo.setHost("localhost");
//		return mongo;
//	}
//	
//	@Bean
//	public MongoOperations mongoTemplate(Mongo mongo) {
//		return new MongoTemplate(mongo, "OrdersDB");
//	}

}