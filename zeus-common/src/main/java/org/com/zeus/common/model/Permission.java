package org.com.zeus.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.com.zeus.common.constants.ColumnDefinition;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;

@Entity
@Data
public class Permission extends Model<Permission> {

	private static final long serialVersionUID = 1L;
	@Id
	@TableId
	private Long permissionId;

	private String permissionKey;

	private String permissionName;

	private String permissionUrl;

	@Column(columnDefinition = ColumnDefinition.LONGDEFAULT)
	private Long parentId;

	private String icon;

	private Integer orderNum;
	

	@Transient
	@TableField(exist = false)
	private List<Permission> children;

	@Column(columnDefinition = ColumnDefinition.DATEDEFAULT)
	private Date createTime;
	@Column(columnDefinition = ColumnDefinition.DATEDEFAULT)
	private Date modifyTime;
	@Column(columnDefinition = ColumnDefinition.BYTEDEFAULT)
	private Byte deleted;

	@Override
	protected Serializable pkVal() {
		return permissionId;
	}

}
