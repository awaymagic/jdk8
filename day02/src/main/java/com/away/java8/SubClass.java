package com.away.java8;

public class SubClass /*extends MyClass*/ implements MyFun, MyInterface{

	@Override
	public String getName() {
		// 类优先原则，会先执行MyFun
		// return super.getName();

		return MyInterface.super.getName();
	}

}
