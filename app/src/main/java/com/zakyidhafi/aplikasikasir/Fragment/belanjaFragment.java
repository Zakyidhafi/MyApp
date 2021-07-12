package com.zakyidhafi.aplikasikasir.Fragment;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zakyidhafi.aplikasikasir.*;
import com.zakyidhafi.aplikasikasir.Adapter.*;
import com.zakyidhafi.aplikasikasir.Model.*;
import com.zakyidhafi.aplikasikasir.Widget.*;

import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.listeners.TableDataLongClickListener;

public class belanjaFragment extends Fragment
{
	public static TextView totaljum;
	public static BottomSheetBehavior bottomSheetBehavior;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.belanja, container, false);
		tabelBelanjaan velanjaan=(tabelBelanjaan) v.findViewById(R.id.belanjaan);
		velanjaan.setDataAdapter(com.zakyidhafi.aplikasikasir.MainActivity.dataBalanjaan);
		velanjaan.addDataClickListener(new DtaClickListener());
		velanjaan.addDataLongClickListener(new DataLongClickListener());
		return v;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		FloatingActionButton fabshop=(FloatingActionButton) view.findViewById(R.id.fab_shopping);
		totaljum=(TextView) view.findViewById(R.id.totaljumlah);
		bottomSheetBehavior = BottomSheetBehavior.from(view.findViewById(R.id.bottomSheet));
		
		fabshop.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View p1) {
					Toast.makeText(getActivity().getApplicationContext(), "Pembelian Berhasil di proses", Toast.LENGTH_LONG).show();
				}
			});
		
		if(BelanjaanDataAdapter.total!=0){
			totaljum.setText("Rp. "+BelanjaanDataAdapter.PRICE_FORMATTER.format(BelanjaanDataAdapter.total));
			bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
		}else{
			bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
		}
	}
	private class DtaClickListener implements TableDataClickListener<Belanjaan> {
		@Override
		public void onDataClicked(int p1, Belanjaan belanjaan) {
			
			//new inputProdukScanner(getActivity()).shoping();
			new EditorDialog(getActivity(), belanjaan, totaljum);
		}
    }
	private class DataLongClickListener implements TableDataLongClickListener<Belanjaan> {
        @Override
        public boolean onDataLongClicked(int rowIndex, Belanjaan belanjaan) {
            //showdlg(belanjaan.getProduk().getNama(), belanjaan.getProduk().getHarga(), belanjaan.getQuantity());
			return true;
        }
    }
	
}
