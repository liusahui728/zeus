package org.com.zeus.sequence.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 序列注册
 * 
 * @author 凯旋
 *
 */
@Configuration
public class SequenceInitConfig {
	@Bean(initMethod = "init")
	public SeqGenerator seqSequence() {
		return new SeqGeneratorImpl("seq", 10);
	}
	public SeqGenerator seq2Sequence() {
		return new SeqGeneratorImpl("seq_2", 10);
	}
	public SeqGenerator seq3Sequence() {
		return new SeqGeneratorImpl("seq_3", 10);
	}
	public SeqGenerator seq4Sequence() {
		return new SeqGeneratorImpl("seq3", 10);
	}

}
