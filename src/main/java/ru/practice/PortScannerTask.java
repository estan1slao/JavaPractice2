package ru.practice;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class PortScannerTask implements Task {

    private final String host;
    private final int startPort;
    private final int endPort;
    private final int timeoutMs;

    private final AtomicBoolean running =
            new AtomicBoolean(false);

    private Thread workerThread;

    public PortScannerTask(
            String host,
            int startPort,
            int endPort,
            int timeoutMs
    ) {

        if (startPort < 1
                || endPort > 65535
                || startPort > endPort) {

            throw new IllegalArgumentException(
                    "Некорректный диапазон портов."
            );
        }

        if (timeoutMs < 1) {
            throw new IllegalArgumentException(
                    "Timeout должен быть больше 0."
            );
        }

        this.host = host;
        this.startPort = startPort;
        this.endPort = endPort;
        this.timeoutMs = timeoutMs;
    }

    @Override
    public synchronized void start() {

        if (running.get()) {
            System.out.println(
                    "Сканирование уже запущено."
            );
            return;
        }

        running.set(true);

        workerThread = new Thread(
                this::scan,
                "PortScanner"
        );

        workerThread.start();
    }

    @Override
    public synchronized void stop() {

        running.set(false);

        if (workerThread != null) {
            workerThread.interrupt();
        }
    }

    private void scan() {

        System.out.printf(
                "Сканирование %s, порты %d-%d...%n",
                host,
                startPort,
                endPort
        );

        int openPorts = 0;

        try {

            for (
                    int port = startPort;
                    port <= endPort && running.get();
                    port++
            ) {

                if (isPortOpen(port)) {

                    System.out.println(
                            "Открыт порт: " + port
                    );

                    openPorts++;
                }
            }

        } finally {

            running.set(false);

            System.out.println(
                    "Сканирование завершено. "
                            + "Найдено открытых портов: "
                            + openPorts
            );
        }
    }

    private boolean isPortOpen(int port) {

        try (Socket socket = new Socket()) {

            socket.connect(
                    new InetSocketAddress(
                            host,
                            port
                    ),
                    timeoutMs
            );

            return true;

        } catch (IOException ignored) {

            return false;
        }
    }
}