public class RecordFilter{
	public int count;
	public Character character_p2;
	
	public RecordFilter(){
		this(-1, Character.INVALID);
	}
	public RecordFilter(int cnt, Character chara2){
		count = cnt;
		character_p2 = chara2;
	}
	public String toString(){
		//直近n戦の戦績、相手キャラでフィルタリング
		StringBuilder sb = new StringBuilder();
		if(character_p2.isValid()){
			sb.append("where p2id = ").append(character_p2.getId());
		}
		if(count > 0){
			sb.append(" order by timestamp desc");
			sb.append(" limit ").append(count);
		}
		return sb.toString();
	}
}
