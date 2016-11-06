package pt.unl.fct.di.pedroalves.ast;

import java.util.LinkedList;
import java.util.List;

import pt.unl.fct.di.pedroalves.environment.Environment;
import pt.unl.fct.di.pedroalves.exception.TypeError;
import pt.unl.fct.di.pedroalves.exception.UnknownIdentifierException;
import pt.unl.fct.di.pedroalves.type.Type;
import pt.unl.fct.di.pedroalves.type.TypeEnvironment;
import pt.unl.fct.di.pedroalves.type.TypeRecord;
import pt.unl.fct.di.pedroalves.value.Record;
import pt.unl.fct.di.pedroalves.value.Value;

/**
 * Node that represents a record structure.
 * 
 * @author pedroalves
 *
 */
public class ASTRecord implements ASTNode {

	private final List<String> fields;
	private final List<ASTNode> values;

	public ASTRecord(final List<String> fields, final List<ASTNode> values) {
		this.fields = fields;
		this.values = values;
	}

	@Override
	public Value eval(final Environment env) throws TypeError, UnknownIdentifierException {
		final List<Value> values = new LinkedList<Value>();
		for(final ASTNode vExp: this.values){
			final Value v = vExp.eval(env);
			values.add(v);
		}
		return new Record(fields, values);
	}


	@Override
	public Type typecheck(final TypeEnvironment tenv) throws TypeError, UnknownIdentifierException {
		final List<Type> types = new LinkedList<Type>();
		for(final ASTNode vExp: this.values){
			types.add(vExp.typecheck(tenv));
		}
		return new TypeRecord(fields, types);
	}
}
