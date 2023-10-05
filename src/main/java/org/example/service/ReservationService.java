package org.example.service;

import java.util.ArrayList;
import java.util.List;

import org.example.models.*;
import org.example.service.PrintService;
import org.example.service.ValidationService;

public class ReservationService {
    public static List<Reservation> createReservation(List<Person> listAllPerson, List<Service> listAllService, int numberReservID){
        List<Reservation> result = new ArrayList<Reservation>();

        String regexCustomerID = "Cust-\\d{2}", regexEmployeeID = "Emp-\\d{2}", regexServiceID = "Serv-\\d{2}";
        boolean isLooping = true;

        //logic input Customer ID
        String newCustomerID = "";
        int indexListCustomer = 0;
        Person newCustomer = new Customer();
        PrintService service = new PrintService();

        service.showAllCustomer(listAllPerson);


        do {
            isLooping = true;
            newCustomerID = ValidationService.validationOfID("Silahkan masukkan Customer ID : ", "Maaf, ID Costumer harus sesuai dengan format 'Cust-' diikuti dengan 2 digit angka ID.", regexCustomerID);
            System.out.println();
            int index = 0;
            int indexToFind = -1;

            for (Person listPerson : listAllPerson) {
                if (newCustomerID.contains(listPerson.getId())) {
                    System.out.println("Selamat!!!, Customer ID terverifikasi sehingga Customer yang anda cari tersedia.");
                    System.out.println();
                    indexListCustomer = index;
                    newCustomer = listAllPerson.get(indexListCustomer);
                    indexToFind++;
                    break;
                }

                index++;
            }

            if (indexToFind != -1) {
                isLooping = false;
                break;
            } else {
                System.out.println("Mohon maaf!!!, Customer ID  tidak valid sehingga Customer yang anda cari tidak tersedia.");
                System.out.println("Input ulang yang mulia!!!.");
                System.out.println();
            }

        } while (isLooping);

        System.out.println();

        //logic input Employee ID
        String newEmployeeID = "";
        int indexListEmployee = 0;
        Person newEmployee = new Employee();

        service.showAllCustomer(listAllPerson);


        do {
            isLooping = true;
            newEmployeeID = ValidationService.validationOfID("Silahkan masukkan Employe ID : ", "Maaf, ID Employee harus sesuai dengan format 'Emp-' diikuti dengan 2 digit angka ID.", regexEmployeeID);
            System.out.println();
            int index = 0;
            int indexToFind = -1;

            for (Person listPerson : listAllPerson) {
                if (newEmployeeID.contains(listPerson.getId())) {
                    System.out.println("Selamat!!!, Employe ID terverifikasi sehingga Employee yang anda cari tersedia.");
                    System.out.println();
                    indexListEmployee = index;
                    newEmployee = listAllPerson.get(indexListEmployee);
                    indexToFind++;
                    break;
                }

                index++;
            }

            if (indexToFind != -1) {
                isLooping = false;
                break;
            } else {
                System.out.println("Mohon maaf!!!, Employee ID tidak valid sehingga Employee yang anda cari tidak tersedia");
                System.out.println("Input ulang yang mulia!!!.");
                System.out.println();
            }

        } while (isLooping);

        System.out.println();

        //Logic input Service ID
        String newServiceID = "";
        int indexListService = 0;
        double price = 0;
        List<Service> newService = new ArrayList<>();

        do {
            PrintService.showAllService(listAllService);
            isLooping = true;
            newServiceID = ValidationService.validationOfID("Silahkan masukkan Service ID : ","Maaf, ID Service harus sesuai dengan format 'Serv-' diikuti dengan 2 digit angka ID.", regexServiceID);
            System.out.println();
            int index = 0;
            int indexToFind = -1;
            int indexTofind2 = -1;

            for (Service listService : listAllService) {
                if (newServiceID.contains(listService.getServiceId())) {
                    System.out.println("Selamat!!!, Service ID terverifikasi sehingga layanan Service yang anda inginkan tersedia.");
                    System.out.println();
                    newService.add(listService);
                    indexListService = index;
                    price += listService.getPrice();
                    boolean isLooping2 = true;
                    indexToFind++;

                    do {
                        isLooping2 = true;
                        String choice = ValidationService.validationOfID("Apakah anda menginginkan Service yang lain juga (Y/T)? : ", "Mohon maaf, Pilihan hanya 'Y' jika ingin menambah layanan service atau 'N' jika sudah cukup.", "Y|T|y|t");
                        System.out.println();

                        if (choice.matches("Y|y")) {
                            isLooping2 = false;

                        } else {
                            indexTofind2++;
                            isLooping2 = false;
                        }

                    } while (isLooping2);
                }

                index++;
            }

            if (indexToFind != -1 && indexTofind2 != -1) {
                isLooping = false;
                break;
            }else if(indexToFind != -1){
                listAllService.remove(indexListService);
                System.out.println("Service sudah dipilih!!!");
                System.out.println("Input ulang yang mulia!!!.");
                System.out.println();
            } else {
                System.out.println("Mohon maaf!!!, Service ID tidak valid sehingga layanan Service yang anda cari tidak tersedia");
                System.out.println("Input ulang yang mulia!!!.");
                System.out.println();
            }

        } while (isLooping);

        double reservationPrice = 0;
        if (((Customer)newCustomer).getMember().getMembershipName().equals("none")) {
            reservationPrice = price;
        } else if(((Customer)newCustomer).getMember().getMembershipName().equals("Silver")){
            reservationPrice = price - (price * 5 / 100);
        } else if(((Customer)newCustomer).getMember().getMembershipName().equals("Gold")){
            reservationPrice = price - (price * 10 / 100);
        }

        System.out.println("Booking Berhasil!!!");
        System.out.println("Total biaya booking adalah : RP." + reservationPrice);
        System.out.println();

        result.add(new Reservation("Rsv-0" + numberReservID, (Customer)newCustomer, (Employee)newEmployee, newService, reservationPrice, "In Process"));

        return result;
    }

    public static void editReservationWorkstage(List<Reservation> listAllReservation){
        PrintService print = new PrintService();
        print.showRecentReservation(listAllReservation);

        String regexReservationID = "Rsv-\\d{2}";
        boolean isLooping = true;
        String newReservationID = "";

        do {
            isLooping = true;
            newReservationID = ValidationService.validationOfID("Silahkan Masukkan Reservation ID : ","Maaf, ID Reservation harus sesuai dengan format 'Rsv-' diikuti dengan 2 digit angka ID.", regexReservationID);

            for (Reservation reservation : listAllReservation) {
                if (newReservationID.contains(reservation.getReservationId())) {
                    System.out.println("Selamat!!!, Reservasi ID terverifikasi.");
                    System.out.println();
                    String workstage = ValidationService.validationOfID("Selesaikan status Reservasi!!!, Silahkan untuk melakukan pengeditan workstage : ", "Mohon maaf, pilihan harus berupa pilihan ini (Cancel/Finish)", "Cancel|Finish|cancel|finish|CANCEL|FINISH");

                    if (workstage.equalsIgnoreCase("Finish") && reservation.getWorkstage().equals("In Process")) {
                        reservation.setWorkstage("Finish");
                        System.out.println("Reservasi dengan ID " + newReservationID + " sudah finish");
                        isLooping = false;
                        break;
                    } else if (workstage.equalsIgnoreCase("Cancel") && reservation.getWorkstage().equals("In Process")){
                        reservation.setWorkstage("Cancel");
                        System.out.println("Reservasi dengan ID " + newReservationID + " sudah cancel");
                        isLooping = false;
                        break;
                    }else{
                        System.out.println("Maaf, Reservasi yang dicari sudah selesai.");
                    }
                }
            }

        } while (isLooping);
    }
}