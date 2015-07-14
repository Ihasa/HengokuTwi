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
};
/*
ÇOóÏñ≤
ÇPÇ‹ÇËÇ≥
ÇQÇ¢ÇøÇËÇÒ
ÇRÇ–Ç∂ÇË
ÇSÇ”Ç∆
ÇTÇ›Ç±
ÇUÇ…Ç∆ÇË
ÇVÇ±Ç¢Çµ
ÇWÉ}É~É]ÉE
ÇXÇ±Ç±ÇÎ
ÇPÇOÇ©ÇπÇÒ
ÇPÇPÇ‡Ç±Ç§
ÇPÇQÇµÇÒÇ›ÇÂÅ[Ç‹ÇÈ
ÇPÇRÇ∑Ç›ÇÍÇ±
*/
