package com.tui.proof.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Log4j2
@SuppressWarnings({"squid:S4784", "squid:S106", "squid:S1444", "squid:ClassVariableVisibilityCheck", "squid:S1075"})
public class ControllerHelper {

    public static String hostname = "unknownHostname";
    public static String serviceName = "unknownService";
    public static String version = "unknownVersion";

    public static final String X_PROCESSTIME = "X-ProcessTime";
    public static final String X_INIT_TIMESTAMP = "X-Timestamp";
    public static final String X_REQUESTHOST = "X-RequestHost";
    public static final String X_SERVICENAME = "X-ServiceName";
    public static final String X_VERSION = "X-Version";

    public static final String CLIENT_CONTROLLER_PATH = "/1.0/clients";
    public static final String AUTH_CONTROLLER_PATH = "/1.0/auth";
    public static final String CLIENT_TAG = "client";

    public static final String ORDER_CONTROLLER_PATH = "/1.0/orders";
    public static final String ORDER_TAG = "order";

    private ControllerHelper() {
        throw new IllegalAccessError("Utility class");
    }

    static {
        try {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            hostname = addr.getHostName();
        } catch (UnknownHostException ex) {
            System.out.println("Hostname can not be resolved");
        }
    }

    public static HttpHeaders buildHeaders(LocalDateTime processingStartTime) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(X_INIT_TIMESTAMP, processingStartTime.toString());
        httpHeaders.set(X_REQUESTHOST, hostname);
        httpHeaders.set(X_SERVICENAME, serviceName);
        httpHeaders.set(X_VERSION, version);
        httpHeaders.set(X_PROCESSTIME, Long.toString(ChronoUnit.MILLIS.between(processingStartTime, LocalDateTime.now())));
        return httpHeaders;
    }
}
