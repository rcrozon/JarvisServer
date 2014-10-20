package Entities;

import java.io.File;
import java.util.ArrayList;

public class Album extends Entity {
	
	private static final long serialVersionUID = 2L;
	private String msPath ;
	private ArrayList<Music> mvMusics = new ArrayList<Music>();
	private boolean bHasPicture = false;

	public Album(String psPath){
		File oFile = new File(psPath);
		this.msLibelle = oFile.getName();
		this.msPath = oFile.getAbsolutePath();
	}
	
	public Album(String psPath, ArrayList<Music> pvMusics){
		this(psPath);
		this.mvMusics = pvMusics;
	}
	
	public boolean hasPicture(){
		return bHasPicture;
	}
	
	public void setHasPicture(boolean pbHasPicture){
		bHasPicture = pbHasPicture;
	}

	/**
	 * @return the msPath
	 */
	public String getMsPath() {
		return msPath;
	}

	/**
	 * @param msPath the msPath to set
	 */
	public void setMsPath(String psPath) {
		this.msPath = psPath;
	}

	public ArrayList<Music> getMvMusics() {
		return mvMusics;
	}

	public void setMvMusics(ArrayList<Music> pvMusics) {
		this.mvMusics = pvMusics;
	}
}
