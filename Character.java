public enum Character{
	UNSPECIFIED(-1),
	REIMU(0), MARISA(1),
	ICHIRIN(2), BYAKUREN(3), HUTO(4), MIKO(5),
	NITORI(6), KOISHI(7), MAMIZOU(8), KOKORO(9),
	KASEN(10), MOKOU(11), SHINMYOMARU(12), SUMIREKO(13);
	
	private final int id;
	private Character(int id){
		this.id = id;
	}
	public int getId(){ return id; }
	public boolean isSpecified(){ return id != -1; }
	public String toString(){
		if(isSpecified())
			return names[id];
		return "–¢w’è";
	}
	public static Character fromId(int id){
		if(id == -1)
			return Character.UNSPECIFIED;
		return enumValues[id];
	}
	private String[] names = {
		"”—í—ì–²", "–¶‰J–‚—¹",
		"‰_‹ˆê—Ö•‰_R","¹”’˜@",
		"•¨•”•z“s", "–L‹½¨_q",
		"‰Íé‚É‚Æ‚è", "ŒÃ–¾’n‚±‚¢‚µ", "“ñƒcŠâƒ}ƒ~ƒ]ƒE", "`‚±‚±‚ë",
		"ˆï–Ø‰Øå", "“¡Œ´–…g", "­–¼j–­ŠÛ", "‰F²Œ©“Ÿq"
	};
	private static Character[] enumValues = {
		Character.REIMU, Character.MARISA,
		Character.ICHIRIN,Character.BYAKUREN,
		Character.HUTO, Character.MIKO,
		Character.NITORI, Character.KOISHI, Character.MAMIZOU, Character.KOKORO,
		Character.KASEN, Character.MOKOU, Character.SHINMYOMARU, Character.SUMIREKO
	};
};
/*
‚O—ì–²
‚P‚Ü‚è‚³
‚Q‚¢‚¿‚è‚ñ
‚R‚Ğ‚¶‚è
‚S‚Ó‚Æ
‚T‚İ‚±
‚U‚É‚Æ‚è
‚V‚±‚¢‚µ
‚Wƒ}ƒ~ƒ]ƒE
‚X‚±‚±‚ë
‚P‚O‚©‚¹‚ñ
‚P‚P‚à‚±‚¤
‚P‚Q‚µ‚ñ‚İ‚å[‚Ü‚é
‚P‚R‚·‚İ‚ê‚±
*/
