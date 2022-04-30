package cn.wzpmc.mybot.UIListener;

import lombok.extern.slf4j.Slf4j;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/16
 */
@Slf4j
public class OnStopClicked implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        log.info("stop");
    }
}
