package junit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;

import org.junit.Test;

import com.ftoul.common.DateUtil;
/**
 * 
 * @author hud
 *
 */
public class OrderNumberTest implements Runnable{

	@Test
	public void orderNumberTest(){
		OrderNumberTest orderNumberTest = new OrderNumberTest();
		for(int i=0;i<100;i++){
			new Thread(orderNumberTest,"新线程" + i).start(); 
		}
//		System.out.println(orderNumberTest.saveOrder());
	}
	
	public String saveOrder() throws Exception {
		String param = "param=%7B%22userToken%22:%7B%22id%22:%224028b7ce565849d00156586159f6000b%22,%22user%22:%7B%22id%22:%224028b7bc564aa7c701564ae9004e0004%22,%22p2pId%22:%22442539%22,%22ip%22:null,%22source%22:null,%22username%22:%2213787151379%22,%22score%22:null,%22xp%22:null,%22email%22:null,%22level%22:null,%22pic%22:null,%22name%22:null,%22sex%22:null,%22mobil%22:%2213787151379%22,%22cardId%22:null,%22payPassword%22:null,%22passwordVersion%22:%222%22,%22static_%22:null,%22createTime%22:null,%22createPerson%22:null,%22modifyTime%22:null,%22modifyPerson%22:null,%22state%22:%221%22%7D,%22secretKey%22:%22fae6635893ad9cab30d710746f59749b%22,%22mobilToken%22:%22821fb0e0cd6f0df67719eebad8b7995c%22,%22pcToken%22:null,%22uploadTime%22:%222016-08-17+10:38:36%22%7D,%22obj%22:%7B%22addressId%22:%224028b7ca562631eb0156263272ac0000%22,%22goodsParameter%22:%224,1,5800%22,%22payable%22:5800%7D%7D";
		/** http连接 */
		URL url = new URL("http://localhost:8080/FtShop/web/orders/saveOrders.action?" + param);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				url.openStream(), "UTF-8"));
		String str = null;
		StringBuffer sb = new StringBuffer();
		while ((str = in.readLine()) != null) {
			sb.append(str);
		}
		if (in != null) {
			in.close();
		}
		String result = sb.toString();
		return result;
	}
	
	 public void run() {
		String timeStr = "2016-08-18 14:23:00";
		System.out.println(123);
		Date d = DateUtil.stringFormatToDate(timeStr, "yyyy-MM-dd HH:mm:ss");
		
		Date d2 = new Date();
		System.out.println(d2.getTime() - d.getTime());
		if(d2.getTime() <= d.getTime())
			System.out.println(Thread.currentThread().getName());  
    }  
	
//	@Test
//	public void test2(){
//		
//		System.out.println(d.getTime());
//	}

}
