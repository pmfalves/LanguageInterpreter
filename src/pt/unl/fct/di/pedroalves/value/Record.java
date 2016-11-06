package pt.unl.fct.di.pedroalves.value;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Stores a value of type RECORD. A record is a value composed of multiple
 * fields (each storing a distinct value).
 * 
 * @author pedroalves
 *
 */
public class Record implements Value {

	private final Map<String, Value> fields;

	public Record(final List<String> fields, final List<Value> values) {
		this.fields = new HashMap<String, Value>(fields.size());
		final Iterator<Value> iter = values.iterator();
		for (final String m : fields) {
			this.fields.put(m, iter.next());
		}
	}

	public Value getFieldValue(final String field) {
		return this.fields.get(field);
	}

	@Override
	public VType typeOf() {
		return VType.RECORD;
	}

}
