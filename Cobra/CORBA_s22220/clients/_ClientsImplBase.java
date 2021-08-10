
/**
* _ClientsImplBase.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Client.idl
* pi�tek, 4 czerwca 2021 18:53:24 CEST
*/

public abstract class _ClientsImplBase extends org.omg.CORBA.portable.ObjectImpl
                implements Clients, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors
  public _ClientsImplBase ()
  {
  }

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("_get_firstName", new java.lang.Integer (0));
    _methods.put ("_set_firstName", new java.lang.Integer (1));
    _methods.put ("_get_lastName", new java.lang.Integer (2));
    _methods.put ("_set_lastName", new java.lang.Integer (3));
    _methods.put ("_get_companyName", new java.lang.Integer (4));
    _methods.put ("_set_companyName", new java.lang.Integer (5));
    _methods.put ("_get_contractActive", new java.lang.Integer (6));
    _methods.put ("_set_contractActive", new java.lang.Integer (7));
    _methods.put ("_get_PESEL", new java.lang.Integer (8));
    _methods.put ("_set_PESEL", new java.lang.Integer (9));
    _methods.put ("_get_client", new java.lang.Integer (10));
    _methods.put ("_set_client", new java.lang.Integer (11));
    _methods.put ("_get_clients", new java.lang.Integer (12));
    _methods.put ("_set_clients", new java.lang.Integer (13));
    _methods.put ("setPesel", new java.lang.Integer (14));
    _methods.put ("updateClient", new java.lang.Integer (15));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // Clients/_get_firstName
       {
         String $result = null;
         $result = this.firstName ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // Clients/_set_firstName
       {
         String newFirstName = in.read_string ();
         this.firstName (newFirstName);
         out = $rh.createReply();
         break;
       }

       case 2:  // Clients/_get_lastName
       {
         String $result = null;
         $result = this.lastName ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 3:  // Clients/_set_lastName
       {
         String newLastName = in.read_string ();
         this.lastName (newLastName);
         out = $rh.createReply();
         break;
       }

       case 4:  // Clients/_get_companyName
       {
         String $result = null;
         $result = this.companyName ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 5:  // Clients/_set_companyName
       {
         String newCompanyName = in.read_string ();
         this.companyName (newCompanyName);
         out = $rh.createReply();
         break;
       }

       case 6:  // Clients/_get_contractActive
       {
         boolean $result = false;
         $result = this.contractActive ();
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 7:  // Clients/_set_contractActive
       {
         boolean newContractActive = in.read_boolean ();
         this.contractActive (newContractActive);
         out = $rh.createReply();
         break;
       }

       case 8:  // Clients/_get_PESEL
       {
         int $result = (int)0;
         $result = this.PESEL ();
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 9:  // Clients/_set_PESEL
       {
         int newPESEL = in.read_long ();
         this.PESEL (newPESEL);
         out = $rh.createReply();
         break;
       }

       case 10:  // Clients/_get_client
       {
         ClientsPackage.Client $result = null;
         $result = this.client ();
         out = $rh.createReply();
         ClientsPackage.ClientHelper.write (out, $result);
         break;
       }

       case 11:  // Clients/_set_client
       {
         ClientsPackage.Client newClient = ClientsPackage.ClientHelper.read (in);
         this.client (newClient);
         out = $rh.createReply();
         break;
       }

       case 12:  // Clients/_get_clients
       {
         ClientsPackage.Client $result[] = null;
         $result = this.clients ();
         out = $rh.createReply();
         ClientsPackage.ClientSequenceHelper.write (out, $result);
         break;
       }

       case 13:  // Clients/_set_clients
       {
         ClientsPackage.Client newClients[] = ClientsPackage.ClientSequenceHelper.read (in);
         this.clients (newClients);
         out = $rh.createReply();
         break;
       }

       case 14:  // Clients/setPesel
       {
         ClientsPackage.Client $result = null;
         $result = this.setPesel ();
         out = $rh.createReply();
         ClientsPackage.ClientHelper.write (out, $result);
         break;
       }

       case 15:  // Clients/updateClient
       {
         ClientsPackage.Client $result = null;
         $result = this.updateClient ();
         out = $rh.createReply();
         ClientsPackage.ClientHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:Clients:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }


} // class _ClientsImplBase