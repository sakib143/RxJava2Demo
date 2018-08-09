package com.example.admn.rxjava2demo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admn.rxjava2demo.Model.StudentModel;
import com.example.admn.rxjava2demo.R;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder>{

    private Context context;
    private List<StudentModel> alStudent;

    public StudentAdapter(Context context,List<StudentModel> alStudent){
        this.context  = context;
        this.alStudent = alStudent;
    }

    @NonNull
    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_student, parent, false);
        return new StudentAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder holder, int position) {
        holder.tvStudentName.setText(alStudent.get(position).getStrName());
    }

    @Override
    public int getItemCount() {
        return alStudent.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvStudentName;

        public ViewHolder(View itemView) {
            super(itemView);

            tvStudentName = itemView.findViewById(R.id.tvStudentName);
        }
    }
}
