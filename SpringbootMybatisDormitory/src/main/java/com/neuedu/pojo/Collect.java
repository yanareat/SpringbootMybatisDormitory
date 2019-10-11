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
public class Collect extends Bus {

    private static final long serialVersionUID = 1L;
//
//    @TableId(value = "Id", type = IdType.AUTO)
//    private Integer Id;

    /**
     * 标题
     */
    @TableField("Title")
    private String Title;

    /**
     * 内容
     */
    @TableField("Content")
    private String Content;

    /**
     * 图片
     */
    @TableField("Image")
    private String Image;

    /**
     * 发生时间
     */
    @TableField("OccurDate")
    private LocalDate OccurDate;

    /**
     * 添加时间
     */
    @TableField("AddDate")
    private LocalDate AddDate;

    /**
     * 联系电话
     */
    @TableField("Phone")
    private String Phone;

    /**
     * 提供者信息
     */
    @TableField("ProviderInformation")
    private String ProviderInformation;

    @TableField(exist=false)
    private Date beginTime=null;

    @TableField(exist=false)//time
    private Date endTime=null;

    @TableField(exist=false)
    private Date begin=null;

    @TableField(exist=false)//addtime
    private Date end=null;

}
