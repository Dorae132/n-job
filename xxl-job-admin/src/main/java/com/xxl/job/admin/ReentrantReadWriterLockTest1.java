package com.xxl.job.admin;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 * locak test
 * 
 * @author Dorae
 *
 */
class ReadWrite {
	// 共享数据，只能一个线程写数据，可以多个线程读数据
	private Object data = null;
	// 创建一个读写锁
	ReadWriteLock rwlock = new ReentrantReadWriteLock();
	Lock r = rwlock.readLock(); // 读锁
	Lock w = rwlock.writeLock(); // 写锁

	public void get() {
		r.lock(); // 读上锁
		try {
			System.out.println(Thread.currentThread().getName() + "**准备读数据!");
			Thread.sleep(10000); // 模拟读操作
			System.out.println(Thread.currentThread().getName() + "---------读出的数据为 :" + data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			r.unlock();
		}

	}

	public void put(Object data) {
		w.lock(); // 写上锁
		try {
			System.out.println(Thread.currentThread().getName() + " +++++++准备写数据!");
			Thread.sleep((long) (Math.random() * 1000)); // 模拟写操作
			this.data = data;
			System.out.println(Thread.currentThread().getName() + " ........写入的数据: " + data);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			w.unlock();
		}
	}
}

public class ReentrantReadWriterLockTest1 {

	public static void main(String[] args) {

		final ReadWrite readWrite = new ReadWrite();

		for (int i = 0; i < 9; i++) { // 创建并启动了9个读线程
			new Thread(new Runnable() {

				@Override
				public void run() {
					readWrite.get();

				}
			}).start();
		}

		for (int i = 0; i < 3; i++) { // 创建并启动3个写线程
			new Thread(new Runnable() {

				@Override
				public void run() {
					readWrite.put(new Random().nextInt(8)); // 随机写入一个数据
				}
			}).start();
		}

	}
}
