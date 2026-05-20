package org.example;

import oracle.net.jdbc.TNSAddress.SOException;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String insert = "INSERT INTO EMPLEADO VALUES (?,?,?)";

        try (Connection conn = DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUser(),
                DBConfig.getPassword())) {
            /**
             * Pone a false el autoCommit
             */
            conn.setAutoCommit(false);

            System.out.println("Indique cuantos empleados desea añadir");
            int empleados =  sc.nextInt();
            sc.nextLine();

            try (PreparedStatement ps = conn.prepareStatement(insert)) {
                for (int i = 1; i <= empleados; i++) {
                    System.out.println("Indique el id del empleado");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Indique el nombre del empleado");
                    String nombre = sc.nextLine();
                    System.out.println("Indique el salario del empleado");
                    double salario = sc.nextDouble();
                    sc.nextLine();
                    ps.setInt(1, id);
                    ps.setString(2, nombre);
                    ps.setDouble(3, salario);
                    ps.executeUpdate();
                    System.out.println("Empleado " + nombre + " añadido correctamente");
                }
                System.out.println("Empleados añadidos correctamente. Realizando un commit");
                conn.commit();
            } catch (SQLException ex) {
                System.out.println("Error al añadir el empleado. Realizando rollback");
                conn.rollback();
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }
}