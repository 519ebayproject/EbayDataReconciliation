package edu.tongji.sse.hadoop.datareconciliation.job.basicjoin;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import edu.tongji.sse.hadoop.datareconciliation.bloomfilters.constants.BytesWritable;
import edu.tongji.sse.hadoop.datareconciliation.bloomfilters.hash.DefaultBloomFilterHash;
import edu.tongji.sse.hadoop.datareconciliation.hashgeneration.HashGener;
import edu.tongji.sse.hadoop.datareconciliation.hashgeneration.interfaces.IHashGener;
import edu.tongji.sse.hadoop.datareconciliation.utility.BloomFilterKey;


public class HFileMapper extends Mapper< LongWritable, Text, LongWritable, Text> {
	
	private Text outputVal = new Text();
	private LongWritable BFKey = new LongWritable();
	private int nRecords = 0;

	private IHashGener<byte[]> hashGener= null;
	private String MD5Type = "";
	private BytesWritable MD5Bytes = new BytesWritable();

	

	/**
	   * Initiate
	   */
	  @SuppressWarnings("unchecked")
	@Override
	protected void setup(Context context) throws IOException,
			InterruptedException {
		Configuration conf = context.getConfiguration();

		this.nRecords = conf.getInt("filter-nRecords", nRecords);
		this.MD5Type = conf.get("MD5-types", MD5Type);
		try {
			this.hashGener = HashGener.getInstance(this.MD5Type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.append(e.getMessage());
			e.printStackTrace();
		}
	}

	  /**
	   * Cleanup
	   */
	  @Override
	  protected void cleanup(Context context) throws IOException,
	      InterruptedException {
	    super.cleanup(context);

	  }

	  /**
	   * Map task.
	   */
	  public void map(LongWritable key, Text row, Context context)
		      throws IOException, InterruptedException {

				// Generate MD5 values
				
				// the map input key is generated by system because of
				// TextInputFormat
				String record = row.toString();
				String[] splits = record.split("[\t|,]");
/*
				// add record key to value
				String value = new String("" + Long.parseLong(splits[0]));
				// add every fields without ',' to value
				for (int i = 1; i < splits.length; i++) {
					value += splits[i];
				}

				// set hash source string
				this.hashGener.setSource(value);
				
				// get MD5 value and set to MD5Bytes
				MD5Bytes.set(this.hashGener.getHashValue());
		
	*/	  
			  context.write( new LongWritable(Long.parseLong(splits[0])), new Text("r"+record));//splits[0]+","+MD5Bytes.toString())) ;
			  //context.write( new IntWritable(Integer.parseInt(splits[0])), row) ;
		  }
}
