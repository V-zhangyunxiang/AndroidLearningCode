package com.zyx.sharesdkdemo;

import java.util.HashMap;

import com.mob.tools.utils.UIHandler;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SMSSDK.initSDK(this, "1b2ef3b074750", "f6e5e3ee4c91c37e6dc9b2b399a18273");
//		ShareSDK.initSDK(this);
	}
    public void MessageSDKClick(View view){
    	//打开注册页面
    	RegisterPage registerPage = new RegisterPage();
    	registerPage.setRegisterCallback(new EventHandler() {
    	public void afterEvent(int event, int result, Object data) {
    	// 解析注册结果
    	if (result == SMSSDK.RESULT_COMPLETE) {
    	@SuppressWarnings("unchecked")
    	HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
    	String country = (String) phoneMap.get("country");
    	String phone = (String) phoneMap.get("phone"); 
    	// 提交用户信息（此方法可以不调用）
    	registerUser(country, phone);
    	}
    	}
    	});
    	registerPage.show(this);
    	
    }
    public void registerUser(String country,String phone){
    	System.out.println(country+phone);
    }
	public void shareSDKClick(View view) {
		showShare();
	}

	public void shareLoginClick(View view) {
		Platform plat = ShareSDK.getPlatform(this, QQ.NAME);
		if (plat.isAuthValid()) {
			plat.removeAccount(true);
			}
		plat.setPlatformActionListener(new PlatformActionListener() {

			@Override
			public void onError(Platform arg0, int arg1, Throwable arg2) {
			}

			@Override
			public void onComplete(Platform platform, int action,
					HashMap<String, Object> arg2) {
				if (action == Platform.ACTION_USER_INFOR) {
					PlatformDb platDB = platform.getDb();// 获取数平台数据DB
					// 通过DB获取各种数据
					platDB.getToken();
					platDB.getUserGender();
					platDB.getUserIcon();
					platDB.getUserId();
					platDB.getUserName();
					System.out.println(platDB.getToken()+"-"+platDB.getUserGender()
					+"-"+	platDB.getUserId()+"-"+	platDB.getUserName()+"-"+platDB.getUserIcon());
				}
			}

			@Override
			public void onCancel(Platform arg0, int arg1) {
			}
		});
		// 判断指定平台是否已经完成授权
		// if(plat.isAuthValid()) {
		// String userId = plat.getDb().getUserId();
		// if (userId != null) {
		// // UIHandler.sendEmptyMessage(MSG_USERID_FOUND, this);
		// // login(plat.getName(), userId, null);
		// return;
		// }
		// }
		// true不使用SSO授权，false使用SSO授权
		plat.SSOSetting(true);
		// 获取用户资料
		plat.showUser(null);
		// 只要功能,不要数据
		// plat.authorize();
	}

	private void showShare() {
		OnekeyShare oks = new OnekeyShare();
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();
		// title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
		oks.setTitle("标题");
		// titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
		oks.setTitleUrl("http://sharesdk.cn");
		// text是分享文本，所有平台都需要这个字段
		oks.setText("我是分享文本");
		// 分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
		oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		// oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
		
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl("http://sharesdk.cn");
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment("我是测试评论文本");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite("ShareSDK");
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl("http://sharesdk.cn");

		// 启动分享GUI
		oks.show(this);
	}

}
