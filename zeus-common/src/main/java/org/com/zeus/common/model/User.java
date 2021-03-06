package org.com.zeus.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.com.zeus.common.constants.ColumnDefinition;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;

@Entity
@Data
public class User extends Model<User> {

	private static final long serialVersionUID = 1L;
	@Id
	@TableId
	private Long userId;
	@Column(unique = true, length = 30, nullable = false)
	private String account;
	@Column(length = 30, nullable = false)
	private String userName;
	private String password;
	@Column(columnDefinition = ColumnDefinition.BYTEDEFAULT)
	private Byte status;

	@Column(columnDefinition = ColumnDefinition.DATEDEFAULT)
	private Date createTime;
	@Column(columnDefinition = ColumnDefinition.DATEDEFAULT)
	private Date modifyTime;
	@Column(columnDefinition = ColumnDefinition.BYTEDEFAULT)
	private Byte deleted;

	@Override
	protected Serializable pkVal() {
		return this.userId;
	}
}
