package com.dhr.io;

import io.netty.buffer.PooledByteBufAllocator;
import org.asynchttpclient.*;

import javax.annotation.PostConstruct;
import java.nio.channels.AsynchronousChannelGroup;
import java.util.concurrent.ExecutionException;

/**
 * @author duanhaoran
 * @since 2019/4/16 1:09 PM
 */
public class AsynServer {

    private AsyncHttpClient client;

    public AsynServer(){
        this.client = init();
    }

    @PostConstruct
    private AsyncHttpClient init(){
        DefaultAsyncHttpClientConfig.Builder builder = new DefaultAsyncHttpClientConfig.Builder();
        builder.setAllocator(PooledByteBufAllocator.DEFAULT);
        builder.setCompressionEnforced(true);
        builder.setConnectTimeout(2000);
        builder.setRequestTimeout(1000);
        builder.setPooledConnectionIdleTimeout(3 * 60 * 1000);
        return new DefaultAsyncHttpClient(builder.build());
    }

    private void testLink() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            ListenableFuture<Response> execute = client.prepareGet("http://127.0.0.1:8000/").execute();
            Response response = execute.get();
            System.out.println(response.getStatusCode());
            Thread.sleep(500);
        }
    }

    public static void main(String[] args) {
        AsynServer asynServer = new AsynServer();
        try {
            asynServer.testLink();
        } catch (ExecutionException | InterruptedException e) {
            //ignore
            System.out.println(e);
        }
    }
}
