package org.com.zeus.sequence.handler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.com.zeus.common.model.SequenceDO;
import org.com.zeus.sequence.dao.SequenceDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SeqGeneratorImpl implements SeqGenerator {
	private static final Logger log = LoggerFactory.getLogger(SeqGeneratorImpl.class);

	@Autowired
	SequenceDao sequenceDao;

	ConcurrentHashMap<String, SequenceDTO> map = new ConcurrentHashMap<String, SequenceDTO>();

	Lock lock = new ReentrantLock();

	public int step = 10;

	public String seqName;

	public String getSeqName() {
		return seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public void init() {
		log.info("开始初始化Sequence序列------------->SequenceInitConf init sequence start");
		if (null == map.get(seqName)) {
			initSeq();
		}
		System.out.println("初始化Sequence序列结束------------->SequenceInit init------------->end");
	}

	@Override
	public Long getNextVal() {
		SequenceDTO sequenceDTO = map.get(seqName);
		Long currentVal = sequenceDTO.getCurrentVal();
		Long newValue = currentVal + 1;
		// 如果缓存中的最大值等于当前值
		if (sequenceDTO.getMaxVal().equals(currentVal)) {
			initSeq();
			// 迭代
			getNextVal();
		} else {
			map.put(seqName, new SequenceDTO(seqName, newValue, sequenceDTO.getMaxVal(), sequenceDTO.getMinVal()));
		}
		return newValue;
	}

	private synchronized void initSeq() {
		// 重新初始化缓存
		SequenceDO sequence = sequenceDao.getListByName(seqName);
		if (sequence == null) {
			log.info("未找到名为" + seqName + "的序列------------------->can't find sequence " + seqName
					+ "----------->start create sequence");
			SequenceDO sequenceDO = new SequenceDO();
			sequenceDO.setSeqName(seqName);
			sequenceDO.setCurrentVal(0L);
			try {
				Integer count = sequenceDao.insert(sequenceDO);
				System.out.println("count----》" + count);
				log.info("创建名为" + seqName + "的序列成功------------------->create sequence" + seqName + "success!");
				//initSeq();
				sequence=sequenceDO;
			} catch (Exception e) {
				log.error("创建名为" + seqName + "的序列失败-------------->can't create sequence " + seqName + "");
			}
		}
		Long newCurrentVal = sequence.getCurrentVal();
		System.out.println("Thread=---->" + Thread.currentThread().getName());
		Long max = sequence.getCurrentVal() + step;
		Long min = sequence.getCurrentVal();
		SequenceDTO sCount = new SequenceDTO(seqName, newCurrentVal, max, min);
		// 乐观锁说明更新成功
		Integer count = sequenceDao.update(sCount);
		if (count == 1) {
			map.put(sequence.getSeqName(), sCount);
		} else {
			initSeq();
		}
	}
}
