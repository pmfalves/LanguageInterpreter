package pt.unl.fct.di.pedroalves.type;

/**
 * Represents the type REFERENCE
 * 
 * @author pedroalves
 *
 */
public class TypeReference implements Type {

	/**
	 * The type of the value that is referenced
	 */
	private final Type refType;

	public TypeReference(final Type refType) {
		this.refType = refType;
	}

	public Type getRefType() {
		return refType;
	}

	@Override
	public TypeDesc getTypeDesc() {
		return TypeDesc.REF;
	}

	public boolean equals(final Object obj) {
		if (obj instanceof TypeReference) {
			final TypeReference other = (TypeReference) obj;
			return other.refType.equals(this.refType);
		}
		return false;
	}

	public String toString() {
		return TypeDesc.REF.name() + refType;
	}

}
