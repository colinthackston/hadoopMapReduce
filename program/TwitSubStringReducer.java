package lastPratical_GoBigOrGoHome;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
//copied from the example given in the lecture example
public class TwitSubStringReducer  extends MapReduceBase implements Reducer<Text, LongWritable, Text, LongWritable>{
	public void reduce(Text key, Iterator<LongWritable> values, OutputCollector<Text, LongWritable> output, Reporter reporter) throws IOException {

        // The key is the word.
        // The values are all the counts associated with that word (one copy of '1' for each occurrence)
		 int sum = 0;
	        while (values.hasNext()) {
	            long value = values.next().get();
	            sum += value;
	        }
	        output.collect(key, new LongWritable(sum));
      
    }
}
