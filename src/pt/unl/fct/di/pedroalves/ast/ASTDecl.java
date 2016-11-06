package pt.unl.fct.di.pedroalves.ast;

import java.util.Iterator;
import java.util.List;

import pt.unl.fct.di.pedroalves.environment.Environment;
import pt.unl.fct.di.pedroalves.exception.TypeError;
import pt.unl.fct.di.pedroalves.exception.UnknownIdentifierException;
import pt.unl.fct.di.pedroalves.type.Type;
import pt.unl.fct.di.pedroalves.type.TypeEnvironment;
import pt.unl.fct.di.pedroalves.value.Value;

/**
 * Node that represents a declaration.
 * 
 * @author pedroalves
 *
 */
public class ASTDecl implements ASTNode {

	private final List<String> listIds;
	private final List<ASTNode> listExps;
	private final ASTNode expression;

	public ASTDecl(final List<String> listIds, final List<ASTNode> listExps, final ASTNode expression) {
		this.listIds = listIds;
		this.listExps = listExps;
		this.expression = expression;
	}

	@Override
	public Value eval(final Environment env) throws TypeError, UnknownIdentifierException {

		final Environment newEnv = env.beginScope();

		final Iterator<ASTNode> exps = listExps.iterator();

		for (final String id : listIds) {
			final Value v = exps.next().eval(newEnv);
			newEnv.bind(id, v);
		}
		final Value v = expression.eval(newEnv);

		newEnv.endScope();

		return v;

	}

	@Override
	public Type typecheck(final TypeEnvironment tenv) throws TypeError, UnknownIdentifierException {

		final TypeEnvironment newEnv = tenv.beginScope();

		final Iterator<ASTNode> exps = listExps.iterator();

		for (final String id : listIds) {
			final Type t = exps.next().typecheck(newEnv);
			newEnv.bind(id, t);
		}
		final Type t = expression.typecheck(newEnv);

		newEnv.endScope();

		return t;

	}

}
