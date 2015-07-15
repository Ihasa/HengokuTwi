import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class HengokuTwi {
    public static void main(String[] args) throws Exception {
		TweetManager tm = TweetManager.getInstance();
		if(!tm.isAuthorized()){
			System.out.println(tm.createRequestToken());
			Scanner s = new Scanner(System.in);
			String pin = s.nextLine();
			s.close();
			tm.authorize(pin);
		}

		//ツイート内容の決定(外部ファイルからフィルタリング設定等読み込む)
		//Default.dbへのパスも一応読み込むことにする
		List<RecordFilter> filters = new ArrayList<RecordFilter>();
		Scanner s = new Scanner(new File("config.txt"));
		while(s.hasNextLine()){
			String line = s.nextLine();
			filters.add(createFilter(line));
		}
		s.close();

		StringBuilder sb = new StringBuilder();
		sb.append("＊あなたの東方深秘録戦績＊").append("\n\n");
		RecordGetter getter = RecordGetter.createInstance("Default.db");
		//フィルターに基づいてツイート
		if(filters.size() > 0){
			for(RecordFilter f : filters){
				RecordInfo info = getter.getRecordInfo(f);
				sb.append(info.toString()).append("\n\n");
			}
		}else{
			//共通で総合戦績は出す。あとメインキャラも
			RecordInfo info = getter.getRecordInfo();
			sb.append(info.toString()).append("\n\n");
			//直近50
			info = getter.getRecordInfo(50);
			sb.append(info.toString()).append("\n\n");
			//直近30
			info = getter.getRecordInfo(30);
			sb.append(info.toString()).append("\n\n");
			//vs神子戦での総合戦績
			info = getter.getRecordInfo(10, Character.UNSPECIFIED, Character.MIKO);
			sb.append(info.toString()).append("\n\n");
			//直近40戦のvs一輪戦の戦績
			info = getter.getRecordInfo(40, Character.UNSPECIFIED, Character.ICHIRIN);
			sb.append(info.toString()).append("\n\n");
			//霊夢使用時のvs魔理沙戦の戦績(エラー)
			info = getter.getRecordInfo(Character.REIMU, Character.MARISA);
			if(info != null)
				sb.append(info.toString()).append("\n\n");
		}
		sb.append("#東方深秘録");//　#辺獄録　#HengokuTwi");
		
		System.out.println(sb.toString());
		System.out.println(sb.length());
		/*
		if(tm.tweet(sb.toString()) != null)
			System.out.println("tweeted");*/
		//カスタムで設定ファイルによって規定される内容をツイートする
	}
	private static RecordFilter createFilter(String str){
		String[] words = str.split(",");
		int count = -1;
		Character c1 = Character.UNSPECIFIED;
		Character c2 = Character.UNSPECIFIED;
		try{
			count = Integer.parseInt(words[0]);
		}catch(Exception e){}
		try{
			c1 = Character.valueOf(words[1]);
		}catch(Exception e){}
		try{
			c2 = Character.valueOf(words[2]);
		}catch(Exception e){}
		return new RecordFilter(count, c1, c2);
	}
}
