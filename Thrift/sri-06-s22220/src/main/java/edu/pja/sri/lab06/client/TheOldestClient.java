package edu.pja.sri.lab06.client;

import edu.pja.sri.lab06.Product;
import edu.pja.sri.lab06.theOldest;
import edu.pja.sri.lab06.theYoungest;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.List;

public class TheOldestClient {
    public static void main(String [] args) {
        try {
            TSocket transport = new TSocket("localhost", 9090);
            transport.open();
            TBinaryProtocol protocol = new TBinaryProtocol(transport);
            TMultiplexedProtocol mp = new TMultiplexedProtocol(protocol, "TheOldest");
            theOldest.Client theOldestclient = new theOldest.Client(mp);
            perform(theOldestclient);

            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        }
    }

    private static void perform(theOldest.Client theOldestclient) throws TException {

        Product oneProduct = theOldestclient.oldest();
        System.out.println(oneProduct);
    }
}
