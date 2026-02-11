/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgPenyakit.java
 *
 * Created on May 23, 2010, 12:57:16 AM
 */

package keuangan;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen
 */
public final class DlgCariDaftarOperasi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabModeDetailBHP;
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    private PreparedStatement pstindakan,psset_tarif,pstindakan2;
    private ResultSet rs,rsset_tarif;
    private int i=0;
    private String kelas_operasi="Yes",kelas="",cara_bayar_operasi="Yes",kd_pj="";
    private File file;
    private FileWriter fileWriter;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode response;
    private FileReader myObj;
    /** Creates new form DlgPenyakit
     * @param parent
     * @param modal */
    public DlgCariDaftarOperasi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(10,2);
        setSize(656,250);
        tabMode=new DefaultTableModel(null,new Object[]{"✔",
                "Kode Paket","Nama Operasi","Kategori","Operator 1","Operator 2","Operator 3",
                "Asisten Op 1","Asisten Op 2","Asisten Op 3","Instrumen","dr Anak","Perawat Resus","dr Anastesi",
                "Asisten Anast 1","Asisten Anast 2","Bidan 1","Bidan 2","Bidan 3","Perawat Luar","Alat","Sewa OK/VK",
                "Akomodasi","N.M.S.","Onloop 1","Onloop 2","Onloop 3","Onloop 4","Onloop 5",
                "Sarpras","dr Pj Anak","Tarif"
            }){
            @Override public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
             }
             Class[] types = new Class[] {
                  Boolean.class,
                 java.lang.Object.class,java.lang.Object.class,java.lang.Object.class, java.lang.Double.class, 
                 java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, 
                 java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, 
                 java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, 
                 java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, 
                 java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, 
                 java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, 
                 java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tabModeDetailBHP=new DefaultTableModel(null,new Object[]{
                "Nama BHP","Jumlah"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbDetailBHP.setModel(tabModeDetailBHP);

        tbDetailBHP.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbDetailBHP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 2; i++) {
            TableColumn column = tbDetailBHP.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(70);
            }else if(i==1){
                column.setPreferredWidth(10);
            }
        }
        tbDetailBHP.setDefaultRenderer(Object.class, new WarnaTable());
        
        tbKamar.setModel(tabMode);

        tbKamar.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbKamar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < tbKamar.getColumnCount(); i++) {
                TableColumn column = tbKamar.getColumnModel().getColumn(i);

                if (i == 0) { 
                    column.setPreferredWidth(30);
                    column.setMinWidth(30);
                    column.setMaxWidth(30);

                } else if (i == 1) { 
                    column.setPreferredWidth(100);
                    column.setMinWidth(80);
                    column.setMaxWidth(120);

                } else if (i == 2) {
                    column.setPreferredWidth(350);
                    column.setMinWidth(300);
                    column.setMaxWidth(600);

                } else if (i == 3) { 
                    column.setPreferredWidth(120);

                } else {
                    column.setMinWidth(0);
                    column.setMaxWidth(0);
                }
            }

        tbKamar.setDefaultRenderer(Object.class, new WarnaTable());
        
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil2();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil2();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil2();
                    }
                }
            });
        } 
        
        try {
            psset_tarif=koneksi.prepareStatement("select * from set_tarif");
            try {
                rsset_tarif=psset_tarif.executeQuery();
                if(rsset_tarif.next()){
                    cara_bayar_operasi=rsset_tarif.getString("cara_bayar_operasi");
                    kelas_operasi=rsset_tarif.getString("kelas_operasi");
                }else{
                    cara_bayar_operasi="Yes";
                    kelas_operasi="Yes";
                }  
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            }finally{
                if(rsset_tarif != null){
                    rsset_tarif.close();
                }
                if(psset_tarif != null){
                    psset_tarif.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        }
        
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbKamar = new widget.Table();
        panelisi3 = new widget.panelisi();
        label9 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        BtnTambah = new widget.Button();
        label10 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        FormDetailBHP = new widget.PanelBiasa();
        Scroll7 = new widget.ScrollPane();
        tbDetailBHP = new widget.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(null);
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbKamar.setAutoCreateRowSorter(true);
        tbKamar.setName("tbKamar"); // NOI18N
        tbKamar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKamarMouseClicked(evt);
            }
        });
        tbKamar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbKamarKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbKamar);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 43));
        panelisi3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(68, 23));
        panelisi3.add(label9);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(312, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi3.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('1');
        BtnCari.setToolTipText("Alt+1");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelisi3.add(BtnCari);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('2');
        BtnAll.setToolTipText("2Alt+2");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelisi3.add(BtnAll);

        BtnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambah.setMnemonic('3');
        BtnTambah.setToolTipText("Alt+3");
        BtnTambah.setName("BtnTambah"); // NOI18N
        BtnTambah.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahActionPerformed(evt);
            }
        });
        panelisi3.add(BtnTambah);

        label10.setText("Record :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi3.add(label10);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelisi3.add(LCount);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('4');
        BtnKeluar.setToolTipText("Alt+4");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        panelisi3.add(BtnKeluar);

        internalFrame1.add(panelisi3, java.awt.BorderLayout.PAGE_END);

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(470, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout(1, 1));

        ChkAccor.setBackground(new java.awt.Color(255, 250, 250));
        ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkAccor.setSelected(true);
        ChkAccor.setFocusable(false);
        ChkAccor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkAccor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkAccor.setName("ChkAccor"); // NOI18N
        ChkAccor.setPreferredSize(new java.awt.Dimension(15, 20));
        ChkAccor.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkAccor.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        ChkAccor.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        ChkAccor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAccorActionPerformed(evt);
            }
        });
        PanelAccor.add(ChkAccor, java.awt.BorderLayout.WEST);

        FormDetailBHP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)));
        FormDetailBHP.setName("FormDetailBHP"); // NOI18N
        FormDetailBHP.setLayout(new java.awt.GridLayout(1, 0, 1, 1));

        Scroll7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)));
        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);
        Scroll7.setPreferredSize(new java.awt.Dimension(454, 807));

        tbDetailBHP.setName("tbDetailBHP"); // NOI18N
        tbDetailBHP.setPreferredSize(new java.awt.Dimension(454, 807));
        Scroll7.setViewportView(tbDetailBHP);

        FormDetailBHP.add(Scroll7);

        PanelAccor.add(FormDetailBHP, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelAccor, java.awt.BorderLayout.EAST);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbKamar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil2();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
        tampil2();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCari, TCari);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbKamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKamarMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                ChkAccor.setSelected(true);
                isMenu();
                getMasalah();
            } catch (java.lang.NullPointerException e) {
            }
            if(evt.getClickCount()==2){
                dispose();
            }
        }
}//GEN-LAST:event_tbKamarMouseClicked

    private void tbKamarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbKamarKeyPressed
        if(tabMode.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                dispose();
            }else if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
                TCari.setText("");
                TCari.requestFocus();
            }
        }
}//GEN-LAST:event_tbKamarKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));        
        DlgJnsPerawatanOperasi bangsal=new DlgJnsPerawatanOperasi(null,false);
        bangsal.emptTeks();
        bangsal.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        bangsal.setLocationRelativeTo(internalFrame1);
        bangsal.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());   
        
    }//GEN-LAST:event_BtnTambahActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        emptTeks();
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //tampil();
    }//GEN-LAST:event_formWindowOpened

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        if(tbKamar.getSelectedRow()!= -1){
            isMenu();
        }else{
            ChkAccor.setSelected(false);
            JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data yang mau ditampilkan...!!!!");
        }
    }//GEN-LAST:event_ChkAccorActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCariDaftarOperasi dialog = new DlgCariDaftarOperasi(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnAll;
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnTambah;
    private widget.CekBox ChkAccor;
    private widget.PanelBiasa FormDetailBHP;
    private widget.Label LCount;
    private widget.PanelBiasa PanelAccor;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll7;
    private widget.TextBox TCari;
    private widget.InternalFrame internalFrame1;
    private widget.Label label10;
    private widget.Label label9;
    private widget.panelisi panelisi3;
    private widget.Table tbDetailBHP;
    private widget.Table tbKamar;
    // End of variables declaration//GEN-END:variables

  private void tampil() {  

    // ========== PASTIKAN MODEL TERHUBUNG ==========
    tbKamar.setModel(tabMode);

    // ================== RULE KD_PJ ==================
    String kdPjFilter;
    if(kd_pj != null){
        if(kd_pj.equals("UMU")){
            kdPjFilter = "UMU";
        }else if(kd_pj.equals("BPJ")){
            kdPjFilter = "BPJ";
        }else if(kd_pj.equals("BPK")){
            kdPjFilter = "BPK";
        }else{
            kdPjFilter = "-";
        }
    }else{
        kdPjFilter = "-";
    }
    // =================================================

//    System.out.println("DEBUG FILTER:");
//    System.out.println("kdPjFilter = " + kdPjFilter);
//    System.out.println("kelas = " + kelas);
//    System.out.println("cara_bayar_operasi = " + cara_bayar_operasi);
//    System.out.println("kelas_operasi = " + kelas_operasi);
//    System.out.println("cari = " + TCari.getText());

    Valid.tabelKosong(tabMode);

    try{
        pstindakan = koneksi.prepareStatement(
           "SELECT kode_paket, nm_perawatan, kategori, operator1, operator2, operator3, "+
           "asisten_operator1, asisten_operator2, asisten_operator3, instrumen, dokter_anak, perawaat_resusitas, "+
           "dokter_anestesi, asisten_anestesi, asisten_anestesi2, bidan, bidan2, bidan3, perawat_luar, alat, "+
           "sewa_ok, akomodasi, bagian_rs, omloop, omloop2, omloop3, omloop4, omloop5, sarpras, dokter_pjanak, dokter_umum, "+
           "(operator1+operator2+operator3+asisten_operator1+asisten_operator2+asisten_operator3+"+
           "instrumen+dokter_anak+perawaat_resusitas+alat+dokter_anestesi+asisten_anestesi+"+
           "asisten_anestesi2+bidan+bidan2+bidan3+perawat_luar+sewa_ok+akomodasi+bagian_rs+"+
           "omloop+omloop2+omloop3+omloop4+omloop5+sarpras+dokter_pjanak+dokter_umum) AS jumlah "+
           "FROM paket_operasi "+
           "WHERE status='1' AND kd_pj=? AND (kelas=? or kelas='-' ) AND (kode_paket LIKE ? OR nm_perawatan LIKE ?) "+
           "ORDER BY nm_perawatan"
        );

        pstindakan2 = koneksi.prepareStatement(
           "SELECT kode_paket, nm_perawatan, kategori, operator1, operator2, operator3, "+
           "asisten_operator1, asisten_operator2, asisten_operator3, instrumen, dokter_anak, perawaat_resusitas, "+
           "dokter_anestesi, asisten_anestesi, asisten_anestesi2, bidan, bidan2, bidan3, perawat_luar, alat, "+
           "sewa_ok, akomodasi, bagian_rs, omloop, omloop2, omloop3, omloop4, omloop5, sarpras, dokter_pjanak, dokter_umum, "+
           "(operator1+operator2+operator3+asisten_operator1+asisten_operator2+asisten_operator3+"+
           "instrumen+dokter_anak+perawaat_resusitas+alat+dokter_anestesi+asisten_anestesi+"+
           "asisten_anestesi2+bidan+bidan2+bidan3+perawat_luar+sewa_ok+akomodasi+bagian_rs+"+
           "omloop+omloop2+omloop3+omloop4+omloop5+sarpras+dokter_pjanak+dokter_umum) AS jumlah "+
           "FROM paket_operasi "+
           "WHERE status='1' AND (kode_paket LIKE ? OR nm_perawatan LIKE ?) "+
           "ORDER BY nm_perawatan"
        );

//        boolean pakaiFilterLengkap = cara_bayar_operasi.equals("Yes") && kelas_operasi.equals("Yes");
//
//        if(pakaiFilterLengkap){
            pstindakan.setString(1, kdPjFilter);
            pstindakan.setString(2, kelas.trim());
            pstindakan.setString(3, "%" + TCari.getText() + "%");
            pstindakan.setString(4, "%" + TCari.getText() + "%");
            rs = pstindakan.executeQuery();
//        }else{
//            pstindakan2.setString(1, "%" + TCari.getText() + "%");
//            pstindakan2.setString(2, "%" + TCari.getText() + "%");
//            rs = pstindakan2.executeQuery();
//        }

        int jumlahData = 0;

        while(rs.next()){
            jumlahData++;
     tabMode.addRow(new Object[]{
    false,
    rs.getString("kode_paket"),
    rs.getString("nm_perawatan"),
    rs.getString("kategori"),
    getDoubleSafe(rs, "operator1"),
    getDoubleSafe(rs, "operator2"),
    getDoubleSafe(rs, "operator3"),
    getDoubleSafe(rs, "asisten_operator1"),
    getDoubleSafe(rs, "asisten_operator2"),
    getDoubleSafe(rs, "asisten_operator3"),
    getDoubleSafe(rs, "instrumen"),
    getDoubleSafe(rs, "dokter_anak"),
    getDoubleSafe(rs, "perawaat_resusitas"),
    getDoubleSafe(rs, "dokter_anestesi"),
    getDoubleSafe(rs, "asisten_anestesi"),
    getDoubleSafe(rs, "asisten_anestesi2"),
    getDoubleSafe(rs, "bidan"),
    getDoubleSafe(rs, "bidan2"),
    getDoubleSafe(rs, "bidan3"),
    getDoubleSafe(rs, "perawat_luar"),
    getDoubleSafe(rs, "alat"),
    getDoubleSafe(rs, "sewa_ok"),
    getDoubleSafe(rs, "akomodasi"),
    getDoubleSafe(rs, "bagian_rs"),
    getDoubleSafe(rs, "omloop"),
    getDoubleSafe(rs, "omloop2"),
    getDoubleSafe(rs, "omloop3"),
    getDoubleSafe(rs, "omloop4"),
    getDoubleSafe(rs, "omloop5"),
    getDoubleSafe(rs, "sarpras"),
    getDoubleSafe(rs, "dokter_pjanak"),
 
    getDoubleSafe(rs, "jumlah")
});

        }


    }catch(Exception e){
        System.out.println("ERROR tampil(): " + e);
        e.printStackTrace();
    }finally{
        try {
            if(rs!=null) rs.close();
            if(pstindakan!=null) pstindakan.close();
            if(pstindakan2!=null) pstindakan2.close();
        } catch (Exception e) {}
    }

    LCount.setText(""+tabMode.getRowCount());
}

    
    private void tampil2() {  
       // ========== PASTIKAN MODEL TERHUBUNG ==========
    tbKamar.setModel(tabMode);

    // ================== RULE KD_PJ ==================
    String kdPjFilter;
    if(kd_pj != null){
        if(kd_pj.equals("UMU")){
            kdPjFilter = "UMU";
        }else if(kd_pj.equals("BPJ")){
            kdPjFilter = "BPJ";
        }else if(kd_pj.equals("BPK")){
            kdPjFilter = "BPK";
        }else{
            kdPjFilter = "-";
        }
    }else{
        kdPjFilter = "-";
    }
    // =================================================

//    System.out.println("DEBUG FILTER:");
//    System.out.println("kdPjFilter = " + kdPjFilter);
//    System.out.println("kelas = " + kelas);
//    System.out.println("cara_bayar_operasi = " + cara_bayar_operasi);
//    System.out.println("kelas_operasi = " + kelas_operasi);
//    System.out.println("cari = " + TCari.getText());

    Valid.tabelKosong(tabMode);

    try{
        pstindakan = koneksi.prepareStatement(
           "SELECT kode_paket, nm_perawatan, kategori, operator1, operator2, operator3, "+
           "asisten_operator1, asisten_operator2, asisten_operator3, instrumen, dokter_anak, perawaat_resusitas, "+
           "dokter_anestesi, asisten_anestesi, asisten_anestesi2, bidan, bidan2, bidan3, perawat_luar, alat, "+
           "sewa_ok, akomodasi, bagian_rs, omloop, omloop2, omloop3, omloop4, omloop5, sarpras, dokter_pjanak, dokter_umum, "+
           "(operator1+operator2+operator3+asisten_operator1+asisten_operator2+asisten_operator3+"+
           "instrumen+dokter_anak+perawaat_resusitas+alat+dokter_anestesi+asisten_anestesi+"+
           "asisten_anestesi2+bidan+bidan2+bidan3+perawat_luar+sewa_ok+akomodasi+bagian_rs+"+
           "omloop+omloop2+omloop3+omloop4+omloop5+sarpras+dokter_pjanak+dokter_umum) AS jumlah "+
           "FROM paket_operasi "+
           "WHERE status='1' AND kd_pj=? AND (kelas=? or kelas='-') AND (kode_paket LIKE ? OR nm_perawatan LIKE ?) "+
           "ORDER BY nm_perawatan"
        );

        pstindakan2 = koneksi.prepareStatement(
           "SELECT kode_paket, nm_perawatan, kategori, operator1, operator2, operator3, "+
           "asisten_operator1, asisten_operator2, asisten_operator3, instrumen, dokter_anak, perawaat_resusitas, "+
           "dokter_anestesi, asisten_anestesi, asisten_anestesi2, bidan, bidan2, bidan3, perawat_luar, alat, "+
           "sewa_ok, akomodasi, bagian_rs, omloop, omloop2, omloop3, omloop4, omloop5, sarpras, dokter_pjanak, dokter_umum, "+
           "(operator1+operator2+operator3+asisten_operator1+asisten_operator2+asisten_operator3+"+
           "instrumen+dokter_anak+perawaat_resusitas+alat+dokter_anestesi+asisten_anestesi+"+
           "asisten_anestesi2+bidan+bidan2+bidan3+perawat_luar+sewa_ok+akomodasi+bagian_rs+"+
           "omloop+omloop2+omloop3+omloop4+omloop5+sarpras+dokter_pjanak+dokter_umum) AS jumlah "+
           "FROM paket_operasi "+
           "WHERE status='1' AND (kode_paket LIKE ? OR nm_perawatan LIKE ?) "+
           "ORDER BY nm_perawatan"
        );

//        boolean pakaiFilterLengkap = cara_bayar_operasi.equals("Yes") && kelas_operasi.equals("Yes");
//
//        if(pakaiFilterLengkap){
            pstindakan.setString(1, kdPjFilter);
            pstindakan.setString(2, kelas.trim());
            pstindakan.setString(3, "%" + TCari.getText() + "%");
            pstindakan.setString(4, "%" + TCari.getText() + "%");
            rs = pstindakan.executeQuery();
//        }else{
//            pstindakan2.setString(1, "%" + TCari.getText() + "%");
//            pstindakan2.setString(2, "%" + TCari.getText() + "%");
//            rs = pstindakan2.executeQuery();
//        }

        int jumlahData = 0;

        while(rs.next()){
            jumlahData++;
          tabMode.addRow(new Object[]{
    false,
    rs.getString("kode_paket"),
    rs.getString("nm_perawatan"),
    rs.getString("kategori"),
    getDoubleSafe(rs, "operator1"),
    getDoubleSafe(rs, "operator2"),
    getDoubleSafe(rs, "operator3"),
    getDoubleSafe(rs, "asisten_operator1"),
    getDoubleSafe(rs, "asisten_operator2"),
    getDoubleSafe(rs, "asisten_operator3"),
    getDoubleSafe(rs, "instrumen"),
    getDoubleSafe(rs, "dokter_anak"),
    getDoubleSafe(rs, "perawaat_resusitas"),
    getDoubleSafe(rs, "dokter_anestesi"),
    getDoubleSafe(rs, "asisten_anestesi"),
    getDoubleSafe(rs, "asisten_anestesi2"),
    getDoubleSafe(rs, "bidan"),
    getDoubleSafe(rs, "bidan2"),
    getDoubleSafe(rs, "bidan3"),
    getDoubleSafe(rs, "perawat_luar"),
    getDoubleSafe(rs, "alat"),
    getDoubleSafe(rs, "sewa_ok"),
    getDoubleSafe(rs, "akomodasi"),
    getDoubleSafe(rs, "bagian_rs"),
    getDoubleSafe(rs, "omloop"),
    getDoubleSafe(rs, "omloop2"),
    getDoubleSafe(rs, "omloop3"),
    getDoubleSafe(rs, "omloop4"),
    getDoubleSafe(rs, "omloop5"),
    getDoubleSafe(rs, "sarpras"),
    getDoubleSafe(rs, "dokter_pjanak"),
   
    getDoubleSafe(rs, "jumlah")
});

        }

      

    }catch(Exception e){
        System.out.println("ERROR tampil(): " + e);
        e.printStackTrace();
    }finally{
        try {
            if(rs!=null) rs.close();
            if(pstindakan!=null) pstindakan.close();
            if(pstindakan2!=null) pstindakan2.close();
        } catch (Exception e) {}
    }

    LCount.setText(""+tabMode.getRowCount());
    }
private double getDoubleSafe(ResultSet rs, String field) {
    try {
        double val = rs.getDouble(field);
        if (rs.wasNull()) {
            return 0;
        }
        return val;
    } catch (Exception e) {
        return 0;
    }
}

    public void emptTeks() {
        TCari.requestFocus();
    }

    public JTable getTable(){
        return tbKamar;
    }
    
    public void setBayar(String penjab,String kelasoperasi){
        this.kd_pj=penjab;
        this.kelas=kelasoperasi;
        try {
            if(Valid.daysOld("./cache/paketoperasi.iyem")<8){
                tampil2();
            }
        } catch (Exception e) {
        }
    }
    public void isCek(){        
       BtnTambah.setEnabled(akses.gettarif_operasi());
    }
    
    private void isMenu(){
        if(ChkAccor.isSelected()==true){
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(470,HEIGHT));
//            FormMenu.setVisible(true);  
//            FormMasalahRencana.setVisible(true);  
            ChkAccor.setVisible(true);
        }else if(ChkAccor.isSelected()==false){   
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(15,HEIGHT));
//            FormMenu.setVisible(false);  
//            FormMasalahRencana.setVisible(false);   
            ChkAccor.setVisible(true);
        }
    }
    
    private void getMasalah() {
    if (tbKamar.getSelectedRow() != -1) { 
        try {
            Valid.tabelKosong(tabModeDetailBHP);

            pstindakan = koneksi.prepareStatement(
                "SELECT db.nama_brng AS nama_barang, " +
                "       apo.jml " +
                "FROM paket_operasi po " +
                "JOIN alkes_paket_operasi apo ON apo.kode_paket = po.kode_paket " +
                "JOIN databarang db ON apo.kode_brng = db.kode_brng " +
                "WHERE po.kode_paket = ? " +
                "ORDER BY apo.kode_paket"
            );

            try {
                // Ambil kode_paket dari tbKamar kolom pertama (index 0)
                pstindakan.setString(1, tbKamar.getValueAt(tbKamar.getSelectedRow(), 0).toString());
                
                rs = pstindakan.executeQuery();
                while (rs.next()) {
                    tabModeDetailBHP.addRow(new Object[]{
                        rs.getString("nama_barang"),
                        rs.getString("jml")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) rs.close();
                if (pstindakan != null) pstindakan.close();
            }
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
    }
}


}
