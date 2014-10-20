package Controler;

import Entries.Entry;
import Entries.FolderEntry;
import GenericActions.CGenericConstantes;

public class CFolderControler extends AControler {

	
	public CFolderControler(Object poObject) {
		super(poObject);
	}

	@Override
	public Object serverReponse() {
		System.out.println("server Response");
		return Entry.fillEntries((FolderEntry)oObjectToHandle, CGenericConstantes.ALL_FILES);
	}

	
}
