import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HengokuTwi {
    public static void main(String[] args) throws ClassNotFoundException {
		RecordGetter getter = RecordGetter.createInstance("Default.db");
		RecordInfo info = getter.getRecordInfo();
		System.out.println("��������:" + info.getRecordCount());
		System.out.println("����:" + info.getWins());
		System.out.println("����:" + info.getWinningPercentage());

		//filter�̃e�X�g
		RecordFilter filter = new RecordFilter(30, 5);
		info = getter.getRecordInfo(filter);
		System.out.println("��������:" + info.getRecordCount());
		System.out.println("����:" + info.getWins());
		System.out.println("����:" + info.getWinningPercentage());
	}
}
