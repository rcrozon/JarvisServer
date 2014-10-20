package Controler;

import Audio.Player;
import Entities.Command;
import VocalSynthesis.VocalSynthesis;
import XML.XMLCreator;

public class CVocalControler extends AControler {

	private XMLCreator moXMLCreator = new XMLCreator();
	private Player moPlayer = null;
	
	public CVocalControler(Object poObject) {
		super(poObject);
		moPlayer = Player.getInstance();
	}

	@Override
	public Object serverReponse() {
		// TODO Auto-generated method stub
		System.out.println("serverResponse Command");
		return oObjectToHandle;
	}

	public void processVocalCommand(Command poCommand){
		String sCommand = poCommand.getMsCommand();
		String sResponse = "";
		System.out.println("COMMAND " + sCommand);
		if (sCommand.contains("Bonjour")){
			sCommand.replace("Bonjour", "");
			System.out.println("COMMAND1|" + sCommand+ "|");
		}
		String commandBody = sCommand.substring(sCommand.indexOf(" ")+1, sCommand.length());
		System.out.println("COMMAND2|" + sCommand+ "|");
		if (sCommand.startsWith("mets") || sCommand.startsWith("lit") || sCommand.startsWith("lis") || sCommand.startsWith("mais")){
			if (commandBody.startsWith("la")){
				commandBody = commandBody.substring(commandBody.indexOf(" ")+1, commandBody.length());
				if (commandBody.startsWith("prochaine")){
					commandBody = commandBody.substring(commandBody.indexOf(" ")+1, commandBody.length());
					if (commandBody.startsWith("video")){
						sResponse = "Lancement prochaine video";
					}else if (commandBody.startsWith("musique") || commandBody.startsWith("chanson")){
						sResponse = "Lancement prochaine chanson";
					}
				}else if (commandBody.startsWith("premiere")){
					commandBody = commandBody.substring(commandBody.indexOf(" ")+1, commandBody.length()-1);
					if (commandBody.startsWith("video")){
						sResponse = "Lancement premiere chanson";
					}else if (commandBody.startsWith("musique") || commandBody.startsWith("chanson")){
						sResponse = "Lancement premiere chanson";
					}else if (commandBody.startsWith("liste de lecture") || commandBody.startsWith("playlist")){
						if (Player.moPlaylist != null && Player.moPlaylist.getMoMusics() != null && Player.moPlaylist.getMoMusics().size() > 0){
							Player.playMusic(Player.moPlaylist.getMoMusics().get(0));
							sResponse = "Lancement premiere playlist";
						}
					}
				}
			}
		}else if (sCommand.contains("comment t'appelles tu") || sCommand.contains("comment tu t'appelles")){
			sResponse = "Je m'appelle Jarviss";
		}else if (sCommand.contains("comment vas tu") || sCommand.contains("comment vas-tu") || sCommand.contains("ça va")){
			sResponse = "Bien monsieur. Et vous?";
		}else if (sCommand.contains("ca va merci")){
			
		}
		VocalSynthesis.speak(sResponse);
	}
	
}
