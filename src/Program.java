
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.*;

public class Program {
    static Connection connection;
    static String pilihanUser;
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        boolean isLanjutkan = true;
        String url = "jdbc:mysql://localhost:3306/faktur"; 
        String ucapan = "\nselamat datang";
        System.out.println(ucapan.toUpperCase());
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, "root", "");
            System.out.println("Class Driver ditemukan");
            
            while(isLanjutkan){   
                System.out.println("\n------------------");
				System.out.println("     Toko ABC");
				System.out.println("------------------");
				System.out.println("1. Lihat data barang");
				System.out.println("2. Tambah data barang");
				System.out.println("3. Ubah data barang");
				System.out.println("4. Hapus data barang");
				System.out.println("5. Cari data barang");
                System.out.println("6. Inputkan transaksi pelanggan");
                System.out.println("7. Cetak faktur pembelian");
                System.out.print("\nPilihan anda (1/2/3/4/5): ");
				pilihanUser = scan.next();
				
				switch (pilihanUser) {
				case "1":
					lihatdata();
					break;
				case "2":
					tambahdata();
					break;
				case "3":
					ubahdata();
					break;
				case "4":
					hapusdata();
					break;
				case "5":
					caridata();
					break;
                case "6":
					inputPembelian();
					break;
                case "7":
					cetakFaktur();
					break;
				default:
					System.err.println("\nInput anda tidak ditemukan\nSilakan pilih [1-5]");
				}
				
				System.out.print("\nApakah Anda ingin melanjutkan [y/n]? ");
				pilihanUser = scan.next();
				isLanjutkan = pilihanUser.equalsIgnoreCase("y");
			}
			System.out.println("\nBye.... Selamat Berjumpa Kembali!!!");
		
                // barang.noFaktur();
                // barang.namaBarang();
                // barang.hargaBarang();
                // barang.jumlah();
                // barang.subTotal();
                // barang.discount();
                // barang.totalHarga();  
                // System.out.print(barang);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        } catch(IndexOutOfBoundsException e){
            System.out.println(e); 
            System.out.println("inputkan pilihan antara 1 sampai 5!"); 
        } catch(ClassNotFoundException ex) {
			System.err.println("Driver Error");
			System.exit(0);
		} catch(SQLException e){
			System.err.println("Tidak berhasil koneksi");
		} finally{
            ucapan = ucapan.replace("selamat datang", "Terimakasih\n");
            System.out.println(ucapan.toUpperCase());
        }      
    }

    private static void lihatdata() throws SQLException {
		String text1 = "\n===Daftar Seluruh Data Barang===";
		System.out.println(text1.toUpperCase());
						
		String sql ="SELECT * FROM barang";
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(sql);
		
		while(result.next()){
			System.out.print("\nKode Barang\t: ");
            System.out.print(result.getString("kode_barang"));
            System.out.print("\nBarang\t: ");
            System.out.print(result.getString("nama_barang"));
            System.out.print("\nHarga\t: ");
            System.out.print(result.getInt("harga"));
            System.out.print("\n");
		}
	}

    private static void tambahdata() throws SQLException{
		String text2 = "\n===Tambah Data Barang===";
		System.out.println(text2.toUpperCase());
				
		try {
            System.out.print("Kode Barang\t: ");
            String kodeBarang = scan.next();
            System.out.print("Barang\t: ");
            String namaBarang = scan.next();
            System.out.print("Harga\t: ");
            int harga = scan.nextInt();
            
            String sql = "INSERT INTO barang (kode_barang, nama_barang, harga) VALUES ('"+kodeBarang+"','"+namaBarang+"','"+harga+"')";
                        
            Statement statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("Berhasil input data");
	
	    } catch (SQLException e) {
	        System.err.println("Terjadi kesalahan input data");
	    } catch (InputMismatchException e) {
	    	System.err.println("Inputlah dengan angka saja");
	   	}
	}

    private static void ubahdata() throws SQLException{
		String text3 = "\n===Ubah Data Barang===";
		System.out.println(text3.toUpperCase());
		
		try {
            lihatdata();
            System.out.print("Masukkan 'Kode Barang' dari data barang yang akan di ubah atau update : ");
            String kodeBarang = scan.next();
            
            String sql = "SELECT * FROM barang WHERE kode_barang LIKE '%"+kodeBarang+"%'";
            
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            if(result.next()){
                
                System.out.print("Barang ["+result.getString("nama_barang")+"]\t: ");
                String namaBarang = scan.next();
                
                System.out.print("Harga ["+result.getInt("harga")+"]\t: ");
                int harga = scan.nextInt();
                   
                sql = "UPDATE barang SET nama_barang='"+namaBarang+"', harga= '"+harga+"' WHERE kode_barang='"+kodeBarang+"'"; 

                if(statement.executeUpdate(sql) > 0){
                    System.out.println("Berhasil memperbaharui data barang (Kode barang "+kodeBarang+")");
                }
            }
            statement.close();        
        } catch (SQLException e) {
            System.err.println("Terjadi kesalahan dalam mengedit data");
            System.err.println(e.getMessage());
        }
		}

    private static void hapusdata() {
		String text4 = "\n===Hapus Data Barang===";
		System.out.println(text4.toUpperCase());
		
		try{
	        lihatdata();
	        System.out.print("Ketik 'Kode Barang' dari data barang yang akan Anda Hapus : ");
            String kodeBarang = scan.next();
	        
	        String sql = "DELETE FROM barang WHERE kode_barang LIKE '%"+kodeBarang+"%'";
	        Statement statement = connection.createStatement();
	        
	        if(statement.executeUpdate(sql) > 0){
	            System.out.println("Berhasil menghapus data barang (kode barang "+kodeBarang+")");
	        }
	    } catch(SQLException e){
	        System.out.println("Terjadi kesalahan dalam menghapus data barang");
	        }
	}
	
    private static void caridata() throws SQLException {
		String text5 = "\n===Cari Data Barang===";
		System.out.println(text5.toUpperCase());
				
		System.out.print("Masukkan Nama Barang : ");
		String keyword = scan.next();

        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM barang WHERE nama_barang LIKE '%"+keyword+"%'";
        ResultSet result = statement.executeQuery(sql); 
                
        while(result.next()){
        	System.out.print("\nKode Barang\t: ");
            System.out.print(result.getInt("kode_barang"));
            System.out.print("\nBarang\t: ");
            System.out.print(result.getString("nama_barang"));
            System.out.print("\nHarga\t: ");
            System.out.print(result.getInt("harga"));
            System.out.print("\n");
        }
	}

    private static void inputPembelian() throws SQLException {
		String text5 = "\n===Pembelian===";
		System.out.println(text5.toUpperCase());
        Transaksi transaksi = new Transaksi();

        try{
            transaksi.noFaktur();
            System.out.println("Tanggal : " +transaksi.getTanggal());
            System.out.print("Nama Pelanggan : ");
            String namaPelanggan = scan.next();
            Barang barang = new Barang();
            String kodeBarang = "";
            String sql1 = "INSERT INTO transaksi (no_faktur, tanggal, nama_pelanggan) VALUES ('"+transaksi.noFkt+"', str_to_date('"+transaksi.getTanggal()+"','%Y-%m-%d'), '"+namaPelanggan+"')";     
            Statement statement = connection.createStatement();
            statement.execute(sql1); 

            boolean beli = true;
            while(beli){
                lihatdata();
                System.out.print("Masukkan kode barang : ");
                kodeBarang = scan.next();
            
                String sql = "SELECT * FROM barang WHERE kode_barang LIKE '%"+kodeBarang+"%'";
                ResultSet result = statement.executeQuery(sql);

                while(result.next()){
                    System.out.print("\nKode Barang\t: ");
                    System.out.print(result.getString("kode_barang"));
                    System.out.print("\nBarang\t: ");
                    System.out.print(result.getString("nama_barang"));
                    System.out.print("\nHarga\t: ");
                    System.out.print(result.getInt("harga"));
                    transaksi.hargaBrg = result.getInt("harga");
                    System.out.print("\n");
                }

                barang.jumlah();   
                barang.ttlHarga += barang.subTotal(transaksi.hargaBrg);  

                String sql2 = "INSERT INTO detail_transaksi (no_faktur, kode_barang, jumlah) VALUES ('"+transaksi.noFkt+"', '"+kodeBarang+"', '"+barang.jmlh+"')";
                statement.execute(sql2);               

                System.out.print("\nApakah masih ada barang yang diinputkan [y/n]? ");
                pilihanUser = scan.next();
                beli = pilihanUser.equalsIgnoreCase("y");
            }
            barang.discount();
            barang.totalHarga(); 
            

        } catch (SQLException e) {
	        System.err.println("Terjadi kesalahan input data");
	    } catch (InputMismatchException e) {
	    	System.err.println("Inputlah dengan angka saja");
	   	}       
	}

    private static void cetakFaktur() throws SQLException {
		String text5 = "\n===Faktur Pembelian===";
		System.out.println(text5.toUpperCase());
		Transaksi transaksi = new Transaksi();	
        transaksi.noFaktur();
        try{
            String sql = "SELECT * FROM transaksi WHERE no_faktur LIKE '%"+transaksi.noFkt+"%'";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql); 
                    
            while(result.next()){
                System.out.print("\nNo. Faktur\t: ");
                System.out.print(result.getString("no_faktur"));
                transaksi.noFkt = result.getString("no_faktur");
                System.out.print("\nTanggal\t: ");
                System.out.print(result.getDate("tanggal"));
                System.out.print("\nNama Pelanggan\t: ");
                System.out.print(result.getString("nama_pelanggan"));
                System.out.print("\n");
            }

            String sql1 = "SELECT detail_transaksi.no_faktur, barang.kode_barang, barang.nama_barang, barang.harga, detail_transaksi.jumlah FROM barang, detail_transaksi WHERE barang.kode_barang = detail_transaksi.kode_barang and detail_transaksi.no_faktur = '"+transaksi.noFkt+"'";
            ResultSet result1 = statement.executeQuery(sql1); 
            // String sql2 = "SELECT * FROM detail_transaksi WHERE no_faktur LIKE '%"+transaksi.noFkt+"%'";
            // ResultSet result2 = statement.executeQuery(sql2);
            while(result1.next()){
                        System.out.print("\nKode barang\t: ");
                        System.out.print(result1.getString("kode_barang"));
                        System.out.print("\nBarang\t: ");
                        System.out.print(result1.getString("nama_barang"));
                        System.out.print("\nHarga\t: ");
                        System.out.print(result1.getInt("harga"));
                        System.out.print("\nJumlah\t: ");
                        System.out.print(result1.getInt("jumlah"));
                        System.out.print("\n");
            }
        } catch (SQLException e) {
	        System.err.println("Terjadi kesalahan input data");
	    } catch (InputMismatchException e) {
	    	System.err.println("Inputlah dengan angka saja");
	   	}  
	}

}
