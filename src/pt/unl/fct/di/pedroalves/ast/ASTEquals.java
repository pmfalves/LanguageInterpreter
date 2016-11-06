package pt.unl.fct.di.pedroalves.ast;

import pt.unl.fct.di.pedroalves.environment.Environment;
import pt.unl.fct.di.pedroalves.exception.TypeError;
import pt.unl.fct.di.pedroalves.exception.UnknownIdentifierException;
import pt.unl.fct.di.pedroalves.type.Type;
import pt.unl.fct.di.pedroalves.type.TypeBool;
import pt.unl.fct.di.pedroalves.type.TypeEnvironment;
import pt.unl.fct.di.pedroalves.type.Type.TypeDesc;
import pt.unl.fct.di.pedroalves.value.BoolValue;
import pt.unl.fct.di.pedroalves.value.Value;
import pt.unl.fct.di.pedroalves.value.ValueUtils;
import pt.unl.fct.di.pedroalves.value.Value.VType;


/**
 * Node that represents the binary comparison operator "equals".
 * 
 * @author pedroalves
 *
 */
public class ASTEquals implements ASTNode {

	private final ASTNode left, right;

	public ASTEquals(final ASTNode left, final ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public Value eval(final Environment env) throws TypeError, UnknownIdentifierException {
		final Value v1 = left.eval(env);
		final Value v2 = right.eval(env);
		if (v1.typeOf() == VType.INTEGER && v2.typeOf() == VType.INTEGER) {
			final int i1 = ValueUtils.toInt(v1);
			final int i2 = ValueUtils.toInt(v2);
			return new BoolValue(i1 == i2);
		} else if (v1.typeOf() == VType.BOOLEAN && v2.typeOf() == VType.BOOLEAN) {
			final boolean b1 = ValueUtils.toBool(v1);
			final boolean b2 = ValueUtils.toBool(v2);
			return new BoolValue(b1 == b2);
		} else {
			throw new TypeError();
		}
	}

	@Override
	public Type typecheck(final TypeEnvironment tenv) throws TypeError, UnknownIdentifierException {
		final Type lt = left.typecheck(tenv);
		final Type rt = right.typecheck(tenv);
		if (lt.getTypeDesc() == TypeDesc.INT && rt.getTypeDesc() == TypeDesc.INT
				|| lt.getTypeDesc() == TypeDesc.BOOL && rt.getTypeDesc() == TypeDesc.BOOL) {
			return new TypeBool();
		} else {
			throw new TypeError("Undefined operator " + this
					+ " for the arguments: " + lt + ", " + rt);

		}
	}

}
