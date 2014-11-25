/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.jmamcallister.vertx.services.handlers;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.platform.Verticle;

/**
 *
 * @author alex
 */
public class SnippetsGetHandler extends Verticle {

    @Override
    public void start() {

        vertx.eventBus().registerHandler("snippets-get-all", new Handler<Message<String>>() {

            @Override
            public void handle(Message<String> message) {
                System.out.printf("Message sent to handler: %s", message.body());
                message.reply("The reply!");
            }
        });

    }

}
