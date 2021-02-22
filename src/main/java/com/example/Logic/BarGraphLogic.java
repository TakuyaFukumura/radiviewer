/**
 *
 */
package com.example.Logic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.Dto.DividendDto;

/**
 * @author fukumura
 *
 */
public class BarGraphLogic {


	/**
	 * 配当データのリストから棒グラフ描画用の
	 * 文字列を生成して返します。<br>
	 * 生成するのは2019年～2021年の3年分です
	 * @param dividendDtoList 配当データリスト
	 * @return contents グラフ描画用データ配列
	 */
	public String[] getCartData( List<DividendDto> dividendDtoList, String[] years ) {

		List<DividendDto> contents = new ArrayList<DividendDto>();

		for(DividendDto dividendDto : dividendDtoList){
			DividendDto content = new DividendDto(); // newが必要
			content.setAll(dividendDto.getAll()); // 全データ転記
			contents.add(content); // 新しいリストに追加
		} // sessionの値が書き換わる問題の回避策として追加

		String data[] = new String[years.length]; // 年毎の表示用データ格納用
		BigDecimal rate = new BigDecimal("100"); // ドル円レート

		contents = exchange(contents, rate); //ドルを円に変換
		for(int i = 0; i < years.length; i++) {
			String[] temp = getMonthlyIncome(contents, years[i]); // 月別配当
			data[i] = createCartData(temp); // 表示用データ成形
		}
		return data; // 年毎の表示用データ
	}

	/**
	 * ドルは円にして、円は円のまま数値を返します
	 * @param dividendDto 配当情報リスト
	 * @return afterTaxDividendIncome 税引き後、為替適用後配当受取額
	 */
	public List<DividendDto> exchange(List<DividendDto> dividendDtoList, BigDecimal rate) {

		DividendDto dividendDto = new DividendDto(); // 個別配当データ格納用
		BigDecimal afterTaxDividendIncome = new BigDecimal(0); // 受取配当額格納用

		for(int i = 0; i < dividendDtoList.size(); ++i){ // データの数だけループ
			dividendDto = dividendDtoList.get(i); // 配当データを取り出す
			if("米国株式".contentEquals(dividendDto.getProduct())) { // 商品が米国株なら
				afterTaxDividendIncome = dividendDto.getAfterTaxDividendIncome(); // 配当金情報取得
				afterTaxDividendIncome = afterTaxDividendIncome.multiply(rate); // 両替処理
				dividendDtoList.get(i).setAfterTaxDividendIncome(afterTaxDividendIncome); // 両替後の配当情報をリストに戻す
			}
		}
		return dividendDtoList; // ドル円両替済みの配当情報リスト
	}

	/**
	 * 指定した年の月別配当受取合計額をString配列で取得します
	 * @param year 取得したい年（西暦）
	 * @param dividendDtoList 配当データリスト
	 * @return monthlyDividend 月別の配当受取額
	 */
	public String[] getMonthlyIncome( List<DividendDto> dividendDtoList, String year ) {
		String[] month = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}; // 月を表す
		String[] monthlyDividendIncome = new String[month.length]; // 月別配当受取額格納用

		final BigDecimal ZERO = new BigDecimal(0); // 初期化用0
		BigDecimal sum = new BigDecimal(0); // 合計値格納用
		BigDecimal afterTaxDividendIncome = new BigDecimal(0); // 配当額一時格納用

		for( int i = 0; i < month.length; i++ ) { // 月の回数12回ループ
			sum = ZERO; // 初期化
	        for( DividendDto dividendDto : dividendDtoList ){ // データの数分だけループ、拡張for文
	        	if(( year + month[i] ).equals( dividendDto.getPaymentDay(false) )) { //指定年月と一致しているかどうか
	        		afterTaxDividendIncome = dividendDto.getAfterTaxDividendIncome(); // 配当情報を取り出す
	        		sum = sum.add(afterTaxDividendIncome); // 合算する
	        	}
	        }
	        monthlyDividendIncome[i] = sum.toString(); //この月の配当受取額を文字列に変換
		}
		return monthlyDividendIncome; // 月別の配当受取額(文字)
	}

	/**
	 * 月毎配当額情報をグラフ描画用に成形します<br>
	 * 文字列の配列を一つの文字列に合成します<br>
	 * なお、合成する際には各要素の間に「,」を入れます<br>
	 * 例： " 0.7, 2.2, 4.0"<br>
	 * @param getSumIncomeList 月毎配当額情報 文字列配列型
	 * @return Stringグラフ描画用文字列
	 */
	public String createCartData(String[] monthlyDividendIncome) {
		String result = ""; // 合成結果格納用
		result += monthlyDividendIncome[0]; // 初項を取り出す
		for(int i = 1; i < monthlyDividendIncome.length; i++) { // 配列2番目からループ開始
			result += ","; // データ間にコンマを入れる
			result += monthlyDividendIncome[i]; // データを取り出してくっつける
		}
		return result; // 合成結果を返す
	}

}
