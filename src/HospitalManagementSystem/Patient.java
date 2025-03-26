package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    private Connection connection;
    private Scanner scanner;

    public Patient(Connection connection,Scanner scanner){
        this.connection=connection;
        this.scanner=scanner;

    }
    public void addPatient(){
        System.out.print("Enter Patient Name: ");
        String name=scanner.next();
        System.out.print("Enter Patient Age: ");
        int age=scanner.nextInt();
        System.out.print("Enter Patient Gender: ");
        String gender=scanner.next();
        try{
            String query="insert into patients(name,age,gender) values(?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,age);
            preparedStatement.setString(3,gender);
            int affectedRows=preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Patient Added Successfully!!");

            }
            else{
                System.out.println("Failded to add Patient!!");
            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void viewPatients(){
        String query="select* from patients";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            ResultSet rs=preparedStatement.executeQuery();
            System.out.println( "Patients: ");
            System.out.println("+-------------------+-------------------+-----------------+----------------+");
            System.out.println("  Patient Id        |  Name             |  Age            | Gender         |");
            System.out.println("+-------------------+-------------------+-----------------+----------------+");
            while(rs.next()){
                int id=rs.getInt("id");
                String name=rs.getString("name");
                int age=rs.getInt("age");
                String gender=rs.getString("gender");
                System.out.printf("| %-17s | %-17s | %-15s |%-14s |\n",id,name,age,gender);
                System.out.println("+-------------------+-------------------+-----------------+----------------+");

            }
        }catch(SQLException e){
            e.printStackTrace();

        }
    }
    public boolean getPatientById(int id){
        String query="select* from patients where id = ?";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet rs=preparedStatement.executeQuery();
            if(rs.next()){
                return true;
            }
            else{
                return false;
            }
        }
        catch (SQLException e){
            e.printStackTrace();;
        }
        return false;
    }

}
