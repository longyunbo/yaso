package com.drag.yaso.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WxUtil {
	
	
	private static String requestMethod;
	private static String appid;
	private static String secret;
	
	@Value("${weixin.url.requestMethod}")
    public void setRequestMethod(String value) {
		requestMethod = value;
    }
	@Value("${weixin.url.appid}")
    public void setAppid(String value) {
		appid = value;
    }
	@Value("${weixin.url.secret}")
    public void setSecret(String value) {
		secret = value;
    }
	
	/**
	 * 调用微信接口获取openid
	 * @param code
	 * @return
	 */
	public static String returnOpenid(String code) {
		String openid = "";
		try {
			String requestUrl = String.format("https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code", appid , secret, code);
			openid = HttpsUtil.httpsRequest(requestUrl, requestMethod, null);
		} catch (Exception e) {
			log.error("获取openid异常{}",e);
		}
		return openid;
	}
	
	 /**
     * 获取accessToken
     * @return
     */
    public static String getAccessToken(){
    	String requestUrl = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", appid , secret);
        JSONObject resultJson =null;
        String result = HttpsUtil.httpsRequest(requestUrl, requestMethod, null);
         try {
             resultJson = JSON.parseObject(result);
             String errmsg = (String) resultJson.get("errmsg");
             if(!"".equals(errmsg) && errmsg != null){
                 log.error("获取access_token失败："+errmsg);
                 return "error";
             }
         } catch (JSONException e) {
             e.printStackTrace();
         }
         return (String) resultJson.get("access_token");
    }
    
    /**
     * 获取模板
     * @return
     */
    public static List getTemplate(){
    	String token = getAccessToken();
    	String requestUrl = String.format("https://api.weixin.qq.com/cgi-bin/wxopen/template/library/list?access_token=%s", token);
        JSONObject resultJson =null;
        JSONObject json = new JSONObject();
		json.put("offset", "0");
		json.put("count", "10");
//        String result = HttpsUtil.httpsRequest(requestUrl, "POST", null);
        String result = HttpsUtil.doPost(requestUrl, json.toString(), "utf-8");
         try {
             resultJson = JSON.parseObject(result);
         } catch (JSONException e) {
             e.printStackTrace();
         }
         return (List) resultJson.get("list");
    }
    
    /**
     * 发送小程序
     * @param token
     * @param template
     * @return
     */
	public static boolean sendTemplateMsg(JSONObject json) {
		boolean flag = false;
		String token = getAccessToken();
		String requestUrl = String.format("https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=%s", token);

		String result = HttpsUtil.doPost(requestUrl, json.toString(), "utf-8");
		if (result != null) {
			JSONObject jsonResult = JSON.parseObject(result);
			int errorCode = jsonResult.getInteger("errcode");
			String errorMessage = jsonResult.getString("errmsg");
			if (errorCode == 0) {
				flag = true;
			} else {
				log.error("模板消息发送失败:" + errorCode + "," + errorMessage + "," + json.toJSONString());
				flag = false;
			}
		}
		return flag;
	}
}
