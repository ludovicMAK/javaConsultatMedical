package Controlers;

import Entities.Consultation;
import Entities.Medicament;
import Tools.ConnexionBDD;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CtrlMedicament
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlMedicament() {
        cnx = ConnexionBDD.getCnx();
    }

    public ArrayList<Medicament> GetAllMedicamentsByIdConsultations(int idConsultation)
    {
        ArrayList<Medicament> lesMedicaments = new ArrayList<>();
        try {
            ps = cnx.prepareStatement("SELECT medicament.idMedoc,medicament.nomMedoc,SUM(medicament.prixMedoc*prescrire.quantite) FROM medicament,prescrire WHERE prescrire.numMedoc = medicament.idMedoc AND prescrire.numConsult = ?");
            ps.setInt(1,idConsultation);
            rs = ps.executeQuery();
            while (rs.next()) {
                Medicament unMedicaments = new Medicament(rs.getInt(1),rs.getString(2),rs.getInt(3));
                lesMedicaments.add(unMedicaments);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"On n' arrive pas à afficher les médicament selon la consultation");
        }
        return lesMedicaments;

    }
    public ArrayList<Medicament> getAllMedicaments()
    {

        return null;
    }
}
