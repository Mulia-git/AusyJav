/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rekammedis;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.nio.file.Files;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import kepegawaian.DlgCariDokter;
import laporan.DlgBerkasRawat;
import laporan.DlgDiagnosaPenyakit;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import java.net.URI;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Base64;
import org.apache.hc.core5.http.io.entity.StringEntity;


/**
 *
 * @author perpustakaan
 */
public final class RMDataKK3Ralan extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps,ps2;
    private ResultSet rs,rs2;
    private int i=0;    
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private String kodekamar="",namakamar="",tglkeluar="",jamkeluar="",finger="",json;
    private ObjectMapper mapper= new ObjectMapper();
    private JsonNode root;
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMDataKK3Ralan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        WindowURLSertisign.setSize(570,100);
        WindowPhrase.setSize(320,100);
        
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.RM","Nama Pasien","Kode Dokter","Nama Dokter","Jenis Dokter","Tanggal Pemeriksaan","No Peserta",
            "Tempat Kerja","Tanggal Kecelakaan","Anamnesa","Pemeriksaan Fisik","Penatalaksanaan","Diagnosis","Komplikasi","Keterangan","Sembuh","Cacat Anatomis","Keterangan",
            "Cacat Fungsi","Keterangan","Besar","Terbilang","Prothesa","Keterangan","Orthesa","Keterangan","Meninggal","Jam Meninggal","Keterangan",
            "Setelah Sembuh","Keterangan","Lama Perawatan Awal","Lama Perawatan Akhir","Istirahat Awal","Istirahat Akhir", "Keterangan Lainnya"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 37; i++) {
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
                column.setPreferredWidth(200);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        Anamnesa.setDocument(new batasInput((int)2000).getKata(Anamnesa));
        Fisik.setDocument(new batasInput((int)2000).getKata(Fisik));
        Tatalaksana.setDocument(new batasInput((int)2000).getKata(Tatalaksana));
        Tatalaksana.setDocument(new batasInput((int)2000).getKata(Tatalaksana));
        DiagnosaUtama.setDocument(new batasInput((int)1000).getKata(DiagnosaUtama));
//        KodeDiagnosaUtama.setDocument(new batasInput((int)10).getKata(KodeDiagnosaUtama));
        Mitra.setDocument(new batasInput((int)100).getKata(Mitra));
        IstirahatPulang.setDocument(new batasInput((int)50).getKata(IstirahatPulang));
        IstirahatMasuk.setDocument(new batasInput((int)50).getKata(IstirahatMasuk));
        
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
        
//        dokter.addWindowListener(new WindowListener() {
//            @Override
//            public void windowOpened(WindowEvent e) {}
//            @Override
//            public void windowClosing(WindowEvent e) {}
//            @Override
//            public void windowClosed(WindowEvent e) {
//                if(dokter.getTable().getSelectedRow()!= -1){
//                    KodeDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
//                    NamaDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
//                    KodeDokter.requestFocus();
//                }
//            }
//            @Override
//            public void windowIconified(WindowEvent e) {}
//            @Override
//            public void windowDeiconified(WindowEvent e) {}
//            @Override
//            public void windowActivated(WindowEvent e) {}
//            @Override
//            public void windowDeactivated(WindowEvent e) {}
//        });
        
        ChkInput.setSelected(false);
        isForm();
      
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
        MnInputDiagnosa = new javax.swing.JMenuItem();
        WindowURLSertisign = new javax.swing.JDialog();
        internalFrame9 = new widget.InternalFrame();
        jLabel43 = new widget.Label();
        panelisi6 = new widget.panelisi();
        BtnCloseUrl = new widget.Button();
        BtnBukaURL = new widget.Button();
        jLabel44 = new widget.Label();
        URLSertisign = new widget.TextBox();
        BtnDownloadFile = new widget.Button();
        BtnDownloadBukaFile = new widget.Button();
        WindowPhrase = new javax.swing.JDialog();
        internalFrame8 = new widget.InternalFrame();
        jLabel45 = new widget.Label();
        panelisi5 = new widget.panelisi();
        BtnClosePhrase = new widget.Button();
        BtnSimpanTandaTangan = new widget.Button();
        jLabel46 = new widget.Label();
        Phrase = new widget.TextBox();
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
        TNoRM = new widget.TextBox();
        DiagnosaUtama = new widget.TextBox();
        jLabel27 = new widget.Label();
        scrollPane2 = new widget.ScrollPane();
        Anamnesa = new widget.TextArea();
        jLabel5 = new widget.Label();
        jLabel8 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        Fisik = new widget.TextArea();
        jLabel29 = new widget.Label();
        label14 = new widget.Label();
        BtnDokter1 = new widget.Button();
        BtnDokter5 = new widget.Button();
        jLabel16 = new widget.Label();
        Masuk = new widget.TextBox();
        KodeDokterPengirim = new widget.TextBox();
        NamaDokterPengirim = new widget.TextBox();
        Mitra = new widget.TextBox();
        IstirahatMasuk = new widget.TextBox();
        jLabel40 = new widget.Label();
        jLabel41 = new widget.Label();
        IstirahatPulang = new widget.TextBox();
        jLabel11 = new widget.Label();
        scrollPane6 = new widget.ScrollPane();
        Tatalaksana = new widget.TextArea();
        BtnDokter6 = new widget.Button();
        JnsDokter = new widget.ComboBox();
        NoPeserta = new widget.TextBox();
        TglKecelakaan = new widget.Tanggal();
        PanelWall = new usu.widget.glass.PanelGlass();
        Komplikasi = new widget.TextBox();
        DdKomplikasi = new widget.ComboBox();
        jSeparator12 = new javax.swing.JSeparator();
        scrollPane9 = new widget.ScrollPane();
        Keterangan = new widget.TextArea();
        jLabel79 = new widget.Label();
        jLabel100 = new widget.Label();
        jLabel14 = new widget.Label();
        jLabel30 = new widget.Label();
        jLabel31 = new widget.Label();
        jLabel47 = new widget.Label();
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
        jLabel57 = new widget.Label();
        jLabel58 = new widget.Label();
        jLabel59 = new widget.Label();
        jLabel60 = new widget.Label();
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
        jLabel74 = new widget.Label();
        jLabel76 = new widget.Label();
        jLabel77 = new widget.Label();
        jLabel78 = new widget.Label();
        Meninggal = new widget.TextBox();
        JamMeninggal = new widget.TextBox();
        jLabel80 = new widget.Label();
        jLabel81 = new widget.Label();
        LamaMasuk = new widget.TextBox();
        LamaPulang = new widget.TextBox();
        BtnDokter7 = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnInputDiagnosa.setBackground(new java.awt.Color(255, 255, 254));
        MnInputDiagnosa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInputDiagnosa.setForeground(new java.awt.Color(50, 50, 50));
        MnInputDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnInputDiagnosa.setText("Input Diagnosa Pasien");
        MnInputDiagnosa.setName("MnInputDiagnosa"); // NOI18N
        MnInputDiagnosa.setPreferredSize(new java.awt.Dimension(250, 26));
        MnInputDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInputDiagnosaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnInputDiagnosa);

        WindowURLSertisign.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowURLSertisign.setModal(true);
        WindowURLSertisign.setName("WindowURLSertisign"); // NOI18N
        WindowURLSertisign.setUndecorated(true);
        WindowURLSertisign.setResizable(false);

        internalFrame9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ URL File Hasil Tanda Tangan Sertisign ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setLayout(new java.awt.BorderLayout());

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel43.setText("%");
        jLabel43.setName("jLabel43"); // NOI18N
        internalFrame9.add(jLabel43, java.awt.BorderLayout.CENTER);

        panelisi6.setName("panelisi6"); // NOI18N
        panelisi6.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi6.setLayout(null);

        BtnCloseUrl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseUrl.setMnemonic('T');
        BtnCloseUrl.setText("Tutup");
        BtnCloseUrl.setToolTipText("Alt+T");
        BtnCloseUrl.setName("BtnCloseUrl"); // NOI18N
        BtnCloseUrl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseUrlActionPerformed(evt);
            }
        });
        panelisi6.add(BtnCloseUrl);
        BtnCloseUrl.setBounds(450, 40, 100, 30);

        BtnBukaURL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnBukaURL.setMnemonic('B');
        BtnBukaURL.setText("Buka URL");
        BtnBukaURL.setToolTipText("Alt+B");
        BtnBukaURL.setName("BtnBukaURL"); // NOI18N
        BtnBukaURL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBukaURLActionPerformed(evt);
            }
        });
        panelisi6.add(BtnBukaURL);
        BtnBukaURL.setBounds(10, 40, 105, 30);

        jLabel44.setText("URL :");
        jLabel44.setName("jLabel44"); // NOI18N
        panelisi6.add(jLabel44);
        jLabel44.setBounds(0, 10, 40, 23);

        URLSertisign.setEditable(false);
        URLSertisign.setHighlighter(null);
        URLSertisign.setName("URLSertisign"); // NOI18N
        panelisi6.add(URLSertisign);
        URLSertisign.setBounds(44, 10, 505, 23);

        BtnDownloadFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnDownloadFile.setMnemonic('D');
        BtnDownloadFile.setText("Download File");
        BtnDownloadFile.setToolTipText("Alt+D");
        BtnDownloadFile.setName("BtnDownloadFile"); // NOI18N
        BtnDownloadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDownloadFileActionPerformed(evt);
            }
        });
        panelisi6.add(BtnDownloadFile);
        BtnDownloadFile.setBounds(125, 40, 130, 30);

        BtnDownloadBukaFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/preview-16x16.png"))); // NOI18N
        BtnDownloadBukaFile.setMnemonic('F');
        BtnDownloadBukaFile.setText("Download & Buka File");
        BtnDownloadBukaFile.setToolTipText("Alt+F");
        BtnDownloadBukaFile.setName("BtnDownloadBukaFile"); // NOI18N
        BtnDownloadBukaFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDownloadBukaFileActionPerformed(evt);
            }
        });
        panelisi6.add(BtnDownloadBukaFile);
        BtnDownloadBukaFile.setBounds(265, 40, 175, 30);

        internalFrame9.add(panelisi6, java.awt.BorderLayout.CENTER);

        WindowURLSertisign.getContentPane().add(internalFrame9, java.awt.BorderLayout.CENTER);

        WindowPhrase.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowPhrase.setModal(true);
        WindowPhrase.setName("WindowPhrase"); // NOI18N
        WindowPhrase.setUndecorated(true);
        WindowPhrase.setResizable(false);

        internalFrame8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ E-Sign / Tanda Tangan Elektronik ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setLayout(new java.awt.BorderLayout());

        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel45.setText("%");
        jLabel45.setName("jLabel45"); // NOI18N
        internalFrame8.add(jLabel45, java.awt.BorderLayout.CENTER);

        panelisi5.setName("panelisi5"); // NOI18N
        panelisi5.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi5.setLayout(null);

        BtnClosePhrase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnClosePhrase.setMnemonic('U');
        BtnClosePhrase.setText("Batal");
        BtnClosePhrase.setToolTipText("Alt+U");
        BtnClosePhrase.setName("BtnClosePhrase"); // NOI18N
        BtnClosePhrase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnClosePhraseActionPerformed(evt);
            }
        });
        panelisi5.add(BtnClosePhrase);
        BtnClosePhrase.setBounds(200, 40, 100, 30);

        BtnSimpanTandaTangan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanTandaTangan.setMnemonic('S');
        BtnSimpanTandaTangan.setText("Simpan");
        BtnSimpanTandaTangan.setToolTipText("Alt+S");
        BtnSimpanTandaTangan.setName("BtnSimpanTandaTangan"); // NOI18N
        BtnSimpanTandaTangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanTandaTanganActionPerformed(evt);
            }
        });
        panelisi5.add(BtnSimpanTandaTangan);
        BtnSimpanTandaTangan.setBounds(10, 40, 100, 30);

        jLabel46.setText("Masukkan Passphrase :");
        jLabel46.setName("jLabel46"); // NOI18N
        panelisi5.add(jLabel46);
        jLabel46.setBounds(0, 10, 130, 23);

        Phrase.setHighlighter(null);
        Phrase.setName("Phrase"); // NOI18N
        panelisi5.add(Phrase);
        Phrase.setBounds(134, 10, 160, 23);

        internalFrame8.add(panelisi5, java.awt.BorderLayout.CENTER);

        WindowPhrase.getContentPane().add(internalFrame8, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data KK3 Pasien Rawat Jalan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat.setAutoCreateRowSorter(true);
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

        jLabel19.setText("Tgl.Rawat :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(67, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-09-2025" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "08-09-2025" }));
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
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 448));
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

        FormInput.setBackground(new java.awt.Color(250, 255, 245));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 1172));
        FormInput.setLayout(null);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Berdasarkan Anamnesa :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(390, 80, 120, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(104, 10, 141, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        FormInput.add(TPasien);
        TPasien.setBounds(361, 10, 424, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        FormInput.add(TNoRM);
        TNoRM.setBounds(247, 10, 112, 23);

        DiagnosaUtama.setHighlighter(null);
        DiagnosaUtama.setName("DiagnosaUtama"); // NOI18N
        DiagnosaUtama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaUtamaKeyPressed(evt);
            }
        });
        FormInput.add(DiagnosaUtama);
        DiagnosaUtama.setBounds(140, 480, 650, 23);

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("Diagnosa Utama :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(15, 480, 90, 23);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        Anamnesa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Anamnesa.setColumns(20);
        Anamnesa.setRows(5);
        Anamnesa.setName("Anamnesa"); // NOI18N
        Anamnesa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AnamnesaKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(Anamnesa);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(390, 110, 390, 110);

        jLabel5.setText("No.Rawat :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 10, 100, 23);

        jLabel8.setText("Pemeriksaan Fisik :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(280, 230, 100, 23);

        scrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane3.setName("scrollPane3"); // NOI18N

        Fisik.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Fisik.setColumns(20);
        Fisik.setRows(5);
        Fisik.setName("Fisik"); // NOI18N
        Fisik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FisikKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(Fisik);

        FormInput.add(scrollPane3);
        scrollPane3.setBounds(390, 230, 390, 110);

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("Tanggal Kecelakaan :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(20, 190, 120, 23);

        label14.setText("Dokter P.J. :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(0, 40, 100, 23);

        BtnDokter1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter1.setMnemonic('2');
        BtnDokter1.setToolTipText("Alt+2");
        BtnDokter1.setName("BtnDokter1"); // NOI18N
        BtnDokter1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter1ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter1);
        BtnDokter1.setBounds(510, 80, 28, 23);

        BtnDokter5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter5.setMnemonic('2');
        BtnDokter5.setToolTipText("Alt+2");
        BtnDokter5.setName("BtnDokter5"); // NOI18N
        BtnDokter5.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter5ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter5);
        BtnDokter5.setBounds(350, 250, 28, 23);

        jLabel16.setText("Tanggal Perawatan  :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(580, 40, 110, 23);

        Masuk.setEditable(false);
        Masuk.setHighlighter(null);
        Masuk.setName("Masuk"); // NOI18N
        FormInput.add(Masuk);
        Masuk.setBounds(690, 40, 90, 23);

        KodeDokterPengirim.setEditable(false);
        KodeDokterPengirim.setName("KodeDokterPengirim"); // NOI18N
        KodeDokterPengirim.setPreferredSize(new java.awt.Dimension(80, 23));
        KodeDokterPengirim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeDokterPengirimKeyPressed(evt);
            }
        });
        FormInput.add(KodeDokterPengirim);
        KodeDokterPengirim.setBounds(100, 40, 120, 23);

        NamaDokterPengirim.setEditable(false);
        NamaDokterPengirim.setName("NamaDokterPengirim"); // NOI18N
        NamaDokterPengirim.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NamaDokterPengirim);
        NamaDokterPengirim.setBounds(220, 40, 220, 23);

        Mitra.setHighlighter(null);
        Mitra.setName("Mitra"); // NOI18N
        Mitra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MitraKeyPressed(evt);
            }
        });
        FormInput.add(Mitra);
        Mitra.setBounds(20, 160, 360, 23);

        IstirahatMasuk.setHighlighter(null);
        IstirahatMasuk.setName("IstirahatMasuk"); // NOI18N
        IstirahatMasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IstirahatMasukKeyPressed(evt);
            }
        });
        FormInput.add(IstirahatMasuk);
        IstirahatMasuk.setBounds(520, 880, 120, 23);

        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel40.setText("No Peserta BPJS Ketenagakerjaan  :");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(20, 80, 230, 23);

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel41.setText("Nama Pemberi Kerja / Wadah / Mitra / Proyek Jasa Konstruksi :");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(20, 140, 310, 23);

        IstirahatPulang.setHighlighter(null);
        IstirahatPulang.setName("IstirahatPulang"); // NOI18N
        IstirahatPulang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IstirahatPulangKeyPressed(evt);
            }
        });
        FormInput.add(IstirahatPulang);
        IstirahatPulang.setBounds(690, 880, 100, 23);

        jLabel11.setText("Penatalaksanaan :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(280, 350, 100, 23);

        scrollPane6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane6.setName("scrollPane6"); // NOI18N

        Tatalaksana.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tatalaksana.setColumns(20);
        Tatalaksana.setRows(5);
        Tatalaksana.setName("Tatalaksana"); // NOI18N
        Tatalaksana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TatalaksanaKeyPressed(evt);
            }
        });
        scrollPane6.setViewportView(Tatalaksana);

        FormInput.add(scrollPane6);
        scrollPane6.setBounds(390, 350, 390, 110);

        BtnDokter6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter6.setMnemonic('2');
        BtnDokter6.setToolTipText("Alt+2");
        BtnDokter6.setName("BtnDokter6"); // NOI18N
        BtnDokter6.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter6ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter6);
        BtnDokter6.setBounds(350, 370, 28, 23);

        JnsDokter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dokter Pemeriksa", "Dokter Penasehat" }));
        JnsDokter.setName("JnsDokter"); // NOI18N
        JnsDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JnsDokterKeyPressed(evt);
            }
        });
        FormInput.add(JnsDokter);
        JnsDokter.setBounds(450, 40, 128, 23);

        NoPeserta.setHighlighter(null);
        NoPeserta.setName("NoPeserta"); // NOI18N
        NoPeserta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoPesertaKeyPressed(evt);
            }
        });
        FormInput.add(NoPeserta);
        NoPeserta.setBounds(20, 110, 360, 23);

        TglKecelakaan.setDisplayFormat("dd-MM-yyyy");
        TglKecelakaan.setName("TglKecelakaan"); // NOI18N
        TglKecelakaan.setPreferredSize(new java.awt.Dimension(95, 23));
        TglKecelakaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglKecelakaanKeyPressed(evt);
            }
        });
        FormInput.add(TglKecelakaan);
        TglKecelakaan.setBounds(130, 190, 250, 23);

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/rekammedis/kk3.PNG"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.setLayout(null);
        FormInput.add(PanelWall);
        PanelWall.setBounds(20, 230, 270, 230);

        Komplikasi.setFocusTraversalPolicyProvider(true);
        Komplikasi.setName("Komplikasi"); // NOI18N
        Komplikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KomplikasiKeyPressed(evt);
            }
        });
        FormInput.add(Komplikasi);
        Komplikasi.setBounds(170, 510, 620, 23);

        DdKomplikasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        DdKomplikasi.setName("DdKomplikasi"); // NOI18N
        DdKomplikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DdKomplikasiKeyPressed(evt);
            }
        });
        FormInput.add(DdKomplikasi);
        DdKomplikasi.setBounds(90, 510, 70, 23);

        jSeparator12.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator12.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator12.setName("jSeparator12"); // NOI18N
        FormInput.add(jSeparator12);
        jSeparator12.setBounds(-10, 550, 880, 1);

        scrollPane9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane9.setName("scrollPane9"); // NOI18N

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
        scrollPane9.setViewportView(Keterangan);

        FormInput.add(scrollPane9);
        scrollPane9.setBounds(20, 930, 810, 83);

        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel79.setText("Keterangan Lainnya Jika Perlu :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(20, 910, 260, 23);

        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel100.setText("HASIL PEMERIKSAAN ATAU PENGOBATAN");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(20, 550, 810, 23);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Kehilangan Anggota Badan");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(20, 640, 140, 23);

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("Komplikasi :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(20, 510, 70, 23);

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Sembuh Tanpa Cacat");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(20, 580, 140, 23);

        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("Cacat Anatomis Akibat");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(20, 620, 140, 23);

        DdSembuh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        DdSembuh.setName("DdSembuh"); // NOI18N
        DdSembuh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DdSembuhKeyPressed(evt);
            }
        });
        FormInput.add(DdSembuh);
        DdSembuh.setBounds(180, 580, 70, 23);

        Anatomis.setFocusTraversalPolicyProvider(true);
        Anatomis.setName("Anatomis"); // NOI18N
        Anatomis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AnatomisKeyPressed(evt);
            }
        });
        FormInput.add(Anatomis);
        Anatomis.setBounds(260, 630, 530, 23);

        DdAnatomis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        DdAnatomis.setName("DdAnatomis"); // NOI18N
        DdAnatomis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DdAnatomisKeyPressed(evt);
            }
        });
        FormInput.add(DdAnatomis);
        DdAnatomis.setBounds(180, 630, 70, 23);

        jLabel48.setText("Terbilang");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(430, 700, 60, 23);

        DdFungsi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        DdFungsi.setName("DdFungsi"); // NOI18N
        DdFungsi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DdFungsiKeyPressed(evt);
            }
        });
        FormInput.add(DdFungsi);
        DdFungsi.setBounds(180, 670, 70, 23);

        TerbilangFungsi.setFocusTraversalPolicyProvider(true);
        TerbilangFungsi.setName("TerbilangFungsi"); // NOI18N
        TerbilangFungsi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TerbilangFungsiKeyPressed(evt);
            }
        });
        FormInput.add(TerbilangFungsi);
        TerbilangFungsi.setBounds(490, 700, 300, 23);

        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel54.setText("Cacat Fungsi Anggota Badan");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(20, 680, 150, 23);

        Fungsi.setFocusTraversalPolicyProvider(true);
        Fungsi.setName("Fungsi"); // NOI18N
        Fungsi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FungsiKeyPressed(evt);
            }
        });
        FormInput.add(Fungsi);
        Fungsi.setBounds(260, 670, 530, 23);

        BesarFungsi.setFocusTraversalPolicyProvider(true);
        BesarFungsi.setName("BesarFungsi"); // NOI18N
        BesarFungsi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BesarFungsiKeyPressed(evt);
            }
        });
        FormInput.add(BesarFungsi);
        BesarFungsi.setBounds(340, 700, 70, 23);

        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel55.setText("Dengan Besarnya Cacat Fungsi");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(180, 700, 160, 23);

        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel57.setText("%");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(410, 700, 20, 23);

        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel58.setText(" : ");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(160, 680, 20, 23);

        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel59.setText(" : ");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(160, 630, 20, 23);

        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel60.setText(" : ");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(160, 580, 20, 23);

        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel62.setText(" : ");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(160, 730, 20, 23);

        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel63.setText("Memerlukan Prothesa ");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(20, 730, 150, 23);

        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel64.setText(" : ");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(160, 760, 20, 23);

        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel65.setText("Memerlukan Orthesa");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(20, 760, 150, 23);

        DdProthesa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        DdProthesa.setName("DdProthesa"); // NOI18N
        DdProthesa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DdProthesaKeyPressed(evt);
            }
        });
        FormInput.add(DdProthesa);
        DdProthesa.setBounds(180, 730, 70, 23);

        Prothesa.setFocusTraversalPolicyProvider(true);
        Prothesa.setName("Prothesa"); // NOI18N
        Prothesa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProthesaKeyPressed(evt);
            }
        });
        FormInput.add(Prothesa);
        Prothesa.setBounds(260, 730, 530, 23);

        DdOrthesa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        DdOrthesa.setName("DdOrthesa"); // NOI18N
        DdOrthesa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DdOrthesaKeyPressed(evt);
            }
        });
        FormInput.add(DdOrthesa);
        DdOrthesa.setBounds(180, 760, 70, 23);

        Orthesa.setFocusTraversalPolicyProvider(true);
        Orthesa.setName("Orthesa"); // NOI18N
        Orthesa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                OrthesaKeyPressed(evt);
            }
        });
        FormInput.add(Orthesa);
        Orthesa.setBounds(260, 760, 530, 23);

        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel66.setText(" : ");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(160, 790, 20, 23);

        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel67.setText("Sampai");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(240, 880, 50, 23);

        DdMeninggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        DdMeninggal.setName("DdMeninggal"); // NOI18N
        DdMeninggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DdMeninggalKeyPressed(evt);
            }
        });
        FormInput.add(DdMeninggal);
        DdMeninggal.setBounds(180, 790, 70, 23);

        jSeparator13.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator13.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator13.setName("jSeparator13"); // NOI18N
        FormInput.add(jSeparator13);
        jSeparator13.setBounds(-10, 820, 880, 1);

        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel68.setText(" : ");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(160, 840, 20, 23);

        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel69.setText("Dapat Melakukan Pekerjaan");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(20, 850, 150, 23);

        DdSetelah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Biasa Dengan Kondisi", "Ringan Dengan Kondisi", "TIdak Dapat Bekerja" }));
        DdSetelah.setName("DdSetelah"); // NOI18N
        DdSetelah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DdSetelahKeyPressed(evt);
            }
        });
        FormInput.add(DdSetelah);
        DdSetelah.setBounds(180, 840, 140, 23);

        Setelah.setFocusTraversalPolicyProvider(true);
        Setelah.setName("Setelah"); // NOI18N
        Setelah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SetelahKeyPressed(evt);
            }
        });
        FormInput.add(Setelah);
        Setelah.setBounds(370, 840, 420, 23);

        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel70.setText("Setelah Sembuh Peserta");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(20, 830, 150, 23);

        jLabel71.setText("Jam");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(630, 790, 30, 23);

        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel72.setText("Meninggal Dunia");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(20, 790, 150, 23);

        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel73.setText(" : ");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(110, 880, 20, 23);

        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel75.setText("Lama Perawatan");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(20, 880, 90, 23);

        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel74.setText("Berupa");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(330, 840, 50, 23);

        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel76.setText("Sampai");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(640, 880, 50, 23);

        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel77.setText(" : ");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(510, 880, 20, 23);

        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel78.setText("Diberikan Istirahat");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(420, 880, 90, 23);

        Meninggal.setFocusTraversalPolicyProvider(true);
        Meninggal.setName("Meninggal"); // NOI18N
        Meninggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MeninggalKeyPressed(evt);
            }
        });
        FormInput.add(Meninggal);
        Meninggal.setBounds(310, 790, 320, 23);

        JamMeninggal.setFocusTraversalPolicyProvider(true);
        JamMeninggal.setName("JamMeninggal"); // NOI18N
        JamMeninggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JamMeninggalKeyPressed(evt);
            }
        });
        FormInput.add(JamMeninggal);
        JamMeninggal.setBounds(670, 790, 120, 23);

        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel80.setText("Meninggal Dunia");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(20, 790, 150, 23);

        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel81.setText("Tanggal");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(260, 790, 50, 23);

        LamaMasuk.setHighlighter(null);
        LamaMasuk.setName("LamaMasuk"); // NOI18N
        LamaMasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LamaMasukKeyPressed(evt);
            }
        });
        FormInput.add(LamaMasuk);
        LamaMasuk.setBounds(120, 880, 120, 23);

        LamaPulang.setHighlighter(null);
        LamaPulang.setName("LamaPulang"); // NOI18N
        LamaPulang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LamaPulangKeyPressed(evt);
            }
        });
        FormInput.add(LamaPulang);
        LamaPulang.setBounds(290, 880, 120, 23);

        BtnDokter7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter7.setMnemonic('2');
        BtnDokter7.setToolTipText("Alt+2");
        BtnDokter7.setName("BtnDokter7"); // NOI18N
        BtnDokter7.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter7ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter7);
        BtnDokter7.setBounds(110, 480, 28, 23);

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
            Valid.pindah(evt,TCari,JnsDokter);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        Valid.pindah(evt,TCari,BtnSimpan);
}//GEN-LAST:event_TPasienKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TNoRw.getText().equals("")||TNoRM.getText().equals("")||TPasien.getText().equals("")){
            Valid.textKosong(TNoRw,"Pasien");
        }else if(Anamnesa.getText().equals("")){
            Valid.textKosong(Anamnesa,"Keluhan utama riwayat penyakit yang postif");
        }else if(Tatalaksana.getText().equals("")){
            Valid.textKosong(Tatalaksana,"Jalannya penyakit selama perawatan");
        }else if(DiagnosaUtama.getText().equals("")){
            Valid.textKosong(DiagnosaUtama,"Diagnosa Utama");
        }else{
           if(Sequel.menyimpantf("datakk3_ralan","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat",34,new String[]{
                TNoRw.getText(), // no_rawat
                KodeDokterPengirim.getText(), // kd_dokter
                JnsDokter.getSelectedItem().toString(), // jns_dokter
                Masuk.getText(), // jns_dokter
                NoPeserta.getText(), // no_peserta
                Mitra.getText(), // mitra
                Valid.SetTgl(TglKecelakaan.getSelectedItem()+""), // tgl_kecelakaan
                Anamnesa.getText(), // anamnesa
                Fisik.getText(), // fisik
                Tatalaksana.getText(), // tatalaksana
                DiagnosaUtama.getText(), // diagnose
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
                JamMeninggal.getText(), // meninggal (jam)
                DdSetelah.getSelectedItem().toString(), // setelah_pilih
                Setelah.getText(), // setelah
                LamaMasuk.getText(),  // lama_awal
                LamaPulang.getText(),  // lama_akhir
                IstirahatMasuk.getText(),  // istirahat_awal
                IstirahatPulang.getText(),  // istirahat_akhir
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
            Valid.pindah(evt,Keterangan,BtnBatal);
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
                hapus();
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
        if(TNoRw.getText().equals("")||TNoRM.getText().equals("")||TPasien.getText().equals("")){
            Valid.textKosong(TNoRw,"Pasien");
        }else if(Anamnesa.getText().equals("")){
            Valid.textKosong(Anamnesa,"Keluhan utama riwayat penyakit yang postif");
        }else if(Tatalaksana.getText().equals("")){
            Valid.textKosong(Tatalaksana,"Jalannya penyakit selama perawatan");
        }else if(DiagnosaUtama.getText().equals("")){
            Valid.textKosong(DiagnosaUtama,"Diagnosa Utama");
        }else{
            if(tbObat.getSelectedRow()>-1){
                    ganti();
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
        dokter.dispose();
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnKeluarActionPerformed(null);
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(! TCari.getText().trim().equals("")){
            BtnCariActionPerformed(evt);
        }
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
            Valid.MyReportqry("rptDataResumePasienRanap.jasper","report","::[ Data Resume Pasien ]::",
                    "select reg_periksa.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,resume_pasien_ralan.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter as kodepengirim,pengirim.nm_dokter as pengirim,"+
                    "reg_periksa.tgl_registrasi,reg_periksa.jam_reg,resume_pasien_ralan.diagnosa_awal,resume_pasien_ralan.alasan,resume_pasien_ralan.keluhan_utama,resume_pasien_ralan.pemeriksaan_fisik,"+
                    "resume_pasien_ralan.jalannya_penyakit,resume_pasien_ralan.pemeriksaan_penunjang,resume_pasien_ralan.hasil_laborat,resume_pasien_ralan.tindakan_dan_operasi,resume_pasien_ralan.obat_di_rs,"+
                    "resume_pasien_ralan.diagnosa_utama,resume_pasien_ralan.kd_diagnosa_utama,resume_pasien_ralan.diagnosa_sekunder,resume_pasien_ralan.kd_diagnosa_sekunder,resume_pasien_ralan.diagnosa_sekunder2,"+
                    "resume_pasien_ralan.kd_diagnosa_sekunder2,resume_pasien_ralan.diagnosa_sekunder3,resume_pasien_ralan.kd_diagnosa_sekunder3,resume_pasien_ralan.diagnosa_sekunder4,"+
                    "resume_pasien_ralan.kd_diagnosa_sekunder4,resume_pasien_ralan.prosedur_utama,resume_pasien_ralan.kd_prosedur_utama,resume_pasien_ralan.prosedur_sekunder,resume_pasien_ralan.kd_prosedur_sekunder,"+
                    "resume_pasien_ralan.prosedur_sekunder2,resume_pasien_ralan.kd_prosedur_sekunder2,resume_pasien_ralan.prosedur_sekunder3,resume_pasien_ralan.kd_prosedur_sekunder3,resume_pasien_ralan.alergi,"+
                    "resume_pasien_ralan.diet,resume_pasien_ralan.lab_belum,resume_pasien_ralan.edukasi,resume_pasien_ralan.cara_keluar,resume_pasien_ralan.ket_keluar,resume_pasien_ralan.keadaan,"+
                    "resume_pasien_ralan.ket_keadaan,resume_pasien_ralan.dilanjutkan,resume_pasien_ralan.ket_dilanjutkan,resume_pasien_ralan.kontrol,resume_pasien_ralan.obat_pulang "+
                    "from resume_pasien_ralan inner join reg_periksa on resume_pasien_ralan.no_rawat=reg_periksa.no_rawat inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join dokter on resume_pasien_ralan.kd_dokter=dokter.kd_dokter inner join dokter as pengirim on reg_periksa.kd_dokter=pengirim.kd_dokter "+
                    "where reg_periksa.tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' "+
                    (TCari.getText().trim().equals("")?"":"and (reg_periksa.no_rkm_medis like '%"+TCari.getText().trim()+"%' or pasien.nm_pasien like '%"+TCari.getText().trim()+"%' or "+
                    "resume_pasien_ralan.kd_dokter like '%"+TCari.getText().trim()+"%' or dokter.nm_dokter like '%"+TCari.getText().trim()+"%' or resume_pasien_ralan.keadaan like '%"+TCari.getText().trim()+"%' or "+
                    "resume_pasien_ralan.kd_diagnosa_utama like '%"+TCari.getText().trim()+"%' or resume_pasien_ralan.diagnosa_utama like '%"+TCari.getText().trim()+"%' or "+
                    "resume_pasien_ralan.prosedur_utama like '%"+TCari.getText().trim()+"%' or reg_periksa.no_rawat like '%"+TCari.getText().trim()+"%' or "+
                    "resume_pasien_ralan.kd_prosedur_utama like '%"+TCari.getText().trim()+"%')")+"order by reg_periksa.tgl_registrasi,reg_periksa.status_lanjut",param);
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
            tampil();
            TCari.setText("");
        }else{
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
        // Valid.pindah(evt, TNm, BtnSimpan);
}//GEN-LAST:event_TNoRMKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
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
                    ChkInput.setSelected(true);
                    isForm(); 
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void DiagnosaUtamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaUtamaKeyPressed
       Valid.pindah(evt,DiagnosaUtama,Tatalaksana);
    }//GEN-LAST:event_DiagnosaUtamaKeyPressed

    private void AnamnesaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AnamnesaKeyPressed
        Valid.pindah2(evt,TglKecelakaan,Fisik);
    }//GEN-LAST:event_AnamnesaKeyPressed

    private void FisikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FisikKeyPressed
        Valid.pindah2(evt,Anamnesa,Tatalaksana);
    }//GEN-LAST:event_FisikKeyPressed

    private void BtnDokter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter1ActionPerformed
        if(TNoRw.getText().equals("")&&TNoRM.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Pasien masih kosong...!!!");
        }else{
            RMCariKeluhan carikeluhan=new RMCariKeluhan(null,false);
            carikeluhan.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {}
                @Override
                public void windowClosing(WindowEvent e) {}
                @Override
                public void windowClosed(WindowEvent e) {
                    if(carikeluhan.getTable().getSelectedRow()!= -1){
                        Anamnesa.append(carikeluhan.getTable().getValueAt(carikeluhan.getTable().getSelectedRow(),2).toString()+", ");
                        Anamnesa.requestFocus();
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
            carikeluhan.setNoRawat(TNoRw.getText());
            carikeluhan.tampil();
            carikeluhan.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            carikeluhan.setLocationRelativeTo(internalFrame1);
            carikeluhan.setVisible(true);
        }
    }//GEN-LAST:event_BtnDokter1ActionPerformed

    private void MnInputDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInputDiagnosaActionPerformed
        if(TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        }else{
            DlgDiagnosaPenyakit penyakit=new DlgDiagnosaPenyakit(null,false);
            penyakit.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {}
                @Override
                public void windowClosing(WindowEvent e) {}
                @Override
                public void windowClosed(WindowEvent e) {
                    tampil();
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
            penyakit.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            penyakit.setLocationRelativeTo(internalFrame1);
            penyakit.isCek();
            penyakit.setNoRm(TNoRw.getText(),DTPCari1.getDate(),DTPCari2.getDate(),Sequel.cariIsi("select reg_periksa.status_lanjut from reg_periksa where reg_periksa.no_rawat=?",TNoRw.getText()));
            penyakit.panelDiagnosa1.tampil();
            penyakit.setVisible(true);
        }
    }//GEN-LAST:event_MnInputDiagnosaActionPerformed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void BtnDokter5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter5ActionPerformed
        if(TNoRw.getText().equals("")&&TNoRM.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Pasien masih kosong...!!!");
        }else{
            RMCariPemeriksaan caripemeriksaan=new RMCariPemeriksaan(null,false);
            caripemeriksaan.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {}
                @Override
                public void windowClosing(WindowEvent e) {}
                @Override
                public void windowClosed(WindowEvent e) {
                    if(caripemeriksaan.getTable().getSelectedRow()!= -1){
                        Fisik.append(caripemeriksaan.getTable().getValueAt(caripemeriksaan.getTable().getSelectedRow(),2).toString()+", ");
                        Fisik.requestFocus();
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
            caripemeriksaan.setNoRawat(TNoRw.getText());
            caripemeriksaan.tampil();
            caripemeriksaan.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            caripemeriksaan.setLocationRelativeTo(internalFrame1);
            caripemeriksaan.setVisible(true);
        } 
    }//GEN-LAST:event_BtnDokter5ActionPerformed

    private void KodeDokterPengirimKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeDokterPengirimKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KodeDokterPengirimKeyPressed

    private void TatalaksanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TatalaksanaKeyPressed
        Valid.pindah2(evt,Fisik,Tatalaksana);
    }//GEN-LAST:event_TatalaksanaKeyPressed

    private void MitraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MitraKeyPressed
        Valid.pindah(evt,NoPeserta,TglKecelakaan);
    }//GEN-LAST:event_MitraKeyPressed

    private void IstirahatPulangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IstirahatPulangKeyPressed
        Valid.pindah(evt,IstirahatMasuk,Keterangan);
    }//GEN-LAST:event_IstirahatPulangKeyPressed

    private void IstirahatMasukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IstirahatMasukKeyPressed
        Valid.pindah(evt,LamaPulang,IstirahatPulang);
    }//GEN-LAST:event_IstirahatMasukKeyPressed

    private void BtnDokter6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter6ActionPerformed
        if(TNoRw.getText().equals("")&&TNoRM.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Pasien masih kosong...!!!");
        }else{
            RMCariTindakan caritindakan=new RMCariTindakan(null,false);
            caritindakan.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {}
                @Override
                public void windowClosing(WindowEvent e) {}
                @Override
                public void windowClosed(WindowEvent e) {
                    if(caritindakan.getTable().getSelectedRow()!= -1){
                        Tatalaksana.append(caritindakan.getTable().getValueAt(caritindakan.getTable().getSelectedRow(),2).toString()+", ");
                        Tatalaksana.requestFocus();
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
            caritindakan.setNoRawat(TNoRw.getText());
            caritindakan.tampil();
            caritindakan.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            caritindakan.setLocationRelativeTo(internalFrame1);
            caritindakan.setVisible(true);
        }
    }//GEN-LAST:event_BtnDokter6ActionPerformed

    private void BtnCloseUrlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseUrlActionPerformed
        URLSertisign.setText("");
        WindowURLSertisign.dispose();
    }//GEN-LAST:event_BtnCloseUrlActionPerformed

    private void BtnBukaURLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBukaURLActionPerformed
        try {
            Desktop.getDesktop().browse(new URI(URLSertisign.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane,"File belum tersedia, silahkan tunggu beberapa saat lagi..!!");
            System.out.println("Notifikasi : " + e);
        }
    }//GEN-LAST:event_BtnBukaURLActionPerformed

    private void BtnDownloadFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDownloadFileActionPerformed
        try {
            URL url = new URL(URLSertisign.getText());
            ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream("Resume"+TNoRw.getText().trim().replaceAll("/","")+".pdf");
            fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            fileOutputStream.close();
            readableByteChannel.close();
            System.out.println("Download Selesai : " + "Resume"+TNoRw.getText().trim().replaceAll("/","")+".pdf");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(rootPane,"File belum tersedia, silahkan tunggu & ulangi beberapa saat lagi..!!");
            System.out.println("Notifikasi : " + e);
        }
    }//GEN-LAST:event_BtnDownloadFileActionPerformed

    private void BtnDownloadBukaFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDownloadBukaFileActionPerformed
        try {
            URL url = new URL(URLSertisign.getText());
            ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream("Resume"+TNoRw.getText().trim().replaceAll("/","")+".pdf");
            fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            fileOutputStream.close();
            readableByteChannel.close();
            System.out.println("Download Selesai : " + "Resume"+TNoRw.getText().trim().replaceAll("/","")+".pdf");
            Desktop.getDesktop().browse(new File("Resume"+TNoRw.getText().trim().replaceAll("/","")+".pdf").toURI());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(rootPane,"File belum tersedia, silahkan tunggu & ulangi beberapa saat lagi..!!");
            System.out.println("Notifikasi : " + e);
        }
    }//GEN-LAST:event_BtnDownloadBukaFileActionPerformed

    private void BtnClosePhraseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnClosePhraseActionPerformed
        Phrase.setText("");
        WindowPhrase.dispose();
    }//GEN-LAST:event_BtnClosePhraseActionPerformed

    private void BtnSimpanTandaTanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanTandaTanganActionPerformed
        if(Phrase.getText().equals("")){
            Valid.textKosong(Phrase,"Phrase");
        }else{
            if(tbObat.getSelectedRow()>-1){
                Map<String, Object> param = new HashMap<>();    
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());   
                param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                param.put("norawat",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                param.put("finger","#"); 
                try {
                    ps=koneksi.prepareStatement("select dpjp_ralan.kd_dokter,dokter.nm_dokter from dpjp_ralan inner join dokter on dpjp_ralan.kd_dokter=dokter.kd_dokter where dpjp_ralan.no_rawat=? and dpjp_ralan.kd_dokter<>?");
                    try {
                        ps.setString(1,tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                        ps.setString(2,tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
                        rs=ps.executeQuery();
                        i=2;
                        while(rs.next()){
                           if(i==2){
                               finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",rs.getString("kd_dokter"));
                               param.put("finger2","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+rs.getString("nm_dokter")+"\nID "+(finger.equals("")?rs.getString("kd_dokter"):finger)+"\n"+Valid.SetTgl3(IstirahatMasuk.getText()));
                               param.put("namadokter2",rs.getString("nm_dokter")); 
                           }
                           if(i==3){
                               finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",rs.getString("kd_dokter"));
                               param.put("finger3","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+rs.getString("nm_dokter")+"\nID "+(finger.equals("")?rs.getString("kd_dokter"):finger)+"\n"+Valid.SetTgl3(IstirahatMasuk.getText()));
                               param.put("namadokter3",rs.getString("nm_dokter")); 
                           }
                           i++;
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
                param.put("tanggalkeluar",Valid.SetTgl3(IstirahatMasuk.getText()));
                Valid.MyReportPDF2("rptLaporanResumeRanap2.jasper","report","::[ Laporan Resume Pasien ]::",param);
                File f = new File("./report/rptLaporanResumeRanap2.pdf");  
                try {
                    CloseableHttpClient httpClient = HttpClients.createDefault();
                    HttpPost post = new HttpPost(koneksiDB.URLAKSESFILEESIGN());
                    post.setHeader("Content-Type", "application/json");
                    post.addHeader("username", koneksiDB.USERNAMEAPIESIGN());
                    post.addHeader("password", koneksiDB.PASSAPIESIGN());
                    post.addHeader("url", koneksiDB.URLAPIESIGN());
                    
                    byte[] fileContent = Files.readAllBytes(f.toPath());
                    
                    json="{" +
                             "\"file\":\""+Base64.getEncoder().encodeToString(fileContent)+"\"," +
                             "\"nik\":\""+Sequel.cariIsi("select pegawai.no_ktp from pegawai where pegawai.nik=?", akses.getkode())+"\"," +
                             "\"passphrase\":\""+Phrase.getText()+"\"," +
                             "\"tampilan\":\"visible\"," +
                             "\"image\":\"false\"," +
                             "\"linkQR\":\"Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+" dan ditandatangani secara elektronik oleh "+NamaDokterPengirim.getText()+" ID "+KodeDokterPengirim.getText()+" Tanggal "+Valid.SetTgl3(IstirahatMasuk.getText())+"\"," +
                             "\"width\":\"55\"," +
                             "\"height\":\"55\"," +
                             "\"tag_koordinat\":\"#\"" +
                          "}";
                    
                    System.out.println("URL Akses file :"+koneksiDB.URLAKSESFILEESIGN());
                    System.out.println("JSON Dikirim :"+json);
                    post.setEntity(new StringEntity(json));
                    try (CloseableHttpResponse response = httpClient.execute(post)) {
                        System.out.println("Response Status : " + response.getCode());
                        json=EntityUtils.toString(response.getEntity());
                        root = mapper.readTree(json);
                        if (response.getCode() == 200) {
                            try (FileOutputStream fos = new FileOutputStream(new File("Resume"+TNoRw.getText().trim().replaceAll("/","")+"_"+TNoRM.getText().trim().replaceAll(" ","")+"_"+TPasien.getText().trim().replaceAll(" ","")+".pdf"))) {
                                byte[] fileBytes = Base64.getDecoder().decode(root.path("response").asText());
                                fos.write(fileBytes);
                                WindowPhrase.dispose();
                                JOptionPane.showMessageDialog(null,"Proses tanda tangan berhasil...");
                                Desktop.getDesktop().browse(new File("Resume"+TNoRw.getText().trim().replaceAll("/","")+"_"+TNoRM.getText().trim().replaceAll(" ","")+"_"+TPasien.getText().trim().replaceAll(" ","")+".pdf").toURI());
                            } catch (Exception e) {
                                WindowPhrase.dispose();
                                JOptionPane.showMessageDialog(null,"Gagal mengkonversi base64 ke file...");
                                System.out.println("Notif : " +e);
                            }
                        } else {
                            WindowPhrase.dispose();
                            JOptionPane.showMessageDialog(null,"Code : "+root.path("metadata").path("code").asText()+" Pesan : "+root.path("metadata").path("message").asText());
                        }
                    } catch (IOException a) {
                        System.out.println("Notifikasi : " + a);
                        WindowPhrase.dispose();
                        JOptionPane.showMessageDialog(null,""+a);
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                    WindowPhrase.dispose();
                    JOptionPane.showMessageDialog(null,""+e);
                }
            }
        }
    }//GEN-LAST:event_BtnSimpanTandaTanganActionPerformed

    private void JnsDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JnsDokterKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JnsDokterKeyPressed

    private void NoPesertaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoPesertaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoPesertaKeyPressed

    private void TglKecelakaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglKecelakaanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglKecelakaanKeyPressed

    private void KomplikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KomplikasiKeyPressed
        Valid.pindah(evt,Anamnesa,DdKomplikasi);
    }//GEN-LAST:event_KomplikasiKeyPressed

    private void DdKomplikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DdKomplikasiKeyPressed
        Valid.pindah(evt,Meninggal,DdKomplikasi);
    }//GEN-LAST:event_DdKomplikasiKeyPressed

    private void KeteranganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganKeyPressed
        Valid.pindah2(evt,DdKomplikasi,DdKomplikasi);
    }//GEN-LAST:event_KeteranganKeyPressed

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

    private void DdSetelahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DdSetelahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DdSetelahKeyPressed

    private void SetelahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SetelahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SetelahKeyPressed

    private void MeninggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MeninggalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MeninggalKeyPressed

    private void JamMeninggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JamMeninggalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JamMeninggalKeyPressed

    private void LamaMasukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LamaMasukKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LamaMasukKeyPressed

    private void LamaPulangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LamaPulangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LamaPulangKeyPressed

    private void BtnDokter7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter7ActionPerformed
        if(TNoRw.getText().equals("")&&TNoRM.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Pasien masih kosong...!!!");
        }else{
            RMCariDiagnosa diagnosa=new RMCariDiagnosa(null,false);
            diagnosa.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {}
                @Override
                public void windowClosing(WindowEvent e) {}
                @Override
                public void windowClosed(WindowEvent e) {
                    if(diagnosa.getTable().getSelectedRow()!= -1){
                        DiagnosaUtama.setText(DiagnosaUtama.getText() + 
                        diagnosa.getTable().getValueAt(diagnosa.getTable().getSelectedRow(),0).toString()+" - "+
                        diagnosa.getTable().getValueAt(diagnosa.getTable().getSelectedRow(),1).toString()+", ");
                        DiagnosaUtama.requestFocus();
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
            diagnosa.setNoRawat(TNoRw.getText());
            diagnosa.tampil();
            diagnosa.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            diagnosa.setLocationRelativeTo(internalFrame1);
            diagnosa.setVisible(true);// TODO add your handling code here:
        }
    }//GEN-LAST:event_BtnDokter7ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMDataKK3Ralan dialog = new RMDataKK3Ralan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBukaURL;
    private widget.Button BtnCari;
    private widget.Button BtnClosePhrase;
    private widget.Button BtnCloseUrl;
    private widget.Button BtnDokter1;
    private widget.Button BtnDokter5;
    private widget.Button BtnDokter6;
    private widget.Button BtnDokter7;
    private widget.Button BtnDownloadBukaFile;
    private widget.Button BtnDownloadFile;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpanTandaTangan;
    private widget.CekBox ChkInput;
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
    private widget.TextBox DiagnosaUtama;
    private widget.TextArea Fisik;
    private widget.PanelBiasa FormInput;
    private widget.TextBox Fungsi;
    private widget.TextBox IstirahatMasuk;
    private widget.TextBox IstirahatPulang;
    private widget.TextBox JamMeninggal;
    private widget.ComboBox JnsDokter;
    private widget.TextArea Keterangan;
    private widget.TextBox KodeDokterPengirim;
    private widget.TextBox Komplikasi;
    private widget.Label LCount;
    private widget.TextBox LamaMasuk;
    private widget.TextBox LamaPulang;
    private widget.TextBox Masuk;
    private widget.TextBox Meninggal;
    private widget.TextBox Mitra;
    private javax.swing.JMenuItem MnInputDiagnosa;
    private widget.TextBox NamaDokterPengirim;
    private widget.TextBox NoPeserta;
    private widget.TextBox Orthesa;
    private javax.swing.JPanel PanelInput;
    private usu.widget.glass.PanelGlass PanelWall;
    private widget.TextBox Phrase;
    private widget.TextBox Prothesa;
    private widget.ScrollPane Scroll;
    private widget.TextBox Setelah;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextArea Tatalaksana;
    private widget.TextBox TerbilangFungsi;
    private widget.Tanggal TglKecelakaan;
    private widget.TextBox URLSertisign;
    private javax.swing.JDialog WindowPhrase;
    private javax.swing.JDialog WindowURLSertisign;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame8;
    private widget.InternalFrame internalFrame9;
    private widget.Label jLabel100;
    private widget.Label jLabel11;
    private widget.Label jLabel14;
    private widget.Label jLabel16;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel27;
    private widget.Label jLabel29;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel4;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel5;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private widget.Label label14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi5;
    private widget.panelisi panelisi6;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane6;
    private widget.ScrollPane scrollPane9;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().trim().equals("")){
                ps = koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,"+
                    "if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,"+
                    "       datakk3_ralan.tgl_periksa, " +
                    "       datakk3_ralan.kd_dokter, " +
                    "       datakk3_ralan.jns_dokter, " +
                    "       datakk3_ralan.no_peserta, " +
                    "       datakk3_ralan.mitra, " +
                    "       datakk3_ralan.tgl_kecelakaan, " +
                    "       datakk3_ralan.anamnesa, " +
                    "       datakk3_ralan.fisik, " +
                    "       datakk3_ralan.tatalaksana, " +
                    "       datakk3_ralan.diagnose, " +
                    "       datakk3_ralan.komplikasi_pilih, " +
                    "       datakk3_ralan.komplikasi, " +
                    "       datakk3_ralan.sembuh, " +
                    "       datakk3_ralan.anatomis_pilih, " +
                    "       datakk3_ralan.anatomis, " +
                    "       datakk3_ralan.fungsi_pilih, " +
                    "       datakk3_ralan.fungsi, " +
                    "       datakk3_ralan.fungsi_besar, " +
                    "       datakk3_ralan.fungsi_terbilang, " +
                    "       datakk3_ralan.prothesa_pilih, " +
                    "       datakk3_ralan.prothesa, " +
                    "       datakk3_ralan.orthesa_pilih, " +
                    "       datakk3_ralan.orthesa, " +
                    "       datakk3_ralan.meninggal_pilih, " +
                    "       datakk3_ralan.meninggal, " +
                    "       datakk3_ralan.meninggal_jam, " +
                    "       datakk3_ralan.setelah_pilih, " +
                    "       datakk3_ralan.setelah, " +
                    "       datakk3_ralan.lama_awal, " +
                    "       datakk3_ralan.lama_akhir, " +
                    "       datakk3_ralan.istirahat_awal, " +
                    "       datakk3_ralan.istirahat_akhir, " +
                    "       datakk3_ralan.keterangan, " +
                    "       dokter.nm_dokter " +
                    "FROM reg_periksa " +
                    "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis " +
                    "INNER JOIN datakk3_ralan ON reg_periksa.no_rawat = datakk3_ralan.no_rawat " +
                    "INNER JOIN dokter ON datakk3_ralan.kd_dokter = dokter.kd_dokter " +
                    "WHERE datakk3_ralan.tgl_periksa = ? " +
                    "ORDER BY datakk3_ralan.tgl_periksa"
                );
            }else{
                ps = koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,"+
                    "datakk3_ralan.tgl_periksa, datakk3_ralan.kd_dokter, " +
                    "datakk3_ralan.jns_dokter, datakk3_ralan.no_peserta,datakk3_ralan.mitra, datakk3_ralan.tgl_kecelakaan, " +
                    "datakk3_ralan.anamnesa, datakk3_ralan.fisik, datakk3_ralan.tatalaksana, " +
                    "datakk3_ralan.diagnose, datakk3_ralan.komplikasi_pilih, datakk3_ralan.komplikasi, " +
                    "datakk3_ralan.sembuh, datakk3_ralan.anatomis_pilih, datakk3_ralan.anatomis, " +
                    "datakk3_ralan.fungsi_pilih, datakk3_ralan.fungsi, datakk3_ralan.fungsi_besar, " +
                    "datakk3_ralan.fungsi_terbilang, datakk3_ralan.prothesa_pilih, datakk3_ralan.prothesa, " +
                    "datakk3_ralan.orthesa_pilih, datakk3_ralan.orthesa, datakk3_ralan.meninggal_pilih, " +
                    "datakk3_ralan.meninggal, datakk3_ralan.meninggal_jam, datakk3_ralan.setelah_pilih, datakk3_ralan.setelah, " +
                    "datakk3_ralan.lama_awal, datakk3_ralan.lama_akhir, datakk3_ralan.istirahat_awal, " +
                    "datakk3_ralan.istirahat_akhir, datakk3_ralan.keterangan, dokter.nm_dokter " +
                    "FROM reg_periksa " +
                    "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis " +
                    "INNER JOIN datakk3_ralan ON reg_periksa.no_rawat = datakk3_ralan.no_rawat " +
                    "INNER JOIN dokter ON datakk3_ralan.kd_dokter = dokter.kd_dokter " +
                    "WHERE datakk3_ralan.tgl_periksa = ? " +
                    "AND (reg_periksa.no_rawat LIKE ? OR pasien.no_rkm_medis LIKE ? " +
                    "OR pasien.nm_pasien LIKE ? OR datakk3_ralan.kd_dokter LIKE ? " +
                    "OR dokter.nm_dokter LIKE ?) " +
                    "ORDER BY datakk3_ralan.tgl_periksa"
                );}
                
            try {
                if(TCari.getText().trim().equals("")){
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem()+"")); // hasilnya yyyy-MM-dd
//                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
//                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                    }else{
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem()+"")); // hasilnya yyyy-MM-dd
//                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
//                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                    ps.setString(2,"%"+TCari.getText()+"%");
                    ps.setString(3,"%"+TCari.getText()+"%");
                    ps.setString(4,"%"+TCari.getText()+"%");
                    ps.setString(5,"%"+TCari.getText()+"%");
                    ps.setString(6,"%"+TCari.getText()+"%");
                }   
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
//                        rs.getString("tgl_lahir"),
//                        rs.getString("jk"),
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
                        rs.getString("meninggal_jam"),
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
    JamMeninggal.setText("");

    // textarea
    Fisik.setText("");
    Tatalaksana.setText("");
    Anamnesa.setText("");
    Komplikasi.setText("");
    Keterangan.setText("");
//    KodeDiagnosaUtama.setText("");
    DiagnosaUtama.setText("");
    IstirahatMasuk.setText("");
    IstirahatPulang.setText("");

    // tanggal
    TglKecelakaan.setDate(new Date());

    // fokus awal
    DdKomplikasi.requestFocus();
}


     private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());   // no_rawat
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());   // no_rkm_medis
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()); // nama pasien
            KodeDokterPengirim.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString()); // kd_dokter
            NamaDokterPengirim.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString()); // nama dokter
            JnsDokter.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString()); // jns_dokter
            Masuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()); // jns_dokter
            NoPeserta.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            Mitra.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString()); // nama dokter
            Valid.SetTgl2(TglKecelakaan, tbObat.getValueAt(tbObat.getSelectedRow(),9).toString()); 
            Anamnesa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            Fisik.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            Tatalaksana.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            DiagnosaUtama.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            DdKomplikasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString()); 
            Komplikasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            DdSembuh.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            DdAnatomis.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            Anatomis.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            DdFungsi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            Fungsi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            BesarFungsi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
            TerbilangFungsi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            DdProthesa.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            Prothesa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            DdOrthesa.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
            Orthesa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());
            DdMeninggal.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
            Meninggal.setText(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
            JamMeninggal.setText(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
            DdSetelah.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
            Setelah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
            LamaMasuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),30).toString());
            LamaPulang.setText(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
            IstirahatMasuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
            IstirahatPulang.setText(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());
            Keterangan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),34).toString());

        }
    }

    private void isRawat() {
        try {
            ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rkm_medis,concat(pasien.nm_pasien,' (',reg_periksa.umurdaftar,' ',reg_periksa.sttsumur,')') as pasien,"
                            + "reg_periksa.tgl_registrasi,reg_periksa.kd_dokter,dokter.nm_dokter "
                            + "from reg_periksa "
                            + "inner join dokter on dokter.kd_dokter=reg_periksa.kd_dokter "
                            + "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.no_rawat=?");
            try {
                ps.setString(1,TNoRw.getText());
                rs=ps.executeQuery();
                if(rs.next()){
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    TPasien.setText(rs.getString("pasien"));
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    Masuk.setText(rs.getString("tgl_registrasi"));
                    LamaMasuk.setText(rs.getString("tgl_registrasi"));
                    LamaPulang.setText(rs.getString("tgl_registrasi"));
                    KodeDokterPengirim.setText(rs.getString("kd_dokter"));
                    NamaDokterPengirim.setText(rs.getString("nm_dokter"));
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
//        try {
//            ps=koneksi.prepareStatement(
//                    "select diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit,diagnosa_pasien.prioritas "+
//                    "from diagnosa_pasien inner join penyakit on diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit "+
//                    "where diagnosa_pasien.no_rawat=? and diagnosa_pasien.status='Ranap' order by diagnosa_pasien.prioritas ");
//            try {
//                ps.setString(1,norwt);
//                rs=ps.executeQuery();
//                while(rs.next()){
//                        DiagnosaUtama.setText(rs.getString("nm_penyakit"));
//                }
//            } catch (Exception e) {
//                System.out.println("Notif : "+e);
//            } finally{
//                if(rs!=null){
//                    rs.close();
//                }
//                if(ps!=null){
//                    ps.close();
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("Notif : "+e);
//        } 
        
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,this.getHeight()-122));
            scrollInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            scrollInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getdatakk3_ralan());
        BtnHapus.setEnabled(akses.getdatakk3_ralan());
        BtnEdit.setEnabled(akses.getdatakk3_ralan());
        BtnPrint.setEnabled(akses.getdatakk3_ralan()); 
        MnInputDiagnosa.setEnabled(akses.getdiagnosa_pasien());   
//        ppBerkasDigital.setEnabled(akses.getberkas_digital_perawatan());
    }

    private void ganti() {
         if(Sequel.mengedittf("datakk3_ralan","no_rawat=?",
                "no_rawat=?,kd_dokter=?,jns_dokter=?,tgl_periksa=?,no_peserta=?,mitra=?,tgl_kecelakaan=?,anamnesa=?,fisik=?,tatalaksana=?,diagnose=?," +
                "komplikasi_pilih=?,komplikasi=?,sembuh=?,anatomis_pilih=?,anatomis=?,fungsi_pilih=?,fungsi=?,fungsi_besar=?," +
                "fungsi_terbilang=?,prothesa_pilih=?,prothesa=?,orthesa_pilih=?,orthesa=?,meninggal_pilih=?,meninggal=?,meninggal_jam=?,setelah_pilih=?," +
                "setelah=?,lama_awal=?,lama_akhir=?,istirahat_awal=?,istirahat_akhir=?,keterangan=?",
                34,new String[]{                 
        TNoRw.getText() ,
        KodeDokterPengirim.getText(),                      // kd_dokter
        JnsDokter.getSelectedItem().toString(),  // jns_dokter
        Masuk.getText(),
        NoPeserta.getText(),                     // no_peserta
        Mitra.getText(),                         // mitra
        Valid.SetTgl(TglKecelakaan.getSelectedItem()+""), // tgl_kecelakaan
        Anamnesa.getText(),                      // anamnesa
        Fisik.getText(),                         // fisik
        Tatalaksana.getText(),                   // tatalaksana
        DiagnosaUtama.getText(),                      // diagnosa
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
        JamMeninggal.getText(),                  
        DdSetelah.getSelectedItem().toString(),  // setelah_pilih
        Setelah.getText(),                       // setelah  // setelah_pilih
        LamaMasuk.getText(),      // setelah_pilih
        LamaPulang.getText(),      // setelah_pilih
        IstirahatMasuk.getText(),      // setelah_pilih
        IstirahatPulang.getText(),    
        Keterangan.getText(), 
        tbObat.getValueAt(tbObat.getSelectedRow(),0).toString() 
                })==true){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());   // no_rawat
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());   // no_rkm_medis
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()); // nama pasien
            KodeDokterPengirim.setText(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString()); // kd_dokter
            NamaDokterPengirim.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()); // nama dokter
            JnsDokter.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString()); // jns_dokter
            Masuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString()); // jns_dokter
            NoPeserta.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            Mitra.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString()); // nama dokter
            Valid.SetTgl2(TglKecelakaan, tbObat.getValueAt(tbObat.getSelectedRow(),11).toString()); 
            Anamnesa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            Fisik.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            Tatalaksana.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            DiagnosaUtama.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
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
            JamMeninggal.setText(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
            DdSetelah.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
            Setelah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());
            LamaMasuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),34).toString());
            LamaPulang.setText(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString());
            IstirahatMasuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),36).toString());
            IstirahatPulang.setText(tbObat.getValueAt(tbObat.getSelectedRow(),37).toString());
            Keterangan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),38).toString());
                   emptTeks();
            }
    }

    private void hapus() {
         if(Sequel.queryu2tf("delete from datakk3_ralan where no_rawat=?",1,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        })==true){
            tabMode.removeRow(tbObat.getSelectedRow());
            LCount.setText(""+tabMode.getRowCount());
            emptTeks();
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }

    
}
