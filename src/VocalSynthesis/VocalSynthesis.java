package VocalSynthesis;

import t2s.son.LecteurTexte;

public class VocalSynthesis {
		 
	public int i = 0;
    public static LecteurTexte oLecteur ;
    
    public static LecteurTexte getLecteurInstance(){
    	if (oLecteur == null){
    		oLecteur = new LecteurTexte();
    	}
    	return oLecteur;
    }
    
    public static void speak(String psSpeech) {
        getLecteurInstance().setTexte(psSpeech);
        getLecteurInstance().playAll();
    } 
    
    public static void main(String[] args) {
    	speak("Bonjour, je suis Jarviss");
    }
	 
}
