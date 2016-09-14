package lastPratical_GoBigOrGoHome;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//class that finds the top 3 most tweeted users 
public class Top3 {
	public void FileSpitter(File file) throws IOException{
		Writer writer = new PrintWriter("done.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		Map map1 = new HashMap();
        while (br.readLine() != null){
        	String content = br.readLine();
        	
        	String[] Content = content.split("[\\W\\s]+");
        	//splits the text from the MapReduce program into an array and then adds them to another map
        	int co2 = Integer.parseInt(Content[2]);
        	map1.put(Content[1], co2);
        	
        	}
        ArrayList<Integer> keys = new ArrayList<Integer>(sortByValue(map1).keySet());
		ArrayList final_order = new ArrayList();
		for (int j=keys.size()-1; j>=0; j--){
			 Object word = keys.get(j);
			
		 }
		 for (int j=keys.size()-1; j>=0; j--){
			
			Object word = keys.get(j);
			final_order.add(word);
		 }
		 for(int z=0; z<3; z++){
			 writer.append((CharSequence) final_order.get(z) + " ");
			 writer.append(String.valueOf(map1.get(final_order.get(z)) + "\n"));
			 
			 
		 }
		 writer.close();
	}
	//taken from the previous practical on Zipf's law
	public Map sortByValue(Map map) {
		//creates a new list of all the entries in the map
	     List listKeys = new LinkedList(map.entrySet());
	     //sorts the list of entries 
	     Collections.sort(listKeys, new Comparator() {
	    	 //calls the entries and compares them
	          public int compare(Object key1, Object key2) {
	        	  //casts the result as a comparable and then compares the values of the keys
	               return ((Comparable) ((Map.Entry) (key1)).getValue())
	              .compareTo(((Map.Entry) (key2)).getValue());
	          }});
	    //creates a new Linkedhashmap of the final output 
	    Map result = new LinkedHashMap();
	    //creates of loop of an iterator that increments through the original list
	    for (Iterator lidioit = listKeys.iterator(); lidioit.hasNext();) {
	    	//calls the next entry from list
	        Map.Entry entry = (Map.Entry)lidioit.next();
	        //adds the new key and value to result
	        result.put(entry.getKey(), entry.getValue());
	    }
	    return result;
	} 

}
