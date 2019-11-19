package com.lzc.jaxws.service;

import java.util.HashMap;
import java.util.Map;

import com.lzc.jaxws.bean.Spitter;

/**
 * Description:   
 * @author:     lzc  
 * @version:    1.0  
 * @date:       2019-04-20 15:32:03  
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-04-20   lzc         1.0         1.0 Version  
 */

public class SpitterServiceImpl implements SpitterService {

	@Override
	public Spitter getById(Long id) {
		return store().get(id);
	}
	
	private static Map<Long, Spitter> store(){
		Map<Long, Spitter> store = new HashMap<Long, Spitter>();
		Spitter spitter1 = new Spitter(1L, "张三", "zhangsan233");
		Spitter spitter2 = new Spitter(2L, "李四", "lisi55667788");
		store.put(spitter1.getId(), spitter1);
		store.put(spitter2.getId(), spitter2);
		return store;
	}

}
