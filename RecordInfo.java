public class RecordInfo {
	private int recordCount;
	private int wins;
	private RecordFilter filter;
	public RecordInfo(int count, int win, RecordFilter f)throws Exception{
		if(count <= 0)
			throw new Exception("Invalid record count");
		else{
			recordCount = count;
			wins = win;
			filter = f;
		}
	}

	public int getRecordCount(){return recordCount;}
	public int getWins(){return wins;}
	public int getLoses(){return recordCount - wins;}
	public float getWinningPercentage(){
		if(recordCount > 0)
			return (float)wins / recordCount;
		return -1;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(filter.toString() + ":");
		sb.append(getWins() + "èü");
		sb.append(getLoses() + "îs");
		float winning = (int)(getWinningPercentage() * 1000) / 10.0f;
		sb.append("(" + winning + "%)");
		return sb.toString();
	}
}
