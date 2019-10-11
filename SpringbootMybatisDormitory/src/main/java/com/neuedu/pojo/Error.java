package com.neuedu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.neuedu.pojo.Bus;
import java.time.LocalDate;
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
public class Error extends Bus {

    private static final long serialVersionUID = 1L;

//    @TableId(value = "Id", type = IdType.AUTO)
//    private Integer Id;

    /**
     * 出错事件
     */
    @TableField("EventId")
    private Integer EventId;

    /**
     * 纠错者电话
     */
    @TableField("Phone")
    private String Phone;

    /**
     * 纠错时间
     */
    @TableField("AddDate")
    private LocalDate AddDate;

    /**
     * 纠错内容
     */
    @TableField("AddText")
    private String AddText;


}
