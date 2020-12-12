package com.lzc.soundsystem.mixedconfig.impl;

import com.lzc.soundsystem.mixedconfig.CompactDisc;

public class SgtPeppers implements CompactDisc {

	private String title = "夜曲";
	private String artist = "周杰伦";

	public void play() {
		System.out.println("Playing " + title + " by " + artist);
	}

}
