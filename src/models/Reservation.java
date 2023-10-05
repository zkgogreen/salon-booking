package models;

import java.util.List;

public class Reservation {
    private String reservationId;
    private Customer customer;
    private Employee employee;
    private List<Service> services;
    private double reservationPrice;

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public double getReservationPrice() {
        return reservationPrice;
    }

    public void setReservationPrice(double reservationPrice) {
        this.reservationPrice = reservationPrice;
    }

    public String getWorkstage() {
        return workstage;
    }

    public void setWorkstage(String workstage) {
        this.workstage = workstage;
    }

    private String workstage;
    //   workStage (In Process, Finish, Canceled)

    public Reservation(String reservationId, Customer customer, Employee employee, List<Service> services,
                       String workstage) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.employee = employee;
        this.services = services;
        this.reservationPrice = calculateReservationPrice();
        this.workstage = workstage;
    };

    private double calculateReservationPrice(){
        return 0;
    }

}
