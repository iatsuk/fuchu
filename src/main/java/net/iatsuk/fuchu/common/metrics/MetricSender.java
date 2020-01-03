package net.iatsuk.fuchu.common.metrics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;

/**
 * Send metrics to carbon.
 */
public class MetricSender implements AutoCloseable {
    private static transient Logger LOG = LoggerFactory.getLogger(MetricSender.class);

    private final boolean enabled;
    private final int port;
    private final InetAddress host;
    private final DatagramSocket socket;

    public MetricSender(String host, int port) throws SocketException, UnknownHostException {
        this(host, port, true);
    }

    public MetricSender(String host, int port, boolean enabled) throws UnknownHostException, SocketException {
        this.port = port;
        this.enabled = enabled;
        if (enabled) {
            this.host = Inet4Address.getByName(host);
            this.socket = new DatagramSocket(new InetSocketAddress(InetAddress.getLocalHost(), 0));
        } else {
            this.host = null;
            this.socket = null;
        }
    }

    public void send(String metric, Number value, long timestamp) {
        String msg = String.format("%s %s %d", metric, value.toString(), timestamp);

        if (LOG.isDebugEnabled()) {
            LOG.debug(msg);
        }

        if (!enabled) {
            return;
        }

        try {
            DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), host, port);
            socket.send(packet);
        } catch (IOException e) {
            LOG.error("", e);
        }
    }

    @Override
    public void close() {
        if (socket != null) {
            socket.close();
        }
    }
}
