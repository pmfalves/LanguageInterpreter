package pt.unl.fct.di.pedroalves.ast;

import pt.unl.fct.di.pedroalves.environment.Environment;
import pt.unl.fct.di.pedroalves.exception.TypeError;
import pt.unl.fct.di.pedroalves.exception.UnknownIdentifierException;
import pt.unl.fct.di.pedroalves.type.Type;
import pt.unl.fct.di.pedroalves.type.TypeEnvironment;
import pt.unl.fct.di.pedroalves.value.Value;

/**
 * Represents a node in the Abstract Syntax Tree
 * @author pedroalves
 *
 */
public interface ASTNode {

	/**
	 * Evaluates the node and returns the computed value.
	 * 
	 * @param env
	 * @return
	 * @throws TypeError
	 * @throws UnknownIdentifierException
	 */
	public Value eval(Environment env) throws TypeError, UnknownIdentifierException;

	/**
	 * Determines the type that should be returned when the node is evaluated.
	 * 
	 * @param env
	 * @return
	 * @throws TypeError
	 * @throws UnknownIdentifierException
	 */
	public Type typecheck(TypeEnvironment tenv) throws TypeError, UnknownIdentifierException;

}
