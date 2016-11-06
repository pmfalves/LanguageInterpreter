package pt.unl.fct.di.pedroalves.ast;

import pt.unl.fct.di.pedroalves.environment.Environment;
import pt.unl.fct.di.pedroalves.exception.TypeError;
import pt.unl.fct.di.pedroalves.exception.UnknownIdentifierException;
import pt.unl.fct.di.pedroalves.type.Type;
import pt.unl.fct.di.pedroalves.type.TypeEnvironment;
import pt.unl.fct.di.pedroalves.type.TypeReference;
import pt.unl.fct.di.pedroalves.type.Type.TypeDesc;
import pt.unl.fct.di.pedroalves.value.Value;
import pt.unl.fct.di.pedroalves.value.Variable;
import pt.unl.fct.di.pedroalves.value.Value.VType;

/**
 * Node that represents the dereference operator.
 * 
 * @author pedroalves
 *
 */
public class ASTDeref implements ASTNode {

	private final ASTNode expression;

	public ASTDeref(final ASTNode expression) {
		this.expression = expression;
	}

	@Override
	public Value eval(final Environment env) throws TypeError, UnknownIdentifierException {
		final Value evExp = expression.eval(env);
		if (evExp.typeOf() == VType.VAR) {
			final Variable var = (Variable) evExp;
			return var.getValue();
		} else {
			throw new TypeError();
		}
	}

	@Override
	public Type typecheck(final TypeEnvironment tenv) throws TypeError, UnknownIdentifierException {
		final Type t = expression.typecheck(tenv);
		if (t.getTypeDesc() != TypeDesc.REF) {
			throw new TypeError("Invalid reference: " + expression);
		}
		final TypeReference tref = (TypeReference) t;
		return tref.getRefType();
	}

}
