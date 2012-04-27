package edu.tongji.sse.hadoop.datareconciliation.hashgeneration.algorithms;

import java.security.MessageDigest;

import edu.tongji.sse.hadoop.datareconciliation.hashgeneration.HashGener;
import edu.tongji.sse.hadoop.datareconciliation.utility.HexString;
public class MD5_64 extends HashGener<byte[]> {
	public MD5_64(){
		super(64);
	}
	/** Set the source String using setSource() first, an exception otherwise
	 * @see edu.tongji.sse.hadoop.datareconciliation.hashgeneration.HashGener#getHashValue()
	 * @return 64 bits (16 bytes)  of the MD5 value as a format byte[].
	 * @version current
	 */
	@Override
	public byte[] getHashValue() {
		try {
			byte[] strTemp = super.strSource.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			return HexString.parseBytes(HexString.parseHexString64(md));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
