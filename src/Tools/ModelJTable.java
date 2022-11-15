package Tools;



import Entities.Consultation;
import Entities.Medicament;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModelJTable extends AbstractTableModel {
    private String[] colonnes;
    private Object[][] lignes;

    @Override
    public String getColumnName(int column) {
        return colonnes[column];
    }

    @Override
    public int getRowCount() {
        return lignes.length;
    }

    @Override
    public int getColumnCount() {
        return colonnes.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return lignes[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        lignes[row][column] = value;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 3 ;
    }
    public void loadConsultat(ArrayList<Consultation> lesConsultation){
        colonnes = new  String[]{"Numéro","Date","Nom patient","Nom Medecin","Montant"};
        lignes = new Object[lesConsultation.size()][5];
        int i =0;
        for(Consultation consult:lesConsultation){
            lignes[i][0] = consult.getNumero();
            lignes[i][1] = consult.getDate();
            lignes[i][2] = consult.getNomPatient();
            lignes[i][3] = consult.getNomMedecin();
            lignes[i][4] = consult.getMontant();
            i++;
        }
        fireTableChanged(null);
    }
    public void loadMedicamentSelonConsultat(ArrayList<Medicament> lesMedicaments){
        colonnes = new  String[]{"Numéro","Nom","Prix"};
        lignes = new Object[lesMedicaments.size()][3];
        int i =0;
        for(Medicament med:lesMedicaments){
            lignes[i][0] = med.getNumero();
            lignes[i][1] = med.getNom();
            lignes[i][2] = med.getPrix();

            i++;
        }
        fireTableChanged(null);
    }


}
