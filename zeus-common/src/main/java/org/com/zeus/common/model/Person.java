package org.com.zeus.common.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.com.zeus.common.base.entity.BaseQuery;

import lombok.Data;

@Entity
@Data
public class Person extends BaseQuery<Person> {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Long personId;

	private String name;

	@Override
	protected Serializable pkVal() {
		return this.personId;
	}

}
