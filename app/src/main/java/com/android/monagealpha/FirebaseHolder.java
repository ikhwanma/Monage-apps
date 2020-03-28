package com.android.monagealpha;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseHolder extends RecyclerView.ViewHolder {
    public TextView viewUang,viewTanggal,viewKategori,viewMemo,title;

    public FirebaseHolder(@NonNull View itemView) {
        super(itemView);

        viewUang = itemView.findViewById(R.id.viewUang);
        viewTanggal = itemView.findViewById(R.id.viewTanggal);
        viewMemo = itemView.findViewById(R.id.viewMemo);
        viewKategori = itemView.findViewById(R.id.viewKategori);
        title = itemView.findViewById(R.id.title);
    }
}
