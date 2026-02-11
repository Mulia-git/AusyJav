/*
  Dilarang keras menggandakan/mengcopy/menyebarkan/membajak/mendecompile 
  Software ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */

package inventory;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
import restore.DlgRestoreObat;
import simrskhanza.DlgCariBangsal;

public class DlgGudang extends javax.swing.JDialog {

    private DefaultTableModel tabMode;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    public DlgCariJenis jenis = new DlgCariJenis(null, false);
    public DlgCariKategori kategori = new DlgCariKategori(null, false);
    public DlgCariGolongan golongan = new DlgCariGolongan(null, false);
    public DlgCariSatuan satuan = new DlgCariSatuan(null, false);
    public DlgCariIndustriFarmasi industri=new DlgCariIndustriFarmasi(null,false);
    private DlgCariBangsal bangsal = new DlgCariBangsal(null, false);
    private double totalstok, stokgudang;
    private PreparedStatement ps, ps2, ps3, ps4;
    private ResultSet rs, rs2, rs3;
    private int i = 0;
    public String aktifkanbatch="no",pengaturanharga=Sequel.cariIsi("select set_harga_obat.setharga from set_harga_obat");
    private String kdlokasi = "", nmlokasi = "", tanggal = "0000-00-00",qrystok="";


    public DlgGudang(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode = new DefaultTableModel(null,new Object[]{
                      "P", "Kode Barang", "Nama Barang","Expired Date","Kandungan","Golongan", "Nm.Satuan Besar", 
            "Nm.Satuan Kecil","Stok Minimal","Min","Max","Produsen",
            "Distributor","LeadTime","Distribusi","Jml Total"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
              Class[] types = new Class[]{
                Boolean.class,  // 0 P
                Object.class,   // 1 Kode Barang
                Object.class,   // 2 Nama Barang
                Object.class,   // 3 Expired Date
                Object.class,   // 4 Golongan
                Object.class,   // 5 Nm.Satuan Besar
                Object.class,   // 6 Nm.Satuan Kecil
                Object.class, 
                Double.class,   // 7 Stok Minimal
                Object.class,   // 8 Min (boleh string/angka)
                Object.class,   // 9 Max
                Object.class,   // 10 Produsen
                Object.class,   // 11 Distributor
                Object.class,   // 12 LeadTime
                Object.class,   // 13 Distribusi
                Double.class    // 14 Jml Total
            };


            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbObat.setModel(tabMode);

        tbObat.setPreferredScrollableViewportSize(new Dimension(1000, 600));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Lebar kolom sesuai tabel terbaru (0–14)
        int[] widths = new int[]{
                30,   // 0 P
                110,  // 1 Kode Barang
                260,  // 2 Nama Barang
                110,  // 3 Expired Date
                100,
                140,  // 4 Golongan
                150,  // 5 Nm. Satuan Besar
                150,  // 6 Nm. Satuan Kecil
                90,   // 7 Stok Minimal
                80,   // 8 Min
                80,   // 9 Max
                180,  // 10 Produsen
                180,  // 11 Distributor
                120,  // 12 Lead Time
                120,  // 13 Distribusi
                100   // 14 Jumlah Total
        };

        for (int i = 0; i < widths.length; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);

            // Kalau width = 0 → kolom disembunyikan
            if (widths[i] == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else {
                column.setPreferredWidth(widths[i]);
            }
        }

        tbObat.setDefaultRenderer(Object.class, new WarnaTable());   
        
    


        bangsal.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (bangsal.getTable().getSelectedRow() != -1) {
                    kdlokasi = bangsal.getTable().getValueAt(bangsal.getTable().getSelectedRow(), 0).toString();
                    nmlokasi = bangsal.getTable().getValueAt(bangsal.getTable().getSelectedRow(), 1).toString();
                    tampil3();
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
                bangsal.emptTeks();
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        jenis.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
        
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        satuan.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("dlgGudang")) {
                    if (satuan.getTable().getSelectedRow() != -1) {
                       
                    }
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
                satuan.emptTeks();
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
        
        golongan.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("dlgGudang")) {
                    
                }
               
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
                golongan.emptTeks();
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
        
        kategori.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("dlgGudang")) {
                    if (kategori.getTable().getSelectedRow() != -1) {
                       
                    }
                }
              
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
                kategori.emptTeks();
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
        
        industri.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("dlgGudang")){
                    if(industri.getTable().getSelectedRow()!= -1){                   
                      
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
        
        industri.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(akses.getform().equals("dlgGudang")){
                    if(e.getKeyCode()==KeyEvent.VK_SPACE){
                        industri.dispose();
                    }     
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        }); 
        
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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Popup = new javax.swing.JPopupMenu();
        ppBarcode = new javax.swing.JMenuItem();
        ppBarcodeItem = new javax.swing.JMenuItem();
        ppStok = new javax.swing.JMenuItem();
        ppStok2 = new javax.swing.JMenuItem();
        MnRestore = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        jPanel2 = new javax.swing.JPanel();
        panelisi2 = new widget.panelisi();
        label9 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        label10 = new widget.Label();
        LCount = new widget.Label();
        panelisi1 = new widget.panelisi();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        scrollPane1 = new widget.ScrollPane();
        tbObat = new widget.Table();

        Popup.setName("Popup"); // NOI18N

        ppBarcode.setBackground(new java.awt.Color(255, 255, 254));
        ppBarcode.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBarcode.setForeground(new java.awt.Color(50, 50, 50));
        ppBarcode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBarcode.setText("Barcode");
        ppBarcode.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBarcode.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBarcode.setName("ppBarcode"); // NOI18N
        ppBarcode.setPreferredSize(new java.awt.Dimension(200, 26));
        ppBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBarcodeBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppBarcode);

        ppBarcodeItem.setBackground(new java.awt.Color(255, 255, 254));
        ppBarcodeItem.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBarcodeItem.setForeground(new java.awt.Color(50, 50, 50));
        ppBarcodeItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBarcodeItem.setText("Barcode Perbarang");
        ppBarcodeItem.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBarcodeItem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBarcodeItem.setName("ppBarcodeItem"); // NOI18N
        ppBarcodeItem.setPreferredSize(new java.awt.Dimension(200, 26));
        ppBarcodeItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBarcodeItemBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppBarcodeItem);

        ppStok.setBackground(new java.awt.Color(255, 255, 254));
        ppStok.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppStok.setForeground(new java.awt.Color(50, 50, 50));
        ppStok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppStok.setText("Tampilkan Semua Stok");
        ppStok.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppStok.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppStok.setName("ppStok"); // NOI18N
        ppStok.setPreferredSize(new java.awt.Dimension(200, 26));
        ppStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppStokBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppStok);

        ppStok2.setBackground(new java.awt.Color(255, 255, 254));
        ppStok2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppStok2.setForeground(new java.awt.Color(50, 50, 50));
        ppStok2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppStok2.setText("Tampilkan Stok Per Lokasi");
        ppStok2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppStok2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppStok2.setName("ppStok2"); // NOI18N
        ppStok2.setPreferredSize(new java.awt.Dimension(200, 26));
        ppStok2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppStok2BtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppStok2);

        MnRestore.setBackground(new java.awt.Color(255, 255, 254));
        MnRestore.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRestore.setForeground(new java.awt.Color(50, 50, 50));
        MnRestore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRestore.setText("Data Sampah");
        MnRestore.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRestore.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRestore.setName("MnRestore"); // NOI18N
        MnRestore.setPreferredSize(new java.awt.Dimension(200, 26));
        MnRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRestoreActionPerformed(evt);
            }
        });
        Popup.add(MnRestore);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Obat, Alkes & BHP Medis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(816, 100));
        jPanel2.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi2.setBackground(new java.awt.Color(255, 150, 255));
        panelisi2.setName("panelisi2"); // NOI18N
        panelisi2.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(label9);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(400, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi2.add(TCari);

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
        panelisi2.add(BtnCari);

        label10.setText("Record :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(label10);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi2.add(LCount);

        jPanel2.add(panelisi2, java.awt.BorderLayout.PAGE_START);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelisi1.add(BtnPrint);

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
        panelisi1.add(BtnAll);

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

        jPanel2.add(panelisi1, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        scrollPane1.setComponentPopupMenu(Popup);
        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbObat.setAutoCreateRowSorter(true);
        tbObat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(Popup);
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbObatKeyReleased(evt);
            }
        });
        scrollPane1.setViewportView(tbObat);

        internalFrame1.add(scrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbObat.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        if(akses.getform().equals("tampil3")){
            tampil3();
        }else{
            tampil();
        }   
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if (tabMode.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                TCari.setText("");
                TCari.requestFocus();
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BtnCariActionPerformed(evt);
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
         
        } else if (tabMode.getRowCount() != 0) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
            if(TCari.getText().trim().equals("")){
                Valid.MyReportqry("rptBarang.jasper", "report", "::[ Data Barang ]::", 
                        "select databarang.kode_brng, databarang.nama_brng,databarang.kode_satbesar,satuanbesar.satuan as satuanbesar, "
                        + " databarang.isi,databarang.kode_sat,kodesatuan.satuan,databarang.letak_barang,databarang.dasar,databarang.h_beli,"
                        + " databarang.ralan,databarang.kelas1,databarang.kelas2,databarang.kelas3,"
                        + " databarang.utama,databarang.vip,databarang.vvip,databarang.beliluar,databarang.jualbebas,"
                        + " databarang.karyawan,databarang.stokminimal, databarang.kdjns,"
                        + " jenis.nama,kapasitas,databarang.expire,databarang.kode_industri,industrifarmasi.nama_industri, "
                        + " databarang.kode_kategori,kategori_barang.nama as kategori,databarang.kode_golongan,golongan_barang.nama as golongan "
                        + " from databarang inner join kodesatuan on databarang.kode_sat=kodesatuan.kode_sat "
                        + " inner join kodesatuan as satuanbesar on databarang.kode_satbesar=satuanbesar.kode_sat "
                        + " inner join jenis on databarang.kdjns=jenis.kdjns "
                        + " inner join industrifarmasi on databarang.kode_industri=industrifarmasi.kode_industri "
                        + " inner join golongan_barang on databarang.kode_golongan=golongan_barang.kode "
                        + " inner join kategori_barang on databarang.kode_kategori=kategori_barang.kode "
                        + " where databarang.status='1' order by databarang.nama_brng", param);
            }else{
                Valid.MyReportqry("rptBarang.jasper", "report", "::[ Data Barang ]::", 
                        "select databarang.kode_brng, databarang.nama_brng,databarang.kode_satbesar,satuanbesar.satuan as satuanbesar, "
                        + " databarang.isi,databarang.kode_sat,kodesatuan.satuan,databarang.letak_barang,databarang.dasar,databarang.h_beli,"
                        + " databarang.ralan,databarang.kelas1,databarang.kelas2,databarang.kelas3,"
                        + " databarang.utama,databarang.vip,databarang.vvip,databarang.beliluar,databarang.jualbebas,"
                        + " databarang.karyawan,databarang.stokminimal, databarang.kdjns,"
                        + " jenis.nama,kapasitas,databarang.expire,databarang.kode_industri,industrifarmasi.nama_industri, "
                        + " databarang.kode_kategori,kategori_barang.nama as kategori,databarang.kode_golongan,golongan_barang.nama as golongan "
                        + " from databarang inner join kodesatuan on databarang.kode_sat=kodesatuan.kode_sat "
                        + " inner join kodesatuan as satuanbesar on databarang.kode_satbesar=satuanbesar.kode_sat "
                        + " inner join jenis on databarang.kdjns=jenis.kdjns "
                        + " inner join industrifarmasi on databarang.kode_industri=industrifarmasi.kode_industri "
                        + " inner join golongan_barang on databarang.kode_golongan=golongan_barang.kode "
                        + " inner join kategori_barang on databarang.kode_kategori=kategori_barang.kode "
                        + " where databarang.status='1' and databarang.kode_brng like '%" + TCari.getText().trim() + "%' or "
                        + " databarang.status='1' and databarang.nama_brng like '%" + TCari.getText().trim() + "%' or "
                        + " databarang.status='1' and databarang.kode_sat like '%" + TCari.getText().trim() + "%' or "
                        + " databarang.status='1' and kodesatuan.satuan like '%" + TCari.getText().trim() + "%' or "
                        + " databarang.status='1' and databarang.kode_satbesar like '%" + TCari.getText().trim() + "%' or "
                        + " databarang.status='1' and satuanbesar.satuan like '%" + TCari.getText().trim() + "%' or "
                        + " databarang.status='1' and databarang.letak_barang like '%" + TCari.getText().trim() + "%' or "
                        + " databarang.status='1' and databarang.kdjns like '%" + TCari.getText().trim() + "%' or "
                        + " databarang.status='1' and jenis.nama like '%" + TCari.getText().trim() + "%' or "
                        + " databarang.status='1' and databarang.kode_industri like '%" + TCari.getText().trim() + "%' or "
                        + " databarang.status='1' and kategori_barang.nama like '%" + TCari.getText().trim() + "%' or "
                        + " databarang.status='1' and golongan_barang.nama like '%" + TCari.getText().trim() + "%' or "
                        + " databarang.status='1' and industrifarmasi.nama_industri like '%" + TCari.getText().trim() + "%' order by databarang.nama_brng", param);
            }
                
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
           
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        if(akses.getform().equals("tampil3")){
            tampil3();
        }else{
            tampil();
        }        
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnAll, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void ppBarcodeBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBarcodeBtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BtnCariActionPerformed(evt);
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
           
        } else if (tabMode.getRowCount() != 0) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
            Valid.MyReportqry("rptBarcodeBarang.jasper", "report", "::[ Data Barang ]::", 
                   "select databarang.kode_brng, databarang.nama_brng, "
                    + " databarang.kode_sat,kodesatuan.satuan,databarang.letak_barang, databarang.h_beli,"
                    + " databarang.ralan,databarang.kelas1,databarang.kelas2,databarang.kelas3,"
                    + " databarang.utama,databarang.vip,databarang.vvip,databarang.beliluar,databarang.jualbebas,"
                    + " databarang.karyawan,databarang.stokminimal, databarang.kdjns,"
                    + " jenis.nama,kapasitas,databarang.expire,databarang.kode_industri,industrifarmasi.nama_industri, "
                    + " databarang.kode_kategori,kategori_barang.nama as kategori,databarang.kode_golongan,golongan_barang.nama as golongan "
                    + " from databarang inner join kodesatuan inner join jenis inner join industrifarmasi inner join golongan_barang inner join kategori_barang "
                    + " on databarang.kode_sat=kodesatuan.kode_sat and databarang.kdjns=jenis.kdjns "
                    + " and databarang.kode_golongan=golongan_barang.kode and databarang.kode_kategori=kategori_barang.kode "
                    + " and databarang.kode_industri=industrifarmasi.kode_industri "
                    + " where databarang.status='1' and databarang.kode_brng like '%" + TCari.getText().trim() + "%' or "
                    + " databarang.status='1' and databarang.nama_brng like '%" + TCari.getText().trim() + "%' or "
                    + " databarang.status='1' and databarang.kode_sat like '%" + TCari.getText().trim() + "%' or "
                    + " databarang.status='1' and kodesatuan.satuan like '%" + TCari.getText().trim() + "%' or "
                    + " databarang.status='1' and databarang.letak_barang like '%" + TCari.getText().trim() + "%' or "
                    + " databarang.status='1' and databarang.kdjns like '%" + TCari.getText().trim() + "%' or "
                    + " databarang.status='1' and jenis.nama like '%" + TCari.getText().trim() + "%' or "
                    + " databarang.status='1' and databarang.kode_industri like '%" + TCari.getText().trim() + "%' or "
                    + " databarang.status='1' and kategori_barang.nama like '%" + TCari.getText().trim() + "%' or "
                    + " databarang.status='1' and golongan_barang.nama like '%" + TCari.getText().trim() + "%' or "
                    + " databarang.status='1' and industrifarmasi.nama_industri like '%" + TCari.getText().trim() + "%' order by databarang.nama_brng",param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_ppBarcodeBtnPrintActionPerformed

    private void ppBarcodeItemBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBarcodeItemBtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
       
        }  else if (tabMode.getRowCount() != 0) {
            int jml;
            try {
                jml = Integer.parseInt(JOptionPane.showInputDialog("Masukkan jumlah barcode yang mau dicetak !!!"));
            } catch (HeadlessException | NumberFormatException e) {
                jml = 0;
            }

            if (jml > 0) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Sequel.queryu("delete from temporary where temp37='"+akses.getalamatip()+"'");
                jml = Math.round(jml / 3);
                for (int i = 0; i < jml; i++) {
                  
                }
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select setting.logo from setting"));
                Valid.MyReportqry("rptBarcodeItem.jasper", "report", "::[ Barcode Perbarang ]::","select * from temporary where temporary.temp37='"+akses.getalamatip()+"' order by temporary.no",param);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }

    }//GEN-LAST:event_ppBarcodeItemBtnPrintActionPerformed

private void ppStokBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppStokBtnPrintActionPerformed
    tampil2();
}//GEN-LAST:event_ppStokBtnPrintActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if(!akses.getform().equals("DlgReturJual")){
            if(akses.getform().equals("tampil3")){
                tampil3();
            }else{
                tampil();
            }   
        }
    }//GEN-LAST:event_formWindowOpened

    private void ppStok2BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppStok2BtnPrintActionPerformed
        bangsal.isCek();
        bangsal.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        bangsal.setLocationRelativeTo(internalFrame1);
        bangsal.setVisible(true);
    }//GEN-LAST:event_ppStok2BtnPrintActionPerformed

    private void MnRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRestoreActionPerformed
        DlgRestoreObat restore=new DlgRestoreObat(null,true);
        restore.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        restore.setLocationRelativeTo(internalFrame1);
        restore.setVisible(true);
    }//GEN-LAST:event_MnRestoreActionPerformed

    private void tbObatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyReleased
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbObatKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgGudang dialog = new DlgGudang(new javax.swing.JFrame(), true);
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
    private widget.Button BtnPrint;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnRestore;
    private javax.swing.JPopupMenu Popup;
    private widget.TextBox TCari;
    private widget.InternalFrame internalFrame1;
    private javax.swing.JPanel jPanel2;
    private widget.Label label10;
    private widget.Label label9;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi2;
    private javax.swing.JMenuItem ppBarcode;
    private javax.swing.JMenuItem ppBarcodeItem;
    private javax.swing.JMenuItem ppStok;
    private javax.swing.JMenuItem ppStok2;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        if(aktifkanbatch.equals("no")){
            try {
                if(TCari.getText().trim().equals("")){
                    ps = koneksi.prepareStatement(
                            "select databarang.kode_brng, databarang.nama_brng,databarang.kode_satbesar,satuanbesar.satuan as satuanbesar, "
                            + " databarang.isi,databarang.kode_sat,kodesatuan.satuan,databarang.letak_barang,databarang.dasar,databarang.h_beli,"
                            + " databarang.ralan,databarang.kelas1,databarang.kelas2,databarang.kelas3,"
                            + " databarang.utama,databarang.vip,databarang.vvip,databarang.beliluar,databarang.jualbebas,"
                            + " databarang.karyawan,databarang.stokminimal, databarang.kdjns,"
                            + " jenis.nama as jenis_nama,kapasitas,databarang.expire,databarang.kode_industri,industrifarmasi.nama_industri, "
                            + " databarang.kode_kategori,kategori_barang.nama as kategori,databarang.kode_golongan,golongan_barang.nama as golongan "
                            + " from databarang inner join kodesatuan on databarang.kode_sat=kodesatuan.kode_sat "
                            + " inner join kodesatuan as satuanbesar on databarang.kode_satbesar=satuanbesar.kode_sat "
                            + " inner join jenis on databarang.kdjns=jenis.kdjns "
                            + " inner join industrifarmasi on databarang.kode_industri=industrifarmasi.kode_industri "
                            + " inner join golongan_barang on databarang.kode_golongan=golongan_barang.kode "
                            + " inner join kategori_barang on databarang.kode_kategori=kategori_barang.kode "
                            + " where databarang.status='1' order by databarang.nama_brng");
                }else{
                    ps = koneksi.prepareStatement(
                            "select databarang.kode_brng, databarang.nama_brng,databarang.kode_satbesar,satuanbesar.satuan as satuanbesar, "
                            + " databarang.isi,databarang.kode_sat,kodesatuan.satuan,databarang.letak_barang,databarang.dasar,databarang.h_beli,"
                            + " databarang.ralan,databarang.kelas1,databarang.kelas2,databarang.kelas3,"
                            + " databarang.utama,databarang.vip,databarang.vvip,databarang.beliluar,databarang.jualbebas,"
                            + " databarang.karyawan,databarang.stokminimal, databarang.kdjns,"
                            + " jenis.nama as jenis_nama,kapasitas,databarang.expire,databarang.kode_industri,industrifarmasi.nama_industri, "
                            + " databarang.kode_kategori,kategori_barang.nama as kategori,databarang.kode_golongan,golongan_barang.nama as golongan "
                            + " from databarang inner join kodesatuan on databarang.kode_sat=kodesatuan.kode_sat "
                            + " inner join kodesatuan as satuanbesar on databarang.kode_satbesar=satuanbesar.kode_sat "
                            + " inner join jenis on databarang.kdjns=jenis.kdjns "
                            + " inner join industrifarmasi on databarang.kode_industri=industrifarmasi.kode_industri "
                            + " inner join golongan_barang on databarang.kode_golongan=golongan_barang.kode "
                            + " inner join kategori_barang on databarang.kode_kategori=kategori_barang.kode "
                            + " where databarang.status='1' and databarang.kode_brng like ? or "
                            + " databarang.status='1' and databarang.nama_brng like ? or "
                            + " databarang.status='1' and databarang.kode_sat like ? or "
                            + " databarang.status='1' and kodesatuan.satuan like ? or "
                            + " databarang.status='1' and databarang.kode_satbesar like ? or "
                            + " databarang.status='1' and satuanbesar.satuan like ? or "
                            + " databarang.status='1' and databarang.letak_barang like ? or "
                            + " databarang.status='1' and databarang.kdjns like ? or "
                            + " databarang.status='1' and kategori_barang.nama like ? or "
                            + " databarang.status='1' and golongan_barang.nama like ? or "
                            + " databarang.status='1' and jenis.nama like ? or "
                            + " databarang.status='1' and databarang.kode_industri like ? or "
                            + " databarang.status='1' and industrifarmasi.nama_industri like ? order by databarang.nama_brng");
                }
                    
                try {
                    if(!TCari.getText().trim().equals("")){
                        ps.setString(1, "%" + TCari.getText().trim() + "%");
                        ps.setString(2, "%" + TCari.getText().trim() + "%");
                        ps.setString(3, "%" + TCari.getText().trim() + "%");
                        ps.setString(4, "%" + TCari.getText().trim() + "%");
                        ps.setString(5, "%" + TCari.getText().trim() + "%");
                        ps.setString(6, "%" + TCari.getText().trim() + "%");
                        ps.setString(7, "%" + TCari.getText().trim() + "%");
                        ps.setString(8, "%" + TCari.getText().trim() + "%");
                        ps.setString(9, "%" + TCari.getText().trim() + "%");
                        ps.setString(10, "%" + TCari.getText().trim() + "%");
                        ps.setString(11, "%" + TCari.getText().trim() + "%");
                        ps.setString(12, "%" + TCari.getText().trim() + "%");
                        ps.setString(13, "%" + TCari.getText().trim() + "%");
                    }
                     
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        tabMode.addRow(new Object[]{
                            false, rs.getString("kode_brng"),
                            rs.getString("nama_brng"),
                            rs.getString("expire"),
                            rs.getString("letak_barang"),
                            rs.getString("jenis_nama"),
                            rs.getString("satuanbesar"),
                            rs.getString("satuan"),
                            rs.getDouble("stokminimal"),
                            "",
                            "",
                              rs.getString("nama_industri"),
                              "",
                              "",
                              "",
                              rs.getDouble("kapasitas"),
                           
                        });
                    }
                    LCount.setText("" + tabMode.getRowCount());
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally{
                    if(rs != null){
                        rs.close();
                    }

                    if(ps != null){
                        ps.close();
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }else if(aktifkanbatch.equals("yes")){
            try {
                ps = koneksi.prepareStatement(
                      "select data_batch.kode_brng, databarang.nama_brng,databarang.kode_satbesar,satuanbesar.satuan as satuanbesar, "
                    + " databarang.kode_sat,kodesatuan.satuan,databarang.letak_barang,data_batch.dasar,data_batch.h_beli,"
                    + " data_batch.ralan,data_batch.kelas1,data_batch.kelas2,data_batch.kelas3,"
                    + " data_batch.utama,data_batch.vip,data_batch.vvip,data_batch.beliluar,data_batch.jualbebas,"
                    + " data_batch.karyawan,databarang.stokminimal, databarang.kdjns,"
                    + " jenis.nama,databarang.kapasitas,databarang.isi,data_batch.tgl_kadaluarsa as expire,databarang.kode_industri,industrifarmasi.nama_industri, "
                    + " databarang.kode_kategori,kategori_barang.nama as kategori,databarang.kode_golongan,golongan_barang.nama as golongan,data_batch.no_batch,data_batch.no_faktur "
                    + " from data_batch inner join databarang on data_batch.kode_brng=databarang.kode_brng "
                    + " inner join kodesatuan on databarang.kode_sat=kodesatuan.kode_sat "
                    + " inner join kodesatuan as satuanbesar on databarang.kode_satbesar=satuanbesar.kode_sat "
                    + " inner join jenis on databarang.kdjns=jenis.kdjns "
                    + " inner join industrifarmasi on databarang.kode_industri=industrifarmasi.kode_industri "
                    + " inner join golongan_barang on databarang.kode_golongan=golongan_barang.kode "
                    + " inner join kategori_barang on databarang.kode_kategori=kategori_barang.kode "
                    + " where data_batch.sisa>0 and data_batch.kode_brng like ? or "
                    + " data_batch.sisa>0 and databarang.nama_brng like ? or "
                    + " data_batch.sisa>0 and databarang.kode_sat like ? or "
                    + " data_batch.sisa>0 and kodesatuan.satuan like ? or "
                    + " data_batch.sisa>0 and databarang.kode_satbesar like ? or "
                    + " data_batch.sisa>0 and satuanbesar.satuan like ? or "
                    + " data_batch.sisa>0 and databarang.letak_barang like ? or "
                    + " data_batch.sisa>0 and databarang.kdjns like ? or "
                    + " data_batch.sisa>0 and kategori_barang.nama like ? or "
                    + " data_batch.sisa>0 and golongan_barang.nama like ? or "
                    + " data_batch.sisa>0 and data_batch.no_batch like ? or "
                    + " data_batch.sisa>0 and data_batch.no_faktur like ? or "
                    + " data_batch.sisa>0 and jenis.nama like ? or "
                    + " data_batch.sisa>0 and databarang.kode_industri like ? or "
                    + " data_batch.sisa>0 and industrifarmasi.nama_industri like ? order by data_batch.tgl_kadaluarsa");
                try {
                    ps.setString(1, "%" + TCari.getText().trim() + "%");
                    ps.setString(2, "%" + TCari.getText().trim() + "%");
                    ps.setString(3, "%" + TCari.getText().trim() + "%");
                    ps.setString(4, "%" + TCari.getText().trim() + "%");
                    ps.setString(5, "%" + TCari.getText().trim() + "%");
                    ps.setString(6, "%" + TCari.getText().trim() + "%");
                    ps.setString(7, "%" + TCari.getText().trim() + "%");
                    ps.setString(8, "%" + TCari.getText().trim() + "%");
                    ps.setString(9, "%" + TCari.getText().trim() + "%");
                    ps.setString(10, "%" + TCari.getText().trim() + "%");
                    ps.setString(11, "%" + TCari.getText().trim() + "%");
                    ps.setString(12, "%" + TCari.getText().trim() + "%");
                    ps.setString(13, "%" + TCari.getText().trim() + "%");
                    ps.setString(14, "%" + TCari.getText().trim() + "%");
                    ps.setString(15, "%" + TCari.getText().trim() + "%");
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        tabMode.addRow(new Object[]{
                            false, rs.getString("kode_brng"),
                            rs.getString("nama_brng"),
                            rs.getString("kode_satbesar"),
                            rs.getString("satuanbesar"),
                            rs.getDouble("isi"),
                            rs.getString("kode_sat"),
                            rs.getString("satuan"),
                            rs.getDouble("kapasitas"),
                            rs.getString("letak_barang"),
                            rs.getDouble("dasar"),
                            rs.getDouble("h_beli"),
                            rs.getDouble("ralan"),
                            rs.getDouble("kelas1"),
                            rs.getDouble("kelas2"),
                            rs.getDouble("kelas3"),
                            rs.getDouble("utama"),
                            rs.getDouble("vip"),
                            rs.getDouble("vvip"),
                            rs.getDouble("beliluar"),
                            rs.getDouble("jualbebas"),
                            rs.getDouble("karyawan"),
                            rs.getDouble("stokminimal"),
                            rs.getString("kdjns"),
                            rs.getString("nama"),
                            rs.getString("expire"),
                            rs.getString("kode_industri"),
                            rs.getString("nama_industri"),
                            rs.getString("kode_kategori"),
                            rs.getString("kategori"),
                            rs.getString("kode_golongan"),
                            rs.getString("golongan"),
                            rs.getString("no_batch"),
                            rs.getString("no_faktur")
                        });
                    }
                    LCount.setText("" + tabMode.getRowCount());
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally{
                    if(rs != null){
                        rs.close();
                    }

                    if(ps != null){
                        ps.close();
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
            
    }

    private void tampil2() {
        Valid.tabelKosong(tabMode);
        try {
            if(aktifkanbatch.equals("yes")){
                qrystok="select sum(stok) from gudangbarang where kode_brng=? and kd_bangsal=? and no_batch<>'' and no_faktur<>''";
            }else{
                qrystok="select sum(stok) from gudangbarang where kode_brng=? and kd_bangsal=? and no_batch='' and no_faktur=''";
            }
            if(TCari.getText().trim().equals("")){
                ps = koneksi.prepareStatement(
                        "select databarang.kode_brng, databarang.nama_brng,databarang.kode_satbesar,satuanbesar.satuan as satuanbesar, "
                        + " databarang.isi,databarang.kode_sat,kodesatuan.satuan,databarang.letak_barang,databarang.dasar,databarang.h_beli,"
                        + " databarang.ralan,databarang.kelas1,databarang.kelas2,databarang.kelas3,"
                        + " databarang.utama,databarang.vip,databarang.vvip,databarang.beliluar,databarang.jualbebas,"
                        + " databarang.karyawan,databarang.stokminimal, databarang.kdjns,"
                        + " jenis.nama,kapasitas,databarang.expire,databarang.kode_industri,industrifarmasi.nama_industri, "
                        + " databarang.kode_kategori,kategori_barang.nama as kategori,databarang.kode_golongan,golongan_barang.nama as golongan "
                        + " from databarang inner join kodesatuan on databarang.kode_sat=kodesatuan.kode_sat "
                        + " inner join kodesatuan as satuanbesar on databarang.kode_satbesar=satuanbesar.kode_sat "
                        + " inner join jenis on databarang.kdjns=jenis.kdjns "
                        + " inner join industrifarmasi on databarang.kode_industri=industrifarmasi.kode_industri "
                        + " inner join golongan_barang on databarang.kode_golongan=golongan_barang.kode "
                        + " inner join kategori_barang on databarang.kode_kategori=kategori_barang.kode "
                        + " where databarang.status='1' order by databarang.nama_brng");
            }else{
                ps = koneksi.prepareStatement(
                        "select databarang.kode_brng, databarang.nama_brng,databarang.kode_satbesar,satuanbesar.satuan as satuanbesar, "
                        + " databarang.isi,databarang.kode_sat,kodesatuan.satuan,databarang.letak_barang,databarang.dasar,databarang.h_beli,"
                        + " databarang.ralan,databarang.kelas1,databarang.kelas2,databarang.kelas3,"
                        + " databarang.utama,databarang.vip,databarang.vvip,databarang.beliluar,databarang.jualbebas,"
                        + " databarang.karyawan,databarang.stokminimal, databarang.kdjns,"
                        + " jenis.nama,kapasitas,databarang.expire,databarang.kode_industri,industrifarmasi.nama_industri, "
                        + " databarang.kode_kategori,kategori_barang.nama as kategori,databarang.kode_golongan,golongan_barang.nama as golongan "
                        + " from databarang inner join kodesatuan on databarang.kode_sat=kodesatuan.kode_sat "
                        + " inner join kodesatuan as satuanbesar on databarang.kode_satbesar=satuanbesar.kode_sat "
                        + " inner join jenis on databarang.kdjns=jenis.kdjns "
                        + " inner join industrifarmasi on databarang.kode_industri=industrifarmasi.kode_industri "
                        + " inner join golongan_barang on databarang.kode_golongan=golongan_barang.kode "
                        + " inner join kategori_barang on databarang.kode_kategori=kategori_barang.kode "
                        + " where databarang.status='1' and databarang.kode_brng like ? or "
                        + " databarang.status='1' and databarang.nama_brng like ? or "
                        + " databarang.status='1' and databarang.kode_sat like ? or "
                        + " databarang.status='1' and kodesatuan.satuan like ? or "
                        + " databarang.status='1' and databarang.kode_satbesar like ? or "
                        + " databarang.status='1' and satuanbesar.satuan like ? or "
                        + " databarang.status='1' and databarang.letak_barang like ? or "
                        + " databarang.status='1' and databarang.kdjns like ? or "
                        + " databarang.status='1' and kategori_barang.nama like ? or "
                        + " databarang.status='1' and golongan_barang.nama like ? or "
                        + " databarang.status='1' and jenis.nama like ? or "
                        + " databarang.status='1' and databarang.kode_industri like ? or "
                        + " databarang.status='1' and industrifarmasi.nama_industri like ? order by databarang.nama_brng");
            }

            try {
                if(!TCari.getText().trim().equals("")){
                    ps.setString(1, "%" + TCari.getText().trim() + "%");
                    ps.setString(2, "%" + TCari.getText().trim() + "%");
                    ps.setString(3, "%" + TCari.getText().trim() + "%");
                    ps.setString(4, "%" + TCari.getText().trim() + "%");
                    ps.setString(5, "%" + TCari.getText().trim() + "%");
                    ps.setString(6, "%" + TCari.getText().trim() + "%");
                    ps.setString(7, "%" + TCari.getText().trim() + "%");
                    ps.setString(8, "%" + TCari.getText().trim() + "%");
                    ps.setString(9, "%" + TCari.getText().trim() + "%");
                    ps.setString(10, "%" + TCari.getText().trim() + "%");
                    ps.setString(11, "%" + TCari.getText().trim() + "%");
                    ps.setString(12, "%" + TCari.getText().trim() + "%");
                    ps.setString(13, "%" + TCari.getText().trim() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        false, rs.getString("kode_brng"),
                        rs.getString("nama_brng"),
                        rs.getString("kode_satbesar"),
                        rs.getString("satuanbesar"),
                        rs.getDouble("isi"),
                        rs.getString("kode_sat"),
                        rs.getString("satuan"),
                        rs.getDouble("kapasitas"),
                        rs.getString("letak_barang"),
                        rs.getDouble("dasar"),
                        rs.getDouble("h_beli"),
                        rs.getDouble("ralan"),
                        rs.getDouble("kelas1"),
                        rs.getDouble("kelas2"),
                        rs.getDouble("kelas3"),
                        rs.getDouble("utama"),
                        rs.getDouble("vip"),
                        rs.getDouble("vvip"),
                        rs.getDouble("beliluar"),
                        rs.getDouble("jualbebas"),
                        rs.getDouble("karyawan"),
                        rs.getDouble("stokminimal"),
                        rs.getString("kdjns"),
                        rs.getString("nama"),
                        rs.getString("expire"),
                        rs.getString("kode_industri"),
                        rs.getString("nama_industri"),
                        rs.getString("kode_kategori"),
                        rs.getString("kategori"),
                        rs.getString("kode_golongan"),
                        rs.getString("golongan")
                    });
                    
                    ps2 = koneksi.prepareStatement("select bangsal.kd_bangsal,bangsal.nm_bangsal from bangsal");
                    try {
                        rs2 = ps2.executeQuery();
                        totalstok = 0;
                        while (rs2.next()) {
                            stokgudang = 0;
                            ps3 = koneksi.prepareStatement(qrystok);
                            try {
                                ps3.setString(1, rs.getString(1));
                                ps3.setString(2, rs2.getString(1));
                                rs3 = ps3.executeQuery();
                                if (rs3.next()) {
                                    stokgudang = rs3.getDouble(1);
                                    totalstok = totalstok + rs3.getDouble(1);
                                }
                                tabMode.addRow(new Object[]{false, "","    "+rs2.getString(2),": " + stokgudang, "", null,null,null,null,null, null, null, null, null, null, null, null, null, null, null, null,null,null, null,null,null,null,null,null,null});
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally{
                                if(rs3 != null){
                                    rs3.close();
                                }
                                
                                if(ps3 != null){
                                    ps3.close();
                                }
                            }
                        }
                        tabMode.addRow(new Object[]{false, "", "  Total Stok", ": " + totalstok, "", null,null,null,null,null, null, null, null, null, null, null, null, null, null, null, null, null,null, null,null,null,null,null,null,null});
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally{
                        if(rs2 != null){
                            rs2.close();
                        }
                        if(ps2 != null){
                            ps2.close();
                        }
                    }  
                }
                rs.last();
                LCount.setText("" + rs.getRow());
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally{
                if(rs != null){
                    rs.close();
                }                
                if(ps != null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void tampil3() {
        Valid.tabelKosong(tabMode);
        try {
            if(aktifkanbatch.equals("yes")){
                qrystok="select sum(stok) from gudangbarang where kode_brng=? and kd_bangsal=? and no_batch<>'' and no_faktur<>''";
            }else{
                qrystok="select sum(stok) from gudangbarang where kode_brng=? and kd_bangsal=? and no_batch='' and no_faktur=''";
            }
            if(TCari.getText().trim().equals("")){
                ps = koneksi.prepareStatement(
                        "select databarang.kode_brng, databarang.nama_brng,databarang.kode_satbesar,satuanbesar.satuan as satuanbesar, "
                        + " databarang.isi,databarang.kode_sat,kodesatuan.satuan,databarang.letak_barang,databarang.dasar,databarang.h_beli,"
                        + " databarang.ralan,databarang.kelas1,databarang.kelas2,databarang.kelas3,"
                        + " databarang.utama,databarang.vip,databarang.vvip,databarang.beliluar,databarang.jualbebas,"
                        + " databarang.karyawan,databarang.stokminimal, databarang.kdjns,"
                        + " jenis.nama,kapasitas,databarang.expire,databarang.kode_industri,industrifarmasi.nama_industri, "
                        + " databarang.kode_kategori,kategori_barang.nama as kategori,databarang.kode_golongan,golongan_barang.nama as golongan "
                        + " from databarang inner join kodesatuan on databarang.kode_sat=kodesatuan.kode_sat "
                        + " inner join kodesatuan as satuanbesar on databarang.kode_satbesar=satuanbesar.kode_sat "
                        + " inner join jenis on databarang.kdjns=jenis.kdjns "
                        + " inner join industrifarmasi on databarang.kode_industri=industrifarmasi.kode_industri "
                        + " inner join golongan_barang on databarang.kode_golongan=golongan_barang.kode "
                        + " inner join kategori_barang on databarang.kode_kategori=kategori_barang.kode "
                        + " where databarang.status='1' order by databarang.nama_brng");
            }else{
                ps = koneksi.prepareStatement(
                        "select databarang.kode_brng, databarang.nama_brng,databarang.kode_satbesar,satuanbesar.satuan as satuanbesar, "
                        + " databarang.isi,databarang.kode_sat,kodesatuan.satuan,databarang.letak_barang,databarang.dasar,databarang.h_beli,"
                        + " databarang.ralan,databarang.kelas1,databarang.kelas2,databarang.kelas3,"
                        + " databarang.utama,databarang.vip,databarang.vvip,databarang.beliluar,databarang.jualbebas,"
                        + " databarang.karyawan,databarang.stokminimal, databarang.kdjns,"
                        + " jenis.nama,kapasitas,databarang.expire,databarang.kode_industri,industrifarmasi.nama_industri, "
                        + " databarang.kode_kategori,kategori_barang.nama as kategori,databarang.kode_golongan,golongan_barang.nama as golongan "
                        + " from databarang inner join kodesatuan on databarang.kode_sat=kodesatuan.kode_sat "
                        + " inner join kodesatuan as satuanbesar on databarang.kode_satbesar=satuanbesar.kode_sat "
                        + " inner join jenis on databarang.kdjns=jenis.kdjns "
                        + " inner join industrifarmasi on databarang.kode_industri=industrifarmasi.kode_industri "
                        + " inner join golongan_barang on databarang.kode_golongan=golongan_barang.kode "
                        + " inner join kategori_barang on databarang.kode_kategori=kategori_barang.kode "
                        + " where databarang.status='1' and databarang.kode_brng like ? or "
                        + " databarang.status='1' and databarang.nama_brng like ? or "
                        + " databarang.status='1' and databarang.kode_sat like ? or "
                        + " databarang.status='1' and kodesatuan.satuan like ? or "
                        + " databarang.status='1' and databarang.kode_satbesar like ? or "
                        + " databarang.status='1' and satuanbesar.satuan like ? or "
                        + " databarang.status='1' and databarang.letak_barang like ? or "
                        + " databarang.status='1' and databarang.kdjns like ? or "
                        + " databarang.status='1' and kategori_barang.nama like ? or "
                        + " databarang.status='1' and golongan_barang.nama like ? or "
                        + " databarang.status='1' and jenis.nama like ? or "
                        + " databarang.status='1' and databarang.kode_industri like ? or "
                        + " databarang.status='1' and industrifarmasi.nama_industri like ? order by databarang.nama_brng");
            }

            try {
                if(!TCari.getText().trim().equals("")){
                    ps.setString(1, "%" + TCari.getText().trim() + "%");
                    ps.setString(2, "%" + TCari.getText().trim() + "%");
                    ps.setString(3, "%" + TCari.getText().trim() + "%");
                    ps.setString(4, "%" + TCari.getText().trim() + "%");
                    ps.setString(5, "%" + TCari.getText().trim() + "%");
                    ps.setString(6, "%" + TCari.getText().trim() + "%");
                    ps.setString(7, "%" + TCari.getText().trim() + "%");
                    ps.setString(8, "%" + TCari.getText().trim() + "%");
                    ps.setString(9, "%" + TCari.getText().trim() + "%");
                    ps.setString(10, "%" + TCari.getText().trim() + "%");
                    ps.setString(11, "%" + TCari.getText().trim() + "%");
                    ps.setString(12, "%" + TCari.getText().trim() + "%");
                    ps.setString(13, "%" + TCari.getText().trim() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        false, rs.getString("kode_brng"),
                        rs.getString("nama_brng"),
                        rs.getString("kode_satbesar"),
                        rs.getString("satuanbesar"),
                        rs.getDouble("isi"),
                        rs.getString("kode_sat"),
                        rs.getString("satuan"),
                        rs.getDouble("kapasitas"),
                        rs.getString("letak_barang"),
                        rs.getDouble("dasar"),
                        rs.getDouble("h_beli"),
                        rs.getDouble("ralan"),
                        rs.getDouble("kelas1"),
                        rs.getDouble("kelas2"),
                        rs.getDouble("kelas3"),
                        rs.getDouble("utama"),
                        rs.getDouble("vip"),
                        rs.getDouble("vvip"),
                        rs.getDouble("beliluar"),
                        rs.getDouble("jualbebas"),
                        rs.getDouble("karyawan"),
                        rs.getDouble("stokminimal"),
                        rs.getString("kdjns"),
                        rs.getString("nama"),
                        rs.getString("expire"),
                        rs.getString("kode_industri"),
                        rs.getString("nama_industri"),
                        rs.getString("kode_kategori"),
                        rs.getString("kategori"),
                        rs.getString("kode_golongan"),
                        rs.getString("golongan")
                    });
                    stokgudang = 0;
                    ps3 = koneksi.prepareStatement(qrystok);
                    try {
                        ps3.setString(1, rs.getString(1));
                        ps3.setString(2, kdlokasi);
                        rs3 = ps3.executeQuery();
                        if (rs3.next()) {
                            stokgudang = rs3.getDouble(1);
                        }
                        tabMode.addRow(new Object[]{false, "","    "+nmlokasi, ": " + stokgudang, "", null,null,null,null,null, null, null, null, null, null, null, null, null, null, null, null,null,null, null,null,null,null,null,null,null});
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally{
                        if(rs3 != null){
                            rs3.close();
                        }
                        if(ps3 != null){
                            ps3.close();
                        }
                    }
                }
                rs.last();
                LCount.setText("" + rs.getRow());
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally{
                if(rs != null){
                    rs.close();
                }
                if(ps != null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
            
    }
    
    public void tampil4(String NoRetur) {
        if(akses.getform().equals("DlgReturJual")){
            if(aktifkanbatch.equals("yes")){
                Valid.tabelKosong(tabMode);
                try {
                    ps4 = koneksi.prepareStatement(
                            "select databarang.kode_brng, databarang.nama_brng,databarang.kode_satbesar,satuanbesar.satuan as satuanbesar, "
                            + " databarang.isi,databarang.kode_sat,kodesatuan.satuan,databarang.letak_barang,data_batch.dasar,data_batch.h_beli,"
                            + " data_batch.ralan,data_batch.kelas1,data_batch.kelas2,data_batch.kelas3,detail_pemberian_obat.no_batch,"
                            + " data_batch.utama,databarang.vip,data_batch.vvip,data_batch.beliluar,data_batch.jualbebas,"
                            + " data_batch.karyawan,databarang.stokminimal, databarang.kdjns,detail_pemberian_obat.no_faktur,"
                            + " jenis.nama,kapasitas,databarang.expire,databarang.kode_industri,industrifarmasi.nama_industri, "
                            + " databarang.kode_kategori,kategori_barang.nama as kategori,databarang.kode_golongan,golongan_barang.nama as golongan "
                            + " from data_batch inner join databarang on data_batch.kode_brng=databarang.kode_brng "
                            + " inner join kodesatuan on databarang.kode_sat=kodesatuan.kode_sat "
                            + " inner join kodesatuan as satuanbesar on databarang.kode_satbesar=satuanbesar.kode_sat "
                            + " inner join jenis on databarang.kdjns=jenis.kdjns "
                            + " inner join industrifarmasi on databarang.kode_industri=industrifarmasi.kode_industri "
                            + " inner join golongan_barang on databarang.kode_golongan=golongan_barang.kode "
                            + " inner join kategori_barang on databarang.kode_kategori=kategori_barang.kode "
                            + " inner join detail_pemberian_obat on detail_pemberian_obat.kode_brng=data_batch.kode_brng and detail_pemberian_obat.no_batch=data_batch.no_batch and detail_pemberian_obat.no_faktur=data_batch.no_faktur "
                            + " where detail_pemberian_obat.no_rawat=? AND detail_pemberian_obat.status='Ranap' group by databarang.kode_brng,detail_pemberian_obat.no_batch,detail_pemberian_obat.no_faktur order by databarang.nama_brng");
                    try {
                        ps4.setString(1,NoRetur);
                        rs = ps4.executeQuery();
                        while (rs.next()) {
                            tabMode.addRow(new Object[]{
                                false, rs.getString("kode_brng"),
                                rs.getString("nama_brng"),
                                rs.getString("kode_satbesar"),
                                rs.getString("satuanbesar"),
                                rs.getDouble("isi"),
                                rs.getString("kode_sat"),
                                rs.getString("satuan"),
                                rs.getDouble("kapasitas"),
                                rs.getString("letak_barang"),
                                rs.getDouble("dasar"),
                                rs.getDouble("h_beli"),
                                rs.getDouble("ralan"),
                                rs.getDouble("kelas1"),
                                rs.getDouble("kelas2"),
                                rs.getDouble("kelas3"),
                                rs.getDouble("utama"),
                                rs.getDouble("vip"),
                                rs.getDouble("vvip"),
                                rs.getDouble("beliluar"),
                                rs.getDouble("jualbebas"),
                                rs.getDouble("karyawan"),
                                rs.getDouble("stokminimal"),
                                rs.getString("kdjns"),
                                rs.getString("nama"),
                                rs.getString("expire"),
                                rs.getString("kode_industri"),
                                rs.getString("nama_industri"),
                                rs.getString("kode_kategori"),
                                rs.getString("kategori"),
                                rs.getString("kode_golongan"),
                                rs.getString("golongan"),
                                rs.getString("no_batch"),
                                rs.getString("no_faktur")
                            });
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }finally{
                        if(rs != null){
                            rs.close();
                        }
                        if(ps4 != null){
                            ps4.close();
                        }
                    }
                    LCount.setText("" + tabMode.getRowCount());
                    if(tabMode.getRowCount()==0){
                       tampil();
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
            }else{
                Valid.tabelKosong(tabMode);
                try {
                    ps4 = koneksi.prepareStatement(
                            "select databarang.kode_brng, databarang.nama_brng,databarang.kode_satbesar,satuanbesar.satuan as satuanbesar, "
                            + " databarang.isi,databarang.kode_sat,kodesatuan.satuan,databarang.letak_barang,databarang.dasar,databarang.h_beli,"
                            + " databarang.ralan,databarang.kelas1,databarang.kelas2,databarang.kelas3,"
                            + " databarang.utama,databarang.vip,databarang.vvip,databarang.beliluar,databarang.jualbebas,"
                            + " databarang.karyawan,databarang.stokminimal, databarang.kdjns,"
                            + " jenis.nama,kapasitas,databarang.expire,databarang.kode_industri,industrifarmasi.nama_industri, "
                            + " databarang.kode_kategori,kategori_barang.nama as kategori,databarang.kode_golongan,golongan_barang.nama as golongan "
                            + " from databarang inner join kodesatuan on databarang.kode_sat=kodesatuan.kode_sat "
                            + " inner join kodesatuan as satuanbesar on databarang.kode_satbesar=satuanbesar.kode_sat "
                            + " inner join jenis on databarang.kdjns=jenis.kdjns "
                            + " inner join industrifarmasi on databarang.kode_industri=industrifarmasi.kode_industri "
                            + " inner join golongan_barang on databarang.kode_golongan=golongan_barang.kode "
                            + " inner join kategori_barang on databarang.kode_kategori=kategori_barang.kode "
                            + " inner join detail_pemberian_obat on detail_pemberian_obat.kode_brng=databarang.kode_brng "
                            + " where detail_pemberian_obat.status = 'Ranap' AND detail_pemberian_obat.no_rawat=? group by databarang.kode_brng order by databarang.nama_brng");
                    try {
                        ps4.setString(1,NoRetur);
                        rs = ps4.executeQuery();
                        while (rs.next()) {
                            tabMode.addRow(new Object[]{
                                false, rs.getString("kode_brng"),
                                rs.getString("nama_brng"),
                                rs.getString("kode_satbesar"),
                                rs.getString("satuanbesar"),
                                rs.getDouble("isi"),
                                rs.getString("kode_sat"),
                                rs.getString("satuan"),
                                rs.getDouble("kapasitas"),
                                rs.getString("letak_barang"),
                  
                               
                                rs.getDouble("stokminimal"),
                                rs.getString("kdjns"),
                                rs.getString("nama"),
                                rs.getString("expire"),
                                rs.getString("kode_industri"),
                                rs.getString("nama_industri"),
                                rs.getString("kode_kategori"),
                                rs.getString("kategori"),
                                rs.getString("kode_golongan"),
                                rs.getString("golongan")
                            });
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }finally{
                        if(rs != null){
                            rs.close();
                        }
                        if(ps4 != null){
                            ps4.close();
                        }
                    }
                    LCount.setText("" + tabMode.getRowCount());
                    if(tabMode.getRowCount()==0){
                       tampil();
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
            }
        }else{
            tampil();
        }    
            
    }

    public void emptTeks() {
      
      
        //Valid.autoNomer("databarang","B",9,Kd);
    }

    private void getData() {
        int row = tbObat.getSelectedRow();
        if (row != -1) {
          
        }
    }

    public JTable getTable() {
        return tbObat;
    }

   
    public void setLokasi(String kodelokasi,String namalokasi){
        this.kdlokasi=kodelokasi;
        this.nmlokasi=namalokasi;
    }
            
    public void isCek() {
        TCari.requestFocus();
        
        BtnPrint.setEnabled(akses.getobat());
        if(akses.getkode().equals("Admin Utama")){
            MnRestore.setEnabled(true);
        }else{
            MnRestore.setEnabled(false);
        } 
    }
    
    public void isBatch(){
        tabMode = new DefaultTableModel(null,new Object[]{
                "P", "Kode Barang", "Nama Barang", "Kd.Sat Besar", "Nm.Satuan Besar","Isi", "Kd.Sat Kecil", "Nm.Satuan Kecil",
                "Kps", "Kandungan", 
                "Stok Min", "Kode Jenis", "Nama Jenis","Kadaluwarsa","Kode I.F.","Industri Farmasi","Kode Kategori","Kategori",
                "Kode Golongan","Golongan","No.Batch","No.Faktur"
            }) {
                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    boolean a = false;
                    if (colIndex == 0) {
                        a = true;
                    }
                    return a;
                }
                Class[] types = new Class[]{
                    java.lang.Boolean.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                    java.lang.Double.class,java.lang.Object.class,java.lang.Object.class,java.lang.Double.class,java.lang.Object.class,
                    java.lang.Double.class,java.lang.Double.class,java.lang.Double.class,java.lang.Double.class,java.lang.Double.class,
                    java.lang.Double.class,java.lang.Double.class,java.lang.Double.class,java.lang.Double.class,java.lang.Double.class,
                    java.lang.Double.class,java.lang.Double.class,java.lang.Double.class,java.lang.Object.class,java.lang.Object.class,
                    java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                    java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
                };

                @Override
                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }
            };
            tbObat.setModel(tabMode);

            tbObat.setPreferredScrollableViewportSize(new Dimension(800, 800));
            tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            for (i = 0; i < 34; i++) {
                TableColumn column = tbObat.getColumnModel().getColumn(i);
                if (i == 0) {
                    column.setPreferredWidth(20);
                } else if (i == 1) {
                    column.setPreferredWidth(85);
                } else if (i == 2) {
                    column.setPreferredWidth(200);
                } else if (i == 3) {
                    column.setPreferredWidth(68);
                } else if (i == 4) {
                    column.setPreferredWidth(90);
                } else if (i == 5) {
                    column.setPreferredWidth(37);
                } else if (i == 6) {
                    column.setPreferredWidth(68);
                } else if (i == 7) {
                    column.setPreferredWidth(90);
                } else if (i == 8) {
                    column.setPreferredWidth(37);
                } else if (i == 9) {
                    column.setPreferredWidth(120);
                } else if (i == 10) {
                    column.setPreferredWidth(85);
                } else if (i == 11) {
                    column.setPreferredWidth(85);
                } else if (i == 12) {
                    column.setPreferredWidth(85);
                } else if (i == 13) {
                    column.setPreferredWidth(85);
                } else if (i == 14) {
                    column.setPreferredWidth(85);
                } else if (i == 15) {
                    column.setPreferredWidth(85);
                } else if (i == 16) {
                    column.setPreferredWidth(85);
                } else if (i == 17) {
                    column.setPreferredWidth(85);
                } else if (i == 18) {
                    column.setPreferredWidth(85);
                } else if (i == 19) {
                    column.setPreferredWidth(85);
                } else if (i == 20) {
                    column.setPreferredWidth(85);
                } else if (i == 21) {
                    column.setPreferredWidth(85);
                } else if (i == 22) {
                    column.setPreferredWidth(50);
                } else if (i == 23) {
                    column.setMinWidth(0);
                    column.setMaxWidth(0);
                } else if (i == 24) {
                    column.setPreferredWidth(120);
                } else if (i == 25) {
                    column.setPreferredWidth(70);
                } else if (i == 26) {
                    column.setMinWidth(0);
                    column.setMaxWidth(0);
                } else if (i == 27) {
                    column.setPreferredWidth(120);
                } else if (i == 28) {
                    column.setMinWidth(0);
                    column.setMaxWidth(0);
                } else if (i == 29) {
                    column.setPreferredWidth(120);
                } else if (i == 30) {
                    column.setMinWidth(0);
                    column.setMaxWidth(0);
                } else if (i == 31) {
                    column.setPreferredWidth(120);
                } else if (i == 32) {
                    column.setPreferredWidth(80);
                } else if (i == 33) {
                    column.setPreferredWidth(100);
                }
            }
            tbObat.setDefaultRenderer(Object.class, new WarnaTable());
    }

}
