/**
 *
 */
package com.example.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.Dto.DividendDto;

/**
 * @author fukumura
 *
 */
@Controller
@RequestMapping("/result")
public class ResultController {

	/*
	 * 参考ページ
	 * https://qiita.com/nvtomo1029/items/316c5e8fe5d0cd92339c
	 * https://qiita.com/misskabu/items/81fa2c774f92c63125b5
	 */
	@PostMapping
	public String index(@RequestParam("csv_file")MultipartFile csv_file, Map<String, Object> model) {
		if(csv_file != null) {
			List<DividendDto> contents = this.fileContents(csv_file);
			model.put("contents", contents);
		}
		return "result";
	}

    /*
     * ファイル内容を文字列化するメソッドです。
     */
	private List<DividendDto> fileContents(MultipartFile uploadFile) {
        CSVParser parse = null;

        List<DividendDto> dividendDtoList = new ArrayList<DividendDto>();
        try {
            InputStream stream = uploadFile.getInputStream();
            Reader reader = new InputStreamReader(stream,"SJIS"); //参考ページ：https://dev.classmethod.jp/articles/csv_read_java_char_trans/
            BufferedReader buf= new BufferedReader(reader);

            // CSVファイルをパース
            parse = CSVFormat.EXCEL.withHeader().parse(buf);
            // レコードのリストに変換
            List<CSVRecord> recordList = parse.getRecords();
            // 各レコードを標準出力に出力＆画面表示用のリストに格納
            for (CSVRecord record : recordList) { //参考ページ：http://itref.fc2web.com/java/commons/csv.html
            	DividendDto dividendDto = new DividendDto(record.get("入金日"),
            			record.get("商品"),record.get("口座"),
            			record.get("銘柄コード"),record.get("銘柄"),
            			record.get("単価[円/現地通貨]"),record.get("数量[株/口]"),
            			record.get("配当・分配金合計（税引前）[円/現地通貨]"),
            			record.get("税額合計[円/現地通貨]"),record.get("受取金額[円/現地通貨]"));

                dividendDtoList.add(dividendDto);
            }

        } catch (IOException e) { // csv読み込み失敗時
            e.printStackTrace();
        }
        return dividendDtoList;
    }

}
