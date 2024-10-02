package com.example.demo.common.errors;

public class Exception400 extends RuntimeException {

	public Exception400(String msg) {
		super(msg); // 부모 클래스에 만들어져 있으므로 넘겨주기
	}
}
