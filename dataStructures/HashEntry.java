package dataStructures;
import java.util.Map.Entry;

public class HashEntry<K,V> implements Entry<K,V> {
	protected K key;
	protected V value;
	public HashEntry(K k, V v) { key = k; value = v;}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public V setValue(V val) {
		V oldValue = value;
		value = val;
		return oldValue;
	}
	@Override
	public boolean equals(Object o) {
		HashEntry<K,V> ent;
		try { ent = (HashEntry<K,V>) o; }
		catch (ClassCastException ex) {return false;}
		return (ent.getKey() == key) && (ent.getValue() == value);
	}
}
