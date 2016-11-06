package pt.unl.fct.di.pedroalves.type;

import java.util.HashMap;

/**
 * A type environment stores a collection of bindings between identifiers and the respective types.
 * 
 * @author pedroalves
 *
 */
public class TypeEnvironment {
	
	private TypeEnvironment previous;
	private final HashMap<String, Type> bindings;

	public TypeEnvironment() {
		this.bindings = new HashMap<String, Type>();
	}

	public TypeEnvironment beginScope() {
		final TypeEnvironment newEnv = new TypeEnvironment();
		newEnv.previous = this;
		return newEnv;
	}

	public TypeEnvironment endScope() {
		return previous;
	}

	public void bind(String id, Type ty) {
		bindings.put(id, ty);
	}

	public Type find(String id) {
		if (bindings.containsKey(id)) {
			return bindings.get(id);
		} else if (previous == null) {
			return null;
		} else {
			return previous.find(id);
		}
	}

}
