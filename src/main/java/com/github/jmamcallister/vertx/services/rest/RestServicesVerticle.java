/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.jmamcallister.vertx.services.rest;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.platform.Verticle;

/**
 * Vert-x providing RESTful services
 *
 * @author alex
 */
public class RestServicesVerticle extends Verticle {

    private final int VERTX_HTTP_SERVER_PORT = 8888;
    private final String SERVICE_NAME = "snippets";

    @Override
    public void start() {

        RouteMatcher matcher = new RouteMatcher();

        matcher.get(String.format("/%s", SERVICE_NAME), new Handler<HttpServerRequest>() {

            @Override
            public void handle(HttpServerRequest request) {

                request.endHandler(new Handler<Void>() {

                    @Override
                    public void handle(Void event) {
                    }

                });

                System.out.printf("GET request received: /%s\n", SERVICE_NAME);
                System.out.printf("Sending message on event bus: /%s\n", "get all the stuff for me");
                vertx.eventBus().send("snippets-get-all", "get me all the stuff", new Handler<Message<String>>() {

                    @Override
                    public void handle(Message<String> reply) {
                    }

                });
            }

        });

        vertx.createHttpServer()
                .requestHandler(matcher)
                .listen(VERTX_HTTP_SERVER_PORT)
                ;

        container.logger().info(String.format("Webserver started, listening on port: %s", VERTX_HTTP_SERVER_PORT));

    }
}
