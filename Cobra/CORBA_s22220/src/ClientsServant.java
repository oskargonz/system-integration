import ClientsPackage.Client;

public class ClientsServant extends _ClientsImplBase {

    String firstName, lastName, companyName;
    int PESEL;
    Boolean contractActive;
    Client client;
    Client[] clients;


    @Override
    public String firstName() {
        return firstName;
    }

    @Override
    public void firstName(String newName) {
        firstName = newName;
    }

    @Override
    public String lastName() {
        return lastName;
    }

    @Override
    public void lastName(String newLastName) {
        lastName = newLastName;
    }

    @Override
    public String companyName() {
        return companyName;
    }

    @Override
    public void companyName(String newCompanyName) {
        companyName = newCompanyName;
    }

    @Override
    public boolean contractActive() {
        return false;
    }

    @Override
    public void contractActive(boolean newContractActive) { contractActive = newContractActive; }

    @Override
    public int PESEL() { return PESEL; }

    @Override
    public void PESEL(int newPESEL) {
        PESEL = newPESEL;
    }


    @Override
    public Client client() {
        return client;
    }

    @Override
    public void client(Client newClient) {

    }

    @Override
    public void client(Client newClient) {
        client = new Client(newClient.firstName, newClient.lastName, newClient.contractActive, newClient.companyName, newClient.PESEL);
    }

    @Override
    public Client[] clients() {
        return clients;
    }

    @Override
    public void clients(Client[] newClients) { clients = newClients; }


    @Override
    public Client setPesel() {
        //This method returns the oldest client
        Client theOldest = clients[0];

        for (Client client: clients) {
            if (client.PESEL < theOldest.PESEL) {
                theOldest = client;
            }
        }
        return theOldest;
    }

    @Override
    public Client updateClient() {
        //This method returns the youngest client
        Client theYoungest = clients[0];

        for (Client client: clients) {
            if (client.PESEL > theYoungest.PESEL) {
                theYoungest = client;
            }
        }
        return theYoungest;
    }
}
