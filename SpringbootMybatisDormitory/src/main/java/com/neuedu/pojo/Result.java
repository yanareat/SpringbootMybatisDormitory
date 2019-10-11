package com.neuedu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 12483 on 2019/8/12.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    /*返回状态码，0成功，1失败*/
    private Integer code;
    /*返回值*/
    private Object obj;
    /*返回文字消息*/
    private String txt;

}
