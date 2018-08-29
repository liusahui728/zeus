package org.com.zeus.sequence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.com.zeus.common.model.SequenceDO;
import org.com.zeus.sequence.handler.SequenceDTO;

public interface SequenceDao {
	
	public List<SequenceDO> getList();

	public SequenceDO getListByName(@Param("seqName") String seqName);
	
	public Integer update(SequenceDTO sequence);
	
	public Integer insert(SequenceDO sequence);
}
