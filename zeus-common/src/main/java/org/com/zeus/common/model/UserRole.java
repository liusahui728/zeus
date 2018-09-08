package org.com.zeus.common.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;

@Data
@Entity
public class UserRole extends Model<UserRole> {

	private static final long serialVersionUID = 1L;
	@Id
	@TableId
	private Long userRoleId;
	private Long roleId;
	private Long userId;

	@Override
	protected Serializable pkVal() {
		return userRoleId;
	}

}
