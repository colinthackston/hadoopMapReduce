package lastPratical_GoBigOrGoHome;


import java.io.File;
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
public class MostPopularUserDriver {
public static void main(String[] args) throws IOException{
		
	System.out.println("Please enter file location of tweets");
	Scanner scanner = new Scanner(System.in);
	String path1 = scanner.nextLine();
	
	System.out.println("Please enter where you would like the output");
	String path2=scanner.nextLine();
	
    JobConf job = new JobConf();

    FileInputFormat.setInputPaths(job, new Path(path1));
    FileOutputFormat.setOutputPath(job, new Path(path2));

        // Output from reducer maps words to counts.
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        // The output of the mapper is a map from words (including duplicates) to the value 1.
        job.setMapperClass(MostPopularUserMapper.class);

        // The output of the reducer is a map from unique words to their total counts.
        job.setReducerClass(MostPopularUserReducer.class);

        //JobClient.runJob(job);
        //final output of the three most tweeted users is in a separate file called done.txt
       // File file = new File("/cs/home/cjt6/Documents/cs1003/popular/part-00000");
        //Top3 top3 = new Top3();
        //top3.FileSpitter(file);
        
        
	}
}
