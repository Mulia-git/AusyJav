/*
 * Kontribusi dari windiarto
 */


package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariDokter;


/**
 *
 * @author perpustakaan
 */
public final class RMPenilaianKK3 extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private StringBuilder htmlContent;
    private String finger="";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMPenilaianKK3(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.RM","Nama Pasien","Tgl.Lahir","J.K.","Kode Dokter","Nama Dokter","Jenis Dokter","Tanggal Pemeriksaan","No Peserta",
            "Tempat Kerja","Tanggal Kecelakaan","Anamnesa","Pemeriksaan Fisik","Penatalaksanaan","Diagnosis","Komplikasi","Keterangan","Sembuh","Cacat Anatomis","Keterangan",
            "Cacat Fungsi","Keterangan","Besar","Terbilang","Prothesa","Keterangan","Orthesa","Keterangan","Meninggal","Keterangan",
            "Setelah Sembuh","Keterangan","Lama Perawatan Awal","Lama Perawatan Akhir","Istirahat Awal","Istirahat Akhir", "Keterangan Lainnya"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 38; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(70);
            }else if(i==2){
                column.setPreferredWidth(150);
            }else if(i==3){
                column.setPreferredWidth(65);
            }else if(i==4){
                column.setPreferredWidth(55);
            }else if(i==5){
                column.setPreferredWidth(80);
            }else if(i==6){
                column.setPreferredWidth(150);
            }else if(i==7){
                column.setPreferredWidth(100);
            }else if(i==8){
                column.setPreferredWidth(150);
            }else if(i==9){
                column.setPreferredWidth(100);
            }else if(i==10){
                column.setPreferredWidth(150);
            }else if(i==11){
                column.setPreferredWidth(100);
            }else if(i==12){
                column.setPreferredWidth(250);
            }else if(i==13){
                column.setPreferredWidth(250);
            }else if(i==14){
                column.setPreferredWidth(250);
            }else if(i==15){
                column.setPreferredWidth(150);
            }else if(i==16){
                column.setPreferredWidth(80);
            }else if(i==17){
                column.setPreferredWidth(100);
            }else if(i==18){
                column.setPreferredWidth(100);
            }else if(i==19){
                column.setPreferredWidth(100);
            }else if(i==20){
                column.setPreferredWidth(100);
            }else if(i==21){
                column.setPreferredWidth(100);
            }else if(i==22){
                column.setPreferredWidth(100);
            }else if(i==23){
                column.setPreferredWidth(100);
            }else if(i==24){
                column.setPreferredWidth(100);
            }else if(i==25){
                column.setPreferredWidth(100);
            }else if(i==26){
                column.setPreferredWidth(100);
            }else if(i==27){
                column.setPreferredWidth(100);
            }else if(i==28){
                column.setPreferredWidth(100);
            }else if(i==29){
                column.setPreferredWidth(100);
            }else if(i==30){
                column.setPreferredWidth(100);
            }else if(i==31){
                column.setPreferredWidth(100);
            }else if(i==32){
                column.setPreferredWidth(100);
            }else if(i==33){
                column.setPreferredWidth(100);
            }else if(i==34){
                column.setPreferredWidth(100);
            }else if(i==35){
                column.setPreferredWidth(100);
            }else if(i==36){
                column.setPreferredWidth(100);
            }else if(i==37){
                column.setPreferredWidth(200);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        NoPeserta.setDocument(new batasInput((int)30).getKata(NoPeserta));
        Keterangan.setDocument(new batasInput((int)3000).getKata(Keterangan));
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
        
        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML.setEditable(true);
        LoadHTML.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(
                ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
        );
        Document doc = kit.createDefaultDocument();
        LoadHTML.setDocument(doc);
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LoadHTML = new widget.editorpane();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnPenilaianKK3 = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        label14 = new widget.Label();
        KdDokter = new widget.TextBox();
        NmDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        Jk = new widget.TextBox();
        jLabel10 = new widget.Label();
        jLabel11 = new widget.Label();
        Komplikasi = new widget.TextBox();
        DdKomplikasi = new widget.ComboBox();
        scrollPane1 = new widget.ScrollPane();
        Fisik = new widget.TextArea();
        scrollPane3 = new widget.ScrollPane();
        Tatalaksana = new widget.TextArea();
        jLabel32 = new widget.Label();
        scrollPane4 = new widget.ScrollPane();
        Anamnesa = new widget.TextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel33 = new widget.Label();
        jSeparator12 = new javax.swing.JSeparator();
        PanelWall = new usu.widget.glass.PanelGlass();
        scrollPane8 = new widget.ScrollPane();
        Keterangan = new widget.TextArea();
        jLabel79 = new widget.Label();
        jLabel100 = new widget.Label();
        label11 = new widget.Label();
        TglLamaAkhir = new widget.Tanggal();
        jLabel34 = new widget.Label();
        NoPeserta = new widget.TextBox();
        jLabel36 = new widget.Label();
        jLabel14 = new widget.Label();
        jLabel38 = new widget.Label();
        jLabel27 = new widget.Label();
        jLabel43 = new widget.Label();
        JnsDokter = new widget.ComboBox();
        Mitra = new widget.TextBox();
        Diagnosa = new widget.TextBox();
        jLabel30 = new widget.Label();
        jLabel31 = new widget.Label();
        jLabel37 = new widget.Label();
        DdSembuh = new widget.ComboBox();
        Anatomis = new widget.TextBox();
        DdAnatomis = new widget.ComboBox();
        jLabel48 = new widget.Label();
        DdFungsi = new widget.ComboBox();
        TerbilangFungsi = new widget.TextBox();
        jLabel54 = new widget.Label();
        Fungsi = new widget.TextBox();
        BesarFungsi = new widget.TextBox();
        jLabel55 = new widget.Label();
        jLabel56 = new widget.Label();
        jLabel57 = new widget.Label();
        jLabel58 = new widget.Label();
        jLabel59 = new widget.Label();
        jLabel60 = new widget.Label();
        jLabel61 = new widget.Label();
        jLabel62 = new widget.Label();
        jLabel63 = new widget.Label();
        jLabel64 = new widget.Label();
        jLabel65 = new widget.Label();
        DdProthesa = new widget.ComboBox();
        Prothesa = new widget.TextBox();
        DdOrthesa = new widget.ComboBox();
        Orthesa = new widget.TextBox();
        jLabel66 = new widget.Label();
        jLabel67 = new widget.Label();
        DdMeninggal = new widget.ComboBox();
        TglPemeriksaan = new widget.Tanggal();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel68 = new widget.Label();
        jLabel69 = new widget.Label();
        DdSetelah = new widget.ComboBox();
        Setelah = new widget.TextBox();
        jLabel70 = new widget.Label();
        jLabel71 = new widget.Label();
        jLabel72 = new widget.Label();
        jLabel73 = new widget.Label();
        jLabel75 = new widget.Label();
        TglKecelakaan = new widget.Tanggal();
        jLabel74 = new widget.Label();
        TglLamaAwal = new widget.Tanggal();
        TglIstirahatAkhir = new widget.Tanggal();
        jLabel76 = new widget.Label();
        jLabel77 = new widget.Label();
        TglIstirahatAwal = new widget.Tanggal();
        jLabel78 = new widget.Label();
        Meninggal = new widget.TextBox();
        Meninggal1 = new widget.TextBox();
        jLabel80 = new widget.Label();
        jLabel81 = new widget.Label();
        internalFrame3 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnPenilaianKK3.setBackground(new java.awt.Color(255, 255, 254));
        MnPenilaianKK3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPenilaianKK3.setForeground(new java.awt.Color(50, 50, 50));
        MnPenilaianKK3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPenilaianKK3.setText("Laporan Pengkajian Medis");
        MnPenilaianKK3.setName("MnPenilaianKK3"); // NOI18N
        MnPenilaianKK3.setPreferredSize(new java.awt.Dimension(220, 26));
        MnPenilaianKK3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPenilaianKK3ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPenilaianKK3);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Penilaian KK3 BPJS Ketenagakerjaan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 54));
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

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 30));
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
        panelGlass8.add(BtnAll);

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

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(254, 255, 254));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1083));
        FormInput.setLayout(null);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(74, 10, 131, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(309, 10, 260, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(207, 10, 100, 23);

        label14.setText("Dokter :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(0, 40, 70, 23);

        KdDokter.setEditable(false);
        KdDokter.setName("KdDokter"); // NOI18N
        KdDokter.setPreferredSize(new java.awt.Dimension(80, 23));
        KdDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdDokterKeyPressed(evt);
            }
        });
        FormInput.add(KdDokter);
        KdDokter.setBounds(74, 40, 90, 23);

        NmDokter.setEditable(false);
        NmDokter.setName("NmDokter"); // NOI18N
        NmDokter.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmDokter);
        NmDokter.setBounds(166, 40, 180, 23);

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
        BtnDokter.setBounds(348, 40, 28, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(580, 10, 60, 23);

        TglLahir.setEditable(false);
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(644, 10, 80, 23);

        Jk.setEditable(false);
        Jk.setHighlighter(null);
        Jk.setName("Jk"); // NOI18N
        FormInput.add(Jk);
        Jk.setBounds(774, 10, 80, 23);

        jLabel10.setText("No.Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 70, 23);

        jLabel11.setText("J.K. :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(740, 10, 30, 23);

        Komplikasi.setFocusTraversalPolicyProvider(true);
        Komplikasi.setName("Komplikasi"); // NOI18N
        Komplikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KomplikasiKeyPressed(evt);
            }
        });
        FormInput.add(Komplikasi);
        Komplikasi.setBounds(190, 530, 660, 23);

        DdKomplikasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        DdKomplikasi.setName("DdKomplikasi"); // NOI18N
        DdKomplikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DdKomplikasiKeyPressed(evt);
            }
        });
        FormInput.add(DdKomplikasi);
        DdKomplikasi.setBounds(110, 530, 70, 23);

        scrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane1.setName("scrollPane1"); // NOI18N

        Fisik.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Fisik.setColumns(20);
        Fisik.setRows(5);
        Fisik.setName("Fisik"); // NOI18N
        Fisik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FisikKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(Fisik);

        FormInput.add(scrollPane1);
        scrollPane1.setBounds(440, 240, 410, 100);

        scrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane3.setName("scrollPane3"); // NOI18N

        Tatalaksana.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tatalaksana.setColumns(20);
        Tatalaksana.setRows(5);
        Tatalaksana.setName("Tatalaksana"); // NOI18N
        Tatalaksana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TatalaksanaKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(Tatalaksana);

        FormInput.add(scrollPane3);
        scrollPane3.setBounds(440, 350, 410, 120);

        jLabel32.setText("Penatalaksanaan");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(330, 350, 90, 23);

        scrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane4.setName("scrollPane4"); // NOI18N

        Anamnesa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Anamnesa.setColumns(20);
        Anamnesa.setRows(5);
        Anamnesa.setName("Anamnesa"); // NOI18N
        Anamnesa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AnamnesaKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(Anamnesa);

        FormInput.add(scrollPane4);
        scrollPane4.setBounds(440, 110, 410, 110);

        jSeparator1.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator1.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(0, 70, 880, 1);

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("Nama Pemberi Kerja / Wadah / Mitra / Proyek Jasa Konstruksi :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(40, 140, 360, 23);

        jSeparator12.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator12.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator12.setName("jSeparator12"); // NOI18N
        FormInput.add(jSeparator12);
        jSeparator12.setBounds(0, 565, 880, 1);

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/rekammedis/kk3.PNG"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.setLayout(null);
        FormInput.add(PanelWall);
        PanelWall.setBounds(50, 240, 270, 230);

        scrollPane8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane8.setName("scrollPane8"); // NOI18N

        Keterangan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Keterangan.setColumns(20);
        Keterangan.setRows(5);
        Keterangan.setName("Keterangan"); // NOI18N
        Keterangan.setPreferredSize(new java.awt.Dimension(182, 92));
        Keterangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganKeyPressed(evt);
            }
        });
        scrollPane8.setViewportView(Keterangan);

        FormInput.add(scrollPane8);
        scrollPane8.setBounds(40, 950, 810, 83);

        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel79.setText("Keterangan Lainnya Jika Perlu :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(40, 930, 260, 23);

        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel100.setText("HASIL PEMERIKSAAN ATAU PENGOBATAN");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(40, 570, 810, 23);

        label11.setText("Tanggal Pemeriksaan Dokter :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(560, 40, 150, 23);

        TglLamaAkhir.setDisplayFormat("dd-MM-yyyy");
        TglLamaAkhir.setName("TglLamaAkhir"); // NOI18N
        TglLamaAkhir.setPreferredSize(new java.awt.Dimension(95, 23));
        TglLamaAkhir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglLamaAkhirKeyPressed(evt);
            }
        });
        FormInput.add(TglLamaAkhir);
        TglLamaAkhir.setBounds(310, 900, 100, 23);

        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("Anamnesa :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(440, 90, 410, 23);

        NoPeserta.setFocusTraversalPolicyProvider(true);
        NoPeserta.setName("NoPeserta"); // NOI18N
        NoPeserta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoPesertaKeyPressed(evt);
            }
        });
        FormInput.add(NoPeserta);
        NoPeserta.setBounds(40, 110, 360, 23);

        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("No Peserta BPJS Ketenagakerjaan  :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(40, 90, 360, 23);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Kehilangan Anggota Badan");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(40, 660, 140, 23);

        jLabel38.setText("Pemeriksaan Fisik");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(330, 240, 90, 23);

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("Tanggal Kecelakaan :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(40, 200, 110, 23);

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel43.setText("Diagnosa :");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(40, 490, 70, 23);

        JnsDokter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dokter Pemeriksa", "Dokter Penasehat" }));
        JnsDokter.setName("JnsDokter"); // NOI18N
        JnsDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JnsDokterKeyPressed(evt);
            }
        });
        FormInput.add(JnsDokter);
        JnsDokter.setBounds(390, 40, 128, 23);

        Mitra.setFocusTraversalPolicyProvider(true);
        Mitra.setName("Mitra"); // NOI18N
        Mitra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MitraKeyPressed(evt);
            }
        });
        FormInput.add(Mitra);
        Mitra.setBounds(40, 160, 360, 23);

        Diagnosa.setFocusTraversalPolicyProvider(true);
        Diagnosa.setName("Diagnosa"); // NOI18N
        Diagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaKeyPressed(evt);
            }
        });
        FormInput.add(Diagnosa);
        Diagnosa.setBounds(110, 490, 740, 23);

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("Komplikasi :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(40, 530, 70, 23);

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Sembuh Tanpa Cacat");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(40, 600, 140, 23);

        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("Cacat Anatomis Akibat");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(40, 640, 140, 23);

        DdSembuh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        DdSembuh.setName("DdSembuh"); // NOI18N
        DdSembuh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DdSembuhKeyPressed(evt);
            }
        });
        FormInput.add(DdSembuh);
        DdSembuh.setBounds(200, 600, 70, 23);

        Anatomis.setFocusTraversalPolicyProvider(true);
        Anatomis.setName("Anatomis"); // NOI18N
        Anatomis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AnatomisKeyPressed(evt);
            }
        });
        FormInput.add(Anatomis);
        Anatomis.setBounds(280, 650, 570, 23);

        DdAnatomis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        DdAnatomis.setName("DdAnatomis"); // NOI18N
        DdAnatomis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DdAnatomisKeyPressed(evt);
            }
        });
        FormInput.add(DdAnatomis);
        DdAnatomis.setBounds(200, 650, 70, 23);

        jLabel48.setText("Terbilang");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(450, 720, 60, 23);

        DdFungsi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        DdFungsi.setName("DdFungsi"); // NOI18N
        DdFungsi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DdFungsiKeyPressed(evt);
            }
        });
        FormInput.add(DdFungsi);
        DdFungsi.setBounds(200, 690, 70, 23);

        TerbilangFungsi.setFocusTraversalPolicyProvider(true);
        TerbilangFungsi.setName("TerbilangFungsi"); // NOI18N
        TerbilangFungsi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TerbilangFungsiKeyPressed(evt);
            }
        });
        FormInput.add(TerbilangFungsi);
        TerbilangFungsi.setBounds(510, 720, 340, 23);

        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel54.setText("Cacat Fungsi Anggota Badan");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(40, 700, 150, 23);

        Fungsi.setFocusTraversalPolicyProvider(true);
        Fungsi.setName("Fungsi"); // NOI18N
        Fungsi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FungsiKeyPressed(evt);
            }
        });
        FormInput.add(Fungsi);
        Fungsi.setBounds(280, 690, 570, 23);

        BesarFungsi.setFocusTraversalPolicyProvider(true);
        BesarFungsi.setName("BesarFungsi"); // NOI18N
        BesarFungsi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BesarFungsiKeyPressed(evt);
            }
        });
        FormInput.add(BesarFungsi);
        BesarFungsi.setBounds(360, 720, 70, 23);

        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel55.setText("Dengan Besarnya Cacat Fungsi");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(200, 720, 160, 23);

        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel56.setText(" : ");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(420, 240, 20, 23);

        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel57.setText("%");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(430, 720, 20, 23);

        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel58.setText(" : ");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(180, 700, 20, 23);

        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel59.setText(" : ");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(180, 650, 20, 23);

        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel60.setText(" : ");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(180, 600, 20, 23);

        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel61.setText(" : ");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(420, 350, 20, 23);

        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel62.setText(" : ");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(180, 750, 20, 23);

        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel63.setText("Memerlukan Prothesa ");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(40, 750, 150, 23);

        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel64.setText(" : ");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(180, 780, 20, 23);

        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel65.setText("Memerlukan Orthesa");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(40, 780, 150, 23);

        DdProthesa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        DdProthesa.setName("DdProthesa"); // NOI18N
        DdProthesa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DdProthesaKeyPressed(evt);
            }
        });
        FormInput.add(DdProthesa);
        DdProthesa.setBounds(200, 750, 70, 23);

        Prothesa.setFocusTraversalPolicyProvider(true);
        Prothesa.setName("Prothesa"); // NOI18N
        Prothesa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProthesaKeyPressed(evt);
            }
        });
        FormInput.add(Prothesa);
        Prothesa.setBounds(280, 750, 570, 23);

        DdOrthesa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        DdOrthesa.setName("DdOrthesa"); // NOI18N
        DdOrthesa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DdOrthesaKeyPressed(evt);
            }
        });
        FormInput.add(DdOrthesa);
        DdOrthesa.setBounds(200, 780, 70, 23);

        Orthesa.setFocusTraversalPolicyProvider(true);
        Orthesa.setName("Orthesa"); // NOI18N
        Orthesa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OrthesaKeyPressed(evt);
            }
        });
        FormInput.add(Orthesa);
        Orthesa.setBounds(280, 780, 570, 23);

        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel66.setText(" : ");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(180, 810, 20, 23);

        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel67.setText("Sampai");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(260, 900, 50, 23);

        DdMeninggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        DdMeninggal.setName("DdMeninggal"); // NOI18N
        DdMeninggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DdMeninggalKeyPressed(evt);
            }
        });
        FormInput.add(DdMeninggal);
        DdMeninggal.setBounds(200, 810, 70, 23);

        TglPemeriksaan.setForeground(new java.awt.Color(50, 70, 50));
        TglPemeriksaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "02-09-2025 09:51:44" }));
        TglPemeriksaan.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglPemeriksaan.setName("TglPemeriksaan"); // NOI18N
        TglPemeriksaan.setOpaque(false);
        TglPemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglPemeriksaanKeyPressed(evt);
            }
        });
        FormInput.add(TglPemeriksaan);
        TglPemeriksaan.setBounds(720, 40, 130, 23);

        jSeparator13.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator13.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator13.setName("jSeparator13"); // NOI18N
        FormInput.add(jSeparator13);
        jSeparator13.setBounds(0, 840, 880, 1);

        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel68.setText(" : ");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(180, 860, 20, 23);

        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel69.setText("Dapat Melakukan Pekerjaan");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(40, 870, 150, 23);

        DdSetelah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Biasa Dengan Kondisi", "Ringan Dengan Kondisi", "TIdak Dapat Bekerja" }));
        DdSetelah.setName("DdSetelah"); // NOI18N
        DdSetelah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DdSetelahActionPerformed(evt);
            }
        });
        DdSetelah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DdSetelahKeyPressed(evt);
            }
        });
        FormInput.add(DdSetelah);
        DdSetelah.setBounds(200, 860, 140, 23);

        Setelah.setFocusTraversalPolicyProvider(true);
        Setelah.setName("Setelah"); // NOI18N
        Setelah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SetelahKeyPressed(evt);
            }
        });
        FormInput.add(Setelah);
        Setelah.setBounds(390, 860, 460, 23);

        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel70.setText("Setelah Sembuh Peserta");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(40, 850, 150, 23);

        jLabel71.setText("Jam");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(650, 810, 30, 23);

        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel72.setText("Meninggal Dunia");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(40, 810, 150, 23);

        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel73.setText(" : ");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(130, 900, 20, 23);

        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel75.setText("Lama Perawatan");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(40, 900, 90, 23);

        TglKecelakaan.setDisplayFormat("dd-MM-yyyy");
        TglKecelakaan.setName("TglKecelakaan"); // NOI18N
        TglKecelakaan.setPreferredSize(new java.awt.Dimension(95, 23));
        TglKecelakaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglKecelakaanKeyPressed(evt);
            }
        });
        FormInput.add(TglKecelakaan);
        TglKecelakaan.setBounds(150, 200, 250, 23);

        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel74.setText("Berupa");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(350, 860, 50, 23);

        TglLamaAwal.setDisplayFormat("dd-MM-yyyy");
        TglLamaAwal.setName("TglLamaAwal"); // NOI18N
        TglLamaAwal.setPreferredSize(new java.awt.Dimension(95, 23));
        TglLamaAwal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglLamaAwalKeyPressed(evt);
            }
        });
        FormInput.add(TglLamaAwal);
        TglLamaAwal.setBounds(150, 900, 100, 23);

        TglIstirahatAkhir.setDisplayFormat("dd-MM-yyyy");
        TglIstirahatAkhir.setName("TglIstirahatAkhir"); // NOI18N
        TglIstirahatAkhir.setPreferredSize(new java.awt.Dimension(95, 23));
        TglIstirahatAkhir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglIstirahatAkhirKeyPressed(evt);
            }
        });
        FormInput.add(TglIstirahatAkhir);
        TglIstirahatAkhir.setBounds(750, 900, 100, 23);

        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel76.setText("Sampai");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(700, 900, 50, 23);

        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel77.setText(" : ");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(570, 900, 20, 23);

        TglIstirahatAwal.setDisplayFormat("dd-MM-yyyy");
        TglIstirahatAwal.setName("TglIstirahatAwal"); // NOI18N
        TglIstirahatAwal.setPreferredSize(new java.awt.Dimension(95, 23));
        TglIstirahatAwal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglIstirahatAwalKeyPressed(evt);
            }
        });
        FormInput.add(TglIstirahatAwal);
        TglIstirahatAwal.setBounds(590, 900, 100, 23);

        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel78.setText("Diberikan Istirahat");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(480, 900, 90, 23);

        Meninggal.setFocusTraversalPolicyProvider(true);
        Meninggal.setName("Meninggal"); // NOI18N
        Meninggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MeninggalKeyPressed(evt);
            }
        });
        FormInput.add(Meninggal);
        Meninggal.setBounds(330, 810, 320, 23);

        Meninggal1.setFocusTraversalPolicyProvider(true);
        Meninggal1.setName("Meninggal1"); // NOI18N
        Meninggal1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Meninggal1KeyPressed(evt);
            }
        });
        FormInput.add(Meninggal1);
        Meninggal1.setBounds(690, 810, 160, 23);

        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel80.setText("Meninggal Dunia");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(40, 810, 150, 23);

        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel81.setText("Tanggal");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(280, 810, 50, 23);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Pengkajian", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

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

        internalFrame3.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tgl.Asuhan :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "02-09-2025" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "02-09-2025" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(195, 23));
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

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(LCount);

        internalFrame3.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Data Pengkajian", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
        }else{            
            Valid.pindah(evt,TCari,BtnDokter);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(NmDokter.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Dokter");
        }else{
            if(Sequel.menyimpantf("penilaian_kk3","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat",33,new String[]{
                TNoRw.getText(), // no_rawat
                KdDokter.getText(), // kd_dokter
                JnsDokter.getSelectedItem().toString(), // jns_dokter
                Valid.SetTgl(TglPemeriksaan.getSelectedItem()+"")+" "+TglPemeriksaan.getSelectedItem().toString().substring(11,19), // tgl_periksa (datetime)
                NoPeserta.getText(), // no_peserta
                Mitra.getText(), // mitra
                Valid.SetTgl(TglKecelakaan.getSelectedItem()+""), // tgl_kecelakaan
                Anamnesa.getText(), // anamnesa
                Fisik.getText(), // fisik
                Tatalaksana.getText(), // tatalaksana
                Diagnosa.getText(), // diagnose
                DdKomplikasi.getSelectedItem().toString(), // komplikasi_pilih
                Komplikasi.getText(), // komplikasi
                DdSembuh.getSelectedItem().toString(), // sembuh
                DdAnatomis.getSelectedItem().toString(), // anatomis_pilih
                Anatomis.getText(), // anatomis
                DdFungsi.getSelectedItem().toString(), // fungsi_pilih
                Fungsi.getText(), // fungsi
                BesarFungsi.getText(), // fungsi_besar
                TerbilangFungsi.getText(), // fungsi_terbilang
                DdProthesa.getSelectedItem().toString(), // prothesa_pilih
                Prothesa.getText(), // prothesa
                DdOrthesa.getSelectedItem().toString(), // orthesa_pilih
                Orthesa.getText(), // orthesa
                DdMeninggal.getSelectedItem().toString(), // meninggal_pilih 
                Meninggal.getText(), // meninggal (date)
                DdSetelah.getSelectedItem().toString(), // setelah_pilih
                Setelah.getText(), // setelah
                Valid.SetTgl(TglLamaAwal.getSelectedItem()+""), // lama_awal
                Valid.SetTgl(TglLamaAkhir.getSelectedItem()+""), // lama_akhir
                Valid.SetTgl(TglIstirahatAwal.getSelectedItem()+""), // istirahat_awal
                Valid.SetTgl(TglIstirahatAkhir.getSelectedItem()+""), // istirahat_akhir
                Keterangan.getText() // keterangan
            })==true){
                emptTeks();
            }

        }
    
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,BtnBatal,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
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
                if(KdDokter.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString())){
                    hapus();
                }else{
                    JOptionPane.showMessageDialog(null,"Hanya bisa dihapus oleh dokter yang bersangkutan..!!");
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
        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(NmDokter.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Dokter");
        }else{
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode().equals("Admin Utama")){
                    ganti();
                }else{
                    if(KdDokter.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString())){
                        ganti();
                    }else{
                        JOptionPane.showMessageDialog(null,"Hanya bisa diganti oleh dokter yang bersangkutan..!!");
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
            try{
                if(TCari.getText().trim().equals("")){
                    ps = koneksi.prepareStatement(
    "SELECT reg_periksa.no_rawat, " +
    "       pasien.no_rkm_medis, " +
    "       pasien.nm_pasien, " +
    "       IF(pasien.jk='L','Laki-Laki','Perempuan') AS jk, " +
    "       pasien.tgl_lahir, " +
    "       penilaian_kk3.tgl_periksa, " +
    "       penilaian_kk3.kd_dokter, " +
    "       penilaian_kk3.jns_dokter, " +
    "       penilaian_kk3.no_peserta, " +
    "       penilaian_kk3.mitra, " +
    "       penilaian_kk3.tgl_kecelakaan, " +
    "       penilaian_kk3.anamnesa, " +
    "       penilaian_kk3.fisik, " +
    "       penilaian_kk3.tatalaksana, " +
    "       penilaian_kk3.diagnose, " +
    "       penilaian_kk3.komplikasi_pilih, " +
    "       penilaian_kk3.komplikasi, " +
    "       penilaian_kk3.sembuh, " +
    "       penilaian_kk3.anatomis_pilih, " +
    "       penilaian_kk3.anatomis, " +
    "       penilaian_kk3.fungsi_pilih, " +
    "       penilaian_kk3.fungsi, " +
    "       penilaian_kk3.fungsi_besar, " +
    "       penilaian_kk3.fungsi_terbilang, " +
    "       penilaian_kk3.prothesa_pilih, " +
    "       penilaian_kk3.prothesa, " +
    "       penilaian_kk3.orthesa_pilih, " +
    "       penilaian_kk3.orthesa, " +
    "       penilaian_kk3.meninggal_pilih, " +
    "       penilaian_kk3.meninggal, " +
    "       penilaian_kk3.setelah_pilih, " +
    "       penilaian_kk3.setelah, " +
    "       penilaian_kk3.lama_awal, " +
    "       penilaian_kk3.lama_akhir, " +
    "       penilaian_kk3.istirahat_awal, " +
    "       penilaian_kk3.istirahat_akhir, " +
    "       penilaian_kk3.keterangan, " +
    "       dokter.nm_dokter " +
    "FROM reg_periksa " +
    "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis " +
    "INNER JOIN penilaian_kk3 ON reg_periksa.no_rawat = penilaian_kk3.no_rawat " +
    "INNER JOIN dokter ON penilaian_kk3.kd_dokter = dokter.kd_dokter " +
    "WHERE penilaian_kk3.tgl_periksa BETWEEN ? AND ? " +
    "ORDER BY penilaian_kk3.tgl_periksa"
);

                }else{
                    ps = koneksi.prepareStatement(
    "SELECT reg_periksa.no_rawat, pasien.no_rkm_medis, pasien.nm_pasien, " +
    "IF(pasien.jk='L','Laki-Laki','Perempuan') AS jk, pasien.tgl_lahir, " +
    "penilaian_kk3.tgl_periksa, penilaian_kk3.kd_dokter, " +
    "penilaian_kk3.jns_dokter, penilaian_kk3.no_peserta,penilaian_kk3.mitra, penilaian_kk3.tgl_kecelakaan, " +
    "penilaian_kk3.anamnesa, penilaian_kk3.fisik, penilaian_kk3.tatalaksana, " +
    "penilaian_kk3.diagnose, penilaian_kk3.komplikasi_pilih, penilaian_kk3.komplikasi, " +
    "penilaian_kk3.sembuh, penilaian_kk3.anatomis_pilih, penilaian_kk3.anatomis, " +
    "penilaian_kk3.fungsi_pilih, penilaian_kk3.fungsi, penilaian_kk3.fungsi_besar, " +
    "penilaian_kk3.fungsi_terbilang, penilaian_kk3.prothesa_pilih, penilaian_kk3.prothesa, " +
    "penilaian_kk3.orthesa_pilih, penilaian_kk3.orthesa, penilaian_kk3.meninggal_pilih, " +
    "penilaian_kk3.meninggal, penilaian_kk3.setelah_pilih, penilaian_kk3.setelah, " +
    "penilaian_kk3.lama_awal, penilaian_kk3.lama_akhir, penilaian_kk3.istirahat_awal, " +
    "penilaian_kk3.istirahat_akhir, penilaian_kk3.keterangan, dokter.nm_dokter " +
    "FROM reg_periksa " +
    "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis " +
    "INNER JOIN penilaian_kk3 ON reg_periksa.no_rawat = penilaian_kk3.no_rawat " +
    "INNER JOIN dokter ON penilaian_kk3.kd_dokter = dokter.kd_dokter " +
    "WHERE penilaian_kk3.tgl_periksa BETWEEN ? AND ? " +
    "AND (reg_periksa.no_rawat LIKE ? OR pasien.no_rkm_medis LIKE ? " +
    "OR pasien.nm_pasien LIKE ? OR penilaian_kk3.kd_dokter LIKE ? " +
    "OR dokter.nm_dokter LIKE ?) " +
    "ORDER BY penilaian_kk3.tgl_periksa"
);

                }

                try {
                    if(TCari.getText().trim().equals("")){
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
                    }  
                    rs=ps.executeQuery();
                    htmlContent = new StringBuilder();
                    htmlContent.append(                             
                        "<tr class='isi'>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='105px'><b>No.Rawat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'><b>No.RM</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Nama Pasien</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'><b>Tgl.Lahir</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='55px'><b>J.K.</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Kode Dokter</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Nama Dokter</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='115px'><b>Tanggal</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Anamnesis</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='100px'><b>Hubungan</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='300px'><b>Keluhan Utama</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Riwayat Penyakit Sekarang</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Riwayat Penyakit Dahulu</b></td>"+
			    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Riwayat Penyakit Keluarga</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Riwayat Penggunakan Obat</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='120px'><b>Riwayat Alergi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='90px'><b>Keadaan Umum</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50px'><b>GCS</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Kesadaran</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='60px'><b>TD(mmHg)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='75px'><b>Nadi(x/menit)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='67px'><b>RR(x/menit)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'><b>Suhu</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'><b>SpO2</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'><b>BB(Kg)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'><b>TB(cm)</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Kepala</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Mata</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Gigi & Mulut</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>THT</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Thoraks</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Jantung</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Paru</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Abdomen</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Genital & Anus</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Ekstremitas</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'><b>Kulit</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='300px'><b>Ket.Pemeriksaan Fisik</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='200px'><b>Ket.Status Lokalis</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='170px'><b>Laboratorium</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='170px'><b>Radiologi</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='170px'><b>Penunjang Lainnya</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Diagnosis/Asesmen</b></td>"+
			    "<td valign='middle' bgcolor='#FFFAF8' align='center' width='300px'><b>Tatalaksana</b></td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'><b>Edukasi</b></td>"+
                        "</tr>"
                    );
                    while(rs.next()){
                        htmlContent.append(
                            "<tr class='isi'>"+
                               "<td valign='top'>"+rs.getString("no_rawat")+"</td>"+
                               "<td valign='top'>"+rs.getString("no_rkm_medis")+"</td>"+
                               "<td valign='top'>"+rs.getString("nm_pasien")+"</td>"+
                               "<td valign='top'>"+rs.getString("tgl_lahir")+"</td>"+
                               "<td valign='top'>"+rs.getString("jk")+"</td>"+
                               "<td valign='top'>"+rs.getString("kd_dokter")+"</td>"+
                               "<td valign='top'>"+rs.getString("nm_dokter")+"</td>"+
                               "<td valign='top'>"+rs.getString("tanggal")+"</td>"+
                               "<td valign='top'>"+rs.getString("anamnesis")+"</td>"+
                               "<td valign='top'>"+rs.getString("hubungan")+"</td>"+
                               "<td valign='top'>"+rs.getString("keluhan_utama")+"</td>"+
                               "<td valign='top'>"+rs.getString("rps")+"</td>"+
                               "<td valign='top'>"+rs.getString("rpd")+"</td>"+
                               "<td valign='top'>"+rs.getString("rpk")+"</td>"+
                               "<td valign='top'>"+rs.getString("rpo")+"</td>"+
                               "<td valign='top'>"+rs.getString("alergi")+"</td>"+
                               "<td valign='top'>"+rs.getString("keadaan")+"</td>"+
                               "<td valign='top'>"+rs.getString("gcs")+"</td>"+
                               "<td valign='top'>"+rs.getString("kesadaran")+"</td>"+
                               "<td valign='top'>"+rs.getString("td")+"</td>"+
                               "<td valign='top'>"+rs.getString("nadi")+"</td>"+
                               "<td valign='top'>"+rs.getString("rr")+"</td>"+
                               "<td valign='top'>"+rs.getString("suhu")+"</td>"+
                               "<td valign='top'>"+rs.getString("spo")+"</td>"+
                               "<td valign='top'>"+rs.getString("bb")+"</td>"+
                               "<td valign='top'>"+rs.getString("tb")+"</td>"+
                               "<td valign='top'>"+rs.getString("kepala")+"</td>"+
                               "<td valign='top'>"+rs.getString("mata")+"</td>"+
                               "<td valign='top'>"+rs.getString("gigi")+"</td>"+
                               "<td valign='top'>"+rs.getString("tht")+"</td>"+
                               "<td valign='top'>"+rs.getString("thoraks")+"</td>"+
                               "<td valign='top'>"+rs.getString("jantung")+"</td>"+
                               "<td valign='top'>"+rs.getString("paru")+"</td>"+
                               "<td valign='top'>"+rs.getString("abdomen")+"</td>"+
                               "<td valign='top'>"+rs.getString("genital")+"</td>"+
                               "<td valign='top'>"+rs.getString("ekstremitas")+"</td>"+
                               "<td valign='top'>"+rs.getString("kulit")+"</td>"+
                               "<td valign='top'>"+rs.getString("ket_fisik")+"</td>"+
                               "<td valign='top'>"+rs.getString("ket_lokalis")+"</td>"+
                               "<td valign='top'>"+rs.getString("lab")+"</td>"+
                               "<td valign='top'>"+rs.getString("rad")+"</td>"+
                               "<td valign='top'>"+rs.getString("penunjang")+"</td>"+
                               "<td valign='top'>"+rs.getString("diagnosis")+"</td>"+
                               "<td valign='top'>"+rs.getString("tata")+"</td>"+
                               "<td valign='top'>"+rs.getString("edukasi")+"</td>"+
                            "</tr>");
                    }
                    LoadHTML.setText(
                        "<html>"+
                          "<table width='4600px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
                           htmlContent.toString()+
                          "</table>"+
                        "</html>"
                    );

                    File g = new File("file2.css");            
                    BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                    bg.write(
                        ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                        ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                        ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                        ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                        ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                        ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                        ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
                    );
                    bg.close();

                    File f = new File("DataPenilaianAwalMedisRanap.html");            
                    BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                    bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                                "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                                "<table width='4600px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                    "<tr class='isi2'>"+
                                        "<td valign='top' align='center'>"+
                                            "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                            akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                            akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                            "<font size='2' face='Tahoma'>DATA PENGKAJIAN AWAL MEDIS RAWAT INAP<br><br></font>"+        
                                        "</td>"+
                                   "</tr>"+
                                "</table>")
                    );
                    bw.close();                         
                    Desktop.getDesktop().browse(f.toURI());
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

            }catch(Exception e){
                System.out.println("Notifikasi : "+e);
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

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if((evt.getClickCount()==2)&&(tbObat.getSelectedColumn()==0)){
                TabRawat.setSelectedIndex(0);
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }else if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                try {
                    getData();
                    TabRawat.setSelectedIndex(0);
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void KdDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdDokterKeyPressed
        
    }//GEN-LAST:event_KdDokterKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
        //Valid.pindah(evt,Monitoring,BtnSimpan);
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void KomplikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KomplikasiKeyPressed
        Valid.pindah(evt,Anamnesa,DdKomplikasi);
    }//GEN-LAST:event_KomplikasiKeyPressed

    private void DdKomplikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DdKomplikasiKeyPressed
        Valid.pindah(evt,Meninggal,DdKomplikasi);
    }//GEN-LAST:event_DdKomplikasiKeyPressed

    private void FisikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FisikKeyPressed
        Valid.pindah2(evt,DdKomplikasi,DdKomplikasi);
    }//GEN-LAST:event_FisikKeyPressed

    private void TatalaksanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TatalaksanaKeyPressed
        Valid.pindah2(evt,DdKomplikasi,DdKomplikasi);
    }//GEN-LAST:event_TatalaksanaKeyPressed

    private void AnamnesaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AnamnesaKeyPressed
        Valid.pindah2(evt,DdKomplikasi,Komplikasi);
    }//GEN-LAST:event_AnamnesaKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==1){
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void KeteranganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganKeyPressed
        Valid.pindah2(evt,DdKomplikasi,DdKomplikasi);
    }//GEN-LAST:event_KeteranganKeyPressed

    private void MnPenilaianKK3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenilaianKK3ActionPerformed
        if(tbObat.getSelectedRow()>-1){
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());          
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            try {
                param.put("lokalis",getClass().getResource("/picture/semua.png").openStream());
            } catch (Exception e) {
            } 
            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),5).toString():finger)+"\n"+Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString())); 
            
            Valid.MyReportqry("rptCetakPenilaianAwalMedisRanap.jasper","report","::[ Laporan Pengkajian Awal Medis Rawat Inap ]::",
                "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,penilaian_medis_ranap.tanggal,"+
                "penilaian_medis_ranap.kd_dokter,penilaian_medis_ranap.anamnesis,penilaian_medis_ranap.hubungan,penilaian_medis_ranap.keluhan_utama,penilaian_medis_ranap.rps,penilaian_medis_ranap.rpk,penilaian_medis_ranap.rpd,penilaian_medis_ranap.rpo,penilaian_medis_ranap.alergi,"+
                "penilaian_medis_ranap.keadaan,penilaian_medis_ranap.gcs,penilaian_medis_ranap.kesadaran,penilaian_medis_ranap.td,penilaian_medis_ranap.nadi,penilaian_medis_ranap.rr,penilaian_medis_ranap.suhu,penilaian_medis_ranap.spo,penilaian_medis_ranap.bb,penilaian_medis_ranap.tb,"+
                "penilaian_medis_ranap.kepala,penilaian_medis_ranap.mata,penilaian_medis_ranap.gigi,penilaian_medis_ranap.tht,penilaian_medis_ranap.thoraks,penilaian_medis_ranap.jantung,penilaian_medis_ranap.paru,penilaian_medis_ranap.abdomen,penilaian_medis_ranap.ekstremitas,"+
                "penilaian_medis_ranap.genital,penilaian_medis_ranap.kulit,penilaian_medis_ranap.ket_fisik,penilaian_medis_ranap.ket_lokalis,penilaian_medis_ranap.lab,penilaian_medis_ranap.rad,penilaian_medis_ranap.penunjang,penilaian_medis_ranap.diagnosis,penilaian_medis_ranap.tata,"+
                "penilaian_medis_ranap.edukasi,dokter.nm_dokter "+
                "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                "inner join penilaian_medis_ranap on reg_periksa.no_rawat=penilaian_medis_ranap.no_rawat "+
                "inner join dokter on penilaian_medis_ranap.kd_dokter=dokter.kd_dokter where penilaian_medis_ranap.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
        }
    }//GEN-LAST:event_MnPenilaianKK3ActionPerformed

    private void TglLamaAkhirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglLamaAkhirKeyPressed
        Valid.pindah(evt,TCari,TCari);
    }//GEN-LAST:event_TglLamaAkhirKeyPressed

    private void NoPesertaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoPesertaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoPesertaKeyPressed

    private void JnsDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JnsDokterKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JnsDokterKeyPressed

    private void MitraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MitraKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MitraKeyPressed

    private void DiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiagnosaKeyPressed

    private void DdSembuhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DdSembuhKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DdSembuhKeyPressed

    private void AnatomisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AnatomisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AnatomisKeyPressed

    private void DdAnatomisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DdAnatomisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DdAnatomisKeyPressed

    private void DdFungsiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DdFungsiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DdFungsiKeyPressed

    private void TerbilangFungsiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TerbilangFungsiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TerbilangFungsiKeyPressed

    private void FungsiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FungsiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FungsiKeyPressed

    private void BesarFungsiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BesarFungsiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BesarFungsiKeyPressed

    private void DdProthesaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DdProthesaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DdProthesaKeyPressed

    private void ProthesaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProthesaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProthesaKeyPressed

    private void DdOrthesaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DdOrthesaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DdOrthesaKeyPressed

    private void OrthesaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OrthesaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_OrthesaKeyPressed

    private void DdMeninggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DdMeninggalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DdMeninggalKeyPressed

    private void TglPemeriksaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglPemeriksaanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglPemeriksaanKeyPressed

    private void DdSetelahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DdSetelahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DdSetelahKeyPressed

    private void SetelahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SetelahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SetelahKeyPressed

    private void TglKecelakaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglKecelakaanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglKecelakaanKeyPressed

    private void TglLamaAwalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglLamaAwalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglLamaAwalKeyPressed

    private void TglIstirahatAkhirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglIstirahatAkhirKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglIstirahatAkhirKeyPressed

    private void TglIstirahatAwalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglIstirahatAwalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglIstirahatAwalKeyPressed

    private void MeninggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MeninggalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MeninggalKeyPressed

    private void Meninggal1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Meninggal1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Meninggal1KeyPressed

    private void DdSetelahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DdSetelahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DdSetelahActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPenilaianAwalMedisRanapDewasa dialog = new RMPenilaianAwalMedisRanapDewasa(new javax.swing.JFrame(), true);
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
    private widget.TextArea Anamnesa;
    private widget.TextBox Anatomis;
    private widget.TextBox BesarFungsi;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.ComboBox DdAnatomis;
    private widget.ComboBox DdFungsi;
    private widget.ComboBox DdKomplikasi;
    private widget.ComboBox DdMeninggal;
    private widget.ComboBox DdOrthesa;
    private widget.ComboBox DdProthesa;
    private widget.ComboBox DdSembuh;
    private widget.ComboBox DdSetelah;
    private widget.TextBox Diagnosa;
    private widget.TextArea Fisik;
    private widget.PanelBiasa FormInput;
    private widget.TextBox Fungsi;
    private widget.TextBox Jk;
    private widget.ComboBox JnsDokter;
    private widget.TextBox KdDokter;
    private widget.TextArea Keterangan;
    private widget.TextBox Komplikasi;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private widget.TextBox Meninggal;
    private widget.TextBox Meninggal1;
    private widget.TextBox Mitra;
    private javax.swing.JMenuItem MnPenilaianKK3;
    private widget.TextBox NmDokter;
    private widget.TextBox NoPeserta;
    private widget.TextBox Orthesa;
    private usu.widget.glass.PanelGlass PanelWall;
    private widget.TextBox Prothesa;
    private widget.ScrollPane Scroll;
    private widget.TextBox Setelah;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextArea Tatalaksana;
    private widget.TextBox TerbilangFungsi;
    private widget.Tanggal TglIstirahatAkhir;
    private widget.Tanggal TglIstirahatAwal;
    private widget.Tanggal TglKecelakaan;
    private widget.TextBox TglLahir;
    private widget.Tanggal TglLamaAkhir;
    private widget.Tanggal TglLamaAwal;
    private widget.Tanggal TglPemeriksaan;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel11;
    private widget.Label jLabel14;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel27;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel43;
    private widget.Label jLabel48;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel61;
    private widget.Label jLabel62;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel65;
    private widget.Label jLabel66;
    private widget.Label jLabel67;
    private widget.Label jLabel68;
    private widget.Label jLabel69;
    private widget.Label jLabel7;
    private widget.Label jLabel70;
    private widget.Label jLabel71;
    private widget.Label jLabel72;
    private widget.Label jLabel73;
    private widget.Label jLabel74;
    private widget.Label jLabel75;
    private widget.Label jLabel76;
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel8;
    private widget.Label jLabel80;
    private widget.Label jLabel81;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private widget.Label label11;
    private widget.Label label14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane8;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().trim().equals("")){
                ps = koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,"+
                    "if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,"+
    "       penilaian_kk3.tgl_periksa, " +
    "       penilaian_kk3.kd_dokter, " +
    "       penilaian_kk3.jns_dokter, " +
    "       penilaian_kk3.no_peserta, " +
    "       penilaian_kk3.mitra, " +
    "       penilaian_kk3.tgl_kecelakaan, " +
    "       penilaian_kk3.anamnesa, " +
    "       penilaian_kk3.fisik, " +
    "       penilaian_kk3.tatalaksana, " +
    "       penilaian_kk3.diagnose, " +
    "       penilaian_kk3.komplikasi_pilih, " +
    "       penilaian_kk3.komplikasi, " +
    "       penilaian_kk3.sembuh, " +
    "       penilaian_kk3.anatomis_pilih, " +
    "       penilaian_kk3.anatomis, " +
    "       penilaian_kk3.fungsi_pilih, " +
    "       penilaian_kk3.fungsi, " +
    "       penilaian_kk3.fungsi_besar, " +
    "       penilaian_kk3.fungsi_terbilang, " +
    "       penilaian_kk3.prothesa_pilih, " +
    "       penilaian_kk3.prothesa, " +
    "       penilaian_kk3.orthesa_pilih, " +
    "       penilaian_kk3.orthesa, " +
    "       penilaian_kk3.meninggal_pilih, " +
    "       penilaian_kk3.meninggal, " +
    "       penilaian_kk3.setelah_pilih, " +
    "       penilaian_kk3.setelah, " +
    "       penilaian_kk3.lama_awal, " +
    "       penilaian_kk3.lama_akhir, " +
    "       penilaian_kk3.istirahat_awal, " +
    "       penilaian_kk3.istirahat_akhir, " +
    "       penilaian_kk3.keterangan, " +
    "       dokter.nm_dokter " +
    "FROM reg_periksa " +
    "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis " +
    "INNER JOIN penilaian_kk3 ON reg_periksa.no_rawat = penilaian_kk3.no_rawat " +
    "INNER JOIN dokter ON penilaian_kk3.kd_dokter = dokter.kd_dokter " +
    "WHERE penilaian_kk3.tgl_periksa BETWEEN ? AND ? " +
    "ORDER BY penilaian_kk3.tgl_periksa"
);

            }else{
                ps = koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,"+
                    "if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,"+
    "penilaian_kk3.tgl_periksa, penilaian_kk3.kd_dokter, " +
    "penilaian_kk3.jns_dokter, penilaian_kk3.no_peserta,penilaian_kk3.mitra, penilaian_kk3.tgl_kecelakaan, " +
    "penilaian_kk3.anamnesa, penilaian_kk3.fisik, penilaian_kk3.tatalaksana, " +
    "penilaian_kk3.diagnose, penilaian_kk3.komplikasi_pilih, penilaian_kk3.komplikasi, " +
    "penilaian_kk3.sembuh, penilaian_kk3.anatomis_pilih, penilaian_kk3.anatomis, " +
    "penilaian_kk3.fungsi_pilih, penilaian_kk3.fungsi, penilaian_kk3.fungsi_besar, " +
    "penilaian_kk3.fungsi_terbilang, penilaian_kk3.prothesa_pilih, penilaian_kk3.prothesa, " +
    "penilaian_kk3.orthesa_pilih, penilaian_kk3.orthesa, penilaian_kk3.meninggal_pilih, " +
    "penilaian_kk3.meninggal, penilaian_kk3.setelah_pilih, penilaian_kk3.setelah, " +
    "penilaian_kk3.lama_awal, penilaian_kk3.lama_akhir, penilaian_kk3.istirahat_awal, " +
    "penilaian_kk3.istirahat_akhir, penilaian_kk3.keterangan, dokter.nm_dokter " +
    "FROM reg_periksa " +
    "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis " +
    "INNER JOIN penilaian_kk3 ON reg_periksa.no_rawat = penilaian_kk3.no_rawat " +
    "INNER JOIN dokter ON penilaian_kk3.kd_dokter = dokter.kd_dokter " +
    "WHERE penilaian_kk3.tgl_periksa BETWEEN ? AND ? " +
    "AND (reg_periksa.no_rawat LIKE ? OR pasien.no_rkm_medis LIKE ? " +
    "OR pasien.nm_pasien LIKE ? OR penilaian_kk3.kd_dokter LIKE ? " +
    "OR dokter.nm_dokter LIKE ?) " +
    "ORDER BY penilaian_kk3.tgl_periksa"
);

            }
                
            try {
                if(TCari.getText().trim().equals("")){
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
                }   
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getDate("tgl_lahir"),
                        rs.getString("jk"),
                        rs.getString("kd_dokter"),
                        rs.getString("nm_dokter"),
                        rs.getString("jns_dokter"),
                        rs.getString("tgl_periksa"),
                        rs.getString("no_peserta"),
                        rs.getString("mitra"),
                        rs.getString("tgl_kecelakaan"),
                        rs.getString("anamnesa"),
                        rs.getString("fisik"),
                        rs.getString("tatalaksana"),
                        rs.getString("diagnose"),
                        rs.getString("komplikasi_pilih"),
                        rs.getString("komplikasi"),
                        rs.getString("sembuh"),
                        rs.getString("anatomis_pilih"),
                        rs.getString("anatomis"),
                        rs.getString("fungsi_pilih"),
                        rs.getString("fungsi"),
                        rs.getString("fungsi_besar"),
                        rs.getString("fungsi_terbilang"),
                        rs.getString("prothesa_pilih"),
                        rs.getString("prothesa"),
                        rs.getString("orthesa_pilih"),
                        rs.getString("orthesa"),
                        rs.getString("meninggal_pilih"),
                        rs.getString("meninggal"),
                        rs.getString("setelah_pilih"),
                        rs.getString("setelah"),
                        rs.getString("lama_awal"),
                        rs.getString("lama_akhir"),
                        rs.getString("istirahat_awal"),
                        rs.getString("istirahat_akhir"),
                        rs.getString("keterangan")
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
            
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabMode.getRowCount());
    }

    public void emptTeks() {
    // identitas pasien
//    TNoRw.setText("");
//    TNoRM.setText("");
//    TPasien.setText("");
//    TglLahir.setText("");
//    Jk.setText("");
//
//    // dokter
//    KdDokter.setText("");
//    NmDokter.setText("");
    JnsDokter.setSelectedIndex(0);

    // combo hasil pemeriksaan
    DdKomplikasi.setSelectedIndex(0);
    DdOrthesa.setSelectedIndex(0);
    DdProthesa.setSelectedIndex(0);
    DdSetelah.setSelectedIndex(0);
    DdSembuh.setSelectedIndex(0);
    DdAnatomis.setSelectedIndex(0);
    DdFungsi.setSelectedIndex(0);

    // teks/angka
    Anatomis.setText("");
    Fungsi.setText("");
    TerbilangFungsi.setText("");
    BesarFungsi.setText("");
    Orthesa.setText("");
    Prothesa.setText("");
    Setelah.setText("");
    Meninggal.setText("");

    // textarea
    Fisik.setText("");
    Tatalaksana.setText("");
    Anamnesa.setText("");
    Komplikasi.setText("");
    Keterangan.setText("");
    Diagnosa.setText("");

    // tanggal
    TglPemeriksaan.setDate(new Date());
//    TglMeninggal.setDate(new Date());
    TglIstirahatAwal.setDate(new Date());
    TglIstirahatAkhir.setDate(new Date());
    TglLamaAwal.setDate(new Date());
    TglLamaAkhir.setDate(new Date());
    TglKecelakaan.setDate(new Date());

    // tab kembali ke awal
    TabRawat.setSelectedIndex(0);

    // fokus awal
    DdKomplikasi.requestFocus();
}


    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());   // no_rawat
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());   // no_rkm_medis
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()); // nama pasien
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString()); // tgl_lahir
            Jk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());      // jenis kelamin
            KdDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString()); // kd_dokter
            NmDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()); // nama dokter
            JnsDokter.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString()); // jns_dokter
            Valid.SetTgl2(TglPemeriksaan, tbObat.getValueAt(tbObat.getSelectedRow(),8).toString()); 
            NoPeserta.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            Mitra.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString()); // nama dokter
            Valid.SetTgl2(TglKecelakaan, tbObat.getValueAt(tbObat.getSelectedRow(),11).toString()); 
            Anamnesa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            Fisik.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            Tatalaksana.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            Diagnosa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            DdKomplikasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString()); 
            Komplikasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            DdSembuh.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            DdAnatomis.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            Anatomis.setText(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
            DdFungsi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            Fungsi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            BesarFungsi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
            TerbilangFungsi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());
            DdProthesa.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
            Prothesa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
            DdOrthesa.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
            Orthesa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
            DdMeninggal.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
            Meninggal.setText(tbObat.getValueAt(tbObat.getSelectedRow(),30).toString());
            DdSetelah.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
            Setelah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
            Valid.SetTgl2(TglLamaAwal, tbObat.getValueAt(tbObat.getSelectedRow(),33).toString()); 
            Valid.SetTgl2(TglLamaAkhir, tbObat.getValueAt(tbObat.getSelectedRow(),34).toString()); 
            Valid.SetTgl2(TglIstirahatAwal, tbObat.getValueAt(tbObat.getSelectedRow(),35).toString()); 
            Valid.SetTgl2(TglIstirahatAkhir, tbObat.getValueAt(tbObat.getSelectedRow(),36).toString()); 
            Keterangan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),37).toString());

        }
    }

    private void isRawat() {
        try {
            ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rkm_medis,pasien.nm_pasien, if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,reg_periksa.tgl_registrasi "+
                    "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "where reg_periksa.no_rawat=?");
            try {
                ps.setString(1,TNoRw.getText());
                rs=ps.executeQuery();
                if(rs.next()){
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    Jk.setText(rs.getString("jk"));
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
 
    public void setNoRm(String norwt,Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(tgl2);    
        isRawat(); 
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getdatakk3_ranap());
        BtnHapus.setEnabled(akses.getdatakk3_ranap());
        BtnEdit.setEnabled(akses.getdatakk3_ranap());
        if(akses.getjml2()>=1){
            KdDokter.setEditable(false);
            BtnDokter.setEnabled(false);
            KdDokter.setText(akses.getkode());
            NmDokter.setText(dokter.tampil3(KdDokter.getText()));
            if(NmDokter.getText().equals("")){
                KdDokter.setText("");
                JOptionPane.showMessageDialog(null,"User login bukan Dokter...!!");
            }
        }            
    }
    
    public void setTampil(){
       TabRawat.setSelectedIndex(1);
    }

    private void hapus() {
        if(Sequel.queryu2tf("delete from penilaian_kk3 where no_rawat=?",1,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        })==true){
            tabMode.removeRow(tbObat.getSelectedRow());
            LCount.setText(""+tabMode.getRowCount());
            TabRawat.setSelectedIndex(1);
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }

    private void ganti() {
        if(Sequel.mengedittf("penilaian_kk3","no_rawat=?",
    "no_rawat=?,kd_dokter=?,jns_dokter=?,tgl_periksa=?,no_peserta=?,mitra=?,tgl_kecelakaan=?,anamnesa=?,fisik=?,tatalaksana=?,diagnose=?," +
    "komplikasi_pilih=?,komplikasi=?,sembuh=?,anatomis_pilih=?,anatomis=?,fungsi_pilih=?,fungsi=?,fungsi_besar=?," +
    "fungsi_terbilang=?,prothesa_pilih=?,prothesa=?,orthesa_pilih=?,orthesa=?,meninggal_pilih=?,meninggal=?,setelah_pilih=?," +
    "setelah=?,lama_awal=?,lama_akhir=?,istirahat_awal=?,istirahat_akhir=?,keterangan=?",
    34,new String[]{                   
        TNoRw.getText() ,
        KdDokter.getText(),                      // kd_dokter
        JnsDokter.getSelectedItem().toString(),  // jns_dokter
        Valid.SetTgl(TglPemeriksaan.getSelectedItem()+"")+" "+TglPemeriksaan.getSelectedItem().toString().substring(11,19), // tgl_periksa
        NoPeserta.getText(),                     // no_peserta
        Mitra.getText(),                         // mitra
        Valid.SetTgl(TglKecelakaan.getSelectedItem()+""), // tgl_kecelakaan
        Anamnesa.getText(),                      // anamnesa
        Fisik.getText(),                         // fisik
        Tatalaksana.getText(),                   // tatalaksana
        Diagnosa.getText(),                      // diagnosa
        DdKomplikasi.getSelectedItem().toString(), // komplikasi_pilih
        Komplikasi.getText(),                    // komplikasi
        DdSembuh.getSelectedItem().toString(),   // sembuh
        DdAnatomis.getSelectedItem().toString(), // anatomis_pilih
        Anatomis.getText(),                      // anatomis
        DdFungsi.getSelectedItem().toString(),   // fungsi_pilih
        Fungsi.getText(),                        // fungsi
        BesarFungsi.getText(),                   // besar_fungsi
        TerbilangFungsi.getText(),               // terbilang_fungsi
        DdProthesa.getSelectedItem().toString(), // prothesa_pilih
        Prothesa.getText(),                      // prothesa
        DdOrthesa.getSelectedItem().toString(),  // orthesa_pilih
        Orthesa.getText(),                       // orthesa
        DdMeninggal.getSelectedItem().toString(),// meninggal_pilih
        Meninggal.getText(),                  
        DdSetelah.getSelectedItem().toString(),  // setelah_pilih
        Setelah.getText(),                       // setelah
        Valid.SetTgl(TglLamaAwal.getSelectedItem()+""), // lama_awal
        Valid.SetTgl(TglLamaAkhir.getSelectedItem()+""), // lama_akhir
        Valid.SetTgl(TglIstirahatAwal.getSelectedItem()+""), // istirahat_awal
        Valid.SetTgl(TglIstirahatAkhir.getSelectedItem()+""), // istirahat_akhir
        Keterangan.getText(), 
        tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()                         
    })==true){
        tampil();
        emptTeks();
        TabRawat.setSelectedIndex(1);


}

    }
}
