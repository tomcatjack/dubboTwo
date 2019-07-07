package com.debug.two.server.service;

import com.debug.two.server.data.DubboRecordResponse;
import com.debug.two.server.data.ItemVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @sum ：
 * @description：
 * @date ：Created in 2019/7/7
 */
@Service
public class ItemService {

  private static final Logger log= LoggerFactory.getLogger(ItemService.class);

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private HttpService httpService;


  private static final String url="http://127.0.0.1:9013/v1/moocOne/item/httpList";

  private OkHttpClient httpClient=new OkHttpClient();

  /**
   * 处理controller层过来的用户下单数据-采用通用化http服务类实战
   * @param pushRequest
   */
  public DubboRecordResponse pushOrderV2(ItemVO pushRequest) throws Exception{
    try {
      Map<String,String> headerMap=new HashMap<>();
      headerMap.put("Content-Type","application/json");
      String res=httpService.post(url,headerMap,"application/json"
          ,objectMapper.writeValueAsString(pushRequest));
      log.info("响应结果：{} ",res);

    //TODO:对象解析-更加通用-数据字段比较多/复杂的解析
      DubboRecordResponse dubboRecordResponse=objectMapper.readValue(res,DubboRecordResponse.class);
      log.info("得到的响应解析结果：{} ",dubboRecordResponse);
      if (dubboRecordResponse.getCode().equals(0)){
        //TODO:属于自己的业务逻辑....
          return dubboRecordResponse;
      }
    }catch (Exception e){
      throw e;
    }
    return null;
  }

}
