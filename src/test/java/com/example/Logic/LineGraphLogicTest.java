/**
 *
 */
package com.example.Logic;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.Dto.DividendDto;

/**
 * @author fukumura
 *
 */
class LineGraphLogicTest {
	SampleLogic sampleLogic = new SampleLogic();
	private List<DividendDto> dividendDtoList = sampleLogic.readInternalFile("test.csv");

	LineGraphLogic lineGraphLogic = new LineGraphLogic();

	/**
	 * {@link com.example.Logic.LineGraphLogic#getCartData(java.util.List, java.lang.String[])} のためのテスト・メソッド。
	 */
	@Test
	void testGetCartData() {
		String[] year = {"2019","2020"};
		String expected = "0,0,24,24,80,83,143,177,182,561.00,590.00,597.00,1072.00,1126.00,1348.00,1507.00,1810.00,2006.00,2151.00,2418.00,2550.00,2741.00,3129.00,3358.00"; // 期待される値
		String actual =  lineGraphLogic.getCartData( dividendDtoList, year ); // 実際の値

		assertEquals( expected, actual );
	}

	/**
	 * {@link com.example.Logic.LineGraphLogic#createCumulativeList(java.util.List)} のためのテスト・メソッド。
	 */
	@Test
	void testCreateCumulativeList() {
		List<BigDecimal[]> dataList = new ArrayList<BigDecimal[]>();
		BigDecimal[] data = {new BigDecimal("2"), new BigDecimal("3")};
		dataList.add(data);
		BigDecimal[] data2 = {new BigDecimal("2"), new BigDecimal("3")};
		dataList.add(data2);

		List<BigDecimal> tmp = lineGraphLogic.createCumulativeList( dataList );

		String actual = tmp.get(3).toString(); // 実際の値
		String expected = "10"; // 期待される値

		assertEquals( expected, actual );
	}

	/**
	 * {@link com.example.Logic.LineGraphLogic#createCartData(java.util.List, java.lang.String)} のためのテスト・メソッド。
	 */
	@Test
	void testCreateCartData() {
		BigDecimal[] tmp = lineGraphLogic.createCartData( dividendDtoList, "2020" );

		String actual = tmp[0].toString(); // 実際の値
		String expected = "53.26"; // 期待される値

		assertEquals( expected, actual );
	}

	/**
	 * {@link com.example.Logic.LineGraphLogic#strComposition(java.util.List)} のためのテスト・メソッド。
	 */
	@Test
	void testStrComposition() {
		List<BigDecimal> dataList = new ArrayList<BigDecimal>();
		BigDecimal data = new BigDecimal("2");
		dataList.add(data);
		BigDecimal data2 = new BigDecimal("3");
		dataList.add(data2);

		String actual = lineGraphLogic.strComposition( dataList ); // 実際の値
		String expected = "2,3"; // 期待される値

		assertEquals( expected, actual );
	}

	/**
	 * {@link com.example.Logic.LineGraphLogic#exchange(java.util.List)} のためのテスト・メソッド。
	 */
	@Test
	void testExchange() {
		List<DividendDto> tmp = lineGraphLogic.exchange( dividendDtoList );

		String actual = tmp.get(3).getAfterTaxDividendIncome().toString(); // 実際の値
		String expected = "42.00"; // 期待される値

		assertEquals( expected, actual );
	}

}
