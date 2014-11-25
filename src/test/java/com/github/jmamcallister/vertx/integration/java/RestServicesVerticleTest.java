/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.jmamcallister.vertx.integration.java;

import org.junit.Test;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.AsyncResultHandler;
import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpClientResponse;
import org.vertx.testtools.TestVerticle;
import static org.vertx.testtools.VertxAssert.assertEquals;
import static org.vertx.testtools.VertxAssert.assertTrue;
import static org.vertx.testtools.VertxAssert.testComplete;

/**
 *
 * @author alex
 */
public class RestServicesVerticleTest extends TestVerticle {

    @Test
    public void defaultTest() {
        testComplete();
    }

    /**
     * Test a response is received within 2 seconds
     */
    @Test
    public void testResourceGET() {
        vertx.createHttpClient().setConnectTimeout(2000).setPort(8888).getNow("/snippets", new Handler<HttpClientResponse>() {

            @Override
            public void handle(HttpClientResponse event) {
                assertEquals(200, event.statusCode());
                testComplete();
            }
        });

    }

    @Override
    public void start() {
        initialize();
        container.deployModule(System.getProperty("vertx.modulename"), new AsyncResultHandler<String>() {

            @Override
            public void handle(AsyncResult<String> event) {
                assertTrue(event.succeeded());
                startTests();
            }

        });
    }
}
