package Audio;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import javazoom.jl.decoder.JavaLayerException;
import Entities.Music;
import Entities.Playlist;


public class Player implements Observer{

	public static Music moMusicPlaying = null;
	public static AudioPlayer moAudioPlayer;
	public static Playlist moPlaylist = null;
    
	public static Player moPlayer = null;
	
	public static Player getInstance(){
		if (moPlayer == null){
			moPlayer = new Player();
		}
		return moPlayer;
	}
	
	public Player(){
		
//		oPlaylist.add(new Music("Drive.mp3", System.getProperty("user.home") + "/Bureau/Music/Incubus/Incubus - Drive.mp3"));
//		oPlaylist.add(new Music("Anna MollY.MP3", System.getProperty("user.home") + "/Bureau/Music/Incubus/Incubus-Anna Molly.mp3"));
//		
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		playMusic(oPlaylist.get(0)); 
//        
	}
	
	/**
 	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Player oPlayer = new Player();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (moPlaylist != null && moPlaylist.getMoMusics() != null && moPlaylist.getMoMusics().size() > 0){
			if (moPlaylist.getMoMusics().get(moPlaylist.getMoMusics().indexOf(moMusicPlaying)+1) != null){
				playMusic(moPlaylist.getMoMusics().get(moPlaylist.getMoMusics().indexOf(moMusicPlaying)+1));
			}else if (moPlaylist.getMoMusics().get(0) != null){
				playMusic(moPlaylist.getMoMusics().get(0));
			}
		}
	}
	
	public static void playMusic(Music poMusic){
		try {
			FileInputStream input = new FileInputStream(poMusic.getMsPath());
			moMusicPlaying = poMusic;
			moAudioPlayer = new AudioPlayer(input);
	        moAudioPlayer.deleteObservers();
	        moAudioPlayer.addObserver(moPlayer);
	
	        // start playing
	        moAudioPlayer.play();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setCurrentPlaylist(Playlist poPlaylist){
		moPlaylist = poPlaylist;
	}
}

