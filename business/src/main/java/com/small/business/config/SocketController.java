package com.small.business.config;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author baibing
 * @project: springboot-socket
 * @package: com.sailing.websocket.controller
 * @Description: websocket测试controller
 * @date 2018/12/20 10：11
 */
@RestController
public class SocketController {

    @Resource
    private WebSocketServer webSocketServer;

    /**
     * 给指定用户推送消息
     * @param userId 用户名
     * @param message 消息
     * @throws IOException
     */
    @RequestMapping(value = "/socket", method = RequestMethod.GET)
    public void testSocket1(@RequestParam String userId, @RequestParam String message){
        webSocketServer.sendInfo(userId, message);
    }

    /**
     * 给所有用户推送消息
     * @param message 消息
     * @throws IOException
     */
    @RequestMapping(value = "/socket/all", method = RequestMethod.GET)
    public void testSocket2(@RequestParam String message){
        try {
            webSocketServer.onMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}