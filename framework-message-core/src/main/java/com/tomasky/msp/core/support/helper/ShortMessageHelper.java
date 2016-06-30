package com.tomasky.msp.core.support.helper;

import com.tomasky.msp.core.support.MessageSendResult;
import com.tomasky.msp.enumeration.SmsChannel;
import com.tomasky.msp.support.HttpClientUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.crypto.RuntimeCryptoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShortMessageHelper
{
  private static final Logger logger = LoggerFactory.getLogger(ShortMessageHelper.class);

  private static final char[] mChars = "0123456789ABCDEF".toCharArray();
  private static final String SUCCESS = "ACCEPTD";
  private static final Set<String> sensitiveWords = new HashSet<String>();
  private static final String SINGLE_COMMAND = "MT_REQUEST";
  private static final String MULT_COMMAND = "MULTI_MT_REQUEST";
  private String url;
  private String saleSpid;
  private String salePwd;
  private String autoSpid;
  private String autoPwd;
  private String vipSpid;
  private String vipPwd;

  public MessageSendResult sendShortMessage(List<String> receivers, String content, SmsChannel channel)
  {
    content = clearContent(content);
    Map<String, Object> params = buildParams(receivers, content, channel);
    String result = HttpClientUtils.httpPost(this.url, params);
    logger.info("result for sending sms :" + result);
    Map<String, String> responseMap = analyzeResponse(result);
    MessageSendResult messageSendResult = null;
    if (responseMap != null) {
      messageSendResult = new MessageSendResult();
      if (SUCCESS.equals(responseMap.get("mtstat"))) {
        logger.info(new StringBuilder().append("send sms total:").append(receivers.size()).toString());
        messageSendResult.setStatus(true);
      } else {
    	  logger.info( "sms 发送失败:"+ content);
        messageSendResult.setStatus(false);
      }
      messageSendResult.setCode(new StringBuilder().append((String)responseMap.get("mterrcode")).append("_").append((String)responseMap.get("mtstat")).toString());
    }
    return messageSendResult;
  }

  private Map<String, Object> buildParams(List<String> receivers, String content, SmsChannel channel)
  {
    Map<String,Object> params = new HashMap<String,Object>();
    switch (channel) {
    case SEND_TYPE_AUTO:
      params.put("spid", this.autoSpid);
      params.put("sppassword", this.autoPwd);
      break;
    case SEND_TYPE_SALE:
      params.put("spid", this.saleSpid);
      params.put("sppassword", this.salePwd);
      break;
    case SEND_TYPE_VIP:
      params.put("spid", this.vipSpid);
      params.put("sppassword", this.vipPwd);
      break;
    default:
      throw new RuntimeException("不能识别的短信通道类型");
    }

    if (receivers.size() > 1) {
      params.put("command", MULT_COMMAND);
      params.put("das", spellReceivers(receivers));
    } else {
      params.put("command", SINGLE_COMMAND);
      params.put("da", spellReceivers(receivers));
    }
    params.put("dc", "15");
    try {
      params.put("sm", str2HexStr(content));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      throw new RuntimeCryptoException(e.getMessage());
    }
    return params;
  }

  private String spellReceivers(List<String> receivers)
  {
    String receiverStr = null;
    if (!CollectionUtils.isEmpty(receivers)) {
      StringBuilder stringBuilder = new StringBuilder();
      for (String receiver : receivers) {
        stringBuilder.append("86");
        stringBuilder.append(receiver);
        stringBuilder.append(",");
      }
      receiverStr = stringBuilder.toString();
      receiverStr = receiverStr.substring(0, receiverStr.length() - 1);
    }
    return receiverStr;
  }

  private Map<String, String> analyzeResponse(String responseStr)
  {
    Map<String,String> responseMap = null;
    if (StringUtils.isNotBlank(responseStr)) {
      responseMap = new HashMap<String,String>();
      String[] result = responseStr.split("&");
      for (String str : result) {
        String[] param = str.split("=");
        responseMap.put(param[0], param[1]);
      }
    }
    return responseMap;
  }

  private String str2HexStr(String str)
    throws UnsupportedEncodingException
  {
    StringBuilder sb = new StringBuilder();
    byte[] bs = str.getBytes("GBK");

    for (int i = 0; i < bs.length; i++) {
      sb.append(mChars[((bs[i] & 0xFF) >> 4)]);
      sb.append(mChars[(bs[i] & 0xF)]);
    }
    return sb.toString().trim();
  }

  private String clearContent(String content)
  {
    for (String limit : sensitiveWords)
      try {
        content = content.replace(limit, "***");
      }
      catch (Exception e)
      {
      }
    return content.trim();
  }

  private static void loadSensitiveWords()
  {
    BufferedReader bufferedReader = null;
    try {
      bufferedReader = new BufferedReader(new InputStreamReader(ShortMessageHelper.class.getResourceAsStream("/sensitivewords.txt"), "utf-8"));
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        line = line.trim();
        sensitiveWords.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        bufferedReader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getSaleSpid() {
    return this.saleSpid;
  }

  public void setSaleSpid(String saleSpid) {
    this.saleSpid = saleSpid;
  }

  public String getSalePwd() {
    return this.salePwd;
  }

  public void setSalePwd(String salePwd) {
    this.salePwd = salePwd;
  }

  public String getAutoSpid() {
    return this.autoSpid;
  }

  public void setAutoSpid(String autoSpid) {
    this.autoSpid = autoSpid;
  }

  public String getAutoPwd() {
    return this.autoPwd;
  }

  public void setAutoPwd(String autoPwd) {
    this.autoPwd = autoPwd;
  }

  public String getVipSpid() {
    return this.vipSpid;
  }

  public void setVipSpid(String vipSpid) {
    this.vipSpid = vipSpid;
  }

  public String getVipPwd() {
    return this.vipPwd;
  }

  public void setVipPwd(String vipPwd) {
    this.vipPwd = vipPwd;
  }

  static
  {
    loadSensitiveWords();
  }
}