import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Transaksi implements Penjualan {
    public String noFkt;
    public Integer noBrg;
    public String namaBrg;
    public Integer hargaBrg;
    public Integer jmlh;
    public Integer subTtl;
    public Integer diskon;
    public Integer ttlHarga = 0;
    public String tanggal;
    

    Scanner scan = new Scanner(System.in);

    public String getTanggal(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
    public String getWaktu(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(date);
    }
    @Override
    public void noFaktur(){
        System.out.print("No Faktur : ");
        noFkt = scan.nextLine();
    }
    @Override
    public int subTotal(int harga){
        hargaBrg = harga;
        subTtl = jmlh * hargaBrg;
        System.out.println("Subtotal : "+subTtl);
        return subTtl;
    }
    @Override
    public Integer discount(){
        if( ttlHarga >= 500000 && ttlHarga <= 1000000){
            diskon = ttlHarga * 1/100;
        }
        else if(ttlHarga > 1000000 && ttlHarga <= 7000000){
            diskon =  subTtl * 3/100;
        }
        else if(ttlHarga > 7000000 && ttlHarga <= 10000000){
            diskon = ttlHarga * 5/100;
        }
        else if(ttlHarga > 10000000){
            diskon = ttlHarga * 7/100;
        }
        else  diskon = 0;
        System.out.println("Diskon : "+diskon);
        return diskon;
    }
    @Override
    public void totalHarga(){
        ttlHarga = ttlHarga - diskon; 
        System.out.println("Total : "+ttlHarga); 
    }
    @Override
    public void namaBarang() {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void hargaBarang() {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void jumlah() {
        // TODO Auto-generated method stub
        
    }


}
