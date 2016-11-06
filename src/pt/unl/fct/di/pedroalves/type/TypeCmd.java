package pt.unl.fct.di.pedroalves.type;

/**
 * Represents the type COMMAND
 * @author pedroalves
 *
 */
public class TypeCmd implements Type{

	@Override
	public TypeDesc getTypeDesc() {
		return TypeDesc.CMD;
	}

	public boolean equals(Object obj){
		if(obj instanceof TypeCmd){
			return true;
		}
		return false;
	}
	
	
	public String toString(){
		return TypeDesc.CMD.name();
	}

}
