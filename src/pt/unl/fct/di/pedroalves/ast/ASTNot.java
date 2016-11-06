package pt.unl.fct.di.pedroalves.ast;

import pt.unl.fct.di.pedroalves.environment.Environment;
import pt.unl.fct.di.pedroalves.exception.TypeError;
import pt.unl.fct.di.pedroalves.exception.UnknownIdentifierException;
import pt.unl.fct.di.pedroalves.type.Type;
import pt.unl.fct.di.pedroalves.type.TypeEnvironment;
import pt.unl.fct.di.pedroalves.type.Type.TypeDesc;
import pt.unl.fct.di.pedroalves.value.BoolValue;
import pt.unl.fct.di.pedroalves.value.Value;
import pt.unl.fct.di.pedroalves.value.ValueUtils;

/**
 * Node that represents the boolean operator "NOT".
 * 
 * @author pedroalves
 *
 */
public class ASTNot implements ASTNode {

	private final ASTNode child;

	public ASTNot(final ASTNode c) {
		child = c;
	}

	@Override
	public Value eval(final Environment env) throws TypeError,
			UnknownIdentifierException {
		final boolean v = ValueUtils.toBool(child.eval(env));
		return new BoolValue(!v);

	}

	@Override
	public Type typecheck(final TypeEnvironment tenv) throws TypeError, UnknownIdentifierException {
		final Type ct = child.typecheck(tenv);
		if (ct.getTypeDesc() == TypeDesc.BOOL) {
			return ct;
		} else {
			throw new TypeError("Undefined operator " + this
					+ " for the argument: " + ct);

		}
	}

}
