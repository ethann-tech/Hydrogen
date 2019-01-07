package com.wonium.aclj.ui.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ActivityH5Binding;
import com.wonium.cicada.android.BaseActivity;

/**
 * @ClassName: MainActivity
 * @Description: 描述一下
 * @Author: Ethan
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/12/11 21:50
 * @UpdateUser: update user
 * @UpdateDate: 2018/12/11 21:50
 * @UpdateDescription: 更新说明
 * @Version: 1.0.0
 */
public class H5Activity extends BaseActivity {
    private ActivityH5Binding mBinding;
    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_h5;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding =DataBindingUtil.setContentView(this,layoutResId);
    }

    @Override
    public void initView() {
        settingWebView();
    }

    @Override
    public void initListener() {

    }

    private  void settingWebView(){
        WebSettings webSettings = mBinding.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        // 设置支持缩放
        webSettings.setSupportZoom(true);
        //显示图片时自适应屏幕大小,但是4.4以前好用,4,4以后不好用
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //weView中有链接,在当前 browser 中相应
        mBinding.webView.setWebViewClient(new MyWebViewClient());
        //设置进度条,处理提示框
        mBinding.webView.setWebChromeClient(new MyWebChromeClient());
        //添加监听
        mBinding.webView.addJavascriptInterface( new JavaScriptInterface(this), "android");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            mBinding.webView.evaluateJavascript("getImgName", new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String name) {
                    Log.d("onReceiveValue",name);
                }
            });
        }
        mBinding.webView.loadUrl("file:///android_asset/testh5.html");


        findViewById(R.id.btnJs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.webView.loadUrl("javascript:javacalljs()");
            }
        });

        findViewById(R.id.btnJs2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.webView.loadUrl("javascript:javacalljswith('btnJs2')");
            }
        });
    }


  private   class  MyWebViewClient extends WebViewClient{
      public MyWebViewClient() {
          super();
      }
      @Override
      public void onPageFinished(WebView view, String url) {
          super.onPageFinished(view, url);
//          progressBar.setVisibility(View.GONE);
      }

      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
          view.loadUrl(url);
          return true;
      }
  }

    /**
     * 设置进度条和提示框
     */
    private class MyWebChromeClient extends WebChromeClient {
        /**
         *该方法中可以设置进度条
         */

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        /**
         * 该方法中可以处理提示框
         * @param view
         * @param url
         * @param message
         * @param result
         * @return
         */
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }
    }

    private class JavaScriptInterface {

        private Context context;
        JavaScriptInterface(Context context) {
            this.context = context;
        }

        /**
         * 点击图片回调方法
         * 必须添加注解,否则无法响应
         */

        @JavascriptInterface
        public void onClickImg() {

            ((Activity)context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context,"调用java方法",Toast.LENGTH_SHORT).show();


                }
            });
        }
        @JavascriptInterface
        public void onClickImg(final String name){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context,"调用Java方法并且有参数"+name,Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
