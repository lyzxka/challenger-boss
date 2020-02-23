package com.rancii.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.rancii.entity.User;
import com.xiaoleilu.hutool.codec.Base64;
import com.xiaoleilu.hutool.http.HttpUtil;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.json.JSONUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class ToolUtil {

	public static final Logger LOGGER = LoggerFactory.getLogger(ToolUtil.class);

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	public static void entryptPassword(User user) {
		byte[] salt = Digests.generateSalt(Constants.SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));
		byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(), salt, Constants.HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}

	/**
	 *
	 * @param paramStr 输入需要加密的字符串
	 * @return
	 */
	public static String entryptPassword(String paramStr,String salt) {
		if(StringUtils.isNotEmpty(paramStr)){
			byte[] saltStr = Encodes.decodeHex(salt);
			byte[] hashPassword = Digests.sha1(paramStr.getBytes(), saltStr, Constants.HASH_INTERATIONS);
			String password = Encodes.encodeHex(hashPassword);
			return password;
		}else{
			return null;
		}

	}

	/**
	 * 获取客户端的ip信息
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		LOGGER.info("ipadd : " + ip);
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		LOGGER.info(" ip --> " + ip);
		return ip;
	}
	
	/**
     * 将bean转换成map
     * @param condition
     * @return
     */
    @SuppressWarnings("unchecked")
	public static Map<String, Object> convertBeanToMap(Object condition) {
		if (condition == null) {
			return null;
		}
		if (condition instanceof Map) {
			return (Map<String, Object>) condition;
		}
		Map<String, Object> objectAsMap = new HashMap<String, Object>();
		BeanInfo info = null;
		try {
			info = Introspector.getBeanInfo(condition.getClass());
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}

		for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
			Method reader = pd.getReadMethod();
			if (reader != null&&!"class".equals(pd.getName()))
				try {
					objectAsMap.put(pd.getName(), reader.invoke(condition));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
		}
		return objectAsMap;
	}

	/**

	 * 通过文件名判断并获取OSS服务文件上传时文件的contentType

	 * @param fileName 文件名

	 * @return 文件的contentType

	 */
	public static  String getContentType(String fileName){
		int d = fileName.lastIndexOf(".");
		if( d== -1){
			return "text/html";
		}
		String fileExtension = fileName.substring(fileName.lastIndexOf("."));
		if(".bmp".equalsIgnoreCase(fileExtension)) return "image/bmp";
		if(".gif".equalsIgnoreCase(fileExtension)) return "image/gif";
		if(".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension)) return "image/jpeg";
		if(".png".equalsIgnoreCase(fileExtension)) return "image/png";
		if(".html".equalsIgnoreCase(fileExtension)) return "text/html";
		if(".txt".equalsIgnoreCase(fileExtension)) return "text/plain";
		if(".vsd".equalsIgnoreCase(fileExtension)) return "application/vnd.visio";
		if(".ppt".equalsIgnoreCase(fileExtension) || ".pptx".equalsIgnoreCase(fileExtension)) return "application/vnd.ms-powerpoint";
		if(".doc".equalsIgnoreCase(fileExtension) || ".docx".equalsIgnoreCase(fileExtension)) return "application/msword";
		if(".xml".equalsIgnoreCase(fileExtension)) return "text/xml";
		return "text/html";
	}

	/**
	 * 判断请求是否是ajax请求
	 * @param request
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest request){
		String accept = request.getHeader("accept");
        return accept != null && accept.contains("application/json") || (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").contains("XMLHttpRequest"));
    }

	/**
	 * 获取操作系统,浏览器及浏览器版本信息
	 * @param request
	 * @return
	 */
	public static Map<String,String> getOsAndBrowserInfo(HttpServletRequest request){
		Map<String,String> map = Maps.newHashMap();
		String  browserDetails  =   request.getHeader("User-Agent");
		String  userAgent       =   browserDetails;
		String  user            =   userAgent.toLowerCase();

		String os = "";
		String browser = "";

		//=================OS Info=======================
		if (userAgent.toLowerCase().contains("windows"))
		{
			os = "Windows";
		} else if(userAgent.toLowerCase().contains("mac"))
		{
			os = "Mac";
		} else if(userAgent.toLowerCase().contains("x11"))
		{
			os = "Unix";
		} else if(userAgent.toLowerCase().contains("android"))
		{
			os = "Android";
		} else if(userAgent.toLowerCase().contains("iphone"))
		{
			os = "IPhone";
		}else{
			os = "UnKnown, More-Info: "+userAgent;
		}
		//===============Browser===========================
		if (user.contains("edge"))
		{
			browser=(userAgent.substring(userAgent.indexOf("Edge")).split(" ")[0]).replace("/", "-");
		} else if (user.contains("msie"))
		{
			String substring=userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
			browser=substring.split(" ")[0].replace("MSIE", "IE")+"-"+substring.split(" ")[1];
		} else if (user.contains("safari") && user.contains("version"))
		{
			browser=(userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0]
					+ "-" +(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
		} else if ( user.contains("opr") || user.contains("opera"))
		{
			if(user.contains("opera")){
				browser=(userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0]
						+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
			}else if(user.contains("opr")){
				browser=((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-"))
						.replace("OPR", "Opera");
			}

		} else if (user.contains("chrome"))
		{
			browser=(userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
		} else if ((user.contains("mozilla/7.0")) || (user.contains("netscape6"))  ||
				(user.contains("mozilla/4.7")) || (user.contains("mozilla/4.78")) ||
				(user.contains("mozilla/4.08")) || (user.contains("mozilla/3")) )
		{
			browser = "Netscape-?";

		} else if (user.contains("firefox"))
		{
			browser=(userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
		} else if(user.contains("rv"))
		{
			String IEVersion = (userAgent.substring(userAgent.indexOf("rv")).split(" ")[0]).replace("rv:", "-");
			browser="IE" + IEVersion.substring(0,IEVersion.length() - 1);
		} else
		{
			browser = "UnKnown, More-Info: "+userAgent;
		}
		map.put("os",os);
		map.put("browser",browser);
		return map;
	}

	public static Map<String,String> getAddressByIP(String ip) {
		if("0:0:0:0:0:0:0:1".equals(ip)){
			ip = "0.0.0.0";
		}
		Map<String,String> map = Maps.newHashMap();
		StringBuilder sb = new StringBuilder("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=");
		sb.append(ip);
		String result= HttpUtil.get(sb.toString(), "UTF-8");
		LOGGER.info(result);
		Map resultMap = JSON.parseObject(result,Map.class);
		return resultMap;
	}

	public static void main(String args[]) throws Exception {
		long t1 = System.currentTimeMillis();
		Map<String,String> map = getAddressByIP("0.0.0.0");
		LOGGER.info("地区："+map.get("country"));
		LOGGER.info("省："+map.get("province"));
		LOGGER.info("市："+map.get("city"));
		LOGGER.info("互联网服务提供商："+map.get("isp"));
		long t2 = System.currentTimeMillis();
		System.out.println("执行时间为"+(t2-t1));

	}

	/**
	 * 生成订单号 (线程安全)
	 */
	static HashMap<String, AtomicLong> mapSequence = new HashMap<String, AtomicLong>();
	public static String priStrID() {
		AtomicLong mySeq = mapSequence.get("order");
		if (mySeq == null) {
			mySeq = new AtomicLong(100001);
			mapSequence.put("order", mySeq);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateFormat = sdf.format(new Date());
		String inc = mySeq.getAndIncrement() + "";
		int incLen = inc.length();
		inc = inc.substring(incLen - 3, incLen);
		String orderNo = dateFormat + inc;
		return orderNo;
	}
	/**
	 * url 转Map
	 * @param paramStr
	 * @return
	 */
	public static SortedMap<String, String> paramToMap(String paramStr) {
		String[] params = paramStr.split("&");
		SortedMap<String, String> resMap = new TreeMap<String,String>();
		for (int i = 0; i < params.length; i++) {
			String[] param = params[i].split("=");
			if (param.length >= 2) {
				String key = param[0];
				String value = param[1];
				for (int j = 2; j < param.length; j++) {
					value += "=" + param[j];
				}
				resMap.put(key, value);
			}
		}
		return resMap;
	}
	public static String mapToStringAndTrim(SortedMap<String, String> sortedMap){
		StringBuffer sb = new StringBuffer();
		Iterator it =	sortedMap.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String key = entry.getKey().toString().trim();
			if(entry.getValue()==null){
				continue;
			}
			String value = entry.getValue().toString().trim();
			if (!"".equals(value) && value!=null) {
				sb.append(key+"="+value+"&");
			}
		}
		return sb.substring(0,sb.length()-1);
	}
	/**
	 * 验证签名
	 * @param sortedMap 待签名的map
	 * @param sign 上游上送的sign签名
	 * @return
	 */
	public static boolean validateMd5(SortedMap<String, String> sortedMap,String sign,String secretKey){
		try {
			String str = mapToStringAndTrim(sortedMap)+"&key="+secretKey;
			LOGGER.info("----待加密的字符串-----"+str);
			String mac = Md5Utils.md5(str,"utf-8").toUpperCase();
			if(!sign.equals(mac)){
				LOGGER.info("验证签名失败！报文签名:"+sign+" 计算签名:"+mac);
			}
			if(!sign.equalsIgnoreCase(mac)){
				LOGGER.info("验证签名失败！报文签名:"+sign+" 计算签名:"+mac);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 获取时间
	 * @param data 20180926
	 * @param time 110957
	 * @return
	 */
	public static String getTimeStr(String data,String time){
		StringBuffer str = new StringBuffer();
		str.append(data.substring(0,4)+"-");
		str.append(data.substring(4,6)+"-");
		str.append(data.substring(6)+" ");
		if(StringUtils.isNotEmpty(time)){
			str.append(time.substring(0,2)+":");
			str.append(time.substring(2,4)+":");
			str.append(time.substring(4));
		}
		return str.toString();
	}

	/**
	 *
	 * @param request
	 * @return
	 */
	public static SortedMap<String,String> request2JsonMap(HttpServletRequest request){
		InputStream inputStream;
		String json = "";
		//获得响应流，获得输入对象
		try {
			inputStream = request.getInputStream();
			//建立接收流缓冲，准备处理
			StringBuffer requestBuffer = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
			//读入流，并转换成字符串
			String readLine;
			while ((readLine = reader.readLine()) != null) {
				requestBuffer.append(readLine);
			}
			reader.close();
			json = requestBuffer.toString();
		} catch (Exception e) {
			//logger.error("接收同步消息失败"+e);
		}
		try {
			LOGGER.info("接收到下游上送base64报文"+json);
			String s = Base64.decodeStr(json, "utf-8");
			LOGGER.info("解析报文原串："+s);
			JSONObject jsonObject = JSONUtil.parseObj(s);
			SortedMap<String,String> map = new TreeMap();
			for (Map.Entry<String, Object> stringObjectEntry : jsonObject.entrySet()) {
				map.put(stringObjectEntry.getKey(),stringObjectEntry.getValue().toString());
			}
			return map;
		} catch (Exception e) {
			LOGGER.error("解析下游上送报文异常", e);
		}
		return null;
	}

	/**
	 * 加签
	 * @param sortedMap 待签名的map
	 * @return
	 */
	public static String signMd5(SortedMap<String, String> sortedMap,String apiKey){
		try {
			String str = mapToStringAndTrim(sortedMap)+"&key="+apiKey;
			System.out.println("-加签字符串-"+str);
			String mac = Md5Utils.md5(str,"utf-8").toUpperCase();
			return mac;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
