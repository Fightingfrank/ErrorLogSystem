package com.errorLogSystem.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.errorLogSystem.model.ErrorObject;

public class ListComparator{

	public static List<ErrorObject> sortList(List<ErrorObject> errorObjectList, int flag){
		// 从大到小
		if(flag == 0){
			
			Comparator c = new Comparator<ErrorObject>(){

				@Override
				public int compare(ErrorObject o1, ErrorObject o2) {
					if(Integer.valueOf(o1.getNum()) > Integer.valueOf(o2.getNum())){
						return 1;
					}else if(Integer.valueOf(o1.getNum()) == Integer.valueOf(o2.getNum())){
						return -1;
					}else {
						return -1;
					}
				}
				
			};
			
			Collections.sort(errorObjectList, c);
		}else if(flag == 1){  //从小到大
			Comparator c = new Comparator<ErrorObject>(){

				@Override
				public int compare(ErrorObject o1, ErrorObject o2) {
					if(Integer.valueOf(o1.getNum()) < Integer.valueOf(o2.getNum())){
						return 1;
					}else if(Integer.valueOf(o1.getNum()) == Integer.valueOf(o2.getNum())){
						return -1;
					}else {
						return -1;
					}
				}
				
			};
			
			Collections.sort(errorObjectList, c);
		}
		return errorObjectList;
	}
}
