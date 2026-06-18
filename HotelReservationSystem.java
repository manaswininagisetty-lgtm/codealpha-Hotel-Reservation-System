import java.util.Scanner;

public class HotelReservationSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Hotel hotel = new Hotel();

        int choice;

        do {

            System.out.println("\n=======================");
            System.out.println(" HOTEL RESERVATION ");
            System.out.println("=======================");

            System.out.println("1. View Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View Bookings");
            System.out.println("5. Exit");

            System.out.print("Enter Choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    hotel.displayRooms();
                    break;

                case 2:
                    hotel.bookRoom(sc);
                    break;

                case 3:
                    hotel.cancelBooking(sc);
                    break;

                case 4:
                    hotel.viewBookings();
                    break;

                case 5:
                    System.out.println(
                            "Thank You!");
                    break;

                default:
                    System.out.println(
                            "Invalid Choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}