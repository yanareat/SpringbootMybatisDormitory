package com.neuedu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.neuedu.pojo.Bus;
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
 * @since 2019-08-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Userinfor extends Bus {

    private static final long serialVersionUID = 1L;

    /*@TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;*/

    @TableField("UserName")
    private String UserName;

    @TableField("Password")
    private String Password;

    @TableField("Jurisdiction")
    private Integer Jurisdiction;

    @TableField("Remarks")
    private String Remarks;

    @TableField("IsDel")
    private Integer IsDel;


}
