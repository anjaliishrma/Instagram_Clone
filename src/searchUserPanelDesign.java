/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anchal
 */
public class searchUserPanelDesign extends javax.swing.JPanel {

    /**
     * Creates new form searchUserPanelDesign
     */
    public searchUserPanelDesign() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jusername = new javax.swing.JLabel();
        jphoto = new javax.swing.JLabel();
        jfollow = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 153, 255));
        setLayout(null);

        jusername.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jusername.setText("jLabel1");
        add(jusername);
        jusername.setBounds(10, 30, 90, 20);

        jphoto.setText("jLabel2");
        add(jphoto);
        jphoto.setBounds(10, 60, 190, 40);

        jfollow.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jfollow.setText("Follow");
        add(jfollow);
        jfollow.setBounds(260, 30, 140, 40);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jfollow;
    public javax.swing.JLabel jphoto;
    public javax.swing.JLabel jusername;
    // End of variables declaration//GEN-END:variables
}
