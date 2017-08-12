/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. */
package com.baidu.ueditor.define;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.baidu.ueditor.Encoder;

/**
 * 澶氱姸鎬侀泦鍚堢姸鎬� * 鍏跺寘鍚簡澶氫釜鐘舵�鐨勯泦鍚� 鍏舵湰韬嚜宸变篃鏄竴涓姸鎬� * @author hancong03@baidu.com
 *
 */
public class MultiState implements State {

	private boolean state = false;
	private String info = null;
	private Map<String, Long> intMap = new HashMap<String, Long>();
	private Map<String, String> infoMap = new HashMap<String, String>();
	private List<String> stateList = new ArrayList<String>();
	
	public MultiState ( boolean state ) {
		this.state = state;
	}
	
	public MultiState ( boolean state, String info ) {
		this.state = state;
		this.info = info;
	}
	
	public MultiState ( boolean state, int infoKey ) {
		this.state = state;
		this.info = AppInfo.getStateInfo( infoKey );
	}
	
	@Override
	public boolean isSuccess() {
		return this.state;
	}
	
	public void addState ( State state ) {
		stateList.add( state.toJSONString() );
	}

	/**
	 * 璇ユ柟娉曡皟鐢ㄦ棤鏁堟灉
	 */
	@Override
	public void putInfo(String name, String val) {
		this.infoMap.put(name, val);
	}

	@Override
	public String toJSONString() {
		
		String stateVal = this.isSuccess() ? AppInfo.getStateInfo( AppInfo.SUCCESS ) : this.info;
		
		StringBuilder builder = new StringBuilder();
		
		builder.append( "{\"state\": \"" + stateVal + "\"" );
		
		// 鏁板瓧杞崲
		Iterator<String> iterator = this.intMap.keySet().iterator();
		
		while ( iterator.hasNext() ) {
			
			stateVal = iterator.next();
			
			builder.append( ",\""+ stateVal +"\": " + this.intMap.get( stateVal ) );
			
		}
		
		iterator = this.infoMap.keySet().iterator();
		
		while ( iterator.hasNext() ) {
			
			stateVal = iterator.next();
			
			builder.append( ",\""+ stateVal +"\": \"" + this.infoMap.get( stateVal ) + "\"" );
			
		}
		
		builder.append( ", list: [" );
		
		
		iterator = this.stateList.iterator();
		
		while ( iterator.hasNext() ) {
			
			builder.append( iterator.next() + "," );
			
		}
		
		if ( this.stateList.size() > 0 ) {
			builder.deleteCharAt( builder.length() - 1 );
		}
		
		builder.append( " ]}" );

		return Encoder.toUnicode( builder.toString() );

	}

	@Override
	public void putInfo(String name, long val) {
		this.intMap.put( name, val );
	}

}
