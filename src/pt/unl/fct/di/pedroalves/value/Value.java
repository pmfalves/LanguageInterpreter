package pt.unl.fct.di.pedroalves.value;

/**
 * Represents a value
 * 
 * @author pedroalves
 *
 */
public interface Value {

	public enum VType {
		INTEGER, BOOLEAN, VAR, FUNCTION, RECORD;
	}

	/**
	 * @return type of the value
	 */
	public VType typeOf();

	String toString();

}
