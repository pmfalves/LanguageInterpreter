package pt.unl.fct.di.pedroalves.value;

/**
 * Stores a value of type BOOLEAN
 * 
 * @author pedroalves
 *
 */
public class BoolValue implements Value{

	private final boolean val;

	public BoolValue(boolean v) {
		val = v;
	}

	
	@Override
	public VType typeOf() {
		return Value.VType.BOOLEAN;
	}
	
	public boolean getVal() {
		return val;
	}

	public String toString() {
		return Boolean.toString(val);
	}

}
