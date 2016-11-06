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
 * Node that represents a WHILE-cycle structure.
 * 
 * @author pedroalves
 *
 */
public class ASTWhile implements ASTNode {

	private final ASTNode condition, expression;

	public ASTWhile(final ASTNode condition, final ASTNode expression) {
		this.condition = condition;
		this.expression = expression;
	}

	@Override
	public Value eval(final Environment env) throws TypeError, UnknownIdentifierException {
		while (ValueUtils.toBool(condition.eval(env))) {
			expression.eval(env);
		}
		return null;
	}

	@Override
	public Type typecheck(final TypeEnvironment tenv) throws TypeError, UnknownIdentifierException {
		final Type tc = condition.typecheck(tenv);
		if (tc.getTypeDesc() != TypeDesc.BOOL) {
			throw new TypeError("Invalid condition: " + condition);
		}
		final Type et = expression.typecheck(tenv);
		if (et.getTypeDesc() != TypeDesc.CMD) {
			throw new TypeError("Invalid command: " + expression);
		}

		return et;
	}

}
