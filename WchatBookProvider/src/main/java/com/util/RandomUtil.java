

/**   
* @Title: RandomUtil.java
* @Package com.goujia.website.base.utils
* @Description: TODO(用一句话描述该文件做什么)
* @author chenghao   
* @date Sep 11, 2015 10:47:46 AM
* @version V1.0   
*/
package com.util;

import java.util.Date;
import java.util.Random;


/**
 * @Description: 
 * @author  chenghao
 * @date Sep 11, 2015 10:47:46 AM
 * 
 */
public class RandomUtil {
	/**
	 * 
	* @Description start =< n < end  
	* @param start
	* @param end
	* @return
	* @author chenghao   
	* @date Sep 11, 2015 10:49:21 AM
	 */
    
    private static Random random=new Random(new Date().getTime());
    
	public static Integer randomInt(Integer start,Integer end){
		return random.nextInt(end-start)+start;
	}
	public static Integer randomInt(Integer length){
		int s=1;
		while(length--!=0)
			s*=10;
		return randomInt(s/10,s);
	}
	public static String randomStr(int length){
	    return String.valueOf(randomInt(length));
	}
}
