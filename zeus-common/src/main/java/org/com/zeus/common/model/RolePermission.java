package org.com.zeus.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;

@Data
@Entity
@Table(name = "role_permission")
public class RolePermission extends Model<RolePermission> {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "role_permision_id")
	private Long rolePermisionId;
	@Column(name = "role_id")
	private Long roleId;
	@Column(name = "permission_id")
	private Long permissionId;

	@Override
	protected Serializable pkVal() {
		return rolePermisionId;
	}

}
