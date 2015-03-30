package com.web.util;

import java.util.ArrayList;
import java.util.List;

public class ClientTest {
	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		list.add("123123");
		list.add("zhaopeng");
		list.add("ljh");
		list.add("zhaopeng");
		list.add("ljh");
		
		Tools.printfList(list);
		List<String> after=Tools.removeDuplicate(list);
		System.out.println("after remove");
		Tools.printfList(after);
	}

}
