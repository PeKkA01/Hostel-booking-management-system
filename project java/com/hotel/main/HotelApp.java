package com.hotel.main;

import com.hotel.services.BookingService;
import com.hotel.services.ReportService;

import java.util.Scanner;

public class HotelApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BookingService bookingService = new BookingService();
        ReportService reportService = new ReportService();

        int choice;

        do {
            System.out.println("\n===== HOTEL BOOKING SYSTEM =====");
            System.out.println("1. View Rooms by Type");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Search Room by Type");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {

                case 1:
                    System.out.print("Enter room type (Single/Double/Suite): ");
                    String viewType = sc.nextLine();
                    bookingService.searchRoomByType(viewType);
                    break;

                case 2:
                    System.out.print("Enter your name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter room type (Single/Double/Suite): ");
                    String type = sc.nextLine();

                    System.out.print("Check-in (YYYY-MM-DD): ");
                    String checkIn = sc.nextLine();

                    System.out.print("Check-out (YYYY-MM-DD): ");
                    String checkOut = sc.nextLine();

                    bookingService.bookRoom(name, type, checkIn, checkOut);
                    break;

                case 3:
                    System.out.print("Enter Room Number to Cancel: ");
                    int rn = sc.nextInt();
                    bookingService.cancelBooking(rn);
                    break;

                case 4:
                    System.out.print("Enter type to search (Single/Double/Suite): ");
                    String t = sc.nextLine();
                    bookingService.searchRoomByType(t);
                    break;

                case 5:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 5);

        sc.close();
    }
}
