package pt.unl.fct.di.pedroalves.value;


/**
 * A variable represents a value that can change.
 * 
 * @author pedroalves
 *
 */
public class Variable implements Value {


	private Value valueStored;
	
	public Variable(Value val){
		this.valueStored = val;
	}
	
	public Value getValue(){
		return valueStored;
	}
	
	/**
	 * Set the value
	 * @param v - the new value for the variable
	 */
	public void setValue(Value v){
		valueStored = v;
	}
	
	@Override
	public VType typeOf() {
		return VType.VAR;
	}

}
