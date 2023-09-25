/**
 *
 */
package com.example.Logic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.Dto.DividendDto;

/**
 * @author fukumura
 *
 */
public class PieGraphLogic {
	/**
	 * 配当情報リストから円グラフの描画に必要な情報を取り出します
	 * @param dividendDtoList 配当情報リスト
	 * @return data 銘柄と配当額の情報
	 */
	public String[] getCartData( List<DividendDto> dividendDtoList ) {

		List<DividendDto> contents = new ArrayList<>(dividendDtoList);

		contents = exchange(contents); // 両替
		contents = grouping(contents); // グループ化
		contents = sort(contents); // 大きい順に並べ替え

		String[] data = new String[2];
		data[0] = createCartData(contents, 9); // グラフ描画用データ
		data[1] = createIssueData(contents, 8); // 銘柄データ

		return data;
	}
	/**
	 * グループ化処理<br>
	 * 同じ銘柄名の税引き後配当受取額を
	 * 合算したリストを作成する
	 * @param dividendDtoList 配当情報リスト
	 * @return groupingDtoList 銘柄毎に纏めた配当情報リスト
	 */
	public List<DividendDto> grouping( List<DividendDto> dividendDtoList ) {
		// フラグ作成
		boolean flag = false;
		// 第2リスト作成
		List<DividendDto> groupingDtoList = new ArrayList<DividendDto>();

		// 第1リスト参照ループ開始
		for(DividendDto dividendDto : dividendDtoList){
			// フラグ初期化
			flag = true;
			// 第2リスト参照ループ開始
			// TODO 分かりやすく書き直したい
			for(int i = 0; i < groupingDtoList.size(); ++i) {
				// 一致したら一致した箇所に加算してループ終了
				DividendDto groupingDto = new DividendDto();
				groupingDto = groupingDtoList.get(i);
				if(dividendDto.getIssue().equals(groupingDto.getIssue())) {
					BigDecimal sum = new BigDecimal(0);
					sum = dividendDto.getAfterTaxDividendIncome().add(groupingDto.getAfterTaxDividendIncome());
					groupingDtoList.get(i).setAfterTaxDividendIncome(sum);
					flag = false;
				}
			}
			/*
			 * 参照中の第1リストデータと第2リストが
			 * 一致しなかったら第2リストにadd
			 */
			if(flag) {
				groupingDtoList.add(dividendDto);
			}
		}
		return groupingDtoList;
	}

	/**
	 * ソート処理<br>
	 * 受取配当額を大きい順に並べ替える
	 * @param dividendDtoList 配当情報リスト
	 * @return dividendDtoList 並べ替えた配当情報リスト
	 */
	public List<DividendDto> sort( List<DividendDto> dividendDtoList ) {
		dividendDtoList.sort((a,b)-> b.getAfterTaxDividendIncome().intValue() - a.getAfterTaxDividendIncome().intValue() ); /*ラムダ式*/
		return dividendDtoList;
	}

	/**
	 * ドルは円にして、円は円のまま数値を返します
	 * @param dividendDtoList 個別配当情報
	 * @return afterTaxDividendIncome 税引き後、為替適用後配当受取額
	 */
	public List<DividendDto> exchange(List<DividendDto> dividendDtoList) {
		BigDecimal exchangeRate = new BigDecimal(100); // 為替レート

		for (DividendDto dividendDto : dividendDtoList) {
			if ("米国株式".contentEquals(dividendDto.getProduct())) {
				BigDecimal afterTaxDividendIncome = dividendDto.getAfterTaxDividendIncome();
				afterTaxDividendIncome = afterTaxDividendIncome.multiply(exchangeRate);
				dividendDto.setAfterTaxDividendIncome(afterTaxDividendIncome);
			}
		}
		return dividendDtoList;
	}

	/**
	 * 円グラフ表示用に、
	 * 配当割合の上位銘柄名を返します<br>
	 * 銘柄名は「"」で囲み、「,」で区切ります<br>
	 * 例）"a","b","c"
	 * @param groupingDtoList 配当情報リスト
	 * @return result 銘柄文字列
	 */
	public String createIssueData(List<DividendDto> groupingDtoList, int num) {

        return groupingDtoList.stream()
				.limit(num) // 指定数だけを処理する
				.map(dto -> dto != null ? dto.getIssue() : "") // 銘柄名取得
				.collect(Collectors.joining("\",\"", "\"", "\"")); // 文字列合成
	}

	/**
	 * グラフ描画用に
	 * 配当割合上位の配当額情報を文字列で返します<br>
	 * イメージ：38, 31, 21, 10, 10, 10, 10, 10
	 * @param groupingDtoList 配当情報リスト
	 * @param num 取り出す数
	 * @return result グラフ表示用数値
	 */
	public String createCartData(List<DividendDto> groupingDtoList, int num) {
		String[] monthlyDividend = new String[num];
		// TODO must be null check
		// 大きい順で配当額を取り出す
		for (int i = 0; i < monthlyDividend.length; ++i) {
			monthlyDividend[i] = groupingDtoList.get(i).getAfterTaxDividendIncome().toString();
		}
		// その他の額を計算して格納
		monthlyDividend[monthlyDividend.length - 1] = "0";
		for (int i = monthlyDividend.length - 1; i < groupingDtoList.size(); ++i) {
			BigDecimal tmp = new BigDecimal(0);
			tmp = new BigDecimal(monthlyDividend[monthlyDividend.length - 1]).add(groupingDtoList.get(i).getAfterTaxDividendIncome());
			monthlyDividend[monthlyDividend.length - 1] = tmp.toString();
		}

		return strComposition(monthlyDividend);
	}

	/**
	 * 配列で受け取った文字をコンマ区切りで合成します<br>
	 * 戻り値の例）1,2,3
	 * @param monthlyDividend 合成したい情報
	 * @return result 合成した文字列
	 */
	public String strComposition(String[] monthlyDividend) {
		return String.join(",", monthlyDividend);
	}
}
