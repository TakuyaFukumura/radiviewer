/**
 *
 */
package com.example.Logic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.Dto.DividendDto;

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
		List<DividendDto> dividendDtoList = sampleLogic.readInternalFile("test.csv");

		String expected = "ARCC"; // 期待される値
		String actual =  dividendDtoList.get(2).getIssueCode(); // 実際の値

		assertEquals( expected, actual );
	}
}
