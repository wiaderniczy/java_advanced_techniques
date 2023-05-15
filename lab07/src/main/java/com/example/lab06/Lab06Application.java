package com.example.lab06;


import com.example.lab06.models.Client;
import com.example.lab06.models.Installation;
import com.example.lab06.models.Payments;
import com.example.lab06.models.Pricing;
import com.example.lab06.repository.ClientRepository;
import com.example.lab06.repository.InstallationRepository;
import com.example.lab06.repository.PaymentsRepository;
import com.example.lab06.repository.PricingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import java.util.Scanner;

@SpringBootApplication
public class Lab06Application //implements CommandLineRunner
{
    public static void main(String[] args) {
        SpringApplication.run(Lab06Application.class, args);
    }
//    @Autowired
//    ClientRepository client;
//
//    @Autowired
//    InstallationRepository installation;
//
//    @Autowired
//    PaymentsRepository payments;
//
//    @Autowired
//    PricingRepository pricing;
//
//    static String input;
//
//    Scanner in = new Scanner(System.in);
//
//    public static void main(String[] args) {
//        SpringApplication.run(Lab06Application.class, args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        int choice = 0;
//        while (choice != 9){
//            System.out.println("What table you want to perform actions on?\n"
//                    + "1. Clients\n"
//                    + "2. Installations\n"
//                    + "3. Payments\n"
//                    + "4. Pricing\n"
//                    + "9. Return\n");
//            choice = in.nextInt();
//            int choice2;
//            switch (choice) {
//                case 1:
//                    choice2 = submenu();
//                    if (choice2 == 9) break;
//                    clientOperations(choice2);
//                    break;
//                case 2:
//                    choice2 = submenu();
//                    if (choice2 == 9) break;
//                    installationsOperations(choice2);
//                    break;
//                case 3:
//                    choice2 = submenu();
//                    if (choice2 == 9) break;
//                    paymentsOperations(choice2);
//                    break;
//                case 4:
//                    choice2 = submenu();
//                    if (choice2 == 9) break;
//                    pricingOperations(choice2);
//                    break;
//                case 9:
//                default:
//            }
//        }
//    }
//
//    private int submenu(){
//        System.out.println("1. Insert data\n"
//                + "2. Delete data\n"
//                + "3. Print data\n"
//                + "4. Update table\n"
//                + "9. Return\n");
//        Scanner in = new Scanner(System.in);
//        return in.nextInt();
//    }
//
//    private void clientOperations(int choice){
//        in.nextLine();
//        switch (choice){
//            case 1:
//                System.out.println("Type in the data you want to insert separated by ';': \n");
//                String[] item = in.nextLine().split(";");
//                Client entity = new Client(item[0], item[1]);
//                client.save(entity);
//                System.out.println("Inserted entity: " + entity);
//                break;
//            case 2:
//                System.out.println("Which id do you want to delete: \n");
//                Long id = in.nextLong();
//                client.deleteById(id);
//                break;
//            case 3:
//                System.out.println(client.findAll());
//                break;
//            case 4:
//                System.out.println("Which id do you want to update: \n");
//                Long find = in.nextLong();
//                client.deleteById(find);
//                System.out.println("Type in the new data of the client separated by ';': \n");
//                in.nextLine();
//                String[] item1 = in.nextLine().split(";");
//                Client updating = new Client(item1[0], item1[1]);
//                client.save(updating);
//                break;
//        }
//    }
//
//    private void installationsOperations(int choice){
//        in.nextLine();
//        switch (choice){
//            case 1:
//                System.out.println("Type in the data you want to insert separated by ';': \n");
//                String[] item = in.nextLine().split(";");
//                Installation entity = new Installation(Integer.parseInt(item[0]), item[1], item[2]);
//                installation.save(entity);
//                System.out.println("Inserted entity: " + entity);
//                break;
//            case 2:
//                System.out.println("Which id do you want to delete: \n");
//                Long id = in.nextLong();
//                installation.deleteById(id);
//                break;
//            case 3:
//                System.out.println(installation.findAll());
//                break;
//            case 4:
//                System.out.println("Which id do you want to update: \n");
//                Long find = in.nextLong();
//                installation.deleteById(find);
//                System.out.println("Type in the new data of the client separated by ';': \n");
//                in.nextLine();
//                String[] item1 = in.nextLine().split(";");
//                Installation updating = new Installation(Integer.parseInt(item1[0]), item1[1], item1[2]);
//                installation.save(updating);
//                break;
//        }
//    }
//
//    private void paymentsOperations(int choice){
//        in.nextLine();
//        switch (choice){
//            case 1:
//                System.out.println("Type in the data you want to insert separated by ';': \n");
//                String[] item = in.nextLine().split(";");
//                Payments entity = new Payments(item[0], Double.parseDouble(item[1]), Boolean.parseBoolean(item[2]));
//                payments.save(entity);
//                System.out.println("Inserted entity: " + entity);
//                break;
//            case 2:
//                System.out.println("Which id do you want to delete: \n");
//                Long id = in.nextLong();
//                payments.deleteById(id);
//                break;
//            case 3:
//                System.out.println(payments.findAll());
//                break;
//            case 4:
//                System.out.println("Which id do you want to update: \n");
//                Long find = in.nextLong();
//                payments.deleteById(find);
//                System.out.println("Type in the new data of the client separated by ';': \n");
//                in.nextLine();
//                String[] item1 = in.nextLine().split(";");
//                Payments updating = new Payments(item1[0], Double.parseDouble(item1[1]), Boolean.parseBoolean(item1[2]));
//                payments.save(updating);
//                break;
//        }
//    }
//
//    private void pricingOperations(int choice){
//        in.nextLine();
//        switch (choice){
//            case 1:
//                System.out.println("Type in the data you want to insert separated by ';': \n");
//                String[] item = in.nextLine().split(";");
//                Pricing entity = new Pricing(Integer.parseInt(item[0]), Double.parseDouble(item[1]));
//                pricing.save(entity);
//                System.out.println("Inserted entity: " + entity);
//                break;
//            case 2:
//                System.out.println("Which id do you want to delete: \n");
//                String id = in.nextLine();
//                pricing.deleteById(id);
//                break;
//            case 3:
//                System.out.println(pricing.findAll());
//                break;
//            case 4:
//                System.out.println("Which id do you want to update: \n");
//                String find = in.nextLine();
//                pricing.deleteById(find);
//                System.out.println("Type in the new data of the client separated by ';': \n");
//                in.nextLine();
//                String[] item1 = in.nextLine().split(";");
//                Pricing updating = new Pricing(Integer.parseInt(item1[0]), Double.parseDouble(item1[1]));
//                pricing.save(updating);
//                break;
//        }
//    }

}
