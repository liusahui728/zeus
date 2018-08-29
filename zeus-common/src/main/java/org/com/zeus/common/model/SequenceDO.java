package org.com.zeus.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "sequence")
@Data
public class SequenceDO {
	@Id
	@Column(name = "seq_name", length = 20)
	private String seqName;

	@Column(name = "current_val")
	private Long currentVal;

}
