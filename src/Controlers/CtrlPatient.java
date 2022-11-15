package Controlers;

import Entities.Consultation;
import Tools.ConnexionBDD;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CtrlPatient
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlPatient() {
        cnx = ConnexionBDD.getCnx();
    }

    public ArrayList<String> getAllPatients()
    {
        ArrayList<String> nomPatient = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("SELECT patient.nomPatient FROM patient");

            rs = ps.executeQuery();
            while (rs.next()) {
                nomPatient.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"On n' arrive pas pu récupérer les nom des patients");
        }
        return nomPatient;

    }
    public int getIdPatientByName(String nomPat)
    {

        return 0;
    }
}
