package com.secret.sdk.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class ArrayUtil {

	
	/**
	 * 用于找出2个数组的里的字符串 差集,  以arr1 为主集,如果arr2里有,arr1里没,那么  arr1没有的字符串就会打印出来
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static List minusString(String[] arr1, String[] arr2) {
		LinkedList<String> list = new LinkedList<String>();
		LinkedList<String> history = new LinkedList<String>();
		String[] firstArr = arr1;
		String[] secondArr = arr2;

		for (String str : firstArr) {
			if (!list.contains(str)) {
				list.add(str);
			}
		}
		for (String str : secondArr) {
			if (list.contains(str)) {
				history.add(str);
				list.remove(str);
			}
		}
		return list;
	}

	
	public static void main(String[] args){


		String[] a = {


				"HCT",
				"BYB",
				"AIRDROP",
				"THBY",
				"BUKY",
				"YTT",
				"EOS",
				"PSY",
				"USDY",
				"RNT",
				"OMG",
				"INE",
				"YXL",
				"LTC",
				"CCKK",
				"QKL",
				"ITW",
				"BTKB",
				"USDT",
				"ESM",
				"ETH",
				"BCH",
				"NYTSC",
				"BCB",
				"HAND",
				"ETC",
				"EOSDAC",
				"ESA",
				"BTC",
				"USDX",
				"JITT",
				"POK",
				"RHINO",
				"CTXC",
				"VEN",
				"SOC",
				"XRP",
				"HOC",
				"DASH",
				"AMT",
				"HB",
				"XT",
				"MSCE",
				"ZIL",
				"IOST",
				"ICX"


		};



		String[] b = {

				"BCB",
				"ETH",
				"BTC",
				"USDX",
				"ETC",
				"LTC",
				"DC",
				"EOS",
				"OMG",
				"EOSDAC",
				"USDT",
				"BCH",
				"IOST",
				"XRP",
				"ZIL",
				"CTXC",
				"SOC",
				"VEN",
				"ICX",
				"DASH",
				"XT",
				"HB",
				"HOC",
				"MSCE",
				"AMT",
				"ESM",
				"QKL",
				"BTKB",
				"YTT",
				"YXL",
				"RNT",
				"POK",
				"HAND",
				"HPT",
				"RHINO",
				"MKC",
				"QC",
				"JITT",
				"NYTSC",
				"ESA",
				"INE",
				"ITW",
				"CCKK",
				"AIRDROP",
				"HCT",
				"BYB",
				"USDY",
				"PSY",
				"THBY",
				"BUKY"


		};


		List list = minusString(b, a);
		System.out.println(list.toString());




	}
	
	
}
