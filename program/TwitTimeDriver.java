package lastPratical_GoBigOrGoHome;

import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
//copied from the lecture example slides 
public class TwitTimeDriver {
public static void main(String[] args) throws IOException{
		System.out.println("Please enter file location of tweets");
		Scanner scanner = new Scanner(System.in);
		String path1 = scanner.nextLine();
		
		System.out.println("Please enter where you would like the output");
		String path2=scanner.nextLine();
		
        JobConf job = new JobConf();

        FileInputFormat.setInputPaths(job, new Path("/cs/home/cjt6/Documents/bbc2012-08-11-04-58-split.jsonl"));
        FileOutputFormat.setOutputPath(job, new Path("/cs/home/cjt6/Documents/cs1003/twitDate"));

        // Output from reducer maps words to counts.
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        // The output of the mapper is a map from words (including duplicates) to the value 1.
        job.setMapperClass(TwitTimeMapper.class);

        // The output of the reducer is a map from unique words to their total counts.
        job.setReducerClass(TwitTimeReducer.class);

        JobClient.runJob(job);
		
        
	}
	

}
