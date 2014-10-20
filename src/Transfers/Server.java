package Transfers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

import Entities.Music;
import Entries.Entry;
import GenericActions.CGenericConstantes;
import Serialization.Serializor;

public class Server extends Observable{

	public void startServer() throws IOException{
		System.out.println("Démarage du serveur");
		ServerSocket s = new ServerSocket(CGenericConstantes.PORT_GLOBAL);
		while (true) {
			System.out.println("En attente de connexion");
			// On attend la connexion d'un client
			Socket socket = s.accept();
			System.out.println("Connexion..."); 

			Byte[] taillefichier;
			int lu;
			long taille = 0;

			// Création de l'entrÃ©e
			InputStream inpute = socket.getInputStream();
			File file = File.createTempFile("object", null);
			OutputStream out = new FileOutputStream(file);

			// Reçoit du client
			BufferedInputStream inBuffer = new BufferedInputStream(inpute);

			// Envoi vers le fichier
			BufferedOutputStream outBuffer = new BufferedOutputStream(out);

			System.out.println("lecture des données");
			lu = inBuffer.read();
			
			while (lu > -1) {
				outBuffer.write(lu);
				lu = inBuffer.read();

			}

			outBuffer.write(lu);

			outBuffer.flush();
			outBuffer.close();
			inBuffer.close();

			out.flush();
			out.close();
			inpute.close();
			socket.close();
			
			System.out.println("Serialisation");
			Object oObjectReceived = Serializor.unserialize(file);
			handleMessage(oObjectReceived);
			
		}
	}
	
	public void handleMessage(Object oObjectReceived){
		System.out.println("Notification des observers " + oObjectReceived);
		setChanged();
		notifyObservers(oObjectReceived);
	}
	
	
}