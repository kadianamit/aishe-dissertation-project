package aishe.gov.in.utility;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


public class IpAddressProvider {

    public static String getClientIpAddr(HttpServletRequest request) {
        String ip = null;
        for (String s : Arrays.asList("X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_X_FORWARDED_FOR",
                "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP", "HTTP_FORWARDED_FOR", "HTTP_FORWARDED",
                "HTTP_VIA", "REMOTE_ADDR")) {
            if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
                ip = request.getHeader(s);
            } else {
                return ip;
            }
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
