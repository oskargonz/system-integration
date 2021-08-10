package edu.pja.sri.lab06.server;

import edu.pja.sri.lab06.Product;
import edu.pja.sri.lab06.ProductManager;
import edu.pja.sri.lab06.theOldest;
import edu.pja.sri.lab06.theYoungest;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;

public class myHandler implements ProductManager.Iface, theOldest.Iface, theYoungest.Iface{
    @Override
    public List<Product> getProducts() throws TException {
        List<Product> myList = new ArrayList();
        Product p1 = new Product("Bill","Gates",true, "Microsoft", 68);
        Product p2 = new Product("Steave","Jobs",true, "Apple", 99);
        Product p3 = new Product("Elon","Musk",false, "SpaceX", 39);
        myList.add(p1);
        myList.add(p2);
        myList.add(p3);

        return myList;
    }

    @Override
    public Product oldest() throws TException {
        List<Product> myList2 = getProducts();

        Product oldestProduct = myList2.get(0);
        for (Product product:myList2){
            if (product.age > oldestProduct.age){
                oldestProduct = product;
            }
        }
        return oldestProduct;
    }

    @Override
    public Product youngest() throws TException {
        List<Product> myList2 = getProducts();

        Product ypungestProduct = myList2.get(0);
        for (Product product:myList2){
            if (product.age < ypungestProduct.age){
                ypungestProduct = product;
            }
        }
        return ypungestProduct;
    }
}
