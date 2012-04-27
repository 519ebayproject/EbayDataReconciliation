/**
 * 
 */
package edu.tongji.sse.hadoop.datareconciliation.hashgeneration;

import edu.tongji.sse.hadoop.datareconciliation.hashgeneration.interfaces.IHashGener;

/**
 * @author root
 *
 */
public abstract class HashGener<V> implements IHashGener<V> {
	protected String strSource;
	protected int Bits;
	HashGener() {
		strSource = new String("");
		Bits = 0; 
	}	
	public HashGener(int bits) {
		this.strSource = "";
		Bits = bits;
	}


	public abstract V getHashValue();
	
	@Override
	public void setSource(String s) {
		this.strSource = new String(s);
	}

	/**
	 * @param fullclassname the full name of the intended algorithm
	 * @return an instance of that algorithm. Using IHashGener to handle 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static IHashGener getInstance(String fullclassname) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class<? extends HashGener<?>> algorithm = 
				(Class<? extends HashGener<?>>) Class.forName(fullclassname);
		return (IHashGener) algorithm.newInstance();
	}
}
