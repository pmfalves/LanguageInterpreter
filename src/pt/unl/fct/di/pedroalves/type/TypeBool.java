package pt.unl.fct.di.pedroalves.type;

/**
 * Represents the type BOOLEAN
 * 
 * @author pedroalves
 *
 */
public class TypeBool implements Type {

	@Override
	public TypeDesc getTypeDesc() {
		return TypeDesc.BOOL;
	}

	public boolean equals(final Object obj) {
		return obj instanceof TypeBool;
	}

	public String toString() {
		return TypeDesc.BOOL.name();
	}

}
