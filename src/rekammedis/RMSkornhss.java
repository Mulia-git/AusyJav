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
import java.awt.Component;
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
public final class RMSkornhss extends javax.swing.JDialog {
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
   
    private javax.swing.JComboBox SkalaKriteria1b = new javax.swing.JComboBox();;
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
   private javax.swing.JLabel labelSkalaBaru=new javax.swing.JLabel();;
   private  javax.swing.JLabel label1b = new javax.swing.JLabel();
    private  javax.swing.JLabel labelSkala1b = new javax.swing.JLabel();
    private  javax.swing.JLabel labelNilai1b = new javax.swing.JLabel();

     
   private widget.TextBox NilaiKriteria1b = new widget.TextBox();

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
    
    private javax.swing.JLabel label1c;
    private javax.swing.JLabel labelSkala1c;
    private javax.swing.JLabel labelSkala2baru;
    private javax.swing.JLabel labelNilai2baru;
    private javax.swing.JLabel labelNilai1c;
    private javax.swing.JLabel label3baru;
    private javax.swing.JLabel labelSkala3baru;
    private javax.swing.JLabel labelNilai3baru;
    private javax.swing.JLabel label4baru;
    private javax.swing.JLabel labelSkala4baru;
    private javax.swing.JLabel labelNilai4baru;
    private javax.swing.JLabel label5baru;
    private javax.swing.JLabel labelSkala5baru;
    private javax.swing.JLabel labelNilai5baru;
    private javax.swing.JLabel labelJelaskan5;
    private javax.swing.JLabel label6baru;
    private javax.swing.JLabel labelSkala6baru;
    private javax.swing.JLabel labelNilai6baru;
    private javax.swing.JLabel labelJelaskan6;
    private javax.swing.JLabel label7baru;
    private javax.swing.JLabel labelSkala7baru;
    private javax.swing.JLabel labelNilai7baru;
    private javax.swing.JLabel label8baru;
    private javax.swing.JLabel labelSkala8baru;
    private javax.swing.JLabel labelNilai8baru;
    private javax.swing.JLabel label9baru;
    private javax.swing.JLabel labelSkala9baru;
    private javax.swing.JLabel labelNilai9baru;
    private javax.swing.JLabel label10baru;
    private javax.swing.JLabel labelSkala10baru;
    private javax.swing.JLabel labelNilai10baru;
    private javax.swing.JLabel labelJelaskan10;
    private widget.TextBox txtJelaskan10;
    private widget.TextBox label2baru;
    private javax.swing.JComboBox LokasiLengan = new javax.swing.JComboBox();
    private javax.swing.JComboBox LokasiTungkai = new javax.swing.JComboBox();




    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMSkornhss(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(800,674);
      
        int rowGap = 35;
        int y = jLabel57.getY() + jLabel57.getHeight() + 10;
  
       

      
        // ============================================================
        // BARIS 6: "1a. Tingkat Kesadaran" (UPDATE POSISI)
        // ============================================================

        // 1. Label Judul
        jLabel232.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel232.setFont(new java.awt.Font("Tahoma", 0, 11));
        jLabel232.setText("1a. Tingkat Kesadaran");
        jLabel232.setName("jLabel232"); 
        FormInput.add(jLabel232);
        

        // 2. Label "Skala :" (Manual dibuat baru)
        labelSkalaBaru = new javax.swing.JLabel();
        labelSkalaBaru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelSkalaBaru.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelSkalaBaru.setText("Skala :");
        labelSkalaBaru.setName("labelSkalaBaru");
        
        FormInput.add(labelSkalaBaru);

      
        SkalaKriteria6.setName("SkalaKriteria6");
        SkalaKriteria6.setFont(new java.awt.Font("Tahoma", 0, 11)); 
        SkalaKriteria6.setModel(new DefaultComboBoxModel<>(new String[] {
            "-", 
            "0 = Sadar penuh",
            "1 = Tidak sadar penuh; dapat dibangunkan dengan stimulasi minor (suara)",
            "2 = Tidak sadar penuh; dapat berespon dengan stimulasi berulang atau stimulasi nyeri",
            "3 = Koma; tidak sadar dan tidak berespon dengan stimulasi apapun" 
        }));
        SkalaKriteria6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKriteria6ItemStateChanged(evt);
            }
        
            private void SkalaKriteria6ItemStateChanged(java.awt.event.ItemEvent evt) {
                if (SkalaKriteria6.getSelectedIndex() == 0) {
                    NilaKriteria6.setText("0");
                } else if (SkalaKriteria6.getSelectedIndex() == 1) {
                    NilaKriteria6.setText("0");
                } else if (SkalaKriteria6.getSelectedIndex() == 2) {
                    NilaKriteria6.setText("1");
                } else if (SkalaKriteria6.getSelectedIndex() == 3) {
                    NilaKriteria6.setText("2");
                } else {
                    NilaKriteria6.setText("3");
                }
                isTotalResiko();
            }
        });

        SkalaKriteria6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKriteria6KeyPressed(evt);
            }
            private void SkalaKriteria6KeyPressed(java.awt.event.KeyEvent evt) {
                    Valid.pindah(evt, SkalaKriteria5, SkalaKriteria1b);
                }
        });

        FormInput.add(SkalaKriteria6);

       
       

       
        // 5. Textbox Input Nilai
        NilaKriteria6.setName("NilaKriteria6");
        NilaKriteria6.setFont(new java.awt.Font("Tahoma", 0, 11));
        FormInput.add(NilaKriteria6);
     // 1a. Tingkat Kesadaran (GRID SAMA)
 

      // ============================================================
    // BARIS 1b: MENJAWAB PERTANYAAN 
    // ============================================================
    
        // 1. Label Judul
       
        label1b.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label1b.setFont(new java.awt.Font("Tahoma", 0, 11));
        label1b.setText("1b. Menjawab Pertanyaan");
        label1b.setName("label1b");
        FormInput.add(label1b);
       

        // 2. Label Skala
     
        labelSkala1b.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelSkala1b.setText("Skala :");
        labelSkala1b.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelSkala1b.setName("labelSkala1b");
        FormInput.add(labelSkala1b);
        

        // 3. Dropdown
        SkalaKriteria1b = new javax.swing.JComboBox();
        SkalaKriteria1b.setName("SkalaKriteria1b");
        SkalaKriteria1b.setFont(new java.awt.Font("Tahoma", 0, 11));
        SkalaKriteria1b.setModel(new DefaultComboBoxModel<>(new String[] {
            "-", 
            "0 = Benar semua",
            "1 = 1 benar/ETT/disartria",
            "2 = Salah semua/afasia/stupor/koma"
        }));
        SkalaKriteria1b.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKriteria1bItemStateChanged(evt);
            }

            private void SkalaKriteria1bItemStateChanged(java.awt.event.ItemEvent evt) {
                if (SkalaKriteria1b.getSelectedIndex() == 0) {
                    NilaiKriteria1b.setText("0");
                } else if (SkalaKriteria1b.getSelectedIndex() == 1) {
                    NilaiKriteria1b.setText("0");
                } else if (SkalaKriteria1b.getSelectedIndex() == 2) {
                    NilaiKriteria1b.setText("1");
                } else {
                    NilaiKriteria1b.setText("2");
                }
                isTotalResiko();
            }
        });

        SkalaKriteria1b.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKriteria1bKeyPressed(evt);
            }

            private void SkalaKriteria1bKeyPressed(java.awt.event.KeyEvent evt) {
                Valid.pindah(evt, SkalaKriteria6, SkalaKriteria1c);
            }
        });
        FormInput.add(SkalaKriteria1b);
       

        // 4. Label Nilai
        
        labelNilai1b.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelNilai1b.setText("Nilai :");
        labelNilai1b.setName("labelNilai1b");
        FormInput.add(labelNilai1b);
   

        // 5. Textbox Nilai (PAKAI widget.TextBox AGAR BULAT)
        NilaiKriteria1b = new widget.TextBox(); // <--- INI KUNCINYA
        NilaiKriteria1b.setName("NilaiKriteria1b");
        NilaiKriteria1b.setFont(new java.awt.Font("Tahoma", 0, 11));
        NilaiKriteria1b.setText("0");
        NilaiKriteria1b.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NilaiKriteria1b.setEditable(false);
        NilaiKriteria1b.setFocusTraversalPolicyProvider(true);
        FormInput.add(NilaiKriteria1b);
     
       
        y += rowGap;
        label1b.setBounds(34, y, 130, 23);
        labelSkala1b.setBounds(165, y, 40, 23);
        SkalaKriteria1b.setBounds(210, y, 460, 23);
        labelNilai1b.setBounds(690, y, 50, 23);
        NilaiKriteria1b.setBounds(729, y, 60, 23);

        y += rowGap;


        // ============================================================
        // BARIS 1c: MENGIKUTI PERINTAH (Y = 345)
        // ============================================================

        javax.swing.JLabel label1c = new javax.swing.JLabel();
        label1c.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label1c.setFont(new java.awt.Font("Tahoma", 0, 11));
        label1c.setText("1c. Mengikuti Perintah");
        label1c.setName("label1c");
        FormInput.add(label1c);
     

        javax.swing.JLabel labelSkala1c = new javax.swing.JLabel();
        labelSkala1c.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelSkala1c.setText("Skala :");
        labelSkala1c.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelSkala1c.setName("labelSkala1c");
        FormInput.add(labelSkala1c);
       

        SkalaKriteria1c = new javax.swing.JComboBox();
        SkalaKriteria1c.setName("SkalaKriteria1c");
        SkalaKriteria1c.setFont(new java.awt.Font("Tahoma", 0, 11));
        SkalaKriteria1c.setModel(new DefaultComboBoxModel<>(new String[] {
            "-", 
            "0 = Mampu melakukan 2 perintah",
            "1 = Mampu melakukan 1 perintah",
            "2 = Tidak mampu melakukan perintah"
        }));
        SkalaKriteria1c.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKriteria1cItemStateChanged(evt);
            }

            private void SkalaKriteria1cItemStateChanged(java.awt.event.ItemEvent evt) {
                if (SkalaKriteria1c.getSelectedIndex() == 0) {
                    NilaiKriteria1c.setText("0");
                } else if (SkalaKriteria1c.getSelectedIndex() == 1) {
                    NilaiKriteria1c.setText("0");
                } else if (SkalaKriteria1c.getSelectedIndex() == 2) {
                    NilaiKriteria1c.setText("1");
                } else {
                    NilaiKriteria1c.setText("2");
                }
                isTotalResiko();
            }
        });

        SkalaKriteria1c.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKriteria1cKeyPressed(evt);
            }

            private void SkalaKriteria1cKeyPressed(java.awt.event.KeyEvent evt) {
                Valid.pindah(evt, SkalaKriteria1b, SkalaKriteria2baru);
            }
        });

        FormInput.add(SkalaKriteria1c);
       

        javax.swing.JLabel labelNilai1c = new javax.swing.JLabel();
        labelNilai1c.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelNilai1c.setText("Nilai :");
        labelNilai1c.setName("labelNilai1c");
        FormInput.add(labelNilai1c);
       


        NilaiKriteria1c = new widget.TextBox();
        NilaiKriteria1c.setName("NilaiKriteria1c");
        NilaiKriteria1c.setFont(new java.awt.Font("Tahoma", 0, 11));
        NilaiKriteria1c.setText("0");
        NilaiKriteria1c.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NilaiKriteria1c.setEditable(false);
        NilaiKriteria1c.setFocusTraversalPolicyProvider(true);
        FormInput.add(NilaiKriteria1c);
      

        label1c.setBounds(34, y, 130, 23);
        labelSkala1c.setBounds(165, y, 40, 23);
        SkalaKriteria1c.setBounds(210, y, 460, 23);
        labelNilai1c.setBounds(690, y, 50, 23);
        NilaiKriteria1c.setBounds(729, y, 60, 23);

        y += rowGap;
        // ============================================================
        // BARIS 2: GAZE / GERAKAN MATA (Y = 380)
        // ============================================================

        javax.swing.JLabel label2baru = new javax.swing.JLabel();
        label2baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label2baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        label2baru.setText("2. Gaze: Gerakan Mata");
        label2baru.setName("label2baru");
        FormInput.add(label2baru);
       

        javax.swing.JLabel labelSkala2baru = new javax.swing.JLabel();
        labelSkala2baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelSkala2baru.setText("Skala :");
        labelSkala2baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelSkala2baru.setName("labelSkala2baru");
        FormInput.add(labelSkala2baru);
      

        SkalaKriteria2baru = new javax.swing.JComboBox();
        SkalaKriteria2baru.setName("SkalaKriteria2baru");
        SkalaKriteria2baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        SkalaKriteria2baru.setModel(new DefaultComboBoxModel<>(new String[] {
            "-", 
            "0 = Normal",
            "1 = Paresis gaze parsial pada 1 atau 2 mata, terdapat abnormal gaze",
            "2 = Forced deviation, atau paresis gaze total tidak dapat diatasi"
        }));
        SkalaKriteria2baru.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKriteria2baruItemStateChanged(evt);
            }
            private void SkalaKriteria2baruItemStateChanged(java.awt.event.ItemEvent evt) {
                if (SkalaKriteria2baru.getSelectedIndex() == 0) {
                    NilaiKriteria2baru.setText("0");
                } else if (SkalaKriteria2baru.getSelectedIndex() == 1) {
                    NilaiKriteria2baru.setText("0");
                } else if (SkalaKriteria2baru.getSelectedIndex() == 2) {
                    NilaiKriteria2baru.setText("1");
                } else {
                    NilaiKriteria2baru.setText("2");
                }
                isTotalResiko();
            }
        });

        SkalaKriteria2baru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKriteria2baruKeyPressed(evt);
            }
            private void SkalaKriteria2baruKeyPressed(java.awt.event.KeyEvent evt) {
                Valid.pindah(evt, SkalaKriteria1c, SkalaKriteria3baru);
            }
        });

        FormInput.add(SkalaKriteria2baru);
       
        javax.swing.JLabel labelNilai2baru = new javax.swing.JLabel();
        labelNilai2baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelNilai2baru.setText("Nilai :");
        labelNilai2baru.setName("labelNilai2baru");
        FormInput.add(labelNilai2baru);
        


        NilaiKriteria2baru = new widget.TextBox();
        NilaiKriteria2baru.setName("NilaiKriteria2baru");
        NilaiKriteria2baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        NilaiKriteria2baru.setText("0");
        NilaiKriteria2baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NilaiKriteria2baru.setEditable(false);
        NilaiKriteria2baru.setFocusTraversalPolicyProvider(true);
        FormInput.add(NilaiKriteria2baru);
   

        label2baru.setBounds(34, y, 130, 23);
        labelSkala2baru.setBounds(165, y, 40, 23);
        SkalaKriteria2baru.setBounds(210, y, 460, 23);
        labelNilai2baru.setBounds(690, y, 50, 23);
        NilaiKriteria2baru.setBounds(729, y, 60, 23);

     
        y += rowGap;

        // ============================================================
        // BARIS 3: VISUAL / LAPANG PANDANG (Y = 415)
        // ============================================================

        javax.swing.JLabel label3baru = new javax.swing.JLabel();
        label3baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label3baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        label3baru.setText("3. Visual: Lapang Pandang");
        label3baru.setName("label3baru");
        FormInput.add(label3baru);
       
        javax.swing.JLabel labelSkala3baru = new javax.swing.JLabel();
        labelSkala3baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelSkala3baru.setText("Skala :");
        labelSkala3baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelSkala3baru.setName("labelSkala3baru");
        FormInput.add(labelSkala3baru);
       

        SkalaKriteria3baru = new javax.swing.JComboBox();
        SkalaKriteria3baru.setName("SkalaKriteria3baru");
        SkalaKriteria3baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        SkalaKriteria3baru.setModel(new DefaultComboBoxModel<>(new String[] {
            "-", 
            "0 = Tidak ada gangguan",
            "1 = Paralisis minor (sulcus nasolabial rata, asimetri saat tersenyum)",
            "2 = Paralisis parsial (paralisis total/near-total dari wajah bawah)",
            "3 = Paralisis komplit dari satu atau kedua sisi wajah"
        }));
        SkalaKriteria3baru.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKriteria3baruItemStateChanged(evt);
            }
            private void SkalaKriteria3baruItemStateChanged(java.awt.event.ItemEvent evt) {
                if (SkalaKriteria3baru.getSelectedIndex() == 0) {
                    NilaiKriteria3baru.setText("0");
                } else if (SkalaKriteria3baru.getSelectedIndex() == 1) {
                    NilaiKriteria3baru.setText("0");
                } else if (SkalaKriteria3baru.getSelectedIndex() == 2) {
                    NilaiKriteria3baru.setText("1");
                } else {
                    NilaiKriteria3baru.setText("2");
                }
                isTotalResiko();
            }
        });

        SkalaKriteria3baru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKriteria3baruKeyPressed(evt);
            }
            private void SkalaKriteria3baruKeyPressed(java.awt.event.KeyEvent evt) {
                Valid.pindah(evt, SkalaKriteria2baru, SkalaKriteria4baru);
            }
        });
        FormInput.add(SkalaKriteria3baru);
        

        javax.swing.JLabel labelNilai3baru = new javax.swing.JLabel();
        labelNilai3baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelNilai3baru.setText("Nilai :");
        labelNilai3baru.setName("labelNilai3baru");
        FormInput.add(labelNilai3baru);
        labelNilai3baru.setBounds(690, 415, 50, 23);

        // Textbox Nilai (PAKAI widget.TextBox)
        NilaiKriteria3baru = new widget.TextBox();
        NilaiKriteria3baru.setName("NilaiKriteria3baru");
        NilaiKriteria3baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        NilaiKriteria3baru.setText("0");
        NilaiKriteria3baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NilaiKriteria3baru.setEditable(false);
        NilaiKriteria3baru.setFocusTraversalPolicyProvider(true);
        FormInput.add(NilaiKriteria3baru);
       

        label3baru.setBounds(34, y, 130, 23);
        labelSkala3baru.setBounds(165, y, 40, 23);
        SkalaKriteria3baru.setBounds(210, y, 460, 23);
        labelNilai3baru.setBounds(690, y, 50, 23); 
        NilaiKriteria3baru.setBounds(729, y, 60, 23);
         y += rowGap;

      


        // ============================================================
        // BARIS 4: PARESIS WAJAH (Y = 450)
        // ============================================================

        javax.swing.JLabel label4baru = new javax.swing.JLabel();
        label4baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label4baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        label4baru.setText("4. Paresis Wajah");
        label4baru.setName("label4baru");
        FormInput.add(label4baru);
        

        javax.swing.JLabel labelSkala4baru = new javax.swing.JLabel();
        labelSkala4baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelSkala4baru.setText("Skala :");
        labelSkala4baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelSkala4baru.setName("labelSkala4baru");
        FormInput.add(labelSkala4baru);
       

        SkalaKriteria4baru = new javax.swing.JComboBox();
        SkalaKriteria4baru.setName("SkalaKriteria4baru");
        SkalaKriteria4baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        SkalaKriteria4baru.setModel(new DefaultComboBoxModel<>(new String[] {
            "-", 
            "0 = Normal",
            "1 = Paralisis minor (sulcus nasolabial rata, asimetri saat tersenyum)",
            "2 = Paralisis parsial (paralisis total/near-total dari wajah bawah)",
            "3 = Paralisis komplit dari satu atau kedua sisi wajah"
        }));
        SkalaKriteria4baru.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKriteria4baruItemStateChanged(evt);
            }
            private void SkalaKriteria4baruItemStateChanged(java.awt.event.ItemEvent evt) {
                if (SkalaKriteria4baru.getSelectedIndex() == 0) {
                    NilaiKriteria4baru.setText("0");
                } else if (SkalaKriteria4baru.getSelectedIndex() == 1) {
                    NilaiKriteria4baru.setText("0");
                } else if (SkalaKriteria4baru.getSelectedIndex() == 2) {
                    NilaiKriteria4baru.setText("1");
                } else {
                    NilaiKriteria4baru.setText("2");
                }
                isTotalResiko();
            }

        });

        SkalaKriteria4baru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKriteria4baruKeyPressed(evt);
            }
            private void SkalaKriteria4baruKeyPressed(java.awt.event.KeyEvent evt) {
                Valid.pindah(evt, SkalaKriteria3baru, SkalaKriteria5baru);
            }
        });
        FormInput.add(SkalaKriteria4baru);
        

        javax.swing.JLabel labelNilai4baru = new javax.swing.JLabel();
        labelNilai4baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelNilai4baru.setText("Nilai :");
        labelNilai4baru.setName("labelNilai4baru");
        FormInput.add(labelNilai4baru);
        


        NilaiKriteria4baru = new widget.TextBox();
        NilaiKriteria4baru.setName("NilaiKriteria4baru");
        NilaiKriteria4baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        NilaiKriteria4baru.setText("0");
        NilaiKriteria4baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NilaiKriteria4baru.setEditable(false);
        NilaiKriteria4baru.setFocusTraversalPolicyProvider(true);
        FormInput.add(NilaiKriteria4baru);
      label4baru.setBounds(34, y, 130, 23);
        labelSkala4baru.setBounds(165, y, 40, 23);
        SkalaKriteria4baru.setBounds(210, y, 460, 23);
        labelNilai4baru.setBounds(690, y, 50, 23);
        NilaiKriteria4baru.setBounds(729, y, 60, 23);

          
        y += rowGap;


        // ============================================================
        // BARIS 5: MOTORIK LENGAN (Y = 485)
        // ============================================================
              javax.swing.JLabel label5baru = new javax.swing.JLabel();
        label5baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label5baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        label5baru.setText("5. Motorik Lengan");
        label5baru.setName("label5baru");
        FormInput.add(label5baru);
       

        javax.swing.JLabel labelSkala5baru = new javax.swing.JLabel();
        labelSkala5baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelSkala5baru.setText("Skala :");
        labelSkala5baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelSkala5baru.setName("labelSkala5baru");
        FormInput.add(labelSkala5baru);
      

         SkalaKriteria5baru = new javax.swing.JComboBox();
        SkalaKriteria5baru.setName("SkalaKriteria5baru");
        SkalaKriteria5baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        SkalaKriteria5baru.setModel(new DefaultComboBoxModel<>(new String[] {
            "-",
            "0 = Tidak ada drift; lengan dapat diangkat 90 (45)°, selama minimal 10 detik penuh",
            "1 = Drift; lengan dapat diangkat 90 (45) namun turun sebelum 10 detik",
            "2 = Ada upaya melawan gravitasi; lengan tidak dapat diangkat/dipertahankan posisi 90 (45)°",
            "3 = Tidak ada upaya melawan gravitasi, tidak mampu mengangkat, hanya bergeser",
            "4 = Tidak ada gerakan",
            "UN = Amputasi atau fusi sendi"
        }));
      SkalaKriteria5baru.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                int idx = SkalaKriteria5baru.getSelectedIndex();

                if (idx == 0) {          
                    NilaiKriteria5baru.setText("0");
                } else if (idx >= 1 && idx <= 5) {
                    NilaiKriteria5baru.setText(String.valueOf(idx - 1));
                } else {                
                    NilaiKriteria5baru.setText("0");
                }
                isTotalResiko();
            }
        });

        SkalaKriteria5baru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKriteria5baruKeyPressed(evt);
            }
            private void SkalaKriteria5baruKeyPressed(java.awt.event.KeyEvent evt) {
                Valid.pindah(evt, SkalaKriteria4baru, SkalaKriteria6baru);
            }
        });
        FormInput.add(SkalaKriteria5baru);
       
        // 2. Dropdown LOKASI (Kanan/Kiri)
       
        LokasiLengan.setName("LokasiLengan");
        LokasiLengan.setFont(new java.awt.Font("Tahoma", 0, 11));
        LokasiLengan.setModel(new DefaultComboBoxModel<>(new String[] { "Kanan", "Kiri" }));
        FormInput.add(LokasiLengan);
      
        javax.swing.JLabel labelNilai5baru = new javax.swing.JLabel();
        labelNilai5baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelNilai5baru.setText("Nilai :");
        labelNilai5baru.setName("labelNilai5baru");
        FormInput.add(labelNilai5baru);
        

        NilaiKriteria5baru = new widget.TextBox();
        NilaiKriteria5baru.setName("NilaiKriteria5baru");
        NilaiKriteria5baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        NilaiKriteria5baru.setText("0");
        NilaiKriteria5baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NilaiKriteria5baru.setEditable(false);
        NilaiKriteria5baru.setFocusTraversalPolicyProvider(true);
        FormInput.add(NilaiKriteria5baru);
       

        javax.swing.JLabel labelJelaskan5 = new javax.swing.JLabel();
        labelJelaskan5.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelJelaskan5.setText("Jelaskan :");
        labelJelaskan5.setName("labelJelaskan5");
        labelJelaskan5.setVisible(false);
        FormInput.add(labelJelaskan5);
        labelJelaskan5.setBounds(34, 520, 130, 23);

        txtJelaskan5 = new widget.TextBox();
        txtJelaskan5.setName("txtJelaskan5");
        txtJelaskan5.setFont(new java.awt.Font("Tahoma", 0, 11));
        txtJelaskan5.setVisible(false);
        FormInput.add(txtJelaskan5);
       

        SkalaKriteria5baru.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                if (SkalaKriteria5baru.getSelectedItem().toString().startsWith("UN")) {
                    labelJelaskan5.setVisible(true);
                    txtJelaskan5.setVisible(true);
                    txtJelaskan5.requestFocus();
                } else {
                    labelJelaskan5.setVisible(false);
                    txtJelaskan5.setVisible(false);
                    txtJelaskan5.setText("");
                }
            }
        });
        label5baru.setBounds(34, y, 130, 23);
        labelSkala5baru.setBounds(165, y, 40, 23);
       SkalaKriteria5baru.setBounds(210, y, 320, 23);
        LokasiLengan.setBounds(535, y, 110, 23);
        labelNilai5baru.setBounds(670, y, 50, 23);
        NilaiKriteria5baru.setBounds(730, y, 60, 23);

        
       
        y += rowGap;

        labelJelaskan5.setBounds(34, y, 130, 23);
        txtJelaskan5.setBounds(165, y, 624, 23);

        // default hidden
        labelJelaskan5.setVisible(false);
        txtJelaskan5.setVisible(false);

      
        y += rowGap;



        // ============================================================
        // BARIS 6: MOTORIK TUNGKAI (Y = 555)
        // ============================================================
        javax.swing.JLabel label6baru = new javax.swing.JLabel();
        label6baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label6baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        label6baru.setText("6. Motorik Tungkai");
        label6baru.setName("label6baru");
        FormInput.add(label6baru);
        

        javax.swing.JLabel labelSkala6baru = new javax.swing.JLabel();
        labelSkala6baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelSkala6baru.setText("Skala :");
        labelSkala6baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelSkala6baru.setName("labelSkala6baru");
        FormInput.add(labelSkala6baru);
        

        SkalaKriteria6baru = new javax.swing.JComboBox();
        SkalaKriteria6baru.setName("SkalaKriteria6baru");
        SkalaKriteria6baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        SkalaKriteria6baru.setModel(new DefaultComboBoxModel<>(new String[] {
            "-",
            "0 = Tidak ada drift; tungkai dapat dipertahankan dalam posisi 30° minimal 5 detik",
            "1 = Drift; tungkai jatuh persis 5 detik, namun tidak mengenai tempat tidur",
            "2 = Ada upaya melawan gravitasi; tungkai jatuh mengenai tempat tidur dalam 5 detik",
            "3 = Tidak ada upaya melawan gravitasi",
            "4 = Tidak ada gerakan",
            "UN = Amputasi atau fusi sendi Jelaskan"
        }));
       SkalaKriteria6baru.addItemListener(new java.awt.event.ItemListener() {
                    public void itemStateChanged(java.awt.event.ItemEvent evt) {
                        int idx = SkalaKriteria6baru.getSelectedIndex();

                        if (idx == 0) {          
                            NilaiKriteria6baru.setText("0");
                        } else if (idx >= 1 && idx <= 5) {
                            NilaiKriteria6baru.setText(String.valueOf(idx - 1));
                        } else {                
                            NilaiKriteria6baru.setText("0");
                        }
                        isTotalResiko();
                    }
                });


        SkalaKriteria6baru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKriteria6baruKeyPressed(evt);
            }
            private void SkalaKriteria6baruKeyPressed(java.awt.event.KeyEvent evt) {
                Valid.pindah(evt, SkalaKriteria5baru, SkalaKriteria7baru);
            }
        });
        FormInput.add(SkalaKriteria6baru);
        


        
        LokasiTungkai.setName("LokasiTungkai");
        LokasiTungkai.setFont(new java.awt.Font("Tahoma", 0, 11));
        LokasiTungkai.setModel(new DefaultComboBoxModel<>(new String[] { "Kanan", "Kiri" }));
        FormInput.add(LokasiTungkai);
       

        javax.swing.JLabel labelNilai6baru = new javax.swing.JLabel();
        labelNilai6baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelNilai6baru.setText("Nilai :");
        labelNilai6baru.setName("labelNilai6baru");
        FormInput.add(labelNilai6baru);
        

        NilaiKriteria6baru = new widget.TextBox();
        NilaiKriteria6baru.setName("NilaiKriteria6baru");
        NilaiKriteria6baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        NilaiKriteria6baru.setText("0");
        NilaiKriteria6baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NilaiKriteria6baru.setEditable(false);
        NilaiKriteria6baru.setFocusTraversalPolicyProvider(true);
        FormInput.add(NilaiKriteria6baru);
        


        javax.swing.JLabel labelJelaskan6 = new javax.swing.JLabel();
        labelJelaskan6.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelJelaskan6.setText("Jelaskan :");
        labelJelaskan6.setName("labelJelaskan6");
        labelJelaskan6.setVisible(false);
        FormInput.add(labelJelaskan6);
        

        txtJelaskan6 = new widget.TextBox();
        txtJelaskan6.setName("txtJelaskan6");
        txtJelaskan6.setFont(new java.awt.Font("Tahoma", 0, 11));
        txtJelaskan6.setVisible(false);
        FormInput.add(txtJelaskan6);
       


        SkalaKriteria6baru.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                if (SkalaKriteria6baru.getSelectedItem().toString().startsWith("UN")) {
                    labelJelaskan6.setVisible(true);
                    txtJelaskan6.setVisible(true);
                    txtJelaskan6.requestFocus();
                } else {
                    labelJelaskan6.setVisible(false);
                    txtJelaskan6.setVisible(false);
                    txtJelaskan6.setText("");
                }
            }
        });
        
        

     
        
        label6baru.setBounds(34, y, 130, 23);
        labelSkala6baru.setBounds(165, y, 40, 23);


       

       
       SkalaKriteria6baru.setBounds(210, y, 320, 23);
        LokasiTungkai.setBounds(535, y, 110, 23);
        labelNilai6baru.setBounds(670, y, 50, 23);
        NilaiKriteria6baru.setBounds(730, y, 60, 23);

        y += rowGap;

        labelJelaskan6.setBounds(34, y, 130, 23);
        txtJelaskan6.setBounds(165, y, 624, 23);



        // ============================================================
        // BARIS 7: ATAKSIA ANGGOTA GERAK (DIGESER KE Y = 625)
        // ============================================================
       javax.swing.JLabel label7baru = new javax.swing.JLabel();
        label7baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label7baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        label7baru.setText("7. Ataksia Anggota Gerak");
        label7baru.setName("label7baru");
        FormInput.add(label7baru);
       

        javax.swing.JLabel labelSkala7baru = new javax.swing.JLabel();
        labelSkala7baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelSkala7baru.setText("Skala :");
        labelSkala7baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelSkala7baru.setName("labelSkala7baru");
        FormInput.add(labelSkala7baru);
       

        SkalaKriteria7baru = new javax.swing.JComboBox();
        SkalaKriteria7baru.setName("SkalaKriteria7baru");
        SkalaKriteria7baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        SkalaKriteria7baru.setModel(new DefaultComboBoxModel<>(new String[] {
            "-",
            "0 = Tidak ada ataksia",
            "1 = Ataksia pada 1 ekstremitas",
            "2 = Ataksia pada 2 ekstremitas"
        }));
        SkalaKriteria7baru.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
            
                int idx = SkalaKriteria7baru.getSelectedIndex();

                if (idx == 0) {          // "-"
                    NilaiKriteria7baru.setText("0");
                } else {
                    // index = nilai langsung
                    NilaiKriteria7baru.setText(String.valueOf(idx - 1));
                }
                isTotalResiko();
            }
        });

        SkalaKriteria6baru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKriteria7baruKeyPressed(evt);
            }
            private void SkalaKriteria7baruKeyPressed(java.awt.event.KeyEvent evt) {
                Valid.pindah(evt, SkalaKriteria6baru, SkalaKriteria8baru);
            }
        });
        FormInput.add(SkalaKriteria7baru);
        
        javax.swing.JLabel labelNilai7baru = new javax.swing.JLabel();
        labelNilai7baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelNilai7baru.setText("Nilai :");
        labelNilai7baru.setName("labelNilai7baru");
        FormInput.add(labelNilai7baru);
        
        NilaiKriteria7baru = new widget.TextBox();
        NilaiKriteria7baru.setName("NilaiKriteria7baru");
        NilaiKriteria7baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        NilaiKriteria7baru.setText("0");
        NilaiKriteria7baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NilaiKriteria7baru.setEditable(false);
        NilaiKriteria7baru.setFocusTraversalPolicyProvider(true);
        FormInput.add(NilaiKriteria7baru);
       
        y += rowGap;

        label7baru.setBounds(34, y, 130, 23);
        labelSkala7baru.setBounds(165, y, 40, 23);
        SkalaKriteria7baru.setBounds(210, y, 460, 23);
        labelNilai7baru.setBounds(690, y, 50, 23);
        NilaiKriteria7baru.setBounds(729, y, 60, 23);

        // ============================================================
        // BARIS 8: SENSORIK (DIGESER KE Y = 660)
        // ============================================================
        javax.swing.JLabel label8baru = new javax.swing.JLabel();
        label8baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label8baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        label8baru.setText("8. Sensorik");
        label8baru.setName("label8baru");
        FormInput.add(label8baru);
      

        javax.swing.JLabel labelSkala8baru = new javax.swing.JLabel();
        labelSkala8baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelSkala8baru.setText("Skala :");
        labelSkala8baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelSkala8baru.setName("labelSkala8baru");
        FormInput.add(labelSkala8baru);
       

        SkalaKriteria8baru = new javax.swing.JComboBox();
        SkalaKriteria8baru.setName("SkalaKriteria8baru");
        SkalaKriteria8baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        SkalaKriteria8baru.setModel(new DefaultComboBoxModel<>(new String[] {
            "-",
            "0 = Normal; tidak ada gangguan sensorik",
            "1 = Gangguan sensorik ringan-sedang; berkurang namun masih terasa disentuh",
            "2 = Gangguan sensorik berat; tidak merasakan sentuhan di wajah, lengan, atau tungkai"
        }));
        SkalaKriteria8baru.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
              
                int idx = SkalaKriteria8baru.getSelectedIndex();

                if (idx == 0) {          // "-"
                    NilaiKriteria8baru.setText("0");
                } else {
                    // index = nilai langsung
                    NilaiKriteria8baru.setText(String.valueOf(idx - 1));
                }
                isTotalResiko();
            }
        });

        SkalaKriteria8baru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKriteria8baruKeyPressed(evt);
            }
            private void SkalaKriteria8baruKeyPressed(java.awt.event.KeyEvent evt) {
                Valid.pindah(evt, SkalaKriteria7baru, SkalaKriteria9baru);
            }
        });
        FormInput.add(SkalaKriteria8baru);
      
        javax.swing.JLabel labelNilai8baru = new javax.swing.JLabel();
        labelNilai8baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelNilai8baru.setText("Nilai :");
        labelNilai8baru.setName("labelNilai8baru");
        FormInput.add(labelNilai8baru);
       
        NilaiKriteria8baru = new widget.TextBox();
        NilaiKriteria8baru.setName("NilaiKriteria8baru");
        NilaiKriteria8baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        NilaiKriteria8baru.setText("0");
        NilaiKriteria8baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NilaiKriteria8baru.setEditable(false);
        NilaiKriteria8baru.setFocusTraversalPolicyProvider(true);
        FormInput.add(NilaiKriteria8baru);
       
        y += rowGap;

        label8baru.setBounds(34, y, 130, 23);
        labelSkala8baru.setBounds(165, y, 40, 23);
        SkalaKriteria8baru.setBounds(210, y, 460, 23);
        labelNilai8baru.setBounds(690, y, 50, 23);
        NilaiKriteria8baru.setBounds(729, y, 60, 23);



        // ============================================================
        // BARIS 9: BAHASA TERBALIK (DIGESER KE Y = 695)
        // ============================================================
        javax.swing.JLabel label9baru = new javax.swing.JLabel();
        label9baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label9baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        label9baru.setText("9. Bahasa Terbalik");
        label9baru.setName("label9baru");
        FormInput.add(label9baru);
     

        javax.swing.JLabel labelSkala9baru = new javax.swing.JLabel();
        labelSkala9baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelSkala9baru.setText("Skala :");
        labelSkala9baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelSkala9baru.setName("labelSkala9baru");
        FormInput.add(labelSkala9baru);
     

        SkalaKriteria9baru = new javax.swing.JComboBox();
        SkalaKriteria9baru.setName("SkalaKriteria9baru");
        SkalaKriteria9baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        SkalaKriteria9baru.setModel(new DefaultComboBoxModel<>(new String[] {
            "-",
            "0 = Normal; tidak ada afasia",
            "1 = Afasia ringan-sedang; dapat berkomunikasi namun terbatas",
            "2 = Afasia berat; seluruh komunikasi melalui ekspresi yang terfragmentasi",
            "3 = Mutisme, afasia global; tidak ada kata-kata yang keluar"
        }));
        SkalaKriteria9baru.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
              
                int idx = SkalaKriteria8baru.getSelectedIndex();

                if (idx == 0) {          // "-"
                    NilaiKriteria9baru.setText("0");
                } else {
                    // index = nilai langsung
                    NilaiKriteria9baru.setText(String.valueOf(idx - 1));
                }
                isTotalResiko();
            }
        });

        SkalaKriteria9baru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKriteria9baruKeyPressed(evt);
            }
            private void SkalaKriteria9baruKeyPressed(java.awt.event.KeyEvent evt) {
                Valid.pindah(evt, SkalaKriteria7baru, SkalaKriteria9baru);
            }
        });
                FormInput.add(SkalaKriteria9baru);
        

        javax.swing.JLabel labelNilai9baru = new javax.swing.JLabel();
        labelNilai9baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelNilai9baru.setText("Nilai :");
        labelNilai9baru.setName("labelNilai9baru");
        FormInput.add(labelNilai9baru);
      

        NilaiKriteria9baru = new widget.TextBox();
        NilaiKriteria9baru.setName("NilaiKriteria9baru");
        NilaiKriteria9baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        NilaiKriteria9baru.setText("0");
        NilaiKriteria9baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NilaiKriteria9baru.setEditable(false);
        NilaiKriteria9baru.setFocusTraversalPolicyProvider(true);
        FormInput.add(NilaiKriteria9baru);
      
        y += rowGap;

        label9baru.setBounds(34, y, 130, 23);
        labelSkala9baru.setBounds(165, y, 40, 23);
        SkalaKriteria9baru.setBounds(210, y, 460, 23);
        labelNilai9baru.setBounds(690, y, 50, 23);
        NilaiKriteria9baru.setBounds(729, y, 60, 23);



        // ============================================================
        // BARIS 10: DISARTRIA (DIGESER KE Y = 730)
        // ============================================================
        javax.swing.JLabel label10baru = new javax.swing.JLabel();
        label10baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label10baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        label10baru.setText("10. Disartria");
        label10baru.setName("label10baru");
        FormInput.add(label10baru);
       

        javax.swing.JLabel labelSkala10baru = new javax.swing.JLabel();
        labelSkala10baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelSkala10baru.setText("Skala :");
        labelSkala10baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelSkala10baru.setName("labelSkala10baru");
        FormInput.add(labelSkala10baru);
      
        SkalaKriteria10baru = new javax.swing.JComboBox();
        SkalaKriteria10baru.setName("SkalaKriteria10baru");
        SkalaKriteria10baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        SkalaKriteria10baru.setModel(new DefaultComboBoxModel<>(new String[] {
            "-",
            "0 = Normal",
            "1 = Disartria ringan-sedang; pelo pada beberapa kata namun dapat dimengerti",
            "2 = Disartria berat; bicara pasien sangat pelo namun tidak afasia",
            "UN = Intubasi atau hambatan fisik lain Jelaskan....."
        }));
          javax.swing.JLabel labelNilai10baru = new javax.swing.JLabel();
          NilaiKriteria10baru = new widget.TextBox();
        SkalaKriteria10baru.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKriteria10baruItemStateChanged(evt);
            }
            
            private void SkalaKriteria10baruItemStateChanged(java.awt.event.ItemEvent evt) {
                int idx = SkalaKriteria10baru.getSelectedIndex();

                if (idx == 0) {          // "-"
                    NilaiKriteria10baru.setText("0");
                } else if (idx == 1) {   // Normal
                    NilaiKriteria10baru.setText("0");
                } else if (idx == 2) {   // Disartria ringan-sedang
                    NilaiKriteria10baru.setText("1");
                } else if (idx == 3) {   // Disartria berat
                    NilaiKriteria10baru.setText("2");
                } else {                // UN
                    NilaiKriteria10baru.setText("0");
                }

                isTotalResiko();
            }


        });

        SkalaKriteria10baru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKriteria10baruKeyPressed(evt);
            }
            private void SkalaKriteria10baruKeyPressed(java.awt.event.KeyEvent evt) {
                Valid.pindah(evt, SkalaKriteria9baru, SkalaKriteria11baru);
            }
        });
        FormInput.add(SkalaKriteria10baru);
       

      
        labelNilai10baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelNilai10baru.setText("Nilai :");
        labelNilai10baru.setName("labelNilai10baru");
        FormInput.add(labelNilai10baru);
     
        
        NilaiKriteria10baru.setName("NilaiKriteria10baru");
        NilaiKriteria10baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        NilaiKriteria10baru.setText("0");
        NilaiKriteria10baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NilaiKriteria10baru.setEditable(false);
        NilaiKriteria10baru.setFocusTraversalPolicyProvider(true);
        FormInput.add(NilaiKriteria10baru);
        
        javax.swing.JLabel labelJelaskan10 = new javax.swing.JLabel();
        labelJelaskan10.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelJelaskan10.setText("Jelaskan :");
        labelJelaskan10.setName("labelJelaskan10");
        labelJelaskan10.setVisible(false);
        FormInput.add(labelJelaskan10);
        

      txtJelaskan10 = new widget.TextBox();
        txtJelaskan10.setName("txtJelaskan10");
        txtJelaskan10.setFont(new java.awt.Font("Tahoma", 0, 11));
        txtJelaskan10.setVisible(false);
        FormInput.add(txtJelaskan10);
       
        SkalaKriteria10baru.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                if (SkalaKriteria10baru.getSelectedItem().toString().startsWith("UN")) {
                    labelJelaskan10.setVisible(true);
                    txtJelaskan10.setVisible(true);
                    txtJelaskan10.requestFocus();
//                    geserBaris11(true);
                } else {
                    labelJelaskan10.setVisible(false);
                    txtJelaskan10.setVisible(false);
                    txtJelaskan10.setText("");
//                    geserBaris11(false);
                }
            }
        });


        y += rowGap;

        label10baru.setBounds(34, y, 130, 23);
        labelSkala10baru.setBounds(165, y, 40, 23);
        SkalaKriteria10baru.setBounds(210, y, 460, 23);
        labelNilai10baru.setBounds(690, y, 50, 23);
        NilaiKriteria10baru.setBounds(729, y, 60, 23);
        y += rowGap;

        labelJelaskan10.setBounds(34, y, 130, 23);
        txtJelaskan10.setBounds(165, y, 624, 23);




        // ============================================================
        // BARIS 11: PENGABAIAN & INATENSI (DIGESER KE Y = 765)
        // ============================================================
       
        label11baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label11baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        label11baru.setText("11. Pengabaian & Inatensi");
        label11baru.setName("label11baru");
        FormInput.add(label11baru);
        

      
        labelSkala11baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelSkala11baru.setText("Skala :");
        labelSkala11baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelSkala11baru.setName("labelSkala11baru");
        FormInput.add(labelSkala11baru);
       

        SkalaKriteria11baru = new javax.swing.JComboBox();
        SkalaKriteria11baru.setName("SkalaKriteria11baru");
        SkalaKriteria11baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        SkalaKriteria11baru.setModel(new DefaultComboBoxModel<>(new String[] {
            "-",
            "0 = Tidak ada neglect",
            "1 = Tidak ada atensi pada salah satu modalitas (visual, tactile, auditory, etc)",
            "2 = Tidak ada atensi pada lebih dari satu modalitas"
        }));
         NilaiKriteria11baru = new widget.TextBox();
        SkalaKriteria11baru.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    SkalaKriteria11baruItemStateChanged(evt);
                }
                private void SkalaKriteria11baruItemStateChanged(java.awt.event.ItemEvent evt) {
                    if (SkalaKriteria11baru.getSelectedIndex() == 0) {
                        NilaiKriteria11baru.setText("0");
                    } else if (SkalaKriteria11baru.getSelectedIndex() == 1) {
                        NilaiKriteria11baru.setText("0");
                    } else if (SkalaKriteria11baru.getSelectedIndex() == 2) {
                        NilaiKriteria11baru.setText("1");
                    } else {
                        NilaiKriteria11baru.setText("2");
                    }
                    isTotalResiko();
                }
                
            });

            SkalaKriteria11baru.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    SkalaKriteria11baruKeyPressed(evt);
                }
                private void SkalaKriteria11baruKeyPressed(java.awt.event.KeyEvent evt) {
                        Valid.pindah(evt, SkalaKriteria10baru, NilaiKriteriaTotal);
                    }
            });
                    FormInput.add(SkalaKriteria11baru);
       

        
        labelNilai11baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        labelNilai11baru.setText("Nilai :");
        labelNilai11baru.setName("labelNilai11baru");
        FormInput.add(labelNilai11baru);
      

       
        NilaiKriteria11baru.setName("NilaiKriteria11baru");
        NilaiKriteria11baru.setFont(new java.awt.Font("Tahoma", 0, 11));
        NilaiKriteria11baru.setText("0");
        NilaiKriteria11baru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NilaiKriteria11baru.setEditable(false);
        NilaiKriteria11baru.setFocusTraversalPolicyProvider(true);
        FormInput.add(NilaiKriteria11baru);
     
    
    

        TingkatSkor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TingkatSkor.setText("Pasien Bisa Dipindahkan Ke Ruang Perawatan Bila Skor Minimal 8");
        TingkatSkor.setToolTipText("");
        TingkatSkor.setName("TingkatSkor"); 
        FormInput.add(TingkatSkor);
       

        jLabel235.setText("Total :");
        jLabel235.setName("jLabel235"); 
        FormInput.add(jLabel235);
       

        NilaiKriteriaTotal.setEditable(false);
        NilaiKriteriaTotal.setFocusTraversalPolicyProvider(true);
        NilaiKriteriaTotal.setName("NilaiKriteriaTotal"); 
        FormInput.add(NilaiKriteriaTotal);
        y += rowGap;

        label11baru.setBounds(34, y, 130, 23);
        labelSkala11baru.setBounds(165, y, 40, 23);
        SkalaKriteria11baru.setBounds(210, y, 460, 23);
        labelNilai11baru.setBounds(690, y, 50, 23);
        NilaiKriteria11baru.setBounds(729, y, 60, 23);

         y += rowGap;  
        TingkatSkor.setBounds(34, y, 640, 23);
        jLabel235.setBounds(675, y, 50, 23);
        NilaiKriteriaTotal.setBounds(729, y, 60, 23);

       
       
       


        FormInput.setPreferredSize(new Dimension(750, 1100));

      hideBaris1sd5();
    //      rapikanFormDiBawahKriteria();
            FormInput.revalidate();
            FormInput.repaint();

            tabMode = new DefaultTableModel(null, new Object[]{
        "No.Rawat","No.R.M.","Nama Pasien","Tgl.Lahir","JK","Tanggal",

        "1a. Kesadaran","Skor",
        "1b. Menjawab","Skor",
        "1c. Perintah","Skor",
        "2. Gaze","Skor",
        "3. Visual","Skor",
        "4. Wajah","Skor",
        "5. Lengan","Skor",
        "6. Tungkai","Skor",
        "7. Ataksia","Skor",
        "8. Sensorik","Skor",
        "9. Bahasa","Skor",
        "10. Disartria","Skor",
        "11. Inatensi","Skor",

        "Ket. Lengan",
        "Ket. Tungkai",
        "Ket. Disartria",
        "Total",

//        "Keluar","Instruksi",
        "Kode Dokter","Nama Dokter"
    }){
        @Override public boolean isCellEditable(int r,int c){ return false; }
    };
    tbObat.setModel(tabMode);
    tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


        for (int i = 0; i < tbObat.getColumnCount(); i++) {
            TableColumn c = tbObat.getColumnModel().getColumn(i);

            if(i==0) c.setPreferredWidth(105);          // no rawat
            else if(i==1) c.setPreferredWidth(80);      // rm
            else if(i==2) c.setPreferredWidth(160);     // nama
            else if(i==3) c.setPreferredWidth(80);      // tgl lahir
            else if(i==4) c.setPreferredWidth(30);      // jk
            else if(i==5) c.setPreferredWidth(120);     // tanggal

            else if(i>=6 && i<=29){
                if(i % 2 == 0){
                    c.setPreferredWidth(160); // kolom SKALA
                }else{
                    c.setPreferredWidth(45);  // kolom SKOR
                }
            }
            else if(i==30 || i==31) c.setPreferredWidth(150); // keterangan
            else if(i==32) c.setPreferredWidth(45);           // total
          
            else if(i==33) c.setPreferredWidth(90);           // kd dokter
            else if(i==34) c.setPreferredWidth(150);          // nm dokter
//            else if(i==35) c.setPreferredWidth(90);           // nip
//            else if(i==36) c.setPreferredWidth(150);          // petugas
        }

        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        KdDokter.setDocument(new batasInput((byte)20).getKata(KdDokter));
        NIP.setDocument(new batasInput((byte)20).getKata(NIP));
      
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
        jLabel224 = new widget.Label();
        NilaKriteria1 = new widget.TextBox();
        jLabel222 = new widget.Label();
        jLabel230 = new widget.Label();
        NilaKriteria4 = new widget.TextBox();
        jLabel227 = new widget.Label();
        jLabel223 = new widget.Label();
        SkalaKriteria1 = new widget.ComboBox();
        jLabel219 = new widget.Label();
        jLabel229 = new widget.Label();
        SkalaKriteria4 = new widget.ComboBox();
        jLabel231 = new widget.Label();
        SkalaKriteria2 = new widget.ComboBox();
        jLabel225 = new widget.Label();
        jLabel226 = new widget.Label();
        SkalaKriteria5 = new widget.ComboBox();
        jLabel217 = new widget.Label();
        SkalaKriteria3 = new widget.ComboBox();
        jLabel221 = new widget.Label();
        jLabel218 = new widget.Label();
        jLabel220 = new widget.Label();
        NilaKriteria3 = new widget.TextBox();
        NilaKriteria5 = new widget.TextBox();
        jLabel228 = new widget.Label();
        NilaKriteria2 = new widget.TextBox();
        btnPetugas = new widget.Button();
        NamaPetugas = new widget.TextBox();
        NIP = new widget.TextBox();
        jLabel18 = new widget.Label();
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
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        jLabel57 = new widget.Label();
        TingkatSkor = new widget.Label();
        jLabel235 = new widget.Label();
        NilaiKriteriaTotal = new widget.TextBox();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel232 = new widget.Label();
        jLabel233 = new widget.Label();
        SkalaKriteria6 = new widget.ComboBox();
        jLabel234 = new widget.Label();
        NilaKriteria6 = new widget.TextBox();
        NmDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        KdDokter = new widget.TextBox();
        label14 = new widget.Label();

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

        jLabel224.setText("Skala :");
        jLabel224.setName("jLabel224"); // NOI18N
        jLabel224.getAccessibleContext().setAccessibleName("Skor :");

        NilaKriteria1.setEditable(false);
        NilaKriteria1.setFocusTraversalPolicyProvider(true);
        NilaKriteria1.setName("NilaKriteria1"); // NOI18N

        jLabel222.setText("Nilai :");
        jLabel222.setName("jLabel222"); // NOI18N

        jLabel230.setText("Skala :");
        jLabel230.setName("jLabel230"); // NOI18N
        jLabel230.getAccessibleContext().setAccessibleName("Skor :");

        NilaKriteria4.setEditable(false);
        NilaKriteria4.setFocusTraversalPolicyProvider(true);
        NilaKriteria4.setName("NilaKriteria4"); // NOI18N

        jLabel227.setText("Skala :");
        jLabel227.setName("jLabel227"); // NOI18N
        jLabel227.getAccessibleContext().setAccessibleName("Skor :");

        jLabel223.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel223.setText("3. Tekanan Darah");
        jLabel223.setName("jLabel223"); // NOI18N

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

        jLabel219.setText("Skala :");
        jLabel219.setName("jLabel219"); // NOI18N

        jLabel229.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel229.setText("5. Warna Kulit");
        jLabel229.setName("jLabel229"); // NOI18N

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

        jLabel231.setText("Nilai :");
        jLabel231.setName("jLabel231"); // NOI18N

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

        jLabel225.setText("Nilai :");
        jLabel225.setName("jLabel225"); // NOI18N

        jLabel226.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel226.setText("4. Kesadaran");
        jLabel226.setName("jLabel226"); // NOI18N

        SkalaKriteria5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cianosis", "Pucat", "Kemerahan / Normal" }));
        SkalaKriteria5.setName("SkalaKriteria5"); // NOI18N
        SkalaKriteria5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaKriteria5ItemStateChanged(evt);
            }
        });
        SkalaKriteria5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaKriteria5KeyPressed(evt);
            }
        });

        jLabel217.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel217.setText("1. Aktivitas");
        jLabel217.setName("jLabel217"); // NOI18N

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

        jLabel221.setText("Skala :");
        jLabel221.setName("jLabel221"); // NOI18N
        jLabel221.getAccessibleContext().setAccessibleName("Skor :");

        jLabel218.setText("Nilai :");
        jLabel218.setName("jLabel218"); // NOI18N

        jLabel220.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel220.setText("2. Respirasi");
        jLabel220.setName("jLabel220"); // NOI18N

        NilaKriteria3.setEditable(false);
        NilaKriteria3.setFocusTraversalPolicyProvider(true);
        NilaKriteria3.setName("NilaKriteria3"); // NOI18N

        NilaKriteria5.setEditable(false);
        NilaKriteria5.setFocusTraversalPolicyProvider(true);
        NilaKriteria5.setName("NilaKriteria5"); // NOI18N

        jLabel228.setText("Nilai :");
        jLabel228.setName("jLabel228"); // NOI18N

        NilaKriteria2.setEditable(false);
        NilaKriteria2.setFocusTraversalPolicyProvider(true);
        NilaKriteria2.setName("NilaKriteria2"); // NOI18N

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

        NamaPetugas.setEditable(false);
        NamaPetugas.setName("NamaPetugas"); // NOI18N

        NIP.setEditable(false);
        NIP.setHighlighter(null);
        NIP.setName("NIP"); // NOI18N

        jLabel18.setText("Petugas :");
        jLabel18.setName("jLabel18"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Skor NIHSS (National Institutes of Health Stroke Scale) ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-01-2026" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-01-2026" }));
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
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-01-2026" }));
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

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 100, 810, 1);

        jLabel232.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel232.setText("1a. Tingkat Kesadaran");
        jLabel232.setName("jLabel232"); // NOI18N
        FormInput.add(jLabel232);
        jLabel232.setBounds(34, 130, 140, 23);

        jLabel233.setText("Skala :");
        jLabel233.setName("jLabel233"); // NOI18N
        FormInput.add(jLabel233);
        jLabel233.setBounds(165, 130, 40, 23);

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
        FormInput.add(SkalaKriteria6);
        SkalaKriteria6.setBounds(210, 130, 460, 23);

        jLabel234.setText("Nilai :");
        jLabel234.setName("jLabel234"); // NOI18N
        FormInput.add(jLabel234);
        jLabel234.setBounds(670, 130, 50, 23);

        NilaKriteria6.setEditable(false);
        NilaKriteria6.setFocusTraversalPolicyProvider(true);
        NilaKriteria6.setName("NilaKriteria6"); // NOI18N
        FormInput.add(NilaKriteria6);
        NilaKriteria6.setBounds(730, 130, 60, 23);

        NmDokter.setEditable(false);
        NmDokter.setName("NmDokter"); // NOI18N
        NmDokter.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmDokter);
        NmDokter.setBounds(150, 40, 207, 23);

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
        BtnDokter.setBounds(360, 40, 28, 23);

        KdDokter.setEditable(false);
        KdDokter.setName("KdDokter"); // NOI18N
        KdDokter.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(KdDokter);
        KdDokter.setBounds(70, 40, 80, 23);

        label14.setText("Dokter :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(10, 40, 60, 23);

        scrollInput.setViewportView(FormInput);

        PanelInput.add(scrollInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);
        internalFrame1.getAccessibleContext().setAccessibleName("Skor NHSS");

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
                if(KdDokter.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString())){
                    if(Sequel.cekTanggal48jam(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString(),Sequel.ambiltanggalsekarang())==true){
                        hapus();
                    }
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
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"pasien");
        }else if(NmDokter.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Dokter");
//        }else if(NIP.getText().trim().equals("")||NamaPetugas.getText().trim().equals("")){
//            Valid.textKosong(NIP,"Petugas");
        }else{
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode().equals("Admin Utama")){
                    ganti();
                }else{
                    if(KdDokter.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString())){
                        if(Sequel.cekTanggal48jam(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString(),Sequel.ambiltanggalsekarang())==true){
                            if(TanggalRegistrasi.getText().equals("")){
                                TanggalRegistrasi.setText(Sequel.cariIsi("select concat(reg_periksa.tgl_registrasi,' ',reg_periksa.jam_reg) from reg_periksa where reg_periksa.no_rawat=?",TNoRw.getText()));
                            }
                            if(Sequel.cekTanggalRegistrasi(TanggalRegistrasi.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem())==true){
                                ganti();
                            }
                        }
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
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,skor_nhss.tanggal,"+
                  
                    "skor_nhss.penilaian_totalnilai,skor_nhss.kd_dokter,dokter.nm_dokter "+
                    "from skor_nhss inner join reg_periksa on skor_nhss.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join dokter on skor_nhss.kd_dokter=dokter.kd_dokter "+
//                    "inner join petugas on skor_nhss.nip=petugas.nip where "+
                    "skor_nhss.tanggal between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59' "+
                    "order by skor_nhss.tanggal",param);
            }else{
                Valid.MyReportqry("rptSkorMonitoringAldrette.jasper","report","::[ Data Skor Monitoring Aldrette Pasca Anestesi ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,skor_nhss.tanggal,"+
                    "skor_nhss.penilaian_skala1,skor_nhss.penilaian_nilai1,"+
                    "skor_nhss.penilaian_skala2,skor_nhss.penilaian_nilai2,"+
                    "skor_nhss.penilaian_skala3,skor_nhss.penilaian_nilai3,"+
                    "skor_nhss.penilaian_skala4,skor_nhss.penilaian_nilai4,"+
                    "skor_nhss.penilaian_skala5,skor_nhss.penilaian_nilai5,"+
                    "skor_nhss.penilaian_totalnilai,"+
                    "skor_nhss.kd_dokter,dokter.nm_dokter "+
                    "from skor_nhss inner join reg_periksa on skor_nhss.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join dokter on skor_nhss.kd_dokter=dokter.kd_dokter "+
//                    "inner join petugas on skor_nhss.nip=petugas.nip where "+
                    "skor_nhss.tanggal between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59' and "+
                    "(reg_periksa.no_rawat like '%"+TCari.getText().trim()+"%' or pasien.no_rkm_medis like '%"+TCari.getText().trim()+"%' or pasien.nm_pasien like '%"+TCari.getText().trim()+"%' "+
                    "or skor_nhss.kd_dokter like '%"+TCari.getText().trim()+"%' or dokter.nm_dokter like '%"+TCari.getText().trim()+"%') "+
                    "order by skor_nhss.tanggal ",param);
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
            Valid.MyReportqry("rptMonitoringSkorAldrette.jasper","report","::[ Skor NHSS ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,skor_nhss.tanggal,"+
                    "skor_nhss.penilaian_skala1,skor_nhss.penilaian_nilai1,"+
                    "skor_nhss.penilaian_skala2,skor_nhss.penilaian_nilai2,"+
                    "skor_nhss.penilaian_skala3,skor_nhss.penilaian_nilai3,"+
                    "skor_nhss.penilaian_skala4,skor_nhss.penilaian_nilai4,"+
                    "skor_nhss.penilaian_skala5,skor_nhss.penilaian_nilai5,"+
                    "skor_nhss.penilaian_totalnilai,"+
                    "skor_nhss.kd_dokter,dokter.nm_dokter "+
                    "from skor_nhss inner join reg_periksa on skor_nhss.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join dokter on skor_nhss.kd_dokter=dokter.kd_dokter "+
                    "where reg_periksa.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
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
            Valid.MyReportqry("rptFormulirMonitoringSkorAldrette.jasper","report","::[ Skor NHSS ]::",
                "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,skor_nhss.tanggal,"+
                    "skor_nhss.penilaian_skala1,skor_nhss.penilaian_nilai1,"+
                    "skor_nhss.penilaian_skala2,skor_nhss.penilaian_nilai2,"+
                    "skor_nhss.penilaian_skala3,skor_nhss.penilaian_nilai3,"+
                    "skor_nhss.penilaian_skala4,skor_nhss.penilaian_nilai4,"+
                    "skor_nhss.penilaian_skala5,skor_nhss.penilaian_nilai5,"+
                    "skor_nhss.penilaian_totalnilai,"+
                    "skor_nhss.kd_dokter,dokter.nm_dokter "+
                    "from skor_nhss inner join reg_periksa on skor_nhss.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join dokter on skor_nhss.kd_dokter=dokter.kd_dokter "+
                    "where reg_periksa.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
        }
    }//GEN-LAST:event_MnMonitoringSkorAldrette2ActionPerformed

    private void SkalaKriteria6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaKriteria6ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKriteria6ItemStateChanged

    private void SkalaKriteria6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaKriteria6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SkalaKriteria6KeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMSkornhss dialog = new RMSkornhss(new javax.swing.JFrame(), true);
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
    private widget.TextBox JK;
    private widget.ComboBox Jam;
    private widget.TextBox KdDokter;
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
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables
    
    public void tampil() {
    Valid.tabelKosong(tabMode);
    try {
        if (TCari.getText().trim().equals("")) {
            ps = koneksi.prepareStatement(
                "SELECT " +
                "  rp.no_rawat, " +
                "  p.no_rkm_medis, " +
                "  p.nm_pasien, " +
                "  p.tgl_lahir, " +
                "  p.jk, " +
                "  s.tanggal, " +

                "  s.skala_1a, s.skor_1a, " +
                "  s.skala_1b, s.skor_1b, " +
                "  s.skala_1c, s.skor_1c, " +
                "  s.skala_2,  s.skor_2, " +
                "  s.skala_3,  s.skor_3, " +
                "  s.skala_4,  s.skor_4, " +
                "  s.skala_5,  s.skor_5, " +
                "  s.skala_6,  s.skor_6, " +
                "  s.skala_7,  s.skor_7, " +
                "  s.skala_8,  s.skor_8, " +
                "  s.skala_9,  s.skor_9, " +
                "  s.skala_10, s.skor_10, " +
                "  s.skala_11, s.skor_11, " +

                "  s.keterangan5, " +
                "  s.keterangan6, " +
                "  s.keterangan10, " +
                "  s.penilaian_totalnilai, " +

                "  s.kd_dokter, " +
                "  d.nm_dokter " +
//                "  s.nip, " +
//                "  pg.nama " +

                "FROM skor_nhss s " +
                "INNER JOIN reg_periksa rp ON s.no_rawat = rp.no_rawat " +
                "INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis " +
                "INNER JOIN dokter d ON s.kd_dokter = d.kd_dokter " +
//                "INNER JOIN petugas pg ON s.nip = pg.nip " +
                "WHERE s.tanggal BETWEEN ? AND ? " +
                "ORDER BY s.tanggal"
            );
        } else {
            ps = koneksi.prepareStatement(
                "SELECT " +
                "  rp.no_rawat, " +
                "  p.no_rkm_medis, " +
                "  p.nm_pasien, " +
                "  p.tgl_lahir, " +
                "  p.jk, " +
                "  s.tanggal, " +

                "  s.skala_1a, s.skor_1a, " +
                "  s.skala_1b, s.skor_1b, " +
                "  s.skala_1c, s.skor_1c, " +
                "  s.skala_2,  s.skor_2, " +
                "  s.skala_3,  s.skor_3, " +
                "  s.skala_4,  s.skor_4, " +
                "  s.skala_5,  s.skor_5, " +
                "  s.skala_6,  s.skor_6, " +
                "  s.skala_7,  s.skor_7, " +
                "  s.skala_8,  s.skor_8, " +
                "  s.skala_9,  s.skor_9, " +
                "  s.skala_10, s.skor_10, " +
                "  s.skala_11, s.skor_11, " +

                "  s.keterangan5, " +
                "  s.keterangan6, " +
                "  s.keterangan10, " +
                "  s.penilaian_totalnilai, " +

                "  s.kd_dokter, " +
                "  d.nm_dokter " +
//                "  s.nip, " +
//                "  pg.nama " +

                "FROM skor_nhss s " +
                "INNER JOIN reg_periksa rp ON s.no_rawat = rp.no_rawat " +
                "INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis " +
                "INNER JOIN dokter d ON s.kd_dokter = d.kd_dokter " +
//                "INNER JOIN petugas pg ON s.nip = pg.nip " +
                "WHERE s.tanggal BETWEEN ? AND ? " +
                "AND ( " +
                "  rp.no_rawat LIKE ? OR " +
                "  p.no_rkm_medis LIKE ? OR " +
                "  p.nm_pasien LIKE ? OR " +
                "  s.kd_dokter LIKE ? OR " +
                "  d.nm_dokter LIKE ? " +
//                "  s.nip LIKE ? OR " +
//                "  pg.nama LIKE ? " +
                ") " +
                "ORDER BY s.tanggal"
            );
        }

        // ====== SET PARAMETER ======
        ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00");
        ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59");

        if (!TCari.getText().trim().equals("")) {
            int jmlParam = ps.getParameterMetaData().getParameterCount();
            for (int i = 3; i <= jmlParam; i++) {
                ps.setString(i, "%" + TCari.getText() + "%");
            }
        }

        rs = ps.executeQuery();
        while (rs.next()) {
            tabMode.addRow(new Object[]{
                rs.getString("no_rawat"),
                rs.getString("no_rkm_medis"),
                rs.getString("nm_pasien"),
                rs.getDate("tgl_lahir"),
                rs.getString("jk"),
                rs.getString("tanggal"),

                rs.getString("skala_1a"), rs.getString("skor_1a"),
                rs.getString("skala_1b"), rs.getString("skor_1b"),
                rs.getString("skala_1c"), rs.getString("skor_1c"),
                rs.getString("skala_2"),  rs.getString("skor_2"),
                rs.getString("skala_3"),  rs.getString("skor_3"),
                rs.getString("skala_4"),  rs.getString("skor_4"),
                rs.getString("skala_5"),  rs.getString("skor_5"),
                rs.getString("skala_6"),  rs.getString("skor_6"),
                rs.getString("skala_7"),  rs.getString("skor_7"),
                rs.getString("skala_8"),  rs.getString("skor_8"),
                rs.getString("skala_9"),  rs.getString("skor_9"),
                rs.getString("skala_10"), rs.getString("skor_10"),
                rs.getString("skala_11"), rs.getString("skor_11"),

                rs.getString("keterangan5"),
                rs.getString("keterangan6"),
                rs.getString("keterangan10"),
                rs.getString("penilaian_totalnilai"),

                rs.getString("kd_dokter"),
                rs.getString("nm_dokter")
//                rs.getString("nip"),
//                rs.getString("nama")
            });
        }
    } catch (Exception e) {
        System.out.println("Notif tampil NHSS : " + e);
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        } catch (Exception e) {
            System.out.println("Notif close : " + e);
        }
    }
    LCount.setText("" + tabMode.getRowCount());
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

        TingkatSkor.setText("Pasien Tidak Dapat Dipindahkan Ke Ruangan Perawatan, Karena Kondisi Yang Lemah");
        SkalaKriteria1.requestFocus();
    } 

   private void getData() {
       if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()); 
        if (TNoRw.getText().trim().equals("")) return;
   
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
           ps = koneksi.prepareStatement(
            "SELECT "+
            "sa.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.jk, p.tgl_lahir, sa.tanggal, "+
            "sa.kd_dokter, d.nm_dokter, "+

            // ===== NIHSS =====
            "sa.skala_1a, sa.skor_1a, "+
            "sa.skala_1b, sa.skor_1b, "+
            "sa.skala_1c, sa.skor_1c, "+
            "sa.skala_2,  sa.skor_2, "+
            "sa.skala_3,  sa.skor_3, "+
            "sa.skala_4,  sa.skor_4, "+
            "sa.skala_5, sa.skor_5, sa.skala_5_posisi, "+
            "sa.skala_6, sa.skor_6, sa.skala_6_posisi, "+
            "sa.skala_7,  sa.skor_7, "+
            "sa.skala_8,  sa.skor_8, "+
            "sa.skala_9,  sa.skor_9, "+
            "sa.skala_10, sa.skor_10, "+
            "sa.skala_11, sa.skor_11, "+

            // ===== KETERANGAN =====
            "sa.keterangan5, sa.keterangan6, sa.keterangan10, sa.penilaian_totalnilai, sa.keterangan "+

            "FROM skor_nhss sa "+
            "INNER JOIN reg_periksa rp ON sa.no_rawat = rp.no_rawat "+
            "INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "+
            "INNER JOIN dokter d ON sa.kd_dokter = d.kd_dokter "+
//            "INNER JOIN petugas pt ON sa.nip = pt.nip "+
            "WHERE sa.no_rawat = ?"
        );

            ps.setString(1, TNoRw.getText());
            rs = ps.executeQuery();
         
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

                    // ===== DOKTER =====
                    KdDokter.setText(rs.getString("kd_dokter"));
                    NmDokter.setText(rs.getString("nm_dokter"));
//                    NIP.setText(rs.getString("nip"));
//                    NamaPetugas.setText(rs.getString("nama"));

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
                      LokasiLengan.setSelectedItem(rs.getString("skala_5_posisi"));

                    SkalaKriteria6baru.setSelectedItem(rs.getString("skala_6"));
                    NilaiKriteria6baru.setText(rs.getString("skor_6"));
                      LokasiTungkai.setSelectedItem(rs.getString("skala_6_posisi"));

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
                    txtJelaskan5.setText(rs.getString("keterangan5"));
                    txtJelaskan6.setText(rs.getString("keterangan6"));
                    txtJelaskan10.setText(rs.getString("keterangan10"));
                    NilaiKriteriaTotal.setText(rs.getString("penilaian_totalnilai"));
                    TingkatSkor.setText(rs.getString("keterangan"));

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
        BtnSimpan.setEnabled(true);
        BtnHapus.setEnabled(true);
        BtnEdit.setEnabled(true);
        BtnPrint.setEnabled(true); 
        if(akses.getjml2()>=1){
            KdDokter.setEditable(false);
            BtnDokter.setEnabled(false);
            KdDokter.setText(akses.getkode());
            NmDokter.setText(dokter.tampil3(KdDokter.getText()));
            if(NmDokter.getText().equals("")){
                KdDokter.setText("");
                JOptionPane.showMessageDialog(null,"User login bukan dokter...!!");
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
System.out.println(TNoRw.getText());
    String ket5 = SkalaKriteria5baru.getSelectedItem().toString().startsWith("UN")
            ? txtJelaskan5.getText()
            : "";

    String ket6 = SkalaKriteria6baru.getSelectedItem().toString().startsWith("UN")
            ? txtJelaskan6.getText()
            : "";

        if (Sequel.mengedittf(
        "skor_nhss",
        "no_rawat=? AND tanggal=?",
        "kd_dokter=?,nip=?," +

        "skala_1a=?,skor_1a=?," +
        "skala_1b=?,skor_1b=?," +
        "skala_1c=?,skor_1c=?," +
        "skala_2=?,skor_2=?," +
        "skala_3=?,skor_3=?," +
        "skala_4=?,skor_4=?," +
        "skala_5=?,skor_5=?," +
        "skala_6=?,skor_6=?," +
        "skala_7=?,skor_7=?," +
        "skala_8=?,skor_8=?," +
        "skala_9=?,skor_9=?," +
        "skala_10=?,skor_10=?," +
        "skala_11=?,skor_11=?," +
        "keterangan5=?,keterangan6=?,penilaian_totalnilai=?,keterangan=?",
        34, 
        new String[]{

            "-",
            NIP.getText(),

            SkalaKriteria6.getSelectedItem().toString(),   NilaKriteria6.getText(),
            SkalaKriteria1b.getSelectedItem().toString(), NilaiKriteria1b.getText(),
            SkalaKriteria1c.getSelectedItem().toString(), NilaiKriteria1c.getText(),
            SkalaKriteria2baru.getSelectedItem().toString(), NilaiKriteria2baru.getText(),
            SkalaKriteria3baru.getSelectedItem().toString(), NilaiKriteria3baru.getText(),
            SkalaKriteria4baru.getSelectedItem().toString(), NilaiKriteria4baru.getText(),
            SkalaKriteria5baru.getSelectedItem().toString(), NilaiKriteria5baru.getText(),
            SkalaKriteria6baru.getSelectedItem().toString(), NilaiKriteria6baru.getText(),
            SkalaKriteria7baru.getSelectedItem().toString(), NilaiKriteria7baru.getText(),
            SkalaKriteria8baru.getSelectedItem().toString(), NilaiKriteria8baru.getText(),
            SkalaKriteria9baru.getSelectedItem().toString(), NilaiKriteria9baru.getText(),
            SkalaKriteria10baru.getSelectedItem().toString(), NilaiKriteria10baru.getText(),
            SkalaKriteria11baru.getSelectedItem().toString(), NilaiKriteria11baru.getText(),

            ket5,
            ket6,
            NilaiKriteriaTotal.getText(),
            TingkatSkor.getText(),
              TNoRw.getText(),
            Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+
                Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem()
        }
    )) {
        emptTeks();
    }

}



    private void hapus() {
        if(Sequel.queryu2tf("delete from skor_nhss where tanggal=? and no_rawat=?",2,new String[]{
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
            total += parseNilai(NilaKriteria6);        // 1a
            total += parseNilai(NilaiKriteria1b);      // 1b
            total += parseNilai(NilaiKriteria1c);      // 1c
            total += parseNilai(NilaiKriteria2baru);   // 2
            total += parseNilai(NilaiKriteria3baru);   // 3
            total += parseNilai(NilaiKriteria4baru);   // 4
            total += parseNilai(NilaiKriteria5baru);   // 5
            total += parseNilai(NilaiKriteria6baru);   // 6
            total += parseNilai(NilaiKriteria7baru);   // 7
            total += parseNilai(NilaiKriteria8baru);   // 8
            total += parseNilai(NilaiKriteria9baru);   // 9
            total += parseNilai(NilaiKriteria10baru);  // 10
            total += parseNilai(NilaiKriteria11baru);  // 11

            NilaiKriteriaTotal.setText(String.valueOf(total));

           
            if (total <= 5) {
                TingkatSkor.setText("Defisit neurologis ringan");
            } else if (total >= 6 && total <= 14) {
                TingkatSkor.setText("Defisit neurologis sedang");
            } else if (total >= 15 && total <= 24) {
                TingkatSkor.setText("Defisit neurologis berat");
            } else {
                TingkatSkor.setText("Defisit neurologis sangat berat");
            }

        } catch (Exception e) {
            NilaiKriteriaTotal.setText("0");
            TingkatSkor.setText("Defisit neurologis ringan");
        }
    }

private void simpan() {

    String ket5 = SkalaKriteria5baru.getSelectedItem().toString().startsWith("UN")
            ? safeStr(txtJelaskan5.getText())
            : "";

    String ket6 = SkalaKriteria6baru.getSelectedItem().toString().startsWith("UN")
            ? safeStr(txtJelaskan6.getText())
            : "";

    String ket10 = SkalaKriteria10baru.getSelectedItem().toString().startsWith("UN")
            ? safeStr(txtJelaskan10.getText())
            : "";

    if (Sequel.menyimpantf(
        "skor_nhss",
        "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?",
        "Data NIHSS",
        37,
        new String[]{

            // ===== HEADER =====
            TNoRw.getText(),
            Valid.SetTgl(Tanggal.getSelectedItem()+"") + " " +
                Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem(),
            KdDokter.getText(),
            NIP.getText(),

            // ===== NIHSS =====
            SkalaKriteria6.getSelectedItem().toString(),     // skala_1a
            safeInt(NilaKriteria6.getText()),               // skor_1a

            SkalaKriteria1b.getSelectedItem().toString(),  // skala_1b
            safeInt(NilaiKriteria1b.getText()),            // skor_1b

            SkalaKriteria1c.getSelectedItem().toString(),  // skala_1c
            safeInt(NilaiKriteria1c.getText()),            // skor_1c

            SkalaKriteria2baru.getSelectedItem().toString(), // skala_2
            safeInt(NilaiKriteria2baru.getText()),           // skor_2

            SkalaKriteria3baru.getSelectedItem().toString(), // skala_3
            safeInt(NilaiKriteria3baru.getText()),           // skor_3

            SkalaKriteria4baru.getSelectedItem().toString(), // skala_4
            safeInt(NilaiKriteria4baru.getText()),           // skor_4

            SkalaKriteria5baru.getSelectedItem().toString(), // skala_5
            safeInt(NilaiKriteria5baru.getText()),           // skor_5

            LokasiLengan.getSelectedItem().toString(),       // skala_5_posisi

            SkalaKriteria6baru.getSelectedItem().toString(), // skala_6
            safeInt(NilaiKriteria6baru.getText()),           // skor_6

            LokasiTungkai.getSelectedItem().toString(),      // skala_6_posisi

            SkalaKriteria7baru.getSelectedItem().toString(), // skala_7
            safeInt(NilaiKriteria7baru.getText()),           // skor_7

            SkalaKriteria8baru.getSelectedItem().toString(), // skala_8
            safeInt(NilaiKriteria8baru.getText()),           // skor_8

            SkalaKriteria9baru.getSelectedItem().toString(), // skala_9
            safeInt(NilaiKriteria9baru.getText()),           // skor_9

            SkalaKriteria10baru.getSelectedItem().toString(), // skala_10
            safeInt(NilaiKriteria10baru.getText()),           // skor_10

            SkalaKriteria11baru.getSelectedItem().toString(), // skala_11
            safeInt(NilaiKriteria11baru.getText()),           // skor_11

            // ===== KETERANGAN =====
            ket5,
            ket6,
            safeInt(NilaiKriteriaTotal.getText()),
            TingkatSkor.getText(),
            ket10
        }
    )) {
       JOptionPane.showMessageDialog(null,
        "Data NIHSS berhasil disimpan ✔",
        "Sukses",
        JOptionPane.INFORMATION_MESSAGE
    );

    emptTeks();
    }
}

private String safeInt(String val) {
    if (val == null || val.trim().equals("") || val.trim().equals("-")) {
        return "0";
    }
    return val;
}

private String safeStr(String val) {
    if (val == null || val.trim().equals("-")) {
        return "";
    }
    return val;
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
private void hideBaris1sd5() {
    // BARIS 1
    jLabel217.setVisible(false);
    jLabel219.setVisible(false);
    SkalaKriteria1.setVisible(false);
    jLabel218.setVisible(false);
    NilaKriteria1.setVisible(false);

    // BARIS 2
    jLabel220.setVisible(false);
    jLabel221.setVisible(false);
    SkalaKriteria2.setVisible(false);
    jLabel222.setVisible(false);
    NilaKriteria2.setVisible(false);

    // BARIS 3
    jLabel223.setVisible(false);
    jLabel224.setVisible(false);
    SkalaKriteria3.setVisible(false);
    jLabel225.setVisible(false);
    NilaKriteria3.setVisible(false);

    // BARIS 4
    jLabel226.setVisible(false);
    jLabel227.setVisible(false);
    SkalaKriteria4.setVisible(false);
    jLabel228.setVisible(false);
    NilaKriteria4.setVisible(false);

    // BARIS 5
    jLabel229.setVisible(false);
    jLabel230.setVisible(false);
    SkalaKriteria5.setVisible(false);
     jLabel231.setVisible(false);
    NilaKriteria5.setVisible(false);
//        jLabel233.setVisible(false);
//    NilaKriteria6.setVisible(false);
}

private void geserKeAtas(java.awt.Component c, int offset) {
    if (c != null) {
        c.setBounds(
            c.getX(),
            c.getY() - offset,
            c.getWidth(),
            c.getHeight()
        );
    }
}
private void rapikanFormDiBawahKriteria() {
    int offset = 180; // 🔧 kalau masih terlalu turun, naikkan ke 200

    // ===== 1a =====
    geserKeAtas(jLabel232, offset);
    geserKeAtas(labelSkalaBaru, offset);
    geserKeAtas(SkalaKriteria6, offset);
    geserKeAtas(jLabel233, offset);
    geserKeAtas(NilaKriteria6, offset);

    // ===== 1b =====
    geserKeAtas(label1b, offset);
    geserKeAtas(labelSkala1b, offset);
    geserKeAtas(SkalaKriteria1b, offset);
    geserKeAtas(labelNilai1b, offset);
    geserKeAtas(NilaiKriteria1b, offset);

    // ===== 1c =====
    geserKeAtas(label1c, offset);
    geserKeAtas(labelSkala1c, offset);
    geserKeAtas(SkalaKriteria1c, offset);
    geserKeAtas(labelNilai1c, offset);
    geserKeAtas(NilaiKriteria1c, offset);

    // ===== 2 =====
    geserKeAtas(label2baru, offset);
    geserKeAtas(labelSkala2baru, offset);
    geserKeAtas(SkalaKriteria2baru, offset);
    geserKeAtas(labelNilai2baru, offset);
    geserKeAtas(NilaiKriteria2baru, offset);

    // ===== 3 =====
    geserKeAtas(label3baru, offset);
    geserKeAtas(labelSkala3baru, offset);
    geserKeAtas(SkalaKriteria3baru, offset);
    geserKeAtas(labelNilai3baru, offset);
    geserKeAtas(NilaiKriteria3baru, offset);

    // ===== 4 =====
    geserKeAtas(label4baru, offset);
    geserKeAtas(labelSkala4baru, offset);
    geserKeAtas(SkalaKriteria4baru, offset);
    geserKeAtas(labelNilai4baru, offset);
    geserKeAtas(NilaiKriteria4baru, offset);

    // ===== 5 =====
    geserKeAtas(label5baru, offset);
    geserKeAtas(labelSkala5baru, offset);
    geserKeAtas(SkalaKriteria5baru, offset);
    geserKeAtas(labelNilai5baru, offset);
    geserKeAtas(NilaiKriteria5baru, offset);
    geserKeAtas(labelJelaskan5, offset);
    geserKeAtas(txtJelaskan5, offset);

    // ===== 6 =====
    geserKeAtas(label6baru, offset);
    geserKeAtas(labelSkala6baru, offset);
    geserKeAtas(SkalaKriteria6baru, offset);
    geserKeAtas(labelNilai6baru, offset);
    geserKeAtas(NilaiKriteria6baru, offset);
    geserKeAtas(labelJelaskan6, offset);
    geserKeAtas(txtJelaskan6, offset);

    // ===== 7 =====
    geserKeAtas(label7baru, offset);
    geserKeAtas(labelSkala7baru, offset);
    geserKeAtas(SkalaKriteria7baru, offset);
    geserKeAtas(labelNilai7baru, offset);
    geserKeAtas(NilaiKriteria7baru, offset);

    // ===== 8 =====
    geserKeAtas(label8baru, offset);
    geserKeAtas(labelSkala8baru, offset);
    geserKeAtas(SkalaKriteria8baru, offset);
    geserKeAtas(labelNilai8baru, offset);
    geserKeAtas(NilaiKriteria8baru, offset);

    // ===== 9 =====
    geserKeAtas(label9baru, offset);
    geserKeAtas(labelSkala9baru, offset);
    geserKeAtas(SkalaKriteria9baru, offset);
    geserKeAtas(labelNilai9baru, offset);
    geserKeAtas(NilaiKriteria9baru, offset);

    // ===== 10 =====
    geserKeAtas(label10baru, offset);
    geserKeAtas(labelSkala10baru, offset);
    geserKeAtas(SkalaKriteria10baru, offset);
    geserKeAtas(labelNilai10baru, offset);
    geserKeAtas(NilaiKriteria10baru, offset);
    geserKeAtas(labelJelaskan10, offset);
    geserKeAtas(txtJelaskan10, offset);

    // ===== 11 =====
    geserKeAtas(label11baru, offset);
    geserKeAtas(labelSkala11baru, offset);
    geserKeAtas(SkalaKriteria11baru, offset);
    geserKeAtas(labelNilai11baru, offset);
    geserKeAtas(NilaiKriteria11baru, offset);
}


}
