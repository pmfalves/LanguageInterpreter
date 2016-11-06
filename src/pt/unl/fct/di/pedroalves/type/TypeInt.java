package pt.unl.fct.di.pedroalves.type;

/**
 * Represents the type INTEGER
 * 
 * @author pedroalves
 *
 */
public class TypeInt implements Type {

	@Override
	public TypeDesc getTypeDesc() {
		return TypeDesc.INT;
	}

	public boolean equals(final Object obj) {
		return obj instanceof TypeInt;

	}

	public String toString() {
		return TypeDesc.INT.name();
	}

}
