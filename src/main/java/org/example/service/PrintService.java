package org.example.service;

import java.util.List;

import org.example.models.*;

public class PrintService {
    public static void printMenu(String title, String[] menuArr){
        int num = 1;
        System.out.println(title);
        for (int i = 0; i < menuArr.length; i++) {
            if (i == (menuArr.length - 1)) {   
                num = 0;
            }
            System.out.println(num + ". " + menuArr[i]);   
            num++;
        }
    }

    public String printServices(List<Service> serviceList){
        String result = "";
        // Bisa disesuaikan kembali
        for (Service service : serviceList) {
            result += service.getServiceName() + ", ";
        }
        return result;
    }

    // Function yang dibuat hanya sebgai contoh bisa disesuaikan kembali
    public void showRecentReservation(List<Reservation> reservationList){
        int num = 1;
        System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("+========================================================================================+");
        for (Reservation reservation : reservationList) {
            if (reservation.getWorkstage().equalsIgnoreCase("Waiting") || reservation.getWorkstage().equalsIgnoreCase("In process")) {
                System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), reservation.getReservationPrice(), reservation.getEmployee().getName(), reservation.getWorkstage());
                num++;
            }
        }
    }

    public void showAllCustomer(List<Person> personList){
        int num = 1;
        System.out.printf("| %-4s | %-7s | %-11s | %-15s | %-15s | %-15s |\n",
                            "No.", "ID", "Nama", "Alamat", "Membership", "Uang");
        System.out.println("+========================================================================================+");
        for (Person list : personList) {
            if (list instanceof Customer) {
                System.out.printf("| %-4s | %-7s | %-11s | %-15s | %-15s | %-15s |\n",
                        num, list.getId(), list.getName(),list.getAddress(), ((Customer)list).getMember().getMembershipName(), "Rp." + ((Customer)list).getWallet());
                num++;
            }
        }
    }

    public void showAllEmployee(List<Person> personList){
        int num = 1;
        System.out.printf("| %-4s | %-7s | %-11s | %-15s | %-15s |\n",
                "No.", "ID", "Nama", "Alamat", "Pengalaman (Tahun)");
        System.out.println("+========================================================================================+");
        for (Person list : personList) {
            if (list instanceof Employee) {
                System.out.printf("| %-4s | %-7s | %-11s | %-15s | %-15s |\n",
                        num, list.getId(), list.getName(),list.getAddress(), ((Employee)list).getExperience());
                num++;
            }
        }
    }

    public void showHistoryReservation(List<Reservation> reservationList){
        int num = 1;
        double keuntungan = 0;
        System.out.printf("| %-4s | %-7s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        for (Reservation reservation : reservationList) {
            if(reservation.getWorkstage().equalsIgnoreCase("Finish")){
                keuntungan += reservation.getReservationPrice();
            }

            System.out.printf("| %-4s | %-7s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                    num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), "Rp." + reservation.getReservationPrice(), reservation.getEmployee().getName(), reservation.getWorkstage());
            num++;
        }
    }
    public static void showAllService(List<Service> listAllService){

        int num = 1;
        System.out.println("+------------------------------------------------------+");
        System.out.printf("| %-4s | %-7s | %-16s | %-15s |\n",
                "No.", "ID", "Nama", "Harga");
        System.out.println("+======================================================+");
        for (Service listService: listAllService) {
            System.out.printf("| %-4s | %-7s | %-16s | %-15s |\n",
                    num, listService.getServiceId(), listService.getServiceName(), "Rp." + listService.getPrice());
            num++;
        }

        System.out.println("+------------------------------------------------------+");
    }
}
