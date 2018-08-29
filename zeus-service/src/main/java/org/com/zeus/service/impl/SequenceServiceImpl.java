package org.com.zeus.service.impl;

import java.util.List;

import org.com.zeus.common.model.SequenceDO;
import org.com.zeus.sequence.dao.SequenceDao;
import org.com.zeus.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sequenceService")
public class SequenceServiceImpl implements SequenceService {
	@Autowired
	SequenceDao sequenceDao;
	
	@Override
	public List<SequenceDO> getList() {
		return sequenceDao.getList();
	}
}
