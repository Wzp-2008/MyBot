package cn.wzpmc.mybot;

import java.util.Map;

import static cn.wzpmc.mybot.Main.*;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/5/4
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
