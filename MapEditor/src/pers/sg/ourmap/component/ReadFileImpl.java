/**
 * 
 */
package pers.sg.ourmap.component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import pers.sg.ourmap.model.CooListModel;
import pers.sg.ourmap.model.CoordinateModel;
import pers.sg.ourmap.model.TableModel;

/**
 * @Title:ReadFileImpl
 * @Description:
 * @Personal
 * @author : SGang
 * @date : 2017年5月22日
 */
@Component
public class ReadFileImpl implements IReadFile {

	@Override
	public List<TableModel> ReadFileToTable(String filePath) {
		List<TableModel> list = new ArrayList<TableModel>();
		try {
			String encoding = "GBk";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					TableModel tableModel = SplitTableString(lineTxt);
					if (tableModel != null)
						list.add(tableModel);
					System.out.println(lineTxt);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
			return null;
		}
		return list;
	}

	@Override
	public List<CooListModel> ReadFileToCoordinate(String filePath) {
		List<CooListModel> list = new ArrayList<CooListModel>();
		try {
			String encoding = "GBk";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					CooListModel cooListModel = SplitCoordinateString(lineTxt);
					if (cooListModel != null)
						list.add(cooListModel);
					System.out.println(lineTxt);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
			return null;
		}
		return list;
	}

	/*
	 * 分离table字符串
	 */
	private TableModel SplitTableString(String str) {
		TableModel tableModel = new TableModel();
		String[] list = str.split("-")[1].split("\\*");
		if (list.length != 7)
			return null;
		tableModel.setName(list[0]);
		tableModel.setGongjian(list[1]);
		tableModel.setYiwu(list[2]);
		tableModel.setYiye(list[3]);
		tableModel.setShequ(list[4]);
		tableModel.setGoHomeNums(list[5].trim().isEmpty() ? 0 : Integer.parseInt(list[5]));
		tableModel.setGoSchoolNums(list[6].trim().isEmpty() ? 0 : Integer.parseInt(list[6]));
		return tableModel;
	}

	/*
	 * 分离Coordinate字符串
	 */
	private CooListModel SplitCoordinateString(String str) {
		CoordinateModel coordinateModel = new CoordinateModel();
		CooListModel listModel = new CooListModel();
		List<CoordinateModel> coorlist = new ArrayList<CoordinateModel>();
		String[] list = str.split("-")[0].split("\\*");
		for (int i = 0; i < list.length; i++) {
			coordinateModel.setX(list[i].split(",")[0]);
			coordinateModel.setY(list[i].split(",")[1]);
			coorlist.add(coordinateModel);
		}
		listModel.setList(coorlist);
		return listModel;
	}

	@Override
	public List<String> ReadFileToCoordinate2(String filePath) {
		List<String> list = new ArrayList<String>();
		try {
			String encoding = "GBk";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					String line = lineTxt.split("-")[0];
					if (line != null)
						list.add(line);
					System.out.println(lineTxt);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
			return null;
		}
		return list;
	}
}
