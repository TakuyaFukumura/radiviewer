/**
 *
 */
package com.example.Logic;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.BigInteger;
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
		String[] years = {"2019", "2020", "2021"};
		List<DividendDto> dividendDtoList = sampleLogic.readInternalFile("test.csv");
		String data[] = barGraphLogic.getCartData( dividendDtoList, years );
		assertEquals("0,0,24,0,56,3,60,34,5,379.00,29,7", data[0] );
	}

	/**
	 * {@link com.example.Logic.BarGraphLogic#getMonthlyIncome(int, java.util.List)} のためのテスト・メソッド。
	 */
	@Test
	void testGetMonthlyIncome() {
		List<DividendDto> dividendDtoList = sampleLogic.readInternalFile("test.csv");
		String data[] = barGraphLogic.getMonthlyIncome( dividendDtoList, "2019" );
		assertEquals( "0", data[0] );
	}

	/**
	 * {@link com.example.Logic.BarGraphLogic#exchange(com.example.Dto.DividendDto)} のためのテスト・メソッド。
	 */
	@Test
	void testExchange() {
		List<DividendDto> dividendDtoList = sampleLogic.readInternalFile("test.csv");
		BigDecimal rate = new BigDecimal("100"); // ドル円レート
		dividendDtoList = barGraphLogic.exchange(dividendDtoList, rate); // 全データを両替

		BigInteger actual = new BigDecimal(42).toBigInteger(); // 期待される値
		BigInteger expected = dividendDtoList.get(3).getAfterTaxDividendIncome().toBigInteger(); // 実際の値

		assertEquals( expected, actual );
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
