import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;

import java.util.Scanner;
/**
 * Example application that uses OAuth method to acquire access to your account.<br>
 * This application illustrates how to use OAuth method with Twitter4J.<br>
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public class TweetManager {
	private static TweetManager instance;
	public static TweetManager getInstance(){
		if(instance == null)
			instance = new TweetManager();
		return instance;
	}
	
	String filename = "./twitter4j.properties";
	Twitter twitter;
	private TweetManager(){
		twitter = TwitterFactory.getSingleton();
	}
	//Web�A�v���P�[�V�����łȂ��ꍇ��PIN�R�[�h�Ȃ���̂���͂��Ă��炤�K�v����
	//Web�A�v���P�[�V�����Ȃ�PIN�R�[�h���v���O�����ɓn�����
	public static void main(String[] args) throws Exception{
		TweetManager tm = TweetManager.getInstance();

		/*RequestToken rToken = TwitterFactory.getSingleton().getOAuthRequestToken();
		System.out.println(rToken.getAuthorizationURL());
		Scanner s = new Scanner(System.in);
		String pin = s.nextLine();
		if(tm.authorize(rToken, pin)){
			System.out.println("YEY!");
		}*/
		tm.tweet(args[0]);
	}

	public Status tweet(String text){
		try{
			return twitter.updateStatus(text);
		}catch(Exception e){
			return null;
		}
	}
	
	private AccessToken getAccessToken(){
		String token;
		String tokenSecret;
		try{
			Scanner s = new Scanner(new File(filename));
			token = s.nextLine();
			tokenSecret = s.nextLine();
			s.close();
		}catch(Exception e){
			return null;
		}
		return new AccessToken(token, tokenSecret);
	}
	
	//�A�N�Z�X�g�[�N���擾�E�ۑ�
	public boolean authorize(RequestToken requestToken, String pin){
		//twitter.setOAuthConsumer(...);
    	//���N�G�X�g�g�[�N���擾���āA�n���ꂽPIN�R�[�h�ŃA�N�Z�X�g�[�N�����ɍs��
    	try{
    		AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, pin);
      	   	if(accessToken == null) return false;
    		//�����̎Q�Ɨp�� accessToken ���i��������
    		return storeAccessToken(twitter.verifyCredentials().getId() , accessToken);
      	} catch (TwitterException te) {
       		return false;
    	}
  	}
  	private boolean storeAccessToken(long useId, AccessToken accessToken){
		try{
			StringBuilder sb = new StringBuilder();
			File file = new File(filename);
    		//�t�@�C����S���ǂݍ���ňڂ�
			Scanner s = new Scanner(file);
			String oauthAt = "oauth.accessToken";
			String oauthAts = "oauth.accessTokenSecret";
			while(s.hasNextLine()){
				String str = s.nextLine();
				//oauth.accessToken=...�ƂȂ��Ă���s�͏���
				if(!str.contains(oauthAts) && !str.contains(oauthAt)){
					sb.append(str).append('\n');
				}
			}
			s.close();
			//�V�����A�N�Z�X�g�[�N����ǋL
			sb.append(oauthAt + "=" + accessToken.getToken()).append('\n');
			sb.append(oauthAts + "=" + accessToken.getTokenSecret());
    		//��������
    		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			pw.println(sb.toString());
			pw.close();
    	}catch(Exception e){
			return false;
		}
    	return true;
    	//accessToken.getToken() ��ۑ�
    	//accessToken.getTokenSecret() ��ۑ�
  	}
}