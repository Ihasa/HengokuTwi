import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
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
		//Scanner s = new Scanner("config.txt");
		//s.close();
		
		StringBuilder sb = new StringBuilder();
		RecordGetter getter = RecordGetter.createInstance("Default.db");
		//共通で総合戦績は出す。あとメインキャラも
		RecordInfo info = getter.getRecordInfo();
		sb.append("＊あなたの東方深秘録戦績＊").append('\n');
		//以下は設定ファイルで規定する↓
		//foreach...
		
		//総合
		sb.append(info.toString()).append('\n');
		//直近50
		info = getter.getRecordInfo(new RecordFilter(50));
		sb.append(info.toString()).append('\n');		
		//直近30
		info = getter.getRecordInfo(new RecordFilter(30));
		sb.append(info.toString()).append('\n');
		//魔理沙戦での戦績
		info = getter.getRecordInfo(new RecordFilter(Character.UNSPECIFIED, Character.MARISA));
		sb.append(info.toString()).append('\n');
		//直近40戦の対こいし戦の戦績
		info = getter.getRecordInfo(new RecordFilter(40, Character.UNSPECIFIED, Character.KOISHI));
		sb.append(info.toString()).append('\n');
		sb.append("#東方深秘録");
		
		System.out.println(sb.toString());
		System.out.println(sb.length());
		//tm.tweet(sb.toString());
		//カスタムで設定ファイルによって規定される内容をツイートする
	}
}
