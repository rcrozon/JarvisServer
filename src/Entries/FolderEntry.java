package Entries;

import java.util.Vector;

public class FolderEntry extends Entry{

	private static final long serialVersionUID = 1L;
	private Vector<Entry> 	mvEntries;
	private String 			msPath;
	
	public FolderEntry(String psPath){
		super(psPath);
		msPath = psPath;
	}
	
	public static Entry getEntry(FolderEntry fFolderEntry){
		if (fFolderEntry.exists()){
			Entry fEntry = new FolderEntry(fFolderEntry.getAbsolutePath());
			fEntry.setMvEntries(Entry.getEntries(fFolderEntry.listFiles()));
			return fEntry;
		}
		return null;
	}
}
