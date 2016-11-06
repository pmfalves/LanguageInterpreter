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
 * Node that represents a binary boolean operator "AND".
 * 
 * @author pedroalves
 * 
 */
public class ASTAnd implements ASTNode {

	private final ASTNode left, right;

	public ASTAnd(final ASTNode left, final ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public Value eval(final Environment env) throws TypeError, UnknownIdentifierException {
		final boolean leftValue = ValueUtils.toBool(left.eval(env));
		final boolean rightValue = ValueUtils.toBool(right.eval(env));
		return new BoolValue(leftValue && rightValue);
	}

	@Override
	public Type typecheck(final TypeEnvironment tenv) throws TypeError, UnknownIdentifierException {
		final Type typeLeft = left.typecheck(tenv);
		final Type typeRight = right.typecheck(tenv);
		if (typeLeft.getTypeDesc() == TypeDesc.BOOL && typeRight.getTypeDesc() == TypeDesc.BOOL) {
			return typeLeft;
		} else {
			throw new TypeError("Undefined operator " + this + " for the arguments: " + typeLeft + ", "
					+ typeRight);

		}
	}

}
