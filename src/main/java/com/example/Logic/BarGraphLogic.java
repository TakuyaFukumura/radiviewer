/**
 *
 */
package com.example.Logic;

import java.math.BigDecimal;
import java.util.List;

import com.example.Dto.DividendDto;

/**
 * @author fukumura
 *
 */
public class BarGraphLogic {
	static int[] years = { 2019, 2020, 2021};

	/**
	 * 配当データのリストから棒グラフ描画用の
	 * 文字列を生成して返します。<br>
	 * 生成するのは2019年～2021年の3年分です
	 * @param dividendDtoList 配当データリスト
	 * @return contents グラフ描画用データ配列
	 */
	public String[] getCartData( List<DividendDto> dividendDtoList ) {
		String data[] = new String[3];
		for(int i = 0; i < years.length; i++) {
			String[] temp = getMonthlyIncome(years[i], dividendDtoList);
			data[i] = createCartData(temp);
		}
		return data;
	}

	/**
	 * 指定した年の月別配当受取合計額をString配列で取得します
	 * @param year 取得したい年（西暦）
	 * @param dividendDtoList 配当データリスト
	 * @return monthlyDividend 月別の配当受取額
	 */
	public String[] getMonthlyIncome( int year , List<DividendDto> dividendDtoList ) {
		String[] monthlyDividend = new String[12];
		String[] month = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		BigDecimal sum = new BigDecimal(0);
		BigDecimal afterTaxDividendIncome = new BigDecimal(0);

		for(int i = 0; i < month.length; i++) {
			sum = new BigDecimal(0);
	        for(DividendDto dividendDto : dividendDtoList){ // 拡張for文
	        	if( (year + month[i]).equals(dividendDto.getPaymentDay(false))) { //年月が一致しているかどうか
	        		afterTaxDividendIncome = exchange(dividendDto); //米国株式なら×100する
	        		sum = sum.add(afterTaxDividendIncome); //合計する
	        	}
	        }
	        monthlyDividend[i] = sum.toString(); //月配当受取額を文字列変換
		}
		return monthlyDividend;
	}

	/**
	 * ドルは円にして、円は円のまま数値を返します
	 * @param dividendDto 個別配当情報
	 * @return afterTaxDividendIncome 税引き後、為替適用後配当受取額
	 */
	public BigDecimal exchange(DividendDto dividendDto) {
		BigDecimal afterTaxDividendIncome = dividendDto.getAfterTaxDividendIncome();
		BigDecimal exchangeRate = new BigDecimal(100);
		if("米国株式".contentEquals(dividendDto.getProduct())) {
			afterTaxDividendIncome = afterTaxDividendIncome.multiply(exchangeRate);
		}
		return afterTaxDividendIncome;
	}

	/**
	 * 文字列の配列を一つの文字列に合成します<br>
	 * なお、合成する際には各要素の間に「,」を入れます<br>
	 * 例： " 0.7, 2.2, 4.0"<br>
	 * @param getSumIncomeList 月毎配当額情報 文字列配列型
	 * @return Stringグラフ描画用文字列
	 */
	public String createCartData(String[] monthlyDividend) {
		String result = "";
		result += monthlyDividend[0];
		for(int i = 1; i < monthlyDividend.length; i++) {
			result += ",";
			result += monthlyDividend[i];
		}
		return result;
	}

}
