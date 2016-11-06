package pt.unl.fct.di.pedroalves.ast;

import java.util.LinkedList;
import java.util.List;

import pt.unl.fct.di.pedroalves.environment.Environment;
import pt.unl.fct.di.pedroalves.exception.TypeError;
import pt.unl.fct.di.pedroalves.exception.UnknownIdentifierException;
import pt.unl.fct.di.pedroalves.type.Type;
import pt.unl.fct.di.pedroalves.type.TypeEnvironment;
import pt.unl.fct.di.pedroalves.type.TypeFunction;
import pt.unl.fct.di.pedroalves.type.Type.TypeDesc;
import pt.unl.fct.di.pedroalves.value.FunctionValue;
import pt.unl.fct.di.pedroalves.value.Value;
import pt.unl.fct.di.pedroalves.value.Value.VType;

/**
 * Node that represents the calling of a function.
 * 
 * @author pedroalves
 *
 */
public class ASTApply implements ASTNode {

	final private List<ASTNode> args;
	final private ASTNode func;

	public ASTApply(final ASTNode func, final List<ASTNode> args) {
		this.func = func;
		this.args = args;
	}

	@Override
	public Value eval(final Environment env) throws TypeError, UnknownIdentifierException {
		Value value = func.eval(env);
		if (value.typeOf() != VType.FUNCTION) {
			throw new TypeError("Undefined function: " + value);
		}
		final FunctionValue funcValue = (FunctionValue) value;
		final List<Value> argValues = new LinkedList<Value>();
		for (final ASTNode e : args) {
			value = e.eval(env);
			argValues.add(value);
		}

		return funcValue.call(argValues);

	}

	@Override
	public Type typecheck(final TypeEnvironment tenv) throws TypeError, UnknownIdentifierException {
		final Type type = func.typecheck(tenv);
		if (type.getTypeDesc() != TypeDesc.FUN) {
			throw new TypeError("Undefined function: " + func);
		}
		final TypeFunction typeFunc = (TypeFunction) type;
		final LinkedList<Type> argTypes = new LinkedList<Type>();
		for (final ASTNode e : args) {
			argTypes.add(e.typecheck(tenv));
		}
		if (!typeFunc.getArgTypes().equals(argTypes)) {
			throw new TypeError("Function " + func + ":" + type
					+ " is not applicable for the arguments: " + argTypes);
		}

		return typeFunc.getReturnType();
	}

}
