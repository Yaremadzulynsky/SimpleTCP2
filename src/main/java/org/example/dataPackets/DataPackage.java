package org.example.dataPackets;

import org.example.dataPackets.DataTransferInterface;

import java.io.Serializable;

public class DataPackage<T> implements Serializable, DataTransferInterface {

    private T data;
    private String header;

    public DataPackage(String header, T data) {

        this.header = header;
        this.data = data;

    }

    public T getData() {
        return data;
    }

    public String getHeader() {
        return header;
    }



}
