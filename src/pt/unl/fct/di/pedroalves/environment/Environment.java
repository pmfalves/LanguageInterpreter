package pt.unl.fct.di.pedroalves.environment;

import java.util.HashMap;
import java.util.Map;

import pt.unl.fct.di.pedroalves.exception.DuplicateIdentifierException;
import pt.unl.fct.di.pedroalves.value.Value;


/**
 * An environment stores a collection of bindings between identifiers and values.
 * 
 * @author pedroalves
 *
 */
public class Environment {

	private final Environment previous;
	private final Map<String, Value> bindings;

	public Environment() {
		this.bindings = new HashMap<String, Value>();
		this.previous = null;
	}
	
	private Environment(final Environment previous){
		this.bindings = new HashMap<String, Value>();
		this.previous = previous;
	}

	/**
	 * Begins a new scope (Environment) that has this one as parent.
	 * @return the created child environment
	 */
	public Environment beginScope() {
		return new Environment(this);
	}

	/**
	 * Ends a scope.
	 * @return the parent environment
	 */
	public Environment endScope() {
		return previous;
	}

	
	/**
	 * Associates a new identifier with a value in the current environment.
	 * @param id
	 * @param val
	 * @throws DuplicateIdentifierException
	 */
	public void bind(final String id, final Value val) throws DuplicateIdentifierException {
		if (bindings.containsKey(id)) {
			throw new DuplicateIdentifierException(id);
		}
		bindings.put(id, val);
	}

	/**
	 * Looks for a given identifier. When it's not found in the current scope, recursively tries to find in the parents environment.
	 * @param id
	 * @return the value object for the given identifier or null when it's not found.
	 */
	public Value find(final String id) {
		if (bindings.containsKey(id)) {
			return bindings.get(id);
		} else if (previous == null) {
			return null;
		} else {
			return previous.find(id);
		}
	}

}
