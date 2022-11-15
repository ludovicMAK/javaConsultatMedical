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

public class CtrlMedecin
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlMedecin() {
        cnx = ConnexionBDD.getCnx();
    }

    public ArrayList<String> getAllMedecins()
    {
        ArrayList<String> nomMedecin = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("SELECT medecin.nomMedecin FROM medecin");

            rs = ps.executeQuery();
            while (rs.next()) {
                nomMedecin.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"On n' arrive pas pu récupérer les nom des médecin");
        }
        return nomMedecin;
    }

    public int getIdMedecinByName(String nomMed)
    {

        int idMed= 0;
        try {
            ps = cnx.prepareStatement("SELECT medecin.idMedecin FROM medecin WHERE medecin.nomMedecin = ?");
            ps.setString(1,nomMed);
            rs = ps.executeQuery();
            rs.next();
            idMed = rs.getInt(1);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"On n' arrive pas pu récupérer l' id du médecin");
        }
        return idMed;
    }
}
