package com.gjxaiou.swagger2020.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

// 用于标识实体类的文档注释说明
@ApiModel("This is User Class，类的说明")
public class User {
    @ApiModelProperty("用户名:属性说明")
    public String username;
    @ApiModelProperty("密码")
    public String password;
}
