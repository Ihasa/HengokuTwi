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

	public String getQuery(){
		//���L�����A����L�����Ńt�B���^�����O
		StringBuilder sb = new StringBuilder();
		if(character_p1.isSpecified()){
			sb.append("where p1id = " + character_p1.getId());
			if(character_p2.isSpecified())
				sb.append(" and p2id = ").append(character_p2.getId());
		}
		else if(character_p2.isSpecified()){
			sb.append("where p2id = ").append(character_p2.getId());
		}
		//����n��̐�т����擾
		if(count > 0){
			sb.append(" order by timestamp desc");
			sb.append(" limit ").append(count);
		}
		return sb.toString();
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		if(character_p1 != Character.UNSPECIFIED){
			if(character_p2 != Character.UNSPECIFIED){
				sb.append(character_p1.toString());
				sb.append("vs");
				sb.append(character_p2.toString());
				sb.append("��");
			}else{
				sb.append(character_p1.toString());
				sb.append("�g�p��");
			}
			if(count > 0)
				sb.append("(����" + count + "��)");
		}else{
			if(character_p2 != Character.UNSPECIFIED){
				sb.append("vs");
				sb.append(character_p2.toString());
				sb.append("��");
				if(count > 0)
					sb.append("(����" + count + "��)");
			}else{
				if(count > 0)
					sb.append("����" + count + "��");
				else
					sb.append("����");
			}
		}		
		return sb.toString();
	}
}
