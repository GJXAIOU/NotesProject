package com.lzc.jaxws.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.lzc.jaxws.bean.Spitter;

/**
 * Description: WS发布端点的客户端接口  
 * @author:     lzc  
 * @version:    1.0  
 * @date:       2019-04-20 15:29:47  
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-04-20   lzc         1.0         1.0 Version  
 */

@WebService(serviceName="SpitterWS",
	portName="SpitterWSPort",	//默认类名加Port（SpitterServiceEndpointPort）
	targetNamespace="http://config.jaxws.lzc.com"	//默认值即多级域名（当前包名反写）
)
public interface SpitterService {

	/*
	 * 虽然接口名是SpitterService，但在本例中，与WS发布端的SpitterService接口不是一回事，
	 * 实际上对应的是SpitterServiceEndpoint（发布端点）。
	 * @WebMethod的operationName也要与其一致（默认是方法名），
	 * 方法参数可由@WebParam指定名称，发布端和客户端要么一起设置（相同），要么都不设置（默认参数名可不一样）
	 */
	
	@WebMethod
	Spitter getById(Long id);
}
