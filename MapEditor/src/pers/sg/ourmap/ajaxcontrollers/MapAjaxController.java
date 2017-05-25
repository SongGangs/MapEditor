/**
 * 
 */
package pers.sg.ourmap.ajaxcontrollers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.sg.ourmap.component.IReadFile;
import pers.sg.ourmap.component.ISaveFile;
import pers.sg.ourmap.model.TableModel;

/**
 * @Title:MapAjaxController
 * @Description:
 * @Personal
 * @author : SGang
 * @date : 2017Äê5ÔÂ22ÈÕ
 */
@Controller
@RequestMapping("/Map")

public class MapAjaxController {

	@Autowired
	private IReadFile ReadFileImpl = null;
	@Autowired
	private ISaveFile SaveFileImpl = null;

	@ResponseBody
	@RequestMapping(value = "/QueryOverlay", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> QueryOverlay(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String filePath = request.getServletContext().getRealPath("") + "Resources\\tableInformation.txt";
		List<String> list = ReadFileImpl.ReadFileToCoordinate2(filePath);
		List<TableModel> tableModels = ReadFileImpl.ReadFileToTable(filePath);
		if (list == null) {
			map.put("msg", "error");
		} else {
			map.put("msg", "success");
		}
		map.put("ShapeSize", list.size());
		for (int i = 0; i < list.size(); i++) {
			map.put("Shape" + i, list.get(i));
			map.put("gongjian" + i, tableModels.get(i).getGongjian());
			map.put("yiwu" + i, tableModels.get(i).getYiwu());
			map.put("yiye" + i, tableModels.get(i).getYiye());
			map.put("shequ" + i, tableModels.get(i).getShequ());
			map.put("goHomeNums" + i, tableModels.get(i).getGoHomeNums());
			map.put("goSchoolNums" + i, tableModels.get(i).getGoSchoolNums());
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/SaveInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> SaveInfo(String info, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String filePath = request.getServletContext().getRealPath("") + "Resources\\tableInformation.txt";
		if (!SaveFileImpl.SaveFile(filePath, info.replace("*-", "-"))) {
			map.put("msg", "error");
		} else {
			map.put("msg", "success");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/Modify", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> Modify(String oldstr, String newstr, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String filePath = request.getServletContext().getRealPath("") + "Resources\\tableInformation.txt";
		if (!SaveFileImpl.replaceTxtByStr(filePath, oldstr, newstr)) {
			map.put("msg", "error");
		} else {
			map.put("msg", "success");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/Delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> Delete(String str, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String filePath = request.getServletContext().getRealPath("") + "Resources\\tableInformation.txt";
		if (!SaveFileImpl.replaceTxtByStr(filePath, str, "")) {
			map.put("msg", "error");
		} else {
			map.put("msg", "success");
		}
		return map;
	}
}
