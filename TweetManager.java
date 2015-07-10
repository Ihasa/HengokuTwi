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
	
	String filename = "./token.txt";
	Twitter twitter;
	private TweetManager(){
		twitter = TwitterFactory.getSingleton();
	}
	//Webアプリケーションでない場合はPINコードなるものを入力してもらう必要あり
	//WebアプリケーションならPINコードがプログラムに渡される
	public static void main(String[] args) throws Exception{
		TweetManager tm = TweetManager.getInstance();

		RequestToken rToken = TwitterFactory.getSingleton().getOAuthRequestToken();
		System.out.println(rToken.getAuthorizationURL());
		Scanner s = new Scanner(System.in);
		String pin = s.nextLine();
		if(tm.authorize(rToken, pin)){
			System.out.println("YEY!");
		}
		tm.tweet(args[0]);
	}

	public Status tweet(String text){
		try{
			AccessToken at = getAccessToken();
			twitter.setOAuthAccessToken(at);
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
        	/*if(401 == te.getStatusCode()){
          		System.out.println("Unable to get the access token.");
        	}else{
          		te.printStackTrace();
       		}*/
       		return false;
    	}
  	}
  	private boolean storeAccessToken(long useId, AccessToken accessToken){
		try{
			//ほんとはtwitter.propertyを書き換えに行く方が良い？
			File file = new File(filename);
    		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
    		//pw.println(useId);
    		pw.println(accessToken.getToken());
    		pw.println(accessToken.getTokenSecret());
    		
    		pw.close();
    	}catch(Exception e){
			return false;
		}
    	return true;
    	//accessToken.getToken() を保存
    	//accessToken.getTokenSecret() を保存
  	}
}