package spittr.service;

import javax.annotation.security.RolesAllowed;

import spittr.domain.Spittle;

public class JSR250SpittleService implements SpittleService {

  @Override
  @RolesAllowed("ROLE_SPITTER")//作用与@Secured一样，唯一显著的区别是@RolesAllowed是JSR-250定义的Java标准 注解
  public void addSpittle(Spittle spittle) {
    System.out.println("Method was called successfully");
  }
  
}
