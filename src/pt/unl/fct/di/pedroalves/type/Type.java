package pt.unl.fct.di.pedroalves.type;

/**
 * Represents a type
 * @author pedroalves
 *
 */
public interface Type {
	
	public enum TypeDesc {
		INT, BOOL, REF, FUN, CMD, REC;
	}
	
	public TypeDesc getTypeDesc();


}
