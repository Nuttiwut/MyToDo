package com.example.mytodo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytodo.R;
import com.example.mytodo.UpdateToDoActivity;
import com.example.mytodo.db.ToDo;
import com.example.mytodo.db.ToDoRepository;

import java.util.List;

import static android.widget.AdapterView.*;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.MyViewHolder> {

    private Context mContext;
    private List<ToDo> mToDoList;

    public ToDoListAdapter(Context context, List<ToDo> toDoList){
        this.mContext = context;
        this.mToDoList = toDoList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_todo,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ToDo todo = mToDoList.get(position);
        holder.titleTextView.setText(todo.getTitle());
        holder.detailsTextView.setText(todo.getDetail());
        holder.checkBoxView.setChecked(todo.getChecked());
        holder.toDo = todo;

    }

    @Override
    public int getItemCount() {
        return mToDoList.size();
    }




    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView, detailsTextView;
        private ToDo toDo;
        private CheckBox checkBoxView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext,UpdateToDoActivity.class);
                    intent.putExtra("todo", toDo);
                    mContext.startActivity(intent);

                }
            });

//            itemView.setOnClickListener();

            titleTextView = itemView.findViewById(R.id.title_text_view);
            detailsTextView = itemView.findViewById(R.id.details_text_view);
            checkBoxView = itemView.findViewById(R.id.check_box);

//            checkBoxView.
//
//            checkBoxView.OnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
//                @Override
//                public  void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    ToDoRepository repo = new ToDoRepository(mContext);
//                    repo.updateOnlyCheck(toDo.getId(),toDo.getChecked());
//                }
//            });

        }
    }

}
