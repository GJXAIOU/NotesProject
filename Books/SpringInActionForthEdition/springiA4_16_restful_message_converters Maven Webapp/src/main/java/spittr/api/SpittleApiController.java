package spittr.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import spittr.Spittle;
import spittr.data.SpittleNotFoundException;
import spittr.data.SpittleRepository;

@RestController
@RequestMapping("/spittles")//它的方法所返回的对象将会通过消息转换机制，产生客户端所需的资源表述
public class SpittleApiController {

  private static final String MAX_LONG_AS_STRING = "9223372036854775807";

  private SpittleRepository spittleRepository;

  @Autowired
  public SpittleApiController(SpittleRepository spittleRepository) {
    this.spittleRepository = spittleRepository;
  }

  /*
   * produces="application/json"属性表明这个方法只处理预期输出为JSON的请求。也就是说，
   * 这个方法只会处理Accept头部信息包含“application/json”的请求。其他任何类型的请求，
   * 即使它的URL匹配指定的路径并且是GET请求   * 也不会被这个方法处理。这样的请求会被其他的方法来
   * 进行处理（如果存在适当方法的话），或者   * 返回客户端HTTP 406（Not Acceptable）响应。
   */
  @RequestMapping(method= {RequestMethod.GET,RequestMethod.HEAD}, produces="application/json")
  public List<Spittle> spittles(
      @RequestParam(value="max", defaultValue=MAX_LONG_AS_STRING) long max,
      @RequestParam(value="count", defaultValue="20") int count) {
    return spittleRepository.findSpittles(max, count);
  }
  
  @RequestMapping(value="/{id}", method=RequestMethod.GET, produces="application/json")
  public Spittle spittleById(@PathVariable Long id) {
    return spittleRepository.findOne(id);
  }
  
  /*
   * consumes属性的工作方式类似于produces，不过它会关注请求的Content-Type头部信息。它会
   * 告诉Spring这个方法只会处理对“/spittles”的POST请求，并且要求请求的Content-Type头部信息为
   * “application/json”。如果无法满足这些条件的话，会由其他方法（如果存在合适的方法的话）来处理请求。
   * 
   * @ResponseBody能够告诉Spring在把数据发送给客户端的时候，要使用某一个消息器，
   * 与之类似，@RequestBody也能告诉Spring查找一个消息转换器，将来自客户端的资源表述转换为对象。
   * 
   * 在处理器方法所得到的UriComponentsBuilder中，会预先配置已知的信息如host、端口以及Servlet内容。
   * 它会从处理器方法所对应的请求中获取这些基础信息
   */
  @RequestMapping(method=RequestMethod.POST, consumes="application/json")
//  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Spittle> saveSpittle(@RequestBody Spittle spittle, UriComponentsBuilder ucb) {
    Spittle saved = spittleRepository.save(spittle);
    
    HttpHeaders headers = new HttpHeaders();
    URI locationUri = ucb.path("/spittles/")
        .path(String.valueOf(saved.getId()))
        .build()
        .toUri();
    headers.setLocation(locationUri);//设置location头部信息
    
    /*
     * ResponseEntity中可以包含响应相关的元数据（如头部信息和状态码）以及要转换成资源表述的对象，
     * 还包含了ResponseBody的语义
     */
    ResponseEntity<Spittle> responseEntity = new ResponseEntity<Spittle>(saved, headers, HttpStatus.CREATED);
    return responseEntity;
  }
  
  @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes="application/json")
  public void update(@PathVariable Long id, @RequestBody Spittle spittle) {
	  if(spittle!=null && id.equals(spittle.getId())) {
		  spittleRepository.update(spittle);
	  }
  }
  
  @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
  public void delete(@PathVariable Long id) {
	  spittleRepository.delete(id);
  }
  
  @ExceptionHandler(SpittleNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)//指定状态码
  public @ResponseBody Error spittleNotFound(SpittleNotFoundException e) {
    long spittleId = e.getSpittleId();
    return new Error(4, "Spittle [" + spittleId + "] not found");
  }

}
