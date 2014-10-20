package Entities;

import java.io.Serializable;

public class Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int miId ;
	protected String msLibelle;
	protected String msDescription;
	protected String msAssociatedTable;
	
	protected Entity(int piId){
		this.miId = piId;
	}
	
	public Entity(){}
	
	public String toString(){
		return miId + msLibelle;
	}

	/**
	 * @return the miId
	 */
	public int getMiId() {
		return miId;
	}

	/**
	 * @param miId the miId to set
	 */
	public void setMiId(int miId) {
		this.miId = miId;
	}

	/**
	 * @return the msLibelle
	 */
	public String getMsLibelle() {
		return msLibelle;
	}

	/**
	 * @param msLibelle the msLibelle to set
	 */
	public void setMsLibelle(String msLibelle) {
		this.msLibelle = msLibelle;
	}

	/**
	 * @return the msDescription
	 */
	public String getMsDescription() {
		return msDescription;
	}

	/**
	 * @param msDescription the msDescription to set
	 */
	public void setMsDescription(String msDescription) {
		this.msDescription = msDescription;
	}

	/**
	 * @return the msAssociatedTable
	 */
	public String getMsAssociatedTable() {
		return msAssociatedTable;
	}

	/**
	 * @param msAssociatedTable the msAssociatedTable to set
	 */
	public void setMsAssociatedTable(String msAssociatedTable) {
		this.msAssociatedTable = msAssociatedTable;
	}
	
	
	
	
}
