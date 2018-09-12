package org.com.zeus.common.base.entity;

import java.util.List;

import lombok.Data;

@Data
public class BaseTree {
	private Long id;
	private String text;
	private String iconCls;
	//节点状态，'open' 或 'closed'，默认是 'open'。当设置为 'closed' 时，该节点有子节点，并且将从远程站点加载它们。
	private String state;
	private Boolean checked;
	private String attributes;
	private List<BaseTree> children;
}
