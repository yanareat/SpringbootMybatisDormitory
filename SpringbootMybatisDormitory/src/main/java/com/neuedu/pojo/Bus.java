package com.neuedu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by 12483 on 2019/8/9.
 */
@Data
public class Bus implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer Id;
    @TableField(exist = false)
    private Integer pageNo=1;
    @TableField(exist = false)
    private Integer pageSize=10;
    /* 如果withPage是0，不分页，若是1，分页*/
    @TableField(exist = false)
    private Integer withPage=1;

}
