package pt.unl.fct.di.pedroalves.ast;

import pt.unl.fct.di.pedroalves.environment.Environment;
import pt.unl.fct.di.pedroalves.exception.TypeError;
import pt.unl.fct.di.pedroalves.exception.UnknownIdentifierException;
import pt.unl.fct.di.pedroalves.type.Type;
import pt.unl.fct.di.pedroalves.type.TypeEnvironment;
import pt.unl.fct.di.pedroalves.type.Type.TypeDesc;
import pt.unl.fct.di.pedroalves.value.Value;
import pt.unl.fct.di.pedroalves.value.ValueUtils;

/**
 * Node that represents a conditional value (returns one of two values depending
 * on the evaluation of a condition).
 * 
 * @author pedroalves
 *
 */
public class ASTConditionalValue implements ASTNode {

	private final ASTNode condition, expTrue, expFalse;

	public ASTConditionalValue(final ASTNode condition, final ASTNode expTrue, final ASTNode expFalse) {
		this.condition = condition;
		this.expTrue = expTrue;
		this.expFalse = expFalse;
	}

	@Override
	public Value eval(final Environment env) throws TypeError, UnknownIdentifierException {
		final boolean b = ValueUtils.toBool(condition.eval(env));
		if (b) {
			return expTrue.eval(env);
		} else {
			return expFalse.eval(env);
		}
	}

	@Override
	public Type typecheck(final TypeEnvironment tenv) throws TypeError, UnknownIdentifierException {
		final Type t = condition.typecheck(tenv);
		if (t.getTypeDesc() != TypeDesc.BOOL) {
			throw new TypeError("Invalid condition: " + condition);
		}
		final Type t1 = expTrue.typecheck(tenv);
		final Type t2 = expFalse.typecheck(tenv);
		if (!t1.equals(t2)) {
			throw new TypeError("Type mismatch: " + t1 + ", " + t2);
		}
		return t1;
	}

}
