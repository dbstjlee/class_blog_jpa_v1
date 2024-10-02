package com.example.demo.common.errors;

public class Exception403 extends RuntimeException {

	public Exception403(String msg) {
		super(msg); // 부모 클래스에 만들어져 있으니까 넘겨주기
	}
}
