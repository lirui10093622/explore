package org.demo.demospringboot.dto;

import lombok.Data;

@Data
public class DemoDTO {

    private int id;

    private byte[] data;

    public DemoDTO(int id, int n) {
        this.id = id;
        this.data = new byte[n];
    }
}