package com.lzc.soundsystem.mixedconfig.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.lzc.soundsystem.mixedconfig.CompactDisc;
import com.lzc.soundsystem.mixedconfig.MediaPlayer;

public class CDPlayer implements MediaPlayer {

	private CompactDisc cd;

	@Autowired
	public CDPlayer(CompactDisc cd) {
		this.cd = cd;
	}

	public void play() {
		cd.play();
	}

}
