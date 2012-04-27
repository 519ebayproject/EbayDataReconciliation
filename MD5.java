/**
 * 
 */
package edu.tongji.sse.hadoop.datareconciliation.hashgeneration.algorithms;

import java.security.MessageDigest;

import edu.tongji.sse.hadoop.datareconciliation.hashgeneration.HashGener;


/** Set the source String using setSource() first, an exception otherwise
 * @author Jingxiao Zhang
 * @see edu.tongji.sse.hadoop.datareconciliation.hashgeneration.HashGener#getHashValue()
 * @return 128 bits (16 bytes)  of the MD5 value as a format byte[].
 * @version 1.00
 */
public class MD5 extends HashGener<byte[]> {

	public MD5(){
		super(128);
	}
	
	@Override
	public byte[] getHashValue() {
		try {
			byte[] strTemp = super.strSource.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			return md;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
