package com.example.demo.common.errors;

public class Exception500 extends RuntimeException {

	public Exception500(String msg) {
		super(msg); // 부모 클래스에 만들어져 있으니까 넘겨주기
	}
}
