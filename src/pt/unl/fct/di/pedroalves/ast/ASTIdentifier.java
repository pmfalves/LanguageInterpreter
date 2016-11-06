package pt.unl.fct.di.pedroalves.ast;

import pt.unl.fct.di.pedroalves.environment.Environment;
import pt.unl.fct.di.pedroalves.exception.TypeError;
import pt.unl.fct.di.pedroalves.exception.UnknownIdentifierException;
import pt.unl.fct.di.pedroalves.type.Type;
import pt.unl.fct.di.pedroalves.type.TypeEnvironment;
import pt.unl.fct.di.pedroalves.value.Value;

/**
 * Node that represents an identifier.
 * 
 * @author pedroalves
 *
 */
public class ASTIdentifier implements ASTNode {

	private final String name;

	public ASTIdentifier(final String image) {
		this.name = image;
	}

	@Override
	public Value eval(final Environment env) throws TypeError, UnknownIdentifierException {
		final Value v = env.find(name);
		if (v == null) {
			throw new UnknownIdentifierException(name);
		} else {
			return v;
		}
	}

	@Override
	public Type typecheck(final TypeEnvironment tenv) throws TypeError, UnknownIdentifierException {
		final Type t = tenv.find(name);
		if (t == null) {
			throw new UnknownIdentifierException(name);
		} else {
			return t;
		}
	}

}
