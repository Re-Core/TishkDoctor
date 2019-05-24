package com.example.tishkdoctor.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tishkdoctor.Activitys.AppointmentDetailActivity;
import com.example.tishkdoctor.Models.Appointment;
import com.example.tishkdoctor.R;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentHolder> {

    private Context mContext;
    private List<Appointment>mAppointmentList;

    public AppointmentAdapter(Context context, List<Appointment> appointmentList) {
        mContext = context;
        mAppointmentList = appointmentList;
    }

    @NonNull
    @Override
    public AppointmentHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.card_appointment,viewGroup,false);

        return new AppointmentHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentHolder appointmentHolder, int i) {

        appointmentHolder.txtDoctorName.setText(mAppointmentList.get(i).getName());
        appointmentHolder.txtDate.setText(mAppointmentList.get(i).getDate());
        appointmentHolder.txtTime.setText(mAppointmentList.get(i).getTime());

    }

    @Override
    public int getItemCount() {
        return mAppointmentList.size();
    }

    public class AppointmentHolder extends RecyclerView.ViewHolder{

        private TextView txtDoctorName,txtDate,txtTime;

        public AppointmentHolder(@NonNull View itemView) {
            super(itemView);

            txtDoctorName=(TextView)itemView.findViewById(R.id.doctorName);
            txtDate=(TextView)itemView.findViewById(R.id.date);
            txtTime=(TextView)itemView.findViewById(R.id.time);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    Intent i = new Intent(mContext, AppointmentDetailActivity.class);
                    i.putExtra("patientId",mAppointmentList.get(position).getPatientId());
                    mContext.startActivity(i);

                }
            });
        }
    }

}
