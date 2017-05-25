/**
 * 
 */
package pers.sg.ourmap.component;

import java.util.List;

import pers.sg.ourmap.model.CooListModel;
import pers.sg.ourmap.model.TableModel;

/**
 * @Title:IReadFile
 * @Description:
 * @Personal
 * @author : SGang
 * @date : 2017年5月22日
 */
public interface IReadFile {
	public List<TableModel> ReadFileToTable(String filePath);// 读取TXT文件返回TableModel内容

	public List<CooListModel> ReadFileToCoordinate(String filePath);// 读取TXT文件返回CooListModel内容

	public List<String> ReadFileToCoordinate2(String filePath);// 读取TXT文件返回String内容
}
