import java.util.LinkedList;

public class Barang extends Transaksi {
    
    @Override
    public void namaBarang(){
        LinkedList<String> barang = new LinkedList<>();
        barang.add("Kasur");
        barang.add("Bantal");
        barang.add("Lemari");
        barang.add("Meja belajar");
        barang.add("Tikar");
        System.out.println("Pilih barang yang dibeli: ");
        System.out.print("1. Kasur\n2. Bantal\n3. Lemari\n4. Meja belajar\n5. Tikar\nPilih no: ");       
 
        noBrg = scan.nextInt();
        System.out.println(barang.get(noBrg-1)); 
        namaBrg =  barang.get(noBrg-1);
    }

    // @Override
    // public void hargaBarang(){
    //     if(noBrg == 1){
    //         hargaBrg = 550000;
    //     } else if(noBrg == 2){
    //         hargaBrg = 200000;
    //     } else if(noBrg == 3){
    //         hargaBrg = 800000;
    //     } else if(noBrg == 4){
    //         hargaBrg = 1500000;
    //     } else if(noBrg == 5){
    //         hargaBrg = 1500000;
    //     } else {
    //         throw new IllegalArgumentException("\nInputan salah!\nInputkan antara nomor 1 sampai 5!");
    //     }
    //     System.out.println("Harga : "+hargaBrg);
    // }

    @Override
    public void jumlah(){
        System.out.print("Jumlah : ");
        jmlh = scan.nextInt();
    }
    // public String toString(){
    //     String tampil = "\n====================\n";
    //     tampil += "Faktur Pembelian\r\n";
    //     tampil += "====================\r\n";
    //     tampil += "No Faktur : "+noFkt+"\r\n";
    //     tampil += "Tanggal : "+getTanggal()+"\r\n";
    //     tampil += "Waktu : "+getWaktu()+"\r\n";
    //     tampil += "Nama barang : "+namaBrg+"\r\n";
    //     tampil += "Harga : "+String.valueOf(hargaBrg)+"\r\n";
    //     tampil += "Jumlah : "+jmlh+"\r\n";
    //     tampil += "Subtotal : "+subTtl+"\r\n";
    //     tampil += "Diskon : "+diskon+"\r\n";
    //     tampil += "Total : "+ttlHarga+"\r\n";
    //     return tampil;
    // }
    
}
