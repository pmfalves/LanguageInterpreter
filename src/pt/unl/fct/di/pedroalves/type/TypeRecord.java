package pt.unl.fct.di.pedroalves.type;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the type RECORD
 * 
 * @author pedroalves
 *
 */
public class TypeRecord implements Type {

	private final Map<String, Type> memberTypes;

	public TypeRecord(final List<String> members, final List<Type> types) {
		this.memberTypes = new LinkedHashMap<String, Type>(members.size());
		final Iterator<Type> iter = types.iterator();
		for (final String m : members) {
			this.memberTypes.put(m, iter.next());
		}
	}

	@Override
	public TypeDesc getTypeDesc() {
		return TypeDesc.REC;
	}

	public boolean equals(final Object obj) {
		if (obj instanceof TypeRecord) {
			final TypeRecord other = (TypeRecord) obj;
			return other.memberTypes.equals(this.memberTypes);
		}
		return false;
	}

	public Type getMemberType(String member) {
		return this.memberTypes.get(member);
	}

}
