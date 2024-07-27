import java.util.Scanner;
import Business.Business;
import HashTable.HashTable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;

public class Main {
    private static HashTable hashTable;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        hashTable = new HashTable(99017);
        int choice = 0;

        while (choice != 9) {
            System.out.println("Ingrese una opción:");
            System.out.println("1. Cargar los datos usando hash de multiplicación con listas enlazadas");
            System.out.println("2. Cargar los datos usando hash por división con listas enlazadas");
            System.out.println("3. Buscar por ID usando hash de multiplicación con listas enlazadas");
            System.out.println("4. Buscar por ID usando hash por división con listas enlazadas");
            System.out.println("5. Cargar los datos usando hash de multiplicación con HashMap");
            System.out.println("6. Cargar los datos usando hash por división con HashMap");
            System.out.println("7. Buscar por ID usando hash de multiplicación con HashMap");
            System.out.println("8. Buscar por ID usando hash por división con HashMap");
            System.out.println("9. Salir");
            
            try{
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        loadDataSet(true, true);
                        break;
                    case 2:
                        loadDataSet(false, true);
                        break;
                    case 3:
                        searchByID(true, true);
                        break;
                    case 4:
                        searchByID(false, true);
                        break;
                    case 5:
                        loadDataSet(true, false);
                        break;
                    case 6:
                        loadDataSet(false, false);
                        break;
                    case 7:
                        searchByID(true, false);
                        break;
                    case 8:
                        searchByID(false, false);
                        break;
                    case 9:
                        break;
                    default:
                        System.out.println("Ingrese una opcion valida.");
                }
                
            }catch(InputMismatchException e){
                System.out.println("Ingrese una opcion valida");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void loadDataSet(boolean useMultiplication, boolean useLinkedList) {
        String line = "";
        String come = ",";
        long startTime, endTime;

        try (BufferedReader br = new BufferedReader(new FileReader("./src/resources/bussines.csv"))) {
            startTime = System.nanoTime();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(come);
                String id = data[0];
                String name = data[1];
                String address = data[2];
                String city = data[3];
                String state = data[4];

                Business business = new Business(id, name, address, city, state);

                hashTable.insert(business, useMultiplication, useLinkedList);
            }
            endTime = System.nanoTime();
            long time = endTime - startTime;
            System.out.println("Tiempo de carga: " + time + " ns");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void searchByID(boolean useMultiplication, boolean useLinkedList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID que desea a buscar:");
        String searchId = scanner.nextLine();

        long startTime = System.nanoTime();
        Business foundBusiness = hashTable.search(searchId, useMultiplication, useLinkedList);
        long endTime = System.nanoTime();
        System.out.println("Tiempo de busqueda: " + (endTime - startTime) + " ns");

        if (foundBusiness != null) {
            System.out.println("Datos encontrados: \n" + foundBusiness);
        } else {
            System.out.println("No se encontraron datos.");
        }
    }
}
