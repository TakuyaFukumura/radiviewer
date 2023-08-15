/**
 *
 */
package com.example.Logic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.Dto.DividendDto;

/**
 * @author fukumura
 *
 */
public class LineGraphLogic {
	/**
	 * 配当情報リストから円グラフの描画に必要な情報を取り出します
	 * @param dividendDtoList 配当情報リスト
	 * @return data 配当額の情報
	 */
	public String getCartData( List<DividendDto> dividendDtoList, String[] year ) {
		List<DividendDto> contents = new ArrayList<>();
		List<BigDecimal[]> dataList = new ArrayList<>();

		for(DividendDto dividendDto : dividendDtoList){
			DividendDto tmpDividendDto = new DividendDto(); // newが必要
			tmpDividendDto.setAll(dividendDto.getAll());
			contents.add(tmpDividendDto);
		} // sessionの値が書き換わる問題の回避策として追加

		contents = exchange(contents); //ドルを円に変換

		BigDecimal[] firstCartData = createCartData( contents, year[0] ); // グラフ描画用データ
		dataList.add(firstCartData); // 1つ目のデータを追加

		for(int i = 1; i < year.length; i++) { //2つ目移行のデータを追加
			BigDecimal[] nextCartData = createCartData( contents, year[i] );//year[i] ); // グラフ描画用データ
			dataList.add(nextCartData);
		}
		String data = strComposition(createCumulativeList(dataList));

		return data;
	}

	/**
	 * 累計額のリストを作成して返す
	 * @param dataList 毎月の配当データ入り年別の配列のリスト
	 * @return result 累計受取額のリスト
	 */
	public List<BigDecimal> createCumulativeList(List<BigDecimal[]> dataList ) {
		List<BigDecimal> result = new ArrayList<>();
		BigDecimal sum = new BigDecimal("0"); // 累計額
		// 累計になるように合成して返す
		for(BigDecimal[] dataArray : dataList){
            for (BigDecimal monthlyIncome : dataArray) { // 各月の配当額
				sum = sum.add(monthlyIncome); //累計額を更新
                result.add(sum); // リストに加える
            }
		}
		return result;
	}

	/**
	 * 月ごとの配当受取額を配列で取得します
	 * @param dividendDtoList 配当情報リスト
	 * @param year 情報を得たい年
	 * @return 月別配当受取額情報
	 */
	public BigDecimal[] createCartData( List<DividendDto> dividendDtoList, String year ) {

		String paymentDay = ""; // 配当受取日 例）2020/11/22
		int month = 0; // 配当受取月
		BigDecimal[] cartData = new BigDecimal[12]; // 月毎の配当受取額格納用
		Arrays.fill(cartData, new BigDecimal("0")); // 配列の初期化

		for(DividendDto dividendDto : dividendDtoList){

			paymentDay = dividendDto.getPaymentDay(true); // 配当受取日情報取得
			String[] splitDay = paymentDay.split("/", 0); // スラッシュで分割して格納
			month = Integer.parseInt(splitDay[1]); // 受取月をint型に変換
			BigDecimal afterTaxDividendIncome = dividendDto.getAfterTaxDividendIncome();

			if(year.equals(splitDay[0])) { // 指定されている年と一致したら
				switch (month){ // 配当受取月で分岐
				  case 1:
					  cartData[0] = cartData[0].add(afterTaxDividendIncome);
					  break;
				  case 2:
					  cartData[1] = cartData[1].add(afterTaxDividendIncome);
					  break;
				  case 3:
					  cartData[2] = cartData[2].add(afterTaxDividendIncome);
					  break;
				  case 4:
					  cartData[3] = cartData[3].add(afterTaxDividendIncome);
					  break;
				  case 5:
					  cartData[4] = cartData[4].add(afterTaxDividendIncome);
					  break;
				  case 6:
					  cartData[5] = cartData[5].add(afterTaxDividendIncome);
					  break;
				  case 7:
					  cartData[6] = cartData[6].add(afterTaxDividendIncome);
					  break;
				  case 8:
					  cartData[7] = cartData[7].add(afterTaxDividendIncome);
					  break;
				  case 9:
					  cartData[8] = cartData[8].add(afterTaxDividendIncome);
					  break;
				  case 10:
					  cartData[9] = cartData[9].add(afterTaxDividendIncome);
					  break;
				  case 11:
					  cartData[10] = cartData[10].add(afterTaxDividendIncome);
					  break;
				  case 12:
					  cartData[11] = cartData[11].add(afterTaxDividendIncome);
					  break;
				  default:
					    System.out.println("[ERROR]：LineGraphLogic.createCartData");
				}
			}
		}
		return cartData; // 指定された年の月毎配当受取額情報
	}

	/**
	 * リストで受け取った情報をコンマ区切りで文字列に合成します<br>
	 * 戻り値の例）1,2,3,4,5
	 * @param cartData 合成したい情報
	 * @return result 合成した文字列
	 */
	public String strComposition( List<BigDecimal> cartData ) {
		String result = "";
		result += cartData.get(0).toString();
		for(int i = 1; i < cartData.size(); i++) {
			result += ",";
			result += cartData.get(i).toString();
		}
		return result;
	}

	/**
	 * ドルは円にして、円は円のまま数値を返します
	 * @param dividendDtoList 配当情報リスト
	 * @return afterTaxDividendIncome 税引き後、為替適用後配当受取額
	 */
	public List<DividendDto> exchange(List<DividendDto> dividendDtoList) {
		BigDecimal exchangeRate = new BigDecimal(100); // 為替レート TODO:将来的には設定で変更できるようにしたい

        for (DividendDto dividendDto : dividendDtoList) {
            if ("米国株式".contentEquals(dividendDto.getProduct())) {
				BigDecimal afterTaxDividendIncome = dividendDto.getAfterTaxDividendIncome();
				BigDecimal yenIncome = afterTaxDividendIncome.multiply(exchangeRate); // 掛け算
				dividendDto.setAfterTaxDividendIncome(yenIncome);
            }
        }
		return dividendDtoList;
	}

}
