package pt.unl.fct.di.pedroalves.value;

import java.util.Iterator;
import java.util.List;

import pt.unl.fct.di.pedroalves.ast.ASTNode;
import pt.unl.fct.di.pedroalves.environment.Environment;
import pt.unl.fct.di.pedroalves.exception.InvalidArgumentsException;
import pt.unl.fct.di.pedroalves.exception.TypeError;
import pt.unl.fct.di.pedroalves.exception.UnknownIdentifierException;

/**
 * Stores a value of type FUNCTION (functions are treated as any other value), which is a closure containing:
 * - the body of the function
 * - the names of the arguments
 * - and the environment that should be used when the function is called
 * 
 * @author pedroalves
 *
 */
public class FunctionValue implements Value{
	
	private final Environment env;
	private final ASTNode body;
	private final List<String> argsDef;
	
	public FunctionValue(final List<String> args, final Environment env, final ASTNode body){
		this.env = env;
		this.argsDef = args;
		this.body = body;
	}

	@Override
	public VType typeOf() {
		return VType.FUNCTION;
	}
	
	/**
	 * Calls the function with the given values for the arguments.
	 * @param argValues - list with the values for the arguments (in the same order they were defined)
	 * @return the return-value of the call
	 * @throws TypeError
	 * @throws UnknownIdentifierException
	 */
	public Value call(final List<Value> argValues) throws TypeError, UnknownIdentifierException{
		if(argValues.size()!=argsDef.size()){
			throw new InvalidArgumentsException();
		}
		
		final Environment callEnv = env.beginScope();
		final Iterator<Value> values = argValues.iterator();
		for(final String arg: argsDef){
			callEnv.bind(arg, values.next());
		}
		final Value result = body.eval(callEnv);
		callEnv.endScope();
		return result;
	}

}
