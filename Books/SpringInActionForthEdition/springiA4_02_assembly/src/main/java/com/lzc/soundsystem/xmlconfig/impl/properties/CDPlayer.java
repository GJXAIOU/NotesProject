package com.lzc.soundsystem.xmlconfig.impl.properties;

import org.springframework.beans.factory.annotation.Autowired;

import com.lzc.soundsystem.xmlconfig.CompactDisc;
import com.lzc.soundsystem.xmlconfig.MediaPlayer;

public class CDPlayer implements MediaPlayer {

	private CompactDisc cd;

	// 属性注入
	@Autowired
	public void setCd(CompactDisc cd) {
		this.cd = cd;
	}

	public void play() {
		cd.play();
	}

}
