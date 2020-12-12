package com.gjxaiou.pojo;

import lombok.*;

/**
 * @author GJXAIOU
 * @create 2019-09-28-13:45
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private Integer uid;
    private String username;
    private String password;
    private String address;
}
