package com.lzc.assembly.scopedbeans;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.lzc.assembly.scopedbeans.Notepad;
import com.lzc.assembly.scopedbeans.UniqueThing;

@Configuration
public class ExplicitConfig {

  @Bean
  @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  public Notepad notepad() {
    return new Notepad();
  }
  
  @Bean
  public UniqueThing unique() {
    return new UniqueThing();
  }
  
}
