package com.company;

import java.sql.*;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try
        {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://172.31.73.192:3306/world", "root", "theking12");

            Statement s = conexion.createStatement();

            DatabaseMetaData meta = conexion.getMetaData();

            int aux;
            int opcion;

            System.out.println("BIENVENIDO AL MENU DE LA BD DE WORLD");
            System.out.println("PARA ACCEDER A LA SECCION DE CONSULTAS PULSE                    ---- 1");
            System.out.println("PARA ACCEDER A LA SECCION DE MODIFICACIONES PULSE               ---- 2");
            System.out.println("PARA ACCEDER A LA SECCION DE INFORMACION SOBRE LA BD PULSE      ---- 3");
            aux = sc.nextInt();
            switch (aux) {

                case 1:

                    System.out.println("BIENVENIDO AL MENU DE SELECCIONES");
                    System.out.println("PARA VER CIUDADES PULSE                         ---- 1");
                    System.out.println("PARA VER PAISES PULSE                           ---- 2");
                    System.out.println("PARA VER LOS LENGUAJES SEGUN EL PAIS PULSE      ---- 3");
                    opcion = sc.nextInt();

                    switch (opcion) {

                        case 1:

                            int idciudad;

                            System.out.println("Dame el id de la ciudad que quieras consultar");
                            idciudad = sc.nextInt();
                            ResultSet rs = s.executeQuery("select * from city where (id = " + idciudad + " )");

                            while (rs.next()) {
                                System.out.println("\nID: " + rs.getInt(1) + "\nNombre: " + rs.getString(2) + "\nPais: " + rs.getString(3) + "\nProvincia: " + rs.getString(4) + "\nPoblacion: " + rs.getInt(5) + " habitantes");
                            }
                            break;

                        case 2:

                            ResultSet rs1 = s.executeQuery("select * from country");

                            while (rs1.next()) {
                                System.out.println("\n" + rs1.getString(1) + " \n Capital: " + rs1.getString(2) + " \n Continente: " + rs1.getString(3) + " \n Ubicacion: " + rs1.getString(4));
                            }
                            break;

                        case 3:

                            ResultSet rs2 = s.executeQuery("select * from countrylanguage");

                            while (rs2.next()) {
                                System.out.println("\n Pais:" + rs2.getString(1) + "\n Idioma: " + rs2.getString(2) + "\n  " + rs2.getString(3) + "\n " + rs2.getInt(4));
                            }
                            break;
                    }

                case 2:

                    System.out.println("BIENVENIDO AL MENU DE MODIFICACIONES");
                    System.out.println("PARA BORRAR UNA CIUDAD PULSE                                      ---- 4");
                    System.out.println("PARA MODIFICAR LA POBLACION DE UNA CIUDAD PULSE                   ---- 5");

                    opcion = sc.nextInt();

                    switch (opcion) {

                        case 4:

                            int Nid;

                            System.out.println("Escribe el id de la ciudad que quieres borrar");
                            Nid = sc.nextInt();
                            ResultSet rs3 = s.executeQuery("select * from city where id =" + Nid);

                            break;

                        case 5:

                            System.out.println("Escribe el id de la ciudad que quieres modificar su poblacion");
                            Nid = sc.nextInt();
                            System.out.println("Escribe el nuevo numero de habitantes de dicha poblacion");
                            int habitantes = sc.nextInt();
                            ResultSet rs4 = s.executeQuery("UPDATE City SET Population=" + habitantes + " WHERE id=" + Nid);

                            break;
                    }

                case 3:

                    System.out.println("BIENVENIDO AL MENU DE INFORMACION DE LA BASE DE DATOS");
                    System.out.println("PARA VER EL NOMBRE Y LA VERSION PULSE                                                 ---- 6");
                    System.out.println("PARA VER LAS TABLAS QUE EXISTEN PULSE                                                 ---- 7");

                    opcion = sc.nextInt();

                    switch (opcion) {

                        case 6:

                            System.out.println("ElSGBDes:");
                            System.out.println(meta.getDatabaseProductName());
                            System.out.println(meta.getDatabaseProductVersion());
                            conexion.close();

                            break;

                        case 7:


                            String   catalog          = null;
                            String   schemaPattern    = null;
                            String   tableNamePattern = null;
                            String[] types            = null;

                            ResultSet result = meta.getTables(
                                    catalog, schemaPattern, tableNamePattern, types );
                            System.out.println("Las tablas en esta base de datos son: ");
                            while(result.next()) {
                                String tableName = result.getString(3);

                                System.out.println(tableName);
                            }

                            break;
                    }
            }

            conexion.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
