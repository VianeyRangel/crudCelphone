package mx.edu.utng.dloza.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import mx.edu.utng.dloza.crudcelphone.R;
import mx.edu.utng.dloza.crudcelphone.UpdateCelphoneActivity;
import mx.edu.utng.dloza.model.Celphone;

/**
 * Created by Vianey on 11/04/2018.
 */

public class CelphoneAdapter extends RecyclerView.Adapter<CelphoneAdapter.ViewHolder>{

    private List<Celphone> mCelphoneList;
    private Context mContext;
    private RecyclerView mRecyclerV;



    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView celphoneMarkTxtV;
        public TextView celphoneModelTxtV;
        public TextView celphoneColorTxtV;
        public TextView celphoneMemoryTxtV;
        public TextView celphoneCostTxtV;
        public TextView celphoneCompanyTxtV;



        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            celphoneMarkTxtV = (TextView) v.findViewById(R.id.mark);
            celphoneModelTxtV= (TextView) v.findViewById(R.id.model);
            celphoneColorTxtV = (TextView) v.findViewById(R.id.color);
            celphoneMemoryTxtV = (TextView) v.findViewById(R.id.memory);
            celphoneCostTxtV = (TextView) v.findViewById(R.id.cost);
            celphoneCompanyTxtV = (TextView) v.findViewById(R.id.company);

        }
    }

    public void add(int position, Celphone celphone) {
        mCelphoneList.add(position, celphone);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mCelphoneList.remove(position);
        notifyItemRemoved(position);
    }



    // Provide a suitable constructor (depends on the kind of dataset)
    public CelphoneAdapter(List<Celphone> myDataset, Context context, RecyclerView recyclerView) {
        mCelphoneList = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.single_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final Celphone celphone = mCelphoneList.get(position);
        holder.celphoneMarkTxtV.setText("Mark: " + celphone.getMark());
        holder.celphoneModelTxtV.setText("Model: " + celphone.getModel());
        holder.celphoneColorTxtV.setText("Color: " + celphone.getColor());
        holder.celphoneMemoryTxtV.setText("Memory: " + celphone.getMemory());
        holder.celphoneCostTxtV.setText("Cost: " + celphone.getCost());
        holder.celphoneCompanyTxtV.setText("Company: " + celphone.getCompany());



        //listen to single view layout click
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Choose option");
                builder.setMessage("Update or delete celphone?");
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //go to update activity
                        goToUpdateActivity(celphone.getId());

                    }
                });
                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CelphoneDBHelper dbHelper = new CelphoneDBHelper(mContext);
                        dbHelper.deleteCelphoneRecord(celphone.getId(), mContext);

                        mCelphoneList.remove(position);
                        mRecyclerV.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, mCelphoneList.size());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });


    }

    private void goToUpdateActivity(long personId){
        Intent goToUpdate = new Intent(mContext, UpdateCelphoneActivity.class);
        goToUpdate.putExtra("USER_ID", personId);
        mContext.startActivity(goToUpdate);
    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mCelphoneList.size();
    }


}
