package com.atguigu.principle.singleresponsibility;

public class SingleResponsibility1 {
	public static void main(String[] args) {
		Vehicle vehicle = new Vehicle();
		vehicle.run("Ħ�г�");
		vehicle.run("����");
		vehicle.run("�ɻ�");
	}
}

/**
 * ��ʽ1��ֻʹ��һ����ͨ������
 * 	1. �ڷ�ʽ 1 �� run�����У�Υ���˵�һְ��ԭ��
 * 	2. ����ķ����ǳ��ļ򵥣����ݽ�ͨ�������з�����ͬ���ֽ�ɲ�ͬ�༴��
 */
class Vehicle {
	public void run(String vehicle) {
		System.out.println(vehicle + " �ڹ�·������....");
	}
}
