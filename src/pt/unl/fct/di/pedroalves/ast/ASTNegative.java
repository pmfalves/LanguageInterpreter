package pt.unl.fct.di.pedroalves.ast;

import pt.unl.fct.di.pedroalves.environment.Environment;
import pt.unl.fct.di.pedroalves.exception.TypeError;
import pt.unl.fct.di.pedroalves.exception.UnknownIdentifierException;
import pt.unl.fct.di.pedroalves.type.Type;
import pt.unl.fct.di.pedroalves.type.TypeEnvironment;
import pt.unl.fct.di.pedroalves.type.Type.TypeDesc;
import pt.unl.fct.di.pedroalves.value.IntValue;
import pt.unl.fct.di.pedroalves.value.Value;
import pt.unl.fct.di.pedroalves.value.ValueUtils;

/**
 * Node that represents the arithmetic operator of sign inversion.
 * 
 * @author pedroalves
 *
 */
public class ASTNegative implements ASTNode {

	private final ASTNode child;

	public ASTNegative(final ASTNode child) {
		this.child = child;
	}

	@Override
	public Value eval(final Environment env) throws TypeError,
			UnknownIdentifierException {
		final int v1 = ValueUtils.toInt(child.eval(env));
		return new IntValue(-v1);
	}

	@Override
	public Type typecheck(final TypeEnvironment tenv) throws TypeError, UnknownIdentifierException {
		final Type t = child.typecheck(tenv);
		if (t.getTypeDesc() == TypeDesc.INT) {
			return t;
		} else {
			throw new TypeError("Undefined operator " + this
					+ " for the argument: " + t);
		}
	}

}
