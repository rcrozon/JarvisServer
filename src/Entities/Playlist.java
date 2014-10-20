package Entities;

import java.util.ArrayList;

public class Playlist extends Entity {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Music> moMusics;
	
	public Playlist(String psLibelle, ArrayList<Music> poMusics){
		this.msLibelle = psLibelle;
		this.moMusics = poMusics;
	}
	

	/**
	 * @return the moEntry
	 */
	public ArrayList<Music> getMoMusics() {
		return moMusics;
	}

	/**
	 * @param moEntry the moEntry to set
	 */
	public void setMoMusics(ArrayList<Music> poMusics) {
		this.moMusics = poMusics;
	}
}
