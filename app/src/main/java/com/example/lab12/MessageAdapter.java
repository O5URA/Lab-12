package com.example.lab12;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab12.Database.MessageMaster;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private Context mContext;
    private Cursor mCursor;

    public MessageAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        public TextView teacherName;
        public TextView subjectName;
        public TextView message;
        public CardView messageCard;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            teacherName = itemView.findViewById(R.id.single_teacher_name);
            subjectName = itemView.findViewById(R.id.single_subject_name);
            message = itemView.findViewById(R.id.single_message);
            messageCard = itemView.findViewById(R.id.single_message_card);
        }
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }

        String name = mCursor.getString(mCursor.getColumnIndex(MessageMaster.Messages.COLUMN_TEACHER_NAME));
        final String subName = mCursor.getString(mCursor.getColumnIndex(MessageMaster.Messages.COLUMN_SUBJECT));
        final String subMessage = mCursor.getString(mCursor.getColumnIndex(MessageMaster.Messages.COLUMN_MESSAGE));

        holder.teacherName.setText(name);
        holder.subjectName.setText(subName);
        holder.message.setText(subMessage);

        holder.messageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(mContext,MessageActivity.class);
                sendIntent.putExtra("subject_name", subName);
                sendIntent.putExtra("message", subMessage);
                mContext.startActivity(sendIntent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
}
