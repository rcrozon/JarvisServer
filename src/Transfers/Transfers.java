package Transfers;

import java.io.IOException;

import Entities.Device;

public abstract class Transfers {
	
	public abstract void sendSerializedObject(Object oObjectToSend, Device poDevice) throws IOException ;
}
