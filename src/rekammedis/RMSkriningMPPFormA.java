/*
 * Kontribusi dari Haris Rochmatullah, RS Bhayangkara Nganjuk
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
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariDokter;
import kepegawaian.DlgCariPetugas;


/**
 *
 * @author perpustakaan
 */
public final class RMSkriningMPPFormA extends javax.swing.JDialog {
    private final DefaultTableModel tabMode,tabModeMasalah,tabModeDetailMasalah,tabModeAsesmen,tabModeIdentifikasiMasalah,tabModePerencanaan;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps,ps2;
    private ResultSet rs,rs2;
    private int i=0,jml=0,index=0, pilihan=0;
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private boolean[] pilih; 
    private String[] kode,masalah;
    private String masalahidentifikasi="",finger=""; 
    private File file;
    private FileWriter fileWriter;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode response;
    private FileReader myObj;
    private Map<String, java.util.List<String>> mapOpsiAsesmen = new HashMap<>();
    private java.util.Map<String, String> mapJenisInputAsesmen = new java.util.HashMap<>();
    private Map<String, String> mapTipePerencanaan = new HashMap<>();
    /** Creates new form 
     * @param parent
     * @param modal */
    public RMSkriningMPPFormA(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        isCek();
         panelWrapper.setPreferredSize(new java.awt.Dimension(1000, 1200));
      
//     PanelAccor.setVisible(false);



        panelWrapper.revalidate();
         scrollMain.getViewport().addComponentListener(new java.awt.event.ComponentAdapter() {
        @Override
        public void componentResized(java.awt.event.ComponentEvent e) {
            java.awt.Dimension d = panelWrapper.getPreferredSize();
            d.width = scrollMain.getViewport().getWidth();
            panelWrapper.setPreferredSize(d);
            panelWrapper.revalidate();
        }
    });
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.RM","Nama Pasien","J.K.","Tgl.Lahir","Alamat","Tgl.Evaluasi","Ruang","Tgl.Masuk",
            "Kode DPJP","DPJP","Kode Konsulan 1","Dokter Konsulan 1","Kode Konsulan 2","Dokter Konsulan 2",
            "Kode Konsulan 3","Dokter Konsulan 3","Kode Konsulan 4","Dokter Konsulan 4","Assesmen","Identifikasi",
            "Rencana","NIP","Nama Petugas"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 24; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(65);
            }else if(i==2){
                column.setPreferredWidth(150);
            }else if(i==3){
                column.setPreferredWidth(65);
            }else if(i==4){
                column.setPreferredWidth(60);
            }else if(i==5){
                column.setPreferredWidth(180);
            }else if(i==6){
                column.setPreferredWidth(115);
            }else if(i==7){
                column.setPreferredWidth(150);
            }else if(i==8){
                column.setPreferredWidth(115);
            }else if(i==9){
                column.setPreferredWidth(90);
            }else if(i==10){
                column.setPreferredWidth(150);
            }else if(i==11){
                column.setPreferredWidth(90);
            }else if(i==12){
                column.setPreferredWidth(150);
            }else if(i==13){
                column.setPreferredWidth(160);
            }else if(i==14){
                column.setPreferredWidth(160);
            }else if(i==15){
                column.setPreferredWidth(160);
            }else if(i==16){
                column.setPreferredWidth(160);
            }else if(i==17){
                column.setPreferredWidth(160);
            }else if(i==18){
                column.setPreferredWidth(160);
            }else if(i==19){
                column.setPreferredWidth(80);
            }else if(i==20){
                column.setPreferredWidth(150);
            }else if(i==21){
                column.setPreferredWidth(150);
            }else if(i==22){
                column.setPreferredWidth(150);
            }else if(i==23){
                column.setPreferredWidth(150);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        

       TableColumn column; 

     
      tabModeMasalah = new DefaultTableModel(
    null,
            new Object[]{ "P", "Kode", "Identifikasi Masalah" }
        ){
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex){
                return colIndex == 0; // hanya checkbox
            }

            Class[] types = new Class[]{
                java.lang.Boolean.class, 
                java.lang.Object.class,  // KODE (hidden)
                java.lang.Object.class   // Nama masalah
            };

            @Override
            public Class getColumnClass(int columnIndex){
                return types[columnIndex];
            }
        };


        tbIdentifikasiMPP.setModel(tabModeMasalah);
        tbIdentifikasiMPP.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbIdentifikasiMPP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        
        column = tbIdentifikasiMPP.getColumnModel().getColumn(0);
        column.setPreferredWidth(20);

    
        column = tbIdentifikasiMPP.getColumnModel().getColumn(1);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setPreferredWidth(0);

       
        column = tbIdentifikasiMPP.getColumnModel().getColumn(2);
        column.setPreferredWidth(800);

        tbIdentifikasiMPP.setDefaultRenderer(Object.class, new WarnaTable());
        tbIdentifikasiMPP.getColumnModel()
        .getColumn(0)
        .setCellRenderer(tbIdentifikasiMPP.getDefaultRenderer(Boolean.class));


        tabModeAsesmen = new DefaultTableModel(
                null,
                new Object[]{ "P", "Kode", "Nama Asesmen", "Jawaban" }
            ){
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    // checkbox & jawaban yang boleh diedit
                    return columnIndex == 0 || columnIndex == 3;
                }

                Class[] types = new Class[]{
                    java.lang.Boolean.class, // P
                    java.lang.Object.class,  // Kode (hidden)
                    java.lang.Object.class,  // Nama Asesmen
                    java.lang.Object.class   // Jawaban
                };

                @Override
                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }
            };

            tbIassesmenMPP.setModel(tabModeAsesmen);
            tbIassesmenMPP.setPreferredScrollableViewportSize(new Dimension(500,500));
            tbIassesmenMPP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


         tbIassesmenMPP.getColumnModel()
            .getColumn(3)
            .setCellEditor(new EditorJawabanAsesmen());


              column = tbIassesmenMPP.getColumnModel().getColumn(0); // P
              column.setPreferredWidth(20);

              column = tbIassesmenMPP.getColumnModel().getColumn(1); // Kode (disembunyikan)
              column.setMinWidth(0);
              column.setMaxWidth(0);

              column = tbIassesmenMPP.getColumnModel().getColumn(2); // Nama Asesmen
              column.setPreferredWidth(200);

              column = tbIassesmenMPP.getColumnModel().getColumn(3); // Jawaban
              column.setPreferredWidth(150);



       tabModeDetailMasalah = new DefaultTableModel(
            null,
            new Object[]{ "Identifikasi Masalah" }
            ){
                @Override public boolean isCellEditable(int rowIndex, int colIndex){ return false; }
            };
//            tbMasalahDetailMasalah.setModel(tabModeDetailMasalah);
//            tbMasalahDetailMasalah.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//
//            column = tbMasalahDetailMasalah.getColumnModel().getColumn(0);
//            column.setPreferredWidth(420);

     tabModeIdentifikasiMasalah = new DefaultTableModel(
                null,
                new Object[]{ "P", "Kode", "Jenis Masalah", "Uraian" }
            ){
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    // hanya kolom checkbox (0) dan uraian (3) bisa diedit
                    return columnIndex == 0 || columnIndex == 3;
                }

                Class[] types = new Class[]{
                    java.lang.Boolean.class, // P (checkbox)
                    java.lang.Object.class,  // Kode (hidden)
                    java.lang.Object.class,  // Jenis
                    java.lang.Object.class   // Uraian
                };

                @Override
                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }
            };


            tbIdentifikasiMasalah.setModel(tabModeIdentifikasiMasalah);
            tbIdentifikasiMasalah.setPreferredScrollableViewportSize(new Dimension(500, 500));
            tbIdentifikasiMasalah.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


        

                // P
                column = tbIdentifikasiMasalah.getColumnModel().getColumn(0);
                column.setPreferredWidth(20);

                // Kode (hidden)
                column = tbIdentifikasiMasalah.getColumnModel().getColumn(1);
                column.setMinWidth(0);
                column.setMaxWidth(0);
                column.setPreferredWidth(0);

                // Jenis Masalah
                column = tbIdentifikasiMasalah.getColumnModel().getColumn(2);
                column.setPreferredWidth(220);

                // Uraian
                column = tbIdentifikasiMasalah.getColumnModel().getColumn(3);
                column.setPreferredWidth(300);


            tbIdentifikasiMasalah.setDefaultRenderer(Object.class, new WarnaTable());


           tabModePerencanaan = new DefaultTableModel(
            null,
            new Object[]{ "P", "Kode", "Rencana", "Keterangan" }
        ){
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                // P + Keterangan
                return columnIndex == 0 || columnIndex == 3;
            }

            Class[] types = new Class[]{
                java.lang.Boolean.class, // P
                java.lang.Object.class,  // Kode (hidden)
                java.lang.Object.class,  // Rencana
                java.lang.Object.class   // Keterangan
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbPerencanaanMPP.setModel(tabModePerencanaan);
        tbPerencanaanMPP.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPerencanaanMPP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // P
        column = tbPerencanaanMPP.getColumnModel().getColumn(0);
        column.setPreferredWidth(20);

        // Kode (hidden)
        column = tbPerencanaanMPP.getColumnModel().getColumn(1);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setPreferredWidth(0);

        // Rencana
        column = tbPerencanaanMPP.getColumnModel().getColumn(2);
        column.setPreferredWidth(800);

        // Keterangan
        column = tbPerencanaanMPP.getColumnModel().getColumn(3);
        column.setPreferredWidth(220);

        tbPerencanaanMPP.setDefaultRenderer(Object.class, new WarnaTable());


        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
//        TDiagnosis.setDocument(new batasInput((int)1000).getKata(TDiagnosis));
//        TKelompok.setDocument(new batasInput((int)1000).getKata(TKelompok));
//        Skrining.setDocument(new batasInput((int)1000).getKata(Skrining));
//        Assemen.setDocument(new batasInput((int)1000).getKata(Assemen));
//        Identifikasi.setDocument(new batasInput((int)1000).getKata(Identifikasi));
//        Perencanaan.setDocument(new batasInput((int)1000).getKata(Perencanaan));
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
            
//            TCariAsesmen.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
//                @Override
//                public void insertUpdate(DocumentEvent e) {
////                    if(TCariAsesmen.getText().length()>2){
////                        tampilMasalah2();
////                    }
//                }
//                @Override
//                public void removeUpdate(DocumentEvent e) {
////                    if(TCariAsesmen.getText().length()>2){
////                        tampilMasalah2();
////                    }
//                }
//                @Override
//                public void changedUpdate(DocumentEvent e) {
////                    if(TCariAsesmen.getText().length()>2){
////                        tampilMasalah2();
////                    }
//                }
//            });
        }
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(petugas.getTable().getSelectedRow()!= -1){ 
                    KdPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                    NmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());   
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
//        LoadHTML.setEditable(true);
//        LoadHTML.setEditorKit(kit);
//        StyleSheet styleSheet = kit.getStyleSheet();
//        styleSheet.addRule(
//                ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
//                ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
//                ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
//                ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
//                ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
//                ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
//                ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
//                ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
//                ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
//        );
//        Document doc = kit.createDefaultDocument();
//        LoadHTML.setDocument(doc);
        
        
//        ChkAccor.setSelected(false);
        isMenu();
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
        MnEvaluasiFormB = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        label14 = new widget.Label();
        KdPetugas = new widget.TextBox();
        NmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        Jk = new widget.TextBox();
        jLabel10 = new widget.Label();
        label11 = new widget.Label();
        jLabel11 = new widget.Label();
        TglEvaluasi = new widget.Tanggal();
        jSeparator3 = new javax.swing.JSeparator();
        Alamat = new widget.TextBox();
        jLabel5 = new widget.Label();
        Kamar = new widget.TextBox();
        jLabel12 = new widget.Label();
        jLabel16 = new widget.Label();
        TDokter1 = new widget.TextBox();
        KdDok1 = new widget.TextBox();
        jLabel18 = new widget.Label();
        btnDokter1 = new widget.Button();
        jLabel20 = new widget.Label();
        TDokter2 = new widget.TextBox();
        KdDok2 = new widget.TextBox();
        btnDokter2 = new widget.Button();
        TglMasuk = new widget.TextBox();
        jLabel23 = new widget.Label();
        KdDok3 = new widget.TextBox();
        TDokter3 = new widget.TextBox();
        btnDokter3 = new widget.Button();
        jLabel24 = new widget.Label();
        KdDok4 = new widget.TextBox();
        TDokter4 = new widget.TextBox();
        btnDokter4 = new widget.Button();
        jLabel25 = new widget.Label();
        KdDok5 = new widget.TextBox();
        TDokter5 = new widget.TextBox();
        btnDokter5 = new widget.Button();
        scrollMain = new javax.swing.JScrollPane();
        panelWrapper = new javax.swing.JPanel();
        jLabel94 = new widget.Label();
        Scroll6 = new widget.ScrollPane();
        tbIdentifikasiMPP = new widget.Table();
        jLabel32 = new widget.Label();
        Scroll8 = new widget.ScrollPane();
        tbIassesmenMPP = new widget.Table();
        jLabel9 = new widget.Label();
        Scroll9 = new widget.ScrollPane();
        tbIdentifikasiMasalah = new widget.Table();
        jLabel31 = new widget.Label();
        Scroll10 = new widget.ScrollPane();
        tbPerencanaanMPP = new widget.Table();
        jSplitPane1 = new javax.swing.JSplitPane();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        internalFrame4 = new widget.InternalFrame();
        scrollInput1 = new widget.ScrollPane();
        FormInput1 = new widget.PanelBiasa();
        Scroll1 = new widget.ScrollPane();
        tbObat1 = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass10 = new widget.panelisi();
        BtnSimpan1 = new widget.Button();
        BtnBatal1 = new widget.Button();
        BtnHapus1 = new widget.Button();
        BtnEdit1 = new widget.Button();
        BtnPrint1 = new widget.Button();
        BtnAll1 = new widget.Button();
        BtnKeluar1 = new widget.Button();
        panelGlass11 = new widget.panelisi();
        jLabel22 = new widget.Label();
        DTPCari3 = new widget.Tanggal();
        jLabel26 = new widget.Label();
        DTPCari4 = new widget.Tanggal();
        jLabel13 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        jLabel14 = new widget.Label();
        LCount1 = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        FormInput2 = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        AlamatLengkap = new widget.TextBox();
        jLabel4 = new widget.Label();
        TNoRw1 = new widget.TextBox();
        TPasien2 = new widget.TextBox();
        TNoRM2 = new widget.TextBox();
        TglLahir1 = new widget.TextBox();
        jLabel15 = new widget.Label();
        Jk1 = new widget.TextBox();
        jLabel17 = new widget.Label();
        label15 = new widget.Label();
        KdPetugas1 = new widget.TextBox();
        NmPetugas1 = new widget.TextBox();
        BtnPetugas1 = new widget.Button();
        label12 = new widget.Label();
        TglImplementasi = new widget.Tanggal();
        scrollPane8 = new widget.ScrollPane();
        Advokasi = new widget.TextArea();
        jLabel43 = new widget.Label();
        scrollPane12 = new widget.ScrollPane();
        Terminasi = new widget.TextArea();
        jLabel47 = new widget.Label();
        jLabel46 = new widget.Label();
        scrollPane11 = new widget.ScrollPane();
        Rencana = new widget.TextArea();
        jLabel48 = new widget.Label();
        scrollPane13 = new widget.ScrollPane();
        Monitoring = new widget.TextArea();
        jLabel49 = new widget.Label();
        scrollPane14 = new widget.ScrollPane();
        Fasilitas = new widget.TextArea();
        scrollPane15 = new widget.ScrollPane();
        Hasil = new widget.TextArea();
        jLabel50 = new widget.Label();
        ChkInput = new widget.CekBox();
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

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnEvaluasiFormB.setBackground(new java.awt.Color(255, 255, 254));
        MnEvaluasiFormB.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnEvaluasiFormB.setForeground(new java.awt.Color(50, 50, 50));
        MnEvaluasiFormB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnEvaluasiFormB.setText("Form - B Catatan Implementasi");
        MnEvaluasiFormB.setName("MnEvaluasiFormB"); // NOI18N
        MnEvaluasiFormB.setPreferredSize(new java.awt.Dimension(250, 26));
        MnEvaluasiFormB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnEvaluasiFormBActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnEvaluasiFormB);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Form A – Evaluasi Awal Manajer Pelayanan Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

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
        FormInput.setPreferredSize(new java.awt.Dimension(870, 573));
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

        label14.setText("Petugas :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(0, 130, 70, 23);

        KdPetugas.setEditable(false);
        KdPetugas.setName("KdPetugas"); // NOI18N
        KdPetugas.setPreferredSize(new java.awt.Dimension(80, 23));
        KdPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPetugasKeyPressed(evt);
            }
        });
        FormInput.add(KdPetugas);
        KdPetugas.setBounds(70, 130, 110, 23);

        NmPetugas.setEditable(false);
        NmPetugas.setName("NmPetugas"); // NOI18N
        NmPetugas.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmPetugas);
        NmPetugas.setBounds(180, 130, 190, 23);

        BtnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas.setMnemonic('2');
        BtnPetugas.setToolTipText("Alt+2");
        BtnPetugas.setName("BtnPetugas"); // NOI18N
        BtnPetugas.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasActionPerformed(evt);
            }
        });
        BtnPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPetugasKeyPressed(evt);
            }
        });
        FormInput.add(BtnPetugas);
        BtnPetugas.setBounds(370, 130, 28, 23);

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

        label11.setText("Tgl.Evaluasi :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(200, 70, 80, 23);

        jLabel11.setText("J.K. :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(740, 10, 30, 23);

        TglEvaluasi.setForeground(new java.awt.Color(50, 70, 50));
        TglEvaluasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-12-2025 07:52:26" }));
        TglEvaluasi.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglEvaluasi.setName("TglEvaluasi"); // NOI18N
        TglEvaluasi.setOpaque(false);
        TglEvaluasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglEvaluasiKeyPressed(evt);
            }
        });
        FormInput.add(TglEvaluasi);
        TglEvaluasi.setBounds(284, 70, 130, 23);

        jSeparator3.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator3.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator3.setName("jSeparator3"); // NOI18N
        FormInput.add(jSeparator3);
        jSeparator3.setBounds(0, 210, 880, 1);

        Alamat.setEditable(false);
        Alamat.setHighlighter(null);
        Alamat.setName("Alamat"); // NOI18N
        FormInput.add(Alamat);
        Alamat.setBounds(74, 40, 495, 23);

        jLabel5.setText("Alamat :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 40, 70, 23);

        Kamar.setEditable(false);
        Kamar.setHighlighter(null);
        Kamar.setName("Kamar"); // NOI18N
        Kamar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KamarKeyPressed(evt);
            }
        });
        FormInput.add(Kamar);
        Kamar.setBounds(644, 40, 210, 23);

        jLabel12.setText("Kamar :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(580, 40, 60, 23);

        jLabel16.setText("Tgl.Masuk :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 70, 70, 23);

        TDokter1.setEditable(false);
        TDokter1.setName("TDokter1"); // NOI18N
        FormInput.add(TDokter1);
        TDokter1.setBounds(176, 100, 190, 23);

        KdDok1.setEditable(false);
        KdDok1.setHighlighter(null);
        KdDok1.setName("KdDok1"); // NOI18N
        KdDok1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdDok1KeyPressed(evt);
            }
        });
        FormInput.add(KdDok1);
        KdDok1.setBounds(74, 100, 100, 23);

        jLabel18.setText("Dokter P.J. :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(0, 100, 70, 23);

        btnDokter1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter1.setMnemonic('2');
        btnDokter1.setToolTipText("ALt+2");
        btnDokter1.setName("btnDokter1"); // NOI18N
        btnDokter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokter1ActionPerformed(evt);
            }
        });
        btnDokter1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDokter1KeyPressed(evt);
            }
        });
        FormInput.add(btnDokter1);
        btnDokter1.setBounds(369, 100, 28, 23);

        jLabel20.setText("Dokter Konsulan 1 :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(420, 70, 103, 23);

        TDokter2.setEditable(false);
        TDokter2.setName("TDokter2"); // NOI18N
        FormInput.add(TDokter2);
        TDokter2.setBounds(633, 70, 190, 23);

        KdDok2.setEditable(false);
        KdDok2.setHighlighter(null);
        KdDok2.setName("KdDok2"); // NOI18N
        KdDok2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdDok2KeyPressed(evt);
            }
        });
        FormInput.add(KdDok2);
        KdDok2.setBounds(530, 70, 100, 23);

        btnDokter2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter2.setMnemonic('2');
        btnDokter2.setToolTipText("ALt+2");
        btnDokter2.setName("btnDokter2"); // NOI18N
        btnDokter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokter2ActionPerformed(evt);
            }
        });
        btnDokter2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDokter2KeyPressed(evt);
            }
        });
        FormInput.add(btnDokter2);
        btnDokter2.setBounds(830, 70, 28, 23);

        TglMasuk.setEditable(false);
        TglMasuk.setHighlighter(null);
        TglMasuk.setName("TglMasuk"); // NOI18N
        FormInput.add(TglMasuk);
        TglMasuk.setBounds(74, 70, 130, 23);

        jLabel23.setText("Dokter Konsulan 2 :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(420, 100, 103, 23);

        KdDok3.setEditable(false);
        KdDok3.setHighlighter(null);
        KdDok3.setName("KdDok3"); // NOI18N
        KdDok3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdDok3KeyPressed(evt);
            }
        });
        FormInput.add(KdDok3);
        KdDok3.setBounds(530, 100, 100, 23);

        TDokter3.setEditable(false);
        TDokter3.setName("TDokter3"); // NOI18N
        FormInput.add(TDokter3);
        TDokter3.setBounds(640, 100, 190, 23);

        btnDokter3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter3.setMnemonic('2');
        btnDokter3.setToolTipText("ALt+2");
        btnDokter3.setName("btnDokter3"); // NOI18N
        btnDokter3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokter3ActionPerformed(evt);
            }
        });
        btnDokter3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDokter3KeyPressed(evt);
            }
        });
        FormInput.add(btnDokter3);
        btnDokter3.setBounds(830, 100, 28, 23);

        jLabel24.setText("Dokter Konsulan 3 :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(420, 130, 103, 23);

        KdDok4.setEditable(false);
        KdDok4.setHighlighter(null);
        KdDok4.setName("KdDok4"); // NOI18N
        KdDok4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdDok4KeyPressed(evt);
            }
        });
        FormInput.add(KdDok4);
        KdDok4.setBounds(530, 130, 100, 23);

        TDokter4.setEditable(false);
        TDokter4.setName("TDokter4"); // NOI18N
        FormInput.add(TDokter4);
        TDokter4.setBounds(640, 130, 190, 23);

        btnDokter4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter4.setMnemonic('2');
        btnDokter4.setToolTipText("ALt+2");
        btnDokter4.setName("btnDokter4"); // NOI18N
        btnDokter4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokter4ActionPerformed(evt);
            }
        });
        btnDokter4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDokter4KeyPressed(evt);
            }
        });
        FormInput.add(btnDokter4);
        btnDokter4.setBounds(830, 130, 28, 23);

        jLabel25.setText("Dokter Konsulan 4 :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(420, 160, 103, 23);

        KdDok5.setEditable(false);
        KdDok5.setHighlighter(null);
        KdDok5.setName("KdDok5"); // NOI18N
        KdDok5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdDok5KeyPressed(evt);
            }
        });
        FormInput.add(KdDok5);
        KdDok5.setBounds(530, 160, 100, 23);

        TDokter5.setEditable(false);
        TDokter5.setName("TDokter5"); // NOI18N
        FormInput.add(TDokter5);
        TDokter5.setBounds(640, 160, 190, 23);

        btnDokter5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter5.setMnemonic('2');
        btnDokter5.setToolTipText("ALt+2");
        btnDokter5.setName("btnDokter5"); // NOI18N
        btnDokter5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokter5ActionPerformed(evt);
            }
        });
        btnDokter5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDokter5KeyPressed(evt);
            }
        });
        FormInput.add(btnDokter5);
        btnDokter5.setBounds(830, 160, 28, 23);

        scrollMain.setName("scrollMain"); // NOI18N

        panelWrapper.setBackground(new java.awt.Color(255, 255, 255));
        panelWrapper.setName("panelWrapper"); // NOI18N
        panelWrapper.setPreferredSize(new java.awt.Dimension(5876, 900));
        panelWrapper.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel94.setText("Identifikasi / Skrining Pasien :");
        jLabel94.setName("jLabel94"); // NOI18N
        panelWrapper.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 180, 23));

        Scroll6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbIdentifikasiMPP.setName("tbIdentifikasiMPP"); // NOI18N
        tbIdentifikasiMPP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbIdentifikasiMPPMouseClicked(evt);
            }
        });
        Scroll6.setViewportView(tbIdentifikasiMPP);

        panelWrapper.add(Scroll6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 900, 300));

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("Assesmen :");
        jLabel32.setName("jLabel32"); // NOI18N
        panelWrapper.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 120, 23));

        Scroll8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);

        tbIassesmenMPP.setName("tbIassesmenMPP"); // NOI18N
        tbIassesmenMPP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbIassesmenMPPMouseClicked(evt);
            }
        });
        Scroll8.setViewportView(tbIassesmenMPP);

        panelWrapper.add(Scroll8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 900, 250));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Identifikasi Masalah :");
        jLabel9.setName("jLabel9"); // NOI18N
        panelWrapper.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 630, 120, 23));

        Scroll9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        Scroll9.setName("Scroll9"); // NOI18N
        Scroll9.setOpaque(true);

        tbIdentifikasiMasalah.setName("tbIdentifikasiMasalah"); // NOI18N
        tbIdentifikasiMasalah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbIdentifikasiMasalahMouseClicked(evt);
            }
        });
        Scroll9.setViewportView(tbIdentifikasiMasalah);

        panelWrapper.add(Scroll9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 650, 900, 160));

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Perencanaan :");
        jLabel31.setName("jLabel31"); // NOI18N
        panelWrapper.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 820, 120, 23));

        Scroll10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        Scroll10.setName("Scroll10"); // NOI18N
        Scroll10.setOpaque(true);

        tbPerencanaanMPP.setName("tbPerencanaanMPP"); // NOI18N
        tbPerencanaanMPP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPerencanaanMPPMouseClicked(evt);
            }
        });
        Scroll10.setViewportView(tbPerencanaanMPP);

        panelWrapper.add(Scroll10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 840, 900, 160));

        jSplitPane1.setName("jSplitPane1"); // NOI18N
        panelWrapper.add(jSplitPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5424, 402, 452, 402));

        scrollMain.setViewportView(panelWrapper);

        FormInput.add(scrollMain);
        scrollMain.setBounds(0, 220, 950, 500);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

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

        internalFrame2.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Form A", internalFrame2);

        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput1.setName("scrollInput1"); // NOI18N
        scrollInput1.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput1.setBackground(new java.awt.Color(255, 255, 255));
        FormInput1.setBorder(null);
        FormInput1.setName("FormInput1"); // NOI18N
        FormInput1.setPreferredSize(new java.awt.Dimension(870, 573));
        FormInput1.setLayout(null);
        scrollInput1.setViewportView(FormInput1);

        internalFrame4.add(scrollInput1, java.awt.BorderLayout.CENTER);

        Scroll1.setMinimumSize(new java.awt.Dimension(10, 10));
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 30));

        tbObat1.setAutoCreateRowSorter(true);
        tbObat1.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat1.setName("tbObat1"); // NOI18N
        tbObat1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObat1MouseClicked(evt);
            }
        });
        tbObat1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbObat1KeyReleased(evt);
            }
        });
        Scroll1.setViewportView(tbObat1);

        internalFrame4.add(Scroll1, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan1.setMnemonic('S');
        BtnSimpan1.setText("Simpan");
        BtnSimpan1.setToolTipText("Alt+S");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        BtnSimpan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpan1KeyPressed(evt);
            }
        });
        panelGlass10.add(BtnSimpan1);

        BtnBatal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal1.setMnemonic('B');
        BtnBatal1.setText("Baru");
        BtnBatal1.setToolTipText("Alt+B");
        BtnBatal1.setName("BtnBatal1"); // NOI18N
        BtnBatal1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatal1ActionPerformed(evt);
            }
        });
        BtnBatal1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatal1KeyPressed(evt);
            }
        });
        panelGlass10.add(BtnBatal1);

        BtnHapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus1.setMnemonic('H');
        BtnHapus1.setText("Hapus");
        BtnHapus1.setToolTipText("Alt+H");
        BtnHapus1.setName("BtnHapus1"); // NOI18N
        BtnHapus1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus1ActionPerformed(evt);
            }
        });
        BtnHapus1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapus1KeyPressed(evt);
            }
        });
        panelGlass10.add(BtnHapus1);

        BtnEdit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit1.setMnemonic('G');
        BtnEdit1.setText("Ganti");
        BtnEdit1.setToolTipText("Alt+G");
        BtnEdit1.setName("BtnEdit1"); // NOI18N
        BtnEdit1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEdit1ActionPerformed(evt);
            }
        });
        BtnEdit1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEdit1KeyPressed(evt);
            }
        });
        panelGlass10.add(BtnEdit1);

        BtnPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint1.setMnemonic('T');
        BtnPrint1.setText("Cetak");
        BtnPrint1.setToolTipText("Alt+T");
        BtnPrint1.setName("BtnPrint1"); // NOI18N
        BtnPrint1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint1ActionPerformed(evt);
            }
        });
        BtnPrint1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrint1KeyPressed(evt);
            }
        });
        panelGlass10.add(BtnPrint1);

        BtnAll1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll1.setMnemonic('M');
        BtnAll1.setText("Semua");
        BtnAll1.setToolTipText("Alt+M");
        BtnAll1.setName("BtnAll1"); // NOI18N
        BtnAll1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnAll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll1ActionPerformed(evt);
            }
        });
        BtnAll1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAll1KeyPressed(evt);
            }
        });
        panelGlass10.add(BtnAll1);

        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setMnemonic('K');
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+K");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        BtnKeluar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar1KeyPressed(evt);
            }
        });
        panelGlass10.add(BtnKeluar1);

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel22.setText("Tanggal :");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass11.add(jLabel22);

        DTPCari3.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-12-2025" }));
        DTPCari3.setDisplayFormat("dd-MM-yyyy");
        DTPCari3.setName("DTPCari3"); // NOI18N
        DTPCari3.setOpaque(false);
        DTPCari3.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass11.add(DTPCari3);

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("s.d.");
        jLabel26.setName("jLabel26"); // NOI18N
        jLabel26.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass11.add(jLabel26);

        DTPCari4.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-12-2025" }));
        DTPCari4.setDisplayFormat("dd-MM-yyyy");
        DTPCari4.setName("DTPCari4"); // NOI18N
        DTPCari4.setOpaque(false);
        DTPCari4.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass11.add(DTPCari4);

        jLabel13.setText("Key Word :");
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass11.add(jLabel13);

        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(210, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelGlass11.add(TCari1);

        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('3');
        BtnCari1.setToolTipText("Alt+3");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        BtnCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari1KeyPressed(evt);
            }
        });
        panelGlass11.add(BtnCari1);

        jLabel14.setText("Record :");
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass11.add(jLabel14);

        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass11.add(LCount1);

        jPanel3.add(panelGlass11, java.awt.BorderLayout.PAGE_START);

        internalFrame4.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 470));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput2.setName("FormInput2"); // NOI18N
        FormInput2.setPreferredSize(new java.awt.Dimension(200, 450));
        FormInput2.setLayout(null);

        jLabel3.setText("Tanggal Lahir :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput2.add(jLabel3);
        jLabel3.setBounds(0, 40, 85, 23);

        AlamatLengkap.setEditable(false);
        AlamatLengkap.setHighlighter(null);
        AlamatLengkap.setName("AlamatLengkap"); // NOI18N
        AlamatLengkap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlamatLengkapKeyPressed(evt);
            }
        });
        FormInput2.add(AlamatLengkap);
        AlamatLengkap.setBounds(440, 40, 295, 23);

        jLabel4.setText("No.Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput2.add(jLabel4);
        jLabel4.setBounds(0, 10, 85, 23);

        TNoRw1.setHighlighter(null);
        TNoRw1.setName("TNoRw1"); // NOI18N
        TNoRw1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRw1KeyPressed(evt);
            }
        });
        FormInput2.add(TNoRw1);
        TNoRw1.setBounds(89, 10, 151, 23);

        TPasien2.setEditable(false);
        TPasien2.setHighlighter(null);
        TPasien2.setName("TPasien2"); // NOI18N
        TPasien2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasien2KeyPressed(evt);
            }
        });
        FormInput2.add(TPasien2);
        TPasien2.setBounds(355, 10, 380, 23);

        TNoRM2.setEditable(false);
        TNoRM2.setHighlighter(null);
        TNoRM2.setName("TNoRM2"); // NOI18N
        TNoRM2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRM2KeyPressed(evt);
            }
        });
        FormInput2.add(TNoRM2);
        TNoRM2.setBounds(242, 10, 111, 23);

        TglLahir1.setEditable(false);
        TglLahir1.setHighlighter(null);
        TglLahir1.setName("TglLahir1"); // NOI18N
        FormInput2.add(TglLahir1);
        TglLahir1.setBounds(89, 40, 100, 23);

        jLabel15.setText("J.K. :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput2.add(jLabel15);
        jLabel15.setBounds(208, 40, 30, 23);

        Jk1.setEditable(false);
        Jk1.setHighlighter(null);
        Jk1.setName("Jk1"); // NOI18N
        FormInput2.add(Jk1);
        Jk1.setBounds(242, 40, 111, 23);

        jLabel17.setText("Alamat :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput2.add(jLabel17);
        jLabel17.setBounds(366, 40, 70, 23);

        label15.setText("Petugas :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput2.add(label15);
        label15.setBounds(0, 70, 85, 23);

        KdPetugas1.setEditable(false);
        KdPetugas1.setName("KdPetugas1"); // NOI18N
        KdPetugas1.setPreferredSize(new java.awt.Dimension(80, 23));
        KdPetugas1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPetugas1KeyPressed(evt);
            }
        });
        FormInput2.add(KdPetugas1);
        KdPetugas1.setBounds(89, 70, 110, 23);

        NmPetugas1.setEditable(false);
        NmPetugas1.setName("NmPetugas1"); // NOI18N
        NmPetugas1.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput2.add(NmPetugas1);
        NmPetugas1.setBounds(201, 70, 235, 23);

        BtnPetugas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas1.setMnemonic('2');
        BtnPetugas1.setToolTipText("Alt+2");
        BtnPetugas1.setName("BtnPetugas1"); // NOI18N
        BtnPetugas1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPetugas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugas1ActionPerformed(evt);
            }
        });
        BtnPetugas1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPetugas1KeyPressed(evt);
            }
        });
        FormInput2.add(BtnPetugas1);
        BtnPetugas1.setBounds(440, 70, 28, 23);

        label12.setText("Tanggal Catatan :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput2.add(label12);
        label12.setBounds(491, 70, 100, 23);

        TglImplementasi.setForeground(new java.awt.Color(50, 70, 50));
        TglImplementasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-12-2025 07:52:28" }));
        TglImplementasi.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglImplementasi.setName("TglImplementasi"); // NOI18N
        TglImplementasi.setOpaque(false);
        TglImplementasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglImplementasiKeyPressed(evt);
            }
        });
        FormInput2.add(TglImplementasi);
        TglImplementasi.setBounds(595, 70, 140, 23);

        scrollPane8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane8.setName("scrollPane8"); // NOI18N

        Advokasi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Advokasi.setColumns(20);
        Advokasi.setRows(5);
        Advokasi.setName("Advokasi"); // NOI18N
        Advokasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AdvokasiKeyPressed(evt);
            }
        });
        scrollPane8.setViewportView(Advokasi);

        FormInput2.add(scrollPane8);
        scrollPane8.setBounds(390, 230, 350, 70);

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Advokasi Pelayanan Pasien");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput2.add(jLabel43);
        jLabel43.setBounds(390, 210, 350, 23);

        scrollPane12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane12.setName("scrollPane12"); // NOI18N

        Terminasi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Terminasi.setColumns(20);
        Terminasi.setRows(5);
        Terminasi.setName("Terminasi"); // NOI18N
        Terminasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TerminasiKeyPressed(evt);
            }
        });
        scrollPane12.setViewportView(Terminasi);

        FormInput2.add(scrollPane12);
        scrollPane12.setBounds(390, 330, 350, 70);

        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("Terminasi & Catatan Kepuasan Pasien Terhadap MPP");
        jLabel47.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel47.setName("jLabel47"); // NOI18N
        jLabel47.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        FormInput2.add(jLabel47);
        jLabel47.setBounds(390, 310, 350, 23);

        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Pelaksanaan Rencana Pelayanan Pasien");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput2.add(jLabel46);
        jLabel46.setBounds(20, 110, 340, 23);

        scrollPane11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane11.setName("scrollPane11"); // NOI18N
        scrollPane11.setPreferredSize(new java.awt.Dimension(80, 74));

        Rencana.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Rencana.setColumns(20);
        Rencana.setRows(5);
        Rencana.setName("Rencana"); // NOI18N
        Rencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RencanaKeyPressed(evt);
            }
        });
        scrollPane11.setViewportView(Rencana);

        FormInput2.add(scrollPane11);
        scrollPane11.setBounds(20, 130, 340, 70);

        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("Monitoring Pelayanan");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput2.add(jLabel48);
        jLabel48.setBounds(390, 110, 350, 23);

        scrollPane13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane13.setName("scrollPane13"); // NOI18N

        Monitoring.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Monitoring.setColumns(20);
        Monitoring.setRows(5);
        Monitoring.setName("Monitoring"); // NOI18N
        Monitoring.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MonitoringKeyPressed(evt);
            }
        });
        scrollPane13.setViewportView(Monitoring);

        FormInput2.add(scrollPane13);
        scrollPane13.setBounds(390, 130, 350, 70);

        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("Fasilitas Pelayanan");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput2.add(jLabel49);
        jLabel49.setBounds(20, 210, 340, 23);

        scrollPane14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane14.setName("scrollPane14"); // NOI18N

        Fasilitas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Fasilitas.setColumns(20);
        Fasilitas.setRows(5);
        Fasilitas.setName("Fasilitas"); // NOI18N
        Fasilitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FasilitasKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(Fasilitas);

        FormInput2.add(scrollPane14);
        scrollPane14.setBounds(20, 230, 340, 70);

        scrollPane15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane15.setName("scrollPane15"); // NOI18N

        Hasil.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Hasil.setColumns(20);
        Hasil.setRows(5);
        Hasil.setName("Hasil"); // NOI18N
        Hasil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HasilKeyPressed(evt);
            }
        });
        scrollPane15.setViewportView(Hasil);

        FormInput2.add(scrollPane15);
        scrollPane15.setBounds(20, 330, 340, 70);

        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setText("Hasil Pelayanan");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput2.add(jLabel50);
        jLabel50.setBounds(20, 310, 340, 23);

        PanelInput.add(FormInput2, java.awt.BorderLayout.PAGE_START);

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

        internalFrame4.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("Form B", internalFrame4);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat.setAutoCreateRowSorter(true);
        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
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

        jLabel19.setText("Tgl. Evaluasi : ");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-12-2025" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-12-2025" }));
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

        TabRawat.addTab("Data Evaluasi", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
           
        }else{            
            Valid.pindah(evt,TCari,BtnPetugas);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
 String dok3 = KdDok3.getText().trim().equals("") ? null : KdDok3.getText().trim();
       String dok4 = KdDok4.getText().trim().equals("") ? null : KdDok4.getText().trim();
       String dok5 = KdDok5.getText().trim().equals("") ? null : KdDok5.getText().trim();

      
       if (TNoRM.getText().trim().equals("")) {
           Valid.textKosong(TNoRw, "Nama Pasien");
           return;
       }
       if (NmPetugas.getText().trim().equals("")) {
           Valid.textKosong(BtnPetugas, "Petugas");
           return;
       }
       if (TDokter1.getText().trim().equals("")) {
           Valid.textKosong(TDokter1, "Dokter DPJP");
           return;
       }

       // --- Stop editor JTable jika masih aktif ---
       try { if (tbIassesmenMPP.isEditing()) tbIassesmenMPP.getCellEditor().stopCellEditing(); } catch (Exception e) {}
       try { if (tbIdentifikasiMasalah.isEditing()) tbIdentifikasiMasalah.getCellEditor().stopCellEditing(); } catch (Exception e) {}
       try { if (tbPerencanaanMPP.isEditing()) tbPerencanaanMPP.getCellEditor().stopCellEditing(); } catch (Exception e) {}

       try {
           String tglEval = Valid.SetTgl(TglEvaluasi.getSelectedItem() + "") + " " +
                            TglEvaluasi.getSelectedItem().toString().substring(11, 19);

           String kdKons1 = KdDok2.getText().trim().equals("") ? "NULL" : "'" + KdDok2.getText().trim() + "'";
           String kdKons2 = (dok3 == null) ? "NULL" : "'" + dok3 + "'";
           String kdKons3 = (dok4 == null) ? "NULL" : "'" + dok4 + "'";
           String kdKons4 = (dok5 == null) ? "NULL" : "'" + dok5 + "'";

         
           Sequel.meghapus("mpp_evaluasi", "no_rawat", TNoRw.getText().trim());

           String sqlValue =
               "'" + TNoRw.getText().trim() + "'," +
               "'" + tglEval + "'," +
               "'" + KdDok1.getText().trim() + "'," +
               kdKons1 + "," +
               kdKons2 + "," +
               kdKons3 + "," +
               kdKons4 + "," +
               "'' ," +
               "'' ," +
               "'' ," +
               "'" + KdPetugas.getText().trim() + "'";

           if (!Sequel.menyimpantf("mpp_evaluasi", sqlValue, "No.Rawat")) {
               return;
           }

           // ============================
           // 1. SIMPAN IDENTIFIKASI MASALAH/SKRINING
           // ============================
          
     for (int i = 0; i < tbIdentifikasiMPP.getRowCount(); i++) {

        Object cekObj = tbIdentifikasiMPP.getModel().getValueAt(i, 0);
        Object kdObj  = tbIdentifikasiMPP.getModel().getValueAt(i, 1);

        boolean dipilih = Boolean.TRUE.equals(cekObj);

        if (dipilih && kdObj != null && !kdObj.toString().trim().equals("")) {

            String kode = kdObj.toString().trim();

            boolean sudahAda = Sequel.cariInteger(
                "select count(*) from mpp_evaluasi_masalah " +
                "where no_rawat='" + TNoRw.getText().trim() + "' " +
                "and kode_masalah='" + kode + "'"
            ) > 0;

            System.out.println("kode=" + kode + " sudahAda=" + sudahAda);

            if (!sudahAda) {
                Sequel.queryu(
                    "insert into mpp_evaluasi_masalah(no_rawat,tanggal,kode_masalah) values (" +
                    "'" + TNoRw.getText().trim() + "'," +
                    "'" + tglEval + "'," +
                    "'" + kode + "')"
                );
            }
        }
    }


           // ============================
           // 2. SIMPAN ASESMEN
           // ============================
           Sequel.meghapus("assesmen_mpp_pasien", "no_rawat", TNoRw.getText().trim());

           for (int r = 0; r < tbIassesmenMPP.getRowCount(); r++) {
               boolean dipilih = Boolean.TRUE.equals(tbIassesmenMPP.getValueAt(r, 0));

               String kodeItem = tbIassesmenMPP.getValueAt(r, 1) == null ? "" :
                                 tbIassesmenMPP.getValueAt(r, 1).toString();

               String jawaban = tbIassesmenMPP.getValueAt(r, 3) == null ? "" :
                                tbIassesmenMPP.getValueAt(r, 3).toString().trim();

             
               if (dipilih || !jawaban.equals("")) {
                   Sequel.menyimpan2(
                       "assesmen_mpp_pasien",
                       "?,?,?,?,?",
                       5,
                       new String[]{
                           TNoRw.getText().trim(),
                           kodeItem,
                           jawaban,
                           tglEval,
                           KdPetugas.getText().trim()
                       });
               }
           }

           // ============================
           // 3. SIMPAN IDENTIFIKASI MASALAH DETAIL
           // ============================
           Sequel.meghapus("identifikasi_masalah_mpp_pasien", "no_rawat", TNoRw.getText().trim());

        for (int r = 0; r < tbIdentifikasiMasalah.getRowCount(); r++) {
            boolean dipilih = Boolean.TRUE.equals(tbIdentifikasiMasalah.getValueAt(r, 0));

            String kodeItem = tbIdentifikasiMasalah.getValueAt(r, 1) == null ? "" :
                              tbIdentifikasiMasalah.getValueAt(r, 1).toString();

            String uraian = tbIdentifikasiMasalah.getValueAt(r, 3) == null ? "" :
                            tbIdentifikasiMasalah.getValueAt(r, 3).toString().trim();

            if (dipilih || !uraian.equals("")) {
                Sequel.menyimpan2(
                    "identifikasi_masalah_mpp_pasien",
                    "?,?,?,?,?",
                    5,
                    new String[]{
                        TNoRw.getText().trim(),
                        kodeItem,
                        uraian,
                        tglEval,
                        KdPetugas.getText().trim()
                    }
                );
            }
        }


           // ============================
           // 4. SIMPAN PERENCANAAN
           // ============================
           Sequel.meghapus("perencanaan_mpp_pasien", "no_rawat", TNoRw.getText().trim());

           for (int r = 0; r < tbPerencanaanMPP.getRowCount(); r++) {
               boolean dipilih = Boolean.TRUE.equals(tbPerencanaanMPP.getValueAt(r, 0));

               String kodeItem = tbPerencanaanMPP.getValueAt(r, 1) == null ? "" :
                                 tbPerencanaanMPP.getValueAt(r, 1).toString();

               String ket = tbPerencanaanMPP.getValueAt(r, 3) == null ? "" :
                            tbPerencanaanMPP.getValueAt(r, 3).toString().trim();

               if (dipilih || !ket.equals("")) {
                   Sequel.menyimpan2(
                       "perencanaan_mpp_pasien",
                       "?,?,?,?,?,?",
                       6,
                       new String[]{
                           TNoRw.getText().trim(),
                           kodeItem,
                           dipilih ? "Y" : "N",
                           ket,
                           tglEval,
                           KdPetugas.getText().trim()
                       }
                   );
               }
           }

           emptTeks();

       } catch (Exception e) {
           System.out.println("Notifikasi simpan MPP : " + e);
           JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menyimpan data MPP.");
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
                if(KdPetugas.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString())){
                    hapus();
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
        
        
        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
//        }else if(TDiagnosis.getText().trim().equals("")){
//            Valid.textKosong(TDiagnosis,"Diagnosis");
//        }else if(TKelompok.getText().trim().equals("")){
//            Valid.textKosong(TKelompok,"Kelompok Resiko");
//        }else if(Perencanaan.getText().trim().equals("")){
//            Valid.textKosong(Perencanaan,"Riwayat Penyakit Dahulu");
//        }else if(Skrining.getText().trim().equals("")){
//            Valid.textKosong(Skrining,"Riwayat Penyakit Sekarang");
//        }else if(Assemen.getText().trim().equals("")){
//            Valid.textKosong(Assemen,"Riwayat Penyakit Keluarga");
//        }else if(Identifikasi.getText().trim().equals("")){
//            Valid.textKosong(Identifikasi,"Riwayat Pengobatan");
//        }else if(Perencanaan.getText().trim().equals("")){
//            Valid.textKosong(Perencanaan,"Lokasi");
        }else if(NmPetugas.getText().trim().equals("")){
            Valid.textKosong(BtnPetugas,"Petugas");
        }else if(TDokter1.getText().trim().equals("")){
            Valid.textKosong(TDokter1,"Dokter DPJP");
        }else if(TDokter2.getText().trim().equals("")){
            Valid.textKosong(TDokter2,"Dokter Konsulan");
        }else if(TDokter3.getText().trim().equals("")){
            Valid.textKosong(TDokter3,"Dokter Konsulan 2");
        }else if(TDokter4.getText().trim().equals("")){
            Valid.textKosong(TDokter4,"Dokter Konsulan 3");
        }else if(TDokter5.getText().trim().equals("")){
            Valid.textKosong(TDokter5,"Dokter Konsulan 4");
        }else{
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode().equals("Admin Utama")){
                    ganti();
                }else{
                    if(KdPetugas.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString())){
                        ganti();
                    }else{
                        JOptionPane.showMessageDialog(null,"Hanya bisa diganti oleh petugas yang bersangkutan..!!");
                    }
                }
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
       if (TNoRw.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan pilih data pasien terlebih dahulu...!!!!");
        TNoRw.requestFocus();
    } else {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            // Ambil No Rawat
            String noRawat = TNoRw.getText().trim();

            // 🔥 URL ENCODE (INI PENTING)
            String encodedNoRawat = URLEncoder.encode(noRawat, StandardCharsets.UTF_8.toString());

            // URL ke PHP
            String url = "http://192.168.1.11/webapps/cetak_mpp/forma.php?no_rawat=" + encodedNoRawat;

            // Buka browser
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
            JOptionPane.showMessageDialog(null, "Gagal membuka browser: " + e.getMessage());
        } finally {
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

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

    private void KdPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPetugasKeyPressed
        
    }//GEN-LAST:event_KdPetugasKeyPressed

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void BtnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPetugasKeyPressed
        Valid.pindah(evt,TglEvaluasi,btnDokter1);
    }//GEN-LAST:event_BtnPetugasKeyPressed

    private void TglEvaluasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglEvaluasiKeyPressed
        Valid.pindah2(evt,TNoRw,BtnPetugas);
    }//GEN-LAST:event_TglEvaluasiKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==1){
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
//            if(Valid.daysOld("./cache/masalahmpp.iyem")<30){
//                tampilMasalah2();
//               
//        
//            }else{
//                tampilMasalah();
//                       
//            }
             tampilMasalah();
            tampilAsesmen(TNoRw.getText());
            tampilIdentifikasiMasalah(TNoRw.getText());
            tampilPerencanaan(TNoRw.getText());
             tampilFormB(TNoRw.getText());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_formWindowOpened

    private void KamarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KamarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KamarKeyPressed

    private void KdDok1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdDok1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnDokter1ActionPerformed(null);
        }
    }//GEN-LAST:event_KdDok1KeyPressed

    private void btnDokter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokter1ActionPerformed
        pilihan=1;
        DlgCariDokter dokter=new DlgCariDokter(null,false);
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){
                    if(pilihan==1){
                        KdDok1.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter1.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter1.requestFocus();
                    }else if(pilihan==2){
                        KdDok2.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter2.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter2.requestFocus();
                    }else if(pilihan==3){
                        KdDok3.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter3.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter3.requestFocus();
                    }else if(pilihan==4){
                        KdDok4.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter4.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter4.requestFocus();
                    }else if(pilihan==5){
                        KdDok5.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter5.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter5.requestFocus();
                    }
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
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDokter1ActionPerformed

    private void btnDokter1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDokter1KeyPressed
        Valid.pindah(evt,BtnPetugas,btnDokter2);
    }//GEN-LAST:event_btnDokter1KeyPressed

    private void KdDok2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdDok2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnDokter2ActionPerformed(null);
        }
    }//GEN-LAST:event_KdDok2KeyPressed

    private void btnDokter2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokter2ActionPerformed
        pilihan=2;
        DlgCariDokter dokter=new DlgCariDokter(null,false);
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){
                    if(pilihan==1){
                        KdDok1.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter1.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter1.requestFocus();
                    }else if(pilihan==2){
                        KdDok2.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter2.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter2.requestFocus();
                    }else if(pilihan==3){
                        KdDok3.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter3.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter3.requestFocus();
                    }else if(pilihan==4){
                        KdDok4.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter4.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter4.requestFocus();
                    }else if(pilihan==5){
                        KdDok5.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter5.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter5.requestFocus();
                    }
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
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDokter2ActionPerformed

    private void btnDokter2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDokter2KeyPressed
        Valid.pindah(evt,btnDokter1,btnDokter2);
    }//GEN-LAST:event_btnDokter2KeyPressed

    private void KdDok3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdDok3KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnDokter3ActionPerformed(null);
        }// TODO add your handling code here:
    }//GEN-LAST:event_KdDok3KeyPressed

    private void btnDokter3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokter3ActionPerformed
        pilihan=3;
        DlgCariDokter dokter=new DlgCariDokter(null,false);
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){
                    if(pilihan==1){
                        KdDok1.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter1.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter1.requestFocus();
                    }else if(pilihan==2){
                        KdDok2.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter2.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter2.requestFocus();
                    }else if(pilihan==3){
                        KdDok3.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter3.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter3.requestFocus();
                    }else if(pilihan==4){
                        KdDok4.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter4.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter4.requestFocus();
                    }else if(pilihan==5){
                        KdDok5.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter5.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter5.requestFocus();
                    }
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
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnDokter3ActionPerformed

    private void btnDokter3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDokter3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDokter3KeyPressed

    private void KdDok4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdDok4KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnDokter4ActionPerformed(null);
        }// TODO add your handling code here:
    }//GEN-LAST:event_KdDok4KeyPressed
  

    private void btnDokter4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokter4ActionPerformed
        pilihan=4;
        DlgCariDokter dokter=new DlgCariDokter(null,false);
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){
                    if(pilihan==1){
                        KdDok1.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter1.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter1.requestFocus();
                    }else if(pilihan==2){
                        KdDok2.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter2.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter2.requestFocus();
                    }else if(pilihan==3){
                        KdDok3.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter3.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter3.requestFocus();
                    }else if(pilihan==4){
                        KdDok4.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter4.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter4.requestFocus();
                    }else if(pilihan==5){
                        KdDok5.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter5.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter5.requestFocus();
                    }
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
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnDokter4ActionPerformed

    private void btnDokter4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDokter4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDokter4KeyPressed

    private void KdDok5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdDok5KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnDokter5ActionPerformed(null);
        }// TODO add your handling code here:
    }//GEN-LAST:event_KdDok5KeyPressed

    private void btnDokter5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokter5ActionPerformed
        pilihan=5;
        DlgCariDokter dokter=new DlgCariDokter(null,false);
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){
                    if(pilihan==1){
                        KdDok1.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter1.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter1.requestFocus();
                    }else if(pilihan==2){
                        KdDok2.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter2.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter2.requestFocus();
                    }else if(pilihan==3){
                        KdDok3.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter3.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter3.requestFocus();
                    }else if(pilihan==4){
                        KdDok4.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter4.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter4.requestFocus();
                    }else if(pilihan==5){
                        KdDok5.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        TDokter5.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        btnDokter5.requestFocus();
                    }
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
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnDokter5ActionPerformed

    private void btnDokter5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDokter5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDokter5KeyPressed

    private void MnEvaluasiFormBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnEvaluasiFormBActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TCari.requestFocus();
        }else{
            if(tbObat.getSelectedRow()>-1){
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                RMSkriningMPPFormB form=new RMSkriningMPPFormB(null,false);
                form.isCek();
                form.setNoRm(TNoRw.getText(),DTPCari2.getDate());
                form.tampil();
                form.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnEvaluasiFormBActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
    }//GEN-LAST:event_BtnCariActionPerformed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
//                    ChkAccor.setSelected(true);
                    isMenu();
                    getMasalah();
                } catch (java.lang.NullPointerException e) {
                }
            }else if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                try {
                    getData();
                    getMasalah();
                    TabRawat.setSelectedIndex(0);
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbObatKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
            if (tbObat.getSelectedRow() != -1) {

            int row = tbObat.getSelectedRow();
            String noRawat = tbObat.getValueAt(row, 0).toString();

            // set ke field
//            TNoRw.setText(noRawat);

            // tampilkan data berdasarkan no_rawat terpilih
            isMenu();
            getMasalah();
            getData();

            TabRawat.setSelectedIndex(0);
            tampilMasalah();

            tampilAsesmen(noRawat);
            tampilIdentifikasiMasalah(noRawat);
            tampilPerencanaan(noRawat);
            tampilFormB(noRawat);
        }
    }//GEN-LAST:event_tbObatMouseClicked

    private void tbIdentifikasiMPPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbIdentifikasiMPPMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbIdentifikasiMPPMouseClicked

    private void tbPerencanaanMPPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPerencanaanMPPMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPerencanaanMPPMouseClicked

    private void tbIdentifikasiMasalahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbIdentifikasiMasalahMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbIdentifikasiMasalahMouseClicked

    private void tbIassesmenMPPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbIassesmenMPPMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbIassesmenMPPMouseClicked

    private void tbObat1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObat1MouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObat1MouseClicked

    private void tbObat1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObat1KeyReleased
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbObat1KeyReleased

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        if(Rencana.getText().trim().equals("")){
            Valid.textKosong(Rencana,"Rencana MPP");
        }else if(Monitoring.getText().trim().equals("")){
            Valid.textKosong(Monitoring,"Monitoring MPP");
        }else if(Fasilitas.getText().trim().equals("")){
            Valid.textKosong(Fasilitas,"Fasilitas MPP");
        }else if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"pasien");
        }else{
            if(Sequel.menyimpantf("mpp_evaluasi_catatan","?,?,?,?,?,?,?,?,?","Catatan",9,new String[]{
                TNoRw.getText(),Valid.SetTgl(TglImplementasi.getSelectedItem()+"")+" "+TglImplementasi.getSelectedItem().toString().substring(11,19),
                Rencana.getText(),Monitoring.getText(),Fasilitas.getText(),Advokasi.getText(),Hasil.getText(),Terminasi.getText(),KdPetugas.getText()
            })==true){
                tabMode.addRow(new Object[]{
                    TNoRw.getText(),TNoRM.getText(),TPasien.getText(),Jk.getText().substring(0,1),TglLahir.getText(),AlamatLengkap.getText(),
                    Valid.SetTgl(TglImplementasi.getSelectedItem()+"")+" "+TglImplementasi.getSelectedItem().toString().substring(11,19),
                    Rencana.getText(),Monitoring.getText(),Fasilitas.getText(),Advokasi.getText(),Hasil.getText(),Terminasi.getText(),KdPetugas.getText(),NmPetugas.getText()
                });
                LCount.setText(""+tabMode.getRowCount());
                emptTeks1();
            }
        }
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnSimpan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,Rencana,BtnBatal);
        }
    }//GEN-LAST:event_BtnSimpan1KeyPressed

    private void BtnBatal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatal1ActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
//        isForm();

    }//GEN-LAST:event_BtnBatal1ActionPerformed

    private void BtnBatal1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatal1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
    }//GEN-LAST:event_BtnBatal1KeyPressed

    private void BtnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus1ActionPerformed
        if(tbObat.getSelectedRow()!= -1){
            if(akses.getkode().equals("Admin Utama")){
                hapus();
            }else{
                if(KdPetugas.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString())){
                    hapus();
                }else{
                    JOptionPane.showMessageDialog(null,"Hanya bisa dihapus oleh petugas yang bersangkutan..!!");
                }
            }
        }
    }//GEN-LAST:event_BtnHapus1ActionPerformed

    private void BtnHapus1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapus1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
    }//GEN-LAST:event_BtnHapus1KeyPressed

    private void BtnEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEdit1ActionPerformed
        if(Rencana.getText().trim().equals("")){
            Valid.textKosong(Rencana,"Rencana MPP");
        }else if(Monitoring.getText().trim().equals("")){
            Valid.textKosong(Monitoring,"Monitoring MPP");
        }else if(Fasilitas.getText().trim().equals("")){
            Valid.textKosong(Fasilitas,"Fasilitas MPP");
        }else if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"pasien");
        }else{
            if(tbObat.getSelectedRow()!= -1){
                if(akses.getkode().equals("Admin Utama")){
                    ganti();
                }else{
                    if(KdPetugas.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString())){
                        ganti();
                    }else{
                        JOptionPane.showMessageDialog(null,"Hanya bisa diganti oleh petugas yang bersangkutan..!!");
                    }
                }
            }
        }
    }//GEN-LAST:event_BtnEdit1ActionPerformed

    private void BtnEdit1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEdit1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
    }//GEN-LAST:event_BtnEdit1KeyPressed

    private void BtnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint1ActionPerformed
        if (TNoRw.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan pilih data pasien terlebih dahulu...!!!!");
        TNoRw.requestFocus();
    } else {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            // Ambil No Rawat
            String noRawat = TNoRw.getText().trim();

            // 🔥 URL ENCODE (INI PENTING)
            String encodedNoRawat = URLEncoder.encode(noRawat, StandardCharsets.UTF_8.toString());

            // URL ke PHP
            String url = "http://192.168.1.11/webapps/cetak_mpp/formb.php?no_rawat=" + encodedNoRawat;

            // Buka browser
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
            JOptionPane.showMessageDialog(null, "Gagal membuka browser: " + e.getMessage());
        } finally {
            this.setCursor(Cursor.getDefaultCursor());
        }
        }
    }//GEN-LAST:event_BtnPrint1ActionPerformed

    private void BtnPrint1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrint1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrint1KeyPressed

    private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
        TCari.setText("");
        tampil();
    }//GEN-LAST:event_BtnAll1ActionPerformed

    private void BtnAll1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            tampil();
            TCari.setText("");
        }else{
            Valid.pindah(evt, BtnCari, TPasien);
        }
    }//GEN-LAST:event_BtnAll1KeyPressed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnEdit,TCari);}
    }//GEN-LAST:event_BtnKeluar1KeyPressed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampil();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void AlamatLengkapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlamatLengkapKeyPressed
        Valid.pindah(evt,TCari,BtnPetugas);
    }//GEN-LAST:event_AlamatLengkapKeyPressed

    private void TNoRw1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRw1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            
            isRawatFormB();
        }else{
            Valid.pindah(evt,TCari,BtnPetugas);
        }
    }//GEN-LAST:event_TNoRw1KeyPressed

    private void TPasien2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasien2KeyPressed
        Valid.pindah(evt,TCari,BtnSimpan);
    }//GEN-LAST:event_TPasien2KeyPressed

    private void TNoRM2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRM2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNoRM2KeyPressed

    private void KdPetugas1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPetugas1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdPetugas1KeyPressed

    private void BtnPetugas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugas1ActionPerformed
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugas1ActionPerformed

    private void BtnPetugas1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPetugas1KeyPressed
        //Valid.pindah(evt,Monitoring,BtnSimpan);
    }//GEN-LAST:event_BtnPetugas1KeyPressed

    private void TglImplementasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglImplementasiKeyPressed
        Valid.pindah(evt,TNoRw,Rencana);
    }//GEN-LAST:event_TglImplementasiKeyPressed

    private void AdvokasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AdvokasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AdvokasiKeyPressed

    private void TerminasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TerminasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TerminasiKeyPressed

    private void RencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RencanaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RencanaKeyPressed

    private void MonitoringKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MonitoringKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MonitoringKeyPressed

    private void FasilitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FasilitasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FasilitasKeyPressed

    private void HasilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HasilKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_HasilKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
//        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMSkriningMPPFormA dialog = new RMSkriningMPPFormA(new javax.swing.JFrame(), true);
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
    private widget.TextArea Advokasi;
    private widget.TextBox Alamat;
    private widget.TextBox AlamatLengkap;
    private widget.Button BtnAll;
    private widget.Button BtnAll1;
    private widget.Button BtnBatal;
    private widget.Button BtnBatal1;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnEdit;
    private widget.Button BtnEdit1;
    private widget.Button BtnHapus;
    private widget.Button BtnHapus1;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnPetugas;
    private widget.Button BtnPetugas1;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint1;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.TextArea Fasilitas;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput1;
    private widget.PanelBiasa FormInput2;
    private widget.TextArea Hasil;
    private widget.TextBox Jk;
    private widget.TextBox Jk1;
    private widget.TextBox Kamar;
    private widget.TextBox KdDok1;
    private widget.TextBox KdDok2;
    private widget.TextBox KdDok3;
    private widget.TextBox KdDok4;
    private widget.TextBox KdDok5;
    private widget.TextBox KdPetugas;
    private widget.TextBox KdPetugas1;
    private widget.Label LCount;
    private widget.Label LCount1;
    private javax.swing.JMenuItem MnEvaluasiFormB;
    private widget.TextArea Monitoring;
    private widget.TextBox NmPetugas;
    private widget.TextBox NmPetugas1;
    private javax.swing.JPanel PanelInput;
    private widget.TextArea Rencana;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll10;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TDokter1;
    private widget.TextBox TDokter2;
    private widget.TextBox TDokter3;
    private widget.TextBox TDokter4;
    private widget.TextBox TDokter5;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRM2;
    private widget.TextBox TNoRw;
    private widget.TextBox TNoRw1;
    private widget.TextBox TPasien;
    private widget.TextBox TPasien2;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextArea Terminasi;
    private widget.Tanggal TglEvaluasi;
    private widget.Tanggal TglImplementasi;
    private widget.TextBox TglLahir;
    private widget.TextBox TglLahir1;
    private widget.TextBox TglMasuk;
    private widget.Button btnDokter1;
    private widget.Button btnDokter2;
    private widget.Button btnDokter3;
    private widget.Button btnDokter4;
    private widget.Button btnDokter5;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel3;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel4;
    private widget.Label jLabel43;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel5;
    private widget.Label jLabel50;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.Label jLabel94;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSplitPane jSplitPane1;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label14;
    private widget.Label label15;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private javax.swing.JPanel panelWrapper;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollInput1;
    private javax.swing.JScrollPane scrollMain;
    private widget.ScrollPane scrollPane11;
    private widget.ScrollPane scrollPane12;
    private widget.ScrollPane scrollPane13;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane15;
    private widget.ScrollPane scrollPane8;
    private widget.Table tbIassesmenMPP;
    private widget.Table tbIdentifikasiMPP;
    private widget.Table tbIdentifikasiMasalah;
    private widget.Table tbObat;
    private widget.Table tbObat1;
    private widget.Table tbPerencanaanMPP;
    // End of variables declaration//GEN-END:variables

  public void tampil() {
    Valid.tabelKosong(tabMode);
 
    try {
        if (TCari.getText().equals("")) {
           

            

            ps = koneksi.prepareStatement(
                "SELECT \n" +
                "    reg_periksa.no_rawat,\n" +
                "    pasien.no_rkm_medis,\n" +
                "    pasien.nm_pasien,\n" +
                "    IF(pasien.jk='L','Laki-Laki','Perempuan') AS jk,\n" +
                "    pasien.tgl_lahir,\n" +
                "    CONCAT(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',\n" +
                "           kabupaten.nm_kab,', ',propinsi.nm_prop) AS alamat,\n" +
                "    mpp_evaluasi.tanggal,\n" +
                "    IFNULL(bangsal.nm_bangsal,'Ranap Gabung') AS ruang,\n" +
                "    IFNULL(kamar_inap.kd_kamar,'RG') AS kamar,\n" +
                "    kamar_inap.tgl_masuk,\n" +
                "    kamar_inap.jam_masuk,\n" +
                "    mpp_evaluasi.kd_dokter,\n" +
                "    dokterpj.nm_dokter AS dpjp,\n" +
                "\n" +
                "    /* pakai LEFT JOIN agar tidak menghilangkan data */\n" +
                "    mpp_evaluasi.kd_konsulan,\n" +
                "    dokterkonsulen.nm_dokter AS konsulan,\n" +
                "    mpp_evaluasi.kd_konsulan2,\n" +
                "    dokterkonsulen2.nm_dokter AS konsulan2,\n" +
                "    mpp_evaluasi.kd_konsulan3,\n" +
                "    dokterkonsulen3.nm_dokter AS konsulan3,\n" +
                "    mpp_evaluasi.kd_konsulan4,\n" +
                "    dokterkonsulen4.nm_dokter AS konsulan4,\n" +
                "\n" +
                "    /* ===== SUMMARY HASIL DETAIL ===== */\n" +
                "    (SELECT GROUP_CONCAT(CONCAT(a.kode_item, ': ', a.jawaban) SEPARATOR '; ')\n" +
                "     FROM assesmen_mpp_pasien a\n" +
                "     WHERE a.no_rawat = mpp_evaluasi.no_rawat) AS assesmen,\n" +
                "\n" +
                "    (SELECT GROUP_CONCAT(CONCAT(b.kode_item, ': ', b.uraian) SEPARATOR '; ')\n" +
                "     FROM identifikasi_masalah_mpp_pasien b\n" +
                "     WHERE b.no_rawat = mpp_evaluasi.no_rawat) AS identifikasi,\n" +
                "\n" +
                "    (SELECT GROUP_CONCAT(CONCAT(c.kode_item, ': ', c.uraian) SEPARATOR '; ')\n" +
                "     FROM perencanaan_mpp_pasien c\n" +
                "     WHERE c.no_rawat = mpp_evaluasi.no_rawat) AS rencana,\n" +
                "\n" +
                "    mpp_evaluasi.nip,\n" +
                "    petugas.nama\n" +
                "\n" +
                "FROM reg_periksa\n" +
                "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis\n" +
                "INNER JOIN mpp_evaluasi ON mpp_evaluasi.no_rawat = reg_periksa.no_rawat\n" +
                "\n" +
                "LEFT JOIN kamar_inap ON reg_periksa.no_rawat = kamar_inap.no_rawat\n" +
                "LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar\n" +
                "LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal\n" +
                "\n" +
                "INNER JOIN dokter AS dokterpj ON mpp_evaluasi.kd_dokter = dokterpj.kd_dokter\n" +
                "LEFT JOIN dokter AS dokterkonsulen  ON mpp_evaluasi.kd_konsulan  = dokterkonsulen.kd_dokter\n" +
                "LEFT JOIN dokter AS dokterkonsulen2 ON mpp_evaluasi.kd_konsulan2 = dokterkonsulen2.kd_dokter\n" +
                "LEFT JOIN dokter AS dokterkonsulen3 ON mpp_evaluasi.kd_konsulan3 = dokterkonsulen3.kd_dokter\n" +
                "LEFT JOIN dokter AS dokterkonsulen4 ON mpp_evaluasi.kd_konsulan4 = dokterkonsulen4.kd_dokter\n" +
                "\n" +
                "INNER JOIN petugas ON mpp_evaluasi.nip = petugas.nip\n" +
                "INNER JOIN kelurahan ON pasien.kd_kel = kelurahan.kd_kel\n" +
                "INNER JOIN kecamatan ON pasien.kd_kec = kecamatan.kd_kec\n" +
                "INNER JOIN kabupaten ON pasien.kd_kab = kabupaten.kd_kab\n" +
                "INNER JOIN propinsi ON pasien.kd_prop = propinsi.kd_prop\n" +
                "\n" +
                "WHERE mpp_evaluasi.tanggal BETWEEN ? AND ?\n" +
                "\n" +
                "/* GROUP BY cukup per no_rawat agar summary keluar */\n" +
                "GROUP BY mpp_evaluasi.no_rawat\n" +
                "\n" +
                "ORDER BY mpp_evaluasi.tanggal"
            );
        } else {
            ps = koneksi.prepareStatement(
                "SELECT " +
                "  reg_periksa.no_rawat, " +
                "  pasien.no_rkm_medis, " +
                "  pasien.nm_pasien, " +
                "  IF(pasien.jk='L','Laki-Laki','Perempuan') AS jk, " +
                "  pasien.tgl_lahir, " +
                "  CONCAT(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) AS alamat, " +
                "  mpp_evaluasi.tanggal, " +
                "  IFNULL(bangsal.nm_bangsal,'Ranap Gabung') AS ruang, " +
                "  IFNULL(kamar_inap.kd_kamar,'RG') AS kamar, " +
                "  kamar_inap.tgl_masuk, " +
                "  kamar_inap.jam_masuk, " +
                "  mpp_evaluasi.kd_dokter, " +
                "  dokterpj.nm_dokter AS dpjp, " +
                "  mpp_evaluasi.kd_konsulan, " +
                "  dokterkonsulen.nm_dokter AS konsulan, " +
                "  mpp_evaluasi.kd_konsulan2, " +
                "  dokterkonsulen2.nm_dokter AS konsulan2, " +
                "  mpp_evaluasi.kd_konsulan3, " +
                "  dokterkonsulen3.nm_dokter AS konsulan3, " +
                "  mpp_evaluasi.kd_konsulan4, " +
                "  dokterkonsulen4.nm_dokter AS konsulan4, " +

                // ringkasan dari tabel detail
                "  IFNULL((SELECT GROUP_CONCAT(CONCAT(a.kode_item, ': ', a.jawaban) SEPARATOR '; ') " +
                "          FROM assesmen_mpp_pasien a " +
                "          WHERE a.no_rawat = mpp_evaluasi.no_rawat), '') AS assesmen, " +

                "  IFNULL((SELECT GROUP_CONCAT(CONCAT(b.kode_item, ': ', b.uraian) SEPARATOR '; ') " +
                "          FROM identifikasi_masalah_mpp_pasien b " +
                "          WHERE b.no_rawat = mpp_evaluasi.no_rawat), '') AS identifikasi, " +

                "  IFNULL((SELECT GROUP_CONCAT(CONCAT(c.kode_item, ': ', c.uraian) SEPARATOR '; ') " +
                "          FROM perencanaan_mpp_pasien c " +
                "          WHERE c.no_rawat = mpp_evaluasi.no_rawat), '') AS rencana, " +

                "  mpp_evaluasi.nip, " +
                "  petugas.nama " +
                "FROM reg_periksa " +
                "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis " +
                "INNER JOIN mpp_evaluasi ON mpp_evaluasi.no_rawat = reg_periksa.no_rawat " +
                "LEFT JOIN kamar_inap ON reg_periksa.no_rawat = kamar_inap.no_rawat " +
                "LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar " +
                "LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal " +
                "INNER JOIN dokter AS dokterpj ON mpp_evaluasi.kd_dokter = dokterpj.kd_dokter " +
                "LEFT JOIN dokter AS dokterkonsulen ON mpp_evaluasi.kd_konsulan = dokterkonsulen.kd_dokter " +
                "LEFT JOIN dokter AS dokterkonsulen2 ON mpp_evaluasi.kd_konsulan2 = dokterkonsulen2.kd_dokter " +
                "LEFT JOIN dokter AS dokterkonsulen3 ON mpp_evaluasi.kd_konsulan3 = dokterkonsulen3.kd_dokter " +
                "LEFT JOIN dokter AS dokterkonsulen4 ON mpp_evaluasi.kd_konsulan4 = dokterkonsulen4.kd_dokter " +
                "INNER JOIN petugas ON mpp_evaluasi.nip = petugas.nip " +
                "INNER JOIN kelurahan ON pasien.kd_kel = kelurahan.kd_kel " +
                "INNER JOIN kecamatan ON pasien.kd_kec = kecamatan.kd_kec " +
                "INNER JOIN kabupaten ON pasien.kd_kab = kabupaten.kd_kab " +
                "INNER JOIN propinsi ON pasien.kd_prop = propinsi.kd_prop " +
                "WHERE mpp_evaluasi.tanggal BETWEEN ? AND ? " +
                "  AND (reg_periksa.no_rawat LIKE ? " +
                "       OR pasien.no_rkm_medis LIKE ? " +
                "       OR pasien.nm_pasien LIKE ? " +
                "       OR mpp_evaluasi.nip LIKE ? " +
                "       OR petugas.nama LIKE ?) " +
                "GROUP BY mpp_evaluasi.no_rawat, mpp_evaluasi.tanggal " +
                "ORDER BY mpp_evaluasi.tanggal"
            );
        }

        try {
            if (TCari.getText().equals("")) {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
            } else {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");
                ps.setString(3, "%" + TCari.getText() + "%");
                ps.setString(4, "%" + TCari.getText() + "%");
                ps.setString(5, "%" + TCari.getText() + "%");
                ps.setString(6, "%" + TCari.getText() + "%");
                ps.setString(7, "%" + TCari.getText() + "%");
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                tabMode.addRow(new Object[]{
                    rs.getString("no_rawat"),
                    rs.getString("no_rkm_medis"),
                    rs.getString("nm_pasien"),
                    rs.getString("jk"),
                    rs.getDate("tgl_lahir"),
                    rs.getString("alamat"),
                    rs.getString("tanggal"),
                    rs.getString("kamar") + " " + rs.getString("ruang"),
                    rs.getString("tgl_masuk") + " " + rs.getString("jam_masuk"),
                    rs.getString("kd_dokter"),
                    rs.getString("dpjp"),
                    rs.getString("kd_konsulan"),
                    rs.getString("konsulan"),
                    rs.getString("kd_konsulan2"),
                    rs.getString("konsulan2"),
                    rs.getString("kd_konsulan3"),
                    rs.getString("konsulan3"),
                    rs.getString("kd_konsulan4"),
                    rs.getString("konsulan4"),
                    rs.getString("assesmen"),
                    rs.getString("identifikasi"),
                    rs.getString("rencana"),
                    rs.getString("nip"),
                    rs.getString("nama")
                });
            }
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        } finally {
            if (rs != null) {
                try { rs.close(); } catch (Exception ex) {}
            }
            if (ps != null) {
                try { ps.close(); } catch (Exception ex) {}
            }
        }
    } catch (Exception e) {
        System.out.println("Notifikasi : " + e);
    }

    LCount.setText("" + tabMode.getRowCount());
}

    public void emptTeks() {
        TglEvaluasi.setDate(new Date());
        KdDok1.setText("");
        KdDok2.setText("");
//        TDiagnosis.setText("");
//        TKelompok.setText("");
//        Skrining.setText("");
//        Assemen.setText("");
//        Identifikasi.setText("");
//        Perencanaan.setText("");
        for (i = 0; i < tabModeMasalah.getRowCount(); i++) {
            tabModeMasalah.setValueAt(false,i,0);
        }
        TabRawat.setSelectedIndex(0);
//        Assemen.requestFocus();
    } 
    public void emptTeks1() {
        TabRawat.setSelectedIndex(1);
    }

    private void getData() {
    if (tbObat.getSelectedRow() != -1) {
     
        java.util.function.Function<Object, String> safe = (value) -> value == null ? "" : value.toString();
        String noRawat = tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString();
       TNoRw.setText(noRawat);
       TNoRw1.setText(noRawat);
    
       
       loadDataRegPeriksa(noRawat);

       
        }
    }
   private void loadDataRegPeriksa(String noRawat){
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        ps = koneksi.prepareStatement(
            "SELECT reg_periksa.no_rkm_medis, pasien.nm_pasien, " +
            "IF(pasien.jk='L','Laki-Laki','Perempuan') AS jk, pasien.tgl_lahir, " +
            "CONCAT(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) AS alamat, " +
            "IFNULL(bangsal.nm_bangsal,'Ranap Gabung') AS nm_bangsal, " +
            "IFNULL(kamar_inap.kd_kamar,'RG') AS kamar, " +
            "kamar_inap.tgl_masuk, kamar_inap.jam_masuk, " +

          
            "mpp_evaluasi.kd_dokter, d1.nm_dokter AS nm_dokter1, " +
            "mpp_evaluasi.kd_konsulan, d2.nm_dokter AS nm_konsulan1, " +
            "mpp_evaluasi.kd_konsulan2, d3.nm_dokter AS nm_konsulan2, " +
            "mpp_evaluasi.kd_konsulan3, d4.nm_dokter AS nm_konsulan3, " +
            "mpp_evaluasi.kd_konsulan4, d5.nm_dokter AS nm_konsulan4, " +

           
            "mpp_evaluasi.nip, pt.nama AS nm_petugas " +

            "FROM reg_periksa " +
            "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis " +
            "INNER JOIN kelurahan ON pasien.kd_kel = kelurahan.kd_kel " +
            "INNER JOIN kecamatan ON pasien.kd_kec = kecamatan.kd_kec " +
            "INNER JOIN kabupaten ON pasien.kd_kab = kabupaten.kd_kab " +
            "INNER JOIN propinsi ON pasien.kd_prop = propinsi.kd_prop " +
            "LEFT JOIN kamar_inap ON reg_periksa.no_rawat = kamar_inap.no_rawat " +
            "LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar " +
            "LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal " +
            "LEFT JOIN mpp_evaluasi ON mpp_evaluasi.no_rawat = reg_periksa.no_rawat " +
            "LEFT JOIN dokter d1 ON d1.kd_dokter = mpp_evaluasi.kd_dokter " +
            "LEFT JOIN dokter d2 ON d2.kd_dokter = mpp_evaluasi.kd_konsulan " +
            "LEFT JOIN dokter d3 ON d3.kd_dokter = mpp_evaluasi.kd_konsulan2 " +
            "LEFT JOIN dokter d4 ON d4.kd_dokter = mpp_evaluasi.kd_konsulan3 " +
            "LEFT JOIN dokter d5 ON d5.kd_dokter = mpp_evaluasi.kd_konsulan4 " +
            "LEFT JOIN petugas pt ON pt.nip = mpp_evaluasi.nip " +
            "WHERE reg_periksa.no_rawat = ?"
        );

        ps.setString(1, noRawat);
        rs = ps.executeQuery();

        if(rs.next()){
           
            TNoRM.setText(rs.getString("no_rkm_medis"));
            TPasien.setText(rs.getString("nm_pasien"));
            Jk.setText(rs.getString("jk"));
            TglLahir.setText(rs.getString("tgl_lahir"));
            Alamat.setText(rs.getString("alamat"));
            Kamar.setText(rs.getString("kamar")+" "+rs.getString("nm_bangsal"));
            TglMasuk.setText(rs.getString("tgl_masuk")+" "+rs.getString("jam_masuk"));

            KdDok1.setText(rs.getString("kd_dokter"));
            TDokter1.setText(rs.getString("nm_dokter1"));

            KdDok2.setText(rs.getString("kd_konsulan"));
            TDokter2.setText(rs.getString("nm_konsulan1"));

            KdDok3.setText(rs.getString("kd_konsulan2"));
            TDokter3.setText(rs.getString("nm_konsulan2"));

            KdDok4.setText(rs.getString("kd_konsulan3"));
            TDokter4.setText(rs.getString("nm_konsulan3"));

            KdDok5.setText(rs.getString("kd_konsulan4"));
            TDokter5.setText(rs.getString("nm_konsulan4"));

            KdPetugas.setText(rs.getString("nip"));
            NmPetugas.setText(rs.getString("nm_petugas"));

           
            TNoRM2.setText(rs.getString("no_rkm_medis"));
            TPasien2.setText(rs.getString("nm_pasien"));
            Jk1.setText(rs.getString("jk"));
            TglLahir1.setText(rs.getString("tgl_lahir"));
            AlamatLengkap.setText(rs.getString("alamat"));

            KdPetugas1.setText(rs.getString("nip"));
            NmPetugas1.setText(rs.getString("nm_petugas"));
        }

    } catch (Exception e) {
        System.out.println("Notif loadDataRegPeriksa : "+e);
    } finally {
        try {
            if(rs!=null) rs.close();
            if(ps!=null) ps.close();
        } catch (Exception e) {
            System.out.println("Close Error : "+e);
        }
    }
}


    private void isRawat() {
        
        try {
          ps = koneksi.prepareStatement(
            "SELECT reg_periksa.no_rkm_medis, pasien.nm_pasien, " +
            "IF(pasien.jk='L','Laki-Laki','Perempuan') AS jk, pasien.tgl_lahir, " +
            "reg_periksa.tgl_registrasi, " +
            "CONCAT(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop) AS alamat, " +
            "IFNULL(bangsal.nm_bangsal,'Ranap Gabung') AS nm_bangsal, " +
            "IFNULL(kamar_inap.kd_kamar,'RG') AS kamar, " +
            "kamar_inap.tgl_masuk, kamar_inap.jam_masuk, " +

        
            "mpp_evaluasi.kd_dokter, d1.nm_dokter AS nm_dokter1, " +
            "mpp_evaluasi.kd_konsulan, d2.nm_dokter AS nm_konsulan1, " +
            "mpp_evaluasi.kd_konsulan2, d3.nm_dokter AS nm_konsulan2, " +
            "mpp_evaluasi.kd_konsulan3, d4.nm_dokter AS nm_konsulan3, " +
            "mpp_evaluasi.kd_konsulan4, d5.nm_dokter AS nm_konsulan4, " +

         
            "mpp_evaluasi.nip, pt.nama AS nm_petugas " +

            "FROM reg_periksa " +
            "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis " +
            "INNER JOIN kelurahan ON pasien.kd_kel = kelurahan.kd_kel " +
            "INNER JOIN kecamatan ON pasien.kd_kec = kecamatan.kd_kec " +
            "INNER JOIN kabupaten ON pasien.kd_kab = kabupaten.kd_kab " +
            "INNER JOIN propinsi ON pasien.kd_prop = propinsi.kd_prop " +

            "LEFT JOIN kamar_inap ON reg_periksa.no_rawat = kamar_inap.no_rawat " +
            "LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar " +
            "LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal " +

          
            "LEFT JOIN mpp_evaluasi ON mpp_evaluasi.no_rawat = reg_periksa.no_rawat " +

           
            "LEFT JOIN dokter d1 ON d1.kd_dokter = mpp_evaluasi.kd_dokter " +
            "LEFT JOIN dokter d2 ON d2.kd_dokter = mpp_evaluasi.kd_konsulan " +
            "LEFT JOIN dokter d3 ON d3.kd_dokter = mpp_evaluasi.kd_konsulan2 " +
            "LEFT JOIN dokter d4 ON d4.kd_dokter = mpp_evaluasi.kd_konsulan3 " +
            "LEFT JOIN dokter d5 ON d5.kd_dokter = mpp_evaluasi.kd_konsulan4 " +

          
            "LEFT JOIN petugas pt ON pt.nip = mpp_evaluasi.nip " +

            "WHERE reg_periksa.no_rawat = ? " +
            "GROUP BY reg_periksa.no_rawat"
        );

            try {
                ps.setString(1,TNoRw.getText());
                rs=ps.executeQuery();
                if(rs.next()){
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    TPasien.setText(rs.getString("nm_pasien"));
//                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    Jk.setText(rs.getString("jk"));
                    TglLahir.setText(rs.getString("tgl_lahir"));
                    Alamat.setText(rs.getString("alamat"));
                    TglMasuk.setText(rs.getString("tgl_masuk")+" "+rs.getString("jam_masuk"));
                    Kamar.setText(rs.getString("kamar")+" "+rs.getString("nm_bangsal"));
                     KdDok1.setText(rs.getString("kd_dokter"));
                    KdPetugas.setText(rs.getString("nip"));
                    KdDok2.setText(rs.getString("kd_konsulan"));
                    KdDok3.setText(rs.getString("kd_konsulan2"));
                    KdDok4.setText(rs.getString("kd_konsulan3"));
                    KdDok5.setText(rs.getString("kd_konsulan4"));
                    
                    TDokter1.setText(rs.getString("nm_dokter1"));

                    TDokter2.setText(rs.getString("nm_konsulan1"));
                    TDokter3.setText(rs.getString("nm_konsulan2"));
                    TDokter4.setText(rs.getString("nm_konsulan3"));
                    TDokter5.setText(rs.getString("nm_konsulan4"));

                    NmPetugas.setText(rs.getString("nm_petugas"));

                }
            } catch (Exception e) {
                System.out.println("Notif0 : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif1 : "+e);
        }
    }
    private void isRawatFormB() {
       
        try {
            ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rkm_medis,reg_periksa.jam_reg,reg_periksa.no_rawat,pasien.nm_pasien, if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,reg_periksa.tgl_registrasi,"+
                    "concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab,', ',propinsi.nm_prop)as alamat "+
                    "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel "+
                    "inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec "+
                    "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab " +
                    "inner join propinsi on pasien.kd_prop=propinsi.kd_prop "+
                    "where reg_periksa.no_rawat=?");
            try {
                ps.setString(1,TNoRw.getText());
                rs=ps.executeQuery();
                if(rs.next()){
                   TNoRw1.setText(rs.getString("no_rawat"));
                    TNoRM2.setText(rs.getString("no_rkm_medis"));
                    TPasien2.setText(rs.getString("nm_pasien"));
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    Jk1.setText(rs.getString("jk"));
                    TglLahir1.setText(rs.getString("tgl_lahir"));
                    AlamatLengkap.setText(rs.getString("alamat"));
                    TglMasuk.setText(rs.getString("tgl_registrasi")+" "+rs.getString("jam_reg"));
                    
                }
            } catch (Exception e) {
                System.out.println("Notif error : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif error 1: "+e);
        }
    }
    public void setNoRm(String norwt, Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(tgl2);    
        isRawat(); 
        isRawatFormB();
    }
    
    
public void isCek() {
    BtnSimpan.setEnabled(akses.getmpp_skrining());
    BtnHapus.setEnabled(akses.getmpp_skrining());
    BtnEdit.setEnabled(akses.getmpp_skrining());
    BtnPrint.setEnabled(akses.getmpp_skrining());

    
    if (akses.getjml2() >= 1) {
        String kd = akses.getkode();
        String nama = petugas.tampil4(kd);

        System.out.println("KD: " + kd + " | Nama: " + nama);

        SwingUtilities.invokeLater(() -> {
            KdPetugas.setEditable(false);
            BtnPetugas.setEnabled(false);
            KdPetugas.setText(kd);
            NmPetugas.setText(nama);
            
             KdPetugas1.setEditable(false);
            BtnPetugas1.setEnabled(false);
            KdPetugas1.setText(kd);
            NmPetugas1.setText(nama);
        });
    }
}


    public void setTampil(){
       TabRawat.setSelectedIndex(1);
    }
    
private void tampilMasalah() {
    try {
        Valid.tabelKosong(tabModeMasalah);

        ps = koneksi.prepareStatement(
            "SELECT m.kode_masalah, m.nama_masalah, " +
            "IF(e.kode_masalah IS NULL, 0, 1) AS dipilih " +
            "FROM master_masalah_mpp m " +
            "LEFT JOIN mpp_evaluasi_masalah e " +
            "  ON e.kode_masalah = m.kode_masalah " +
            " AND e.no_rawat = ? " +
            "ORDER BY m.kode_masalah"
        );

        ps.setString(1, TNoRw.getText().trim());
        rs = ps.executeQuery();

        while (rs.next()) {
            tabModeMasalah.addRow(new Object[]{
                rs.getInt("dipilih") == 1,   // 🔥 AUTO TER-CENTANG
                rs.getString("kode_masalah"),
                rs.getString("nama_masalah")
            });
        }

    } catch (Exception e) {
        System.out.println("Notifikasi tampilMasalah : " + e);
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        } catch (Exception e) {}
    }
}


  private void tampilAsesmen(String norawat) {
    try {
        Valid.tabelKosong(tabModeAsesmen);
        mapOpsiAsesmen.clear();
        mapJenisInputAsesmen.clear();

       
        ps = koneksi.prepareStatement(
            "SELECT m.kode_item, m.nama_item, m.jenis_input, " +
            "       IFNULL(a.jawaban,'') AS jawaban " +
            "FROM master_assesmen_mpp_item m " +
            "LEFT JOIN assesmen_mpp_pasien a " +
            "  ON a.kode_item = m.kode_item AND a.no_rawat = ? " +
            "WHERE m.aktif = 'Y' " +
            "ORDER BY m.no_urut"
        );
        ps.setString(1, norawat);
        rs = ps.executeQuery();
        while (rs.next()) {
            String kodeItem    = rs.getString("kode_item");
            String namaItem    = rs.getString("nama_item");
            String jenisInput  = rs.getString("jenis_input");   
            String jawaban     = rs.getString("jawaban");      

            
            mapJenisInputAsesmen.put(kodeItem, jenisInput);

          
            tabModeAsesmen.addRow(new Object[]{
                false,
                kodeItem,
                namaItem,
                jawaban       
            });
        }
        rs.close();
        ps.close();

        
        ps = koneksi.prepareStatement(
            "SELECT o.kode_item, o.nama_opsi " +
            "FROM master_assesmen_mpp_opsi o " +
            "JOIN master_assesmen_mpp_item m ON m.kode_item = o.kode_item " +
            "WHERE o.aktif = 'Y' AND m.aktif = 'Y' AND m.jenis_input = 'pilihan' " +
            "ORDER BY o.kode_item, o.urut"
        );
        rs = ps.executeQuery();
        while (rs.next()) {
            String kodeItem = rs.getString("kode_item");
            String namaOpsi = rs.getString("nama_opsi");

            java.util.List<String> list = mapOpsiAsesmen.get(kodeItem);
            if (list == null) {
                list = new java.util.ArrayList<>();
                mapOpsiAsesmen.put(kodeItem, list);
            }
            list.add(namaOpsi);
        }
        rs.close();
        ps.close();

    } catch (Exception e) {
        System.out.println("Notifikasi Asesmen : " + e);
    }
}

 private void tampilPerencanaan(String norawat) {
    try {
        Valid.tabelKosong(tabModePerencanaan);
        mapTipePerencanaan.clear();

        ps = koneksi.prepareStatement(
            "SELECT m.kode_item, m.nama_item, m.tipe, " +
            "       IFNULL(p.dipilih,'N') AS dipilih, " +  // Y/N
            "       IFNULL(p.uraian,'') AS uraian " +      // uraian
            "FROM master_perencanaan_mpp_item m " +
            "LEFT JOIN perencanaan_mpp_pasien p " +
            "  ON p.kode_item = m.kode_item AND p.no_rawat = ? " +
            "WHERE m.aktif = 'Y' " +
            "ORDER BY m.urut"
        );

        ps.setString(1, norawat);
        rs = ps.executeQuery();

        while (rs.next()) {

            boolean checked = rs.getString("dipilih").equals("Y");

            tabModePerencanaan.addRow(new Object[]{
                checked,                           // checkbox
                rs.getString("kode_item"),         // hidden
                rs.getString("nama_item"),         // nama item
                rs.getString("uraian")             // uraian tersimpan
            });

            
            mapTipePerencanaan.put(
                rs.getString("kode_item"),
                rs.getString("tipe")
            );
        }

        rs.close();
        ps.close();

    } catch (Exception e) {
        System.out.println("Notifikasi tampilPerencanaan : " + e);
    }
}

    

private void tampilIdentifikasiMasalah(String norawat) {
    try {
        Valid.tabelKosong(tabModeIdentifikasiMasalah);

        ps = koneksi.prepareStatement(
            "SELECT m.kode_item, m.nama_item, " +
            "       IFNULL(p.uraian,'') AS uraian " +
            "FROM master_identifikasi_masalah_mpp_item m " +
            "LEFT JOIN identifikasi_masalah_mpp_pasien p " +
            "  ON p.kode_item = m.kode_item AND p.no_rawat = ? " +
            "ORDER BY m.urut"
        );
        ps.setString(1, norawat);
        rs = ps.executeQuery();
        while (rs.next()) {

            // opsi 1: default belum dicentang
            boolean checked = false;

         

            tabModeIdentifikasiMasalah.addRow(new Object[]{
                checked,                          // kolom P (Boolean)
                rs.getString("kode_item"),        // Kode (hidden)
                rs.getString("nama_item"),        // Jenis Masalah
                rs.getString("uraian")            // Uraian
            });
        }
        rs.close();
        ps.close();
    } catch (Exception e) {
        System.out.println("Notifikasi tampilIdentifikasiMasalah : " + e);
    }
}

    private void isMenu(){
//        if(ChkAccor.isSelected()==true){
//            ChkAccor.setVisible(false);
//            PanelAccor.setPreferredSize(new Dimension(470,HEIGHT));
//            FormMenu.setVisible(true);  
//            FormMasalahRencana.setVisible(true);  
//            ChkAccor.setVisible(true);
//        }else if(ChkAccor.isSelected()==false){   
//            ChkAccor.setVisible(false);
//            PanelAccor.setPreferredSize(new Dimension(15,HEIGHT));
//            FormMenu.setVisible(false);  
//            FormMasalahRencana.setVisible(false);   
//            ChkAccor.setVisible(true);
//        }
    }

//    private void getDataMPP() {
////    System.out.println(">>> getMasalah terpanggil <<<");
//
//    if(tbObat.getSelectedRow()!= -1){
//            TNoRM1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
//            TPasien1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()); 
//            DetailRencana.setText(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
//            try {
//                Valid.tabelKosong(tabModeDetailMasalah);
//                ps=koneksi.prepareStatement(
//                        "select master_masalah_mpp.kode_masalah,master_masalah_mpp.nama_masalah from master_masalah_mpp "+
//                        "inner join mpp_evaluasi_masalah on mpp_evaluasi_masalah.kode_masalah=master_masalah_mpp.kode_masalah "+
//                        "where mpp_evaluasi_masalah.no_rawat=? and mpp_evaluasi_masalah.tanggal=? order by master_masalah_mpp.kode_masalah");
//                try {
//                    ps.setString(1,tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
//                    ps.setString(2,tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
//                    rs=ps.executeQuery();
//                    while(rs.next()){
//                        tabModeDetailMasalah.addRow(new Object[]{rs.getString(1),rs.getString(2)});
//                    }
//                } catch (Exception e) {
//                    System.out.println("Notif : "+e);
//                } finally{
//                    if(rs!=null){
//                        rs.close();
//                    }
//                    if(ps!=null){
//                        ps.close();
//                    }
//                }
//            } catch (Exception e) {
//                System.out.println("Notif : "+e);
//            }
//        }
//}
private void getMasalah() {
    if (tbObat.getSelectedRow() != -1) {
//        TNoRM1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString());
//        TPasien1.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
//        DetailRencana.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 21).toString());
        try {
            Valid.tabelKosong(tabModeDetailMasalah);
            ps = koneksi.prepareStatement(
                "SELECT master_masalah_mpp.kode_masalah, master_masalah_mpp.nama_masalah " +
                "FROM master_masalah_mpp " +
                "INNER JOIN mpp_evaluasi_masalah ON mpp_evaluasi_masalah.kode_masalah = master_masalah_mpp.kode_masalah " +
                "WHERE mpp_evaluasi_masalah.no_rawat = ? AND mpp_evaluasi_masalah.tanggal = ? " +
                "ORDER BY master_masalah_mpp.kode_masalah"
            );
            ps.setString(1, tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            ps.setString(2, tbObat.getValueAt(tbObat.getSelectedRow(), 6).toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                tabModeDetailMasalah.addRow(new Object[]{rs.getString(1), rs.getString(2)});
            }
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ex) {}
            try { if (ps != null) ps.close(); } catch (Exception ex) {}
        }

        // isi tabModeMasalah
        try {
            Valid.tabelKosong(tabModeMasalah);
            ps = koneksi.prepareStatement("SELECT kode_masalah, nama_masalah FROM master_masalah_mpp ORDER BY kode_masalah");
            rs = ps.executeQuery();
            while (rs.next()) {
                String kode = rs.getString("kode_masalah");
                String nama = rs.getString("nama_masalah");

                PreparedStatement psCek = koneksi.prepareStatement(
                    "SELECT COUNT(*) FROM mpp_evaluasi_masalah WHERE no_rawat=? AND tanggal=? AND kode_masalah=?"
                );
                psCek.setString(1, tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
                psCek.setString(2, tbObat.getValueAt(tbObat.getSelectedRow(), 6).toString());
                psCek.setString(3, kode);
                ResultSet rsCek = psCek.executeQuery();

                boolean ada = false;
                if (rsCek.next()) {
                    ada = rsCek.getInt(1) > 0;
                }

                tabModeMasalah.addRow(new Object[]{ada, kode, nama});

                rsCek.close();
                psCek.close();
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("Notif getMasalah : " + e);
        }
    }
}

    private void hapus() {
        if(Sequel.queryu2tf("delete from mpp_evaluasi where no_rawat=? and tanggal=?",2,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()
        })==true){
//            TNoRM1.setText("");
//            TPasien1.setText("");
            Sequel.meghapus("mpp_evaluasi_masalah","no_rawat","tanggal",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            Valid.tabelKosong(tabModeDetailMasalah);
//            ChkAccor.setSelected(false);
            isMenu();
            tabMode.removeRow(tbObat.getSelectedRow());
            LCount.setText(""+tabMode.getRowCount());
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }

  private void ganti() {
    if (tbObat.getSelectedRow() < 0) {
        JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data terlebih dahulu..!!");
        return;
    }

    String dok3 = KdDok3.getText().trim().equals("") ? null : KdDok3.getText().trim();
    String dok4 = KdDok4.getText().trim().equals("") ? null : KdDok4.getText().trim();
    String dok5 = KdDok5.getText().trim().equals("") ? null : KdDok5.getText().trim();

    try {
        String tglEval = Valid.SetTgl(TglEvaluasi.getSelectedItem() + "") + " " +
                         TglEvaluasi.getSelectedItem().toString().substring(11, 19);

        String kdKons1 = KdDok2.getText().trim().equals("") ? "NULL" : "'" + KdDok2.getText().trim() + "'";
        String kdKons2 = (dok3 == null) ? "NULL" : "'" + dok3 + "'";
        String kdKons3 = (dok4 == null) ? "NULL" : "'" + dok4 + "'";
        String kdKons4 = (dok5 == null) ? "NULL" : "'" + dok5 + "'";

        Sequel.meghapus("mpp_evaluasi", "no_rawat", TNoRw.getText().trim());

        String sqlValue =
            "'" + TNoRw.getText().trim() + "'," +
            "'" + tglEval + "'," +
            "'" + KdDok1.getText().trim() + "'," +
            kdKons1 + "," +
            kdKons2 + "," +
            kdKons3 + "," +
            kdKons4 + "," +
            "'' ," +
            "'' ," +
            "'' ," +
            "'" + KdPetugas.getText().trim() + "'";

       if (!Sequel.menyimpantf("mpp_evaluasi", sqlValue, "No.Rawat")) {
    JOptionPane.showMessageDialog(
        null,
        "Gagal menyimpan Evaluasi MPP.\nPeriksa data dokter / struktur tabel."
    );
    System.out.println("SQL VALUE : " + sqlValue);
    return;
}

      
        Sequel.meghapus("mpp_evaluasi_masalah", "no_rawat", TNoRw.getText().trim());
        Valid.tabelKosong(tabModeDetailMasalah);

        for (int i = 0; i < tbIdentifikasiMPP.getRowCount(); i++) {
                String kode = tbIdentifikasiMPP.getValueAt(i, 1) == null ? "" :
                              tbIdentifikasiMPP.getValueAt(i, 1).toString();

                if (!kode.equals("")) {
                    Sequel.menyimpan2(
                        "mpp_evaluasi_masalah",
                        "?,?,?",
                        3,
                        new String[]{
                            TNoRw.getText().trim(),
                            tglEval,
                            kode
                        }
                    );
                }
            }

   
        Sequel.meghapus("assesmen_mpp_pasien", "no_rawat", TNoRw.getText().trim());

        for (int r = 0; r < tbIassesmenMPP.getRowCount(); r++) {
            boolean dipilih = Boolean.TRUE.equals(tbIassesmenMPP.getValueAt(r, 0));
            String kode = tbIassesmenMPP.getValueAt(r, 1).toString();
            String jawaban = tbIassesmenMPP.getValueAt(r, 3) == null ? "" :
                             tbIassesmenMPP.getValueAt(r, 3).toString();

            if (dipilih || !jawaban.equals("")) {
                Sequel.menyimpan2(
                    "assesmen_mpp_pasien",
                    "?,?,?,?,?",
                    5,
                    new String[]{
                        TNoRw.getText().trim(),
                        kode,
                        jawaban,
                        tglEval,
                        KdPetugas.getText().trim()
                    }
                );
            }
        }

       
       Sequel.meghapus("identifikasi_masalah_mpp_pasien", "no_rawat", TNoRw.getText().trim());

        for (int r = 0; r < tbIdentifikasiMasalah.getRowCount(); r++) {
            boolean dipilih = Boolean.TRUE.equals(tbIdentifikasiMasalah.getValueAt(r, 0));

            String kodeItem = tbIdentifikasiMasalah.getValueAt(r, 1) == null ? "" :
                              tbIdentifikasiMasalah.getValueAt(r, 1).toString();

            String uraian = tbIdentifikasiMasalah.getValueAt(r, 3) == null ? "" :
                            tbIdentifikasiMasalah.getValueAt(r, 3).toString().trim();

            if (dipilih && !uraian.equals("")) {
                Sequel.menyimpan2(
                    "identifikasi_masalah_mpp_pasien",
                    "?,?,?,?,?",
                    5,
                    new String[]{
                        TNoRw.getText().trim(),
                        kodeItem,
                        uraian,
                        tglEval,
                        KdPetugas.getText().trim()
                    }
                );
            }
        }



        Sequel.meghapus("perencanaan_mpp_pasien", "no_rawat", TNoRw.getText().trim());

        for (int r = 0; r < tbPerencanaanMPP.getRowCount(); r++) {
            boolean dipilih = Boolean.TRUE.equals(tbPerencanaanMPP.getValueAt(r, 0));
            String kode = tbPerencanaanMPP.getValueAt(r, 1).toString();
            String ket = tbPerencanaanMPP.getValueAt(r, 3) == null ? "" :
                         tbPerencanaanMPP.getValueAt(r, 3).toString();

            if (dipilih || !ket.equals("")) {
                Sequel.menyimpan2(
                    "perencanaan_mpp_pasien",
                    "?,?,?,?,?,?",
                    6,
                    new String[]{
                        TNoRw.getText().trim(),
                        kode,
                        dipilih ? "Y" : "N",
                        ket,
                        tglEval,
                        KdPetugas.getText().trim()
                    }
                );
            }
        }

        int row = tbObat.getSelectedRow();
        tbObat.setValueAt(TNoRw.getText(), row, 0);
        tbObat.setValueAt(Valid.SetTgl(tglEval), row, 6);
        tbObat.setValueAt(KdDok1.getText(), row, 9);
        tbObat.setValueAt(KdPetugas.getText(), row, 22);
        tbObat.setValueAt(NmPetugas.getText(), row, 23);

        TabRawat.setSelectedIndex(1);
        emptTeks();

    } catch (Exception e) {
        System.out.println("Notifikasi ganti MPP : " + e);
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengubah data MPP.");
    }
}

   private void tampilFormB(String noRawat) {
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        String sql = "SELECT rencana, monitoring, fasilitas, advokasi, hasil, terminasi " +
                     "FROM mpp_evaluasi_catatan " +
                     "WHERE no_rawat = ?";

        ps = koneksi.prepareStatement(sql);
        ps.setString(1, noRawat);
        rs = ps.executeQuery();

        if (rs.next()) {
            Rencana.setText(rs.getString("rencana"));
            Monitoring.setText(rs.getString("monitoring"));
            Fasilitas.setText(rs.getString("fasilitas"));
            Advokasi.setText(rs.getString("advokasi"));
            Hasil.setText(rs.getString("hasil"));
            Terminasi.setText(rs.getString("terminasi"));
        } else {
            // jika data tidak ada → kosongkan form
            Rencana.setText("");
            Monitoring.setText("");
            Fasilitas.setText("");
            Advokasi.setText("");
            Hasil.setText("");
            Terminasi.setText("");
        }

    } catch (Exception e) {
        System.out.println("Error tampilFormB : " + e);
    } finally {
        if (rs != null) {
            try { rs.close(); } catch (Exception e) {}
        }
        if (ps != null) {
            try { ps.close(); } catch (Exception e) {}
        }
    }
}

    private class EditorJawabanAsesmen
            extends javax.swing.DefaultCellEditor
            implements javax.swing.table.TableCellEditor {

        private final javax.swing.JComboBox<String> combo;

        public EditorJawabanAsesmen() {
            super(new javax.swing.JComboBox<String>());
            combo = (javax.swing.JComboBox<String>) getComponent();
        }

        @Override
        public java.awt.Component getTableCellEditorComponent(
                javax.swing.JTable table, Object value,
                boolean isSelected, int row, int column) {

              combo.removeAllItems();

        // kolom 1 = kode_item (hidden)
        String kodeItem = (String) table.getValueAt(row, 1);
        java.util.List<String> opsi = mapOpsiAsesmen.get(kodeItem);

        if (opsi != null && !opsi.isEmpty()) {
            // PAKAI DROPDOWN BIASA
            combo.setEditable(false);
            for (String o : opsi) {
                combo.addItem(o);
            }
        } else {
            // TIDAK ADA OPSI → FREE TEXT
            combo.setEditable(true);
            // tidak perlu addItem apa-apa, biarkan user ketik sendiri
        }

        // set nilai awal kalau sudah pernah diisi
        if (value != null && !value.toString().trim().isEmpty()) {
            combo.setSelectedItem(value.toString());
        } else if (combo.isEditable()) {
            combo.setSelectedItem(""); // kosong untuk free text
        }

        return combo; 
        }
    }
}