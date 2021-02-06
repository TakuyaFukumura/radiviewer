/**
 *
 */
package com.example.Logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.example.Dto.DividendDto;

/**
 * @author fukumura
 *
 */
public class SampleLogic {

	/**
	 * 内部ファイルデータを読みこむメソッドです。
	 * @return dividendDtoList 配当CSVを読みこんだリスト
	 */
	public List<DividendDto> readInternalFile(String filename) {
		CSVParser parse = null;

		List<DividendDto> dividendDtoList = new ArrayList<DividendDto>(); //格納用のリスト
		Path csv = Paths.get("src/main/resources/public/csv/" + filename);

		try {
			BufferedReader buf = Files.newBufferedReader(csv, Charset.forName("SJIS"));

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
		} catch (IOException e) {
			e.printStackTrace();
		}

		return dividendDtoList;
	}
}
