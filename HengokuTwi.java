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
		List<RecordFilter> filters = new ArrayList<RecordFilter>();
		Scanner s = new Scanner(new File("config.txt"));
		while(s.hasNextLine()){
			String line = s.nextLine();
			filters.add(createFilter(line));
		}
		s.close();

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
			//���ʂő�����т͏o���B���ƃ��C���L������
			RecordInfo info = getter.getRecordInfo();
			sb.append(info.toString()).append("\n\n");
			//����50
			info = getter.getRecordInfo(50);
			sb.append(info.toString()).append("\n\n");
			//����30
			info = getter.getRecordInfo(30);
			sb.append(info.toString()).append("\n\n");
			//vs�_�q��ł̑������
			info = getter.getRecordInfo(10, Character.UNSPECIFIED, Character.MIKO);
			sb.append(info.toString()).append("\n\n");
			//����40���vs��֐�̐��
			info = getter.getRecordInfo(40, Character.UNSPECIFIED, Character.ICHIRIN);
			sb.append(info.toString()).append("\n\n");
			//�얲�g�p����vs��������̐��(�G���[)
			info = getter.getRecordInfo(Character.REIMU, Character.MARISA);
			if(info != null)
				sb.append(info.toString()).append("\n\n");
		}
		sb.append("#�����[��^");//�@#�Ӎ��^�@#HengokuTwi");
		
		System.out.println(sb.toString());
		System.out.println(sb.length());
		/*
		if(tm.tweet(sb.toString()) != null)
			System.out.println("tweeted");*/
		//�J�X�^���Őݒ�t�@�C���ɂ���ċK�肳�����e���c�C�[�g����
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
