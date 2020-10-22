package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResults<T> {
    private Integer id;
    private String message;
    private T data;

    public CommonResults(Integer id, String message) {
        this(id,message,null);

    }
}
