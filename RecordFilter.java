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
		//����n��̐�сA����L�����Ńt�B���^�����O
		StringBuilder sb = new StringBuilder();
		if(charaNumber > 0){
			sb.append("where p2id = ").append(charaNumber);
		}
		if(count > 0){
			sb.append(" order by timestamp desc");
			sb.append(" limit ").append(count);
		}
		return sb.toString();
	}
}
