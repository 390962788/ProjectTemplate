package com.guoyizeng.alipay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.util.H5PayResultModel;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Created by Administrator on 2016/10/28.
 */

public class AlipayUtil {

    // 商户PID
    public static final String PARTNER = "2016102802377649";
    // 商户收款账号
    public static final String SELLER = "390962788@qq.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAN/T+/NyU0jzbzNR\n" +
                                            "YEwqd252EZnyKkNvQ6uJ0XKBrknUE3QINZ4KwxMs8s9eQ/e+zHfL27YdTmns+MkU\n" +
                                            "I98uCVx/ZAPAvELyihvBroHOhIoFq8SMSJoB7Dj9jgpCPTykXtJepMkfS/XLHJYH\n" +
                                            "0Hj3piGXn+/7jinpmG4WThnhonrLAgMBAAECgYAH6AoPWGt+uTKlYmt/ZUSgv0Tx\n" +
                                            "5i7+ANEoHw8p91C7xOUHBT83nPO1GrJdBtxpxPCeDrJtJW3eqgNDvxVhTdQAwog+\n" +
                                            "PEW4Hp0devQm4vhpTo9wXJPJP50uuaUCkp0nPrD7nGw3uYuYlWS2MKoSNFsABkPL\n" +
                                            "zXDvXsddcebRiHgvqQJBAPYjb/logdEQhQmu00f2roN3uxw4buHtguRXZHOjck3k\n" +
                                            "E4Bziff2Ay1FHzY2DxjJRhyUY642K7gQaDLHQUHEWOUCQQDoy7dwoo6OtIToQPGG\n" +
                                            "hWwnjZ4rmIlofWbwqKKH9+mKGLxHmNjHpVUJaArZGAzu/DiP9xUthlkf3ggueRX3\n" +
                                            "XrnvAkBEV8CemtL4zsJx1tYZdDgujs8oXc8D8mPWrSFaLnbtg7eagfo8HFXzeu7Y\n" +
                                            "qZf1hOVvyBAJbkcjW4LjHopTDYa1AkEAyF1mgrx3BJ6c8iXLe+PkZdq/M3izc8rT\n" +
                                            "SflrAqo/SMvnaZxFoPhNS65P//ByWVOe/1JD2Q4UMZhx5EX1wRa34wJBANk7Ffwd\n" +
                                            "MEaeir7O08P/AnYblNBxPuKc4o6yUscPH/FtSB5WnrMGTqc9ouxiI+1BBo+gKcY9\n" +
                                            "+qtGjSOPC8Rwljg=";
    // 支付宝公钥
    public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDf0/vzclNI828zUWBMKndudhGZ\n" +
            "8ipDb0OridFyga5J1BN0CDWeCsMTLPLPXkP3vsx3y9u2HU5p7PjJFCPfLglcf2QD\n" +
            "wLxC8oobwa6BzoSKBavEjEiaAew4/Y4KQj08pF7SXqTJH0v1yxyWB9B496Yhl5/v\n" +
            "+44p6ZhuFk4Z4aJ6ywIDAQAB";
    private static final int SDK_PAY_FLAG = 1;

    private Activity mContext;
    public AlipayUtil(Context mContext) {
        this.mContext = (Activity) mContext;
    }

    /**
     * call alipay sdk pay. 调用SDK支付
     *
     */
    public void pay() {
        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
            new AlertDialog.Builder(mContext).setTitle("警告").setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            mContext. finish();
                        }
                    }).show();
            return;
        }
        String orderInfo = getOrderInfo("测试的商品", "该测试商品的详细描述", "0.01");

        /**
         * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
         */
        String sign = sign(orderInfo);
        try {
            /**
             * 仅需对sign 做URL编码
             */
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(mContext);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * get the sdk version. 获取SDK版本号
     *
     */
    public void getSDKVersion(Activity context) {
        PayTask payTask = new PayTask(context);
        String version = payTask.getVersion();
        Toast.makeText(context, version, Toast.LENGTH_SHORT).show();
    }

    /**
     * 原生的H5（手机网页版支付切natvie支付） 【对应页面网页支付按钮】
     *
     */
    public void h5Pay(Class toActivity) {
        Intent intent = new Intent(mContext, toActivity);
        Bundle extras = new Bundle();
        /**
         * url是测试的网站，在app内部打开页面是基于webview打开的，demo中的webview是H5PayDemoActivity，
         * demo中拦截url进行支付的逻辑是在H5PayDemoActivity中shouldOverrideUrlLoading方法实现，
         * 商户可以根据自己的需求来实现
         */
        String url = "http://m.taobao.com";
        // url可以是一号店或者淘宝等第三方的购物wap站点，在该网站的支付过程中，支付宝sdk完成拦截支付
        extras.putString("url", url);
        intent.putExtras(extras);
        mContext.startActivity(intent);
    }

    /**
     * 原生的H5（手机网页版支付切natvie支付） 【对应页面网页支付按钮】
     *
     */
    public static void h5PayResult(final Activity context, String url, final WebView view) {

        final PayTask task = new PayTask(context);
        final String ex = task.fetchOrderInfoFromH5PayUrl(url);
        if (!TextUtils.isEmpty(ex)) {
            System.out.println("paytask:::::" + url);
            new Thread(new Runnable() {
                public void run() {
                    System.out.println("payTask:::" + ex);
                    final H5PayResultModel result = task.h5Pay(ex, true);
                    if (!TextUtils.isEmpty(result.getReturnUrl())) {
                        context.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                view.loadUrl(result.getReturnUrl());
                            }
                        });
                    }
                }
            }).start();
        } else {
            view.loadUrl(url);
        }
    }

    /**
     * create the order info. 创建订单信息
     *
     */
    private String getOrderInfo(String subject, String body, String price) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        return orderInfo;
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     *
     */
    private String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content
     *            待签名订单信息
     */
    private String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     *
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }


    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(mContext, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };
}
