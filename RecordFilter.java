public class RecordFilter{
	public int count;
	public int charaNumber;
	
	public RecordFilter(){
		this(-1, -1);
	}
	public RecordFilter(int cnt, int charaNum){
		count = cnt;
		charaNumber = charaNum;
	}
	public String toString(){
		//where句以降のsql文を書く
		StringBuilder sb = new StringBuilder();
		if(charaNumber > 0)
			sb.append("p2id = " + charaNumber);
		return sb.toString();
	}
}
