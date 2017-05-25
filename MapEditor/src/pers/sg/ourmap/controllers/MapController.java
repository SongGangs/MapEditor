/**
 * 
 */
package pers.sg.ourmap.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title:HomeController
 * @Description:
 * @Personal
 * @author : SGang
 * @date : 2017Äê5ÔÂ21ÈÕ
 */
@Controller
@RequestMapping("/Map")
public class MapController {
	@RequestMapping("/Index")
	public String Index() {
		return "map/index";
	}
}
