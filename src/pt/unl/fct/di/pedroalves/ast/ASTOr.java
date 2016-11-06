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
 * Node that represents the binary boolean operator "OR".
 * 
 * @author pedroalves
 *
 */
public class ASTOr implements ASTNode {

	private final ASTNode left, right;

	public ASTOr(final ASTNode l, final ASTNode r) {
		left = l;
		right = r;
	}

	@Override
	public Value eval(final Environment env) throws TypeError,
			UnknownIdentifierException {
		final boolean v1 = ValueUtils.toBool(left.eval(env));
		final boolean v2 = ValueUtils.toBool(right.eval(env));
		return new BoolValue(v1 || v2);
	}

	@Override
	public Type typecheck(final TypeEnvironment tenv) throws TypeError, UnknownIdentifierException {
		final Type lt = left.typecheck(tenv);
		final Type rt = right.typecheck(tenv);
		if (lt.getTypeDesc() == TypeDesc.BOOL && rt.getTypeDesc() == TypeDesc.BOOL) {
			return lt;
		} else {
			throw new TypeError("Undefined operator " + this
					+ " for the arguments: " + lt + ", " + rt);

		}
	}

}
