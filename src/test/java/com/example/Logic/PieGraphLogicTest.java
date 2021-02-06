/**
 *
 */
package com.example.Logic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.Dto.DividendDto;

/**
 * @author fukumura
 *
 */
class PieGraphLogicTest {

	SampleLogic sampleLogic = new SampleLogic();
	PieGraphLogic pieGraphLogic = new PieGraphLogic();

	private List<DividendDto> dividendDtoList = sampleLogic.readInternalFile("test.csv");//new ArrayList<DividendDto>();

	/**
	 * {@link com.example.Logic.PieGraphLogic#getCartData(java.util.List)} のためのテスト・メソッド。
	 * グラフ表示用の数値データを意図通りに取得できるかを検証する
	 */
	@Test
	void testGetCartData() {
		String[] tmp = pieGraphLogic.getCartData(dividendDtoList);
		assertEquals("688.00,422,414.00,376.00,190.00,164.00,147.00,140.00,1055.00", tmp[0]);
	}

	/**
	 * {@link com.example.Logic.PieGraphLogic#grouping(java.util.List)} のためのテスト・メソッド。
	 * 同じ銘柄の配当が合算されていることを確かめる
	 */
	@Test
	void testGrouping() {
		List<DividendDto> tmp = pieGraphLogic.grouping(dividendDtoList);
		assertEquals("ARES CAPITAL COR3.76", tmp.get(1).getIssue()+tmp.get(1).getAfterTaxDividendIncome().toString());
	}

	/**
	 * {@link com.example.Logic.PieGraphLogic#sort(java.util.List)} のためのテスト・メソッド。
	 * 並び替えをして、一番配当額の大きいリストが先頭になっていることを確かめる
	 */
	@Test
	void testSort() {
		List<DividendDto> tmp = pieGraphLogic.sort(dividendDtoList);
		assertEquals("ＮＦ株主還元７０54", tmp.get(0).getIssue()+tmp.get(0).getAfterTaxDividendIncome().toString());
	}

	/**
	 * {@link com.example.Logic.PieGraphLogic#exchange(java.util.List)} のためのテスト・メソッド。
	 * 設定レート通りに円換算されていることを確かめる
	 */
	@Test
	void testExchange() {
		List<DividendDto> tmp = pieGraphLogic.exchange(dividendDtoList);
		assertEquals("10.00", tmp.get(0).getAfterTaxDividendIncome().toString());
		// 有効桁数に注意
	}

	/**
	 * {@link com.example.Logic.PieGraphLogic#createIssueData(java.util.List)} のためのテスト・メソッド。
	 * 指定した数だけ銘柄名が取得されていることを確かめる
	 */
	@Test
	void testCreateIssueData() {
		String tmp = pieGraphLogic.createIssueData(dividendDtoList, 2);
		assertEquals("\"VV EM HY BOND\",\"ARES CAPITAL COR\"", tmp);
	}

	/**
	 * {@link com.example.Logic.PieGraphLogic#createCartData(java.util.List)} のためのテスト・メソッド。
	 * 指定した数の配当額データを取得しているか確かめる
	 */
	@Test
	void testCreateCartData() {
		String tmp = pieGraphLogic.createCartData(dividendDtoList, 3);
		assertEquals("0.10,0.32,822.59", tmp);
		// numが3のとき、"初項,次項,残りの合計"
		// 有効桁数に注意
	}

	/**
	 * {@link com.example.Logic.PieGraphLogic#strComposition(java.lang.String[])} のためのテスト・メソッド。
	 * String配列が1つの文字列に合成されていることを確かめる
	 */
	@Test
	void testStrComposition() {
		String[] data = {"1","2","3"};
		String tmp = pieGraphLogic.strComposition(data);
		assertEquals("1,2,3", tmp);
	}

}
