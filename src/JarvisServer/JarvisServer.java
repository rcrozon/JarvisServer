package JarvisServer;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import Controler.AControler;
import Controler.CVocalControler;
import Entities.Command;
import Entities.Device;
import Entities.Music;
import Entries.Entry;
import GenericActions.CGenericConstantes;
import Transfers.Client;
import Transfers.Server;
import XML.XMLCreator;

public class JarvisServer implements Observer {
	
	public static Server moServer;
	public static Client moClient ;
	public static XMLCreator moXMLCreator ;
	public AControler moControler ;
	
	public JarvisServer(){
		moXMLCreator = new XMLCreator();
		moClient = new Client();
		moServer = new Server();
		moServer.addObserver(this);
		startServer();
		
	}
	
	private synchronized void startServer(){
		Thread threadServer = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					moServer.startServer();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		threadServer.start();
	}
	
	public static void main(String[] args) {
		JarvisServer jarvisServer = new JarvisServer();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		moControler = AControler.createControler(arg1);
		if (moControler != null){
			try {
				Object oObject = moControler.serverReponse();
				if (oObject instanceof Entry){
					moClient.sendSerializedObject(oObject, new Device(0, CGenericConstantes.deviceIpAddressPhone));
				}else if (oObject instanceof Music){
					Device oDevice = new Device(1, CGenericConstantes.deviceIpAddressPhone);
					moClient.sendSerializedObject(oObject, oDevice);
				}else if (oObject instanceof Command){
					((CVocalControler)moControler).processVocalCommand((Command)oObject);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("Controler null");
		}
	}
	
	
}
