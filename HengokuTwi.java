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
		
		//�c�C�[�g���e�̌���(�O���t�@�C������t�B���^�����O�ݒ蓙�ǂݍ���)
		//Default.db�ւ̃p�X���ꉞ�ǂݍ��ނ��Ƃɂ���
		//Scanner s = new Scanner("config.txt");
		//s.close();
		
		StringBuilder sb = new StringBuilder();
		RecordGetter getter = RecordGetter.createInstance("Default.db");
		//���ʂő�����т͏o���B���ƃ��C���L������
		RecordInfo info = getter.getRecordInfo();
		sb.append("�����Ȃ��̓����[��^��с�").append('\n');
		//�ȉ��͐ݒ�t�@�C���ŋK�肷�遫
		//foreach...
		
		//����
		sb.append(info.toString()).append('\n');
		//����50
		info = getter.getRecordInfo(new RecordFilter(50));
		sb.append(info.toString()).append('\n');		
		//����30
		info = getter.getRecordInfo(new RecordFilter(30));
		sb.append(info.toString()).append('\n');
		//��������ł̐��
		info = getter.getRecordInfo(new RecordFilter(Character.UNSPECIFIED, Character.MARISA));
		sb.append(info.toString()).append('\n');
		//����40��̑΂�������̐��
		info = getter.getRecordInfo(new RecordFilter(40, Character.UNSPECIFIED, Character.KOISHI));
		sb.append(info.toString()).append('\n');
		sb.append("#�����[��^");
		
		System.out.println(sb.toString());
		System.out.println(sb.length());
		//tm.tweet(sb.toString());
		//�J�X�^���Őݒ�t�@�C���ɂ���ċK�肳�����e���c�C�[�g����
	}
}
