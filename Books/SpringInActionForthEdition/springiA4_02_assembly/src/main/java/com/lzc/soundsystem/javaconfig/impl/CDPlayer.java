package com.lzc.soundsystem.javaconfig.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.lzc.soundsystem.javaconfig.CompactDisc;
import com.lzc.soundsystem.javaconfig.MediaPlayer;

public class CDPlayer implements MediaPlayer {

	private CompactDisc cd;

	//构造器注入，可以不用@Autowired
	@Autowired
	public CDPlayer(CompactDisc cd) {
		this.cd = cd;
	}

	public void play() {
		cd.play();
	}

}
