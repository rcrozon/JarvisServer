package Entities;


public class Command extends Entity{

	
	private static final long serialVersionUID = 3L;
	private String msCommand;
	
	public Command(String psCommand){
		this.msCommand = psCommand;
	}
	
	public String getMsCommand(){
		return msCommand;
	}
	

	public String toString(){
		return msCommand;
	}
}
