package com.debug.two.server.controller;

import com.debug.mooc.dubbo.one.api.response.BaseResponse;
import com.debug.mooc.dubbo.one.api.service.IDubboItemService;
import com.debug.two.server.data.DubboRecordResponse;
import com.debug.two.server.data.ItemVO;
import com.debug.two.server.service.ItemService;
import com.google.common.collect.Maps;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @sum ：
 * @description：
 * @date ：Created in 2019/7/6
 */
@RestController
public class ItemController {

  private static final Logger log= LoggerFactory.getLogger(ItemController.class);

  private static final String prefix="item";

  @Autowired
  private IDubboItemService dubboItemService;

  @Autowired
  ItemService itemService;

  /**
   * 用户商城列表查询 更具编码或者名称
   * @return
   */
  @RequestMapping(value = prefix+"/httpList",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Map<String,Object> httpList(@RequestBody ItemVO itemVO){
    Map<String,Object> resMap= Maps.newHashMap();
    resMap.put("code","0");
    resMap.put("msg","成功");

    try {

      DubboRecordResponse response = itemService.pushOrderV2(itemVO);
      if (response!=null && response.getCode().equals(0)){
        resMap.put("data",response.getData());

      }
    }catch (Exception e){
      e.printStackTrace();
      resMap.put("code","-1");
      resMap.put("msg","失败");
    }
    return resMap;
  }


  /**
   * 用户商城列表查询
   * @return
   */
  @RequestMapping(value = prefix+"/list",method = RequestMethod.GET)
  public Map<String,Object> list(){
    Map<String,Object> resMap= Maps.newHashMap();
    resMap.put("code","0");
    resMap.put("msg","成功");

    //TODO:调用服务提供方dubboOne提供的列表查询功能
    try {
      BaseResponse response=dubboItemService.listItems();
      if (response!=null && response.getCode().equals(0)){
        resMap.put("data",response.getData());

      }
    }catch (Exception e){
      e.printStackTrace();
      resMap.put("code","-1");
      resMap.put("msg","失败");
    }
    return resMap;
  }

  /**
   * 用户商城列表查询-分页查询
   * @return
   */
  @RequestMapping(value = prefix+"/page/list",method = RequestMethod.GET)
  public Map<String,Object> pageList(Integer pageNo,Integer pageSize){
    if (pageNo==null || pageSize==null || pageNo<=0 || pageSize<=0){
      pageNo=1;
      pageSize=2;
    }

    Map<String,Object> resMap= Maps.newHashMap();
    resMap.put("code","0");
    resMap.put("msg","成功");

    //TODO:调用服务提供方dubboOne提供的列表查询-分页查询功能
    try {
      BaseResponse response=dubboItemService.listPageItems(pageNo,pageSize);
      if (response!=null && response.getCode().equals(0)){
        resMap.put("data",response.getData());

      }
    }catch (Exception e){
      e.printStackTrace();
      resMap.put("code","-1");
      resMap.put("msg","失败");
    }
    return resMap;
  }

}
