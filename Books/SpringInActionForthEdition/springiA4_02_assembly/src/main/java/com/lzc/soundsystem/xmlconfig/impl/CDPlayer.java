package com.lzc.soundsystem.xmlconfig.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.lzc.soundsystem.xmlconfig.CompactDisc;
import com.lzc.soundsystem.xmlconfig.MediaPlayer;

public class CDPlayer implements MediaPlayer {

	//既没有构造器注入，也没有属性注入，用@Autowired修饰该属性
//	@Autowired
	private CompactDisc cd;
	
	//在xml中配置bean，如果用属性注入，必须要有属性的setter方法，可以不用@Autowired
//	public void setCd(CompactDisc cd) {
//		this.cd = cd;
//	}

	// 构造器注入，可以不用@Autowired
	@Autowired
	public CDPlayer(CompactDisc cd) {
		this.cd = cd;
	}

	public void play() {
		cd.play();
	}

}
