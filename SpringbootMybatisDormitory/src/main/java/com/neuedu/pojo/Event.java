package com.neuedu.pojo;

import java.time.LocalDate;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.neuedu.pojo.Bus;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;


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
public class Event extends Bus {

    private static final long serialVersionUID = 1L;

//    @TableId(value = "Id", type = IdType.AUTO)
//    private Integer Id;

    /**
     * 标题
     */
    @TableField("Title")
    private String Title;

    /**
     * 副标题
     */
    @TableField("Subtitle")
    private String Subtitle;

    /**
     * 关键字
     */
    @TableField("Keyword")
    private String Keyword;

    /**
     * 摘要
     */
    @TableField("Abstract")
    private String Abstract;

    /**
     * 事件发生时间
     */
    @TableField("Time")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date Time;

    /**
     * 内容
     */
    @TableField("Content")

    private String Content;

    /**
     * 类别
     */
    @TableField("Category")
    private String Category;

    /**
     * 添加时间
     */
    @TableField("AddTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @JSONField(format = "yyyy-MM-dd")
    private Date AddTime;

    /**
     * 阅读量
     */
    @TableField("ReadNum")
    private Integer ReadNum;

    /**
     * 数据源
     */
    @TableField("DataSource")
    private String DataSource;

    /**
     * 数据源网址
     */
    @TableField("Website")
    private String Website;

    /**
     * 爬到图片的网络地址
     */
    @TableField("Image_web")
    private String imageWeb;

    /**
     * 图片
     */
    @TableField("Image")
    private String Image;

    /**
     * 是否有效
     */
    @TableField("IsDel")
    private Integer IsDel;

    @TableField(exist=false)
    @Temporal(TemporalType.DATE)
    @JSONField(format = "yyyy-MM-dd")
    private Date beginTime=null;

    @TableField(exist=false)
    @Temporal(TemporalType.DATE)
    private Date endTime=null;

    @TableField(exist=false)
    @Temporal(TemporalType.DATE)

    private Date begin=null;

    @TableField(exist=false)
    @Temporal(TemporalType.DATE)
    private Date end=null;


}
