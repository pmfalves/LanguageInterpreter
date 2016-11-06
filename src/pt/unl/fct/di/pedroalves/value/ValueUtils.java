package pt.unl.fct.di.pedroalves.value;

import pt.unl.fct.di.pedroalves.exception.TypeError;

/**
 * 
 * @author pedroalves
 *
 */
public class ValueUtils {
	
	public static int toInt(Value v) throws TypeError{
		if (v.typeOf() == Value.VType.INTEGER) {
			IntValue i = (IntValue) v;
			return i.getVal();
		} else
			throw new TypeError();
	}
	
	public static boolean toBool(Value v) throws TypeError{
		if (v.typeOf() == Value.VType.BOOLEAN) {
			BoolValue b = (BoolValue) v;
			return b.getVal();
		} else
			throw new TypeError();
	}

}
