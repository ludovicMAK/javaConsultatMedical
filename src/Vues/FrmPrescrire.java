package Vues;

import Controlers.*;
import Tools.ModelJTable;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FrmPrescrire extends JFrame
{
    private JPanel pnlRoot;
    private JLabel lblTitre;
    private JLabel lblNumero;
    private JLabel lblDate;
    private JLabel lblNomMedecin;
    private JTextField txtNumeroConsultation;
    private JComboBox cboPatients;
    private JComboBox cboMedecins;
    private JButton btnInserer;
    private JTable tblMedicaments;
    private JPanel pnlDate;
    private JLabel lblNomPatient;
    private JLabel lblMedicaments;
    private JDateChooser dcDateConsultation;
    private CtrlConsultation ctrlConsul;
    private CtrlMedecin ctrlMed;
    private CtrlPatient ctrlPat;
    private ModelJTable mld;
    private CtrlMedicament ctrlMedica;


    public FrmPrescrire()
    {
        this.setTitle("Prescrire");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                dcDateConsultation = new JDateChooser();
                dcDateConsultation.setDateFormatString("yyyy-MM-dd");
                pnlDate.add(dcDateConsultation);


                // A vous de jouer
                ctrlConsul = new CtrlConsultation();
                ctrlMed = new CtrlMedecin();
                ctrlPat = new CtrlPatient();
                ctrlMedica = new CtrlMedicament();
                mld = new ModelJTable();
                txtNumeroConsultation.setText(String.valueOf(ctrlConsul.getLastNumberOfConsultation()));
                for(String nomMed:ctrlMed.getAllMedecins()){
                    cboMedecins.addItem(nomMed);
                }
                for(String nomPat:ctrlPat.getAllPatients()){
                    cboPatients.addItem(nomPat);
                }
                mld.loadMedicaments(ctrlMedica.getAllMedicaments());
                tblMedicaments.setModel(mld);



            }
        });
        btnInserer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // A vous de jouer
                if (dcDateConsultation.getDate() == null){
                    JOptionPane.showMessageDialog(null,"Veuillez saisir une date SVP");
                }
                else {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String dateConsultat = sdf.format(dcDateConsultation.getDate());
                    int i = 0;
                    while (i<tblMedicaments.getRowCount()){
                        if (Integer.parseInt(tblMedicaments.getValueAt(i,3).toString())>0){
                            ctrlConsul.InsertConsultation(ctrlConsul.getLastNumberOfConsultation(),dateConsultat,ctrlPat.getIdPatientByName(cboPatients.getSelectedItem().toString()),ctrlMed.getIdMedecinByName(cboMedecins.getSelectedItem().toString()));
                        }
                        i++;
                    }
                }


            }
        });
    }
}
