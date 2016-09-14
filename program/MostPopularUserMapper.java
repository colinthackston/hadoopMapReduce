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

public class MostPopularUserMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, LongWritable>{
	public void map(LongWritable key, Text value, OutputCollector<Text, LongWritable> output, Reporter reporter) throws IOException {
		String line = value.toString();
		BufferedReader br = new BufferedReader(new StringReader(line));
		while (br.readLine() != null) {	
			try{
				JSONObject contributor = new JSONObject(line);
				String hi = contributor.getString("text");
				//splits the string of text into individiual words
				//if it has the @ symbol (which denotes a username) then it is added to the map
				String[] individualWords = hi.split(" ");
				for (int x=0; x<individualWords.length; x++){
					if (individualWords[x].contains("@")){
						String result = "@" + CleanWord(individualWords[x].toLowerCase()); 
						output.collect(new Text(result), new LongWritable(1));
					}
				}
				
				br.readLine();
			 }catch (JSONException e){
	    		e.printStackTrace();
			}
		}
	}
	//removes spaces and special characters to increase matches 
	public static String CleanWord(String word){
		String resultString = word.replaceAll("[^\\p{L}\\p{Nd}]", "");
		return resultString;
	}

}
