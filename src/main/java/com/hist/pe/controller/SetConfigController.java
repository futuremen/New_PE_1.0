package com.hist.pe.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hist.pe.utils.ReadConfig;

@Controller
public class SetConfigController {
	
	
	@RequestMapping(value = "setConfig")
	public String setConfig() {
		return "config/setConfig";
	}
	
	
	@RequestMapping(value = "viewConfig")
	public String viewConfig(ModelMap map) {
		
		String sunScore = ReadConfig.getString("sunScore");
		String onlineScore = ReadConfig.getString("onlineScore");
		String testScore = ReadConfig.getString("testScore");
		
		map.addAttribute("sunScore", sunScore);
		map.addAttribute("onlineScore", onlineScore);
		map.addAttribute("testScore", testScore);
		
		
		
		return "config/viewConfig";
	}
	
	@RequestMapping(value = "setConfig",method=RequestMethod.POST)
	public String setConfigPost(String onlineScore,String sunScore,String testScore) throws IOException {
		
		ReadConfig rc = new ReadConfig();
		
		rc.setString("sunScore", sunScore);
		rc.setString("onlineScore", onlineScore);
		rc.setString("testScore", testScore);
		
		
		return "config/setConfig";
	}
	

}
