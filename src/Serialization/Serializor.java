package Serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Entities.Command;
import Entities.Music;
import Entries.Entry;

public abstract class Serializor {

	public static File serialize(Object poSerializableObject) {
		File file =  new File("C:\\Users\\romai_000\\Desktop\\Object.ser");
		if (!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.err.println("Erreur lors de la cr�ation du fichier");
				e.printStackTrace();
			}
		}
		 // ouverture d'un flux sur un fichier
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			
			// sérialization de l'objet
			oos.writeObject(poSerializableObject) ;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
		
			
	}
	
	public static Object unserialize(File fFile) throws FileNotFoundException, IOException{
		 // ouverture d'un flux sur un fichier
		ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(fFile)) ;
		 // désérialization de l'objet
		Object oObject = null;
		try {
			oObject = ois.readObject();
			if (oObject instanceof Entry){
				return (Entry)oObject;
			}else if (oObject instanceof Music){
				return (Music)oObject;
			}else if (oObject instanceof Command){
				return (Command)oObject;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
