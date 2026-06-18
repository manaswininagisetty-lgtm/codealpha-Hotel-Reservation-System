import java.io.*;
import java.util.*;

public class Hotel {

    private ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations;

    public Hotel() {

        rooms = new ArrayList<>();
        reservations = new ArrayList<>();

        rooms.add(new Room(101, "Standard", 1500));
        rooms.add(new Room(102, "Standard", 1500));

        rooms.add(new Room(201, "Deluxe", 2500));
        rooms.add(new Room(202, "Deluxe", 2500));

        rooms.add(new Room(301, "Suite", 4000));
        rooms.add(new Room(302, "Suite", 4000));
    }

    public void displayRooms() {

        System.out.println("\nAvailable Rooms");

        for (Room room : rooms) {

            if (room.isAvailable()) {

                System.out.println(
                        room.getRoomNumber() + " | "
                                + room.getRoomType() + " | Rs."
                                + room.getPrice());
            }
        }
    }

    public void bookRoom(Scanner sc) {

        displayRooms();

        System.out.print("\nEnter Room Number: ");
        int roomNo = sc.nextInt();
        sc.nextLine();

        Room selectedRoom = null;

        for (Room room : rooms) {

            if (room.getRoomNumber() == roomNo
                    && room.isAvailable()) {

                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {

            System.out.println("Room not available!");
            return;
        }

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        System.out.println(
                "\nPayment Successful: Rs."
                        + selectedRoom.getPrice());

        selectedRoom.setAvailable(false);

        Reservation reservation =
                new Reservation(
                        name,
                        selectedRoom.getRoomNumber(),
                        selectedRoom.getRoomType(),
                        selectedRoom.getPrice());

        reservations.add(reservation);

        saveBooking(reservation);

        System.out.println("Booking Confirmed!");
    }

    public void cancelBooking(Scanner sc) {

        System.out.print("Enter Room Number: ");
        int roomNo = sc.nextInt();

        Iterator<Reservation> iterator =
                reservations.iterator();

        while (iterator.hasNext()) {

            Reservation reservation =
                    iterator.next();

            if (reservation.getRoomNumber()
                    == roomNo) {

                iterator.remove();

                for (Room room : rooms) {

                    if (room.getRoomNumber()
                            == roomNo) {

                        room.setAvailable(true);
                    }
                }

                System.out.println(
                        "Booking Cancelled Successfully!");

                return;
            }
        }

        System.out.println("No Booking Found!");
    }

    public void viewBookings() {

        if (reservations.isEmpty()) {

            System.out.println("No Bookings Found!");
            return;
        }

        System.out.println("\nBooking Details");

        for (Reservation r : reservations) {

            System.out.println(
                    "Customer : " + r.getCustomerName());

            System.out.println(
                    "Room No  : " + r.getRoomNumber());

            System.out.println(
                    "Room Type: " + r.getRoomType());

            System.out.println(
                    "Amount   : Rs." + r.getAmount());

            System.out.println("-----------------------");
        }
    }

    private void saveBooking(
            Reservation reservation) {

        try {

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(
                                    "bookings.txt",
                                    true));

            writer.write(
                    reservation.getCustomerName()
                            + ","
                            + reservation.getRoomNumber()
                            + ","
                            + reservation.getRoomType()
                            + ","
                            + reservation.getAmount());

            writer.newLine();

            writer.close();

        } catch (IOException e) {

            System.out.println(
                    "Error saving booking.");
        }
    }
}