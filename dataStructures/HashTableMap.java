package dataStructures;
// Erik Orndahl 4/3/13 This is the example provided in the book.

import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Map.Entry;
import MTGApplication.Card;


public class HashTableMap<K,V> {
	protected Entry<K,V> AVAILABLE = new HashEntry<K,V>(null,null);
	protected int n = 0;
	protected int prime, capacity;
	public Entry<K,V>[] bucket;
	public long scale;
	protected long shift;
    private static final int VALUES = 1;
	public HashTableMap(int cap) { this(2393, cap); }
	public HashTableMap(int i, int cap) {
		prime = i;
		capacity = cap;
		bucket = new Entry[capacity];
		java.util.Random rand = new java.util.Random();
		scale = rand.nextInt(prime-1) + 1;
		shift = rand.nextInt(prime);
	}
	
	
	//determines whether a key is valid
	protected void checkKey(K k) throws InvalidKeyException {
		if( k == null) throw new InvalidKeyException("Invalid key: null.");
	}
	
	
	//Hash function applying MAD method to default hash code
	public int hashValue(K key) {
		return(int) ((Math.abs(key.hashCode()*scale + shift) % prime) % capacity);
	}
	
	
	//returns the number of entries in the hash code
	public int size() {return n;}
	
	
	//returns whether or not the table is empty
	public boolean isEmpty() {return(n==0);}
	
	
	//helper search method - returns index of found key or -(a + 1), where a is the index of the first
	//empty or available slot found
	public int findEntry(Object key) throws InvalidKeyException {
		int avail = -1;
		checkKey((K) key);
		int i = hashValue((K) key);
		int j = i;
		do{
			Entry<K,V> e = bucket[i];
			if ( e == null) {
					if(avail < 0)
							avail = i; //key is not in table
		break;
		}
		if(key.equals(e.getKey()))
				return i;
		if(e ==AVAILABLE) {
			if(avail < 0)
				avail = i; //remember that this slot is available
		}
		i = ( i + 1) % capacity; //keep looking
	}while( i!= j);
	return -(avail + 1); //first empty or available slot
		}
	
	
	//returns the value associated with a key
	public V get(Object key) {
		int i = 0;
		try {
			i = findEntry(key);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //helper method for finding a key
		if (i < 0) return null; //this is no value for this key, so return null
		return bucket[i].getValue(); // return the found value in this case.
	}
	
	//
	public K getKey(Object o) {
		
		return null;
	}
	
	
	//put a key value pair in the map, replacing previous once if it exists
	public V put(K key, V value){
		int i = 0;
		try {
			i = findEntry(key);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //find the appropriate spot for this entry
		if(i>=0)
			return((HashEntry<K,V>) bucket[i]).setValue(value);
		if (n>= capacity/2) {
			try {
				rehash();
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //rehash to keep the load factor <= to.5
			try {
				i = findEntry(key);
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		bucket[-i-1] = new HashEntry<K,V>(key,value);
		n++;
		return null;
		}
	
	
	//doubles the size of the hash table and rehashes all the entries
	protected void rehash() throws InvalidKeyException {
		capacity = 2*capacity;
		Entry<K,V>[] old = bucket;
		bucket =new Entry[capacity]; //new bucket twice as large
		java.util.Random rand = new java.util.Random();
		scale = rand.nextInt(prime-1) + 1; 
		shift = rand.nextInt(prime); 
		for(int i = 0; i<old.length;i++) {
			Entry<K,V> e = old[i];
			if ((e != null) && (e!=AVAILABLE)) { //a valid entry
				int j = -1 - findEntry(e.getKey());
				bucket[j] = e;
			}
		}
	}
	
	
	//removes the key value pair with a specified key
	public V remove(Object key)  {
		int i = 0;
		try {
			i = findEntry(key);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i<0) return null;
		V toReturn = bucket[i].getValue();
		bucket[i] = AVAILABLE;
		n--;
		return toReturn;
	}
	//return a collection of cards within table
	public ArrayList<Card> cardEntries(){
		ArrayList<Card> list = new ArrayList();
		for(int i = 0; i< bucket.length; i++) {
			if(bucket[i]!= null) {
				list.add((Card) bucket[i].getValue());
			}	
		}
		return list;
	}
}
