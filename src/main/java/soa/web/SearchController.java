package soa.web;

import java.util.HashMap;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SearchController {

	@Autowired
	  private ProducerTemplate producerTemplate;

	@RequestMapping("/")
    public String index() {
        return "index";
    }


    @RequestMapping(value="/search")
    @ResponseBody
    public Object search(@RequestParam("q") String q) {
    	
    	HashMap<String, Object> header = new HashMap<String,Object>();	  	
    	
    	if(q.contains("max:")){
    		
    		String [] query = q.split("max:");

    		header.put("CamelTwitterKeywords", query[0]);
    		header.put("CamelTwitterCount", query[1]);
    		
    	} else {
    		header.put("CamelTwitterKeywords", q);
    	}    	
    	
        return producerTemplate.requestBodyAndHeaders("direct:search", "", header);
    }
}