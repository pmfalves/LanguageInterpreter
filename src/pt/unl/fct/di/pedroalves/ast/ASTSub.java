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
 * Node that represents the binary arithmetic operator of subtraction.
 * 
 * @author pedroalves
 *
 */
public class ASTSub implements ASTNode {

	private final ASTNode left, right;

	public ASTSub(final ASTNode l, final ASTNode r) {
		left = l;
		right = r;
	}

	@Override
	public Value eval(final Environment env) throws TypeError,
			UnknownIdentifierException {
		final int v1 = ValueUtils.toInt(left.eval(env));
		final int v2 = ValueUtils.toInt(right.eval(env));
		return new IntValue(v1 - v2);
	}

	@Override
	public Type typecheck(final TypeEnvironment tenv) throws TypeError, UnknownIdentifierException {
		final Type lt = left.typecheck(tenv);
		final Type rt = right.typecheck(tenv);
		if (lt.getTypeDesc() == TypeDesc.INT && rt.getTypeDesc() == TypeDesc.INT) {
			return lt;
		} else {
			throw new TypeError("Undefined operator " + this
					+ " for the arguments: " + lt + ", " + rt);

		}
	}

}
