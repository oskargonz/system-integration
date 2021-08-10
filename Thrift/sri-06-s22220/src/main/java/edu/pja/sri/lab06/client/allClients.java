package edu.pja.sri.lab06.client;

import edu.pja.sri.lab06.Product;
import edu.pja.sri.lab06.ProductManager;
import edu.pja.sri.lab06.theOldest;
import edu.pja.sri.lab06.theYoungest;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.transport.TSocket;

import java.util.List;

public class allClients {
    public static void main(String [] args) {
        try {
            TSocket transport = new TSocket("localhost", 9090);
            transport.open();
            TBinaryProtocol protocol = new TBinaryProtocol(transport);

            TMultiplexedProtocol mp = new TMultiplexedProtocol(protocol, "ProductManager");
            ProductManager.Client productManagerclient = new ProductManager.Client(mp);
            perform(productManagerclient);

            TMultiplexedProtocol mp2 = new TMultiplexedProtocol(protocol, "TheOldest");
            theOldest.Client theOldestclient = new theOldest.Client(mp2);
            perform2(theOldestclient);

            TMultiplexedProtocol mp3 = new TMultiplexedProtocol(protocol, "TheYoungest");
            theYoungest.Client theYoungestclient = new theYoungest.Client(mp3);
            perform3(theYoungestclient);

            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        }
    }

    private static void perform(ProductManager.Client productManagerclient) throws TException {

        List<Product> allProducts = productManagerclient.getProducts();
        System.out.println(allProducts);
    }

    private static void perform2(theOldest.Client theOldestclient) throws TException {

        Product oneProduct = theOldestclient.oldest();
        System.out.println(oneProduct);
    }

    private static void perform3(theYoungest.Client theYoungestclient) throws TException {

        Product oneProduct = theYoungestclient.youngest();
        System.out.println(oneProduct);
    }
}

