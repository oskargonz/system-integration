package edu.pja.sri.lab06.client;

import edu.pja.sri.lab06.Product;
import edu.pja.sri.lab06.theYoungest;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class TheYoungestClient {
    public static void main(String [] args) {
        try {
            TSocket transport = new TSocket("localhost", 9090);
            transport.open();
            TBinaryProtocol protocol = new TBinaryProtocol(transport);
            TMultiplexedProtocol mp = new TMultiplexedProtocol(protocol, "TheYoungest");
            theYoungest.Client theYoungestclient = new theYoungest.Client(mp);
            perform(theYoungestclient);

            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        }
    }

    private static void perform(theYoungest.Client theYoungestclient) throws TException {

        Product oneProduct = theYoungestclient.youngest();
        System.out.println(oneProduct);
    }
}
