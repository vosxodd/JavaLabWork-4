package Lab4;

/**
 * Class for demonstration of how the program works.
 */
public class Lab4 {
    public static void main(String[] args) throws Exception {
        String PATH = "D:\\DB Accounts\\data.csv"; // You can change the path to your future csv file
        // row number аs equivalent of id is also needed when creating an account
        Account Alexey = new Account("0", "Alexey Labuzov", "10/12/2001", "a.labuzov@mail.ru", "something");
        Account Kirill = new Account("1", "Kirill Bichkov", "10/03/2002", "k.bichkov@mail.ru", "bestie");
        Account Max = new Account("2", "Maxim Skulev", "23/07/2002", "m.skulev@mail.ru", "more");
        Account Tanya = new Account("3", "Tanya Zubareva", "03/06/2002", "t.zubareva@mail.ru", "password");
        Account Edward = new Account("4", "Edward Bikmetov", "13/11/2002", "e.bikmetov@mail.ru", "far24");

        FileService fileService = FileService.getInstance();
        FileAccountManager manager = new FileAccountManager();
        fileService.createFile(PATH);

        // accounts should be registered in the same order as they were created
        manager.register(Alexey);
        manager.register(Kirill);
        manager.register(Max);
        manager.register(Tanya);
        manager.register(Edward);

        manager.removeAccount("m.skulev@mail.ru", "more");

        try {
            manager.register(Alexey);
        } catch (AccountAlreadyExistsException e) {
            System.out.println("The account is already exist. ");
        }


        manager.login("a.labuzov@mail.ru", "sumething"); // not correct try 1
        manager.login("k.bichkov@mail.ru", "best"); // not correct try 1
        manager.login("a.labuzov@mail.ru", "sumething"); // not correct try 2
        manager.login("a.labuzov@mail.ru", "samething"); // not correct try 3
        manager.login("a.labuzov@mail.ru", "sumethin"); // not correct try 4
        manager.login("a.labuzov@mail.ru", "somethinggg"); // not correct and then blocked
        manager.login("a.labuzov@mail.ru", "something"); // correct but the account is already blocked
        manager.login("k.bichkov@mail.ru", "best of the best"); // not correct try 2
        System.out.println(manager.login("k.bichkov@mail.ru", "bestie").getFullName() + " is entered!"); // correct
        manager.login("k.bichkov@mail.ru", "bes"); // not correct try 1
        manager.login("k.bichkov@mail.ru", "bestiary"); // not correct try 2
        manager.login("k.bichkov@mail.ru", "best123"); // not correct try 3
        manager.login("k.bichkov@mail.ru", "best777"); // not correct try 4
        System.out.println(manager.login("k.bichkov@mail.ru", "bestie").getFullName() + " is entered!"); // correct

        manager.removeAccount("a.labuzov@mail.ru", "something"); // success
        try {
            manager.removeAccount("k.bichkov@mail.ru", "besta"); // password is not correct
        } catch (WrongCredentialsException e) {
            System.out.println("Cannot remove account since the password is not correct.");
        }
        manager.removeAccount("k.bichkov@mail.ru", "bestie"); // success
    }
}
