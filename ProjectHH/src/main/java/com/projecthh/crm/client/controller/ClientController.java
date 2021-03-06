package com.projecthh.crm.client.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.projecthh.common.CommandMap;
import com.projecthh.common.PageMaker;
import com.projecthh.crm.client.service.ClientService;
import com.projecthh.crm.client.vo.ClientVO;

@Controller
public class ClientController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "clientService")
	private ClientService clientService;
	
	@RequestMapping(value = "/clientList")
	public String selectClientList() throws Exception {	
		return "/crm/CLIENT/CLILIS01";
	}
	
	//전체리스트, 검색
	@ResponseBody
	@RequestMapping(value = "/clientSearch")
	public Map<String, Object> selectClientList(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("category", request.getParameter("category"));
		map.put("fromDate", request.getParameter("fromDate"));
		map.put("toDate", request.getParameter("toDate"));
		map.put("search", request.getParameter("search"));
			
		int startPage = 0;
		int page = 1;
		if (request.getParameter("page") != "") {
			page = Integer.parseInt(request.getParameter("page"));
			startPage = (page - 1) * 10;
		}
		map.put("page", startPage);
		
		ArrayList<ClientVO> search  = clientService.searchClient(map);
		
		PageMaker pm = new PageMaker();	
		pm.setTotalCount(clientService.searchClientCount(map), page);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("searchList", search);
		resultMap.put("page", pm);
		return resultMap;	
	}
	
	@RequestMapping(value = "/clientDetail")
	public String selectClient(@RequestParam("no") String no, Model model, ClientVO vo) throws Exception {	
		vo = clientService.selectClient(no);
		model.addAttribute("vo", vo);
		return "/crm/CLIENT/CLIDET01";
	}
	
	@RequestMapping(value = "/clientUpdate")
	public String updateClient(CommandMap commandMap) throws Exception {
		clientService.updateClient(commandMap.getMap());
		return "redirect:/clientDetail?no="+commandMap.get("client_no");
	}
	
	@RequestMapping(value = "/clientDelete")
	public String deleteClient(@RequestParam("no") String no) throws Exception {
		clientService.deleteClient(no);
		return "redirect:/clientList";
	}
	
	@RequestMapping(value = "/clientInsertForm")
	public String insertClient() throws Exception {	
		return "/crm/CLIENT/CLIINS01";
	}
	
	@RequestMapping(value = "/clientInsert")
	public String insertClient(CommandMap commandMap) throws Exception {
		clientService.insertClient(commandMap.getMap());
		return "redirect:/clientDetail?no=" + commandMap.get("client_no");
	}
	
	//사업자번호 중복검색
	@ResponseBody
	@RequestMapping(value = "/businessNoSearch")
	public Map<String, Object> searchBusinessNo(@RequestParam("business_no") String bno) throws Exception {
		String no  = clientService.selectNo(bno);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("no", no);
		return resultMap;	
	}
	
	@RequestMapping(value = "elogin")
	public String userLogin() throws Exception {
		return "/crm/CLIENT/EMPLOG01";
	}
	
	@RequestMapping(value = "employlogin")
	public ModelAndView login(HttpServletRequest request, ClientVO vo, HttpSession session) throws Exception {
		
		if (request.getParameter("id") != null) {
			vo.setId(request.getParameter("id"));
			vo.setPw(request.getParameter("pw"));
		}
		vo = clientService.login(vo);
		if (vo != null) {
			session.setAttribute("employee_code", vo.getEmployee_code());
			// 로그인 성공
			return new ModelAndView("redirect:clientList");
		}
			// 로그인 실패
			return new ModelAndView("redirect:elogin");
	}
	
	// 로그아웃
		@RequestMapping(value = "/elogout")
		public ModelAndView logout(HttpSession session, HttpServletRequest request) throws Exception {
			session = request.getSession();
			if (session.getAttribute("employee_code") != null) {
				session.removeAttribute("employee_code");
			}
			return new ModelAndView("redirect:elogin");
		}
}