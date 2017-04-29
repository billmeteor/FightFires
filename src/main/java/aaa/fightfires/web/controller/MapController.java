package aaa.fightfires.web.controller;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import aaa.fightfires.ServerContextListener;
import aaa.fightfires.data.AreaData;


@Controller
@RequestMapping("/map")
public class MapController {
  
  @RequestMapping(value = "/postAreaData", method = RequestMethod.POST)
  public ResponseEntity<String> postAreaData(@RequestBody AreaData areaData) {
    //AreaData areaData = ServerContextListener.getGson().fromJson(data, AreaData.class);
    
    if (areaData != null) {
       String id = areaData.getId();      
       ServerContextListener.getAreaDataMap().put(id, areaData);

       return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
    
    return new ResponseEntity<String>("ERROR", HttpStatus.OK);
  }
  
  @RequestMapping(value = "/getAreaData", method = RequestMethod.GET)
  public ResponseEntity<Collection<AreaData>> getAreaData() {	  
	/*if (ServerContextListener.getAreaDataMap().isEmpty()) {
	  return null;
	}
	return ServerContextListener.getGson().toJson(ServerContextListener.getAreaDataMap().values());*/
    return new ResponseEntity<Collection<AreaData>>(ServerContextListener.getAreaDataMap().values(), HttpStatus.OK);
  }
}
