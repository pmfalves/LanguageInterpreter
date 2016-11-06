package pt.unl.fct.di.pedroalves.ast;

import pt.unl.fct.di.pedroalves.environment.Environment;
import pt.unl.fct.di.pedroalves.exception.TypeError;
import pt.unl.fct.di.pedroalves.exception.UnknownIdentifierException;
import pt.unl.fct.di.pedroalves.type.Type;
import pt.unl.fct.di.pedroalves.type.TypeEnvironment;
import pt.unl.fct.di.pedroalves.type.TypeReference;
import pt.unl.fct.di.pedroalves.value.Value;
import pt.unl.fct.di.pedroalves.value.Variable;

/**
 * Node that represents the instantiation of a new variable.
 * 
 * @author pedroalves
 *
 */
public class ASTNew implements ASTNode {

	private final ASTNode expression;

	public ASTNew(final ASTNode expression) {
		this.expression = expression;
	}

	@Override
	public Value eval(final Environment env) throws TypeError, UnknownIdentifierException {
		final Value v = expression.eval(env);
		return new Variable(v);
	}

	@Override
	public Type typecheck(final TypeEnvironment tenv) throws TypeError, UnknownIdentifierException {
		final Type et = expression.typecheck(tenv);
		return new TypeReference(et);
	}

}
