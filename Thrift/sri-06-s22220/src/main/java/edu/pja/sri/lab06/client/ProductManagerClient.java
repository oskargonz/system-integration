package edu.pja.sri.lab06.client;

import edu.pja.sri.lab06.Product;
import edu.pja.sri.lab06.ProductManager;
import edu.pja.sri.lab06.theOldest;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.List;

public class ProductManagerClient {
	public static void main(String [] args) {
		try {
			TSocket transport = new TSocket("localhost", 9090);
			transport.open();
			TBinaryProtocol protocol = new TBinaryProtocol(transport);
			TMultiplexedProtocol mp = new TMultiplexedProtocol(protocol, "ProductManager");
			ProductManager.Client productManagerclient = new ProductManager.Client(mp);

			perform(productManagerclient);

			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}
	}

		  private static void perform(ProductManager.Client productManagerclient) throws TException {
			  
			  List<Product> allProducts = productManagerclient.getProducts();
			  System.out.println(allProducts);
		  }
		}
