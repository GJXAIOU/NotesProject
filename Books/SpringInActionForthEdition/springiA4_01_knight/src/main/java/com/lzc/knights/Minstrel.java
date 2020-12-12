package com.lzc.knights;

import java.io.PrintStream;

/**
 * Description: 吟游诗人POJO，在minstrel.xml定义为切面  
 * Copyright:   Copyright (c)2019 
 * @author:     LiZC  
 * @version:    1.0  
 * Create at:   2019-02-13 13:12:11  
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2019-02-13   LiZC        1.0         1.0 Version
 */
public class Minstrel {

	private PrintStream stream;
  
	public Minstrel(PrintStream stream) {
		this.stream = stream;
	}

	public void singBeforeQuest() {
		stream.println("Fa la la, the knight is so brave!");
	}

	public void singAfterQuest() {
		stream.println("Tee hee hee, the brave knight " +
    		"did embark on a quest!");
	}

}
