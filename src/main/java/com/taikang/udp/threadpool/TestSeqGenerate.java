package com.taikang.udp.threadpool;

import com.taikang.udp.sys.util.sequence.SequenceGenerator;

public class TestSeqGenerate extends Thread {
	private int count = 0;

	public void run() {
		long userid = SequenceGenerator.getInstance("userId", 6).nextId();
		System.out.println("第" + count + "次调用，userId=" + userid);
		/*
		 * for (int i = 0; i < 10000; i++) { if (count > 0) {
		 * System.out.println("count= " + count--); } }
		 */
	}

	public static void main(String[] args) {
		TestSeqGenerate g = null;
		for (int i = 1; i <= 1; i++) {
			g = new TestSeqGenerate();
			g.count = i;
			g.start();
		}

	}
}
