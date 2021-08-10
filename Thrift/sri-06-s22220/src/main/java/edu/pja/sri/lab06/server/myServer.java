package edu.pja.sri.lab06.server;

import edu.pja.sri.lab06.ProductManager;
import edu.pja.sri.lab06.theOldest;
import edu.pja.sri.lab06.theYoungest;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class myServer {
    public static myHandler handler;

    public static void main(String [] args) {
        try {
            handler = new myHandler();
            int port = 9090;
            TMultiplexedProcessor processor = new TMultiplexedProcessor();
            TServerTransport t = new TServerSocket(port);
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(t).processor(processor));
            processor.registerProcessor("ProductManagerService", new ProductManager.Processor(handler));
            processor.registerProcessor("theOldestService", new theOldest.Processor(handler));
            processor.registerProcessor("theYoungestService", new theYoungest.Processor(handler));

            System.out.println("starting server port:" + port);
            server.serve();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
