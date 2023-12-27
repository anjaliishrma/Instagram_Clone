
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Anchal
 */
public class MyFollowing extends javax.swing.JFrame {

    String username = "";
    String photo = "";

    /**
     * Creates new form MyFollowing
     */
    public MyFollowing(String n) {
        username = n;
        initComponents();
        getfollowing();

    }

    public void getfollowing() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    HttpResponse<String> res = Unirest.get("http://localhost:8999/myfollowing")
                            .queryString("username", username)
                            .asString();
                    if (res.getStatus() == 200) {
                        String ans = res.getBody();
                        if (ans.equals("fails")) {
                            JOptionPane.showMessageDialog(rootPane, "No following");

                        } else {
                            System.out.println("Respnse is" + ans);

                            StringTokenizer st = new StringTokenizer(ans, "&&");
                            int count = st.countTokens();
                            mainPanel1.setPreferredSize(new Dimension(800, count * 250));
                            myfollowingpaneldesign obj[] = new myfollowingpaneldesign[count];
                            int x = 10, y = 10;
                            mainPanel1.removeAll();
                            for (int i = 0; i < count; i++) {
                                String userinfo = st.nextToken();
                                StringTokenizer st2 = new StringTokenizer(userinfo, ";;");
                                String user = st2.nextToken();
                                String photo = st2.nextToken();
                                String followerid = st2.nextToken();
                                System.out.println("username--------" + username);
                                System.out.println("Photo------------" + photo);
                                obj[i] = new myfollowingpaneldesign();

                                obj[i].jremove.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        try {
                                            HttpResponse<String> res = Unirest.get("http://localhost:8999/deletefollowing")
                                                    .queryString("followerid", followerid)
                                                    .asString();
                                            if (res.getStatus() == 200) {
                                                String ans = res.getBody();
                                                if (ans.equals("success")) {
                                                    getfollowing();

                                                    //To change body of generated methods, choose Tools | Templates.
                                                    //send unirest request
                                                }
                                            }
                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                        }
                                    }

                                });
                                obj[i].jusername.setText(user);
                                obj[i].jphoto.setText(photo);
                                obj[i].setBounds(x, y, 700, 300);
                                mainPanel1.add(obj[i]);
                                y += 230;
                            }
                            mainPanel1.repaint();  //refresh
                            repaint();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        mainPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        mainPanel1.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel1.setLayout(null);
        jScrollPane1.setViewportView(mainPanel1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 100, 760, 480);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MyFollowing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MyFollowing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MyFollowing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MyFollowing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MyFollowing("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel1;
    // End of variables declaration//GEN-END:variables
}
