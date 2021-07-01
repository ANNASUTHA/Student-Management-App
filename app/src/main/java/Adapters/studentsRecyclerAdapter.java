package Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.annasutha.student_app.R;

import java.util.List;

import Models.User;

public class studentsRecyclerAdapter extends RecyclerView.Adapter<studentsRecyclerAdapter.StudentsViewHolder> {
    private final List<User> listUsers;

    public studentsRecyclerAdapter(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    @Override
    public StudentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_recycler, parent, false);
        return new StudentsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StudentsViewHolder holder, int position) {
        holder.textViewName.setText(listUsers.get(position).getName());
        //holder.textViewSchool.setText(listUsers.get(position).getId());
        holder.textViewPhone.setText(listUsers.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        Log.v(studentsRecyclerAdapter.class.getSimpleName(), "" + listUsers.size());
        return listUsers.size();
    }

    public class StudentsViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView textViewName;
        //public AppCompatTextView textViewSchool;
        public AppCompatTextView textViewPhone;

        public StudentsViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            //textViewSchool = itemView.findViewById(R.id.textViewSchool);
            textViewPhone = itemView.findViewById(R.id.textViewPhone);
        }
    }
}
