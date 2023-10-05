package org.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.example.models.Person;
import org.example.models.Reservation;
import org.example.models.Service;
import org.example.repositories.PersonRepository;
import org.example.repositories.ServiceRepository;

public class MenuService {
    static List<Person> personList = PersonRepository.getAllPerson();
    static List<Service> serviceList = ServiceRepository.getAllService();
    private static List<Reservation> reservationList = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    public static void mainMenu() {
        String[] mainMenuArr = {"Show Data", "Create Reservation", "Complete/cancel reservation", "Exit"};
        String[] subMenuArr = {"Recent Reservation", "Show Customer", "Show Available Employee", "Back to main menu"};
    
        int optionMainMenu;
        int optionSubMenu;
        int reserveID = 1;

		boolean backToMainMenu = false;
        boolean backToSubMenu = false;
        do {
            PrintService.printMenu("Main Menu", mainMenuArr);
            optionMainMenu = Integer.valueOf(input.nextLine());
            PrintService service = new PrintService();
            switch (optionMainMenu) {
                case 1:
                    do {
                        PrintService.printMenu("Show Data", subMenuArr);
                        optionSubMenu = Integer.valueOf(input.nextLine());
                        // Sub menu - menu 1
                        switch (optionSubMenu) {
                            case 1:
                                // panggil fitur tampilkan recent reservation
                                service.showRecentReservation(reservationList);
                                break;
                            case 2:
                                // panggil fitur tampilkan semua customer
                                service.showAllCustomer(personList);
                                break;
                            case 3:
                                // panggil fitur tampilkan semua employee
                                service.showAllEmployee(personList);
                                break;
                            case 4:
                                // panggil fitur tampilkan history reservation + total keuntungan
                                service.showHistoryReservation(reservationList);
                                break;
                            default:
                                backToSubMenu = false;
                                break;
                        }
                    } while (backToSubMenu);
                    break;
                case 2:
                    // panggil fitur menambahkan reservation
                    List<Service> services = new ArrayList<>(serviceList);
                    reservationList.addAll(ReservationService.createReservation(personList, services, reserveID));
                    reserveID++;
                    break;
                case 3:
                    // panggil fitur mengubah workstage menjadi finish/cancel
                    ReservationService.editReservationWorkstage(reservationList);
                    break;
                default:
                    backToMainMenu = false;
                    break;
            }
        } while (backToMainMenu);
        System.out.println("Terima kasih sudah memakai aplikasi kami");
	}
}
