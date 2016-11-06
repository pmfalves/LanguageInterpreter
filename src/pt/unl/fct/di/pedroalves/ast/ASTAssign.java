package pt.unl.fct.di.pedroalves.ast;

import pt.unl.fct.di.pedroalves.environment.Environment;
import pt.unl.fct.di.pedroalves.exception.TypeError;
import pt.unl.fct.di.pedroalves.exception.UnknownIdentifierException;
import pt.unl.fct.di.pedroalves.type.Type;
import pt.unl.fct.di.pedroalves.type.TypeCmd;
import pt.unl.fct.di.pedroalves.type.TypeEnvironment;
import pt.unl.fct.di.pedroalves.type.TypeReference;
import pt.unl.fct.di.pedroalves.type.Type.TypeDesc;
import pt.unl.fct.di.pedroalves.value.Value;
import pt.unl.fct.di.pedroalves.value.Variable;
import pt.unl.fct.di.pedroalves.value.Value.VType;

/**
 * Node that represents the assignment of a value to a variable.
 * 
 * @author pedroalves
 *
 */
public class ASTAssign implements ASTNode {

	private final ASTNode left, right;

	public ASTAssign(final ASTNode left, final ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public Value eval(final Environment env) throws TypeError, UnknownIdentifierException {
		final Value lv = left.eval(env);
		if (lv.typeOf() == VType.VAR) {
			final Value rv = right.eval(env);
			final Variable var = (Variable) lv;
			var.setValue(rv);
			return null;
		} else {
			throw new TypeError();
		}

	}

	@Override
	public Type typecheck(final TypeEnvironment tenv) throws TypeError, UnknownIdentifierException {
		final Type lt = left.typecheck(tenv);
		if (lt.getTypeDesc() != TypeDesc.REF) {
			throw new TypeError("Invalid reference: " + left);
		}

		final Type rt = right.typecheck(tenv);
		final TypeReference tref = (TypeReference) lt;

		if (tref.getRefType().equals(rt)) {
			return new TypeCmd();
		} else {
			throw new TypeError("Type mismatch: " + tref.getRefType() + " ," + rt);
		}
	}

}
