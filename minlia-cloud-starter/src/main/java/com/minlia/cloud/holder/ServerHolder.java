package com.minlia.cloud.holder;

/**
 * @author user
 * @date 12/7/16
 * TODO
 */
public class ServerHolder {

    private static Integer port;
    private static String host;

    public static Integer getPort() {
        return ServerHolder.port;
    }

    public static void setPort(Integer port) {
        ServerHolder.port = port;
    }

    public static String getHost() {
        return ServerHolder.host;
    }

    public static void setHost(String host) {
        ServerHolder.host = host;
    }

}
