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
 * @date : 2017��5��22��
 */
public interface IReadFile {
	public List<TableModel> ReadFileToTable(String filePath);// ��ȡTXT�ļ�����TableModel����

	public List<CooListModel> ReadFileToCoordinate(String filePath);// ��ȡTXT�ļ�����CooListModel����

	public List<String> ReadFileToCoordinate2(String filePath);// ��ȡTXT�ļ�����String����
}
