package com.itla.prueba_db;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itla.prueba_db.entidad.Materia;

import java.util.List;

public class AdaptadorMateria extends RecyclerView.Adapter<AdaptadorMateria.ViewHolder> {


    private List<Materia> listaMateria;

    public AdaptadorMateria(List<Materia> listaMateria) {
        this.listaMateria = listaMateria;
    }

    public List<Materia> getListaMateria(){
        return  listaMateria;
    }

    public void setListaDeMateria(List<Materia> listaMateria) {
        this.listaMateria = listaMateria;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_materia,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mtNombre.setText((listaMateria.get(position).getNombre().toString()));
    }

    @Override
    public int getItemCount() {

        return listaMateria.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //String
        TextView mtNombre;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mtNombre = (TextView) itemView.findViewById(R.id.mtNombre);
        }
    }
}
