package pt.unl.fct.di.pedroalves.ast;

import pt.unl.fct.di.pedroalves.environment.Environment;
import pt.unl.fct.di.pedroalves.exception.TypeError;
import pt.unl.fct.di.pedroalves.exception.UnknownIdentifierException;
import pt.unl.fct.di.pedroalves.type.Type;
import pt.unl.fct.di.pedroalves.type.TypeEnvironment;
import pt.unl.fct.di.pedroalves.type.Type.TypeDesc;
import pt.unl.fct.di.pedroalves.value.Value;

/**
 * Node that represent multiple expressions.
 * 
 * @author pedroalves
 *
 */
public class ASTComposite implements ASTNode {

	private final ASTNode first, remain;

	public ASTComposite(final ASTNode first, final ASTNode remain) {
		this.first = first;
		this.remain = remain;
	}

	@Override
	public Value eval(final Environment env) throws TypeError,
			UnknownIdentifierException {
		first.eval(env);
		final Value retValue = remain.eval(env);
		return retValue;
	}

	@Override
	public Type typecheck(final TypeEnvironment tenv) throws TypeError, UnknownIdentifierException {
		final Type ft = first.typecheck(tenv);
		if (ft.getTypeDesc() != TypeDesc.CMD) {
			throw new TypeError("Invalid command: " + first);
		}
		return remain.typecheck(tenv);
	}

}
