package com.mxc.love.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ciwei
 * @since 2019-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Live extends Model<Live> {

private static final long serialVersionUID=1L;

    /**
     * 直播id
     */
    @TableId(value = "liveId", type = IdType.AUTO)
    private Integer liveId;

    /**
     * 直播名
     */
    @TableField("liveName")
    private String liveName;


    @Override
    protected Serializable pkVal() {
        return this.liveId;
    }

}
