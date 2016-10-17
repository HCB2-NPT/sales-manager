package pojo;

import java.io.Serializable;

public class _pojo_Initializer implements Serializable {
	private boolean created = false;
	private boolean edited = false;
	
	public boolean getCreated(){
		return created;
	}
	public void setCreated(boolean value){
		created = value;
	}
	public boolean getEdited(){
		return edited;
	}
	public void setEdited(boolean value){
		edited = value;
	}
	public String getState(){
		return edited ? "Edited" : "";
	}
}
