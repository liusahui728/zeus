package org.com.zeus.common.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;

@Entity
@Data
public class Person extends Model<Person> {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Long personId;

	private String name;

	@Override
	protected Serializable pkVal() {
		return this.personId;
	}

}
