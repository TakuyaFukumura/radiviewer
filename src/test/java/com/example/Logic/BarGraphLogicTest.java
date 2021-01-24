/**
 *
 */
package com.example.Logic;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.Dto.DividendDto;

/**
 * @author fukumura
 *
 */
class BarGraphLogicTest {
	@Autowired
	static SampleLogic sampleLogic = new SampleLogic();

	static DividendDto dividendDto = new DividendDto(new Date(),
			"米国株式", "NISA","HYEM", "VV EM HY BOND",
			new BigDecimal(0.1139), new BigDecimal(1),
			new BigDecimal(0.11),new BigDecimal(0), new BigDecimal(0.1));

	@Autowired
	static BarGraphLogic barGraphLogic = new BarGraphLogic();

	/**
	 * {@link com.example.Logic.BarGraphLogic#getCartData(java.util.List)} のためのテスト・メソッド。
	 */
	@Test
	void testGetCartData() {
		List<DividendDto> dividendDtoList = sampleLogic.readInternalFile("test.csv");
		String data[] = barGraphLogic.getCartData(dividendDtoList);
		assertEquals("0,0,24,0,56,3,60,34,5,379.00,29,7", data[0] );
	}

	/**
	 * {@link com.example.Logic.BarGraphLogic#getMonthlyIncome(int, java.util.List)} のためのテスト・メソッド。
	 */
	@Test
	void testGetMonthlyIncome() {
		List<DividendDto> dividendDtoList = sampleLogic.readInternalFile("test.csv");
		String data[] = barGraphLogic.getMonthlyIncome(2019, dividendDtoList);
		assertEquals("0", data[0] );
	}

	/**
	 * {@link com.example.Logic.BarGraphLogic#exchange(com.example.Dto.DividendDto)} のためのテスト・メソッド。
	 */
	@Test
	void testExchange() {

		assertEquals(new BigDecimal(10).toBigInteger(), barGraphLogic.exchange(dividendDto).toBigInteger());
	}

	/**
	 * {@link com.example.Logic.BarGraphLogic#createCartData(java.lang.String[])} のためのテスト・メソッド。
	 */
	@Test
	void testCreateCartData() {
		String[] monthlyDividend = {"0", "0", "0"};
		assertEquals("0,0,0", barGraphLogic.createCartData(monthlyDividend));
	}

}
