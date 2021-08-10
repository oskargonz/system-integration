import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.io.IOException;

public class Server {

    public static void main(String[] args) throws IOException, InvalidName, InterruptedException, org.omg.CosNaming.NamingContextPackage.InvalidName, CannotProceed, NotFound {
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
        ClientsServant cs = new ClientsServant();
        orb.connect(cs);

        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
        NamingContext ncRef = NamingContextHelper.narrow(objRef);
        NameComponent nc = new NameComponent("Clients", "");
        NameComponent path[] = {nc};
        ncRef.rebind(path, cs);

        java.lang.Object sync = new java.lang.Object();
        synchronized (sync) {
            sync.wait();
        }
    }
}
