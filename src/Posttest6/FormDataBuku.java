package Posttest6;
import java.awt.Color;//Perintah yang berfungsi untuk menyediakan ruang fungsi warna 
import java.sql.*; //Mengimpor Class untuk mendukung akses dan pengolahan data dalam database
import javax.swing.JOptionPane;//sebagai dialog box untuk antarmuka pengguna grafis (GUI)
import javax.swing.table.DefaultTableModel; //Mengimpor Class Paket berisi kelas dan interface untuk komponen GUI swing Java yang menyediakan untuk tampilan grafis Tabel( mendukung pesan - pesan komentar)
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class FormDataBuku extends javax.swing.JFrame { //Mendefinisikan  kelas FormDataBuku sebagai Frame atau Form 
private DefaultTableModel model; //untuk membuat model pada data
    private Connection con = koneksi.getConnection();//untuk mengambil koneksi 
    private Statement stt; //untuk eksekusi query database
    private ResultSet rss; // untuk penampung dari database
    private int baris; //mendeklarasikan variabel baris
    private boolean kebenaran; //mendeklarasikan variabel kebenaran

public FormDataBuku() {
        initComponents();
        
    }

private void InitTable(){ //Menginisialisasi Table
    model = new DefaultTableModel();//DefaultTableModel Mendeklarasikan Class yang digunakan untuk memberikan header dan data pada kolom dan baris tabel
    model.addColumn("ID");//Menambahkan Kolom "ID" pada Tabel
    model.addColumn("JUDUL");//Menambahkan Kolom "JUDUL" pada Tabel
    model.addColumn("PENULIS");//Menambahkan Kolom "PENULIS" pada Tabel
    model.addColumn("HARGA");//Menambahkan Kolom "HARGA" pada Tabel
    
    jTable1.setModel(model);
    
}
private void TampilData(){ //Menampilkan data yang tersimpan pada database
     try{
            String sql = "SELECT * FROM buku"; //Deklarasi Variabel "sql" sebagai String dan Menampilkan semua data dalam sebuah table Buku
            stt = con.createStatement();//membuat dan menghubungkan koneksi statment
            rss = stt.executeQuery(sql);//Mendefinisikan untuk mendapatkan data dengan eksekusi Query
            while(rss.next()){//selama masih ada data (next)
                Object[] o = new Object[4];//Menambahkan baris data object Tabel
                o[0] = rss.getString("id");//Menambahkan baris data pada kolom "ID" sebagai String
                o[1] = rss.getString("judul");//Menambahkan baris data pada kolom "JUDUL" sebagai String
                o[2] = rss.getString("penulis");//Menambahkan baris data pada kolom "PENULIS" sebagai String
                o[3] = rss.getInt("harga");//Menambahkan baris data pada kolom "HARGA" sebagai String
                model.addRow(o);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());//Perintah untuk menampilkan Pesan Error jika eksekusi Gagal atau ada yang salah.
        }
    }
private void TambahData(String judul, String penulis, String harga){//Menambahkan data kedalam database
   try{
            String sql =
                    "INSERT INTO buku VALUES (NULL, '"+judul+"','"+penulis+"',"+harga+")";//Mengisi variable "sql" dengan perintah String SQL
            stt = con.createStatement();//membuat dan menghubungkan statment
            stt.executeUpdate(sql);//Mengeksekusi perintah SQL yang sudah dideklarasikan
           
        }catch(SQLException e){
            System.out.println(e.getMessage());//Perintah untuk menampilkan Pesan Error jika eksekusi Gagal atau ada yang salah.
        }
    }
private void UbahData(String id, String judul, String penulis, String harga){
     try{
            String sql = "UPDATE buku SET judul='"+judul+"',"+"penulis='"+penulis+"',harga='"+harga+"'where id='"+id+"'";//String SQL UPDATE untuk mengubah data yang tersimpan pada database
            stt = con.createStatement();//membuat dan menghubungkan statment
            stt.executeUpdate(sql);//Mengeksekusi perintah SQL yang sudah dideklarasikan
            
        }catch(SQLException e){
            System.out.println(e.getMessage());//Perintah untuk menampilkan Pesan Error jika eksekusi Gagal atau ada yang salah.
        }
    }
private void HapusData(String id, int baris){//Menghapus data yang tersimpan pada database
    try{
            String sqldelete = "DELETE from buku where id='"+id+"'";//String SQL DELETE berfungsi untuk menghapus data yang tersimpan pada database
            stt = con.createStatement();//membuat dan menghubungkan statment
            stt.executeUpdate(sqldelete);//Mengeksekusi perintah SQL yang sudah dideklarasikan
            model.removeRow(baris);
        }catch(SQLException e){
            System.out.println(e.getMessage());//Perintah untuk menampilkan Pesan Error jika eksekusi Gagal atau ada yang salah.
        }
    }
private void CariId(){
        try{
            String sql = "SELECT*from buku where id='"+jTextField3.getText()+"'";//Menampilkan data yang terdapat pada kolom ID
            stt = con.createStatement();//membuat dan menghubungkan statment
            rss = stt.executeQuery(sql);//Mendefinisikan untuk mendapatkan data dengan eksekusi Query 
            while(rss.next()){//selama masih ada data (next)
              Object[] o = new Object[4];  //Menambahkan baris data object Tabel   
              o[0] = rss.getInt("id");//Menambahkan baris data pada kolom "ID" sebagai Integer
              o[1] = rss.getString("judul");//Menambahkan baris data pada kolom "JUDUL" sebagai String
              o[2] = rss.getString("penulis");//Menambahkan baris data pada kolom "PENULIS" sebagai String
              o[3] = rss.getString("harga");//Menambahkan baris data pada kolom "HARGA" sebagai String
              model.addRow(o);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());//Perintah untuk menampilkan Pesan Error jika eksekusi Gagal atau ada yang salah.
        }
    }
   
   private void CariJudul(){
        try{
            String sql = "SELECT*from buku where judul='"+jTextField3.getText()+"'";//Menampilkan data yang terdapat pada kolom Judul
            stt = con.createStatement();//membuat dan menghubungkan statment
            rss = stt.executeQuery(sql);//Mendefinisikan untuk mendapatkan data dengan eksekusi Query
            while(rss.next()){//selama masih ada data (next)
              Object[] o = new Object[4];  //Menambahkan baris data object Tabel  
              o[0] = rss.getInt("id");//Menambahkan baris data pada kolom "ID" sebagai Integer
              o[1] = rss.getString("judul");//Menambahkan baris data pada kolom "JUDUL" sebagai String
              o[2] = rss.getString("penulis");//Menambahkan baris data pada kolom "PENULIS" sebagai String
              o[3] = rss.getString("harga");//Menambahkan baris data pada kolom "HARGA" sebagai String
              model.addRow(o);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());//Perintah untuk menampilkan Pesan Error jika eksekusi Gagal atau ada yang salah.
        }
    }
   
    private void CariPenulis(){
        try{
            String sql = "SELECT*from buku where penulis='"+jTextField3.getText()+"'";//Menampilkan data yang terdapat pada kolom Penulis
            stt = con.createStatement();//membuat dan menghubungkan statment
            rss = stt.executeQuery(sql); //Mendefinisikan untuk mendapatkan data dengan eksekusi Query
            while(rss.next()){//selama masih ada data (next)
              Object[] o = new Object[4];  //Menambahkan baris data object Tabel   
              o[0] = rss.getInt("id");//Menambahkan baris data pada kolom "ID" sebagai Integer
              o[1] = rss.getString("judul");//Menambahkan baris data pada kolom "JUDUL" sebagai String
              o[2] = rss.getString("penulis");//Menambahkan baris data pada kolom "PENULIS" sebagai String
              o[3] = rss.getString("harga");//Menambahkan baris data pada kolom "HARGA" sebagai String
              model.addRow(o);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());//Perintah untuk menampilkan Pesan Error jika eksekusi Gagal atau ada yang salah.
        }
    }
    
    private void CariHarga(){//Menampilkan data yang terdapat pada kolom Harga
        try{
            String sql = "SELECT*from buku where harga='"+jTextField3.getText()+"'";//Menampilkan data yang terdapat pada kolom Harga
            stt = con.createStatement();//membuat dan menghubungkan statment
            rss = stt.executeQuery(sql); //Mendefinisikan untuk mendapatkan data dengan eksekusi Query
            while(rss.next()){ //selama masih ada data (next)
              Object[] o = new Object[4]; //Menambahkan baris data object Tabel    
              o[0] = rss.getInt("id");//Menambahkan baris data pada kolom "ID" sebagai Integer
              o[1] = rss.getString("judul");//Menambahkan baris data pada kolom "JUDUL" sebagai String
              o[2] = rss.getString("penulis"); //Menambahkan baris data pada kolom "PENULIS" sebagai String
              o[3] = rss.getString("harga");//Menambahkan baris data pada kolom "HARGA" sebagai String
              model.addRow(o);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());//Perintah untuk menampilkan Pesan Error jika eksekusi Gagal atau ada yang salah.bbkk
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 102, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Form Data Buku");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 102, 102));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Judul");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Penulis");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Harga");

        jTextField1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField1CaretUpdate(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tere Liye", "W.S Rendra", "Felix Siauw", "Asma Nadia", "Dewi Lestari" }));

        jButton1.setText("Simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ubah");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Hapus");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Keluar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "JUDUL", "PENULIS", "HARGA"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Search");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("By");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Id", "Judul", "Penulis", "Harga" }));

        jButton5.setText("Cari");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4))
                                    .addGap(34, 34, 34)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField1)
                                        .addComponent(jTextField2)
                                        .addComponent(jComboBox1, 0, 250, Short.MAX_VALUE)))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jButton1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton4)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(175, 175, 175)))
                        .addContainerGap(52, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
// TODO add your handling code here:
      InitTable();//Meng-inisialisasi Table
      TampilData();//Meng-inisialisasi Tampilan data 
    }//GEN-LAST:event_formComponentShown

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
      String judul = jTextField1.getText();//Kolom Judul sebagai String
        String penulis = jComboBox1.getSelectedItem().toString();//Kolom Penulis sebagai String dan memilih item yang terdapat pada combobox
        String harga = jTextField2.getText();//Kolom Judul sebagai String
        TambahData(judul, penulis, harga);//menambahkan data pada kolom
        InitTable(); //Meng-inisialisasi Table
        TampilData();//Meng-inisialisasi Tampilan data 
       
        JOptionPane.showMessageDialog(null,"Data Tersimpan");//menampilkan pesan dengan jenis pesan tertentu dan pilihan default
                                         
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
      System.exit(0);//Button digunakan untuk Keluar dari program
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
          //Button digunakan untuk menghapus data   
        // TODO add your handling code here:
        try{
        int baris = jTable1.getSelectedRow();//Mengambil Index baris tabel yang di pilih user
        String id = jTable1.getValueAt(baris, 0).toString();// mengambil Index atau Primary dari data di kasus adalah Id
        HapusData(id,baris);//meng-inisialisasi Hapus data
        JOptionPane.showMessageDialog(null,"Data Dihapus");//Menampilkan pesan data yang di Hapus
    }catch (Exception e){
        
   
    }//GEN-LAST:event_jButton3ActionPerformed
    }
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        jButton1.setEnabled(true);//Tombol berfungsi
    }//GEN-LAST:event_formWindowActivated

    private void jTextField1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1CaretUpdate
        // TODO add your handling code here:
        //perintah untuk mengubah data                                      
        if(jTextField1.getText().equals("")||jTextField2.getText().equals("")){//jika TextField Kosong maka Tombol tidak berfungsi
            jButton1.setEnabled(false);
    }else{
   jButton1.setEnabled(true);//Jika Tidak maka Tombol berfungsi
    }//GEN-LAST:event_jTextField1CaretUpdate

    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
   //Button untuk mengubah data 
        int baris = jTable1.getSelectedRow();//Mengambil Index baris tabel yang di pilih user
        jTable1.setValueAt(jTextField1.getText(), baris, 1);//Mengatur Text field pada Table1
        jTable1.setValueAt(jComboBox1.getSelectedItem(), baris, 2);//Mengatur ComboBox pada Table1
        jTable1.setValueAt(jTextField2.getText(), baris, 3);//Mengatur Text field pada Table1
        
        String id = jTable1.getValueAt(baris, 0).toString();//String Id Mengambil Index pada baris 0
        String judul = jTable1.getValueAt(baris, 1).toString();//String Judul Mengambil Index pada baris 1
        String penulis = jTable1.getValueAt(baris, 2).toString();//String Penulis Mengambil Index pada baris 2
        String harga = jTable1.getValueAt(baris, 3).toString();//String Harga Mengambil Index pada baris 3
        UbahData(id,judul,penulis,harga);//men-inisialisasi ubah data
        
        jTextField1.setText(judul);//Mengatur Text Field sebagai Kolom Judul
        jComboBox1.setSelectedItem(penulis);//Mengatur ComboBox sebagai Kolom Penulis
        jTextField2.setText(harga);//Mengatur Text Field sebagai Kolom Harga
       
                            // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //Button untuk Mencari data  
// TODO add your handling code here:
        if(jTextField3.getText().equals("")){//Jika TextField kosong maka Dialog Box muncul
            JOptionPane.showMessageDialog(null,"Tidak Boleh Kosong");//Menampilkan pesan data tidak boleh kosong
        }
            else{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            if(jComboBox2.getSelectedItem().equals("Id")){//jika memilih Id pada ComboBox maka kolom Id Tampil
             CariId();  
            }
            else if(jComboBox2.getSelectedItem().equals("Judul")){//Jika memilih Judul pada ComboBox maka kolom Judul Tampil
             CariJudul();
            }
            else if(jComboBox2.getSelectedItem().equals("Penulis")){//Jika memilih Penulis pada ComboBox maka kolom Penulis Tampil
             CariPenulis();
            }
            else if(jComboBox2.getSelectedItem().equals("Harga")){//Jika memilih Harga pada ComboBox maka kolom Harga Tampil
             CariHarga();
             }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormDataBuku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
