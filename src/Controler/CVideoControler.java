package Controler;

import java.io.File;
import java.io.IOException;

import Entities.Music;
import Entities.Video;
import Entries.Entry;
import Entries.FolderEntry;
import GenericActions.CGenericAction;
import GenericActions.CGenericConstantes;

public class CVideoControler extends AControler {

	public CVideoControler(Object poObject) {
		super(poObject);
	}

	@Override
	public Object serverReponse() {
		// TODO Auto-generated method stub
		System.out.println("serverResponse Video");
		File fVideo = new File(((Video)oObjectToHandle).getMsPath());
		if (fVideo.exists()){
			System.out.println();
			if (fVideo.isDirectory()){
				return Entry.fillEntries(new FolderEntry(fVideo.getAbsolutePath()), CGenericConstantes.VIDEO_FILES_ONLY);
//				return FolderEntry.getEntries(fVideo.listFiles());
			}else{
				try {
					System.out.println("PLAY VIDEO");
					CGenericAction.launchFile(fVideo);
					return oObjectToHandle;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	
}
