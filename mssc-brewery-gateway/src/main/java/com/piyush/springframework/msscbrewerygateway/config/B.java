package com.piyush.springframework.msscbrewerygateway.config;
// package whatever; // don't place package name!

class A {

	// private static A a = new A();
	private static A a = null;

	private A() {

	}

	public static A getInstance() {
		if (a == null) {
			a = new A();
		}
		return a;

	}
}

class B {
	public static void main(String[] args) {

		A a = A.getInstance();
		A a1 = A.getInstance();

		System.out.println(a == a1);

	}
}