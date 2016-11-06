package pt.unl.fct.di.pedroalves.ast;

import java.util.Iterator;
import java.util.List;

import pt.unl.fct.di.pedroalves.environment.Environment;
import pt.unl.fct.di.pedroalves.exception.TypeError;
import pt.unl.fct.di.pedroalves.exception.UnknownIdentifierException;
import pt.unl.fct.di.pedroalves.type.Type;
import pt.unl.fct.di.pedroalves.type.TypeEnvironment;
import pt.unl.fct.di.pedroalves.type.TypeFunction;
import pt.unl.fct.di.pedroalves.value.FunctionValue;
import pt.unl.fct.di.pedroalves.value.Value;

/**
 * Node that represents the definition of a function.
 * 
 * @author pedroalves
 *
 */
public class ASTFunction implements ASTNode {

	private final List<String> args;
	private final List<Type> types;
	private final ASTNode body;

	public ASTFunction(final List<String> args, final List<Type> types, final ASTNode body) {
		this.args = args;
		this.body = body;
		this.types = types;
	}

	@Override
	public Value eval(final Environment env) throws TypeError, UnknownIdentifierException {
		final FunctionValue fun = new FunctionValue(args, env, body);
		return fun;
	}

	@Override
	public Type typecheck(final TypeEnvironment tenv) throws TypeError, UnknownIdentifierException {
		final TypeEnvironment funEnv = tenv.beginScope();
		final Iterator<Type> types = this.types.iterator();
		for (final String arg : args) {
			funEnv.bind(arg, types.next());
		}
		final Type returnType = body.typecheck(funEnv);
		funEnv.endScope();
		return new TypeFunction(this.types, returnType);
	}

}
