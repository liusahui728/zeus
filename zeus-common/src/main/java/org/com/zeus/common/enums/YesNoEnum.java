package org.com.zeus.common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum YesNoEnum implements IEnum<Byte> {
	YES((byte) 1, "是"), NO((byte) 0, "否");

	private Byte value;
	private String desc;

	YesNoEnum(final Byte value, final String desc) {
		this.value = value;
		this.desc = desc;
	}

	@Override
	public Byte getValue() {
		return this.value;
	}

	public String getDesc() {
		return this.desc;
	}

}
