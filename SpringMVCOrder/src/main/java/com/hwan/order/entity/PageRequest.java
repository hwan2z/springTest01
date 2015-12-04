package com.hwan.order.entity;

public class PageRequest implements Pageable {
	private int index;
	private int size;
	
	public PageRequest(int index, int size) {
		this.size = size;
		this.index = index;
	}

	@Override
	public int getIndex() {		
		return index;
	}

	@Override
	public int getSize() {		
		return size;
	}

}
