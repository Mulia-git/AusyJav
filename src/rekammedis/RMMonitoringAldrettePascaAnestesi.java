/*
 * Kontribusi dari Mega Khairunnisa RS Simpangan Depok
 */
package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;
import kepegawaian.DlgCariDokter;


/**
 *
 * @author perpustakaan
 */
public final class RMMonitoringAldrettePascaAnestesi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;    
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private String finger="",finger2="";
    private String TANGGALMUNDUR="yes";
   
    private javax.swing.JComboBox SkalaKriteria1b;
    private javax.swing.JComboBox SkalaKriteria1c;
    private javax.swing.JComboBox SkalaKriteria2baru;
    private javax.swing.JComboBox SkalaKriteria3baru;
    private javax.swing.JComboBox SkalaKriteria4baru;
    private javax.swing.JComboBox SkalaKriteria5baru;
    private javax.swing.JComboBox SkalaKriteria6baru;
    private javax.swing.JComboBox SkalaKriteria7baru;
    private javax.swing.JComboBox SkalaKriteria8baru;
    private javax.swing.JComboBox SkalaKriteria9baru;
    private javax.swing.JComboBox SkalaKriteria10baru;
    private javax.swing.JComboBox SkalaKriteria11baru;
   private javax.swing.JLabel labelSkala11baru = new javax.swing.JLabel();
   private  javax.swing.JLabel label11baru = new javax.swing.JLabel();
   private javax.swing.JLabel labelNilai11baru = new javax.swing.JLabel();

     
    private widget.TextBox NilaiKriteria1b;
    private widget.TextBox NilaiKriteria1c;
    private widget.TextBox NilaiKriteria2baru;
    private widget.TextBox NilaiKriteria3baru;
    private widget.TextBox NilaiKriteria4baru;
    private widget.TextBox NilaiKriteria5baru;
    private widget.TextBox NilaiKriteria6baru;
    private widget.TextBox NilaiKriteria7baru;
    private widget.TextBox NilaiKriteria8baru;
    private widget.TextBox NilaiKriteria9baru;
    private widget.TextBox NilaiKriteria10baru;
    private widget.TextBox NilaiKriteria11baru;
    private widget.TextBox txtJelaskan5;
    private widget.TextBox txtJelaskan6;
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMMonitoringAldrettePascaAnestesi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(800,674);
//        bikinScrollKriteria();
       
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.R.M.","Nama Pasien","Tgl.Lahir","JK","Tanggal","1. Aktivitas","N.K. 1","2. Respirasi","N.K. 2",
            "3. Tekanan Darah","N.K. 3","4. Kesadaran","N.K. 4","5. Warna Kulit","N.K. 5","Total","Keluar","Instruksi",
            "Kode Dokter","Nama Dokter","NIP","Petugas"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 23; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(65);
            }else if(i==2){
                column.setPreferredWidth(160);
            }else if(i==3){
                column.setPreferredWidth(65);
            }else if(i==4){
                column.setPreferredWidth(25);
            }else if(i==5){
                column.setPreferredWidth(115);
            }else if(i==6){
                column.setPreferredWidth(150);
            }else if(i==7){
                column.setPreferredWidth(40);
            }else if(i==8){
                column.setPreferredWidth(150);
            }else if(i==9){
                column.setPreferredWidth(40);
            }else if(i==10){
                column.setPreferredWidth(150);
            }else if(i==11){
                column.setPreferredWidth(50);
            }else if(i==12){
                column.setPreferredWidth(150);
            }else if(i==13){
                column.setPreferredWidth(40);
            }else if(i==14){
                column.setPreferredWidth(150);
            }else if(i==15){
                column.setPreferredWidth(40);
            }else if(i==16){
                column.setPreferredWidth(40);
            }else if(i==17){
                column.setPreferredWidth(200);
            }else if(i==18){
                column.setPreferredWidth(200);
            }else if(i==19){
                column.setPreferredWidth(85);
            }else if(i==20){
                column.setPreferredWidth(150);
            }else if(i==21){
                column.setPreferredWidth(85);
            }else if(i==22){
                column.setPreferredWidth(150);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        KdDokter.setDocument(new batasInput((byte)20).getKata(KdDokter));
        NIP.setDocument(new batasInput((byte)20).getKata(NIP));
        Keluar.setDocument(new batasInput((int)200).getKata(Keluar));
        Instruksi.setDocument(new batasInput((int)250).getKata(Instruksi));
        TCari.setDocument(new batasInput((int)100).getKata(TCari));
        
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
            });
        }
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){
                    KdDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                    NmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                    KdDokter.requestFocus();
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(petugas.getTable().getSelectedRow()!= -1){                   
                    NIP.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                    NamaPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                }  
                NIP.requestFocus();
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        }); 
        
        ChkInput.setSelected(false);
        isForm();
        jam();
        
        try {
            TANGGALMUNDUR=koneksiDB.TANGGALMUNDUR();
        } catch (Exception e) {
            TANGGALMUNDUR="yes";
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnMonitoringSkorAldrette = new javax.swing.JMenuItem();
        MnMonitoringSkorAldrette2 = new javax.swing.JMenuItem();
        JK = new widget.TextBox();
        TanggalRegistrasi = new widget.TextBox();
        jLabel232 = new widget.Label();
        jLabel230 = new widget.Label();
        SkalaKriteria6 = new widget.ComboBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        Tanggal = new widget.Tanggal();
        TNoRM = new widget.TextBox();
        jLabel16 = new widget.Label();
        Jam = new widget.ComboBox();
        Menit = new widget.ComboBox();
        Detik = new widget.ComboBox();
        ChkKejadian = new widget.CekBox();
        jLabel18 = new widget.Label();
        NIP = new widget.TextBox();
        NamaPetugas = new widget.TextBox();
        btnPetugas = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        jLabel57 = new widget.Label();
        jLabel217 = new widget.Label();
        jLabel219 = new widget.Label();
        SkalaKriteria1 = new widget.ComboBox();
        jLabel218 = new widget.Label();
        NilaKriteria1 = new widget.TextBox();
        jLabel220 = new widget.Label();
        jLabel221 = new widget.Label();
        SkalaKriteria2 = new widget.ComboBox();
        jLabel222 = new widget.Label();
        NilaKriteria2 = new widget.TextBox();
        jLabel223 = new widget.Label();
        jLabel224 = new widget.Label();
        SkalaKriteria3 = new widget.ComboBox();
        jLabel225 = new widget.Label();
        NilaKriteria3 = new widget.TextBox();
        jLabel226 = new widget.Label();
        jLabel227 = new widget.Label();
        SkalaKriteria4 = new widget.ComboBox();
        jLabel228 = new widget.Label();
        NilaKriteria4 = new widget.TextBox();
        jLabel229 = new widget.Label();
        SkalaKriteria5 = new widget.ComboBox();
        jLabel231 = new widget.Label();
        NilaKriteria5 = new widget.TextBox();
        TingkatSkor = new widget.Label();
        jLabel235 = new widget.Label();
        NilaiKriteriaTotal = new widget.TextBox();
        jLabel30 = new widget.Label();
        scrollPane1 = new widget.ScrollPane();
        Keluar = new widget.TextArea();
        jLabel31 = new widget.Label();
        scrollPane2 = new widget.ScrollPane();
        Instruksi = new widget.TextArea();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        NmDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        KdDokter = new widget.TextBox();
        label14 = new widget.Label();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel233 = new widget.Label();
        jLabel234 = new widget.Label();
        NilaKriteria6 = new widget.TextBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnMonitoringSkorAldrette.setBackground(new java.awt.Color(255, 255, 254));
        MnMonitoringSkorAldrette.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnMonitoringSkorAldrette.setForeground(new java.awt.Color(50, 50, 50));
        MnMonitoringSkorAldrette.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnMonitoringSkorAldrette.setText("Monitoring Skor Aldrette Pasca Anestesi");
        MnMonitoringSkorAldrette.setName("MnMonitoringSkorAldrette"); // NOI18N
        MnMonitoringSkorAldrette.setPreferredSize(new java.awt.Dimension(290, 26));
        MnMonitoringSkorAldrette.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnMonitoringSkorAldretteActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnMonitoringSkorAldrette);

        MnMonitoringSkorAldrette2.setBackground(new java.awt.Color(255, 255, 254));
        MnMonitoringSkorAldrette2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnMonitoringSkorAldrette2.setForeground(new java.awt.Color(50, 50, 50));
        MnMonitoringSkorAldrette2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnMonitoringSkorAldrette2.setText("Rekap Monitoring Skor Aldrette Pasca Anestesi");
        MnMonitoringSkorAldrette2.setName("MnMonitoringSkorAldrette2"); // NOI18N
        MnMonitoringSkorAldrette2.setPreferredSize(new java.awt.Dimension(230, 26));
        MnMonitoringSkorAldrette2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnMonitoringSkorAldrette2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnMonitoringSkorAldrette2);

        JK.setHighlighter(null);
        JK.setName("JK"); // NOI18N

        TanggalRegistrasi.setHighlighter(null);
        TanggalRegistrasi.setName("TanggalRegistrasi"); // NOI18N

        jLabel232.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel232.setText("1a. Tingkat Kesadaran");
        jLabel232.setName("jLabel232"); // NOI18N

        jLabel230.setText("Skala :");
        jLabel230.setName("jLabel230"); // NOI18N
        jLabel230.getAccessibleContext().setAccessibleName("Skor :");

        SkalaKriteria6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cianosis", "Pucat", "Kemerahan / Normal" }));
        SkalaKriteria6.setName("SkalaKriteria6"); // NOI18N
        SkalaKriteria6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKriteria6ItemStateChanged(evt);
            }
        });
        SkalaKriteria6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKriteria6KeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Skor Aldrette Pasca Anestesi (General Anastesi) ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(jPopupMenu1);
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbObat);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnSimpan);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnBatal);

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnEdit);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnPrint);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass8.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass8.add(LCount);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnKeluar);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-12-2025" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-12-2025" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(310, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('3');
        BtnCari.setToolTipText("Alt+3");
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
        panelGlass9.add(BtnCari);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setToolTipText("Alt+M");
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
        panelGlass9.add(BtnAll);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 476));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setText(".: Input Data");
        ChkInput.setToolTipText("Alt+I");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 570));

        FormInput.setBackground(new java.awt.Color(250, 255, 245));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(700, 1000));
        FormInput.setLayout(null);

        jLabel4.setText("No.Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 70, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(74, 10, 136, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        FormInput.add(TPasien);
        TPasien.setBounds(326, 10, 285, 23);

        Tanggal.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-12-2025" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        FormInput.add(Tanggal);
        Tanggal.setBounds(74, 70, 90, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        FormInput.add(TNoRM);
        TNoRM.setBounds(212, 10, 112, 23);

        jLabel16.setText("Tanggal :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 70, 70, 23);

        Jam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        Jam.setName("Jam"); // NOI18N
        Jam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JamKeyPressed(evt);
            }
        });
        FormInput.add(Jam);
        Jam.setBounds(168, 70, 62, 23);

        Menit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Menit.setName("Menit"); // NOI18N
        Menit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MenitKeyPressed(evt);
            }
        });
        FormInput.add(Menit);
        Menit.setBounds(234, 70, 62, 23);

        Detik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Detik.setName("Detik"); // NOI18N
        Detik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DetikKeyPressed(evt);
            }
        });
        FormInput.add(Detik);
        Detik.setBounds(300, 70, 62, 23);

        ChkKejadian.setBorder(null);
        ChkKejadian.setSelected(true);
        ChkKejadian.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkKejadian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkKejadian.setName("ChkKejadian"); // NOI18N
        FormInput.add(ChkKejadian);
        ChkKejadian.setBounds(366, 70, 23, 23);

        jLabel18.setText("Petugas :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(0, 40, 70, 23);

        NIP.setEditable(false);
        NIP.setHighlighter(null);
        NIP.setName("NIP"); // NOI18N
        FormInput.add(NIP);
        NIP.setBounds(74, 40, 94, 23);

        NamaPetugas.setEditable(false);
        NamaPetugas.setName("NamaPetugas"); // NOI18N
        FormInput.add(NamaPetugas);
        NamaPetugas.setBounds(170, 40, 175, 23);

        btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas.setMnemonic('2');
        btnPetugas.setToolTipText("ALt+2");
        btnPetugas.setName("btnPetugas"); // NOI18N
        btnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasActionPerformed(evt);
            }
        });
        btnPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPetugasKeyPressed(evt);
            }
        });
        FormInput.add(btnPetugas);
        btnPetugas.setBounds(348, 40, 28, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(625, 10, 60, 23);

        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(689, 10, 100, 23);

        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel57.setText("Kriteria :");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(14, 100, 80, 23);

        jLabel217.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel217.setText("1. Aktivitas");
        jLabel217.setName("jLabel217"); // NOI18N
        FormInput.add(jLabel217);
        jLabel217.setBounds(34, 120, 260, 23);

        jLabel219.setText("Skala :");
        jLabel219.setName("jLabel219"); // NOI18N
        FormInput.add(jLabel219);
        jLabel219.setBounds(250, 120, 80, 23);

        SkalaKriteria1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Sanggup Menggerakan Satupun Anggota Gerak", "Sanggup Gerak 2 Anggota Tubuh", "Sanggup Gerak 4 Anggota Tubuh" }));
        SkalaKriteria1.setName("SkalaKriteria1"); // NOI18N
        SkalaKriteria1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKriteria1ItemStateChanged(evt);
            }
        });
        SkalaKriteria1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKriteria1KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKriteria1);
        SkalaKriteria1.setBounds(334, 120, 330, 23);

        jLabel218.setText("Nilai :");
        jLabel218.setName("jLabel218"); // NOI18N
        FormInput.add(jLabel218);
        jLabel218.setBounds(675, 120, 50, 23);

        NilaKriteria1.setEditable(false);
        NilaKriteria1.setFocusTraversalPolicyProvider(true);
        NilaKriteria1.setName("NilaKriteria1"); // NOI18N
        FormInput.add(NilaKriteria1);
        NilaKriteria1.setBounds(729, 120, 60, 23);

        jLabel220.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel220.setText("2. Respirasi");
        jLabel220.setName("jLabel220"); // NOI18N
        FormInput.add(jLabel220);
        jLabel220.setBounds(34, 150, 260, 23);

        jLabel221.setText("Skala :");
        jLabel221.setName("jLabel221"); // NOI18N
        FormInput.add(jLabel221);
        jLabel221.setBounds(250, 150, 80, 23);
        jLabel221.getAccessibleContext().setAccessibleName("Skor :");

        SkalaKriteria2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Apnea Atau Napas Tidak Adekuat", "Sesak Atau Pernapasan Sedikit Terbatas", "Sanggup Bernafas Dalam Serta Disuruh Batuk" }));
        SkalaKriteria2.setName("SkalaKriteria2"); // NOI18N
        SkalaKriteria2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKriteria2ItemStateChanged(evt);
            }
        });
        SkalaKriteria2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKriteria2KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKriteria2);
        SkalaKriteria2.setBounds(334, 150, 330, 23);

        jLabel222.setText("Nilai :");
        jLabel222.setName("jLabel222"); // NOI18N
        FormInput.add(jLabel222);
        jLabel222.setBounds(675, 150, 50, 23);

        NilaKriteria2.setEditable(false);
        NilaKriteria2.setFocusTraversalPolicyProvider(true);
        NilaKriteria2.setName("NilaKriteria2"); // NOI18N
        FormInput.add(NilaKriteria2);
        NilaKriteria2.setBounds(729, 150, 60, 23);

        jLabel223.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel223.setText("3. Tekanan Darah");
        jLabel223.setName("jLabel223"); // NOI18N
        FormInput.add(jLabel223);
        jLabel223.setBounds(34, 180, 260, 23);

        jLabel224.setText("Skala :");
        jLabel224.setName("jLabel224"); // NOI18N
        FormInput.add(jLabel224);
        jLabel224.setBounds(250, 180, 80, 23);
        jLabel224.getAccessibleContext().setAccessibleName("Skor :");

        SkalaKriteria3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "± 50% Tekanan Darah Pra Anestesi", "± 20% - 50% Tekanan Darah Pra Anestesi", "± 20% Tekanan Darah Pra Anestesi" }));
        SkalaKriteria3.setName("SkalaKriteria3"); // NOI18N
        SkalaKriteria3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKriteria3ItemStateChanged(evt);
            }
        });
        SkalaKriteria3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKriteria3KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKriteria3);
        SkalaKriteria3.setBounds(334, 180, 330, 23);

        jLabel225.setText("Nilai :");
        jLabel225.setName("jLabel225"); // NOI18N
        FormInput.add(jLabel225);
        jLabel225.setBounds(675, 180, 50, 23);

        NilaKriteria3.setEditable(false);
        NilaKriteria3.setFocusTraversalPolicyProvider(true);
        NilaKriteria3.setName("NilaKriteria3"); // NOI18N
        FormInput.add(NilaKriteria3);
        NilaKriteria3.setBounds(729, 180, 60, 23);

        jLabel226.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel226.setText("4. Kesadaran");
        jLabel226.setName("jLabel226"); // NOI18N
        FormInput.add(jLabel226);
        jLabel226.setBounds(34, 210, 260, 23);

        jLabel227.setText("Skala :");
        jLabel227.setName("jLabel227"); // NOI18N
        FormInput.add(jLabel227);
        jLabel227.setBounds(250, 210, 80, 23);
        jLabel227.getAccessibleContext().setAccessibleName("Skor :");

        SkalaKriteria4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada Respon", "Respon Terhadap Panggilan", "Sadar Penuh" }));
        SkalaKriteria4.setName("SkalaKriteria4"); // NOI18N
        SkalaKriteria4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKriteria4ItemStateChanged(evt);
            }
        });
        SkalaKriteria4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKriteria4KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKriteria4);
        SkalaKriteria4.setBounds(334, 210, 330, 23);

        jLabel228.setText("Nilai :");
        jLabel228.setName("jLabel228"); // NOI18N
        FormInput.add(jLabel228);
        jLabel228.setBounds(675, 210, 50, 23);

        NilaKriteria4.setEditable(false);
        NilaKriteria4.setFocusTraversalPolicyProvider(true);
        NilaKriteria4.setName("NilaKriteria4"); // NOI18N
        FormInput.add(NilaKriteria4);
        NilaKriteria4.setBounds(729, 210, 60, 23);

        jLabel229.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel229.setText("5. Warna Kulit");
        jLabel229.setName("jLabel229"); // NOI18N
        FormInput.add(jLabel229);
        jLabel229.setBounds(30, 240, 260, 23);

        SkalaKriteria5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cianosis", "Pucat", "Kemerahan / Normal" }));
        SkalaKriteria5.setName("SkalaKriteria5"); // NOI18N
        SkalaKriteria5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKriteria5ItemStateChanged(evt);
            }
        });
        SkalaKriteria5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkalaKriteria5ActionPerformed(evt);
            }
        });
        SkalaKriteria5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKriteria5KeyPressed(evt);
            }
        });
        FormInput.add(SkalaKriteria5);
        SkalaKriteria5.setBounds(340, 240, 330, 23);

        jLabel231.setText("Nilai :");
        jLabel231.setName("jLabel231"); // NOI18N
        FormInput.add(jLabel231);
        jLabel231.setBounds(675, 240, 50, 23);

        NilaKriteria5.setEditable(false);
        NilaKriteria5.setFocusTraversalPolicyProvider(true);
        NilaKriteria5.setName("NilaKriteria5"); // NOI18N
        FormInput.add(NilaKriteria5);
        NilaKriteria5.setBounds(729, 240, 60, 23);

        TingkatSkor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TingkatSkor.setText("Pasien Bisa Dipindahkan Ke Ruang Perawatan Bila Skor Minimal 8");
        TingkatSkor.setToolTipText("");
        TingkatSkor.setName("TingkatSkor"); // NOI18N
        FormInput.add(TingkatSkor);
        TingkatSkor.setBounds(34, 270, 640, 23);

        jLabel235.setText("Total :");
        jLabel235.setName("jLabel235"); // NOI18N
        FormInput.add(jLabel235);
        jLabel235.setBounds(675, 270, 50, 23);

        NilaiKriteriaTotal.setEditable(false);
        NilaiKriteriaTotal.setFocusTraversalPolicyProvider(true);
        NilaiKriteriaTotal.setName("NilaiKriteriaTotal"); // NOI18N
        FormInput.add(NilaiKriteriaTotal);
        NilaiKriteriaTotal.setBounds(729, 270, 60, 23);

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("Keluar :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(14, 300, 80, 23);

        scrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane1.setName("scrollPane1"); // NOI18N

        Keluar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Keluar.setColumns(20);
        Keluar.setRows(5);
        Keluar.setName("Keluar"); // NOI18N
        Keluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeluarKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(Keluar);

        FormInput.add(scrollPane1);
        scrollPane1.setBounds(34, 320, 755, 43);

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Instruksi / Tindakan Di Ruang Pemulihan (RR) :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(14, 370, 280, 23);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        Instruksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Instruksi.setColumns(20);
        Instruksi.setRows(5);
        Instruksi.setName("Instruksi"); // NOI18N
        Instruksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                InstruksiKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(Instruksi);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(34, 390, 755, 53);

        jSeparator3.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator3.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator3.setName("jSeparator3"); // NOI18N
        FormInput.add(jSeparator3);
        jSeparator3.setBounds(0, 300, 810, 1);

        jSeparator4.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator4.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator4.setName("jSeparator4"); // NOI18N
        FormInput.add(jSeparator4);
        jSeparator4.setBounds(0, 370, 810, 1);

        NmDokter.setEditable(false);
        NmDokter.setName("NmDokter"); // NOI18N
        NmDokter.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmDokter);
        NmDokter.setBounds(583, 40, 175, 23);

        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('2');
        BtnDokter.setToolTipText("Alt+2");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        BtnDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokterKeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter);
        BtnDokter.setBounds(761, 40, 28, 23);

        KdDokter.setEditable(false);
        KdDokter.setName("KdDokter"); // NOI18N
        KdDokter.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(KdDokter);
        KdDokter.setBounds(481, 40, 100, 23);

        label14.setText("Dokter Anestesi :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(378, 40, 99, 23);

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 100, 810, 1);

        jLabel233.setText("Skala :");
        jLabel233.setName("jLabel233"); // NOI18N
        FormInput.add(jLabel233);
        jLabel233.setBounds(250, 240, 80, 23);

        jLabel234.setText("Nilai :");
        jLabel234.setName("jLabel234"); // NOI18N
        FormInput.add(jLabel234);
        jLabel234.setBounds(675, 240, 50, 23);

        NilaKriteria6.setEditable(false);
        NilaKriteria6.setFocusTraversalPolicyProvider(true);
        NilaKriteria6.setName("NilaKriteria6"); // NOI18N
        FormInput.add(NilaKriteria6);
        NilaKriteria6.setBounds(729, 240, 60, 23);

        scrollInput.setViewportView(FormInput);

        PanelInput.add(scrollInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
        }else{            
            Valid.pindah(evt,TCari,Tanggal);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        Valid.pindah(evt,TCari,BtnSimpan);
}//GEN-LAST:event_TPasienKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"pasien");
        }else if(NmDokter.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Dokter");
        }else if(NIP.getText().trim().equals("")||NamaPetugas.getText().trim().equals("")){
            Valid.textKosong(NIP,"Petugas");
        }else if(Keluar.getText().trim().equals("")){
            Valid.textKosong(Keluar,"Keluar");
        }else if(Instruksi.getText().trim().equals("")){
            Valid.textKosong(Instruksi,"Instruksi");
        }else{
            if(akses.getkode().equals("Admin Utama")){
                simpan();
            }else{
                if(TanggalRegistrasi.getText().equals("")){
                    TanggalRegistrasi.setText(Sequel.cariIsi("select concat(reg_periksa.tgl_registrasi,' ',reg_periksa.jam_reg) from reg_periksa where reg_periksa.no_rawat=?",TNoRw.getText()));
                }
                if(Sequel.cekTanggalRegistrasi(TanggalRegistrasi.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem())==true){
                    simpan();
                }
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,Instruksi,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm(); 
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(tbObat.getSelectedRow()>-1){
            if(akses.getkode().equals("Admin Utama")){
                hapus();
            }else{
                if(NIP.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString())){
                    if(Sequel.cekTanggal48jam(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString(),Sequel.ambiltanggalsekarang())==true){
                        hapus();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Hanya bisa dihapus oleh petugas yang bersangkutan..!!");
                }
            }
        }else{
            JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
        }   
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"pasien");
        }else if(NmDokter.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Dokter");
        }else if(NIP.getText().trim().equals("")||NamaPetugas.getText().trim().equals("")){
            Valid.textKosong(NIP,"Petugas");
        }else if(Keluar.getText().trim().equals("")){
            Valid.textKosong(Keluar,"Keluar");
        }else if(Instruksi.getText().trim().equals("")){
            Valid.textKosong(Instruksi,"Instruksi");
        }else{
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode().equals("Admin Utama")){
                    ganti();
                }else{
                    if(NIP.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString())){
                        if(Sequel.cekTanggal48jam(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString(),Sequel.ambiltanggalsekarang())==true){
                            if(TanggalRegistrasi.getText().equals("")){
                                TanggalRegistrasi.setText(Sequel.cariIsi("select concat(reg_periksa.tgl_registrasi,' ',reg_periksa.jam_reg) from reg_periksa where reg_periksa.no_rawat=?",TNoRw.getText()));
                            }
                            if(Sequel.cekTanggalRegistrasi(TanggalRegistrasi.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem())==true){
                                ganti();
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Hanya bisa diganti oleh petugas yang bersangkutan..!!");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        petugas.dispose();
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnKeluarActionPerformed(null);
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            Map<String, Object> param = new HashMap<>(); 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            if(TCari.getText().trim().equals("")){
                Valid.MyReportqry("rptSkorMonitoringAldrette.jasper","report","::[ Data Skor Monitoring Aldrette Pasca Anestesi ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,skor_aldrette_pasca_anestesi.tanggal,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala1,skor_aldrette_pasca_anestesi.penilaian_nilai1,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala2,skor_aldrette_pasca_anestesi.penilaian_nilai2,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala3,skor_aldrette_pasca_anestesi.penilaian_nilai3,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala4,skor_aldrette_pasca_anestesi.penilaian_nilai4,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala5,skor_aldrette_pasca_anestesi.penilaian_nilai5,"+
                    "skor_aldrette_pasca_anestesi.penilaian_totalnilai,skor_aldrette_pasca_anestesi.keluar,"+
                    "skor_aldrette_pasca_anestesi.instruksi,skor_aldrette_pasca_anestesi.kd_dokter,dokter.nm_dokter,skor_aldrette_pasca_anestesi.nip,petugas.nama "+
                    "from skor_aldrette_pasca_anestesi inner join reg_periksa on skor_aldrette_pasca_anestesi.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join dokter on skor_aldrette_pasca_anestesi.kd_dokter=dokter.kd_dokter "+
                    "inner join petugas on skor_aldrette_pasca_anestesi.nip=petugas.nip where "+
                    "skor_aldrette_pasca_anestesi.tanggal between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59' "+
                    "order by skor_aldrette_pasca_anestesi.tanggal",param);
            }else{
                Valid.MyReportqry("rptSkorMonitoringAldrette.jasper","report","::[ Data Skor Monitoring Aldrette Pasca Anestesi ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,skor_aldrette_pasca_anestesi.tanggal,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala1,skor_aldrette_pasca_anestesi.penilaian_nilai1,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala2,skor_aldrette_pasca_anestesi.penilaian_nilai2,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala3,skor_aldrette_pasca_anestesi.penilaian_nilai3,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala4,skor_aldrette_pasca_anestesi.penilaian_nilai4,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala5,skor_aldrette_pasca_anestesi.penilaian_nilai5,"+
                    "skor_aldrette_pasca_anestesi.penilaian_totalnilai,skor_aldrette_pasca_anestesi.keluar,"+
                    "skor_aldrette_pasca_anestesi.instruksi,skor_aldrette_pasca_anestesi.kd_dokter,dokter.nm_dokter,skor_aldrette_pasca_anestesi.nip,petugas.nama "+
                    "from skor_aldrette_pasca_anestesi inner join reg_periksa on skor_aldrette_pasca_anestesi.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join dokter on skor_aldrette_pasca_anestesi.kd_dokter=dokter.kd_dokter "+
                    "inner join petugas on skor_aldrette_pasca_anestesi.nip=petugas.nip where "+
                    "skor_aldrette_pasca_anestesi.tanggal between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59' and "+
                    "(reg_periksa.no_rawat like '%"+TCari.getText().trim()+"%' or pasien.no_rkm_medis like '%"+TCari.getText().trim()+"%' or pasien.nm_pasien like '%"+TCari.getText().trim()+"%' "+
                    "or skor_aldrette_pasca_anestesi.kd_dokter like '%"+TCari.getText().trim()+"%' or dokter.nm_dokter like '%"+TCari.getText().trim()+"%' or skor_aldrette_pasca_anestesi.nip like '%"+TCari.getText().trim()+"%' or petugas.nama like '%"+TCari.getText().trim()+"%') "+
                    "order by skor_aldrette_pasca_anestesi.tanggal ",param);
            }  
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
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
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            TCari.setText("");
            tampil();
        }else{
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void TanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeyPressed
        Valid.pindah(evt,TCari,Jam);
}//GEN-LAST:event_TanggalKeyPressed

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
        // Valid.pindah(evt, TNm, BtnSimpan);
}//GEN-LAST:event_TNoRMKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void JamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JamKeyPressed
        Valid.pindah(evt,Tanggal,Menit);
    }//GEN-LAST:event_JamKeyPressed

    private void MenitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MenitKeyPressed
        Valid.pindah(evt,Jam,Detik);
    }//GEN-LAST:event_MenitKeyPressed

    private void DetikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DetikKeyPressed
        Valid.pindah(evt,Menit,btnPetugas);
    }//GEN-LAST:event_DetikKeyPressed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void btnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPetugasKeyPressed
        Valid.pindah(evt,Detik,SkalaKriteria1);
    }//GEN-LAST:event_btnPetugasKeyPressed

    private void MnMonitoringSkorAldretteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnMonitoringSkorAldretteActionPerformed
        if(tbObat.getSelectedRow()>-1){
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),20).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),19).toString():finger)+"\n"+Tanggal.getSelectedItem());
            finger2=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            param.put("finger2","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),22).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),21).toString():finger2)+"\n"+Tanggal.getSelectedItem());
            Valid.MyReportqry("rptMonitoringSkorAldrette.jasper","report","::[ Monitoring Skor Aldrette Pasca Anestesi ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,skor_aldrette_pasca_anestesi.tanggal,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala1,skor_aldrette_pasca_anestesi.penilaian_nilai1,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala2,skor_aldrette_pasca_anestesi.penilaian_nilai2,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala3,skor_aldrette_pasca_anestesi.penilaian_nilai3,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala4,skor_aldrette_pasca_anestesi.penilaian_nilai4,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala5,skor_aldrette_pasca_anestesi.penilaian_nilai5,"+
                    "skor_aldrette_pasca_anestesi.penilaian_totalnilai,skor_aldrette_pasca_anestesi.keluar,"+
                    "skor_aldrette_pasca_anestesi.instruksi,skor_aldrette_pasca_anestesi.kd_dokter,dokter.nm_dokter,skor_aldrette_pasca_anestesi.nip,petugas.nama "+
                    "from skor_aldrette_pasca_anestesi inner join reg_periksa on skor_aldrette_pasca_anestesi.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join dokter on skor_aldrette_pasca_anestesi.kd_dokter=dokter.kd_dokter "+
                    "inner join petugas on skor_aldrette_pasca_anestesi.nip=petugas.nip where reg_periksa.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
        }
    }//GEN-LAST:event_MnMonitoringSkorAldretteActionPerformed

    private void SkalaKriteria1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKriteria1ItemStateChanged
        if(SkalaKriteria1.getSelectedIndex()==0){
            NilaKriteria1.setText("0");
        }else if(SkalaKriteria1.getSelectedIndex()==1){
            NilaKriteria1.setText("1");
        }else{
            NilaKriteria1.setText("2");
        }
        isTotalResiko();
    }//GEN-LAST:event_SkalaKriteria1ItemStateChanged

    private void SkalaKriteria1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKriteria1KeyPressed
        Valid.pindah(evt,btnPetugas,SkalaKriteria2);
    }//GEN-LAST:event_SkalaKriteria1KeyPressed

    private void SkalaKriteria2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKriteria2ItemStateChanged
         if(SkalaKriteria2.getSelectedIndex()==0){
            NilaKriteria2.setText("0");
        }else if(SkalaKriteria2.getSelectedIndex()==1){
            NilaKriteria2.setText("1");
        }else{
            NilaKriteria2.setText("2");
        }
        isTotalResiko();
    }//GEN-LAST:event_SkalaKriteria2ItemStateChanged

    private void SkalaKriteria2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKriteria2KeyPressed
        Valid.pindah(evt,SkalaKriteria1,SkalaKriteria3);
    }//GEN-LAST:event_SkalaKriteria2KeyPressed

    private void SkalaKriteria3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKriteria3ItemStateChanged
         if(SkalaKriteria3.getSelectedIndex()==0){
            NilaKriteria3.setText("0");
        }else if(SkalaKriteria3.getSelectedIndex()==1){
            NilaKriteria3.setText("1");
        }else{
            NilaKriteria3.setText("2");
        }
        isTotalResiko();
    }//GEN-LAST:event_SkalaKriteria3ItemStateChanged

    private void SkalaKriteria3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKriteria3KeyPressed
        Valid.pindah(evt,SkalaKriteria2,SkalaKriteria4);
    }//GEN-LAST:event_SkalaKriteria3KeyPressed

    private void SkalaKriteria4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKriteria4ItemStateChanged
        if(SkalaKriteria4.getSelectedIndex()==0){
            NilaKriteria4.setText("0");
        }else if(SkalaKriteria4.getSelectedIndex()==1){
            NilaKriteria4.setText("1");
        }else{
            NilaKriteria4.setText("2");
        }
        isTotalResiko();
    }//GEN-LAST:event_SkalaKriteria4ItemStateChanged

    private void SkalaKriteria4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKriteria4KeyPressed
        Valid.pindah(evt,SkalaKriteria3,SkalaKriteria5);
    }//GEN-LAST:event_SkalaKriteria4KeyPressed

    private void SkalaKriteria5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKriteria5ItemStateChanged
         if(SkalaKriteria5.getSelectedIndex()==0){
            NilaKriteria5.setText("0");
        }else if(SkalaKriteria5.getSelectedIndex()==1){
            NilaKriteria5.setText("1");
        }else{
            NilaKriteria5.setText("2");
        }
        isTotalResiko();
    }//GEN-LAST:event_SkalaKriteria5ItemStateChanged

    private void SkalaKriteria5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKriteria5KeyPressed
        Valid.pindah(evt,SkalaKriteria4,NilaiKriteriaTotal);
    }//GEN-LAST:event_SkalaKriteria5KeyPressed

    private void KeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeluarKeyPressed
        Valid.pindah2(evt,Keluar,Instruksi);
    }//GEN-LAST:event_KeluarKeyPressed

    private void InstruksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_InstruksiKeyPressed
        Valid.pindah2(evt,Keluar,BtnSimpan);
    }//GEN-LAST:event_InstruksiKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
        Valid.pindah(evt,btnPetugas,BtnSimpan);
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbObatKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObatMouseClicked

    private void MnMonitoringSkorAldrette2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnMonitoringSkorAldrette2ActionPerformed
        if(tbObat.getSelectedRow()>-1){
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());
            param.put("logo",Sequel.cariGambar("select setting.logo from setting"));
            Valid.MyReportqry("rptFormulirMonitoringSkorAldrette.jasper","report","::[ Monitoring Skor Aldrette Pasca Anestesi ]::",
                "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,skor_aldrette_pasca_anestesi.tanggal,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala1,skor_aldrette_pasca_anestesi.penilaian_nilai1,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala2,skor_aldrette_pasca_anestesi.penilaian_nilai2,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala3,skor_aldrette_pasca_anestesi.penilaian_nilai3,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala4,skor_aldrette_pasca_anestesi.penilaian_nilai4,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala5,skor_aldrette_pasca_anestesi.penilaian_nilai5,"+
                    "skor_aldrette_pasca_anestesi.penilaian_totalnilai,skor_aldrette_pasca_anestesi.keluar,"+
                    "skor_aldrette_pasca_anestesi.instruksi,skor_aldrette_pasca_anestesi.kd_dokter,dokter.nm_dokter,skor_aldrette_pasca_anestesi.nip,petugas.nama "+
                    "from skor_aldrette_pasca_anestesi inner join reg_periksa on skor_aldrette_pasca_anestesi.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join dokter on skor_aldrette_pasca_anestesi.kd_dokter=dokter.kd_dokter "+
                    "inner join petugas on skor_aldrette_pasca_anestesi.nip=petugas.nip where reg_periksa.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
        }
    }//GEN-LAST:event_MnMonitoringSkorAldrette2ActionPerformed

    private void SkalaKriteria6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKriteria6ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKriteria6ItemStateChanged

    private void SkalaKriteria6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKriteria6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKriteria6KeyPressed

    private void SkalaKriteria5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkalaKriteria5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKriteria5ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMMonitoringAldrettePascaAnestesi dialog = new RMMonitoringAldrettePascaAnestesi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkKejadian;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.ComboBox Detik;
    private widget.PanelBiasa FormInput;
    private widget.TextArea Instruksi;
    private widget.TextBox JK;
    private widget.ComboBox Jam;
    private widget.TextBox KdDokter;
    private widget.TextArea Keluar;
    private widget.Label LCount;
    private widget.ComboBox Menit;
    private javax.swing.JMenuItem MnMonitoringSkorAldrette;
    private javax.swing.JMenuItem MnMonitoringSkorAldrette2;
    private widget.TextBox NIP;
    private widget.TextBox NamaPetugas;
    private widget.TextBox NilaKriteria1;
    private widget.TextBox NilaKriteria2;
    private widget.TextBox NilaKriteria3;
    private widget.TextBox NilaKriteria4;
    private widget.TextBox NilaKriteria5;
    private widget.TextBox NilaKriteria6;
    private widget.TextBox NilaiKriteriaTotal;
    private widget.TextBox NmDokter;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ComboBox SkalaKriteria1;
    private widget.ComboBox SkalaKriteria2;
    private widget.ComboBox SkalaKriteria3;
    private widget.ComboBox SkalaKriteria4;
    private widget.ComboBox SkalaKriteria5;
    private widget.ComboBox SkalaKriteria6;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.Tanggal Tanggal;
    private widget.TextBox TanggalRegistrasi;
    private widget.TextBox TglLahir;
    private widget.Label TingkatSkor;
    private widget.Button btnPetugas;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel16;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel217;
    private widget.Label jLabel218;
    private widget.Label jLabel219;
    private widget.Label jLabel220;
    private widget.Label jLabel221;
    private widget.Label jLabel222;
    private widget.Label jLabel223;
    private widget.Label jLabel224;
    private widget.Label jLabel225;
    private widget.Label jLabel226;
    private widget.Label jLabel227;
    private widget.Label jLabel228;
    private widget.Label jLabel229;
    private widget.Label jLabel230;
    private widget.Label jLabel231;
    private widget.Label jLabel232;
    private widget.Label jLabel233;
    private widget.Label jLabel234;
    private widget.Label jLabel235;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel4;
    private widget.Label jLabel57;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private widget.Label label14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables
    
    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().toString().trim().equals("")){
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,skor_aldrette_pasca_anestesi.tanggal,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala1,skor_aldrette_pasca_anestesi.penilaian_nilai1,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala2,skor_aldrette_pasca_anestesi.penilaian_nilai2,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala3,skor_aldrette_pasca_anestesi.penilaian_nilai3,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala4,skor_aldrette_pasca_anestesi.penilaian_nilai4,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala5,skor_aldrette_pasca_anestesi.penilaian_nilai5,"+
                    "skor_aldrette_pasca_anestesi.penilaian_totalnilai,skor_aldrette_pasca_anestesi.keluar,"+
                    "skor_aldrette_pasca_anestesi.instruksi,skor_aldrette_pasca_anestesi.kd_dokter,dokter.nm_dokter,skor_aldrette_pasca_anestesi.nip,petugas.nama "+
                    "from skor_aldrette_pasca_anestesi inner join reg_periksa on skor_aldrette_pasca_anestesi.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join dokter on skor_aldrette_pasca_anestesi.kd_dokter=dokter.kd_dokter "+
                    "inner join petugas on skor_aldrette_pasca_anestesi.nip=petugas.nip where "+
                    "skor_aldrette_pasca_anestesi.tanggal between ? and ? order by skor_aldrette_pasca_anestesi.tanggal");
            }else{
                ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,skor_aldrette_pasca_anestesi.tanggal,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala1,skor_aldrette_pasca_anestesi.penilaian_nilai1,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala2,skor_aldrette_pasca_anestesi.penilaian_nilai2,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala3,skor_aldrette_pasca_anestesi.penilaian_nilai3,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala4,skor_aldrette_pasca_anestesi.penilaian_nilai4,"+
                    "skor_aldrette_pasca_anestesi.penilaian_skala5,skor_aldrette_pasca_anestesi.penilaian_nilai5,"+
                    "skor_aldrette_pasca_anestesi.penilaian_totalnilai,skor_aldrette_pasca_anestesi.keluar,"+
                    "skor_aldrette_pasca_anestesi.instruksi,skor_aldrette_pasca_anestesi.kd_dokter,dokter.nm_dokter,skor_aldrette_pasca_anestesi.nip,petugas.nama "+
                    "from skor_aldrette_pasca_anestesi inner join reg_periksa on skor_aldrette_pasca_anestesi.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join dokter on skor_aldrette_pasca_anestesi.kd_dokter=dokter.kd_dokter "+
                    "inner join petugas on skor_aldrette_pasca_anestesi.nip=petugas.nip where "+
                    "skor_aldrette_pasca_anestesi.tanggal between ? and ? and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? "+
                    "or skor_aldrette_pasca_anestesi.kd_dokter like ? or dokter.nm_dokter like ? or skor_aldrette_pasca_anestesi.nip like ? or petugas.nama like ?) "+
                    "order by skor_aldrette_pasca_anestesi.tanggal ");
            }
                
            try {
                if(TCari.getText().toString().trim().equals("")){
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                }else{
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                    ps.setString(3,"%"+TCari.getText()+"%");
                    ps.setString(4,"%"+TCari.getText()+"%");
                    ps.setString(5,"%"+TCari.getText()+"%");
                    ps.setString(6,"%"+TCari.getText()+"%");
                    ps.setString(7,"%"+TCari.getText()+"%");
                    ps.setString(8,"%"+TCari.getText()+"%");
                    ps.setString(9,"%"+TCari.getText()+"%");
                }
                    
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getDate("tgl_lahir"),rs.getString("jk"),rs.getString("tanggal"),
                        rs.getString("penilaian_skala1"),rs.getString("penilaian_nilai1"),rs.getString("penilaian_skala2"),rs.getString("penilaian_nilai2"),rs.getString("penilaian_skala3"),
                        rs.getString("penilaian_nilai3"),rs.getString("penilaian_skala4"),rs.getString("penilaian_nilai4"),rs.getString("penilaian_skala5"),rs.getString("penilaian_nilai5"),
                        rs.getString("penilaian_totalnilai"),rs.getString("keluar"),rs.getString("instruksi"),rs.getString("kd_dokter"),rs.getString("nm_dokter"),rs.getString("nip"),rs.getString("nama")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        }catch(SQLException e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabMode.getRowCount());
    }
    
    public void emptTeks() {
        Tanggal.setDate(new Date());
        SkalaKriteria1.setSelectedIndex(0);
        NilaKriteria1.setText("0");
        SkalaKriteria2.setSelectedIndex(0);
        NilaKriteria2.setText("0");
        SkalaKriteria3.setSelectedIndex(0);
        NilaKriteria3.setText("0");
        SkalaKriteria4.setSelectedIndex(0);
        NilaKriteria4.setText("0");
        SkalaKriteria5.setSelectedIndex(0);
        NilaKriteria5.setText("0");
        SkalaKriteria6.setSelectedIndex(0);
        NilaKriteria6.setText("0");
        NilaiKriteriaTotal.setText("0");
        Keluar.setText("");
        Instruksi.setText("");
        TingkatSkor.setText("Pasien Tidak Dapat Dipindahkan Ke Ruangan Perawatan, Karena Kondisi Yang Lemah");
        SkalaKriteria1.requestFocus();
    } 

   private void getData() {
       if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()); 
        if (TNoRw.getText().trim().equals("")) return;
    System.out.println("DEBUG no_rawat = [" + TNoRw.getText() + "]");

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = koneksi.prepareStatement(
                "SELECT "+
                "sa.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.jk, p.tgl_lahir, sa.tanggal, "+
                "sa.penilaian_skala1, sa.penilaian_nilai1, "+
                "sa.penilaian_skala2, sa.penilaian_nilai2, "+
                "sa.penilaian_skala3, sa.penilaian_nilai3, "+
                "sa.penilaian_skala4, sa.penilaian_nilai4, "+
                "sa.penilaian_skala5, sa.penilaian_nilai5, "+
                "sa.penilaian_totalnilai, sa.keluar, sa.instruksi, "+
                "sa.kd_dokter, d.nm_dokter, sa.nip, pt.nama, "+

                // ===== NIHSS DETAIL =====
                "sa.skala_1a, sa.skor_1a, "+
                "sa.skala_1b, sa.skor_1b, "+
                "sa.skala_1c, sa.skor_1c, "+
                "sa.skala_2, sa.skor_2, "+
                "sa.skala_3, sa.skor_3, "+
                "sa.skala_4, sa.skor_4, "+
                "sa.skala_5, sa.skor_5, "+
                "sa.skala_6, sa.skor_6, "+
                "sa.skala_7, sa.skor_7, "+
                "sa.skala_8, sa.skor_8, "+
                "sa.skala_9, sa.skor_9, "+
                "sa.skala_10, sa.skor_10, "+
                "sa.skala_11, sa.skor_11, "+
                "sa.keterangan, sa.keterangan_5, sa.keterangan_6 "+

                "FROM skor_aldrette_pasca_anestesi sa "+
                "INNER JOIN reg_periksa rp ON sa.no_rawat = rp.no_rawat "+
                "INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "+
                "INNER JOIN dokter d ON sa.kd_dokter = d.kd_dokter "+
                "INNER JOIN petugas pt ON sa.nip = pt.nip "+
                "WHERE sa.no_rawat = ?"
            );

            ps.setString(1, TNoRw.getText());
            rs = ps.executeQuery();
            System.out.println("DEBUG QUERY : " + ps.toString());

            if (rs.next()) {

                // ===== IDENTITAS =====
                TNoRw.setText(rs.getString("no_rawat"));
                TNoRM.setText(rs.getString("no_rkm_medis"));
                TPasien.setText(rs.getString("nm_pasien"));
                JK.setText(rs.getString("jk"));
                TglLahir.setText(rs.getString("tgl_lahir"));

                // ===== TANGGAL =====
                Valid.SetTgl(Tanggal, rs.getString("tanggal"));
                Jam.setSelectedItem(rs.getString("tanggal").substring(11,13));
                Menit.setSelectedItem(rs.getString("tanggal").substring(14,16));
                Detik.setSelectedItem(rs.getString("tanggal").substring(17,19));

                // ===== PENILAIAN AWAL =====
                SkalaKriteria1.setSelectedItem(rs.getString("penilaian_skala1"));
                NilaKriteria1.setText(rs.getString("penilaian_nilai1"));

                SkalaKriteria2.setSelectedItem(rs.getString("penilaian_skala2"));
                NilaKriteria2.setText(rs.getString("penilaian_nilai2"));

                SkalaKriteria3.setSelectedItem(rs.getString("penilaian_skala3"));
                NilaKriteria3.setText(rs.getString("penilaian_nilai3"));

                SkalaKriteria4.setSelectedItem(rs.getString("penilaian_skala4"));
                NilaKriteria4.setText(rs.getString("penilaian_nilai4"));

                SkalaKriteria5.setSelectedItem(rs.getString("penilaian_skala5"));
                NilaKriteria5.setText(rs.getString("penilaian_nilai5"));

                NilaiKriteriaTotal.setText(rs.getString("penilaian_totalnilai"));
                Keluar.setText(rs.getString("keluar"));
                Instruksi.setText(rs.getString("instruksi"));

                // ===== DOKTER =====
                KdDokter.setText(rs.getString("kd_dokter"));
                NmDokter.setText(rs.getString("nm_dokter"));
                NIP.setText(rs.getString("nip"));
                NamaPetugas.setText(rs.getString("nama"));
                // ===== NIHSS =====
                SkalaKriteria6.setSelectedItem(rs.getString("skala_1a"));
                NilaKriteria6.setText(rs.getString("skor_1a"));

                SkalaKriteria1b.setSelectedItem(rs.getString("skala_1b"));
                NilaiKriteria1b.setText(rs.getString("skor_1b"));

                SkalaKriteria1c.setSelectedItem(rs.getString("skala_1c"));
                NilaiKriteria1c.setText(rs.getString("skor_1c"));

                SkalaKriteria2baru.setSelectedItem(rs.getString("skala_2"));
                NilaiKriteria2baru.setText(rs.getString("skor_2"));

                SkalaKriteria3baru.setSelectedItem(rs.getString("skala_3"));
                NilaiKriteria3baru.setText(rs.getString("skor_3"));

                SkalaKriteria4baru.setSelectedItem(rs.getString("skala_4"));
                NilaiKriteria4baru.setText(rs.getString("skor_4"));

                SkalaKriteria5baru.setSelectedItem(rs.getString("skala_5"));
                NilaiKriteria5baru.setText(rs.getString("skor_5"));

                SkalaKriteria6baru.setSelectedItem(rs.getString("skala_6"));
                NilaiKriteria6baru.setText(rs.getString("skor_6"));

                SkalaKriteria7baru.setSelectedItem(rs.getString("skala_7"));
                NilaiKriteria7baru.setText(rs.getString("skor_7"));

                SkalaKriteria8baru.setSelectedItem(rs.getString("skala_8"));
                NilaiKriteria8baru.setText(rs.getString("skor_8"));

                SkalaKriteria9baru.setSelectedItem(rs.getString("skala_9"));
                NilaiKriteria9baru.setText(rs.getString("skor_9"));

                SkalaKriteria10baru.setSelectedItem(rs.getString("skala_10"));
                NilaiKriteria10baru.setText(rs.getString("skor_10"));

                SkalaKriteria11baru.setSelectedItem(rs.getString("skala_11"));
                NilaiKriteria11baru.setText(rs.getString("skor_11"));

                // ===== KETERANGAN =====
                TingkatSkor.setText(rs.getString("keterangan"));
                txtJelaskan5.setText(rs.getString("keterangan_5"));
                txtJelaskan6.setText(rs.getString("keterangan_6"));

                isTotalResiko();
            }

        } catch (Exception e) {
            System.out.println("Error getData skor aldrette : " + e);
        } finally {
            if (rs != null) try { rs.close(); } catch (Exception e) {}
            if (ps != null) try { ps.close(); } catch (Exception e) {}
        }
      }
}

    
    private void isRawat() {
        try {
            ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,reg_periksa.tgl_registrasi,reg_periksa.jam_reg "+
                    "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.no_rawat=?");
            try {
                ps.setString(1,TNoRw.getText());
                rs=ps.executeQuery();
                if(rs.next()){
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    JK.setText(rs.getString("jk"));
                    TanggalRegistrasi.setText(rs.getString("tgl_registrasi")+" "+rs.getString("jam_reg"));
                    TglLahir.setText(rs.getString("tgl_lahir"));
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }
    }
    
    public void setNoRm(String norwt, Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(tgl2);
        isRawat();
        ChkInput.setSelected(true);
        isForm();
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            if(internalFrame1.getHeight()>648){
                ChkInput.setVisible(false);
                PanelInput.setPreferredSize(new Dimension(WIDTH,476));
                FormInput.setVisible(true);      
                ChkInput.setVisible(true);
            }else{
                ChkInput.setVisible(false);
                PanelInput.setPreferredSize(new Dimension(WIDTH,internalFrame1.getHeight()-175));
                FormInput.setVisible(true);      
                ChkInput.setVisible(true);
            }
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getskor_aldrette_pasca_anestesi());
        BtnHapus.setEnabled(akses.getskor_aldrette_pasca_anestesi());
        BtnEdit.setEnabled(akses.getskor_aldrette_pasca_anestesi());
        BtnPrint.setEnabled(akses.getskor_aldrette_pasca_anestesi()); 
        if(akses.getjml2()>=1){
            NIP.setEditable(false);
            btnPetugas.setEnabled(false);
            NIP.setText(akses.getkode());
            NamaPetugas.setText(petugas.tampil3(NIP.getText()));
            if(NamaPetugas.getText().equals("")){
                NIP.setText("");
                JOptionPane.showMessageDialog(null,"User login bukan petugas...!!");
            }
        } 
        
        if(TANGGALMUNDUR.equals("no")){
            if(!akses.getkode().equals("Admin Utama")){
                Tanggal.setEditable(false);
                Tanggal.setEnabled(false);
                ChkKejadian.setEnabled(false);
                Jam.setEnabled(false);
                Menit.setEnabled(false);
                Detik.setEnabled(false);
            }
        }
    }

    private void jam(){
        ActionListener taskPerformer = new ActionListener(){
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                
                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if(ChkKejadian.isSelected()==true){
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                }else if(ChkKejadian.isSelected()==false){
                    nilai_jam =Jam.getSelectedIndex();
                    nilai_menit =Menit.getSelectedIndex();
                    nilai_detik =Detik.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                Jam.setSelectedItem(jam);
                Menit.setSelectedItem(menit);
                Detik.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

    private void ganti() {
        if(Sequel.mengedittf("skor_aldrette_pasca_anestesi","tanggal=? and no_rawat=?","no_rawat=?,tanggal=?,penilaian_skala1=?,penilaian_nilai1=?,"+
                "penilaian_skala2=?,penilaian_nilai2=?,penilaian_skala3=?,penilaian_nilai3=?,penilaian_skala4=?,"+
                "penilaian_nilai4=?,penilaian_skala5=?,penilaian_nilai5=?,"+
                "penilaian_totalnilai=?,keluar=?,instruksi=?,kd_dokter=?,nip=?",19,new String[]{
                TNoRw.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem(),
                SkalaKriteria1.getSelectedItem().toString(),NilaKriteria1.getText(),SkalaKriteria2.getSelectedItem().toString(),NilaKriteria2.getText(),SkalaKriteria3.getSelectedItem().toString(),NilaKriteria3.getText(),
                SkalaKriteria4.getSelectedItem().toString(),NilaKriteria4.getText(),SkalaKriteria5.getSelectedItem().toString(),NilaKriteria5.getText(), 
                NilaiKriteriaTotal.getText(),Keluar.getText(),Instruksi.getText(),KdDokter.getText(),NIP.getText(),tbObat.getValueAt(tbObat.getSelectedRow(),5).toString(),
                tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
            })==true){
            tbObat.setValueAt(TNoRw.getText(),tbObat.getSelectedRow(),0);
            tbObat.setValueAt(TNoRM.getText(),tbObat.getSelectedRow(),1);
            tbObat.setValueAt(TPasien.getText(),tbObat.getSelectedRow(),2);
            tbObat.setValueAt(TglLahir.getText(),tbObat.getSelectedRow(),3);
            tbObat.setValueAt(JK.getText(),tbObat.getSelectedRow(),4);
            tbObat.setValueAt(Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem(),tbObat.getSelectedRow(),5);
            tbObat.setValueAt(SkalaKriteria1.getSelectedItem().toString(),tbObat.getSelectedRow(),6);
            tbObat.setValueAt(NilaKriteria1.getText(),tbObat.getSelectedRow(),7);
            tbObat.setValueAt(SkalaKriteria2.getSelectedItem().toString(),tbObat.getSelectedRow(),8);
            tbObat.setValueAt(NilaKriteria2.getText(),tbObat.getSelectedRow(),9);
            tbObat.setValueAt(SkalaKriteria3.getSelectedItem().toString(),tbObat.getSelectedRow(),10);
            tbObat.setValueAt(NilaKriteria3.getText(),tbObat.getSelectedRow(),11);
            tbObat.setValueAt(SkalaKriteria4.getSelectedItem().toString(),tbObat.getSelectedRow(),12);
            tbObat.setValueAt(NilaKriteria4.getText(),tbObat.getSelectedRow(),13);
            tbObat.setValueAt(SkalaKriteria5.getSelectedItem().toString(),tbObat.getSelectedRow(),14);
            tbObat.setValueAt(NilaKriteria5.getText(),tbObat.getSelectedRow(),15);
            tbObat.setValueAt(NilaiKriteriaTotal.getText(),tbObat.getSelectedRow(),16);
            tbObat.setValueAt(Keluar.getText(),tbObat.getSelectedRow(),17);
            tbObat.setValueAt(Instruksi.getText(),tbObat.getSelectedRow(),18);
            tbObat.setValueAt(KdDokter.getText(),tbObat.getSelectedRow(),19);
            tbObat.setValueAt(NmDokter.getText(),tbObat.getSelectedRow(),20);
            tbObat.setValueAt(NIP.getText(),tbObat.getSelectedRow(),21);
            tbObat.setValueAt(NamaPetugas.getText(),tbObat.getSelectedRow(),22);
            emptTeks();
        }
    }

    private void hapus() {
        if(Sequel.queryu2tf("delete from skor_aldrette_pasca_anestesi where tanggal=? and no_rawat=?",2,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),5).toString(),tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        })==true){
            tabMode.removeRow(tbObat.getSelectedRow());
            emptTeks();
            LCount.setText(""+tabMode.getRowCount());
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }
    private int parseNilai(widget.TextBox txt) {
        if (txt == null) return 0;
        String val = txt.getText();
        if (val == null || val.trim().equals("")) return 0;
        try {
            return Integer.parseInt(val.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    private void isTotalResiko() {
        try {
            int total = 0;
            total += parseNilai(NilaKriteria1);
            total += parseNilai(NilaKriteria2);
            total += parseNilai(NilaKriteria3);
            total += parseNilai(NilaKriteria4);
            total += parseNilai(NilaKriteria5);
          

            NilaiKriteriaTotal.setText(String.valueOf(total));

//           
//            if (total <= 5) {
//                TingkatSkor.setText("Defisit neurologis ringan");
//            } else if (total >= 6 && total <= 14) {
//                TingkatSkor.setText("Defisit neurologis sedang");
//            } else if (total >= 15 && total <= 24) {
//                TingkatSkor.setText("Defisit neurologis berat");
//            } else {
//                TingkatSkor.setText("Defisit neurologis sangat berat");
//            }

        } catch (Exception e) {
//            NilaiKriteriaTotal.setText("0");
//            TingkatSkor.setText("Defisit neurologis ringan");
        }
    }
private void simpan() { 
    if(Sequel.menyimpantf("skor_aldrette_pasca_anestesi","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?",
            "Data",17,new String[]{ TNoRw.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+
                    Jam.getSelectedItem()+":"+Menit.getSelectedItem()+
                    ":"+Detik.getSelectedItem(), SkalaKriteria1.getSelectedItem().toString(),NilaKriteria1.getText(),
                    SkalaKriteria2.getSelectedItem().toString(),NilaKriteria2.getText(),SkalaKriteria3.getSelectedItem().toString(),
                    NilaKriteria3.getText(), SkalaKriteria4.getSelectedItem().toString(),NilaKriteria4.getText(),
                    SkalaKriteria5.getSelectedItem().toString(),NilaKriteria5.getText(), NilaiKriteriaTotal.getText(),
                    Keluar.getText(),Instruksi.getText(),KdDokter.getText(),NIP.getText() })==true)
    {
        tabMode.addRow(new Object[]{ TNoRw.getText(),TNoRM.getText(),TPasien.getText(),TglLahir.getText(),JK.getText(),
            Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem(),
            SkalaKriteria1.getSelectedItem().toString(),NilaKriteria1.getText(),SkalaKriteria2.getSelectedItem().toString(),
            NilaKriteria2.getText(),SkalaKriteria3.getSelectedItem().toString(),NilaKriteria3.getText(), 
            SkalaKriteria4.getSelectedItem().toString(),NilaKriteria4.getText(),SkalaKriteria5.getSelectedItem().toString(),
            NilaKriteria5.getText(), NilaiKriteriaTotal.getText(),Keluar.getText(),Instruksi.getText(),KdDokter.getText(),
            NmDokter.getText(),NIP.getText(),NamaPetugas.getText() }); emptTeks(); LCount.setText(""+tabMode.getRowCount()); 
    } 
}
    
    private void bikinScrollKriteria() {
        // 1. Buat Panel Penampung (Wadah untuk item-item Kriteria)
        JPanel panelIsi = new JPanel();
        panelIsi.setLayout(null); // Pakai null layout agar bisa setBounds manual
        
        // Tentukan ukuran panel dalam (WIDTH, HEIGHT). 
        // HEIGHT harus lebih besar dari tinggi ScrollPane agar scrollbar muncul.
        // Misal tinggi total item Anda sekitar 300-400 pixel.
        panelIsi.setPreferredSize(new Dimension(700, 450)); 
        panelIsi.setBackground(new java.awt.Color(255, 255, 255)); // Samakan warna background

        // 2. Pindahkan Komponen ke dalam Panel Baru
        // Kita "re-parent" komponen yang sudah dibuat oleh NetBeans ke panelIsi
        
        // --- Baris 1 (Aktivitas) ---
        // Ubah setLocation(X, Y). Y harus diurutkan dari 10, 40, 70, dst (jarak antar baris)
        jLabel217.setLocation(20, 10);      panelIsi.add(jLabel217);      // Label "1. Aktivitas"
         jLabel217.setLocation(205, 10); panelIsi.add(jLabel217);
        SkalaKriteria1.setLocation(250, 10); panelIsi.add(SkalaKriteria1); // ComboBox
        jLabel218.setLocation(620, 10);     panelIsi.add(jLabel218);      // Label "Nila :"
        NilaKriteria1.setLocation(660, 10); panelIsi.add(NilaKriteria1); // TextBox Nila
        
        // --- Baris 2 (Respirasi) ---
        jLabel219.setLocation(20, 45);      panelIsi.add(jLabel219);
        jLabel221.setLocation(205, 45); panelIsi.add(jLabel221);
        SkalaKriteria2.setLocation(250, 45); panelIsi.add(SkalaKriteria2);
        jLabel222.setLocation(620, 45);     panelIsi.add(jLabel222);
        NilaKriteria2.setLocation(660, 45); panelIsi.add(NilaKriteria2);

        // --- Baris 3 (Tekanan Darah) ---
        jLabel223.setLocation(20, 80);      panelIsi.add(jLabel223);
          jLabel224.setLocation(205, 80); panelIsi.add(jLabel224);
        SkalaKriteria3.setLocation(250, 80); panelIsi.add(SkalaKriteria3);
        jLabel225.setLocation(620, 80);     panelIsi.add(jLabel225);
        NilaKriteria3.setLocation(660, 80); panelIsi.add(NilaKriteria3);

        // --- Baris 4 (Kesadaran) ---
        jLabel226.setLocation(20, 115);     panelIsi.add(jLabel226);
         jLabel227.setLocation(205, 115); panelIsi.add(jLabel227);
        SkalaKriteria4.setLocation(250, 115); panelIsi.add(SkalaKriteria4);
        jLabel228.setLocation(620, 115);    panelIsi.add(jLabel228);
        NilaKriteria4.setLocation(660, 115); panelIsi.add(NilaKriteria4);

        // --- Baris 5 (Warna Kulit) ---
        jLabel229.setLocation(20, 150);     panelIsi.add(jLabel229);
        jLabel230.setLocation(205, 150); panelIsi.add(jLabel230);
        SkalaKriteria5.setLocation(250, 150); panelIsi.add(SkalaKriteria5);
        jLabel231.setLocation(620, 150);    panelIsi.add(jLabel231);
        NilaKriteria5.setLocation(660, 150); panelIsi.add(NilaKriteria5);
        
        jLabel232.setLocation(20, 185);       panelIsi.add(jLabel232);      
    jLabel232.setLocation(20, 185);       panelIsi.add(jLabel233);      // Ganti nama variabel Label Judul sesuai punya Anda
        SkalaKriteria6.setLocation(250, 185); panelIsi.add(SkalaKriteria6); // ComboBox baru
        SkalaKriteria6.setModel(new DefaultComboBoxModel<>(new String[] {
        "-", 
        "0 = Sadar penuh",
        "1 = Tidak sadar penuh; dapat dibangunkan dengan stimulasi minor (suara)",
        "2 = Tidak sadar penuh; dapat berespon dengan stimulasi berulang atau stimulasi nyeri",
        "3 = Koma; tidak sadar dan tidak berespon dengan stimulasi apapun" 
    }));
       
        jLabel233.setLocation(620, 185);      panelIsi.add(jLabel233);      // Label "Nilai :"
        NilaKriteria6.setLocation(660, 185); panelIsi.add(NilaKriteria6);
        
        // 3. Buat JScrollPane dan masukkan PanelIsi ke dalamnya
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(panelIsi);
        
        // Tentukan posisi ScrollPane di Form Utama (Area Kotak Merah Anda)
        // Sesuaikan X, Y, Width, Height dengan posisi kotak merah di gambar design
        scrollPane.setBounds(20, 150, 760, 150); // Contoh: Tinggi hanya 150 agar bisa di-scroll
        
        // 4. Tambahkan ScrollPane ke Form Utama
        this.add(scrollPane);
        
        // Refresh tampilan
        this.revalidate();
        this.repaint();
    }
   private void geserBaris11(boolean turun) {

        int yBaris11 = turun ? 800 : 765;

        label11baru.setBounds(34, yBaris11, 130, 23);
        labelSkala11baru.setBounds(165, yBaris11, 40, 23);
        SkalaKriteria11baru.setBounds(210, yBaris11, 460, 23);
        labelNilai11baru.setBounds(690, yBaris11, 50, 23);
        NilaiKriteria11baru.setBounds(729, yBaris11, 60, 23);

        // WAJIB
        FormInput.revalidate();
        FormInput.repaint();
    }


}
