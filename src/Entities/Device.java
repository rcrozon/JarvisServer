package Entities;

public class Device extends Entity{

	private String msAddress ;
	
	public Device(int piId, String psAddress) {
		super(piId);
		this.msAddress = psAddress;
	}

	public void setMsAddress(String psAddress){
		this.msAddress = psAddress;
	}
	
	public String getMsAddress(){
		return this.msAddress;
	}
	

	
	
}
