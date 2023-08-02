// Jai Swaminarayan KESHAV , Swami-Shreeji
package com.billrepository.utilities;

import java.io.FileOutputStream;
import java.io.InputStream;

public class ImageSaver {
	public static boolean saveToServer(InputStream in,String imgName) {
		boolean flag = false;
		try {
			byte[] b = new byte[100];
			FileOutputStream fout = new FileOutputStream("C:\\JDBC\\BillRepositorySystem\\src\\main\\webapp\\img\\"+imgName+".jpg");
			while(in.read(b) != -1) {
				fout.write(b);
			}
			flag = true;
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
