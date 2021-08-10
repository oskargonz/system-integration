import ClientsPackage.Client;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

public class Client {
    public static void main(String[] args) throws InvalidName, org.omg.CosNaming.NamingContextPackage.InvalidName, CannotProceed, NotFound {
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);

        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
        NamingContext ncRef = NamingContextHelper.narrow(objRef);
        NameComponent nc = new NameComponent("Clients", "");
        NameComponent path[] = {nc};
        Clients proxy = ClientsHelper.narrow(ncRef.resolve(path));

        Client c1 = new Client();
        Client c2 = new Client();
        Client c3 = new Client();

        Client[] clientsToAdd = {c1, c2, c3};

        proxy.clients(clientsToAdd);

        for (Clients client: proxy.clients()) {
            showClient(client);
            System.out.println();
        }

        System.out.println("The oldest client: ");
        showClient(proxy.setPesel());
        System.out.println();
        System.out.println("The youngest clientt: ");
        showClient(proxy.updateClient());
    }

    private static void showClient(Client client) {
        System.out.println("name: " + client.firstName +
                "\nsurname: " + client.lastName +
                "\npesel: " + client.PESEL);
    }
}
