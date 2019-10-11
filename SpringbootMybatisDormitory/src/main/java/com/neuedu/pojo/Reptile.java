package com.neuedu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.neuedu.pojo.Bus;
import java.time.LocalDate;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangyujie
 * @since 2019-09-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Reptile extends Bus {

    private static final long serialVersionUID = 1L;

//    @TableId(value = "Id", type = IdType.AUTO)
//    private Integer Id;

    @TableField("Url")
    private String Url;

    @TableField("Title")
    private String Title;

    @TableField("Time")
    private LocalDate Time;

    @TableField("AddTime")
    private LocalDate AddTime;

    @TableField("Author")
    private String Author;

    @TableField("Abstract")
    private String Abstract;

    @TableField("Content")
    private String Content;

    @TableField("ImageUrls")
    private String ImageUrls;

    @TableField("ImagePaths")
    private String ImagePaths;

    @TableField(exist=false)
    private Date beginTime=null;

    @TableField(exist=false)//time
    private Date endTime=null;

    @TableField(exist=false)
    private Date begin=null;

    @TableField(exist=false)//addtime
    private Date end=null;



}
