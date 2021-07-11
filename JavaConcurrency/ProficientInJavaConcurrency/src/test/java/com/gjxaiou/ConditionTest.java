package com.gjxaiou;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
	Lock lock = new ReentrantLock();
	// 生成两个 Condition，一个 Lock 实例对应两个 Condition 实例
	Condition notEmpty = lock.newCondition();
	Condition notFull = lock.newCondition();

	Object[] item = new Object[100];

	public void put(Object value){
		lock.lock();
		try {


		}finally {
			lock.unlock();
		}
	}
}
