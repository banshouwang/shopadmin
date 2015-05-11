package com.banshou.app.utils.common;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 产生随机字符串工具类
 *
 */
public class RandomStrUtil {
	
	private static StringBuffer buffer=null;
	
	private static void checkBuffer() {
		buffer=null;
		if(buffer==null){
			buffer=new StringBuffer();
		}
	}
	
	private static  String getCapitlChar(){
		return ((char)(getRandomInt(100)%26+65))+"";
	}

	private static Object getLowerChar() {
		return ((char)(getRandomInt(100)%26+97))+"";
	}
	
	/**
	 * 返回指定范围内的随机数字
	 * @param n
	 * @return
	 */
	private static int getRandomInt(int n){
		return (int) (Math.random()*n);
	}
	
	/**
	 * 获取只含大写字母的字符串
	 * @param length   长度
	 */
	public static String getCapitalStr(int length){
		checkBuffer();
		for(int i=0;i<length;i++){
			buffer.append(getCapitlChar());
		}
		return buffer.toString();
	}
	
	/**
	 * 获取只含小写字母的字符串
	 * @param length  长度
	 * @return
	 */
	public static String getLowerStr(int length){
		checkBuffer();
		for(int i=0;i<length;i++){
			buffer.append(getLowerChar());
		}
		return buffer.toString();
	}

	/**
	 * 获取随机字符串，不区分大小写，但是不包含数字
	 * @param length
	 * @return
	 */
	public static String getComCharStr(int length){
		checkBuffer();
		for(int i=0;i<length;i++){
			if(getRandomInt(10)%2==0)
				buffer.append(getLowerChar());
			if(getRandomInt(10)%2==1)
				buffer.append(getCapitlChar());
		}
		return buffer.toString();
	}

	/**
	 * 获取只含数字的字符串
	 * @param length
	 * @return
	 */
	public synchronized static String getNumStr(int length){
		checkBuffer();
		for(int i=0;i<length;i++){
			buffer.append(getRandomInt(100)%10);
		}
		return buffer.toString();
	}
	
	/**
	 * 生成普通字符串，可能包含数字，大写字母，小写字母
	 * @param length
	 * @return
	 */
	public static String getCommonStr(int length){
		checkBuffer();
		for(int i=0;i<length;i++){
			int n=getRandomInt(100);
			if(n%3==0){
				buffer.append(getLowerChar());
			}else if(n%3==1){
				buffer.append(getCapitlChar());
			}else if(n%3==2){
				buffer.append(getRandomInt(100)%10);
			}
		}
		return buffer.toString();
	}
	
	/**
	 * 获取每时每刻都不同的唯一 20 位(包含前缀)长度以上的  序列串
	 * @param Prdfix    序列串前缀,指定一个大写字符
	 * @param length		长度，必须大约等于20，如果小于，默认为：20
	 * @return
	 */
	 public synchronized static  String getUniqueneStr(char Prdfix, int length){
		if(length<20){
			length=20;
		}
		if(Prdfix==' '){
			Prdfix='Z';
		}
		checkBuffer();
		buffer.append(Prdfix);
		buffer.append(getTimeStr());
		buffer.append(getCapitlChar());
		buffer.append(getCapitlChar());
		if(length>20){
			buffer.append(getCapitalStr(length-20));
		}
		return buffer.toString();
	}
	
	private static String getTimeStr(){
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		String st=null;
		try{
				int y=calendar.get(Calendar.YEAR);
				int m=calendar.get(Calendar.MONTH)+1;
				int d=calendar.get(Calendar.DATE);
				int h=calendar.get(Calendar.HOUR);
				int min=calendar.get(Calendar.MINUTE);
				int s=calendar.get(Calendar.SECOND);
				int ms=calendar.get(Calendar.MILLISECOND);
				st=y+"";
				if(m<10){
					st+="0";
				}
				st=st+m;
				if(d<10){
					st+="0";
				}
				st=st+d;
				if(h<10){
					st+="0";
				}
				st=st+h;
				if(min<10){
					st+="0";
				}
				st=st+min;
				if(s<10){
					st+="0";
				}
				st=st+s;
				if(ms<10){
					st+="00";
				}else if(ms>10 && ms<100){
					st+="0";
				}
				st=st+ms;
		}catch (Exception e) {
		}
		return st;
	}
	
	public synchronized static String getTimeString(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String time = sdf.format(new Date());
		return time;
	}
}
