/**
 *
 */
package com.example.Dto;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 入金日,	"2021/01/07"	日付型	Date
 * 商品,	"米国株式",	文字型10文字	String
 * 口座,	"NISA",	文字型8文字	String
 * 銘柄コード,	"HYEM",	文字5文字	String
 * 銘柄,	"VV EM HY BOND",	文字50文字	String
 * 単価[円/現地通貨],	"0.1139",	数値小数点以下5桁うえ7桁	BigDecimal
 * 数量[株/口],	"1",	数値10桁	BigDecimal
 * 配当・分配金合計（税引前）[円/現地通貨],	"0.11",	数値しも2桁うえ10桁	BigDecimal
 * 税額合計[円/現地通貨],	"0",	数値しも2桁うえ10桁	BigDecimal
 * 受取金額[円/現地通貨]	,"0.10"	数値しも2桁うえ10桁	BigDecimal
 */

/**
 * @author fukumura
 *
 */
public class DividendDto {
	private Date paymentDay;
	private String product;
	private String account;
	private String issueCode;
	private String issue;
	private BigDecimal unitPrice;
	private BigDecimal unit;
	private BigDecimal beforeTaxDividendIncome;
	private BigDecimal tax;
	private BigDecimal afterTaxDividendIncome;

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

	public DividendDto() {

	}
	/**
	 * @param paymentDay
	 * @param product
	 * @param account
	 * @param issueCode
	 * @param issue
	 * @param unitPrice
	 * @param unit
	 * @param beforeTaxDividendIncome
	 * @param tax
	 * @param afterTaxDividendIncome
	 */
	public DividendDto(Date paymentDay, String product,
			String account, String issueCode, String issue,
			BigDecimal unitPrice, BigDecimal unit,
			BigDecimal beforeTaxDividendIncome, BigDecimal tax,
			BigDecimal afterTaxDividendIncome) {
		super();
		this.paymentDay = paymentDay;
		this.product = product;
		this.account = account;
		this.issueCode = issueCode;
		this.issue = issue;
		this.unitPrice = unitPrice;
		this.unit = unit;
		this.beforeTaxDividendIncome = beforeTaxDividendIncome;
		this.tax = tax;
		this.afterTaxDividendIncome = afterTaxDividendIncome;
	}
	/**
	 * @param paymentDay
	 * @param product
	 * @param account
	 * @param issueCode
	 * @param issue
	 * @param unitPrice
	 * @param unit
	 * @param beforeTaxDividendIncome
	 * @param tax
	 * @param afterTaxDividendIncome
	 */
	public DividendDto(String paymentDay, String product, String account, String issueCode, String issue,
			String unitPrice, String unit, String beforeTaxDividendIncome, String tax,
			String afterTaxDividendIncome) {
		super();
		try {
			this.paymentDay = dateFormat.parse(paymentDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.product = product;
		this.account = account;
		this.issueCode = issueCode;
		this.issue = issue;
		this.unitPrice = convStrToBigDecimal(unitPrice);
		this.unit = convStrToBigDecimal(unit);
		this.beforeTaxDividendIncome = convStrToBigDecimal(beforeTaxDividendIncome);
		this.tax = convStrToBigDecimal(tax);
		this.afterTaxDividendIncome = convStrToBigDecimal(afterTaxDividendIncome);
	}
	/**
	 * @return paymentDay
	 */
	public Date getPaymentDay() {
		return paymentDay;
	}
	/**
	 * フォーマットを適用して文字列型で返す<br>
	 * true "yyyy/MM/dd"<br>
	 * false "yyyy-MM-dd"
	 * @param flag
	 * @return paymentDay
	 */
	public String getPaymentDay(boolean flag) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = "";
		if(flag) {
			dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			strDate = dateFormat.format(paymentDay);
		}else {
			strDate = dateFormat.format(paymentDay);
		}
		return strDate;
	}
	/**
	 * @param paymentDay セットする paymentDay
	 */
	public void setPaymentDay(Date paymentDay) {
		this.paymentDay = paymentDay;
	}
	/**
	 * @param paymentDay セットする paymentDay
	 */
	public void setPaymentDay(String paymentDay) {
		try {
			this.paymentDay = dateFormat.parse(paymentDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @return product
	 */
	public String getProduct() {
		return product;
	}
	/**
	 * @param product セットする product
	 */
	public void setProduct(String product) {
		this.product = product;
	}
	/**
	 * @return account
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * @param account セットする account
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * @return issueCode
	 */
	public String getIssueCode() {
		return issueCode;
	}
	/**
	 * @param issueCode セットする issueCode
	 */
	public void setIssueCode(String issueCode) {
		this.issueCode = issueCode;
	}
	/**
	 * @return issue
	 */
	public String getIssue() {
		return issue;
	}
	/**
	 * @param issue セットする issue
	 */
	public void setIssue(String issue) {
		this.issue = issue;
	}
	/**
	 * @return unitPrice
	 */
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	/**
	 * @param unitPrice セットする unitPrice
	 */
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**
	 * @param unitPrice セットする unitPrice
	 */
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = convStrToBigDecimal(unitPrice);
	}
	/**
	 * @return unit
	 */
	public BigDecimal getUnit() {
		return unit;
	}
	/**
	 * @param unit セットする unit
	 */
	public void setUnit(BigDecimal unit) {
		this.unit = unit;
	}
	/**
	 * @param unit セットする unit
	 */
	public void setUnit(String unit) {
		this.unit = convStrToBigDecimal(unit);
	}
	/**
	 * @return beforeTaxDividendIncome
	 */
	public BigDecimal getBeforeTaxDividendIncome() {
		return beforeTaxDividendIncome;
	}
	/**
	 * @param beforeTaxDividendIncome セットする beforeTaxDividendIncome
	 */
	public void setBeforeTaxDividendIncome(BigDecimal beforeTaxDividendIncome) {
		this.beforeTaxDividendIncome = beforeTaxDividendIncome;
	}
	/**
	 * @param beforeTaxDividendIncome セットする beforeTaxDividendIncome
	 */
	public void setBeforeTaxDividendIncome(String beforeTaxDividendIncome) {
		this.beforeTaxDividendIncome = convStrToBigDecimal(beforeTaxDividendIncome);
	}
	/**
	 * @return tax
	 */
	public BigDecimal getTax() {
		return tax;
	}
	/**
	 * @param tax セットする tax
	 */
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	/**
	 * @param tax セットする tax
	 */
	public void setTax(String tax) {
		this.tax = convStrToBigDecimal(tax);
	}
	/**
	 * @return afterTaxDividendIncome
	 */
	public BigDecimal getAfterTaxDividendIncome() {
		return afterTaxDividendIncome;
	}
	/**
	 * @param afterTaxDividendIncome セットする afterTaxDividendIncome
	 */
	public void setAfterTaxDividendIncome(BigDecimal afterTaxDividendIncome) {
		this.afterTaxDividendIncome = afterTaxDividendIncome;
	}
	/**
	 * @param afterTaxDividendIncome セットする afterTaxDividendIncome
	 */
	public void setAfterTaxDividendIncome(String afterTaxDividendIncome) {
		this.afterTaxDividendIncome = convStrToBigDecimal(afterTaxDividendIncome);
	}

	/*
	 * 文字列で表された数値をBigDecimal型に変換する
	 */
	public BigDecimal convStrToBigDecimal(String str) {
		BigDecimal tmpNum = new BigDecimal(0);
		DecimalFormat decimalFormat = new DecimalFormat();
		decimalFormat.setParseBigDecimal(true);

		if( !str.isEmpty() ) {
			try {
				tmpNum = (BigDecimal) decimalFormat.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return tmpNum;
	}


}
/*内容の例
 * 入金日,商品,口座,銘柄コード,銘柄,単価[円/現地通貨],数量[株/口],配当・分配金合計（税引前）[円/現地通貨],税額合計[円/現地通貨],受取金額[円/現地通貨]
"2021/01/07","米国株式","NISA","HYEM","VV EM HY BOND","0.1139","1","0.11","0","0.10"
"2021/01/04","投資信託","特定","","ブラジル株式ツインαファンド(毎月分配型)ツインα・コース","20.00","3,683","7","0","7"
 */
