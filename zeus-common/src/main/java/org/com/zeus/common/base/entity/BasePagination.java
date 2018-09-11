package org.com.zeus.common.base.entity;

import java.util.List;

import lombok.Data;

@Data
public class BasePagination {

	public List<?> rows;

	public long total;

	public BasePagination(List<?> rows, long total) {
		this.rows = rows;
		this.total = total;
	}
}
