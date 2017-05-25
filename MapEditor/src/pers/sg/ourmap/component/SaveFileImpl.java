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
 * @date : 2017��5��23��
 */
@Component
public class SaveFileImpl implements ISaveFile {

	@Override
	public boolean SaveFile(String filePath, String conent) {
		BufferedWriter out = null;
		try {
			File f = new File(filePath);
			if (f.exists()) {
				System.out.print("�ļ�����");
			} else {
				System.out.print("�ļ�������");
				f.createNewFile();// �������򴴽�
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
	 * ���ļ���ָ�����ݵĵ�һ���滻Ϊ��������.
	 * 
	 * @param oldStr
	 *            ��������
	 * @param replaceStr
	 *            �滻����
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

			// �������ǰ�������
			for (int j = 1; (temp = br.readLine()) != null && !temp.contains(oldStr); j++) {
				buf = buf.append(temp);
				buf = buf.append("\n");
			}
			if (replaceStr != "") {
				// �����ݲ���
				temp = temp.replace(oldStr, replaceStr);
				buf = buf.append(temp);
				buf = buf.append("\n");
			}

			// ������к��������
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
