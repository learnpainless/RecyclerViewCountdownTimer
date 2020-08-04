package com.learnpainless.recyclerviewcountdowntimer;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private OnItemClickListener onItemClickListener;
    private Handler handler = new Handler();

    public RecyclerViewAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void clearAll() {
        handler.removeCallbacksAndMessages(null);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView timeStamp;
        ImageView imageView;
        CountdownRunnable countdownRunnable;

        public ViewHolder(View itemView) {
            super(itemView);
            timeStamp = itemView.findViewById(R.id.timestamp);
            imageView = itemView.findViewById(R.id.image_view);
            countdownRunnable = new CountdownRunnable(handler,timeStamp,10000,imageView);
        }

        public void bind(final OnItemClickListener listener) {
            handler.removeCallbacks(countdownRunnable);
            countdownRunnable.holder = timeStamp;
            countdownRunnable.millisUntilFinished = 10000 * getAdapterPosition(); //because i want all timers run separately.
            handler.postDelayed(countdownRunnable, 100);

        }
    }




    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);

        return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(onItemClickListener);
    }

    @Override public int getItemCount() {
        return 100;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
