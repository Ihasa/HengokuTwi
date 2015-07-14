public enum Character{
	UNSPECIFIED(-1),
	REIMU(0), MARISA(1),
	ICHIRIN(2), HIJIRI(3), HUTO(4), MIKO(5),
	NITORI(6), KOISHI(7), MAMIZOU(8), KOKORO(9),
	KASEN(10), MOKOU(11), SINMYOMARU(12), SUMIREKO(13);
	
	private final int id;
	private Character(int id){
		this.id = id;
	}
	public int getId(){ return id; }
	public boolean isSpecified(){ return id != -1; }
	public String toString(){
		if(isSpecified())
			return names[id];
		return "���w��";
	}
	private String[] names = {
		"����얲", "���J������",
		"�_����ց��_�R","�����@",
		"�����z�s", "�L�����_�q",
		"�͏�ɂƂ�", "�Ö��n������", "��c��}�~�]�E", "�`������",
		"��؉ؐ�", "�������g", "�����j����", "�F�������q"
	};
};
/*
�O�얲
�P�܂肳
�Q�������
�R�Ђ���
�S�ӂ�
�T�݂�
�U�ɂƂ�
�V������
�W�}�~�]�E
�X������
�P�O������
�P�P������
�P�Q����݂�[�܂�
�P�R���݂ꂱ
*/
