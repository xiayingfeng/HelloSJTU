package com.bytedance.hellosjtu;
/*
  @author Orange
 * @date 2021/11/1 22:30
 */

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TextViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView mTextView;

    public TextViewHolder(@NonNull View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.text);
        itemView.setOnClickListener(this);
    }

    public void bind(String text) {
        mTextView.setText(text);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), SearchActivity.class);
        intent.putExtra("extra", mTextView.getText().toString());
        view.getContext().startActivity(intent);
    }
}
