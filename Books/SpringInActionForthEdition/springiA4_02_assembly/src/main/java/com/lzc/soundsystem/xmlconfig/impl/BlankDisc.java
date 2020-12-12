package com.lzc.soundsystem.xmlconfig.impl;

import com.lzc.soundsystem.xmlconfig.CompactDisc;

public class BlankDisc implements CompactDisc {

	private String title;
	private String artist;

	public BlankDisc(String title, String artist) {
		this.title = title;
		this.artist = artist;
	}

	public void play() {
		System.out.println("Playing " + title + " by " + artist);
	}

}
