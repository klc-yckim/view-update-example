package com.example.viewupdateexample.service;

import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ViewCountServiceTest {

	private Long id = 1L;

	private Long want = 100L;
	private int threadCount = 100;

	@Autowired
	private ViewCountService viewCountService;

	@Test
	@DisplayName("칼럼 + 1 쿼리를 통한 업데이트")
	public void increaseCountTest() throws Exception {
		ExecutorService executorService = Executors.newFixedThreadPool(32);

		CountDownLatch latch = new CountDownLatch(threadCount);

		for (int i = 0; i < threadCount; i++) {
			executorService.submit(() -> {
				try {
					viewCountService.increaseViewCount(id);
				} catch (Exception e) {
					throw new RuntimeException(e);
				} finally {
					latch.countDown();
				}
			});
		}
		latch.await();

		Long got = viewCountService.getCount(id);
		assertThat(got).isEqualTo(want);
	}

	@Test
	@DisplayName("데이터 조회후 값 + 1을 통한 업데이트")
	public void increaseCountV2Test() throws Exception {
		ExecutorService executorService = Executors.newFixedThreadPool(32);

		CountDownLatch latch = new CountDownLatch(threadCount);

		for (int i = 0; i < threadCount; i++) {
			executorService.submit(() -> {
				try {
					viewCountService.increaseViewCountV2(id);
				} catch (Exception e) {
					throw new RuntimeException(e);
				} finally {
					latch.countDown();
				}
			});
		}
		latch.await();

		Long got = viewCountService.getCount(id);
		assertThat(got).isEqualTo(want);
	}
}