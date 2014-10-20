package Entries;

import java.io.File;
import java.util.Vector;

import GenericActions.CGenericAction;
import GenericActions.CGenericConstantes;

public abstract class Entry extends File {

	Vector<Entry> mvEntries ;
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	
	public Entry(String pPathname) {
		super(pPathname);
	}
	
	public void setMvEntries(Vector<Entry> pvEntry){
		mvEntries = pvEntry;
	}
	
	public void setMvEntries(File[] poFiles){
		for(File file : poFiles){
			Entry oEntry ; 
			if(file.isDirectory()){
				oEntry = new FolderEntry(file.getAbsolutePath());
			}else{
				oEntry = new FileEntry(file.getAbsolutePath());
			}
			mvEntries.add(oEntry);
		}
	}
	
	public Vector<Entry> getMvEntries(){
		return mvEntries;
	}
	
	public static Vector<Entry> getEntries(File[] poFiles){
		Vector<Entry> vEntries = new Vector<Entry>();
		for(File file : poFiles){
			Entry oEntry ; 
			if(file.isDirectory()){
				oEntry = new FolderEntry(file.getAbsolutePath());
			}else{
				oEntry = new FileEntry(file.getAbsolutePath());
			}
			vEntries.add(oEntry);
		}
		return vEntries;
	}

	public static Entry fillEntries(FolderEntry poFolderEntry, int piExtensionFilter){
		if (poFolderEntry.getAbsoluteFile() != null){
			File[] fFiles = poFolderEntry.listFiles();
			Vector<Entry> vEntries = new Vector<Entry>();
			for(File file : fFiles){
				Entry oEntry ; 
				if(file.isDirectory()){
					oEntry = new FolderEntry(file.getAbsolutePath());
					oEntry = (Entry)fillEntries((FolderEntry)oEntry, piExtensionFilter);
					vEntries.add(oEntry);
				}else if (piExtensionFilter != CGenericConstantes.DIRECTORIES_ONLY 
						&& CGenericAction.isFileCorrect(new FileEntry(file.getAbsolutePath()), piExtensionFilter)){
					oEntry = new FileEntry(file.getAbsolutePath());
					vEntries.add(oEntry);
				}
			}
			poFolderEntry.setMvEntries(vEntries);
		}
		return poFolderEntry;
	}
	
	public String toString(){
		String sResult = "RESULT " + getAbsolutePath();
		if (mvEntries != null){
			for(Entry oEntry : mvEntries){
				sResult += "\n" + oEntry.toString();
			}
		}
		System.out.println(sResult);
		return sResult;
	}
	
	
}
