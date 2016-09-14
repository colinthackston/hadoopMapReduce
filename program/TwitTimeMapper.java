package lastPratical_GoBigOrGoHome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.json.JSONException;
import org.json.JSONObject;

public class TwitTimeMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, LongWritable>{
	//the method looks for tweets inbetween these two dates; they can be changed but must be in the same format
	static String date1="08/11/2012 03:02:01";
	static String date2="08/11/2012 04:15:24";
	 public void map(LongWritable key, Text value, OutputCollector<Text, LongWritable> output, Reporter reporter) throws IOException {

	        // The key is the character offset within the file of the start of the line, ignored.
	        // The value is a line from the file.
				 
	      String line = value.toString();
	      BufferedReader br = new BufferedReader(new StringReader(line));
	      while (br.readLine() != null) {	
	    	try{
			JSONObject contributor = new JSONObject(line);
			String hi = contributor.getString("created_at");
			String text = contributor.getString("text");
			String tweetDate = ConvertToDate(hi);
			if (CompareDates(date1, date2, tweetDate)==true){
				output.collect(new Text(text), new LongWritable(1));
			}
			br.readLine();
	    	}
	    	catch (JSONException e){
	    		e.printStackTrace();
	    	}
				
	   		 }
	    }
	 public static String ConvertToDate(String annoyingDate){
	//splits the string into an array so it can be manipulated
			String[] dateArray = annoyingDate.split(" ");
			//then split into an arraylist so items can be added and removed with ease
			ArrayList <String> dateList = new <String> ArrayList();
			for (int x=0; x<dateArray.length; x++){
				dateList.add(dateArray[x]);
			}
			dateList.remove(0);
			dateList.remove(3);
			//12 different conditionals for replacing the month
			if (dateList.get(0).contains("Jan")){
				dateList.remove(0);
				dateList.add(0, "01");
			}
			if (dateList.get(0).contains("Feb")){
				dateList.remove(0);
				dateList.add(0, "02");
			}
			if (dateList.get(0).contains("Mar")){
				dateList.remove(0);
				dateList.add(0, "03");
			}
			if (dateList.get(0).contains("Apr")){
				dateList.remove(0);
				dateList.add(0, "04");
			}
			if (dateList.get(0).contains("May")){
				dateList.remove(0);
				dateList.add(0, "05");
			}
			if (dateList.get(0).contains("Jun")){
				dateList.remove(0);
				dateList.add(0, "06");
			}
			if (dateList.get(0).contains("Jul")){
				dateList.remove(0);
				dateList.add(0, "07");
			}
			if (dateList.get(0).contains("Aug")){
				dateList.remove(0);
				dateList.add(0, "08");
			}
			if (dateList.get(0).contains("Sep")){
				dateList.remove(0);
				dateList.add(0, "09");
			}
			if (dateList.get(0).contains("Oct")){
				dateList.remove(0);
				dateList.add(0, "10");
			}
			if (dateList.get(0).contains("Nov")){
				dateList.remove(0);
				dateList.add(0, "11");
			}
			if (dateList.get(0).contains("Dec")){
				dateList.remove(0);
				dateList.add(0, "12");
			}
			String local = dateList.get(3);
			dateList.remove(3);
			dateList.add(2, local);
			//formats the date 
			String finalDate = (dateList.get(0) + "/" + dateList.get(1) + "/" + dateList.get(2) + " " + dateList.get(3));
			return finalDate;
	 }
	 public static Boolean CompareDates(String userDate1, String userDate2, String twitDate){
		 //uses the built in compare function to to determine if there are tweets between the two dates 
			Boolean result=false;
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				Date date1 = sdf.parse(userDate1);
				Date date2= sdf.parse(userDate2);
				Date date3 = sdf.parse(twitDate);
				if (date1.compareTo(date3)<=0 && date2.compareTo(date3)>=0){
					result=true;
				}
			}catch (ParseException ex){
				ex.printStackTrace();
			}
			return result;
			
		}


}
