package Controler;

import Entities.Album;
import Entities.Command;
import Entities.Music;
import Entities.Video;
import Entries.FileEntry;
import Entries.FolderEntry;
import GenericActions.CGenericAction;

/**
 * Classe builder 
 * @author romai_000
 *
 */
public abstract class AControler {

	protected Object oObjectToHandle ;
	
	public AControler(Object poObject){
		this.oObjectToHandle = poObject;
	}
	
	public abstract Object serverReponse();
	/**
	 * Fonction builder : retourne un objet de gestion en fonction du type d'objet passé en paramètre.
	 * @param oObject
	 * @return
	 */
	public static AControler createControler(Object oObject){
		System.out.println("Object class " + oObject);
		if (oObject instanceof FileEntry){
			if (CGenericAction.isVideo((FileEntry)oObject)){
				System.out.println("isVideo");
				return new CVideoControler(oObject);
			}else if (CGenericAction.isAudio((FileEntry)oObject)){
				System.out.println("isAudio");
				return new CAudioControler(oObject);
			}
		}else if (oObject instanceof FolderEntry){
			System.out.println("Object instance of FolderEntry");
			return new CFolderControler(oObject);
		}else if (oObject instanceof Music || oObject instanceof Album ){
			return new CAudioControler(oObject);
		}else if (oObject instanceof Video){
			return new CVideoControler(oObject);
		}else if (oObject instanceof Command){
			return new CVocalControler(oObject);
		}
		return null;
	}
	
	
}
