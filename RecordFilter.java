public class RecordFilter{
	public int count;
	public Character character_p1;
	public Character character_p2;
	
	public RecordFilter(){
		this(-1, Character.UNSPECIFIED, Character.UNSPECIFIED);
	}
	public RecordFilter(int cnt){
		this(cnt, Character.UNSPECIFIED, Character.UNSPECIFIED);
	}
	public RecordFilter(Character chara1, Character chara2){
		this(-1, chara1, chara2);
	}
	public RecordFilter(int cnt, Character chara1, Character chara2){
		count = cnt;
		character_p1 = chara1;
		character_p2 = chara2;
	}

	public String toString(){
		//自キャラ、相手キャラでフィルタリング
		StringBuilder sb = new StringBuilder();
		if(character_p1.isSpecified()){
			sb.append("where p1id = " + character_p1);
			if(character_p2.isSpecified())
				sb.append(" and p2id = ").append(character_p2.getId());
		}
		else if(character_p2.isSpecified()){
			sb.append("where p2id = ").append(character_p2.getId());
		}
		//直近n戦の戦績だけ取得
		if(count > 0){
			sb.append(" order by timestamp desc");
			sb.append(" limit ").append(count);
		}
		return sb.toString();
	}
}
