package com.douban.query;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author duanhaoran
 * @since 2020/6/11 1:59 PM
 */
public class RunTimeTest {
    private static final Abc  abc = new Abc();

    public static void main(String[] args) throws UnknownHostException {
        System.out.println(InetAddress.getLocalHost().getHostAddress());
        try {
            List<NetworkInterface> nis = Collections.list(NetworkInterface.getNetworkInterfaces());
            List<InetAddress> addresses = new ArrayList<InetAddress>();
            InetAddress local = null;

            try {
                for (NetworkInterface ni : nis) {
                    if (ni.isUp()) {
                        addresses.addAll(Collections.list(ni.getInetAddresses()));
                    }
                }
                local = findValidateIp(addresses);
            } catch (Exception e) {
                // ignore
            }
            System.out.println(local);
        } catch (Exception e) {
            // ignore
        }
    }

        public static InetAddress findValidateIp(List<InetAddress> addresses) {
            InetAddress local = null;
            for (InetAddress address : addresses) {
                if (address instanceof Inet4Address) {
                    if (address.isLoopbackAddress() || address.isSiteLocalAddress()) {
                        if (local == null) {
                            local = address;
                        } else if (address.isSiteLocalAddress() && !address.isLoopbackAddress()) {
                            // site local address has higher priority than other address
                            local = address;
                        } else if (local.isSiteLocalAddress() && address.isSiteLocalAddress()) {
                            // site local address with a host name has higher
                            // priority than one without host name
                            if (local.getHostName().equals(local.getHostAddress())	&& !address.getHostName()
                                    .equals(address.getHostAddress())) {
                                local = address;
                            }
                        }
                    } else {
                        if (local == null) {
                            local = address;
                        }
                    }
                }
            }
            return local;
        }
}
