package com.lzc.aop.xmlconfig;

import java.util.List;

public class BlankDisc {

	private String title;
	private String artist;
	private List<String> tracks;
	
	public BlankDisc() {}

	public BlankDisc(String title, String artist, List<String> tracks) {
		this.title = title;
		this.artist = artist;
		this.tracks = tracks;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public List<String> getTracks() {
		return tracks;
	}

	public void setTracks(List<String> tracks) {
		this.tracks = tracks;
	}

	public void playTrack(int trackNum) {
		System.out.println("Playing " + title + "【"+tracks.get(trackNum-1)+"】 by " + artist);
	}

}
