package pt.unl.fct.di.pedroalves.ast;

import pt.unl.fct.di.pedroalves.environment.Environment;
import pt.unl.fct.di.pedroalves.exception.TypeError;
import pt.unl.fct.di.pedroalves.exception.UnknownIdentifierException;
import pt.unl.fct.di.pedroalves.type.Type;
import pt.unl.fct.di.pedroalves.type.TypeEnvironment;
import pt.unl.fct.di.pedroalves.type.TypeInt;
import pt.unl.fct.di.pedroalves.value.IntValue;
import pt.unl.fct.di.pedroalves.value.Value;

/**
 * Node that represents an integer.
 * 
 * @author pedroalves
 *
 */
public class ASTNum implements ASTNode {

	private final int val;

	@Override
	public Value eval(Environment env) throws TypeError,
			UnknownIdentifierException {
		return new IntValue(val);
	}

	public ASTNum(int n) {
		val = n;
	}

	@Override
	public Type typecheck(TypeEnvironment tenv) throws TypeError, UnknownIdentifierException {
		return new TypeInt();
	}
	
}
