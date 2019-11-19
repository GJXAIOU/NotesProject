package spittr.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spittr.domain.Spitter;

/**
 * Repository interface with operations for {@link Spitter} persistence.
 * @author habuma
 */
public interface SpitterRepository extends JpaRepository<Spitter, Long>, SpitterSweeper {
	
	/*
	 * JpaRepository扩展自Repository标记接口，故SpitterRepository就传递性地
	 * 扩展了Repository接口，也就是Repository扫描时所要查找的接口
	 * 
	 * 混合查询：
	 * 当Spring Data JPA为Repository接口生成实现的时候，它还会查找名字
	 * 与接口相同，并且添加了Impl后缀的一个类。如果这个类存在的话，Spring Data JPA将会
	 * 把它的方法与Spring Data JPA所生成的方法合并在一起。
	 * 对于SpitterRepository接口而言，要查找的类名为SpitterRepositoryImpl。
	 * 
	 * Spring Data JPA将实现类与接口关联起来是基于接口的名称。但是，Impl后缀只是默认的做法，
	 * 如果你想使用其他后缀的话，只需在配置@EnableJpaRepositories的时候，
	 * 设置repositoryImplementationPostfix属性即可
	 */
	
	Spitter findByUsername(String username);
	
	List<Spitter> findByUsernameOrFullNameLike(String username, String fullName);
	
	//自定义查询：不符合Spring Data的方法命名约定，可以用@Query注解来指定sql
//	@Query("select s from Spitter s where s.email like '%gmail.com' ")
//	List<Spitter> findAllGmailSpitters();

}
