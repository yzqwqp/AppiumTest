package com.toutoujinrong.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


/**
 * webService工具类未完成，
 * 待有时间做一做星星说的 webservice cxf 工具类   TODO
 * @author qiupeng
 *
 */
public class WebServiceUtils {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private Map<String, List<String>> map = new HashMap<String, List<String>>();
	private List<String> urls = new ArrayList<String>();
	private CloseableHttpClient ch;
	private boolean isLog = true;

	public int init() throws Throwable {
        initPrameter();
        initTools();
        return 0;
	}//end of init


	public int action() throws Throwable {
        http();
        return 0;
	}//end of action


	public int end() throws Throwable {
		return 0;
	}//end of end

    protected void initTools() {
    	ch = HttpClients.createDefault();
    }

//<users>
    protected void initPrameter() {
    	List<String> l1 = new ArrayList<String>();
        l1.add("{\"userCode\":\"<users>\",\"fisrtLogin\":\"1\",\"infoList\":[{\"systemCode\":\"6\",\"msgType\":\"1\"},{\"systemCode\":\"6\",\"msgType\":\"2\"}]}");
		String u1 = "ttmService";
		urls.add(u1);
		map.put(u1, l1);
    }

    public void http() {
		int i = getRandmon(urls.size());
		String url = urls.get(i);
		HttpPost hp = new HttpPost("http://192.168.2.51:5101/fundbase/ws/" + url);
		hp.addHeader("Content-Type", "application/json");
		HttpEntity he = null;
		List<String> json = map.get(url);
		he = new StringEntity(json.get(getRandmon(json.size())), ContentType.APPLICATION_JSON);
		hp.setEntity(he);
		execute(hp, println(getDateTime()));
    }

    protected String println(String msg) {
		if (isLog) {
		System.out.println(msg);
		}
		return msg;
    }

	protected void execute(HttpPost hp, String id) {
		try {
			CloseableHttpResponse rs = ch.execute(hp);
			int flagCode=rs.getStatusLine().getStatusCode();
			if (flagCode==200  || flagCode==204 ) {
			} else {
			}
			// 日志输出
			if (isLog) {
				if (rs.getEntity() != null) {
					println("[" + id + "]      " + IOUtils.toString(rs.getEntity().getContent()));
				}else{
					System.out.println("Status Code :"+flagCode+";      File user is :");
				}
			}
		} catch (ClientProtocolException e) {
		e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}


	protected int getRandmon(int size) {
		Random rd = new Random();
		int i = rd.nextInt(size);
		return i < 1 ? 0 : i ;
	}

	protected String getDateTime() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss sss");
		return df.format(new Date());
	}


	
}
