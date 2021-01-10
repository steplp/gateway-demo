package com.example.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName：Hello
 * @Description：
 * @Author：Peng Liu
 * @Date：2020/12/26
 * @Version：V1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hello implements Serializable {

    private Long id;
    private String name;
}
