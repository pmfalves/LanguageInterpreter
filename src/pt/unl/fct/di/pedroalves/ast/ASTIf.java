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
 * Node that represents an IF structure.
 * 
 * @author pedroalves
 *
 */
public class ASTIf implements ASTNode {

	private final ASTNode condition, trueCase, falseCase;

	public ASTIf(final ASTNode condition, final ASTNode trueCase, final ASTNode falseCase) {
		this.condition = condition;
		this.trueCase = trueCase;
		this.falseCase = falseCase;
	}

	@Override
	public Value eval(final Environment env) throws TypeError, UnknownIdentifierException {
		final Value v = condition.eval(env);
		final boolean b = ValueUtils.toBool(v);
		if (b) {
			trueCase.eval(env);
		} else if (falseCase != null) {
			falseCase.eval(env);
		}
		return null;
	}

	@Override
	public Type typecheck(final TypeEnvironment tenv) throws TypeError, UnknownIdentifierException {
		final Type tc = condition.typecheck(tenv);
		if (tc.getTypeDesc() != TypeDesc.BOOL) {
			throw new TypeError("Invalid condition: " + condition);
		}
		final Type ttc = trueCase.typecheck(tenv);
		if (ttc.getTypeDesc() != TypeDesc.CMD) {
			throw new TypeError("Invalid command: " + trueCase);
		}

		if (falseCase != null && falseCase.typecheck(tenv).getTypeDesc() != TypeDesc.CMD) {
			throw new TypeError("Invalid command: " + falseCase);
		}
		return ttc;
	}

}
