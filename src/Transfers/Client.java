package Transfers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import Entities.Device;
import GenericActions.CGenericConstantes;
import Serialization.Serializor;

public class Client extends Transfers {

	private Socket oSocket;
	private FileInputStream fileInputStream;
	private BufferedInputStream bufferedInputStream;
	private OutputStream outputStream;

	@Override
	public void sendSerializedObject(Object poObjectToSend, Device poDevice) throws IOException {
//		System.out.println("CLIENT Passe" + poObjectToSend.toString());
		while (oSocket != null && !oSocket.isClosed());
		File file = Serializor.serialize(poObjectToSend);
	    try {
	      
	    	oSocket = new Socket(poDevice.getMsAddress(), CGenericConstantes.PORT_GLOBAL);

	    	byte[] mybytearray = new byte[(int) file.length()]; //create a byte array to file
	 
		    fileInputStream = new FileInputStream(file);
		    bufferedInputStream = new BufferedInputStream(fileInputStream);  
		    bufferedInputStream.read(mybytearray, 0, mybytearray.length); //read the file
		    outputStream = oSocket.getOutputStream();

		    outputStream.write(mybytearray, 0, mybytearray.length); //write file to the output stream byte by byte
		    outputStream.flush();
		    bufferedInputStream.close();
		    outputStream.close();
		    oSocket.close();
	    } catch (UnknownHostException e) {
	    	System.err.println("unknowHost " + e.getMessage());
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	System.err.println("client io " + e.getMessage());
	    	e.printStackTrace();
	    }
	}
	
	public void sendFile(Device poDevice, String psFilePath, boolean pbCloseOnExit) throws UnknownHostException, IOException {
		while (oSocket != null && !oSocket.isClosed());
		oSocket = new Socket(poDevice.getMsAddress(), CGenericConstantes.PORT_PICTURE);
		InputStream in = new FileInputStream(psFilePath);
		OutputStream out = oSocket.getOutputStream();
        
        byte buf[] = new byte[1024];
        
        int n;
        while((n=in.read(buf))!=-1)
            out.write(buf,0,n);
        
        if (pbCloseOnExit)
        {
            in.close();
            out.close();
        }
        oSocket.close();
	}
}
