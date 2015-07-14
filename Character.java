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
		return "未指定";
	}
	private String[] names = {
		"博麗霊夢", "霧雨魔理沙",
		"雲居一輪＆雲山","聖白蓮",
		"物部布都", "豊郷耳神子",
		"河城にとり", "古明地こいし", "二ツ岩マミゾウ", "秦こころ",
		"茨木華仙", "藤原妹紅", "少名針妙丸", "宇佐見董子"
	};
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
