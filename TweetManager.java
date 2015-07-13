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
	//Webアプリケーションでない場合はPINコードなるものを入力してもらう必要あり
	//WebアプリケーションならPINコードがプログラムに渡される
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
	
	//アクセストークン取得・保存
	public boolean authorize(RequestToken requestToken, String pin){
		//twitter.setOAuthConsumer(...);
    	//リクエストトークン取得して、渡されたPINコードでアクセストークン取りに行く
    	try{
    		AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, pin);
      	   	if(accessToken == null) return false;
    		//将来の参照用に accessToken を永続化する
    		return storeAccessToken(twitter.verifyCredentials().getId() , accessToken);
      	} catch (TwitterException te) {
       		return false;
    	}
  	}
  	private boolean storeAccessToken(long useId, AccessToken accessToken){
		try{
			StringBuilder sb = new StringBuilder();
			File file = new File(filename);
    		//ファイルを全部読み込んで移す
			Scanner s = new Scanner(file);
			String oauthAt = "oauth.accessToken";
			String oauthAts = "oauth.accessTokenSecret";
			while(s.hasNextLine()){
				String str = s.nextLine();
				//oauth.accessToken=...となっている行は消す
				if(!str.contains(oauthAts) && !str.contains(oauthAt)){
					sb.append(str).append('\n');
				}
			}
			s.close();
			//新しいアクセストークンを追記
			sb.append(oauthAt + "=" + accessToken.getToken()).append('\n');
			sb.append(oauthAts + "=" + accessToken.getTokenSecret());
    		//書きだす
    		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			pw.println(sb.toString());
			pw.close();
    	}catch(Exception e){
			return false;
		}
    	return true;
    	//accessToken.getToken() を保存
    	//accessToken.getTokenSecret() を保存
  	}
}