package com.lzc.soundsystem.autoconfig.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lzc.soundsystem.autoconfig.CompactDisc;
import com.lzc.soundsystem.autoconfig.MediaPlayer;

@Component
public class CDPlayer implements MediaPlayer {

	//属性注入
	@Autowired
	private CompactDisc cd;
  
//	//属性的setter方法注入，实际上方法名可以改；必须要@Autowired
//	@Autowired
//	public void setCd(CompactDisc cd) {
//		this.cd = cd;
//	}
//	@Autowired
//	public void buildCd(CompactDisc cd) {
//		this.cd = cd;
//	}

//  //构造器注入，可以不用@Autowired
//	@Autowired
//	public CDPlayer(CompactDisc cd) {
//		this.cd = cd;
//	}

	public void play() {
		cd.play();
	}

}
