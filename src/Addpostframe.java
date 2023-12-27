
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
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
public class Addpostframe extends javax.swing.JFrame {

    /**
     * Creates new form Addpostframe
     */
    File f;
    String username;

    public Addpostframe(String username) {
        initComponents();
        this.username = username;
        showpost();
        setSize(1000, 1000);
        setVisible(true);

    }

    void showpost() {
        //send unirest get requyest on a resource getAllPosts
        //with username
//        recieve response
        try {
            HttpResponse<String> res = Unirest.get("http://localhost:8999/getAllposts")
                    .queryString("username", username)
                    .asString();
            if (res.getStatus() == 200) //server working correctly
            {
                String response = res.getBody();
                System.out.println("Response is ----->" + response);
                if (response.equals("success")) {
                    JOptionPane.showMessageDialog(this, "post added Successfull");

                } else {
                    System.out.println("Respnse is" + response);

                    StringTokenizer st = new StringTokenizer(response, "~~");
                    int count = st.countTokens();
                    MainPanel.setPreferredSize(new Dimension(800, count * 250));
                    Addpostpanel obj[] = new Addpostpanel[count];
                    int x = 10, y = 10;
                    MainPanel.removeAll();
                    for (int i = 0; i < count; i++) {
                        String userinfo = st.nextToken();
                        StringTokenizer st2 = new StringTokenizer(userinfo, "!!");
                        String caption = st2.nextToken();
                        String photo = st2.nextToken();
                        int postid=Integer.parseInt(st2.nextToken());

                        System.out.println("caption--------" + caption);
                        System.out.println("Photo------------" + photo);
                        obj[i] = new Addpostpanel();
                        obj[i].jcaption.setText(caption);
                        obj[i].jphoto.setText(photo);
                        try {
                            URL url = new URL(global.ip_address + "GetResource/" + photo);
                            //System.out.println("Path of Image Stored in server side--------------" + url);
                            BufferedImage img = ImageIO.read(url);
                            Image resizedimage = img.getScaledInstance(obj[i].jphoto.getWidth(), obj[i].jphoto.getHeight(), Image.SCALE_SMOOTH);
                            obj[i].jphoto.setIcon(new ImageIcon(resizedimage));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        obj[i].jaddstory.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                //your code goes here 
                                Addstory obj = new Addstory(postid);
                                obj.setVisible(true);

                            }

                            public void actionPerformed1(ActionEvent e) {

                            }

                        });

                        obj[i].setBounds(x, y, 700, 200);
                        MainPanel.add(obj[i]);
                        y += 230;
                    }
                    MainPanel.repaint();  //refresh
                    repaint();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        MainPanel = new javax.swing.JPanel();
        jcaption = new javax.swing.JTextField();
        jphoto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("ADD POST HERE!");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(40, 60, 210, 40);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("PHOTO");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(40, 140, 80, 40);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("BROWSE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(20, 200, 100, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("CAPTION");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 313, 90, 30);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("ADD POST");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(40, 451, 140, 50);

        MainPanel.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 648, Short.MAX_VALUE)
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(MainPanel);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(340, 10, 520, 600);

        jcaption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcaptionActionPerformed(evt);
            }
        });
        getContentPane().add(jcaption);
        jcaption.setBounds(180, 320, 110, 19);

        jphoto.setText("PHOTO");
        getContentPane().add(jphoto);
        jphoto.setBounds(160, 150, 160, 90);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        String caption = jcaption.getText();
        String photo = jphoto.getText();
        if (caption.equals("")) {
            JOptionPane.showMessageDialog(this, "All fields are required");
        } else if (f == null) {
            JOptionPane.showMessageDialog(this, "All fields are required");
        } else {
            try {
                HttpResponse<String> res = Unirest.post("http://localhost:8999/useraddpost")
                        .queryString("username", username)
                        .queryString("caption", caption)
                        .field("photo", f)
                        .asString();
                if (res.getStatus() == 200) //server working correctly
                {
                    String response = res.getBody();
                    System.out.println("Response is ----->" + response);
                    if (response.equals("success")) {
                        JOptionPane.showMessageDialog(this, "Post added Successfull");

                    } else {
                        JOptionPane.showMessageDialog(this, "Cannot add the post");
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        int ans = chooser.showOpenDialog(this);
        if (ans == JFileChooser.APPROVE_OPTION) {
            f = chooser.getSelectedFile();
            ImageIcon icon = new ImageIcon(f.getPath());
            Image resizedImage = icon.getImage().getScaledInstance(jphoto.getWidth(), jphoto.getHeight(), Image.SCALE_SMOOTH);
            jphoto.setIcon(new ImageIcon(resizedImage));
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jcaptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcaptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcaptionActionPerformed

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
            java.util.logging.Logger.getLogger(Addpostframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Addpostframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Addpostframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Addpostframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Addpostframe("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextField jcaption;
    private javax.swing.JLabel jphoto;
    // End of variables declaration//GEN-END:variables
}
