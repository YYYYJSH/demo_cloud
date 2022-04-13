package com.yjsh.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@TableName("food")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Food implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    Integer id;

    @TableField("food")
    String food;
}
