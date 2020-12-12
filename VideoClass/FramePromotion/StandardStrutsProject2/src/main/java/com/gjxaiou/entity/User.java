package com.gjxaiou.entity;

import lombok.*;

/**
 * @author GJXAIOU
 * @create 2019-09-30-14:25
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;
    private String password;
    private String address;
}
