package com.debug.two.server.data;/**
 * Created by Administrator on 2019/1/20.
 */

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/1/20 18:35
 **/
@Data
@ToString
public class DubboRecordResponse implements Serializable{

    private Integer code;

    private String msg;

    private Object data;
}