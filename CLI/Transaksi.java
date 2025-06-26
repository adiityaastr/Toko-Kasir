import java.util.ArrayList;
import java.util.List;

public class Transaksi {
    private List<TransaksiItem> items = new ArrayList<>();
    private int total = 0;

    public void tambahItem(TransaksiItem item) {
        items.add(item);
        total += item.getSubtotal();
        item.getBarang().kurangiStok(item.getJumlah());
    }

    public void tampilkanTransaksi() {
        for (TransaksiItem item : items) {
            item.tampilkan();
        }
        System.out.println("Total: Rp " + total);
    }

    public int getTotal() {
        return total;
    }
}
