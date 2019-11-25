package com.code.java.threads;

/**
 * @author Tirumalesh N
 * Description: This program generates even & odd numbers using two threads
 */
public class EvenOddNumberGenerator {
	

	private final int MAX_NUMBER;
	private boolean isOdd;
	private int count=1;
	
	public EvenOddNumberGenerator(boolean isOdd, int maxNumber) {
		this.isOdd = isOdd;
		this.MAX_NUMBER = maxNumber;
	}
	
	public synchronized void printOdd() {
		
		while(count < MAX_NUMBER) {
			if(!isOdd) {
				try {
					System.out.println("Odd waiting....");
					wait();
					System.out.println("Odd notified...");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Count is: " + count);
			count++;
			isOdd = false;
			notify();
		}
	}
	public synchronized void printEven() {
		
		while(count < MAX_NUMBER) {
			if(isOdd) {
				try {
					System.out.println("Even waiting....");
					wait();
					System.out.println("Even notified...");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Count is: " + count);
			count++;
			isOdd = true;
			notify();
		}
	}

	public static void main(String[] args) {

		EvenOddNumberGenerator printer = new EvenOddNumberGenerator(true,20);
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				printer.printEven();
			}
			
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				printer.printOdd();
			}
			
		});
		t1.start();
		t2.start();
	}

}
