/**
 * 
 */
package pers.sg.ourmap.component;

/**
 * @Title:ISaveFile
 * @Description:
 * @Personal
 * @author : SGang
 * @date : 2017��5��23��
 */
public interface ISaveFile {
	public boolean SaveFile(String filePath, String content);

	public boolean replaceTxtByStr(String filepath, String oldStr, String replaceStr);
}
