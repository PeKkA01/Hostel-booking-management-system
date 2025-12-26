package com.hotel.services;

import com.hotel.model.Room;
import java.sql.*;
import java.util.*;

public class FileService {

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hotel_db",
                    "root",
                    "password");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Room> loadRooms() {

        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms";

        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Room room = new Room(
                        rs.getInt("room_id"),
                        rs.getString("type"),
                        rs.getDouble("price"),
                        rs.getBoolean("available"));
                rooms.add(room);
            }

        } catch (Exception e) {
            System.out.println("Error loading rooms from DB: " + e.getMessage());
        }

        return rooms;
    }

    public void updateRooms(List<Room> rooms) {

        String sql = "UPDATE rooms SET type = ?, price = ?, available = ? WHERE room_id = ?";

        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            for (Room r : rooms) {
                ps.setString(1, r.gettype());
                ps.setDouble(2, r.getPricePerNight());
                ps.setBoolean(3, r.getAvialability());
                ps.setInt(4, r.getroomID());
                ps.addBatch();
            }
            ps.executeBatch();

        } catch (Exception e) {
            System.out.println("Error updating rooms in DB: " + e.getMessage());
        }
    }
}
