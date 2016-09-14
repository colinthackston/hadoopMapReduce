package lastPratical_GoBigOrGoHome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import org.json.JSONException;
import org.json.JSONObject;

public class TwitSubStringMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, LongWritable>{
	public void map(LongWritable key, Text value, OutputCollector<Text, LongWritable> output, Reporter reporter) throws IOException {
		//current string that is the criteria for searching; can be changed to anything
		String search = "gymnastics";
		
		String line = value.toString();
		BufferedReader br = new BufferedReader(new StringReader(line));
		while (br.readLine() != null) {	
			try{
				//parses the JSON file
				JSONObject contributor = new JSONObject(line);
				String hi = contributor.getString("text");
				//converts all of the strings to lower case to increase matches
				if (hi.toLowerCase().contains(search)){
					output.collect(new Text(hi), new LongWritable(1));
				}
				br.readLine();
			}catch (JSONException e){
	    		e.printStackTrace();
			}
		}
	}


	

}
