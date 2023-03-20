package com.hig.component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hig.dto.ResultPackage;

@Component
public class HttpAPIService {
	private static final Gson gson = new Gson();

	@Autowired
	private CloseableHttpClient httpClient;

	@Autowired
	private RequestConfig config;

	/**
	 * 不带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
	 *
	 * @param url
	 * @return
	 * @throws Exception
	 */
	// get方法
	public String doGet(String url) throws Exception {
		Map<String, Object> map = new HashMap<>();

		
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000)
				.setConnectionRequestTimeout(30000)
				.setSocketTimeout(30000)
				.setRedirectsEnabled(true)
				.build();
		
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(requestConfig);

		String result = null;
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(httpResponse.getEntity());

			} else if (httpResponse.getStatusLine().getStatusCode() == 404) {
				throw new Exception("404错误，请求的资源不存在");
			} else if (httpResponse.getStatusLine().getStatusCode() == 500) {
				throw new Exception("500错误,服务器内部错误");
			} else
				throw new Exception("Http请求出错,返回Http状态码:" + httpResponse.getStatusLine().getStatusCode());
		} catch (Exception e) {
			throw new Exception("处理httpGet操作出错,\r\n出错信息:" + e.getMessage());
		}

		return result;
	}

	// get方法
	public Map<String, Object> doGetReturnMap(String url) throws Exception {
		Map<String, Object> map = new HashMap<>();


		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000)
				.setConnectionRequestTimeout(30000)
				.setSocketTimeout(30000)
				.setRedirectsEnabled(true)
				.build();
		
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(requestConfig);
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			// 这个地方调用的时侯有可能会出错，要做出错处理
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				String jsonResult = EntityUtils.toString(httpResponse.getEntity());
				
				map = GsonUtil.fromJson(jsonResult,
		                new TypeToken<Map<String, Object>>() {
		                });
			} else if (httpResponse.getStatusLine().getStatusCode() == 404) {
				throw new Exception("404错误，请求的资源不存在");
			} else if (httpResponse.getStatusLine().getStatusCode() == 500) {
				throw new Exception("500错误,服务器内部错误");
			} else
				throw new Exception("Http请求出错,返回Http状态码:" + httpResponse.getStatusLine().getStatusCode());

		} catch (Exception e) {
			throw new Exception("处理httpGet操作出错,\r\n出错信息:" + e.getMessage());
		}
		return map;
	}

	// post方式
	public Map<String, Object> doPost(String url, Map<String, Object> map) throws Exception {
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000)
				.setConnectionRequestTimeout(30000)
				.setSocketTimeout(30000)
				.setRedirectsEnabled(true)
				.build();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		httpPost.addHeader("Content-Type", "application/json; chartset=UTF-8");
		if (map != null && map.size() > 0) {
			StringEntity stringEntity;
			try {
				String data = new String(JSON.toJSONString(map).getBytes("utf-8"), "iso-8859-1");
				stringEntity = new StringEntity(data);
				httpPost.setEntity(stringEntity);

			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				throw new Exception("转换字符集出错,\r\n出错信息:" + e.getMessage());
			}

		}

		Map<String, Object> resultmap = new HashMap<>();
		try {
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpResponse.getStatusLine().getStatusCode() == 200) {

				String jsonResult = EntityUtils.toString(httpResponse.getEntity());
				resultmap = GsonUtil.fromJson(jsonResult,
		                new TypeToken<Map<String, Object>>() {
		                });

			} else if (httpResponse.getStatusLine().getStatusCode() == 404) {
				throw new Exception("404错误，请求的资源不存在");
			} else if (httpResponse.getStatusLine().getStatusCode() == 500) {
				throw new Exception("500错误,服务器内部错误");
			} else
				throw new Exception("Http请求出错,返回Http状态码:" + httpResponse.getStatusLine().getStatusCode());
		} catch (Exception ex) {
			throw new Exception("处理httpPost操作出错,\r\n出错信息:" + ex.getMessage());
		}
		return resultmap;
	}

	/**
	 * 不带参数post请求
	 *
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> doPost(String url) throws Exception {
		return this.doPost(url, null);
	}
}