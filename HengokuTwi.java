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

		//�c�C�[�g���e�̌���(�O���t�@�C������t�B���^�����O�ݒ蓙�ǂݍ���)
		//Default.db�ւ̃p�X���ꉞ�ǂݍ��ނ��Ƃɂ���
		List<RecordFilter> filters = getFiltersFromFile();

		StringBuilder sb = new StringBuilder();
		sb.append("�����Ȃ��̓����[��^��с�").append("\n\n");
		RecordGetter getter = RecordGetter.createInstance("Default.db");
		//�t�B���^�[�Ɋ�Â��ăc�C�[�g
		if(filters.size() > 0){
			for(RecordFilter f : filters){
				RecordInfo info = getter.getRecordInfo(f);
				sb.append(info.toString()).append("\n\n");
			}
		}else{
			//����
			RecordInfo info = getter.getRecordInfo();
			sb.append(info.toString()).append("\n\n");
			//����100
			info = getter.getRecordInfo(100);
			sb.append(info.toString()).append("\n\n");
			//����50
			info = getter.getRecordInfo(50);
			sb.append(info.toString()).append("\n\n");
			//����30
			info = getter.getRecordInfo(30);
			sb.append(info.toString()).append("\n\n");
		}
		sb.append("#�����[��^");//�@#�Ӎ��^�@#HengokuTwi");
		
		System.out.println(sb.toString());
		System.out.println(sb.length());
		/*
		if(tm.tweet(sb.toString()) != null)
			System.out.println("tweeted");*/
	}
	private static List<RecordFilter> getFiltersFromFile(){
		List<RecordFilter> res = new ArrayList<RecordFilter>();
		try{
			Scanner s = new Scanner(new File("config.txt"));
			while(s.hasNextLine()){
				try{
					String line = s.nextLine();
					res.add(createFilter(line));
				}catch(Exception e){}
			}
			s.close();
		}catch(Exception e){}
		return res;
	}
	private static RecordFilter createFilter(String str) throws Exception{
		String fmted = str.replaceAll("\\s","");
		String[] words = fmted.split(",");
		int count = -1;
		Character c1 = Character.UNSPECIFIED;
		Character c2 = Character.UNSPECIFIED;
		
		if(!words[0].equals("_"))
			count = Integer.parseInt(words[0]);
		if(!words[1].equals("_"))
			c1 = Character.valueOf(words[1]);
		if(!words[2].equals("_"))
			c2 = Character.valueOf(words[2]);
		return new RecordFilter(count, c1, c2);
	}
}
