package simrskhanza;
import kepegawaian.DlgCariDokter;
import kepegawaian.DlgCariPetugas;
import keuangan.DlgJnsPerawatanOperasi;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import keuangan.Jurnal;
import rekammedis.MasterCariTemplateLaporanOperasi;

public class DlgTagihanOperasi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode,tabMode2,tabModeDetailBHP;
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Jurnal jur=new Jurnal();
    private Connection koneksi=koneksiDB.condb();
    private PreparedStatement pstindakan,pstindakan2,pstindakan3,pstindakan4,psobat,psset_tarif,psrekening;
    private ResultSet rs,rsset_tarif,rsrekening;
    private DlgCariPetugas petugas=new DlgCariPetugas( null,false);
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private String kelas_operasi="Yes",kelas="",cara_bayar_operasi="Yes",kd_pj="",status="";
    private double ttljmdokter=0,ttljmpetugas=0,ttlpendapatan=0,ttlbhp=0;
    private String Suspen_Piutang_Operasi_Ranap="",Operasi_Ranap="",Beban_Jasa_Medik_Dokter_Operasi_Ranap="",Utang_Jasa_Medik_Dokter_Operasi_Ranap="",
            Beban_Jasa_Medik_Paramedis_Operasi_Ranap="",Utang_Jasa_Medik_Paramedis_Operasi_Ranap="",HPP_Obat_Operasi_Ranap="",Persediaan_Obat_Kamar_Operasi_Ranap="",
            Suspen_Piutang_Operasi_Ralan="",Operasi_Ralan="",Beban_Jasa_Medik_Dokter_Operasi_Ralan="",Utang_Jasa_Medik_Dokter_Operasi_Ralan="",
            Beban_Jasa_Medik_Paramedis_Operasi_Ralan="",Utang_Jasa_Medik_Paramedis_Operasi_Ralan="",HPP_Obat_Operasi_Ralan="",Persediaan_Obat_Kamar_Operasi_Ralan="",
            norawatibu="";
    private double y=0,biayatindakan=0,biayaobat=0;
    private int jml=0,pilihan=0,i=0,index=0;
    private boolean[] pilih; 
    private boolean sukses=true;
    private String[] kode_paket, nm_perawatan,kategori,kd_obat,nm_obat, satuan;
    private double[] operator1, operator2, operator3, asisten_operator1, asisten_operator2,asisten_operator3,dokter_pjanak,dokter_umum,
                  instrumen, dokter_anak, perawaat_resusitas, dokter_anestesi, asisten_anestesi,asisten_anestesi2, bidan,bidan2,bidan3, 
                  perawat_luar, sewa_ok, alat,akomodasi,bagian_rs,omloop,omloop2,omloop3,omloop4,omloop5,sarpras,ttltindakan,jmlobat,hargasatuan,ttlobat;

    /** Creates new form DlgProgramStudi
     * @param parent
     * @param modal*/
    public DlgTagihanOperasi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
   
//        label18.setVisible(false);
//        nmasistoperator2.setVisible(false);
//        kdasistoperator2.setVisible(false);
//        label35.setVisible(false);
//        kdasistoperator3.setVisible(false);
//        btnAsis2.setVisible(false);
//        btnAsis4.setVisible(false);
//        nmasistoperator3.setVisible(false);
//        
//        label26.setVisible(false);
//        kdasistanestesi.setVisible(false);
//        nmasistanestesi.setVisible(false);
//        btnAsis4.setVisible(false);
//        
//        label36.setVisible(false);
//        kdasistanestesi2.setVisible(false);
//        nmasistanestesi2.setVisible(false);
//        BtnAsnes1.setVisible(false);
//        
//         BtnAsnes.setVisible(false);
//        label24.setVisible(false);
//        kdprwresust.setVisible(false);
//        nmprwresust.setVisible(false);
//        btnPrwRes.setVisible(false);
//        
//        label24.setVisible(false);
//        kdprwresust.setVisible(false);
//        nmprwresust.setVisible(false);
//        btnPrwRes.setVisible(false);
//        
//        label25.setVisible(false);
//        kdonloop1.setVisible(false);
//        nmonloop1.setVisible(false);
//        btnOnloop1.setVisible(false);
//        
//        label31.setVisible(false);
//        kdonloop2.setVisible(false);
//        nmonloop2.setVisible(false);
//        btnOnloop2.setVisible(false);
//        
//        label32.setVisible(false);
//        kdonloop3.setVisible(false);
//        nmonloop3.setVisible(false);
//        btnOnloop3.setVisible(false);
//        
//           label37.setVisible(false);
//        kdonloop4.setVisible(false);
//        nmonloop4.setVisible(false);
//        btnOnloop4.setVisible(false);
//        
//           label38.setVisible(false);
//        kdonloop5.setVisible(false);
//        nmonloop5.setVisible(false);
//        btnOnloop5.setVisible(false);
//        
//        
//        label27.setVisible(false);
//        kdbidan.setVisible(false);
//        nmbidan.setVisible(false);
//        btnBidan.setVisible(false);
//        
//        label29.setVisible(false);
//        kdbidan2.setVisible(false);
//        nmbidan2.setVisible(false);
//        btnBidan2.setVisible(false);
//        
//        label30.setVisible(false);
//        kdbidan3.setVisible(false);
//        nmbidan3.setVisible(false);
//        btnBidan3.setVisible(false);
//        
//        jLabel8.setText("Insisi kulit & pembukaan  lapangan operasi :");
//    
//       
//        int ySetelahDrAnak = label22.getY() + label22.getHeight() + 10;   // jarak 10px di bawah dr Anak
//        int shiftLeft = label28.getY() - ySetelahDrAnak;
//
//       
//        geserKeAtas(label28,     shiftLeft);
//        geserKeAtas(kdprwluar,   shiftLeft);
//        geserKeAtas(nmprwluar,   shiftLeft);
//        geserKeAtas(btnPrwLuar,  shiftLeft);
//
//     
//        geserKeAtas(label23,      shiftLeft);
//        geserKeAtas(kdInstrumen,  shiftLeft);
//        geserKeAtas(nminstrumen,  shiftLeft);
//        geserKeAtas(btnAsis3,     shiftLeft);
//
//        geserKeAtas(label33,      shiftLeft);
//        geserKeAtas(kdpjanak,     shiftLeft);
//        geserKeAtas(nmpjanak,     shiftLeft);
//        geserKeAtas(btndrpjanak,  shiftLeft);
//
//        geserKeAtas(label34,      shiftLeft);
//        geserKeAtas(kddrumum,     shiftLeft);
//        geserKeAtas(nmdrumum,     shiftLeft);
//        geserKeAtas(btndrumum,    shiftLeft);
//
//
//      
//        int ySetelahPrwLuar = label28.getY() + label28.getHeight() + 10; 
//        int shiftDiag = jLabel6.getY() - ySetelahPrwLuar;
//
//        geserKeAtas(jLabel6,   shiftLeft);
//        geserKeAtas(PreOp,     shiftLeft);
//
//        geserKeAtas(jLabel7,   shiftLeft);
//        geserKeAtas(PostOp,    shiftLeft);
//
//        geserKeAtas(jLabel8,   shiftLeft);
//        geserKeAtas(Jaringan,  shiftLeft);
//
//        geserKeAtas(jLabel9,   shiftLeft);
//        geserKeAtas(DikirimPA, shiftLeft);
//
//
//      
//        int ySetelahKruOK = label17.getY() + label17.getHeight() + 10;
//        int shiftRight = label12.getY() - ySetelahKruOK;
//
//        geserKeAtas(label12,     shiftRight);   // "Selesai :"
//        geserKeAtas(tgl2,        shiftRight);   // komponen tanggal selesai
//        geserKeAtas(btnTemplate, shiftRight);   // tombol template (kalau sejajar baris ini)
//        geserKeAtas(jLabel10,     shiftRight); 
//         geserKeAtas(scrollPane2,     shiftRight);


        
        
        Object[] row={"P","Kode Paket","Nama Operasi","Kategori","Operator 1","Operator 2","Operator 3",
                      "Kru OK","-","-","-","dr Anak","-","dr Anastesi",
                      "-","-","-","-","-","Rumah Sakit","Sewa Alat","Sewa OK/VK",
                      "-","-","-","-","-","-","-",
                      "-","-","-","Total"};
        tabMode=new DefaultTableModel(null,row){
            @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = true;
                if ((colIndex==1)||(colIndex==2)||(colIndex==3)||(colIndex==29)) {
                    a=false;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.Object.class,java.lang.Object.class,java.lang.Object.class, java.lang.Double.class, 
                 java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, 
                 java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, 
                 java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, 
                 java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, 
                 java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, 
                 java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, 
                 java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbtindakan.setModel(tabMode);

        tbtindakan.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbtindakan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 33; i++) {
            TableColumn column = tbtindakan.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(100);
            }else if(i==2){
                column.setPreferredWidth(250);
            }else if(i==3){
                column.setPreferredWidth(100);
            }else{
                column.setPreferredWidth(85);
            }
        }
        tbtindakan.setDefaultRenderer(Object.class, new WarnaTable());
        
        
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
        
        //tagihan obat
        Object[] row2={"Jumlah",
        "Kode",
        "Nama",
        "Satuan",
        "Harga",
        "Total"};
        
        tabMode2=new DefaultTableModel(null,row2){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if ((colIndex==0)||(colIndex==4)) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };

//        tbObat.setModel(tabMode2);
        //tampil();

        //tbBangsal.setDefaultRenderer(Object.class, new WarnaTable(jPanel2.getBackground(),tbBangsal.getBackground()));
//        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
//        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//
//        for (i = 0; i < 6; i++) {
//            TableColumn column = tbObat.getColumnModel().getColumn(i);
//            if(i==0){
//                column.setPreferredWidth(50);
//            }else if(i==1){
//                column.setPreferredWidth(80);
//            }else if(i==2){
//                column.setPreferredWidth(150);
//            }else if(i==3){
//                column.setPreferredWidth(70);
//            }else if(i==4){
//                column.setPreferredWidth(90);
//            }else if(i==5){
//                column.setPreferredWidth(90);
//            }
//        }
//
//        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        jenis.setDocument(new batasInput((byte)8).getKata(jenis));
        kdoperator1.setDocument(new batasInput((byte)20).getKata(kdoperator1));
        kdoperator2.setDocument(new batasInput((byte)20).getKata(kdoperator2));
        kdoperator3.setDocument(new batasInput((byte)20).getKata(kdoperator3));
        kdasistoperator1.setDocument(new batasInput((byte)20).getKata(kdasistoperator1));
        kdasistoperator2.setDocument(new batasInput((byte)20).getKata(kdasistoperator2));
        kdasistoperator3.setDocument(new batasInput((byte)20).getKata(kdasistoperator3));
        kdInstrumen.setDocument(new batasInput((byte)20).getKata(kdInstrumen));
        kdanestesi.setDocument(new batasInput((byte)20).getKata(kdanestesi));
        kdasistanestesi.setDocument(new batasInput((byte)20).getKata(kdasistanestesi));
        kdasistanestesi2.setDocument(new batasInput((byte)20).getKata(kdasistanestesi2));
        kddranak.setDocument(new batasInput((byte)20).getKata(kddranak));
        kdprwresust.setDocument(new batasInput((byte)20).getKata(kdprwresust));
        kdbidan.setDocument(new batasInput((byte)20).getKata(kdbidan));
        kdbidan2.setDocument(new batasInput((byte)20).getKata(kdbidan2));
        kdbidan3.setDocument(new batasInput((byte)20).getKata(kdbidan3));
        kdprwluar.setDocument(new batasInput((byte)20).getKata(kdprwluar));
        kdonloop1.setDocument(new batasInput((byte)20).getKata(kdonloop1));
        kdonloop2.setDocument(new batasInput((byte)20).getKata(kdonloop2));
        kdonloop3.setDocument(new batasInput((byte)20).getKata(kdonloop3));
        kdonloop4.setDocument(new batasInput((byte)20).getKata(kdonloop4));        
        kdonloop5.setDocument(new batasInput((byte)20).getKata(kdonloop5));
        kdpjanak.setDocument(new batasInput((byte)20).getKata(kdpjanak));        
        kddrumum.setDocument(new batasInput((byte)20).getKata(kddrumum));      
        PreOp.setDocument(new batasInput((int)100).getKata(PreOp));      
        waktuAnastesi.setDocument(new batasInput((int)100).getKata(waktuAnastesi));      
//        jamMulaiAnastesi.setDocument(new batasInput((int)100).getKata(jamMulaiAnastesi));
        Laporan.setDocument(new batasInput((int)8000).getKata(Laporan));
        
//        TCariPaket.setDocument(new batasInput((byte)100).getKata(TCari)); 
//        TCari.setDocument(new batasInput((byte)100).getKata(TCari)); 
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCariPaket.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCariPaket.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCariPaket.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCariPaket.getText().length()>2){
                        tampil();
                    }
                }
            });
//            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
//                @Override
//                public void insertUpdate(DocumentEvent e) {
//                    if(TCari.getText().length()>2){
//                        tampil2();
//                    }
//                }
//                @Override
//                public void removeUpdate(DocumentEvent e) {
//                    if(TCari.getText().length()>2){
//                        tampil2();
//                    }
//                }
//                @Override
//                public void changedUpdate(DocumentEvent e) {
//                    if(TCari.getText().length()>2){
//                        tampil2();
//                    }
//                }
//            });
        }  
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){                    
                    if(pilihan==1){
                        kdoperator1.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        nmoperator1.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        kdoperator1.requestFocus();
                    }else if(pilihan==2){
                        kdoperator2.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        nmoperator2.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        kdoperator2.requestFocus();
                    }else if(pilihan==3){
                        kdoperator3.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        nmoperator3.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        kdoperator3.requestFocus();
                    }else if(pilihan==4){
                        kdanestesi.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        nmanestesi.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        kdanestesi.requestFocus();
                    }else if(pilihan==5){
                        kddranak.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        nmdranak.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        kddranak.requestFocus();
                    }else if(pilihan==6){
                        kdpjanak.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        nmpjanak.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        kdpjanak.requestFocus();
                    }else if(pilihan==7){
                        kddrumum.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                        nmdrumum.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        kddrumum.requestFocus();
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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(petugas.getTable().getSelectedRow()!= -1){    
                    if(pilihan==1){
                        kdasistoperator1.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        nmasistoperator1.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                        kdasistoperator1.requestFocus();
                    }else if(pilihan==2){
                        kdasistoperator2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        nmasistoperator2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                        kdasistoperator2.requestFocus();
                    }else if(pilihan==3){
                        kdInstrumen.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        nminstrumen.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                        kdInstrumen.requestFocus();
                    }else if(pilihan==4){
                        kdasistanestesi.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        nmasistanestesi.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                        kdasistanestesi.requestFocus();
                    }else if(pilihan==5){
                        kdprwresust.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        nmprwresust.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                        kdprwresust.requestFocus();
                    }else if(pilihan==6){
                        kdprwluar.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        nmprwluar.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                        kdprwluar.requestFocus();
                    }else if(pilihan==7){
                        kdbidan.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        nmbidan.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                        kdbidan.requestFocus();
                    }else if(pilihan==8){
                        kdbidan2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        nmbidan2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                        kdbidan2.requestFocus();
                    }else if(pilihan==9){
                        kdbidan3.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        nmbidan3.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                        kdbidan3.requestFocus();
                    }else if(pilihan==10){
                        kdonloop1.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        nmonloop1.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                        kdonloop1.requestFocus();
                    }else if(pilihan==11){
                        kdonloop2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        nmonloop2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                        kdonloop2.requestFocus();
                    }else if(pilihan==12){
                        kdonloop3.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        nmonloop3.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                        kdonloop3.requestFocus();
                    }else if(pilihan==13){
                        kdonloop4.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        nmonloop4.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                        kdonloop4.requestFocus();
                    }else if(pilihan==14){
                        kdonloop5.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        nmonloop5.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                        kdonloop5.requestFocus();
                    }else if(pilihan==15){
                        kdasistoperator3.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        nmasistoperator3.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                        kdasistoperator3.requestFocus();
                    }else if(pilihan==16){
                        kdasistanestesi2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        nmasistanestesi2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                        kdasistanestesi2.requestFocus();
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
        
//        TCari.requestFocus();
        ChkInput.setSelected(false);
        isForm();
        
        try {
            psrekening=koneksi.prepareStatement(
                "select set_akun_ralan.Suspen_Piutang_Operasi_Ralan,set_akun_ralan.Operasi_Ralan,set_akun_ralan.Beban_Jasa_Medik_Dokter_Operasi_Ralan,"+
                "set_akun_ralan.Utang_Jasa_Medik_Dokter_Operasi_Ralan,set_akun_ralan.Beban_Jasa_Medik_Paramedis_Operasi_Ralan,"+
                "set_akun_ralan.Utang_Jasa_Medik_Paramedis_Operasi_Ralan,set_akun_ralan.HPP_Obat_Operasi_Ralan,"+
                "set_akun_ralan.Persediaan_Obat_Kamar_Operasi_Ralan from set_akun_ralan");
            try {
                rsrekening=psrekening.executeQuery();
                while(rsrekening.next()){
                    Suspen_Piutang_Operasi_Ralan=rsrekening.getString("Suspen_Piutang_Operasi_Ralan");
                    Operasi_Ralan=rsrekening.getString("Operasi_Ralan");
                    Beban_Jasa_Medik_Dokter_Operasi_Ralan=rsrekening.getString("Beban_Jasa_Medik_Dokter_Operasi_Ralan");
                    Utang_Jasa_Medik_Dokter_Operasi_Ralan=rsrekening.getString("Utang_Jasa_Medik_Dokter_Operasi_Ralan");
                    Beban_Jasa_Medik_Paramedis_Operasi_Ralan=rsrekening.getString("Beban_Jasa_Medik_Paramedis_Operasi_Ralan");
                    Utang_Jasa_Medik_Paramedis_Operasi_Ralan=rsrekening.getString("Utang_Jasa_Medik_Paramedis_Operasi_Ralan");
                    HPP_Obat_Operasi_Ralan=rsrekening.getString("HPP_Obat_Operasi_Ralan");
                    Persediaan_Obat_Kamar_Operasi_Ralan=rsrekening.getString("Persediaan_Obat_Kamar_Operasi_Ralan");
                }
            } catch (Exception e) {
                System.out.println("Notif Rekening : "+e);
            } finally{
                if(rsrekening!=null){
                    rsrekening.close();
                }
                if(psrekening!=null){
                    psrekening.close();
                }
            }  
            
            psrekening=koneksi.prepareStatement(
               "select set_akun_ranap.Suspen_Piutang_Operasi_Ranap,set_akun_ranap.Operasi_Ranap,set_akun_ranap.Beban_Jasa_Medik_Dokter_Operasi_Ranap,"+
               "set_akun_ranap.Utang_Jasa_Medik_Dokter_Operasi_Ranap,set_akun_ranap.Beban_Jasa_Medik_Paramedis_Operasi_Ranap,"+
               "set_akun_ranap.Utang_Jasa_Medik_Paramedis_Operasi_Ranap,set_akun_ranap.HPP_Obat_Operasi_Ranap from set_akun_ranap");
            try {
                rsrekening=psrekening.executeQuery();
                while(rsrekening.next()){
                    Suspen_Piutang_Operasi_Ranap=rsrekening.getString("Suspen_Piutang_Operasi_Ranap");
                    Operasi_Ranap=rsrekening.getString("Operasi_Ranap");
                    Beban_Jasa_Medik_Dokter_Operasi_Ranap=rsrekening.getString("Beban_Jasa_Medik_Dokter_Operasi_Ranap");
                    Utang_Jasa_Medik_Dokter_Operasi_Ranap=rsrekening.getString("Utang_Jasa_Medik_Dokter_Operasi_Ranap");
                    Beban_Jasa_Medik_Paramedis_Operasi_Ranap=rsrekening.getString("Beban_Jasa_Medik_Paramedis_Operasi_Ranap");
                    Utang_Jasa_Medik_Paramedis_Operasi_Ranap=rsrekening.getString("Utang_Jasa_Medik_Paramedis_Operasi_Ranap");
                    HPP_Obat_Operasi_Ranap=rsrekening.getString("HPP_Obat_Operasi_Ranap");
                }
            } catch (Exception e) {
                System.out.println("Notif Rekening : "+e);
            } finally{
                if(rsrekening!=null){
                    rsrekening.close();
                }
                if(psrekening!=null){
                    psrekening.close();
                }
            }   
            
            psrekening=koneksi.prepareStatement("select set_akun_ranap2.Persediaan_Obat_Kamar_Operasi_Ranap from set_akun_ranap2");
            try {
                rsrekening=psrekening.executeQuery();
                while(rsrekening.next()){
                    Persediaan_Obat_Kamar_Operasi_Ranap=rsrekening.getString("Persediaan_Obat_Kamar_Operasi_Ranap");
                }
            } catch (Exception e) {
                System.out.println("Notif Rekening : "+e);
            } finally{
                if(rsrekening!=null){
                    rsrekening.close();
                }
                if(psrekening!=null){
                    psrekening.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } 
        
        try {
            psset_tarif=koneksi.prepareStatement("select set_tarif.cara_bayar_operasi,set_tarif.kelas_operasi from set_tarif");
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

        Kd2 = new widget.TextBox();
        Popup = new javax.swing.JPopupMenu();
        ppBersihkan = new javax.swing.JMenuItem();
        label19 = new widget.Label();
        kdoperator2 = new widget.TextBox();
        nmoperator2 = new widget.TextBox();
        label20 = new widget.Label();
        kdoperator3 = new widget.TextBox();
        nmoperator3 = new widget.TextBox();
        BtnOperator2 = new widget.Button();
        btnOperator3 = new widget.Button();
        label27 = new widget.Label();
        kdbidan = new widget.TextBox();
        nmbidan = new widget.TextBox();
        btnBidan = new widget.Button();
        label29 = new widget.Label();
        kdbidan2 = new widget.TextBox();
        nmbidan2 = new widget.TextBox();
        btnBidan2 = new widget.Button();
        label30 = new widget.Label();
        kdbidan3 = new widget.TextBox();
        nmbidan3 = new widget.TextBox();
        btnBidan3 = new widget.Button();
        kdprwluar = new widget.TextBox();
        label28 = new widget.Label();
        nmprwluar = new widget.TextBox();
        btnPrwLuar = new widget.Button();
        label18 = new widget.Label();
        kdasistoperator2 = new widget.TextBox();
        nmasistoperator2 = new widget.TextBox();
        btnAsis2 = new widget.Button();
        btnAsis4 = new widget.Button();
        nmasistoperator3 = new widget.TextBox();
        kdasistoperator3 = new widget.TextBox();
        label35 = new widget.Label();
        label26 = new widget.Label();
        kdasistanestesi = new widget.TextBox();
        nmasistanestesi = new widget.TextBox();
        BtnAsnes = new widget.Button();
        label36 = new widget.Label();
        kdasistanestesi2 = new widget.TextBox();
        nmasistanestesi2 = new widget.TextBox();
        BtnAsnes1 = new widget.Button();
        label24 = new widget.Label();
        kdprwresust = new widget.TextBox();
        nmprwresust = new widget.TextBox();
        btnPrwRes = new widget.Button();
        label25 = new widget.Label();
        kdonloop1 = new widget.TextBox();
        nmonloop1 = new widget.TextBox();
        btnOnloop1 = new widget.Button();
        label31 = new widget.Label();
        kdonloop2 = new widget.TextBox();
        nmonloop2 = new widget.TextBox();
        btnOnloop2 = new widget.Button();
        label32 = new widget.Label();
        kdonloop3 = new widget.TextBox();
        nmonloop3 = new widget.TextBox();
        btnOnloop3 = new widget.Button();
        btnOnloop4 = new widget.Button();
        nmonloop4 = new widget.TextBox();
        kdonloop4 = new widget.TextBox();
        label37 = new widget.Label();
        label38 = new widget.Label();
        kdonloop5 = new widget.TextBox();
        nmonloop5 = new widget.TextBox();
        btnOnloop5 = new widget.Button();
        label23 = new widget.Label();
        kdInstrumen = new widget.TextBox();
        nminstrumen = new widget.TextBox();
        btnAsis3 = new widget.Button();
        btndrpjanak = new widget.Button();
        nmpjanak = new widget.TextBox();
        kdpjanak = new widget.TextBox();
        label33 = new widget.Label();
        label34 = new widget.Label();
        kddrumum = new widget.TextBox();
        nmdrumum = new widget.TextBox();
        btndrumum = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        panelisi5 = new widget.panelisi();
        label10 = new widget.Label();
        TCariPaket = new widget.TextBox();
        BtnCari2 = new widget.Button();
        BtnAll1 = new widget.Button();
        BtnTambahOperasi = new widget.Button();
        Scroll1 = new widget.ScrollPane();
        tbtindakan = new widget.Table();
        PanelAccor = new widget.PanelBiasa();
        FormDetailBHP = new widget.PanelBiasa();
        Scroll7 = new widget.ScrollPane();
        tbDetailBHP = new widget.Table();
        panelisi1 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        LTotal = new widget.Label();
        BtnCari = new widget.Button();
        BtnKeluar = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        scrollPane1 = new widget.ScrollPane();
        FormInput = new widget.panelisi();
        label14 = new widget.Label();
        kdoperator1 = new widget.TextBox();
        nmoperator1 = new widget.TextBox();
        BtnOperator1 = new widget.Button();
        label11 = new widget.Label();
        tgl = new widget.Tanggal();
        jLabel3 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel4 = new widget.Label();
        jenis = new widget.TextBox();
        label17 = new widget.Label();
        kdasistoperator1 = new widget.TextBox();
        nmasistoperator1 = new widget.TextBox();
        btnAsis1 = new widget.Button();
        label21 = new widget.Label();
        kdanestesi = new widget.TextBox();
        nmanestesi = new widget.TextBox();
        BtnAnastesi = new widget.Button();
        label22 = new widget.Label();
        kddranak = new widget.TextBox();
        nmdranak = new widget.TextBox();
        btnAnak = new widget.Button();
        jLabel5 = new widget.Label();
        Kategori = new widget.ComboBox();
        label12 = new widget.Label();
        tgl2 = new widget.Tanggal();
        PreOp = new widget.TextBox();
        jLabel6 = new widget.Label();
        jLabel7 = new widget.Label();
        waktuAnastesi = new widget.TextBox();
        jLabel8 = new widget.Label();
        jLabel9 = new widget.Label();
        DikirimPA = new widget.ComboBox();
        scrollPane2 = new widget.ScrollPane();
        Laporan = new widget.TextArea();
        jLabel10 = new widget.Label();
        btnTemplate = new widget.Button();
        jLabel11 = new widget.Label();
        jnsAnastesi = new widget.TextBox();
        label13 = new widget.Label();
        Jaringan = new widget.TextBox();
        label15 = new widget.Label();
        jLabel12 = new widget.Label();
        bahanAnastesi = new widget.TextBox();
        golonganOperasi = new widget.ComboBox();
        jLabel13 = new widget.Label();
        jLabel14 = new widget.Label();
        macamOperasi = new widget.ComboBox();
        jLabel15 = new widget.Label();
        urgensiOps = new widget.ComboBox();
        icd9 = new widget.TextBox();
        jLabel16 = new widget.Label();
        jLabel17 = new widget.Label();
        selesaiAnastesi = new widget.TextBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel18 = new widget.Label();
        persiapanOps = new widget.TextBox();
        posisiPasien = new widget.TextBox();
        jLabel19 = new widget.Label();
        desinfeksi = new widget.TextBox();
        jLabel20 = new widget.Label();
        jLabel21 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        dilakukan = new widget.TextArea();
        tutupLuka = new widget.TextBox();
        jLabel22 = new widget.Label();
        penuylitOps = new widget.TextBox();
        jLabel23 = new widget.Label();
        jLabel24 = new widget.Label();
        jmlDarah = new widget.TextBox();
        hasilOps1 = new widget.TextBox();
        jLabel25 = new widget.Label();
        hasilOps2 = new widget.TextBox();
        bahanAnastesi1 = new widget.TextBox();
        jLabel26 = new widget.Label();
        noKo1 = new widget.TextBox();
        ronde1 = new widget.TextBox();
        jLabel31 = new widget.Label();
        PostOp = new widget.TextBox();
        jLabel27 = new widget.Label();

        Kd2.setName("Kd2"); // NOI18N
        Kd2.setPreferredSize(new java.awt.Dimension(207, 23));

        Popup.setName("Popup"); // NOI18N

        ppBersihkan.setBackground(new java.awt.Color(255, 255, 254));
        ppBersihkan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ppBersihkan.setForeground(new java.awt.Color(50, 50, 50));
        ppBersihkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        ppBersihkan.setText("Bersihkan Jumlah");
        ppBersihkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBersihkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBersihkan.setName("ppBersihkan"); // NOI18N
        ppBersihkan.setPreferredSize(new java.awt.Dimension(200, 25));
        ppBersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBersihkanActionPerformed(evt);
            }
        });
        Popup.add(ppBersihkan);

        label19.setText("Operator 2 :");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(70, 23));

        kdoperator2.setEditable(false);
        kdoperator2.setName("kdoperator2"); // NOI18N
        kdoperator2.setPreferredSize(new java.awt.Dimension(80, 23));
        kdoperator2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdoperator2KeyPressed(evt);
            }
        });

        nmoperator2.setEditable(false);
        nmoperator2.setName("nmoperator2"); // NOI18N
        nmoperator2.setPreferredSize(new java.awt.Dimension(207, 23));

        label20.setText("Operator 3 :");
        label20.setName("label20"); // NOI18N
        label20.setPreferredSize(new java.awt.Dimension(70, 23));

        kdoperator3.setEditable(false);
        kdoperator3.setName("kdoperator3"); // NOI18N
        kdoperator3.setPreferredSize(new java.awt.Dimension(80, 23));
        kdoperator3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdoperator3KeyPressed(evt);
            }
        });

        nmoperator3.setEditable(false);
        nmoperator3.setName("nmoperator3"); // NOI18N
        nmoperator3.setPreferredSize(new java.awt.Dimension(207, 23));

        BtnOperator2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnOperator2.setMnemonic('2');
        BtnOperator2.setToolTipText("Alt+2");
        BtnOperator2.setName("BtnOperator2"); // NOI18N
        BtnOperator2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnOperator2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnOperator2ActionPerformed(evt);
            }
        });
        BtnOperator2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnOperator2KeyPressed(evt);
            }
        });

        btnOperator3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnOperator3.setMnemonic('2');
        btnOperator3.setToolTipText("Alt+2");
        btnOperator3.setName("btnOperator3"); // NOI18N
        btnOperator3.setPreferredSize(new java.awt.Dimension(28, 23));
        btnOperator3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOperator3ActionPerformed(evt);
            }
        });
        btnOperator3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnOperator3KeyPressed(evt);
            }
        });

        label27.setText("Bidan 1 :");
        label27.setName("label27"); // NOI18N
        label27.setPreferredSize(new java.awt.Dimension(70, 23));

        kdbidan.setEditable(false);
        kdbidan.setName("kdbidan"); // NOI18N
        kdbidan.setPreferredSize(new java.awt.Dimension(80, 23));
        kdbidan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdbidanKeyPressed(evt);
            }
        });

        nmbidan.setEditable(false);
        nmbidan.setName("nmbidan"); // NOI18N
        nmbidan.setPreferredSize(new java.awt.Dimension(207, 23));

        btnBidan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnBidan.setMnemonic('2');
        btnBidan.setToolTipText("Alt+2");
        btnBidan.setName("btnBidan"); // NOI18N
        btnBidan.setPreferredSize(new java.awt.Dimension(28, 23));
        btnBidan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBidanActionPerformed(evt);
            }
        });

        label29.setText("Bidan 2 :");
        label29.setName("label29"); // NOI18N
        label29.setPreferredSize(new java.awt.Dimension(70, 23));

        kdbidan2.setEditable(false);
        kdbidan2.setName("kdbidan2"); // NOI18N
        kdbidan2.setPreferredSize(new java.awt.Dimension(80, 23));
        kdbidan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdbidan2KeyPressed(evt);
            }
        });

        nmbidan2.setEditable(false);
        nmbidan2.setName("nmbidan2"); // NOI18N
        nmbidan2.setPreferredSize(new java.awt.Dimension(207, 23));

        btnBidan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnBidan2.setMnemonic('2');
        btnBidan2.setToolTipText("Alt+2");
        btnBidan2.setName("btnBidan2"); // NOI18N
        btnBidan2.setPreferredSize(new java.awt.Dimension(28, 23));
        btnBidan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBidan2ActionPerformed(evt);
            }
        });

        label30.setText("Bidan 3 :");
        label30.setName("label30"); // NOI18N
        label30.setPreferredSize(new java.awt.Dimension(70, 23));

        kdbidan3.setEditable(false);
        kdbidan3.setName("kdbidan3"); // NOI18N
        kdbidan3.setPreferredSize(new java.awt.Dimension(80, 23));
        kdbidan3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdbidan3KeyPressed(evt);
            }
        });

        nmbidan3.setEditable(false);
        nmbidan3.setName("nmbidan3"); // NOI18N
        nmbidan3.setPreferredSize(new java.awt.Dimension(207, 23));

        btnBidan3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnBidan3.setMnemonic('2');
        btnBidan3.setToolTipText("Alt+2");
        btnBidan3.setName("btnBidan3"); // NOI18N
        btnBidan3.setPreferredSize(new java.awt.Dimension(28, 23));
        btnBidan3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBidan3ActionPerformed(evt);
            }
        });

        kdprwluar.setEditable(false);
        kdprwluar.setName("kdprwluar"); // NOI18N
        kdprwluar.setPreferredSize(new java.awt.Dimension(80, 23));
        kdprwluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdprwluarKeyPressed(evt);
            }
        });

        label28.setText("Prwat Luar :");
        label28.setName("label28"); // NOI18N
        label28.setPreferredSize(new java.awt.Dimension(70, 23));

        nmprwluar.setEditable(false);
        nmprwluar.setName("nmprwluar"); // NOI18N
        nmprwluar.setPreferredSize(new java.awt.Dimension(207, 23));

        btnPrwLuar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPrwLuar.setMnemonic('2');
        btnPrwLuar.setToolTipText("Alt+2");
        btnPrwLuar.setName("btnPrwLuar"); // NOI18N
        btnPrwLuar.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPrwLuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrwLuarActionPerformed(evt);
            }
        });

        label18.setText("Ast. Operator 2 :");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(70, 23));

        kdasistoperator2.setEditable(false);
        kdasistoperator2.setName("kdasistoperator2"); // NOI18N
        kdasistoperator2.setPreferredSize(new java.awt.Dimension(80, 23));
        kdasistoperator2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdasistoperator2KeyPressed(evt);
            }
        });

        nmasistoperator2.setEditable(false);
        nmasistoperator2.setName("nmasistoperator2"); // NOI18N
        nmasistoperator2.setPreferredSize(new java.awt.Dimension(207, 23));

        btnAsis2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnAsis2.setMnemonic('2');
        btnAsis2.setToolTipText("Alt+2");
        btnAsis2.setName("btnAsis2"); // NOI18N
        btnAsis2.setPreferredSize(new java.awt.Dimension(28, 23));
        btnAsis2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsis2ActionPerformed(evt);
            }
        });

        btnAsis4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnAsis4.setMnemonic('2');
        btnAsis4.setToolTipText("Alt+2");
        btnAsis4.setName("btnAsis4"); // NOI18N
        btnAsis4.setPreferredSize(new java.awt.Dimension(28, 23));
        btnAsis4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsis4ActionPerformed(evt);
            }
        });

        nmasistoperator3.setEditable(false);
        nmasistoperator3.setName("nmasistoperator3"); // NOI18N
        nmasistoperator3.setPreferredSize(new java.awt.Dimension(207, 23));

        kdasistoperator3.setEditable(false);
        kdasistoperator3.setName("kdasistoperator3"); // NOI18N
        kdasistoperator3.setPreferredSize(new java.awt.Dimension(80, 23));
        kdasistoperator3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdasistoperator3KeyPressed(evt);
            }
        });

        label35.setText("Ast. Operator 3 :");
        label35.setName("label35"); // NOI18N
        label35.setPreferredSize(new java.awt.Dimension(70, 23));

        label26.setText("Ast. Anestesi 1 :");
        label26.setName("label26"); // NOI18N
        label26.setPreferredSize(new java.awt.Dimension(70, 23));

        kdasistanestesi.setEditable(false);
        kdasistanestesi.setName("kdasistanestesi"); // NOI18N
        kdasistanestesi.setPreferredSize(new java.awt.Dimension(80, 23));
        kdasistanestesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdasistanestesiKeyPressed(evt);
            }
        });

        nmasistanestesi.setEditable(false);
        nmasistanestesi.setName("nmasistanestesi"); // NOI18N
        nmasistanestesi.setPreferredSize(new java.awt.Dimension(207, 23));

        BtnAsnes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnAsnes.setMnemonic('2');
        BtnAsnes.setToolTipText("Alt+2");
        BtnAsnes.setName("BtnAsnes"); // NOI18N
        BtnAsnes.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAsnes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsnesActionPerformed(evt);
            }
        });

        label36.setText("Ast. Anestesi 2 :");
        label36.setName("label36"); // NOI18N
        label36.setPreferredSize(new java.awt.Dimension(70, 23));

        kdasistanestesi2.setEditable(false);
        kdasistanestesi2.setName("kdasistanestesi2"); // NOI18N
        kdasistanestesi2.setPreferredSize(new java.awt.Dimension(80, 23));
        kdasistanestesi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdasistanestesi2KeyPressed(evt);
            }
        });

        nmasistanestesi2.setEditable(false);
        nmasistanestesi2.setName("nmasistanestesi2"); // NOI18N
        nmasistanestesi2.setPreferredSize(new java.awt.Dimension(207, 23));

        BtnAsnes1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnAsnes1.setMnemonic('2');
        BtnAsnes1.setToolTipText("Alt+2");
        BtnAsnes1.setName("BtnAsnes1"); // NOI18N
        BtnAsnes1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAsnes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAsnes1ActionPerformed(evt);
            }
        });

        label24.setText("Prw.Resusitasi :");
        label24.setName("label24"); // NOI18N
        label24.setPreferredSize(new java.awt.Dimension(70, 23));

        kdprwresust.setEditable(false);
        kdprwresust.setName("kdprwresust"); // NOI18N
        kdprwresust.setPreferredSize(new java.awt.Dimension(80, 23));
        kdprwresust.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdprwresustKeyPressed(evt);
            }
        });

        nmprwresust.setEditable(false);
        nmprwresust.setName("nmprwresust"); // NOI18N
        nmprwresust.setPreferredSize(new java.awt.Dimension(207, 23));

        btnPrwRes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPrwRes.setMnemonic('2');
        btnPrwRes.setToolTipText("Alt+2");
        btnPrwRes.setName("btnPrwRes"); // NOI18N
        btnPrwRes.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPrwRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrwResActionPerformed(evt);
            }
        });

        label25.setText("Onloop 1 :");
        label25.setName("label25"); // NOI18N
        label25.setPreferredSize(new java.awt.Dimension(70, 23));

        kdonloop1.setEditable(false);
        kdonloop1.setName("kdonloop1"); // NOI18N
        kdonloop1.setPreferredSize(new java.awt.Dimension(80, 23));
        kdonloop1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdonloop1KeyPressed(evt);
            }
        });

        nmonloop1.setEditable(false);
        nmonloop1.setName("nmonloop1"); // NOI18N
        nmonloop1.setPreferredSize(new java.awt.Dimension(207, 23));

        btnOnloop1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnOnloop1.setMnemonic('2');
        btnOnloop1.setToolTipText("Alt+2");
        btnOnloop1.setName("btnOnloop1"); // NOI18N
        btnOnloop1.setPreferredSize(new java.awt.Dimension(28, 23));
        btnOnloop1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOnloop1ActionPerformed(evt);
            }
        });

        label31.setText("Onloop 2 :");
        label31.setName("label31"); // NOI18N
        label31.setPreferredSize(new java.awt.Dimension(70, 23));

        kdonloop2.setEditable(false);
        kdonloop2.setName("kdonloop2"); // NOI18N
        kdonloop2.setPreferredSize(new java.awt.Dimension(80, 23));
        kdonloop2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdonloop2KeyPressed(evt);
            }
        });

        nmonloop2.setEditable(false);
        nmonloop2.setName("nmonloop2"); // NOI18N
        nmonloop2.setPreferredSize(new java.awt.Dimension(207, 23));

        btnOnloop2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnOnloop2.setMnemonic('2');
        btnOnloop2.setToolTipText("Alt+2");
        btnOnloop2.setName("btnOnloop2"); // NOI18N
        btnOnloop2.setPreferredSize(new java.awt.Dimension(28, 23));
        btnOnloop2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOnloop2ActionPerformed(evt);
            }
        });

        label32.setText("Onloop 3 :");
        label32.setName("label32"); // NOI18N
        label32.setPreferredSize(new java.awt.Dimension(70, 23));

        kdonloop3.setEditable(false);
        kdonloop3.setName("kdonloop3"); // NOI18N
        kdonloop3.setPreferredSize(new java.awt.Dimension(80, 23));
        kdonloop3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdonloop3KeyPressed(evt);
            }
        });

        nmonloop3.setEditable(false);
        nmonloop3.setName("nmonloop3"); // NOI18N
        nmonloop3.setPreferredSize(new java.awt.Dimension(207, 23));

        btnOnloop3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnOnloop3.setMnemonic('2');
        btnOnloop3.setToolTipText("Alt+2");
        btnOnloop3.setName("btnOnloop3"); // NOI18N
        btnOnloop3.setPreferredSize(new java.awt.Dimension(28, 23));
        btnOnloop3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOnloop3ActionPerformed(evt);
            }
        });

        btnOnloop4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnOnloop4.setMnemonic('2');
        btnOnloop4.setToolTipText("Alt+2");
        btnOnloop4.setName("btnOnloop4"); // NOI18N
        btnOnloop4.setPreferredSize(new java.awt.Dimension(28, 23));
        btnOnloop4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOnloop4ActionPerformed(evt);
            }
        });

        nmonloop4.setEditable(false);
        nmonloop4.setName("nmonloop4"); // NOI18N
        nmonloop4.setPreferredSize(new java.awt.Dimension(207, 23));

        kdonloop4.setEditable(false);
        kdonloop4.setName("kdonloop4"); // NOI18N
        kdonloop4.setPreferredSize(new java.awt.Dimension(80, 23));
        kdonloop4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdonloop4KeyPressed(evt);
            }
        });

        label37.setText("Onloop 4 :");
        label37.setName("label37"); // NOI18N
        label37.setPreferredSize(new java.awt.Dimension(70, 23));

        label38.setText("Onloop 5 :");
        label38.setName("label38"); // NOI18N
        label38.setPreferredSize(new java.awt.Dimension(70, 23));

        kdonloop5.setEditable(false);
        kdonloop5.setName("kdonloop5"); // NOI18N
        kdonloop5.setPreferredSize(new java.awt.Dimension(80, 23));
        kdonloop5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdonloop5KeyPressed(evt);
            }
        });

        nmonloop5.setEditable(false);
        nmonloop5.setName("nmonloop5"); // NOI18N
        nmonloop5.setPreferredSize(new java.awt.Dimension(207, 23));

        btnOnloop5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnOnloop5.setMnemonic('2');
        btnOnloop5.setToolTipText("Alt+2");
        btnOnloop5.setName("btnOnloop5"); // NOI18N
        btnOnloop5.setPreferredSize(new java.awt.Dimension(28, 23));
        btnOnloop5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOnloop5ActionPerformed(evt);
            }
        });

        label23.setText("Instrumen :");
        label23.setName("label23"); // NOI18N
        label23.setPreferredSize(new java.awt.Dimension(70, 23));

        kdInstrumen.setEditable(false);
        kdInstrumen.setName("kdInstrumen"); // NOI18N
        kdInstrumen.setPreferredSize(new java.awt.Dimension(80, 23));
        kdInstrumen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdInstrumenKeyPressed(evt);
            }
        });

        nminstrumen.setEditable(false);
        nminstrumen.setName("nminstrumen"); // NOI18N
        nminstrumen.setPreferredSize(new java.awt.Dimension(207, 23));

        btnAsis3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnAsis3.setMnemonic('2');
        btnAsis3.setToolTipText("Alt+2");
        btnAsis3.setName("btnAsis3"); // NOI18N
        btnAsis3.setPreferredSize(new java.awt.Dimension(28, 23));
        btnAsis3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsis3ActionPerformed(evt);
            }
        });

        btndrpjanak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btndrpjanak.setMnemonic('2');
        btndrpjanak.setToolTipText("Alt+2");
        btndrpjanak.setName("btndrpjanak"); // NOI18N
        btndrpjanak.setPreferredSize(new java.awt.Dimension(28, 23));
        btndrpjanak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndrpjanakActionPerformed(evt);
            }
        });

        nmpjanak.setEditable(false);
        nmpjanak.setName("nmpjanak"); // NOI18N
        nmpjanak.setPreferredSize(new java.awt.Dimension(207, 23));

        kdpjanak.setEditable(false);
        kdpjanak.setName("kdpjanak"); // NOI18N
        kdpjanak.setPreferredSize(new java.awt.Dimension(80, 23));
        kdpjanak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpjanakKeyPressed(evt);
            }
        });

        label33.setText("dr Pj. Anak :");
        label33.setName("label33"); // NOI18N
        label33.setPreferredSize(new java.awt.Dimension(70, 23));

        label34.setText("dr Umum :");
        label34.setName("label34"); // NOI18N
        label34.setPreferredSize(new java.awt.Dimension(70, 23));

        kddrumum.setEditable(false);
        kddrumum.setName("kddrumum"); // NOI18N
        kddrumum.setPreferredSize(new java.awt.Dimension(80, 23));
        kddrumum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kddrumumKeyPressed(evt);
            }
        });

        nmdrumum.setEditable(false);
        nmdrumum.setName("nmdrumum"); // NOI18N
        nmdrumum.setPreferredSize(new java.awt.Dimension(207, 23));

        btndrumum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btndrumum.setMnemonic('2');
        btndrumum.setToolTipText("Alt+2");
        btndrumum.setName("btndrumum"); // NOI18N
        btndrumum.setPreferredSize(new java.awt.Dimension(28, 23));
        btndrumum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndrumumActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Tagihan Operasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 102));
        jPanel1.setVerifyInputWhenFocusTarget(false);
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), " Tindakan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(400, 102));
        jPanel3.setVerifyInputWhenFocusTarget(false);
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi5.setBorder(null);
        panelisi5.setName("panelisi5"); // NOI18N
        panelisi5.setPreferredSize(new java.awt.Dimension(100, 43));
        panelisi5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label10.setText("Key Word :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(68, 23));
        panelisi5.add(label10);

        TCariPaket.setToolTipText("Alt+C");
        TCariPaket.setName("TCariPaket"); // NOI18N
        TCariPaket.setPreferredSize(new java.awt.Dimension(215, 23));
        TCariPaket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariPaketKeyPressed(evt);
            }
        });
        panelisi5.add(TCariPaket);

        BtnCari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari2.setMnemonic('1');
        BtnCari2.setToolTipText("Alt+1");
        BtnCari2.setName("BtnCari2"); // NOI18N
        BtnCari2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari2ActionPerformed(evt);
            }
        });
        BtnCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari2KeyPressed(evt);
            }
        });
        panelisi5.add(BtnCari2);

        BtnAll1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll1.setMnemonic('2');
        BtnAll1.setToolTipText("Alt+2");
        BtnAll1.setName("BtnAll1"); // NOI18N
        BtnAll1.setPreferredSize(new java.awt.Dimension(28, 23));
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
        panelisi5.add(BtnAll1);

        BtnTambahOperasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahOperasi.setMnemonic('3');
        BtnTambahOperasi.setToolTipText("Alt+3");
        BtnTambahOperasi.setName("BtnTambahOperasi"); // NOI18N
        BtnTambahOperasi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTambahOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahOperasiActionPerformed(evt);
            }
        });
        panelisi5.add(BtnTambahOperasi);

        jPanel3.add(panelisi5, java.awt.BorderLayout.PAGE_END);

        Scroll1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbtindakan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbtindakan.setName("tbtindakan"); // NOI18N
        tbtindakan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbtindakanMouseClicked(evt);
            }
        });
        tbtindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbtindakanKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbtindakan);

        jPanel3.add(Scroll1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3);

        PanelAccor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Obat BHP Yang Sudah Masuk Paket Operasi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        PanelAccor.setMinimumSize(new java.awt.Dimension(35, 20));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(370, 10));
        PanelAccor.setLayout(new java.awt.BorderLayout(1, 1));

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

        jPanel1.add(PanelAccor);

        internalFrame1.add(jPanel1, java.awt.BorderLayout.CENTER);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelisi1.add(BtnSimpan);

        LTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LTotal.setText("Total Biaya : 0");
        LTotal.setName("LTotal"); // NOI18N
        LTotal.setPreferredSize(new java.awt.Dimension(500, 30));
        panelisi1.add(LTotal);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnCari.setMnemonic('E');
        BtnCari.setText("Cari");
        BtnCari.setToolTipText("Alt+E");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(100, 30));
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
        panelisi1.add(BtnCari);

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
        panelisi1.add(BtnKeluar);

        internalFrame1.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(560, 600));
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

        scrollPane1.setName("scrollPane1"); // NOI18N

        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(89, 553));
        FormInput.setLayout(null);

        label14.setText("Operator 1 :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(0, 70, 81, 23);

        kdoperator1.setEditable(false);
        kdoperator1.setName("kdoperator1"); // NOI18N
        kdoperator1.setPreferredSize(new java.awt.Dimension(80, 23));
        kdoperator1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdoperator1KeyPressed(evt);
            }
        });
        FormInput.add(kdoperator1);
        kdoperator1.setBounds(84, 70, 100, 23);

        nmoperator1.setEditable(false);
        nmoperator1.setName("nmoperator1"); // NOI18N
        nmoperator1.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(nmoperator1);
        nmoperator1.setBounds(185, 70, 190, 23);

        BtnOperator1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnOperator1.setMnemonic('2');
        BtnOperator1.setToolTipText("Alt+2");
        BtnOperator1.setName("BtnOperator1"); // NOI18N
        BtnOperator1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnOperator1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnOperator1ActionPerformed(evt);
            }
        });
        BtnOperator1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnOperator1KeyPressed(evt);
            }
        });
        FormInput.add(BtnOperator1);
        BtnOperator1.setBounds(376, 70, 28, 23);

        label11.setText("Tanggal Operasi :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(406, 40, 101, 23);

        tgl.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        tgl.setName("tgl"); // NOI18N
        tgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglKeyPressed(evt);
            }
        });
        FormInput.add(tgl);
        tgl.setBounds(510, 40, 150, 23);

        jLabel3.setText("No.Rawat :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(0, 10, 81, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(84, 10, 180, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(265, 10, 535, 23);

        jLabel4.setText("Jenis Anasthesi :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(219, 40, 90, 23);

        jenis.setHighlighter(null);
        jenis.setName("jenis"); // NOI18N
        jenis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jenisKeyPressed(evt);
            }
        });
        FormInput.add(jenis);
        jenis.setBounds(312, 40, 92, 23);

        label17.setText("Kru OK :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label17);
        label17.setBounds(406, 70, 101, 23);

        kdasistoperator1.setEditable(false);
        kdasistoperator1.setName("kdasistoperator1"); // NOI18N
        kdasistoperator1.setPreferredSize(new java.awt.Dimension(80, 23));
        kdasistoperator1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdasistoperator1KeyPressed(evt);
            }
        });
        FormInput.add(kdasistoperator1);
        kdasistoperator1.setBounds(510, 70, 100, 23);

        nmasistoperator1.setEditable(false);
        nmasistoperator1.setName("nmasistoperator1"); // NOI18N
        nmasistoperator1.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(nmasistoperator1);
        nmasistoperator1.setBounds(611, 70, 190, 23);

        btnAsis1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnAsis1.setMnemonic('2');
        btnAsis1.setToolTipText("Alt+2");
        btnAsis1.setName("btnAsis1"); // NOI18N
        btnAsis1.setPreferredSize(new java.awt.Dimension(28, 23));
        btnAsis1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsis1ActionPerformed(evt);
            }
        });
        FormInput.add(btnAsis1);
        btnAsis1.setBounds(802, 70, 28, 23);

        label21.setText("dr Anestesi :");
        label21.setName("label21"); // NOI18N
        label21.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label21);
        label21.setBounds(0, 100, 81, 23);

        kdanestesi.setEditable(false);
        kdanestesi.setName("kdanestesi"); // NOI18N
        kdanestesi.setPreferredSize(new java.awt.Dimension(80, 23));
        kdanestesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdanestesiKeyPressed(evt);
            }
        });
        FormInput.add(kdanestesi);
        kdanestesi.setBounds(90, 100, 100, 23);

        nmanestesi.setEditable(false);
        nmanestesi.setName("nmanestesi"); // NOI18N
        nmanestesi.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(nmanestesi);
        nmanestesi.setBounds(190, 100, 190, 23);

        BtnAnastesi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnAnastesi.setMnemonic('2');
        BtnAnastesi.setToolTipText("Alt+2");
        BtnAnastesi.setName("BtnAnastesi"); // NOI18N
        BtnAnastesi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAnastesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAnastesiActionPerformed(evt);
            }
        });
        FormInput.add(BtnAnastesi);
        BtnAnastesi.setBounds(380, 100, 28, 23);

        label22.setText("dr Anak :");
        label22.setName("label22"); // NOI18N
        label22.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label22);
        label22.setBounds(0, 130, 81, 23);

        kddranak.setEditable(false);
        kddranak.setName("kddranak"); // NOI18N
        kddranak.setPreferredSize(new java.awt.Dimension(80, 23));
        kddranak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kddranakKeyPressed(evt);
            }
        });
        FormInput.add(kddranak);
        kddranak.setBounds(90, 130, 100, 23);

        nmdranak.setEditable(false);
        nmdranak.setName("nmdranak"); // NOI18N
        nmdranak.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(nmdranak);
        nmdranak.setBounds(190, 130, 190, 23);

        btnAnak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnAnak.setMnemonic('2');
        btnAnak.setToolTipText("Alt+2");
        btnAnak.setName("btnAnak"); // NOI18N
        btnAnak.setPreferredSize(new java.awt.Dimension(28, 23));
        btnAnak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnakActionPerformed(evt);
            }
        });
        FormInput.add(btnAnak);
        btnAnak.setBounds(380, 130, 28, 23);

        jLabel5.setText("Kategori :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 40, 81, 23);

        Kategori.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Khusus", "Besar", "Sedang", "Kecil", "Elektive", "Emergency" }));
        Kategori.setName("Kategori"); // NOI18N
        Kategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KategoriKeyPressed(evt);
            }
        });
        FormInput.add(Kategori);
        Kategori.setBounds(84, 40, 122, 23);

        label12.setText("Selesai Operasi :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label12);
        label12.setBounds(410, 100, 101, 23);

        tgl2.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        tgl2.setName("tgl2"); // NOI18N
        tgl2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tgl2KeyPressed(evt);
            }
        });
        FormInput.add(tgl2);
        tgl2.setBounds(520, 100, 150, 23);

        PreOp.setHighlighter(null);
        PreOp.setName("PreOp"); // NOI18N
        PreOp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PreOpKeyPressed(evt);
            }
        });
        FormInput.add(PreOp);
        PreOp.setBounds(150, 160, 256, 23);

        jLabel6.setText("Diagnosis Pra Bedah :");
        jLabel6.setName("jLabel6"); // NOI18N
        FormInput.add(jLabel6);
        jLabel6.setBounds(0, 160, 145, 23);

        jLabel7.setText("No KO :");
        jLabel7.setName("jLabel7"); // NOI18N
        FormInput.add(jLabel7);
        jLabel7.setBounds(430, 190, 40, 23);

        waktuAnastesi.setHighlighter(null);
        waktuAnastesi.setName("waktuAnastesi"); // NOI18N
        waktuAnastesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                waktuAnastesiActionPerformed(evt);
            }
        });
        waktuAnastesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                waktuAnastesiKeyPressed(evt);
            }
        });
        FormInput.add(waktuAnastesi);
        waktuAnastesi.setBounds(510, 160, 100, 23);

        jLabel8.setText("lapangan operasi :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 240, 150, 23);

        jLabel9.setText("Pemeriksaan PA :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(190, 360, 145, 23);

        DikirimPA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        DikirimPA.setName("DikirimPA"); // NOI18N
        DikirimPA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DikirimPAKeyPressed(evt);
            }
        });
        FormInput.add(DikirimPA);
        DikirimPA.setBounds(340, 360, 60, 20);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        Laporan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Laporan.setColumns(20);
        Laporan.setRows(5);
        Laporan.setName("Laporan"); // NOI18N
        scrollPane2.setViewportView(Laporan);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(500, 220, 320, 112);

        jLabel10.setText("Hasil Eksplori :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(420, 230, 70, 23);

        btnTemplate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnTemplate.setMnemonic('2');
        btnTemplate.setToolTipText("Alt+2");
        btnTemplate.setName("btnTemplate"); // NOI18N
        btnTemplate.setPreferredSize(new java.awt.Dimension(28, 23));
        btnTemplate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTemplateActionPerformed(evt);
            }
        });
        FormInput.add(btnTemplate);
        btnTemplate.setBounds(470, 250, 28, 23);

        jLabel11.setText("Jenis Anastesi :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 270, 145, 23);

        jnsAnastesi.setHighlighter(null);
        jnsAnastesi.setName("jnsAnastesi"); // NOI18N
        jnsAnastesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jnsAnastesiKeyPressed(evt);
            }
        });
        FormInput.add(jnsAnastesi);
        jnsAnastesi.setBounds(150, 270, 256, 23);

        label13.setText("Mulai Anastesi :");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label13);
        label13.setBounds(400, 160, 101, 23);

        Jaringan.setHighlighter(null);
        Jaringan.setName("Jaringan"); // NOI18N
        Jaringan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JaringanKeyPressed(evt);
            }
        });
        FormInput.add(Jaringan);
        Jaringan.setBounds(150, 230, 256, 23);

        label15.setText("Selesai Anastesi :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label15);
        label15.setBounds(610, 160, 101, 23);

        jLabel12.setText("Bahan Anastesi :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 300, 145, 23);

        bahanAnastesi.setHighlighter(null);
        bahanAnastesi.setName("bahanAnastesi"); // NOI18N
        bahanAnastesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bahanAnastesiKeyPressed(evt);
            }
        });
        FormInput.add(bahanAnastesi);
        bahanAnastesi.setBounds(150, 300, 256, 23);

        golonganOperasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Poliklinik", "Kecil", "Sedang", "Besar", "Khusus" }));
        golonganOperasi.setName("golonganOperasi"); // NOI18N
        golonganOperasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                golonganOperasiKeyPressed(evt);
            }
        });
        FormInput.add(golonganOperasi);
        golonganOperasi.setBounds(100, 330, 110, 23);

        jLabel13.setText("Golongan Operasi :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 330, 92, 23);

        jLabel14.setText("Macam Operasi :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(150, 330, 145, 23);

        macamOperasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Bersih", "Bersih Terkontaminasi", "Kontaminasi", "Kotor" }));
        macamOperasi.setName("macamOperasi"); // NOI18N
        macamOperasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                macamOperasiKeyPressed(evt);
            }
        });
        FormInput.add(macamOperasi);
        macamOperasi.setBounds(300, 330, 90, 23);

        jLabel15.setText("Urgensi Operasi :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(-40, 360, 145, 23);

        urgensiOps.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Darurat", "Urgen", "Efektif" }));
        urgensiOps.setName("urgensiOps"); // NOI18N
        urgensiOps.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                urgensiOpsKeyPressed(evt);
            }
        });
        FormInput.add(urgensiOps);
        urgensiOps.setBounds(100, 360, 110, 23);

        icd9.setHighlighter(null);
        icd9.setName("icd9"); // NOI18N
        icd9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                icd9KeyPressed(evt);
            }
        });
        FormInput.add(icd9);
        icd9.setBounds(510, 130, 256, 23);

        jLabel16.setText("ICD-9-CM :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(360, 130, 145, 23);

        jLabel17.setText("Ronde :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(610, 190, 40, 23);

        selesaiAnastesi.setHighlighter(null);
        selesaiAnastesi.setName("selesaiAnastesi"); // NOI18N
        selesaiAnastesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selesaiAnastesiActionPerformed(evt);
            }
        });
        selesaiAnastesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                selesaiAnastesiKeyPressed(evt);
            }
        });
        FormInput.add(selesaiAnastesi);
        selesaiAnastesi.setBounds(710, 160, 100, 23);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Catatan Operator");
        jLabel1.setName("jLabel1"); // NOI18N
        FormInput.add(jLabel1);
        jLabel1.setBounds(10, 390, 140, 20);

        jLabel18.setText(" Persiapan Operasi :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(-10, 420, 145, 23);

        persiapanOps.setHighlighter(null);
        persiapanOps.setName("persiapanOps"); // NOI18N
        persiapanOps.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                persiapanOpsKeyPressed(evt);
            }
        });
        FormInput.add(persiapanOps);
        persiapanOps.setBounds(130, 420, 256, 23);

        posisiPasien.setHighlighter(null);
        posisiPasien.setName("posisiPasien"); // NOI18N
        posisiPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                posisiPasienKeyPressed(evt);
            }
        });
        FormInput.add(posisiPasien);
        posisiPasien.setBounds(130, 450, 256, 23);

        jLabel19.setText("Posisi Pasieni :");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(-50, 450, 160, 23);

        desinfeksi.setHighlighter(null);
        desinfeksi.setName("desinfeksi"); // NOI18N
        desinfeksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                desinfeksiKeyPressed(evt);
            }
        });
        FormInput.add(desinfeksi);
        desinfeksi.setBounds(130, 480, 256, 23);

        jLabel20.setText("Desinfeksi :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(-90, 480, 190, 23);

        jLabel21.setText("Apa yang dilakukan :");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(0, 510, 120, 23);

        scrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane3.setName("scrollPane3"); // NOI18N

        dilakukan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        dilakukan.setColumns(20);
        dilakukan.setRows(5);
        dilakukan.setName("dilakukan"); // NOI18N
        scrollPane3.setViewportView(dilakukan);

        FormInput.add(scrollPane3);
        scrollPane3.setBounds(130, 510, 270, 74);

        tutupLuka.setHighlighter(null);
        tutupLuka.setName("tutupLuka"); // NOI18N
        tutupLuka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tutupLukaKeyPressed(evt);
            }
        });
        FormInput.add(tutupLuka);
        tutupLuka.setBounds(570, 420, 256, 23);

        jLabel22.setText("Penutupan Luka Operasi & kulit");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(390, 420, 170, 23);

        penuylitOps.setHighlighter(null);
        penuylitOps.setName("penuylitOps"); // NOI18N
        penuylitOps.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                penuylitOpsKeyPressed(evt);
            }
        });
        FormInput.add(penuylitOps);
        penuylitOps.setBounds(570, 450, 256, 23);

        jLabel23.setText("Penyulit Operasi :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(400, 450, 170, 23);

        jLabel24.setText("Jumlah Pendarahan :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(400, 480, 170, 23);

        jmlDarah.setHighlighter(null);
        jmlDarah.setName("jmlDarah"); // NOI18N
        jmlDarah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jmlDarahKeyPressed(evt);
            }
        });
        FormInput.add(jmlDarah);
        jmlDarah.setBounds(570, 480, 256, 23);

        hasilOps1.setHighlighter(null);
        hasilOps1.setName("hasilOps1"); // NOI18N
        hasilOps1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                hasilOps1KeyPressed(evt);
            }
        });
        FormInput.add(hasilOps1);
        hasilOps1.setBounds(570, 510, 256, 23);

        jLabel25.setText("Hasil Operasi :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(400, 510, 170, 23);

        hasilOps2.setHighlighter(null);
        hasilOps2.setName("hasilOps2"); // NOI18N
        hasilOps2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                hasilOps2KeyPressed(evt);
            }
        });
        FormInput.add(hasilOps2);
        hasilOps2.setBounds(570, 540, 256, 23);

        bahanAnastesi1.setHighlighter(null);
        bahanAnastesi1.setName("bahanAnastesi1"); // NOI18N
        bahanAnastesi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bahanAnastesi1KeyPressed(evt);
            }
        });
        FormInput.add(bahanAnastesi1);
        bahanAnastesi1.setBounds(570, 570, 256, 23);

        jLabel26.setText("Lain-lain yang perlu :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(420, 570, 145, 23);

        noKo1.setHighlighter(null);
        noKo1.setName("noKo1"); // NOI18N
        noKo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noKo1ActionPerformed(evt);
            }
        });
        noKo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                noKo1KeyPressed(evt);
            }
        });
        FormInput.add(noKo1);
        noKo1.setBounds(480, 190, 100, 23);

        ronde1.setHighlighter(null);
        ronde1.setName("ronde1"); // NOI18N
        ronde1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ronde1ActionPerformed(evt);
            }
        });
        ronde1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ronde1KeyPressed(evt);
            }
        });
        FormInput.add(ronde1);
        ronde1.setBounds(660, 190, 100, 23);

        jLabel31.setText("Diagnosis Pasca Bedah :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(0, 190, 145, 23);

        PostOp.setHighlighter(null);
        PostOp.setName("PostOp"); // NOI18N
        PostOp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PostOpKeyPressed(evt);
            }
        });
        FormInput.add(PostOp);
        PostOp.setBounds(150, 190, 256, 23);

        jLabel27.setText("Insisi kulit & pembukaan");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(0, 220, 150, 23);

        scrollPane1.setViewportView(FormInput);

        PanelInput.add(scrollPane1, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgCariTagihanOperasi form=new DlgCariTagihanOperasi(null,false);
        //form.emptTeks();      
        form.setPasien(TNoRw.getText());
        form.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        form.setLocationRelativeTo(internalFrame1);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
            dispose();  
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){            
            dispose();              
        }else{Valid.pindah(evt,TCariPaket,BtnCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed
/*
private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
    Valid.pindah(evt,BtnCari,Nm);
}//GEN-LAST:event_TKdKeyPressed
*/

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnSimpan, BtnKeluar);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

private void kdoperator1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdoperator1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            nmoperator1.setText(dokter.tampil3(kdoperator1.getText()));
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            BtnOperator1ActionPerformed(null);
        }else{
            Valid.pindah(evt,tgl,kdoperator2);
        }
}//GEN-LAST:event_kdoperator1KeyPressed

private void BtnOperator1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnOperator1ActionPerformed
        pilihan=1;
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
}//GEN-LAST:event_BtnOperator1ActionPerformed

private void tglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglKeyPressed
        Valid.pindah(evt,jenis,BtnOperator1);
}//GEN-LAST:event_tglKeyPressed

private void ppBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBersihkanActionPerformed
            int row2=tabMode.getRowCount();
            for(int r=0;r<row2;r++){ 
                tabMode.setValueAt("",r,0);
            }
}//GEN-LAST:event_ppBersihkanActionPerformed

private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            Sequel.cariIsi("select concat(pasien.no_rkm_medis,', ',pasien.nm_pasien) from reg_periksa inner join pasien "+
                        " on pasien.no_rkm_medis=reg_periksa.no_rkm_medis where reg_periksa.no_rawat=? ",TPasien,TNoRw.getText());
        }else{            
            Valid.pindah(evt,BtnCari,kdoperator1);
        }
}//GEN-LAST:event_TNoRwKeyPressed

private void jenisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jenisKeyPressed
    Valid.pindah(evt,Kategori,tgl);
}//GEN-LAST:event_jenisKeyPressed

private void kdasistoperator1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdasistoperator1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnAsis1ActionPerformed(null);
        }else{
            Valid.pindah(evt,kdInstrumen,kdasistoperator2);
        }
          
}//GEN-LAST:event_kdasistoperator1KeyPressed

private void btnAsis1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsis1ActionPerformed
   pilihan=1;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
}//GEN-LAST:event_btnAsis1ActionPerformed

private void TCariPaketKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariPaketKeyPressed
    if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCari2ActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari2.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariPaketKeyPressed

private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed
   tampil();
}//GEN-LAST:event_BtnCari2ActionPerformed

private void BtnCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari2KeyPressed
// TODO add your handling code here:
}//GEN-LAST:event_BtnCari2KeyPressed

private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
  TCariPaket.setText("");
  tampil();
}//GEN-LAST:event_BtnAll1ActionPerformed

private void BtnAll1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll1KeyPressed
// TODO add your handling code here:
}//GEN-LAST:event_BtnAll1KeyPressed

private void BtnTambahOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahOperasiActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgJnsPerawatanOperasi produsen=new DlgJnsPerawatanOperasi(null,false);
        produsen.emptTeks();   
        produsen.isCek();
        produsen.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
        produsen.setLocationRelativeTo(internalFrame1);
        produsen.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor()); 
}//GEN-LAST:event_BtnTambahOperasiActionPerformed

private void tbtindakanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbtindakanMouseClicked
       if(tbtindakan.getRowCount()!=0){
            try {
                getMasalah();
                getData2();
            } catch (java.lang.NullPointerException e) {
                System.out.println(e);
            }
        }
       
        LTotal.setText("Total Biaya : "+Valid.SetAngka(biayaobat+biayatindakan));
}//GEN-LAST:event_tbtindakanMouseClicked

private void tbtindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbtindakanKeyPressed
    if(tbtindakan.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                try {
                    int row=tbtindakan.getSelectedColumn();
                    if((row!=0)||(row!=28)){
                        if(tbtindakan.getSelectedRow()>-1){
                          tbtindakan.setValueAt(true,tbtindakan.getSelectedRow(),0);   
                        }                               
                        TCariPaket.setText("");
                        TCariPaket.requestFocus();
                    }                    
                    getData2();
                } catch (java.lang.NullPointerException e) {
                }
            }else if((evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData2();
                } catch (java.lang.NullPointerException e) {
                }
            }
    }
    LTotal.setText("Total Biaya : "+Valid.SetAngka(biayaobat+biayatindakan));
}//GEN-LAST:event_tbtindakanKeyPressed

private void kdoperator2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdoperator2KeyPressed
    if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            nmoperator2.setText(dokter.tampil3(kdoperator2.getText()));
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            BtnOperator2ActionPerformed(null);
        }else{
            Valid.pindah(evt,kdoperator1,kdoperator3);
        }
}//GEN-LAST:event_kdoperator2KeyPressed

private void BtnOperator2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnOperator2ActionPerformed
      pilihan=2;
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
}//GEN-LAST:event_BtnOperator2ActionPerformed

private void kdoperator3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdoperator3KeyPressed
   if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            nmoperator3.setText(dokter.tampil3(kdoperator3.getText()));
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnOperator3ActionPerformed(null);
        }else{
            Valid.pindah(evt,kdoperator2,kdanestesi);
        }
}//GEN-LAST:event_kdoperator3KeyPressed

private void btnOperator3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOperator3ActionPerformed
  pilihan=3;
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
}//GEN-LAST:event_btnOperator3ActionPerformed

private void kdanestesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdanestesiKeyPressed
   if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            nmanestesi.setText(dokter.tampil3(kdanestesi.getText()));
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            BtnAnastesiActionPerformed(null);
        }else{
            Valid.pindah(evt,kdoperator3,kddranak);
        }
}//GEN-LAST:event_kdanestesiKeyPressed

private void BtnAnastesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAnastesiActionPerformed
        pilihan=4;
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
}//GEN-LAST:event_BtnAnastesiActionPerformed

private void kddranakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kddranakKeyPressed
    if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            nmdranak.setText(dokter.tampil3(kddranak.getText()));
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnAnakActionPerformed(null);
        }else{
            Valid.pindah(evt,kdanestesi,kdbidan);
        }
}//GEN-LAST:event_kddranakKeyPressed

private void btnAnakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnakActionPerformed
        pilihan=5;
        dokter.emptTeks();        
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
}//GEN-LAST:event_btnAnakActionPerformed

private void btnAsis2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsis2ActionPerformed
   pilihan=2;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
}//GEN-LAST:event_btnAsis2ActionPerformed

private void kdasistoperator2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdasistoperator2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnAsis2ActionPerformed(null);
        }else{
            Valid.pindah(evt,kdasistoperator1,kdasistoperator3);
        }
}//GEN-LAST:event_kdasistoperator2KeyPressed

private void btnAsis3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsis3ActionPerformed
  pilihan=3;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
}//GEN-LAST:event_btnAsis3ActionPerformed

private void kdInstrumenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdInstrumenKeyPressed
   if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            Sequel.cariIsi("select nama from petugas where nip='"+kdInstrumen.getText()+"'",nminstrumen);            
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnAsis3ActionPerformed(null);
        }else{
            Valid.pindah(evt,kdprwluar,kdasistoperator1);
        }         
}//GEN-LAST:event_kdInstrumenKeyPressed

private void btnPrwResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrwResActionPerformed
    pilihan=5;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
}//GEN-LAST:event_btnPrwResActionPerformed

private void kdprwresustKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdprwresustKeyPressed
    Valid.pindah(evt,kdasistanestesi2,kdonloop1);
}//GEN-LAST:event_kdprwresustKeyPressed

private void kdasistanestesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdasistanestesiKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            BtnAsnesActionPerformed(null);
        }else{
            Valid.pindah(evt,kdasistoperator3,kdasistanestesi2);
        }
}//GEN-LAST:event_kdasistanestesiKeyPressed

private void BtnAsnesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsnesActionPerformed
   pilihan=4;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
}//GEN-LAST:event_BtnAsnesActionPerformed

private void kdbidanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdbidanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnBidanActionPerformed(null);
        }else{
            Valid.pindah(evt,kddranak,kdbidan2);
        }
}//GEN-LAST:event_kdbidanKeyPressed

private void btnBidanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBidanActionPerformed
    pilihan=7;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
}//GEN-LAST:event_btnBidanActionPerformed

private void kdprwluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdprwluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnPrwLuarActionPerformed(null);
        }else{
            Valid.pindah(evt,kdbidan3,kdInstrumen);
        }
}//GEN-LAST:event_kdprwluarKeyPressed

private void btnPrwLuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrwLuarActionPerformed
   pilihan=6;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
}//GEN-LAST:event_btnPrwLuarActionPerformed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
   isForm();  
}//GEN-LAST:event_ChkInputActionPerformed

    private void KategoriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KategoriKeyPressed
        Valid.pindah(evt,TCariPaket,jenis);
    }//GEN-LAST:event_KategoriKeyPressed

    private void btnBidan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBidan2ActionPerformed
        pilihan=8;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnBidan2ActionPerformed

    private void kdbidan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdbidan2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnBidan2ActionPerformed(null);
        }else{
            Valid.pindah(evt,kdbidan,kdbidan3);
        }
    }//GEN-LAST:event_kdbidan2KeyPressed

    private void kdbidan3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdbidan3KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnBidan3ActionPerformed(null);
        }else{
            Valid.pindah(evt,kdbidan2,kdprwluar);
        }
    }//GEN-LAST:event_kdbidan3KeyPressed

    private void btnBidan3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBidan3ActionPerformed
        pilihan=9;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnBidan3ActionPerformed

    private void kdonloop1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdonloop1KeyPressed
        Valid.pindah(evt,kdprwresust,kdonloop2);
    }//GEN-LAST:event_kdonloop1KeyPressed

    private void btnOnloop1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOnloop1ActionPerformed
        pilihan=10;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnOnloop1ActionPerformed

    private void btnOnloop2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOnloop2ActionPerformed
        pilihan=11;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnOnloop2ActionPerformed

    private void kdonloop2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdonloop2KeyPressed
        Valid.pindah(evt,kdonloop1,kdonloop3);
    }//GEN-LAST:event_kdonloop2KeyPressed

    private void btnOnloop3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOnloop3ActionPerformed
        pilihan=12;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnOnloop3ActionPerformed

    private void kdonloop3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdonloop3KeyPressed
        Valid.pindah(evt,kdonloop2,kdonloop4);
    }//GEN-LAST:event_kdonloop3KeyPressed

    private void kdpjanakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpjanakKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            nmpjanak.setText(dokter.tampil3(kdpjanak.getText()));
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btndrpjanakActionPerformed(null);
        }else{
            Valid.pindah(evt,kdonloop3,kddrumum);
        }
    }//GEN-LAST:event_kdpjanakKeyPressed

    private void btndrpjanakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndrpjanakActionPerformed
        pilihan=6;
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_btndrpjanakActionPerformed

    private void kddrumumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kddrumumKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            nmdranak.setText(dokter.tampil3(kddranak.getText()));
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btndrumumActionPerformed(null);
        }else{
            Valid.pindah(evt,kdpjanak,BtnSimpan);
        }
    }//GEN-LAST:event_kddrumumKeyPressed

    private void btndrumumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndrumumActionPerformed
        pilihan=7;
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_btndrumumActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,kddrumum,BtnKeluar);
        }
    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(kdoperator2.getText().trim().equals("")||nmoperator2.getText().trim().equals("")){
            kdoperator2.setText("-");
            nmoperator2.setText("-");
        }

        if(kdoperator3.getText().trim().equals("")||nmoperator3.getText().trim().equals("")){
            kdoperator3.setText("-");
            nmoperator3.setText("-");
        }

        if(kdanestesi.getText().trim().equals("")||nmanestesi.getText().trim().equals("")){
            kdanestesi.setText("-");
            nmanestesi.setText("-");
        }

        if(kddranak.getText().trim().equals("")||nmdranak.getText().trim().equals("")){
            kddranak.setText("-");
            nmdranak.setText("-");
        }

        if(kdbidan.getText().trim().equals("")||nmbidan.getText().trim().equals("")){
            kdbidan.setText("-");
            nmbidan.setText("-");
        }

        if(kdbidan2.getText().trim().equals("")||nmbidan2.getText().trim().equals("")){
            kdbidan2.setText("-");
            nmbidan2.setText("-");
        }

        if(kdbidan3.getText().trim().equals("")||nmbidan3.getText().trim().equals("")){
            kdbidan3.setText("-");
            nmbidan3.setText("-");
        }

        if(kdonloop1.getText().trim().equals("")||nmonloop1.getText().trim().equals("")){
            kdonloop1.setText("-");
            nmonloop1.setText("-");
        }

        if(kdonloop2.getText().trim().equals("")||nmonloop2.getText().trim().equals("")){
            kdonloop2.setText("-");
            nmonloop2.setText("-");
        }

        if(kdonloop3.getText().trim().equals("")||nmonloop3.getText().trim().equals("")){
            kdonloop3.setText("-");
            nmonloop3.setText("-");
        }
        
        if(kdonloop4.getText().trim().equals("")||nmonloop4.getText().trim().equals("")){
            kdonloop4.setText("-");
            nmonloop4.setText("-");
        }
        
        if(kdonloop5.getText().trim().equals("")||nmonloop5.getText().trim().equals("")){
            kdonloop5.setText("-");
            nmonloop5.setText("-");
        }

        if(kdasistoperator1.getText().trim().equals("")||nmasistoperator1.getText().trim().equals("")){
            kdasistoperator1.setText("-");
            nmasistoperator1.setText("-");
        }

        if(kdasistoperator2.getText().trim().equals("")||nmasistoperator2.getText().trim().equals("")){
            kdasistoperator2.setText("-");
            nmasistoperator2.setText("-");
        }
        
        if(kdasistoperator3.getText().trim().equals("")||nmasistoperator3.getText().trim().equals("")){
            kdasistoperator3.setText("-");
            nmasistoperator3.setText("-");
        }

        if(kdInstrumen.getText().trim().equals("")||nminstrumen.getText().trim().equals("")){
            kdInstrumen.setText("-");
            nminstrumen.setText("-");
        }

        if(kdasistanestesi.getText().trim().equals("")||nmasistanestesi.getText().trim().equals("")){
            kdasistanestesi.setText("-");
            nmasistanestesi.setText("-");
        }
        
        if(kdasistanestesi2.getText().trim().equals("")||nmasistanestesi2.getText().trim().equals("")){
            kdasistanestesi2.setText("-");
            nmasistanestesi2.setText("-");
        }

        if(kdprwresust.getText().trim().equals("")||nmprwresust.getText().trim().equals("")){
            kdprwresust.setText("-");
            nmprwresust.setText("-");
        }

        if(kdprwluar.getText().trim().equals("")||nmprwluar.getText().trim().equals("")){
            kdprwluar.setText("-");
            nmprwluar.setText("-");
        }
        
        if(kdpjanak.getText().trim().equals("")||nmpjanak.getText().trim().equals("")){
            kdpjanak.setText("-");
            nmpjanak.setText("-");
        }
        
        if(kddrumum.getText().trim().equals("")||nmdrumum.getText().trim().equals("")){
            kddrumum.setText("-");
            nmdrumum.setText("-");
        }

        jml=0;
        for(i=0;i<tbtindakan.getRowCount();i++){
            if(tabMode.getValueAt(i,0).toString().equals("true")){
                jml++;
            }
        }
        
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Pasien");
        }else if(jenis.getText().trim().equals("")){
            Valid.textKosong(jenis,"Jenis");
        }else if(kdoperator1.getText().trim().equals("")||nmoperator1.getText().trim().equals("")){
            Valid.textKosong(kdoperator1,"Operator 1");
        }else{            
            if(Sequel.cariRegistrasi(TNoRw.getText())>0){
                JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi, data tidak boleh dihapus.\nSilahkan hubungi bagian kasir/keuangan ..!!");
                TCariPaket.requestFocus();
            }else{
                Sequel.AutoComitFalse();
                sukses=true;
                ttljmdokter=0;ttljmpetugas=0;ttlpendapatan=0;ttlbhp=0;
                for(i=0;i<tbtindakan.getRowCount();i++){
                    if(tabMode.getValueAt(i,0).toString().equals("true")){
                       if(Sequel.menyimpantf2(  "operasi",
                        "'" + TNoRw.getText() + "'," +
                        "'" + Valid.SetTgl(tgl.getSelectedItem()+"") + " " +
                              tgl.getSelectedItem().toString().substring(11,19) + "'," +
                        "'" + jenis.getText() + "'," +
                        "'" + Kategori.getSelectedItem() + "'," +
                        "'" + kdoperator1.getText() + "'," +
                        "'" + kdoperator2.getText() + "'," +
                        "'" + kdoperator3.getText() + "'," +
                        "'" + kdasistoperator1.getText() + "'," +
                        "'" + kdasistoperator2.getText() + "'," +
                        "'" + kdasistoperator3.getText() + "'," +
                        "'" + kdInstrumen.getText() + "'," +
                        "'" + kddranak.getText() + "'," +
                        "'" + kdprwresust.getText() + "'," +
                        "'" + kdanestesi.getText() + "'," +
                        "'" + kdasistanestesi.getText() + "'," +
                        "'" + kdasistanestesi2.getText() + "'," +
                        "'" + kdbidan.getText() + "'," +
                        "'" + kdbidan2.getText() + "'," +
                        "'" + kdbidan3.getText() + "'," +
                        "'" + kdprwluar.getText() + "'," +
                        "'" + kdonloop1.getText() + "'," +
                        "'" + kdonloop2.getText() + "'," +
                        "'" + kdonloop3.getText() + "'," +
                        "'" + kdonloop4.getText() + "'," +
                        "'" + kdonloop5.getText() + "'," +
                        "'" + kdpjanak.getText() + "'," +
                        "'" + kddrumum.getText() + "'," +
                        "'" + tbtindakan.getValueAt(i,1) + "'," +

                        "'" + tbtindakan.getValueAt(i,4) + "'," +
                        "'" + tbtindakan.getValueAt(i,5) + "'," +
                        "'" + tbtindakan.getValueAt(i,6) + "'," +
                        "'" + tbtindakan.getValueAt(i,7) + "'," +
                        "'" + tbtindakan.getValueAt(i,8) + "'," +
                        "'" + tbtindakan.getValueAt(i,9) + "'," +
                        "'" + tbtindakan.getValueAt(i,10) + "'," +
                        "'" + tbtindakan.getValueAt(i,11) + "'," +
                        "'" + tbtindakan.getValueAt(i,12) + "'," +
                        "'" + tbtindakan.getValueAt(i,13) + "'," +
                        "'" + tbtindakan.getValueAt(i,14) + "'," +
                        "'" + tbtindakan.getValueAt(i,15) + "'," +
                        "'" + tbtindakan.getValueAt(i,16) + "'," +
                        "'" + tbtindakan.getValueAt(i,17) + "'," +
                        "'" + tbtindakan.getValueAt(i,18) + "'," +
                        "'" + tbtindakan.getValueAt(i,19) + "'," +
                        "'" + tbtindakan.getValueAt(i,20) + "'," +
                        "'" + tbtindakan.getValueAt(i,21) + "'," +
                        "'" + tbtindakan.getValueAt(i,22) + "'," +
                        "'" + tbtindakan.getValueAt(i,23) + "'," +
                        "'" + tbtindakan.getValueAt(i,24) + "'," +
                        "'" + tbtindakan.getValueAt(i,25) + "'," +
                        "'" + tbtindakan.getValueAt(i,26) + "'," +
                        "'" + tbtindakan.getValueAt(i,27) + "'," +
                        "'" + tbtindakan.getValueAt(i,28) + "'," +
                        "'" + tbtindakan.getValueAt(i,29) + "'," +
                        "'" + tbtindakan.getValueAt(i,30) + "'," +
                      "'" + tbtindakan.getValueAt(i,31) + "'," 
                        + "'" + status + "'"
                        ,
                        "data"

             
                      
                    )==true)
                    {

                                                String kode_paket = tbtindakan.getValueAt(i,1).toString();
                            String norawat = TNoRw.getText();
                            String status_lanjut = Sequel.cariIsi("SELECT status_lanjut FROM reg_periksa WHERE no_rawat = '"+norawat+"'");
                            System.out.println("Status Lanjut : "+status_lanjut);
                            String depo = "";
                            
                            if(status_lanjut.equals("Ralan")){
                                depo = "AP1";
                            } else {
                                depo = "AP2";
                            }
                            
                            System.out.println("Depo yang Dipakai : "+depo);
                            
                            ArrayList<String[]> listAlkes = Sequel.ambilDuaKolom("SELECT kode_brng, jml FROM alkes_paket_operasi WHERE kode_paket = '"+kode_paket+"'");
                            
                            for (String[] baris : listAlkes) {
                            
                                System.out.println("Kode Barang: " + baris[0] + ", Jumlah: " + baris[1]);
                                Sequel.menyimpan("gudangbarang","'"+baris[0]+"','"+depo+"','-"+(Double.parseDouble(baris[1]))+"','',''", 
                                                    "stok=stok-'"+(Double.parseDouble(baris[1]))+"'","kode_brng='"+baris[0]+"' and kd_bangsal='"+depo+"' and no_batch='' and no_faktur=''"); 
                            }
                            
                            ttljmdokter=ttljmdokter+Double.parseDouble(tbtindakan.getValueAt(i,4).toString())+
                                    Double.parseDouble(tbtindakan.getValueAt(i,5).toString())+
                                    Double.parseDouble(tbtindakan.getValueAt(i,6).toString())+
                                    Double.parseDouble(tbtindakan.getValueAt(i,11).toString())+
                                    Double.parseDouble(tbtindakan.getValueAt(i,13).toString())+
                                    Double.parseDouble(tbtindakan.getValueAt(i,30).toString())+
                                    Double.parseDouble(tbtindakan.getValueAt(i,31).toString());
                            ttljmpetugas=ttljmpetugas+Double.parseDouble(tbtindakan.getValueAt(i,7).toString())+
                                    Double.parseDouble(tbtindakan.getValueAt(i,8).toString())+
                                    Double.parseDouble(tbtindakan.getValueAt(i,9).toString())+
                                    Double.parseDouble(tbtindakan.getValueAt(i,10).toString())+
                                    Double.parseDouble(tbtindakan.getValueAt(i,12).toString())+
                                    Double.parseDouble(tbtindakan.getValueAt(i,14).toString())+
                                    Double.parseDouble(tbtindakan.getValueAt(i,15).toString())+
                                    Double.parseDouble(tbtindakan.getValueAt(i,16).toString())+
                                    Double.parseDouble(tbtindakan.getValueAt(i,17).toString())+
                                    Double.parseDouble(tbtindakan.getValueAt(i,18).toString())+
                                    Double.parseDouble(tbtindakan.getValueAt(i,19).toString())+
                                    Double.parseDouble(tbtindakan.getValueAt(i,24).toString())+
                                    Double.parseDouble(tbtindakan.getValueAt(i,25).toString())+
                                    Double.parseDouble(tbtindakan.getValueAt(i,26).toString())+
                                    Double.parseDouble(tbtindakan.getValueAt(i,27).toString())+
                                    Double.parseDouble(tbtindakan.getValueAt(i,28).toString());
                            ttlpendapatan=ttlpendapatan+Double.parseDouble(tbtindakan.getValueAt(i,32).toString()); 
                        }else{
                            sukses=false;
                        }
                    }
                }
                
//                if(sukses==true){
//                    for(int r=0;r<tbObat.getRowCount();r++){
//                        if(Valid.SetAngka(tbObat.getValueAt(r,0).toString())>0){
//                            if(Sequel.menyimpantf2("beri_obat_operasi","'"+TNoRw.getText()+"','"+Valid.SetTgl(tgl.getSelectedItem()+"")+" "+tgl.getSelectedItem().toString().substring(11,19)+
//                                "','"+tbObat.getValueAt(r,1).toString()+"','"+tbObat.getValueAt(r,4).toString()+
//                                "','"+tbObat.getValueAt(r,0).toString()+"'","data")==true){
//                                ttlbhp=ttlbhp+Double.parseDouble(tbObat.getValueAt(r,5).toString());
//                            }else{
//                                sukses=false;
//                            }
//                        }
//                    }
//                    ttlpendapatan=ttlpendapatan+ttlbhp;
//                }
                    
//    if(!Laporan.getText().equals("")){

    if(Sequel.menyimpantf2(
        "laporan_operasi",
        "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?",
        "laporan operasi",
        22,
        new String[]{
            TNoRw.getText(), // 1 no_rawat
            Valid.SetTgl(tgl.getSelectedItem()+"")+" "+tgl.getSelectedItem().toString().substring(11,19), // 2 tanggal
            PreOp.getText(), // 3 diagnosa_preop
            PostOp.getText(), // 4 diagnosa_postop
            Jaringan.getText(), // 5 jaringan_dieksekusi
            Valid.SetTgl(tgl2.getSelectedItem()+"")+" "+tgl2.getSelectedItem().toString().substring(11,19), // 6 selesaioperasi
            DikirimPA.getSelectedItem().toString(), // 7 permintaan_pa
            Laporan.getText(), // 8 laporan_operasi
            icd9.getText(), // 9 icd9
            jenis.getText(), // 10 jnsAnastesi
            bahanAnastesi.getText(), // 11 bahanAnastesi
            golonganOperasi.getSelectedItem().toString(), // 12 golonganOperasi
            urgensiOps.getSelectedItem().toString(), // 13 urgensiOps
            macamOperasi.getSelectedItem().toString(), // 14 macamOperasi
            waktuAnastesi.getText(), // 15 waktuAnastesi
            selesaiAnastesi.getText(), // 16 selesaiAnastesi
            noKo1.getText(), // 17 noKo
            ronde1.getText(), // 18 ronde
            persiapanOps.getText(), // 19 persiapanOps
            posisiPasien.getText(), // 20 posisiPasien
            desinfeksi.getText(), // 21 desinfeksi
            dilakukan.getText() // 22 dilakukan
        }
    )==false){
        sukses=false;
    }
//}

  
                if(sukses==true){
                    if(status.equals("Ranap")){
                        Sequel.queryu("delete from tampjurnal");    
                        if(ttlpendapatan>0){
                            if(Sequel.menyimpantf("tampjurnal","'"+Suspen_Piutang_Operasi_Ranap+"','Suspen Piutang Operasi Ranap','"+ttlpendapatan+"','0'","debet=debet+'"+(ttlpendapatan)+"'","kd_rek='"+Suspen_Piutang_Operasi_Ranap+"'")==false){
                                sukses=false;
                            }    
                            if(Sequel.menyimpantf("tampjurnal","'"+Operasi_Ranap+"','Pendapatan Operasi Rawat Inap','0','"+ttlpendapatan+"'","kredit=kredit+'"+(ttlpendapatan)+"'","kd_rek='"+Operasi_Ranap+"'")==false){
                                sukses=false;
                            }                                
                        }
                        if(ttljmdokter>0){
                            if(Sequel.menyimpantf("tampjurnal","'"+Beban_Jasa_Medik_Dokter_Operasi_Ranap+"','Beban Jasa Medik Dokter Operasi Ranap','"+ttljmdokter+"','0'","debet=debet+'"+(ttljmdokter)+"'","kd_rek='"+Beban_Jasa_Medik_Dokter_Operasi_Ranap+"'")==false){
                                sukses=false;
                            }  
                            if(Sequel.menyimpantf("tampjurnal","'"+Utang_Jasa_Medik_Dokter_Operasi_Ranap+"','Utang Jasa Medik Dokter Operasi Ranap','0','"+ttljmdokter+"'","kredit=kredit+'"+(ttljmdokter)+"'","kd_rek='"+Utang_Jasa_Medik_Dokter_Operasi_Ranap+"'")==false){
                                sukses=false;
                            }                              
                        }
                        if(ttljmpetugas>0){
                            if(Sequel.menyimpantf("tampjurnal","'"+Beban_Jasa_Medik_Paramedis_Operasi_Ranap+"','Beban Jasa Medik Petugas Operasi Ranap','"+ttljmpetugas+"','0'","debet=debet+'"+(ttljmpetugas)+"'","kd_rek='"+Beban_Jasa_Medik_Paramedis_Operasi_Ranap+"'")==false){
                                sukses=false;
                            }   
                            if(Sequel.menyimpantf("tampjurnal","'"+Utang_Jasa_Medik_Paramedis_Operasi_Ranap+"','Utang Jasa Medik Petugas Operasi Ranap','0','"+ttljmpetugas+"'","kredit=kredit+'"+(ttljmpetugas)+"'","kd_rek='"+Utang_Jasa_Medik_Paramedis_Operasi_Ranap+"'")==false){
                                sukses=false;
                            }                                 
                        }
                        if(ttlbhp>0){
                            if(Sequel.menyimpantf("tampjurnal","'"+HPP_Obat_Operasi_Ranap+"','HPP Persediaan Operasi Rawat Inap','"+ttlbhp+"','0'","debet=debet+'"+(ttlbhp)+"'","kd_rek='"+HPP_Obat_Operasi_Ranap+"'")==false){
                                sukses=false;
                            }     
                            if(Sequel.menyimpantf("tampjurnal","'"+Persediaan_Obat_Kamar_Operasi_Ranap+"','Persediaan BHP Operasi Rawat Inap','0','"+ttlbhp+"'","kredit=kredit+'"+(ttlbhp)+"'","kd_rek='"+Persediaan_Obat_Kamar_Operasi_Ranap+"'")==false){
                                sukses=false;
                            }                                
                        }
                        if(sukses==true){
                            sukses=jur.simpanJurnal(TNoRw.getText(),"U","OPERASI RAWAT INAP PASIEN "+TPasien.getText()+" DIPOSTING OLEH "+akses.getkode()); 
                        }                                             
                    }else if(status.equals("Ralan")){
                        Sequel.queryu("delete from tampjurnal");    
                        if(ttlpendapatan>0){
                            if(Sequel.menyimpantf("tampjurnal","'"+Suspen_Piutang_Operasi_Ralan+"','Suspen Piutang Operasi Ralan','"+ttlpendapatan+"','0'","debet=debet+'"+(ttlpendapatan)+"'","kd_rek='"+Suspen_Piutang_Operasi_Ralan+"'")==false){
                                sukses=false;
                            }    
                            if(Sequel.menyimpantf("tampjurnal","'"+Operasi_Ralan+"','Pendapatan Operasi Rawat Inap','0','"+ttlpendapatan+"'","kredit=kredit+'"+(ttlpendapatan)+"'","kd_rek='"+Operasi_Ralan+"'")==false){
                                sukses=false;
                            }                                
                        }
                        if(ttljmdokter>0){
                            if(Sequel.menyimpantf("tampjurnal","'"+Beban_Jasa_Medik_Dokter_Operasi_Ralan+"','Beban Jasa Medik Dokter Operasi Ralan','"+ttljmdokter+"','0'","debet=debet+'"+(ttljmdokter)+"'","kd_rek='"+Beban_Jasa_Medik_Dokter_Operasi_Ralan+"'")==false){
                                sukses=false;
                            }  
                            if(Sequel.menyimpantf("tampjurnal","'"+Utang_Jasa_Medik_Dokter_Operasi_Ralan+"','Utang Jasa Medik Dokter Operasi Ralan','0','"+ttljmdokter+"'","kredit=kredit+'"+(ttljmdokter)+"'","kd_rek='"+Utang_Jasa_Medik_Dokter_Operasi_Ralan+"'")==false){
                                sukses=false;
                            }                              
                        }
                        if(ttljmpetugas>0){
                            if(Sequel.menyimpantf("tampjurnal","'"+Beban_Jasa_Medik_Paramedis_Operasi_Ralan+"','Beban Jasa Medik Petugas Operasi Ralan','"+ttljmpetugas+"','0'","debet=debet+'"+(ttljmpetugas)+"'","kd_rek='"+Beban_Jasa_Medik_Paramedis_Operasi_Ralan+"'")==false){
                                sukses=false;
                            }   
                            if(Sequel.menyimpantf("tampjurnal","'"+Utang_Jasa_Medik_Paramedis_Operasi_Ralan+"','Utang Jasa Medik Petugas Operasi Ralan','0','"+ttljmpetugas+"'","kredit=kredit+'"+(ttljmpetugas)+"'","kd_rek='"+Utang_Jasa_Medik_Paramedis_Operasi_Ralan+"'")==false){
                                sukses=false;
                            }                                 
                        }
                        if(ttlbhp>0){
                            if(Sequel.menyimpantf("tampjurnal","'"+HPP_Obat_Operasi_Ralan+"','HPP Persediaan Operasi Rawat Jalan','"+ttlbhp+"','0'","debet=debet+'"+(ttlbhp)+"'","kd_rek='"+HPP_Obat_Operasi_Ralan+"'")==false){
                                sukses=false;
                            }     
                            if(Sequel.menyimpantf("tampjurnal","'"+Persediaan_Obat_Kamar_Operasi_Ralan+"','Persediaan BHP Operasi Rawat Jalan','0','"+ttlbhp+"'","kredit=kredit+'"+(ttlbhp)+"'","kd_rek='"+Persediaan_Obat_Kamar_Operasi_Ralan+"'")==false){
                                sukses=false;
                            }                                
                        }
                        if(sukses==true){
                            sukses=jur.simpanJurnal(TNoRw.getText(),"U","OPERASI RAWAT JALAN PASIEN "+TPasien.getText()+" DIPOSTING OLEH "+akses.getkode()); 
                        }                                             
                    }
                }
                    
                if(sukses==true){
                    Sequel.Commit();
                    for(int r=0;r<tbtindakan.getRowCount();r++){
                        tbtindakan.setValueAt(false,r,0);
                    }
                    tampil();
//                    for(int r=0;r<tbObat.getRowCount();r++){
//                        tbObat.setValueAt("",r,0);
//                    }
//                    tampil2();
                    LTotal.setText("Total Biaya : 0");
                    PreOp.setText("");
                    waktuAnastesi.setText("");
                    Laporan.setText("");
                    Laporan.setText("");
                    jenis.setText("");
                    JOptionPane.showMessageDialog(rootPane,"Proses simpan selesai...!");
                }else{
                    JOptionPane.showMessageDialog(null,"Terjadi kesalahan saat pemrosesan data, transaksi dibatalkan.\nPeriksa kembali data sebelum melanjutkan menyimpan..!!");
                    Sequel.RollBack();
                }
                Sequel.AutoComitTrue();
            }
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void kdasistoperator3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdasistoperator3KeyPressed
        Valid.pindah(evt,kdasistoperator2,kdasistanestesi);
    }//GEN-LAST:event_kdasistoperator3KeyPressed

    private void btnAsis4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsis4ActionPerformed
        pilihan=15;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnAsis4ActionPerformed

    private void kdasistanestesi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdasistanestesi2KeyPressed
        Valid.pindah(evt,kdasistanestesi,kdprwresust);
    }//GEN-LAST:event_kdasistanestesi2KeyPressed

    private void BtnAsnes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAsnes1ActionPerformed
        pilihan=16;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnAsnes1ActionPerformed

    private void kdonloop4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdonloop4KeyPressed
        Valid.pindah(evt,kdonloop3,kdonloop5);
    }//GEN-LAST:event_kdonloop4KeyPressed

    private void btnOnloop4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOnloop4ActionPerformed
        pilihan=13;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnOnloop4ActionPerformed

    private void btnOnloop5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOnloop5ActionPerformed
        pilihan=14;
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnOnloop5ActionPerformed

    private void kdonloop5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdonloop5KeyPressed
        Valid.pindah(evt,kdonloop4,tgl2);
    }//GEN-LAST:event_kdonloop5KeyPressed

    private void tgl2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl2KeyPressed
        Valid.pindah(evt,kdonloop5,PreOp);
    }//GEN-LAST:event_tgl2KeyPressed

    private void PreOpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PreOpKeyPressed
        Valid.pindah(evt,tgl2,waktuAnastesi);
    }//GEN-LAST:event_PreOpKeyPressed

    private void waktuAnastesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_waktuAnastesiKeyPressed
//        Valid.pindah(evt,PreOp,jamMulaiAnastesi);
    }//GEN-LAST:event_waktuAnastesiKeyPressed

    private void DikirimPAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DikirimPAKeyPressed
//        Valid.pindah(evt,jamMulaiAnastesi,Laporan);
    }//GEN-LAST:event_DikirimPAKeyPressed

    private void BtnOperator1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnOperator1KeyPressed
        Valid.pindah(evt,tgl,BtnOperator2);
    }//GEN-LAST:event_BtnOperator1KeyPressed

    private void BtnOperator2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnOperator2KeyPressed
        Valid.pindah(evt,BtnOperator1,btnOperator3);
    }//GEN-LAST:event_BtnOperator2KeyPressed

    private void btnOperator3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnOperator3KeyPressed
        Valid.pindah(evt,BtnOperator2,BtnAnastesi);
    }//GEN-LAST:event_btnOperator3KeyPressed

    private void btnTemplateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTemplateActionPerformed
        MasterCariTemplateLaporanOperasi template=new MasterCariTemplateLaporanOperasi(null,false);
        template.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(template.getTable().getSelectedRow()!= -1){  
                    PreOp.setText(template.getTable().getValueAt(template.getTable().getSelectedRow(),2).toString());
                    waktuAnastesi.setText(template.getTable().getValueAt(template.getTable().getSelectedRow(),3).toString());
                    waktuAnastesi.setText(template.getTable().getValueAt(template.getTable().getSelectedRow(),4).toString());
                    DikirimPA.setSelectedItem(template.getTable().getValueAt(template.getTable().getSelectedRow(),5).toString());
                    Laporan.setText(template.getTable().getValueAt(template.getTable().getSelectedRow(),6).toString());
                    Laporan.requestFocus();
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
        template.emptTeks();
        template.isCek();
        template.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        template.setLocationRelativeTo(internalFrame1);
        template.setVisible(true);
    }//GEN-LAST:event_btnTemplateActionPerformed

    private void jnsAnastesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jnsAnastesiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jnsAnastesiKeyPressed

    private void JaringanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JaringanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JaringanKeyPressed

    private void bahanAnastesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bahanAnastesiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_bahanAnastesiKeyPressed

    private void golonganOperasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_golonganOperasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_golonganOperasiKeyPressed

    private void macamOperasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_macamOperasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_macamOperasiKeyPressed

    private void urgensiOpsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_urgensiOpsKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_urgensiOpsKeyPressed

    private void icd9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_icd9KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_icd9KeyPressed

    private void waktuAnastesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_waktuAnastesiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_waktuAnastesiActionPerformed

    private void selesaiAnastesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selesaiAnastesiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selesaiAnastesiActionPerformed

    private void selesaiAnastesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_selesaiAnastesiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_selesaiAnastesiKeyPressed

    private void persiapanOpsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_persiapanOpsKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_persiapanOpsKeyPressed

    private void posisiPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_posisiPasienKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_posisiPasienKeyPressed

    private void desinfeksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_desinfeksiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_desinfeksiKeyPressed

    private void tutupLukaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tutupLukaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tutupLukaKeyPressed

    private void penuylitOpsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_penuylitOpsKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_penuylitOpsKeyPressed

    private void jmlDarahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jmlDarahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jmlDarahKeyPressed

    private void hasilOps1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hasilOps1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_hasilOps1KeyPressed

    private void hasilOps2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hasilOps2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_hasilOps2KeyPressed

    private void bahanAnastesi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bahanAnastesi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_bahanAnastesi1KeyPressed

    private void noKo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noKo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noKo1ActionPerformed

    private void noKo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noKo1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_noKo1KeyPressed

    private void ronde1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ronde1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ronde1ActionPerformed

    private void ronde1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ronde1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ronde1KeyPressed

    private void PostOpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PostOpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PostOpKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgTagihanOperasi dialog = new DlgTagihanOperasi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll1;
    private widget.Button BtnAnastesi;
    private widget.Button BtnAsnes;
    private widget.Button BtnAsnes1;
    private widget.Button BtnCari;
    private widget.Button BtnCari2;
    private widget.Button BtnKeluar;
    private widget.Button BtnOperator1;
    private widget.Button BtnOperator2;
    private widget.Button BtnSimpan;
    private widget.Button BtnTambahOperasi;
    private widget.CekBox ChkInput;
    private widget.ComboBox DikirimPA;
    private widget.PanelBiasa FormDetailBHP;
    private widget.panelisi FormInput;
    private widget.TextBox Jaringan;
    private widget.ComboBox Kategori;
    private widget.TextBox Kd2;
    private widget.Label LTotal;
    private widget.TextArea Laporan;
    private widget.PanelBiasa PanelAccor;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPopupMenu Popup;
    private widget.TextBox PostOp;
    private widget.TextBox PreOp;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll7;
    private widget.TextBox TCariPaket;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox bahanAnastesi;
    private widget.TextBox bahanAnastesi1;
    private widget.Button btnAnak;
    private widget.Button btnAsis1;
    private widget.Button btnAsis2;
    private widget.Button btnAsis3;
    private widget.Button btnAsis4;
    private widget.Button btnBidan;
    private widget.Button btnBidan2;
    private widget.Button btnBidan3;
    private widget.Button btnOnloop1;
    private widget.Button btnOnloop2;
    private widget.Button btnOnloop3;
    private widget.Button btnOnloop4;
    private widget.Button btnOnloop5;
    private widget.Button btnOperator3;
    private widget.Button btnPrwLuar;
    private widget.Button btnPrwRes;
    private widget.Button btnTemplate;
    private widget.Button btndrpjanak;
    private widget.Button btndrumum;
    private widget.TextBox desinfeksi;
    private widget.TextArea dilakukan;
    private widget.ComboBox golonganOperasi;
    private widget.TextBox hasilOps1;
    private widget.TextBox hasilOps2;
    private widget.TextBox icd9;
    private widget.InternalFrame internalFrame1;
    private javax.swing.JLabel jLabel1;
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
    private widget.Label jLabel27;
    private widget.Label jLabel3;
    private widget.Label jLabel31;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox jenis;
    private widget.TextBox jmlDarah;
    private widget.TextBox jnsAnastesi;
    private widget.TextBox kdInstrumen;
    private widget.TextBox kdanestesi;
    private widget.TextBox kdasistanestesi;
    private widget.TextBox kdasistanestesi2;
    private widget.TextBox kdasistoperator1;
    private widget.TextBox kdasistoperator2;
    private widget.TextBox kdasistoperator3;
    private widget.TextBox kdbidan;
    private widget.TextBox kdbidan2;
    private widget.TextBox kdbidan3;
    private widget.TextBox kddranak;
    private widget.TextBox kddrumum;
    private widget.TextBox kdonloop1;
    private widget.TextBox kdonloop2;
    private widget.TextBox kdonloop3;
    private widget.TextBox kdonloop4;
    private widget.TextBox kdonloop5;
    private widget.TextBox kdoperator1;
    private widget.TextBox kdoperator2;
    private widget.TextBox kdoperator3;
    private widget.TextBox kdpjanak;
    private widget.TextBox kdprwluar;
    private widget.TextBox kdprwresust;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label14;
    private widget.Label label15;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label19;
    private widget.Label label20;
    private widget.Label label21;
    private widget.Label label22;
    private widget.Label label23;
    private widget.Label label24;
    private widget.Label label25;
    private widget.Label label26;
    private widget.Label label27;
    private widget.Label label28;
    private widget.Label label29;
    private widget.Label label30;
    private widget.Label label31;
    private widget.Label label32;
    private widget.Label label33;
    private widget.Label label34;
    private widget.Label label35;
    private widget.Label label36;
    private widget.Label label37;
    private widget.Label label38;
    private widget.ComboBox macamOperasi;
    private widget.TextBox nmanestesi;
    private widget.TextBox nmasistanestesi;
    private widget.TextBox nmasistanestesi2;
    private widget.TextBox nmasistoperator1;
    private widget.TextBox nmasistoperator2;
    private widget.TextBox nmasistoperator3;
    private widget.TextBox nmbidan;
    private widget.TextBox nmbidan2;
    private widget.TextBox nmbidan3;
    private widget.TextBox nmdranak;
    private widget.TextBox nmdrumum;
    private widget.TextBox nminstrumen;
    private widget.TextBox nmonloop1;
    private widget.TextBox nmonloop2;
    private widget.TextBox nmonloop3;
    private widget.TextBox nmonloop4;
    private widget.TextBox nmonloop5;
    private widget.TextBox nmoperator1;
    private widget.TextBox nmoperator2;
    private widget.TextBox nmoperator3;
    private widget.TextBox nmpjanak;
    private widget.TextBox nmprwluar;
    private widget.TextBox nmprwresust;
    private widget.TextBox noKo1;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi5;
    private widget.TextBox penuylitOps;
    private widget.TextBox persiapanOps;
    private widget.TextBox posisiPasien;
    private javax.swing.JMenuItem ppBersihkan;
    private widget.TextBox ronde1;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.TextBox selesaiAnastesi;
    private widget.Table tbDetailBHP;
    private widget.Table tbtindakan;
    private widget.Tanggal tgl;
    private widget.Tanggal tgl2;
    private widget.TextBox tutupLuka;
    private widget.ComboBox urgensiOps;
    private widget.TextBox waktuAnastesi;
    // End of variables declaration//GEN-END:variables

    private void tampil() {  

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

    jml=0;
    for(i=0;i<tabMode.getRowCount();i++){
        if(tabMode.getValueAt(i,0).toString().equals("true")){
            jml++;
        }
    }

    pilih=null;
    pilih=new boolean[jml]; 
    kode_paket=null;
    kode_paket=new String[jml];
    kategori=null;
    kategori=new String[jml];
    nm_perawatan=null;
    nm_perawatan=new String[jml];
    operator1=null;
    operator1=new double[jml];
    operator2=null;
    operator2=new double[jml];
    operator3=null;
    operator3=new double[jml];
    asisten_operator1=null;
    asisten_operator1=new double[jml];
    asisten_operator2=null;
    asisten_operator2=new double[jml];
    asisten_operator3=null;
    asisten_operator3=new double[jml];
    instrumen=null;
    instrumen=new double[jml];
    dokter_anak=null;
    dokter_anak=new double[jml];
    perawaat_resusitas=null;
    perawaat_resusitas=new double[jml];
    dokter_anestesi=null;
    dokter_anestesi=new double[jml];
    asisten_anestesi=null;
    asisten_anestesi=new double[jml];
    asisten_anestesi2=null;
    asisten_anestesi2=new double[jml];
    bidan=null;
    bidan=new double[jml];
    bidan2=null;
    bidan2=new double[jml];
    bidan3=null;
    bidan3=new double[jml];
    perawat_luar=null;
    perawat_luar=new double[jml];   
    sewa_ok=null;
    sewa_ok=new double[jml];
    akomodasi=null;
    akomodasi=new double[jml];
    bagian_rs=null;
    bagian_rs=new double[jml];
    omloop=null;
    omloop=new double[jml];
    omloop2=null;
    omloop2=new double[jml];
    omloop3=null;
    omloop3=new double[jml];
    omloop4=null;
    omloop4=new double[jml];
    omloop5=null;
    omloop5=new double[jml];
    sarpras=null;
    sarpras=new double[jml];
    alat=null;
    alat=new double[jml];   
    dokter_pjanak=null;
    dokter_pjanak=new double[jml]; 
    dokter_umum=null;
    dokter_umum=new double[jml]; 
    ttltindakan=null;
    ttltindakan=new double[jml];        
    index=0;        
   
         for(i=0;i<tabMode.getRowCount();i++){
        if(tabMode.getValueAt(i,0).toString().equals("true")){
            pilih[index]=true;
            kode_paket[index]=tabMode.getValueAt(i,1).toString();
            nm_perawatan[index]=tabMode.getValueAt(i,2).toString();
            kategori[index]=tabMode.getValueAt(i,3).toString();
            operator1[index]=Double.parseDouble(tabMode.getValueAt(i,4).toString());
            operator2[index]=Double.parseDouble(tabMode.getValueAt(i,5).toString());
            operator3[index]=Double.parseDouble(tabMode.getValueAt(i,6).toString());
            asisten_operator1[index]=Double.parseDouble(tabMode.getValueAt(i,7).toString());
            asisten_operator2[index]=Double.parseDouble(tabMode.getValueAt(i,8).toString());
            asisten_operator3[index]=Double.parseDouble(tabMode.getValueAt(i,9).toString());
            instrumen[index]=Double.parseDouble(tabMode.getValueAt(i,10).toString());
            dokter_anak[index]=Double.parseDouble(tabMode.getValueAt(i,11).toString());
            perawaat_resusitas[index]=Double.parseDouble(tabMode.getValueAt(i,12).toString());
            dokter_anestesi[index]=Double.parseDouble(tabMode.getValueAt(i,13).toString());
            asisten_anestesi[index]=Double.parseDouble(tabMode.getValueAt(i,14).toString());
            asisten_anestesi2[index]=Double.parseDouble(tabMode.getValueAt(i,15).toString());
            bidan[index]=Double.parseDouble(tabMode.getValueAt(i,16).toString());
            bidan2[index]=Double.parseDouble(tabMode.getValueAt(i,17).toString());
            bidan3[index]=Double.parseDouble(tabMode.getValueAt(i,18).toString());
            perawat_luar[index]=Double.parseDouble(tabMode.getValueAt(i,19).toString());
            alat[index]=Double.parseDouble(tabMode.getValueAt(i,20).toString());   
            sewa_ok[index]=Double.parseDouble(tabMode.getValueAt(i,21).toString());
            akomodasi[index]=Double.parseDouble(tabMode.getValueAt(i,22).toString());  
            bagian_rs[index]=Double.parseDouble(tabMode.getValueAt(i,23).toString());  
            omloop[index]=Double.parseDouble(tabMode.getValueAt(i,24).toString()); 
            omloop2[index]=Double.parseDouble(tabMode.getValueAt(i,25).toString()); 
            omloop3[index]=Double.parseDouble(tabMode.getValueAt(i,26).toString());   
            omloop4[index]=Double.parseDouble(tabMode.getValueAt(i,27).toString());   
            omloop5[index]=Double.parseDouble(tabMode.getValueAt(i,28).toString());   
            sarpras[index]=Double.parseDouble(tabMode.getValueAt(i,29).toString()); 
            dokter_pjanak[index]=Double.parseDouble(tabMode.getValueAt(i,30).toString()); 
            dokter_umum[index]=Double.parseDouble(tabMode.getValueAt(i,31).toString()); 
            ttltindakan[index]=Double.parseDouble(tabMode.getValueAt(i,32).toString());                
            index++;
        }
    }

    Valid.tabelKosong(tabMode);
    for(i=0;i<jml;i++){
        tabMode.addRow(new Object[]{pilih[i],kode_paket[i],nm_perawatan[i],kategori[i],operator1[i],
            operator2[i],operator3[i],asisten_operator1[i],asisten_operator2[i],asisten_operator3[i],
            instrumen[i],dokter_anak[i],perawaat_resusitas[i],dokter_anestesi[i],
            asisten_anestesi[i],asisten_anestesi2[i],bidan[i],bidan2[i],bidan3[i],perawat_luar[i],
            alat[i],sewa_ok[i],akomodasi[i],bagian_rs[i],omloop[i],omloop2[i],
            omloop3[i],omloop4[i],omloop5[i],sarpras[i],dokter_pjanak[i],dokter_umum[i],ttltindakan[i]
        });
    }
//String sql1 =
//   "select kode_paket, nm_perawatan,kategori, operator1, operator2, operator3, "+
//   "asisten_operator1, asisten_operator2,asisten_operator3, instrumen, dokter_anak,perawaat_resusitas,"+
//   "dokter_anestesi, asisten_anestesi, asisten_anestesi2, bidan, bidan2, bidan3, perawat_luar, alat,"+
//   "sewa_ok,akomodasi,bagian_rs,omloop,omloop2,omloop3,omloop4,omloop5,sarpras,dokter_pjanak,dokter_umum,(operator1+operator2+operator3+"+
//   "asisten_operator1+asisten_operator2+asisten_operator3+instrumen+dokter_anak+perawaat_resusitas+"+
//   "alat+dokter_anestesi+asisten_anestesi+asisten_anestesi2+bidan+bidan2+bidan3+perawat_luar+sewa_ok+"+
//   "akomodasi+bagian_rs+omloop+omloop2+omloop3+omloop4+omloop5+sarpras+dokter_pjanak+dokter_umum) as jumlah "+
//   "from paket_operasi "+
//   "where status='1' and kd_pj=? and kelas=? and (kode_paket like ? or nm_perawatan like ?) order by nm_perawatan ";
//
//String debugSql = sql1
//    .replaceFirst("\\?", "'" + kdPjFilter + "'")
//         .replaceFirst("\\?", "'" + kelas + "'")
//    .replaceFirst("\\?", "'%" + TCariPaket.getText() + "%'")
//    .replaceFirst("\\?", "'%" + TCariPaket.getText() + "%'");
//
//System.out.println("FINAL SQL:");
//System.out.println(debugSql);

    try{
        pstindakan=koneksi.prepareStatement(
           "select kode_paket, nm_perawatan,kategori, operator1, operator2, operator3, "+
           "asisten_operator1, asisten_operator2,asisten_operator3, instrumen, dokter_anak,perawaat_resusitas,"+
           "dokter_anestesi, asisten_anestesi, asisten_anestesi2, bidan, bidan2, bidan3, perawat_luar, alat,"+
           "sewa_ok,akomodasi,bagian_rs,omloop,omloop2,omloop3,omloop4,omloop5,sarpras,dokter_pjanak,dokter_umum,(operator1+operator2+operator3+"+
           "asisten_operator1+asisten_operator2+asisten_operator3+instrumen+dokter_anak+perawaat_resusitas+"+
           "alat+dokter_anestesi+asisten_anestesi+asisten_anestesi2+bidan+bidan2+bidan3+perawat_luar+sewa_ok+"+
           "akomodasi+bagian_rs+omloop+omloop2+omloop3+omloop4+omloop5+sarpras+dokter_pjanak+dokter_umum) as jumlah "+
           "from paket_operasi "+
           "where status='1' and kd_pj=? and (kelas=? or kelas='-') and (kode_paket like ? or nm_perawatan like ?) order by nm_perawatan "
        );

        pstindakan2=koneksi.prepareStatement(
           "select kode_paket, nm_perawatan,kategori, operator1, operator2, operator3, "+
           "asisten_operator1, asisten_operator2,asisten_operator3, instrumen, dokter_anak,perawaat_resusitas,"+
           "dokter_anestesi, asisten_anestesi, asisten_anestesi2, bidan, bidan2, bidan3, perawat_luar, alat,"+
           "sewa_ok,akomodasi,bagian_rs,omloop,omloop2,omloop3,omloop4,omloop5,sarpras,dokter_pjanak,dokter_umum,(operator1+operator2+operator3+"+
           "asisten_operator1+asisten_operator2+asisten_operator3+instrumen+dokter_anak+perawaat_resusitas+"+
           "alat+dokter_anestesi+asisten_anestesi+asisten_anestesi2+bidan+bidan2+bidan3+perawat_luar+sewa_ok+"+
           "akomodasi+bagian_rs+omloop+omloop2+omloop3+omloop4+omloop5+sarpras+dokter_pjanak+dokter_umum) as jumlah "+
           "from paket_operasi "+
           "where status='1' and (kode_paket like ? or nm_perawatan like ?) order by nm_perawatan "
        );

        try {
           
            if(cara_bayar_operasi.equals("Yes")&&kelas_operasi.equals("No")){
                 pstindakan.setString(1, kdPjFilter);
                pstindakan.setString(2, kelas.trim());
                pstindakan.setString(3, "%" + TCariPaket.getText() + "%");
                pstindakan.setString(4, "%" + TCariPaket.getText() + "%");

                rs=pstindakan.executeQuery();
            }else if(cara_bayar_operasi.equals("No")&&kelas_operasi.equals("No")){
                pstindakan2.setString(1,"%"+TCariPaket.getText()+"%");
                pstindakan2.setString(2,"%"+TCariPaket.getText()+"%");
                rs=pstindakan2.executeQuery();
            }

            while(rs.next()){
                tabMode.addRow(new Object[]{false,rs.getString("kode_paket"),
                               rs.getString("nm_perawatan"),
                               rs.getString("kategori"), 
                               rs.getDouble("operator1"), 
                               rs.getDouble("operator2"), 
                               rs.getDouble("operator3"), 
                               rs.getDouble("asisten_operator1"), 
                               rs.getDouble("asisten_operator2"), 
                               rs.getDouble("asisten_operator3"), 
                               rs.getDouble("instrumen"), 
                               rs.getDouble("dokter_anak"), 
                               rs.getDouble("perawaat_resusitas"), 
                               rs.getDouble("dokter_anestesi"), 
                               rs.getDouble("asisten_anestesi"), 
                               rs.getDouble("asisten_anestesi2"), 
                               rs.getDouble("bidan"), 
                               rs.getDouble("bidan2"), 
                               rs.getDouble("bidan3"), 
                               rs.getDouble("perawat_luar"), 
                               rs.getDouble("alat"), 
                               rs.getDouble("sewa_ok"), 
                               rs.getDouble("akomodasi"), 
                               rs.getDouble("bagian_rs"), 
                               rs.getDouble("omloop"), 
                               rs.getDouble("omloop2"), 
                               rs.getDouble("omloop3"), 
                               rs.getDouble("omloop4"), 
                               rs.getDouble("omloop5"), 
                               rs.getDouble("sarpras"), 
                               rs.getDouble("dokter_pjanak"), 
                               rs.getDouble("dokter_umum"), 
                               rs.getDouble("jumlah")});
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        } finally{
            if(rs!=null){
                rs.close();
            }
            if(pstindakan!=null){
                pstindakan.close();
            }
            if(pstindakan2!=null){
                pstindakan2.close();
            }
        }                  
    }catch(SQLException e){
        System.out.println("Notifikasi : "+e);
    }
}

    
    //obat
//    private void tampil2() {
//        jml=0;
//        for(i=0;i<tbObat.getRowCount();i++){
//            //System.out.println(tbObat.getValueAt(i,0).toString());
//            if(!tbObat.getValueAt(i,0).toString().equals("")){
//                jml++;
//            }
//        }
//        
//        jmlobat=new double[jml];
//        kd_obat=new String[jml];
//        nm_obat=new String[jml];
//        satuan=new String[jml];
//        hargasatuan=new double[jml];
//        ttlobat=new double[jml];
//        
//        index=0;        
//        for(i=0;i<tabMode2.getRowCount();i++){
//            if(!tabMode2.getValueAt(i,0).toString().equals("")){
//                jmlobat[index]=Double.parseDouble(tabMode2.getValueAt(i,0).toString());
//                kd_obat[index]=tabMode2.getValueAt(i,1).toString();
//                nm_obat[index]=tabMode2.getValueAt(i,2).toString();
//                satuan[index]=tabMode2.getValueAt(i,3).toString();
//                hargasatuan[index]=Double.parseDouble(tabMode2.getValueAt(i,4).toString());
//                ttlobat[index]=Double.parseDouble(tabMode2.getValueAt(i,5).toString());
//                index++;
//            }
//        }
//        
//        Valid.tabelKosong(tabMode2);
//        
//        for(i=0;i<jml;i++){
//            tabMode2.addRow(new Object[]{jmlobat[i],kd_obat[i],nm_obat[i],satuan[i],hargasatuan[i],ttlobat[i]});
//        }
//        
//        try{    
//            psobat=koneksi.prepareStatement("select obatbhp_ok.kd_obat, obatbhp_ok.nm_obat, kodesatuan.satuan, "+
//                       "obatbhp_ok.hargasatuan from obatbhp_ok inner join kodesatuan "+
//                       "on obatbhp_ok.kode_sat=kodesatuan.kode_sat "+
//                       "where obatbhp_ok.kd_obat like ? or "+
//                       "obatbhp_ok.nm_obat like ? or "+
//                       "kodesatuan.satuan like ? "+
//                       "order by obatbhp_ok.kd_obat");
//            try{
//                psobat.setString(1,"%"+TCari.getText()+"%");
//                psobat.setString(2,"%"+TCari.getText()+"%");
//                psobat.setString(3,"%"+TCari.getText()+"%");
//                rs=psobat.executeQuery();
//                while(rs.next()){
//                    tabMode2.addRow(new Object[]{"",rs.getString(1),
//                                   rs.getString(2),
//                                   rs.getString(3),
//                                   rs.getString(4),0});
//                }
//            }catch(SQLException e){
//                System.out.println(e);
//            }finally{
//                if(rs!=null){
//                    rs.close();
//                }
//                if(psobat!=null){
//                    psobat.close();
//                }
//            }
//        }catch(SQLException e){
//            System.out.println("Notifikasi : "+e);
//        }
//    }

    
       
//    private void getData(){
//       int row=tbObat.getSelectedRow();
//        if(row!= -1){         
//           int kolom=tbObat.getSelectedColumn();  
//           if((kolom==0)||(kolom==1)){    
//               if(!tbObat.getValueAt(row,0).toString().equals("")){
//                   try {
//                       tbObat.setValueAt(Valid.SetAngka2(Double.parseDouble(tbObat.getValueAt(row,0).toString())*Double.parseDouble(tbObat.getValueAt(row,4).toString())), row,5);                    
//                   } catch (Exception e) {
//                       tbObat.setValueAt(0, row,5);                    
//                   }
//                }else if(tbObat.getValueAt(row,0).toString().equals("")){
//                    tbObat.setValueAt(0, row,5);   
//                }                 
//            }              
//            
//            biayaobat=0;
//            y=0;
//            int row2=tbObat.getRowCount();
//            for(int r=0;r<row2;r++){ 
//                if(!tbObat.getValueAt(r,5).toString().isEmpty()){
//                    try {
//                        y=Double.parseDouble(tbObat.getValueAt(r,5).toString()); 
//                    } catch (Exception e) {
//                        y=0;
//                    }                                   
//                }else if(tbObat.getValueAt(r,5).toString().isEmpty()){
//                    y=0;                
//                }
//                biayaobat=biayaobat+y;
//            }
//        }
//    }
    
    private void getData2(){
       int row=tbtindakan.getSelectedRow();
        if(row!= -1){         
            if(tbtindakan.getValueAt(tbtindakan.getSelectedRow(),0).toString().equals("true")){
                try {
                    tbtindakan.setValueAt(Double.parseDouble(tbtindakan.getValueAt(row,4).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,5).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,6).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,7).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,8).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,9).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,10).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,11).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,12).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,13).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,14).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,15).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,16).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,17).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,18).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,19).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,20).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,21).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,22).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,23).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,24).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,25).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,26).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,27).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,28).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,29).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,30).toString())+
                                     Double.parseDouble(tbtindakan.getValueAt(row,31).toString()), row,32);
                } catch (Exception e) {
                    tbtindakan.setValueAt(0, row,32);
                }                    
            }
           
            biayatindakan=0;
            y=0;
            int row2=tbtindakan.getRowCount();
            for(int r=0;r<row2;r++){ 
                switch (tbtindakan.getValueAt(r,0).toString()) {
                    case "true":
                        try {
                            y=Double.parseDouble(tbtindakan.getValueAt(r,32).toString());
                        } catch (Exception e) {
                            y=0;
                        }                        
                        break;                
                    case "false":
                        y=0;
                        break;
                }
                biayatindakan=biayatindakan+y;
            }            
        }
    }
    

    private void geserKeAtas(javax.swing.JComponent comp, int shift) {
    comp.setLocation(comp.getX(), comp.getY() - shift);
}

    public void isCek(){
       // Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(nota_jual,6),signed)),0) from penjualan ","PJ",6,NoNota); 
//        TCari.requestFocus();
        if(akses.getjml2()>=1){
            BtnSimpan.setEnabled(akses.getoperasi());
            BtnTambahOperasi.setEnabled(akses.gettarif_operasi());
//            BtnTambah.setEnabled(akses.getoperasi());
        }        
    }
    
    public void setNoRm(String norm,String nama,String posisi){
        TNoRw.setText(norm);
        TPasien.setText(nama);
        this.status=posisi;
        this.kd_pj=Sequel.cariIsi("select reg_periksa.kd_pj from reg_periksa where reg_periksa.no_rawat=?",norm);        
        if(status.equals("Ranap")){
            norawatibu=Sequel.cariIsi("select ranap_gabung.no_rawat from ranap_gabung where ranap_gabung.no_rawat2=?",TNoRw.getText());
        
            if(!norawatibu.equals("")){
                kelas=Sequel.cariIsi(
                    "select kamar.kelas from kamar inner join kamar_inap "+
                    "on kamar.kd_kamar=kamar_inap.kd_kamar where no_rawat=? "+
                    "and kamar_inap.stts_pulang='-' order by STR_TO_DATE(concat(kamar_inap.tgl_masuk,' ',kamar_inap.jam_masuk),'%Y-%m-%d %H:%i:%s') desc limit 1",norawatibu);
            }else{
                kelas=Sequel.cariIsi(
                    "select kamar.kelas from kamar inner join kamar_inap "+
                    "on kamar.kd_kamar=kamar_inap.kd_kamar where no_rawat=? "+
                    "and kamar_inap.stts_pulang='-' order by STR_TO_DATE(concat(kamar_inap.tgl_masuk,' ',kamar_inap.jam_masuk),'%Y-%m-%d %H:%i:%s') desc limit 1",TNoRw.getText());
            } 
        }else if(status.equals("Ralan")){
            kelas="Rawat Jalan";
        }
        tampil();
//        tampil2();
    }
    
    public void setNoRm(String norm,String nama,String posisi,String KodeOperator,String NamaOperator){
        TNoRw.setText(norm);
        TPasien.setText(nama);
        this.status=posisi;
        this.kd_pj=Sequel.cariIsi("select reg_periksa.kd_pj from reg_periksa where reg_periksa.no_rawat=?",norm);        
        if(status.equals("Ranap")){
            norawatibu=Sequel.cariIsi("select ranap_gabung.no_rawat from ranap_gabung where ranap_gabung.no_rawat2=?",TNoRw.getText());
        
            if(!norawatibu.equals("")){
                kelas=Sequel.cariIsi(
                    "select kamar.kelas from kamar inner join kamar_inap "+
                    "on kamar.kd_kamar=kamar_inap.kd_kamar where no_rawat=? "+
                    "and kamar_inap.stts_pulang='-' order by STR_TO_DATE(concat(kamar_inap.tgl_masuk,' ',kamar_inap.jam_masuk),'%Y-%m-%d %H:%i:%s') desc limit 1",norawatibu);
            }else{
                kelas=Sequel.cariIsi(
                    "select kamar.kelas from kamar inner join kamar_inap "+
                    "on kamar.kd_kamar=kamar_inap.kd_kamar where no_rawat=? "+
                    "and kamar_inap.stts_pulang='-' order by STR_TO_DATE(concat(kamar_inap.tgl_masuk,' ',kamar_inap.jam_masuk),'%Y-%m-%d %H:%i:%s') desc limit 1",TNoRw.getText());
            } 
        }else if(status.equals("Ralan")){
            kelas="Rawat Jalan";
        }
        tampil();
//        tampil2();
        kdoperator1.setText(KodeOperator);
        nmoperator1.setText(NamaOperator);
    }
    
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,internalFrame1.getHeight()-79));
            FormInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    public void SetCariOperasi(String Operasi,String kodedokter,String namadokter){
        TCariPaket.setText(Operasi);
        kdoperator1.setText(kodedokter);
        nmoperator1.setText(namadokter);
    }
    
    
    
    
    private void getMasalah() {
    if (tbtindakan.getSelectedRow() != -1) { 
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
                pstindakan.setString(1, tbtindakan.getValueAt(tbtindakan.getSelectedRow(), 1).toString());
                
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
