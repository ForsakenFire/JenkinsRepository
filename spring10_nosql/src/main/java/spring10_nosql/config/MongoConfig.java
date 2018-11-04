package spring10_nosql.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * 使用MongoFactoryBean已过时，推荐直接继承AbstractMongoConfiguration并重写方法。<br>
 * 隐式创建MongoTemplate Bean。 
 * @author TD
 *
 */
@Configuration
@EnableMongoRepositories(basePackages="spring10_nosql.dao")
public class MongoConfig extends AbstractMongoConfiguration{

	@Autowired
	Environment env;
	
	@Override
	protected String getDatabaseName() {
		return env.getProperty("mongo.dbname");
	}

	/**
	 * 通过MongoClient实例创建mongo需要处理可能抛出的异常
	 */
	@Override
	public Mongo mongo() throws Exception {
		String address = env.getProperty("mongo.address");
		int port = Integer.parseInt(env.getProperty("mongo.port"));
		String password = env.getProperty("mongo.password");
		String userName = env.getProperty("mongo.username");
		String dbName = env.getProperty("mongo.dbname");
		MongoCredential credential = MongoCredential.createCredential(userName, dbName, password.toCharArray());
		return new MongoClient(new ServerAddress(address,port),Arrays.asList(credential));
	}



	
}
