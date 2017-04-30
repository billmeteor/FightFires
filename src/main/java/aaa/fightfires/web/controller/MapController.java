package aaa.fightfires.web.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Collection;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import aaa.fightfires.HttpsTrustManager;
import aaa.fightfires.ServerContextListener;
import aaa.fightfires.data.AreaData;


@Controller
@RequestMapping("/map")
public class MapController {
  
  @RequestMapping(value = "/postAreaData", method = RequestMethod.POST)
  public ResponseEntity<String> postAreaData(@RequestBody AreaData areaData) {
    if (areaData != null) {
       String id = areaData.getId();
       ServerContextListener.getAreaDataMap().put(id, areaData);

       return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
    
    return new ResponseEntity<String>("ERROR", HttpStatus.OK);
  }
  
  @RequestMapping(value = "/getAreaData", method = RequestMethod.GET)
  public ResponseEntity<Collection<AreaData>> getAreaData() {
    return new ResponseEntity<Collection<AreaData>>(ServerContextListener.getAreaDataMap().values(), HttpStatus.OK);
  }
  
  @RequestMapping(value = "/getFireData", method = RequestMethod.POST)
  public ResponseEntity<String> getFireData() throws IOException, KeyManagementException, NoSuchAlgorithmException {
    CloseableHttpClient httpClient = getHttpsClient();
    HttpGet httpGetRequest = new HttpGet("http://firms.modaps.eosdis.nasa.gov/active_fire/c6/text/MODIS_C6_SouthEast_Asia_24h.csv");
    CloseableHttpResponse httpResponse = httpClient.execute(httpGetRequest);
    StringBuilder stringBuilder = new StringBuilder();
    
    try {
      HttpEntity entity = httpResponse.getEntity();

      byte[] buffer = new byte[1024];
      if (entity != null) {
        InputStream inputStream = entity.getContent();
        try {
          int bytesRead = 0;
          BufferedInputStream bis = new BufferedInputStream(inputStream);
          while ((bytesRead = bis.read(buffer)) != -1) {
            String chunk = new String(buffer, 0, bytesRead);
            stringBuilder.append(chunk);
          }
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          try { inputStream.close(); } catch (Exception ignore) {}
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      httpResponse.close();
      httpClient.close();
    }
     
    return new ResponseEntity<String>(stringBuilder.toString(), HttpStatus.OK);
  }
 
  @SuppressWarnings("deprecation")
  private CloseableHttpClient getHttpsClient() throws KeyManagementException, NoSuchAlgorithmException {
    SSLContext sslcontext = SSLContexts.custom().build();
    sslcontext.init(null, new X509TrustManager[]{new HttpsTrustManager()}, new SecureRandom());
    SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(sslcontext,
            SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

    return HttpClients.custom().setSSLSocketFactory(factory).build();
  }
}
