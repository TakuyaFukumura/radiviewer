/**
 *
 */
package com.example.Logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.example.Dto.DividendDto;

/**
 * @author fukumura
 *
 */
public class MenuLogic {
	/**
	 * ファイル内容を文字列化するメソッドです。
	 * @param uploadFile 読みこんだCSVデータ
	 * @return dividendDtoList 配当CSVを読みこんだリスト
	 */
	public List<DividendDto> fileContents(MultipartFile uploadFile) {
		CSVParser parse = null;

		List<DividendDto> dividendDtoList = new ArrayList<DividendDto>(); //格納用のリスト
		try {
			InputStream stream = uploadFile.getInputStream();
			Reader reader = new InputStreamReader(stream, "SJIS"); //参考ページ：https://dev.classmethod.jp/articles/csv_read_java_char_trans/
			BufferedReader buf = new BufferedReader(reader);

			// CSVファイルをパース
			parse = CSVFormat.EXCEL.withHeader().parse(buf);
			// レコードのリストに変換
			List<CSVRecord> recordList = parse.getRecords();
			// 各レコードを標準出力に出力＆画面表示用のリストに格納
			for (CSVRecord record : recordList) { //参考ページ：http://itref.fc2web.com/java/commons/csv.html
				DividendDto dividendDto = new DividendDto(record.get("入金日"),
						record.get("商品"), record.get("口座"),
						record.get("銘柄コード"), record.get("銘柄"),
						record.get("単価[円/現地通貨]"), record.get("数量[株/口]"),
						record.get("配当・分配金合計（税引前）[円/現地通貨]"),
						record.get("税額合計[円/現地通貨]"), record.get("受取金額[円/現地通貨]"));

				dividendDtoList.add(dividendDto); // リストに加える
			}
		} catch (IOException e) { // csv読み込み失敗時
			e.printStackTrace();
		}
		return dividendDtoList; // CSVの内容を読みこんだ一覧データを返す
	}

}
