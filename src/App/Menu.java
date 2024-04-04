package App;
import App.Service.ServiceArt;
import App.Service.ServiceCreators;
import App.Service.ServiceExpositions;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class Menu {
    private static Menu instance;
    private Menu() {}

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
        System.out.println("1. Add a museum.");//done
        System.out.println("2. Add a painting.");//done
        System.out.println("3. Add a sculpture.");//done
        System.out.println("4. Sell an art project from a museum.");//done
        System.out.println("5. Sell a sculpture from a museum.");
        System.out.println("6. Add a new artistic movement.");//done
        System.out.println("7. Add a new author.");//done
        System.out.println("8. Add a new address.");//done
        System.out.println("9. Make a new exposition.");//done
        System.out.println("10. What is the average year of appearance for an exposition?");
        System.out.println("11. Find art projects for a specific author.");
        System.out.println("12. Check if a painting is from a specific artistic movement.");
        System.out.println("13. What is the author with the most artworks.");
        System.out.println("14. What is the heaviest sculpture in a museum.");
        System.out.println("15. Show museums.");//done
        System.out.println("16. Show authors.");//done
        System.out.println("17. Show expositions.");//done
        System.out.println("18. Show artistic movements.");//done
        System.out.println("19. Show every piece of art.");//done
        System.out.println("20. Add a piece of art to a museum.");//done
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
                case 1 -> serviceExpositions.addMuseum(objReader.readMuseum());
                case 2 -> serviceArt.addPainting(objReader.readArtProject());
                case 3-> serviceArt.addSculpture(objReader.readArtProject());
                case 4-> serviceExpositions.sellPainting(objReader.readMuseumNr(), objReader.readArtNr());
                case 5 -> service.addSanitizer(objReader.readSanitizer());
                case 6 -> serviceArt.addArtisticMovement(objReader.readArtisticMovement());
                case 7 -> serviceCreators.addAuthor(objReader.readAuthor());
                case 8 -> serviceExpositions.addAddress(objReader.readAddress());
                case 9 -> serviceExpositions.addExposition(objReader.readExposition());
                case 10 -> service.listClients();
                case 11 -> {
                    return;
                }
                case 12 -> service.addAcquisition(objReader.readAcquisition());
                case 13 -> service.listAcquisitions();
                case 14 -> {
                    return;
                }
                case 15 -> serviceExpositions.showMuseums();
                case 16 -> serviceCreators.showAuthors();
                case 17-> serviceExpositions.showExpositions();
                case 18-> serviceArt.showArtisticMovements();
                case 19-> serviceArt.showArts();
                case 20-> serviceExpositions.addArtToMuseum(objReader.readMuseumNr(), objReader.readArtNr());
                ///case 18 -> service.printLocalData(objReader);
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
