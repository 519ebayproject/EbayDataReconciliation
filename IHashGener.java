/**
 * 
 */
package edu.tongji.sse.hadoop.datareconciliation.hashgeneration.interfaces;

/**
 * @author root
 *
 */
public interface IHashGener<ValueType> {
	public ValueType getHashValue();
	public void setSource(String s);
}
