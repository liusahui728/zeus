package org.com.zeus.sequence.handler;

import lombok.Data;

@Data
public class SequenceDTO {
	private String seqName;
	private Long currentVal;
	private Long maxVal;
	private Long minVal;

	public SequenceDTO(String seqName, Long currentVal, Long maxVal, Long minVal) {
		super();
		this.seqName = seqName;
		this.currentVal = currentVal;
		this.maxVal = maxVal;
		this.minVal = minVal;
	}

}
