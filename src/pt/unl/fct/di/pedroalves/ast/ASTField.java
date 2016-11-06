package pt.unl.fct.di.pedroalves.ast;

import pt.unl.fct.di.pedroalves.environment.Environment;
import pt.unl.fct.di.pedroalves.exception.TypeError;
import pt.unl.fct.di.pedroalves.exception.UnknownIdentifierException;
import pt.unl.fct.di.pedroalves.type.Type;
import pt.unl.fct.di.pedroalves.type.TypeEnvironment;
import pt.unl.fct.di.pedroalves.type.TypeRecord;
import pt.unl.fct.di.pedroalves.type.Type.TypeDesc;
import pt.unl.fct.di.pedroalves.value.Record;
import pt.unl.fct.di.pedroalves.value.Value;
import pt.unl.fct.di.pedroalves.value.Value.VType;

/**
 * Node that represents the operator to access a field.
 * 
 * @author pedroalves
 *
 */
public class ASTField implements ASTNode {

	private final ASTNode recordExp;
	private final String fieldName;

	public ASTField(final ASTNode recordExp, final String fieldName) {
		this.recordExp = recordExp;
		this.fieldName = fieldName;
	}

	@Override
	public Value eval(final Environment env) throws TypeError, UnknownIdentifierException {
		Value v = recordExp.eval(env);
		if (v.typeOf() != VType.RECORD) {
			throw new TypeError();
		}
		final Record r = (Record) v;
		v = r.getFieldValue(fieldName);
		if (v == null) {
			throw new TypeError();
		}
		return v;
	}

	@Override
	public Type typecheck(final TypeEnvironment tenv) throws TypeError, UnknownIdentifierException {
		Type t = recordExp.typecheck(tenv);
		if (t.getTypeDesc() != TypeDesc.REC) {
			throw new TypeError(recordExp + " is not of type record.");
		}

		final TypeRecord tr = (TypeRecord) t;
		t = tr.getMemberType(fieldName);
		if (t == null) {
			throw new TypeError(fieldName + " is not a field in " + recordExp);
		}

		return t;

	}

}
