package com.minlia.cloud.holder;

/**
 * Created by user on 12/7/16.
 */
public class ServerPortHolder {
    private static Integer port;

    public static Integer getPort() {
        return ServerPortHolder.port;
    }

    public static void setPort(Integer port) {
        ServerPortHolder.port=port;
    }
}
