package org.com.zeus;

import org.com.zeus.sequence.handler.SeqGenerator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BeanTest extends AppTest {
	@Autowired
	SeqGenerator seqSequence;
	@Autowired
	SeqGenerator seq2Sequence;
	@Autowired
	SeqGenerator seq3Sequence;

	@Test
	public void test() throws InterruptedException {
		System.out.println("123");
		for (int i = 0; i < 100; i++) {
			Thread thread = new Thread(() -> {

				System.out.println(seqSequence.getNextVal());
			});
			thread.start();
			thread.join();
		}
		for (int i = 0; i < 100; i++) {
			Thread thread2 = new Thread(() -> {
				System.out.println(seq2Sequence.getNextVal());
				System.out.println(seqSequence.getNextVal());
			});
			thread2.start();
			thread2.join();

		}
	}
}
