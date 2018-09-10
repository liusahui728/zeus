package org.com.zeus.common.base.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

public abstract class BaseQuery<T extends BaseQuery> extends Model<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	// 每页数
	@TableField(exist = false)
	private int pageSize = 10;
	// 起始行
	@TableField(exist = false)
	private int offset;
	// 页码
	@TableField(exist = false)
	private int pageNo = 1;


	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		this.offset = (pageNo - 1) * this.pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public BaseQuery setPageSize(int pageSize) {
		this.pageSize = pageSize;
		this.offset = (pageNo - 1) * this.pageSize;
		return this;
	}

	public int getoffset() {
		return offset;
	}

	public void setoffset(int offset) {
		this.offset = offset;
	}

}

