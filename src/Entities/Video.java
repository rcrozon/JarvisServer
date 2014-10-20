package Entities;

public class Video extends Entity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private String msPath ;
	
	public Video(String psLibelle, String psPath){
		this.msLibelle = psLibelle;
		this.msPath = psPath;
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
}
