package com.thinking.helloamqp;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 4594478041595836464L;

    private String name;
    private long id;
}
