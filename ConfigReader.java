import java.util.Scanner;
import java.io.File;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class ConfigReader{
	private Scanner scanner;
	private HashMap<String, RecordFilter> wildCards;
	private ConfigReader(Scanner s){
		scanner = s;
		wildCards = new HashMap<String, RecordFilter>();
		wildCards.put("all", new RecordFilter());
	}
	public ConfigReader(InputStream stream){
		this(new Scanner(stream));
	}
	public ConfigReader(String filename) throws FileNotFoundException{
		this(new Scanner(new File(filename)));
	}
	
	public List<RecordFilter> getFilters(){
		List<RecordFilter> res = new ArrayList<RecordFilter>();
		while(scanner.hasNextLine()){
			try{
				String line = scanner.nextLine().replaceAll("\\s", "");
				if(line.charAt(0) != '#')
					res.add(createFilter(line));
			}catch(Exception e){}
		}
		scanner.reset();
		return res;
	}
	private RecordFilter createFilter(String str) throws Exception{
		if(wildCards.containsKey(str))
			return wildCards.get(str);
		
		String[] words = str.split(",");
		int count = -1;
		Character c1 = Character.UNSPECIFIED;
		Character c2 = Character.UNSPECIFIED;
		
		if(!words[0].equals("_"))
			count = Integer.parseInt(words[0]);
		if(!words[1].equals("_"))
			c1 = Character.valueOf(words[1]);
		if(!words[2].equals("_"))
			c2 = Character.valueOf(words[2]);
		return new RecordFilter(count, c1, c2);
	}
	
	public void finalize(){
		scanner.close();
	}
}
