package Vues;

import Controlers.CtrlConsultation;
import Controlers.CtrlMedicament;
import Tools.ModelJTable;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmConsulter extends JFrame
{
    private JPanel pnlRoot;
    private JLabel lblTitre;
    private JLabel lblConsultations;
    private JTable tblConsultations;
    private JLabel lblMedicaments;
    private JTable tblMedicaments;
    private ModelJTable mld;
    private CtrlConsultation ctrlConsult;
    private CtrlMedicament ctrlMed;

    public FrmConsulter()
    {
        this.setTitle("Consulter");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);



        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                // A vous de jouer
                mld = new ModelJTable();
                ctrlConsult = new CtrlConsultation();
                mld.loadConsultat(ctrlConsult.GetAllConsultations());
                tblConsultations.setModel(mld);
            }
        });
        tblConsultations.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // A vous de jouer
                mld = new ModelJTable();
                ctrlMed = new CtrlMedicament();
                mld.loadMedicamentSelonConsultat(ctrlMed.GetAllMedicamentsByIdConsultations(Integer.parseInt(tblConsultations.getValueAt(tblConsultations.getSelectedRow(),0).toString())));
                tblMedicaments.setModel(mld);

            }
        });
    }
}
