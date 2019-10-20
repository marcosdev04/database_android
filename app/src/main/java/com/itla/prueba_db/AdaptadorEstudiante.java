package com.itla.prueba_db;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itla.prueba_db.entidad.Estudiante;
import com.itla.prueba_db.repositorio.EstudianteRepositorio;
import com.itla.prueba_db.repositorio.EstudianteRepositorioDbImpl;

import org.w3c.dom.Text;

import java.util.List;

public class AdaptadorEstudiante extends RecyclerView.Adapter<AdaptadorEstudiante.MyViewHolder>
{
    private List<Estudiante> listaEstudiante;

    public AdaptadorEstudiante(List<Estudiante> listaEstudiante) {
        this.listaEstudiante = listaEstudiante;
    }

    public List<Estudiante> getListEstudiante(){
        return  listaEstudiante;
    }

    public void setListaDeEstudiante(List<Estudiante> listaEstudiante) {
        this.listaEstudiante = listaEstudiante;

    }

    @Override
    public AdaptadorEstudiante.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_item,null,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorEstudiante.MyViewHolder myViewHolder, int position) {
        myViewHolder.idEst.setText((listaEstudiante.get(position).getId().toString()));
        myViewHolder.nombreEst.setText((listaEstudiante.get(position).getNombre().toString()));
        myViewHolder.matriculaEst.setText((listaEstudiante.get(position).getMatricula().toString()));
    }

    @Override
    public int getItemCount() {
        return listaEstudiante.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView idEst,nombreEst,matriculaEst;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            idEst = (TextView) itemView.findViewById(R.id.txtId);
            nombreEst = (TextView) itemView.findViewById(R.id.txtNombre);
            matriculaEst = (TextView) itemView.findViewById(R.id.txtMatricula);
        }

    }

}
