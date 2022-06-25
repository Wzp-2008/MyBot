package cn.wzpmc.mybot;

import java.util.Map;

import static cn.wzpmc.mybot.Main.nettyThread;
import static cn.wzpmc.mybot.Main.running;

/**
 * @author wzp
 * @version 1.0.0
 */
public class StopThread extends Thread{
    @Override
    public void run() {
        running = false;
        nettyThread.stopNetty();
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        for (Thread thread : allStackTraces.keySet()) {
            thread.interrupt();
        }
    }
}
