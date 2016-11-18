package com.guoyizeng.projecttemplate.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;


public class H5PayDemoActivity extends Activity {

	private WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = null;
		try {
			extras = getIntent().getExtras();
		} catch (Exception e) {
			finish();
			return;
		}
		if (extras == null) {
			finish();
			return;
		}
		String url = null;
		try {
			url = extras.getString("url");
		} catch (Exception e) {
			finish();
			return;
		}
		if (TextUtils.isEmpty(url)) {
			// 测试H5支付，必须设置要打开的url网站
			new AlertDialog.Builder(H5PayDemoActivity.this).setTitle("警告")
					.setMessage("必须配置需要打开的url 站点，请在PayDemoActivity类的h5Pay中配置")
					.setPositiveButton("确定", new OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							finish();
						}
					}).show();

		}
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		LinearLayout layout = new LinearLayout(getApplicationContext());
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout, params);

		mWebView = new WebView(getApplicationContext());
		params.weight = 1;
		mWebView.setVisibility(View.VISIBLE);
		layout.addView(mWebView, params);

		WebSettings settings = mWebView.getSettings();
		settings.setRenderPriority(RenderPriority.HIGH);
		settings.setSupportMultipleWindows(true);
		settings.setJavaScriptEnabled(true);
		settings.setSavePassword(false);
		settings.setJavaScriptCanOpenWindowsAutomatically(true);
		settings.setMinimumFontSize(settings.getMinimumFontSize() + 8);
		settings.setAllowFileAccess(false);
		settings.setTextSize(WebSettings.TextSize.NORMAL);
		mWebView.setVerticalScrollbarOverlay(true);
		mWebView.setWebViewClient(new MyWebViewClient());
		mWebView.loadUrl(url);
	}

	@Override
	public void onBackPressed() {
		if (mWebView.canGoBack()) {
			mWebView.goBack();
		} else {
			finish();
		}
	}


	private class MyWebViewClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(final WebView view, String url) {
			if (!(url.startsWith("http") || url.startsWith("https"))) {
				return true;
			}

			AlipayUtil.h5PayResult(H5PayDemoActivity.this,url,mWebView);
			return true;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mWebView != null) {
			mWebView.removeAllViews();
			try {
				mWebView.destroy();
			} catch (Throwable t) {
			}
			mWebView = null;
		}
	}
}
