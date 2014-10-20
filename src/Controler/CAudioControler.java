package Controler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javazoom.jl.decoder.JavaLayerException;
import Audio.AudioPlayer;
import Entities.Album;
import Entities.Device;
import Entities.Music;
import Entities.Playlist;
import Entries.Entry;
import Entries.FileEntry;
import Entries.FolderEntry;
import GenericActions.CGenericAction;
import GenericActions.CGenericConstantes;
import JarvisServer.JarvisServer;
import XML.XMLCreator;

public class CAudioControler extends AControler implements Observer{

	public static Music moMusicPlaying = null;
	public static AudioPlayer moPlayer = null;
	public static ArrayList<Music> moPlaylist = null;
	
	public CAudioControler(Object poObject) {
		super(poObject);
	}

	@Override
	public Object serverReponse() {
		if (oObjectToHandle instanceof Music){
			System.out.println("MUSIQUE " + ((Music)oObjectToHandle).getMsLibelle());
			File fMusic = new File(((Music)oObjectToHandle).getMsPath());
			Music oMusicToSend = (Music)oObjectToHandle;
			if (fMusic.exists()){
				System.out.println();
				if (fMusic.isDirectory()){
					Device oDevice = new Device(1, CGenericConstantes.deviceIpAddressPhone);
					try {
						FileEntry oFileEntry = CGenericAction.getPictureFilePath(((Music)oObjectToHandle).getMsPath());
						if (oFileEntry != null && oFileEntry.getAbsolutePath() != null)
							JarvisServer.moClient.sendFile(oDevice, oFileEntry.getAbsolutePath(), true);
					} catch (UnknownHostException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (((Music)oObjectToHandle).getMsLibelle().equals("root")){
						System.out.println("root");
						try {
							System.out.println("MUSIC ");
							Playlist oPLaylist = new Playlist("Playlist2", XMLCreator.getPlaylistByName("Playlist2"));
							for(Music o : oPLaylist.getMoMusics()){
								System.out.println("MUSIC " + o.getMsPath());
							}
							JarvisServer.moClient.sendSerializedObject(oPLaylist, oDevice);
						} catch (IOException e) {
							e.printStackTrace();
						}
						oMusicToSend.setMoEntry(Entry.fillEntries(new FolderEntry(((Music)oObjectToHandle).getMsPath()), CGenericConstantes.DIRECTORIES_ONLY));
					}else{
						System.out.println("Not root");
						oMusicToSend.setMoEntry(Entry.fillEntries(new FolderEntry(((Music)oObjectToHandle).getMsPath()), CGenericConstantes.AUDIO_FILES_ONLY));
					}
					return oMusicToSend;
				}else{
					playMusic(oMusicToSend);
				}
			}
		}else if (oObjectToHandle instanceof Album){
			moPlaylist = ((Album)oObjectToHandle).getMvMusics();
			if (moPlaylist != null && moPlaylist.size() > 0 ){
				playMusic(moPlaylist.get(0));
			}
		}
		return oObjectToHandle;
	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (moPlaylist != null){
			if ( moPlaylist.get(moPlaylist.indexOf(moMusicPlaying)+1) != null){
				System.out.println("play next song");
				playMusic(moPlaylist.get(moPlaylist.indexOf(moMusicPlaying)+1));
			}else if (moPlaylist.get(0) != null){
				System.out.println("play first song");
				playMusic(moPlaylist.get(0));
			}
		}
	}

	private void playMusic(Music poMusic){
		try {
			FileInputStream input = new FileInputStream(poMusic.getMsPath());
			moMusicPlaying = poMusic;
			moPlayer = new AudioPlayer(input);
			moPlayer.deleteObservers();
			moPlayer.addObserver(this);
	
	        // start playing
			moPlayer.play();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
