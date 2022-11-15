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

public class CtrlConsultation
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlConsultation() {
        cnx = ConnexionBDD.getCnx();
    }

    public ArrayList<Consultation> GetAllConsultations()
    {
        ArrayList<Consultation> lesConsultats = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("SELECT DISTINCT consultation.idConsult,consultation.dateConsult,patient.nomPatient,medecin.nomMedecin FROM medecin,patient,consultation,prescrire,medicament,vignette WHERE medecin.idMedecin = patient.numMedecinReferent AND patient.idPatient = consultation.numPatient ORDER BY consultation.idConsult ASC");

            rs = ps.executeQuery();
            while (rs.next()) {
                Consultation uneconsultation = new Consultation(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),0.5);
                lesConsultats.add(uneconsultation);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"On n' arrive pas à afficher les consultat");
        }
        return lesConsultats;

    }
    public int getLastNumberOfConsultation()
    {
        int sommeConsulat = 0;
        try {
            ps = cnx.prepareStatement("SELECT COUNT(consultation.idConsult) FROM consultation");

            rs = ps.executeQuery();
            rs.next();
            sommeConsulat = rs.getInt(1)+1;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"On n' a pas pu afficher la somme des consultat");
        }
        return sommeConsulat;

    }
    public void InsertConsultation(int idConsult, String dateConsultation, int numPatient,int numMedecin)
    {
        try {
            ps = cnx.prepareStatement("INSERT INTO `consultation` (`idConsult`, `dateConsult`, `numPatient`, `numMedecin`) VALUES (?,?,?,?);");
            ps.setInt(1, idConsult);
            ps.setString(2, dateConsultation);
            ps.setInt(3, numPatient);
            ps.setInt(4, numMedecin);
            ps.executeUpdate();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"On n' a pas pu insérer une consultation");
        }


    }
}
