/**
 * 
 */
package pers.sg.ourmap.component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.springframework.stereotype.Component;

/**
 * @Title:SaveFileImpl
 * @Description:
 * @Personal
 * @author : SGang
 * @date : 2017年5月23日
 */
@Component
public class SaveFileImpl implements ISaveFile {

	@Override
	public boolean SaveFile(String filePath, String conent) {
		BufferedWriter out = null;
		try {
			File f = new File(filePath);
			if (f.exists()) {
				System.out.print("文件存在");
			} else {
				System.out.print("文件不存在");
				f.createNewFile();// 不存在则创建
			}
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true), "GBK"));
			out.write(conent + "\n");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	/**
	 * 将文件中指定内容的第一行替换为其它内容.
	 * 
	 * @param oldStr
	 *            查找内容
	 * @param replaceStr
	 *            替换内容
	 */
	public boolean replaceTxtByStr(String filepath, String oldStr, String replaceStr) {
		String temp = "";
		try {
			File file = new File(filepath);
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis, "GBk");
			BufferedReader br = new BufferedReader(isr);
			StringBuffer buf = new StringBuffer();
			BufferedWriter out = null;

			// 保存该行前面的内容
			for (int j = 1; (temp = br.readLine()) != null && !temp.contains(oldStr); j++) {
				buf = buf.append(temp);
				buf = buf.append("\n");
			}
			if (replaceStr != "") {
				// 将内容插入
				temp = temp.replace(oldStr, replaceStr);
				buf = buf.append(temp);
				buf = buf.append("\n");
			}

			// 保存该行后面的内容
			while ((temp = br.readLine()) != null) {
				buf = buf.append(temp);
				buf = buf.append("\n");
			}
			br.close();
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath, false), "GBK"));
			out.write(buf.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
