package com.zakyidhafi.aplikasikasir;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.zakyidhafi.aplikasikasir.Model.*;

import java.util.Comparator;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.SortStateViewProviders;
import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;

public class tabelBelanjaan extends SortableTableView<Belanjaan>
{
	public tabelBelanjaan(Context ctx){
		super(ctx, null);
	}
	public tabelBelanjaan(final Context context, final AttributeSet attributes) {
        this(context, attributes, android.R.attr.listViewStyle);
    }

    public tabelBelanjaan(final Context context, final AttributeSet attributes, final int styleAttributes) {
        super(context, attributes, styleAttributes);
		setColumnCount(3);
		SimpleTableHeaderAdapter simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(context, "Nama", "Harga", "Jumlah");
		simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(context, R.color.table_header_text));
		final int rowColorEven = ContextCompat.getColor(context, R.color.table_data_row_even);
        final int rowColorOdd = ContextCompat.getColor(context, R.color.table_data_row_odd);
        setDataRowBackgroundProvider(TableDataRowBackgroundProviders.alternatingRowColors(rowColorEven, rowColorOdd));
        setHeaderSortStateViewProvider(SortStateViewProviders.brightArrows());
		setHeaderAdapter(simpleTableHeaderAdapter);
		setColumnComparator(0, new NamaProdukComparator());
		setColumnComparator(1, new  HargaProdukComparator());
		setColumnComparator(2, new QnProdukComparator());
		
	}
	private static class HargaProdukComparator implements Comparator<Belanjaan> {
        @Override
        public int compare(Belanjaan prod1, Belanjaan prod2) {
            if (prod1.getProduk().getHarga() < prod2.getProduk().getHarga()) return -1;
            if (prod1.getProduk().getHarga() > prod2.getProduk().getHarga()) return 1;
            return 0;
        }
	}
	private static class QnProdukComparator implements Comparator<Belanjaan> {
        @Override
        public int compare(Belanjaan prod1, Belanjaan prod2) {
            if (prod1.getQuantity() < prod2.getQuantity()) return -1;
            if (prod1.getQuantity() > prod2.getQuantity()) return 1;
            return 0;
        }
	}
	private static class NamaProdukComparator implements Comparator<Belanjaan> {
        @Override
        public int compare(Belanjaan prod1, Belanjaan prod2) {
            return prod1.getProduk().getNama().compareTo(prod2.getProduk().getNama());
        }
    }
}
