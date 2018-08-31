package org.com.zeus.common.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;

@Entity
@Data
@Table(name = "person")
public class Person extends Model<Person> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
