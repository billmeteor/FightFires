package aaa.fightfires;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.google.gson.Gson;

import aaa.fightfires.data.AreaData;

public class ServerContextListener implements ServletContextListener {
	
  private static Gson gson;	
  private static Map<String, AreaData> areaDataMap;

  @Override
  public void contextDestroyed(ServletContextEvent arg0) {
    // TODO Auto-generated method stub
  }

  @Override
  public void contextInitialized(ServletContextEvent arg0) {
	gson = new Gson();
    areaDataMap = new HashMap<String, AreaData>();
  }

  public static Gson getGson() {
    return gson;
  }

  public static Map<String, AreaData> getAreaDataMap() {
    return areaDataMap;
  }

}
