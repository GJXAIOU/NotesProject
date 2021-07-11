package com.gjxaiou.composite;

import lombok.Data;

@Data
public abstract class OrganizationComponent {
	private String name;
	private String des;

	protected void add(OrganizationComponent organizationComponent) {
		// Ĭ��ʵ��
		throw new UnsupportedOperationException();
	}

	protected void remove(OrganizationComponent organizationComponent) {
		// Ĭ��ʵ��
		throw new UnsupportedOperationException();
	}

	// ���� print, ���ɳ����, ���඼��Ҫʵ��
	protected abstract void print();
}