package spittr.service;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import spittr.domain.Spittle;

public class ExpressionSecuredSpittleService implements SpittleService {

	@Override
	@PreAuthorize("(hasRole('ROLE_SPITTER') and #spittle.text.length() le 140) or hasRole('ROLE_PREMIUM')")
	public void addSpittle(Spittle spittle) {
		System.out.println("Method was called successfully");
	}

	// principal是另一个Spring Security内置的特殊名称，它代表了当前认证用户的主要信息（通常是用户名）
//  @PostAuthorize("returnObject.spitter.username == principal.username")
//  public Spittle getSpittleById(long id) {
//	  return null;//...不补充
//  }

}
