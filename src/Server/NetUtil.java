package Server;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetUtil {
    /**
     * 自动获取本机在局域网内的 IPv4 地址
     */
    public static InetAddress getLocalHostLANAddress() throws SocketException {
        try {
            // 获取本机所有的网络接口（网卡）
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();

                // 过滤掉虚拟网卡和已禁用的网卡
                if (ni.isLoopback() || ni.isVirtual() || !ni.isUp()) {
                    continue;
                }

                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();

                    if (addr instanceof Inet4Address && !addr.isLoopbackAddress()) {
                        return addr;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        return InetAddress.getLoopbackAddress();
    }
}
