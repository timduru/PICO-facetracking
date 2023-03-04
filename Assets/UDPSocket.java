package com.rogermiranda1000.pico_facetracking;

import java.net.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * UDPSocket.cs client converted into Java by ChatGPT
 */
public class UDPSocket {
    private DatagramSocket socket;
    private static final int bufSize = 8 * 1024;
    private State state = new State();

    public UDPSocket() throws SocketException {
        socket = new DatagramSocket();
    }

    public class State {
        public byte[] buffer = new byte[bufSize];
    }

    public void Client(String address, int port) throws UnknownHostException {
        socket.connect(InetAddress.getByName(address), port);
    }

    public void Send(String text) throws IOException {
        byte[] data = text.getBytes(StandardCharsets.US_ASCII);
        socket.send(new DatagramPacket(data, data.length));
        System.out.printf("SEND: %d, %s\n", data.length, text);
    }
}