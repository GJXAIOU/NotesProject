package com.gjxaiou.composite;

import lombok.Data;

@Data
public abstract class OrganizationComponent {
	private String name;
	private String des;

	protected void add(OrganizationComponent organizationComponent) {
		// 默认实现
		throw new UnsupportedOperationException();
	}

	protected void remove(OrganizationComponent organizationComponent) {
		// 默认实现
		throw new UnsupportedOperationException();
	}

	// 方法 print, 做成抽象的, 子类都需要实现
	protected abstract void print();
}