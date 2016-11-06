package pt.unl.fct.di.pedroalves.value;

/**
 * Stores a value of type INTEGER
 * 
 * @author pedroalves
 *
 */
public class IntValue implements Value {

	private final int val;

	public IntValue(int v) {
		this.val = v;
	}

	public VType typeOf() {
		return VType.INTEGER;
	}

	public int getVal() {
		return val;
	}

	public String toString() {
		return Integer.toString(val);
	}

}
