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
		//List<RecordFilter> filters = getFiltersFromFile();
		ConfigReader cr = new ConfigReader("config.txt");
		List<RecordFilter> filters = cr.getFilters();

		StringBuilder sb = new StringBuilder();
		sb.append("＊あなたの東方深秘録戦績＊").append("\n\n");
		RecordGetter getter = RecordGetter.createInstance("Default.db");
		//フィルターに基づいてツイート
		if(filters.size() > 0){
			for(RecordFilter f : filters){
				RecordInfo info = getter.getRecordInfo(f);
				if(info != null)
					sb.append(info.toString()).append("\n\n");
			}
		}else{
			//総合
			RecordInfo info = getter.getRecordInfo();
			sb.append(info.toString()).append("\n\n");
			//直近100
			info = getter.getRecordInfo(100);
			sb.append(info.toString()).append("\n\n");
			//直近50
			info = getter.getRecordInfo(50);
			sb.append(info.toString()).append("\n\n");
			//直近30
			info = getter.getRecordInfo(30);
			sb.append(info.toString()).append("\n\n");
		}
		sb.append("#東方深秘録");//　#辺獄録　#HengokuTwi");
		
		System.out.println(sb.toString());
		System.out.println(sb.length());
		
		JobThread p = new JobThread(
			() -> {System.out.println("YEY");},
			3
		);
		p.start();
		p.join();
		/*
		if(tm.tweet(sb.toString()) != null)
			System.out.println("tweeted");*/
	}
}
