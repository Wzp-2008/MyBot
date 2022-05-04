package cn.wzpmc.mybot;

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
    }
}
