package com.hotel.services;

import java.sql.*;

public class BookingService {

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hotel_db",
                    "root",
                    "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int findAvailableRoom(String type) {

        String sql = "SELECT room_id FROM rooms WHERE type = ? AND available = true LIMIT 1";

        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("room_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void bookRoom(String customerName, String type, String checkIn, String checkOut) {

        int roomId = findAvailableRoom(type);

        if (roomId == -1) {
            System.out.println("NO " + type + " rooms available");
            return;
        }

        String sql = "UPDATE rooms SET available = false WHERE room_id = ?";

        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, roomId);
            ps.executeUpdate();

            System.out.println("Room booked successfully");
            System.out.println("Customer Name: " + customerName);
            System.out.println("Room ID: " + roomId);
            System.out.println("Check-in: " + checkIn);
            System.out.println("Check-out: " + checkOut);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelBooking(int roomNumber) {

        String checkSql = "SELECT available FROM rooms WHERE room_id = ?";
        String updateSql = "UPDATE rooms SET available = true WHERE room_id = ?";

        try (Connection con = getConnection();
                PreparedStatement checkPs = con.prepareStatement(checkSql)) {

            checkPs.setInt(1, roomNumber);
            ResultSet rs = checkPs.executeQuery();

            if (!rs.next()) {
                System.out.println("Room number does not exist!");
                return;
            }

            if (rs.getBoolean("available")) {
                System.out.println("Room is already available. No booking to cancel.");
                return;
            }

            PreparedStatement updatePs = con.prepareStatement(updateSql);
            updatePs.setInt(1, roomNumber);
            updatePs.executeUpdate();

            System.out.println("Booking cancelled successfully for Room " + roomNumber);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchRoomByType(String type) {

        String sql = "SELECT * FROM rooms WHERE type = ?";

        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();

            boolean found = false;
            System.out.println("\n--- Rooms of Type: " + type + " ---");

            while (rs.next()) {
                System.out.println(
                        "Room No: " + rs.getInt("room_id") +
                                " | Price: " + rs.getDouble("price") +
                                " | Available: " + rs.getBoolean("available"));
                found = true;
            }

            if (!found) {
                System.out.println("No rooms found of type: " + type);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
