package pt.unl.fct.di.pedroalves.type;

import java.util.List;

/**
 * Represents the type FUNCTION
 * 
 * @author pedroalves
 *
 */
public class TypeFunction implements Type {

	private final List<Type> argTypes;
	private final Type returnType;

	public TypeFunction(final List<Type> argTypes, final Type returnType) {
		this.argTypes = argTypes;
		this.returnType = returnType;
	}

	@Override
	public TypeDesc getTypeDesc() {
		return TypeDesc.FUN;
	}

	public List<Type> getArgTypes() {
		return argTypes;
	}

	public Type getReturnType() {
		return returnType;
	}

	public boolean equals(final Object obj) {
		if (obj instanceof TypeFunction) {
			final TypeFunction other = (TypeFunction) obj;
			return other.returnType.equals(this.returnType) && other.argTypes.equals(this.argTypes);
		}
		return false;
	}

	public String toString() {
		return "(" + argTypes + " => " + returnType + ")";
	}

}
