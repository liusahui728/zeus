package org.com.zeus.common.base.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class BaseCombobox {
	private Long id;
	private String text;
	private Boolean selected;
	
	public static List<BaseCombobox> getDefaultSelect(){
		List<BaseCombobox> list=new ArrayList<BaseCombobox>();
		BaseCombobox cb=new BaseCombobox();
		cb.setText("请选择");
		cb.setSelected(true);
		list.add(cb);
		return list;
	}
}
