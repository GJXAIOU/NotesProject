package com.gjxaiou;

import com.gjxaiou.bean.Article;
import com.gjxaiou.bean.Book;
import com.gjxaiou.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot03ElasticsearchApplicationTests {

    @Autowired
    JestClient jestClient;

    /**
     * 测试 Jest 方式
     */
    @Test
    public void contextLoads() {
        //1、给Es中索引（保存）一个文档；
        Article article = new Article();
        article.setId(1);
        article.setTitle("好消息");
        article.setAuthor("zhangsan");
        article.setContent("Hello World");

        //构建一个索引功能
        Index index = new Index.Builder(article).index("atguigu").type("news").build();
        try {
            //执行
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //测试搜索
    @Test
    public void search() {

        //使用查询表达式
        String json = "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"content\" : \"hello\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        //更多操作：https://github.com/searchbox-io/Jest/tree/master/jest

        //构建搜索功能
        Search search = new Search.Builder(json).addIndex("atguigu").addType("news").build();

        //执行
        try {
            SearchResult result = jestClient.execute(search);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Autowired
    BookRepository bookRepository;

    /**
     * 测试 Spring Data 的自定义 ElasticsearchRepository 方式
     */
    @Test
    public void test02() {
        // 插入测试
        Book book = new Book();
        book.setId(1);
        book.setBookName("西游记");
        book.setAuthor("吴承恩");
        bookRepository.index(book);

        // 查找
        for (Book book1 : bookRepository.findByBookNameLike("游")) {
            System.out.println(book1);
        }
    }

}