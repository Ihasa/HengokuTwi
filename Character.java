public enum Character{
	INVALID(-1),
	REIMU(0), MARISA(1),
	ICHIRIN(2), HIJIRI(3), HUTO(4), MIKO(5),
	NITORI(6), KOISHI(7), MAMIZOU(8), KOKORO(9),
	KASEN(10), MOKOU(11), SINMYOMARU(12), SUMIREKO(13);
	
	private final int id;
	private Character(int id){
		this.id = id;
	}
	public int getId(){ return id; }
	public boolean isValid(){ return id != -1; }
};
/*
０霊夢
１まりさ
２いちりん
３ひじり
４ふと
５みこ
６にとり
７こいし
８マミゾウ
９こころ
１０かせん
１１もこう
１２しんみょーまる
１３すみれこ
*/
