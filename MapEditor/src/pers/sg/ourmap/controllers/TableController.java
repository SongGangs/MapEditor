/**
 * 
 */
package pers.sg.ourmap.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pers.sg.ourmap.component.IReadFile;
import pers.sg.ourmap.model.TableModel;

/**
 * @Title:TableController
 * @Description:
 * @Personal
 * @author : SGang
 * @date : 2017Äê5ÔÂ22ÈÕ
 */
@Controller
@RequestMapping("/Table")
public class TableController {

	@Autowired
	private IReadFile ReadFileImpl = null;

	@RequestMapping("/Index")
	public ModelAndView Index(HttpServletRequest request) {
		String filePath = request.getServletContext().getRealPath("") + "Resources\\tableInformation.txt";
		List<TableModel> list = ReadFileImpl.ReadFileToTable(filePath);
		return new ModelAndView("table/index", "list", list);
	}
}
