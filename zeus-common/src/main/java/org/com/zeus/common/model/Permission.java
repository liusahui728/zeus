package org.com.zeus.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.com.zeus.common.constants.ColumnDefinition;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;

@Entity
@Data
public class Permission extends Model<Permission> {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "permission_id")
	private Long permissionId;

	@Column(name = "permission_key")
	private String permissionKey;

	@Column(name = "permission_name")
	private String permissionName;

	@Column(name = "permission_url")
	private String permissionUrl;

	@Column(name = "parent_id")
	private Long parentId;

	@Column(name = "icon")
	private String icon;

	@Column(name = "order_num")
	private Integer orderNum;

	@Column(name = "create_time", columnDefinition = ColumnDefinition.DATEDEFAULT)
	private Date createTime;
	@Column(name = "modify_time", columnDefinition = ColumnDefinition.DATEDEFAULT)
	private Date modifyTime;
	@Column(columnDefinition = ColumnDefinition.BYTEDEFAULT)
	private Byte deleted;

	@Override
	protected Serializable pkVal() {
		return permissionId;
	}

}
