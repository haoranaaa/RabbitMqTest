package com.dhr.compute;

public class IPv4Save {
    public static void main(String[] args) {
        String ip = "255.255.255.254";
        String ip2 = "127.0.0.1";
        int i = parseIpv4(ip);
        parse2Ipv4(i);
        System.out.println();
    }

    private static int parseIpv4(String ip) {
        String[] strings = ip.split("\\.");
        int int0 = Integer.parseInt(strings[0]);
        int int1 = Integer.parseInt(strings[1]);
        int int2 = Integer.parseInt(strings[2]);
        int int3 = Integer.parseInt(strings[3]);

        return (int0 & 0xFF) << 24 | (int1 & 0xFF) << 16 | (int2 & 0xFF) << 8 | (int3 & 0xFF);
    }

    private static void parse2Ipv4(int ip) {

        System.out.println(ip>>24);
    }
}
