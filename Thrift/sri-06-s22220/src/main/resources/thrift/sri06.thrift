namespace java edu.pja.sri.lab06

struct Product {
    1: required string firstName;
        2: required string lastName;
        3: required bool contractActive;
        4: required string companyName;
        5: required i32 age;
}

exception ProductUnvailable {
    1: string message
}

service ProductManager {
    list<Product> getProducts()
}

service theOldest {
    Product oldest();
}

service theYoungest {
    Product youngest();
}

