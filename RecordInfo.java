public class RecordInfo {
	int recordCount;
	int wins;
	public RecordInfo(int count, int win)throws Exception{
		if(count <= 0)
			throw new Exception("Invalid record count");
		else{
			recordCount = count;
			wins = win;
		}
	}

	public int getRecordCount(){return recordCount;}
	public int getWins(){return wins;}
	public float getWinningPercentage(){
		if(recordCount > 0)
			return (float)wins / recordCount;
		return -1;
	}
}
