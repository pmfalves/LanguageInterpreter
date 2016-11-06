package pt.unl.fct.di.pedroalves.ast;

import pt.unl.fct.di.pedroalves.environment.Environment;
import pt.unl.fct.di.pedroalves.exception.TypeError;
import pt.unl.fct.di.pedroalves.exception.UnknownIdentifierException;
import pt.unl.fct.di.pedroalves.type.Type;
import pt.unl.fct.di.pedroalves.type.TypeCmd;
import pt.unl.fct.di.pedroalves.type.TypeEnvironment;
import pt.unl.fct.di.pedroalves.type.Type.TypeDesc;
import pt.unl.fct.di.pedroalves.value.Value;

/**
 * Node that represents the instruction to print.
 * 
 * @author pedroalves
 *
 */
public class ASTPrint implements ASTNode {

	private final ASTNode expression;
	private final boolean printline;

	public ASTPrint(final ASTNode expression, final boolean printline) {
		this.expression = expression;
		this.printline = printline;
	}

	public ASTPrint(final ASTNode expression) {
		this(expression, false);
	}

	@Override
	public Value eval(final Environment env) throws TypeError, UnknownIdentifierException {
		final Value val = expression.eval(env);
		System.out.print(val.toString());
		if (printline) {
			System.out.println();
		}
		return null;
	}

	@Override
	public Type typecheck(final TypeEnvironment tenv) throws TypeError, UnknownIdentifierException {
		final Type et = expression.typecheck(tenv);
		if (et.getTypeDesc() == TypeDesc.CMD) {
			throw new TypeError("Unable to print command type expression. ");
		}
		return new TypeCmd();
	}

}
