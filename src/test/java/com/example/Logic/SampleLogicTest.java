/**
 *
 */
package com.example.Logic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author fukumura
 *
 */
class SampleLogicTest {
	@Autowired
	static SampleLogic sampleLogic = new SampleLogic();

	/**
	 * {@link com.example.Logic.SampleLogic#readInternalFile()} のためのテスト・メソッド。
	 */
	@Test
	void testReadInternalFile() {
		assertTrue(true);
	}

}
