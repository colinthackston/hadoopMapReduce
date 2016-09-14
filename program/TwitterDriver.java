package lastPratical_GoBigOrGoHome;


import java.io.IOException;



import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;

import org.apache.hadoop.mapred.JobConf;

public class TwitterDriver {
//copied from the example in the lecture slides 
	public static void main(String[] args) throws IOException{
				
        JobConf job = new JobConf();
       

        FileInputFormat.setInputPaths(job, new Path("/cs/home/cjt6/Documents/bbc2012-08-11-04-58-split.jsonl"));
        FileOutputFormat.setOutputPath(job, new Path("/cs/home/cjt6/Documents/cs1003/twit"));

        // Output from reducer maps words to counts.
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        // The output of the mapper is a map from words (including duplicates) to the value 1.
        job.setMapperClass(TwitSubStringMapper.class);

        // The output of the reducer is a map from unique words to their total counts.
        job.setReducerClass(TwitSubStringReducer.class);

      //  JobClient.runJob(job);
        
	}
	
	
}

