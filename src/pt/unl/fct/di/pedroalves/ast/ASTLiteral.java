package pt.unl.fct.di.pedroalves.ast;

import pt.unl.fct.di.pedroalves.environment.Environment;
import pt.unl.fct.di.pedroalves.exception.TypeError;
import pt.unl.fct.di.pedroalves.exception.UnknownIdentifierException;
import pt.unl.fct.di.pedroalves.type.Type;
import pt.unl.fct.di.pedroalves.type.TypeBool;
import pt.unl.fct.di.pedroalves.type.TypeEnvironment;
import pt.unl.fct.di.pedroalves.value.BoolValue;
import pt.unl.fct.di.pedroalves.value.Value;

/**
 * Node that represents a boolean literal.
 * 
 * @author pedroalves
 *
 */
public class ASTLiteral implements ASTNode {

	private boolean literal;

	public ASTLiteral(boolean literal) {
		this.literal = literal;
	}

	@Override
	public Value eval(Environment env) throws TypeError,
			UnknownIdentifierException {
		return new BoolValue(literal);
	}

	@Override
	public Type typecheck(TypeEnvironment tenv) throws TypeError, UnknownIdentifierException {
		return new TypeBool();
	}

}
