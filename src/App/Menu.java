package App;
import App.Service.ServiceArt;
import App.Service.ServiceCreators;
import App.Service.ServiceExpositions;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class Menu {
    private static Menu instance;
    private Menu() {}

    // static block initialization for exception handling
    static {
        try {
            instance = new Menu();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating Menu singleton instance");
        }
    }
    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    private void textMenu()
    {
        System.out.println("-----------------------------------------------");
        System.out.println("Local museum application");
        System.out.println("Here are all the actions you can choose from:");
        System.out.println("1. Add a museum.");
        System.out.println("2. Add a painting.");
        System.out.println("3. Add a sculpture.");
        System.out.println("4. Sell a painting from a museum.");
        System.out.println("5. Sell a sculpture from a museum.");
        System.out.println("6. Add a new artistic movement.");
        System.out.println("7. Add a new author.");
        System.out.println("8. Add a new address.");
        System.out.println("9. Make a new exposition.");
        System.out.println("10. What is the average year of appearance for an exposition?");
        System.out.println("11. Find art projects for a specific author.");
        System.out.println("12. Check if a painting is from a specific artistic movement.");
        System.out.println("13. What is the author with the most artworks.");
        System.out.println("14. What is the heaviest sculpture in a museum.");
        System.out.println("15. Add artwork to a museum.");
        ///System.out.println("16. Print all local data.");
        System.out.println("0. Exit");
        System.out.println("-----------------------------------------------");
    }
    public void runMenu(){
        Scanner reader = new Scanner(System.in);
        ServiceArt serviceArt = ServiceArt.getInstance();
        ServiceCreators serviceCreators = ServiceCreators.getInstance();
        ServiceExpositions serviceExpositions = ServiceExpositions.getInstance();
        Reader objReader = Reader.getInstance();
        int op;

        do
        {
            textMenu();
            System.out.print("Please insert your option: ");
            op = reader.nextInt();
            reader.nextLine();
            System.out.println("-----------------------------------------------");
            switch (op) {
                case 1 -> service.addMask(objReader.readMask());
                case 2 -> service.listMasks();
                case 3-> service.showMask(objReader.showMask());
                case 4-> service.deleteMask(objReader.deleteMask());
                case 5 -> service.addSanitizer(objReader.readSanitizer());
                case 6 -> service.listSanitizers();
                case 7 -> service.showSanitizer(objReader.showSanitizer());
                case 8 -> service.deleteSanitizer(objReader.deleteSanitizer());
                case 9 -> service.addClient(objReader.readClient());
                case 10 -> service.listClients();
                case 11 -> {
                    try{
                        service.updateClient(objReader.getIndexForUpdate(), objReader.readClient());
                    }catch (InputMismatchException e){
                        System.out.println("Invalid input!");
                    }
                }
                case 12 -> service.addAcquisition(objReader.readAcquisition());
                case 13 -> service.listAcquisitions();
                case 14 -> {
                    try {
                        service.incomeDate(objReader.readMonth(), objReader.readYear());
                    }catch (InputMismatchException e){
                        System.out.println("Invalid input!");
                    }
                }
                case 15 -> {
                    try{
                        service.VAT(objReader.readYear());
                    }catch (InputMismatchException e){
                        System.out.println("Invalid input!");
                    }
                }
                case 16 -> service.sortedSanitizers();
                case 17-> service.configureTables();
                ///case 18 -> service.printLocalData();
                case 0 -> {
                    service.closeConnection();
                    System.out.println("You left the app. Goodbye!");
                }

                default -> System.out.println("Invalid option!");
            }

        } while (op != 0);

        reader.close();
    }
}
