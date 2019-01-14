package com.panchen.martinet.test.transport;

import org.junit.Test;
import com.panchen.martinet.plugin.collect.transport.NettyClientTransprot;
import com.panchen.martinet.server.transport.NettyTransport;

public class TransportTest {
    
    @Test
    public void ping() throws InterruptedException {
        NettyTransport NettyTransport=new NettyTransport();
        NettyTransport.start();
        
        NettyClientTransprot ClientTransprot=new NettyClientTransprot();
        ClientTransprot.start();
        
        while(true) {
            
        }
    }
}
