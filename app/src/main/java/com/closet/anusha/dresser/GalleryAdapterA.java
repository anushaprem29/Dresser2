package com.closet.anusha.dresser;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class GalleryAdapterA extends BaseAdapter {

    private AccessoriesDatabase handler;
    private Context mContext;
    private List<Accessories> galleryRows;
    private LayoutInflater inflater;
    int colorsValue[];
    int idColor;
    Accessories contact;
    private LinearLayout mDotsLayout;

    public GalleryAdapterA(Context mContext, List<Accessories> galleryRows,AccessoriesDatabase handler) {
        inflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.galleryRows = galleryRows;
        this.handler = handler;
    }
    @Override
    public int getCount() {
        return galleryRows.size();
    }

    @Override
    public Object getItem(int i) {
        return galleryRows.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        contact = galleryRows.get(position);
        View view = convertView;
        if(view == null)
            view = inflater.inflate(R.layout.item_gal, null);
        colorsValue = new int[]{Color.WHITE, Color.BLACK,Color.BLUE,Color.RED,Color.GREEN,Color.YELLOW,
                Color.parseColor("#FF3F00"),Color.parseColor("#FF00FF")
                ,Color.parseColor("#BA55D3"),Color.parseColor("#773300"),
                Color.parseColor("#D3D3D3"),Color.parseColor("#DFDFDF"),
                Color.parseColor("#FFDF00")};
        ImageView mImageView = (ImageView) view.findViewById(R.id.img_pic);
     //   TextView mTextViewId = (TextView) view.findViewById(R.id.tv_id);
      //  TextView mTextViewtypeId = (TextView)view.findViewById(R.id.tv_typeId);
        TextView mTextViewnoOfTimes = (TextView) view.findViewById(R.id.tv_noOfTimes);
     //   TextView mTextViewcolorId = (TextView) view.findViewById(R.id.tv_colorId);
        TextView mTextViewrating = (TextView) view.findViewById(R.id.tv_rating);
       // TextView mTextViewrange = (TextView) view.findViewById(R.id.tv_range);
        TextView mTextViewavailability = (TextView) view.findViewById(R.id.tv_availablity);
        TextView mTextViewlastWorn = (TextView) view.findViewById(R.id.tv_lastWorn);
        TextView mTextViewcomment = (TextView) view.findViewById(R.id.tv_comment);
        ImageView del = (ImageView) view.findViewById(R.id.img_del);
      //  mTextViewId.setText("ID: "+contact.get_id());
      //  mTextViewtypeId.setText("TYPE: "+contact.get_type());
        mTextViewnoOfTimes.setText("NO OF TIMES: "+contact.get_noOfTimes());
       // mTextViewcolorId.setText("COLOR: "+
        idColor=contact.get_color();
        mTextViewrating.setText("RATING: "+contact.get_rating());
        mDotsLayout= (LinearLayout) view.findViewById(R.id.llt_col);
        //  mTextViewrange.setText("RANGE: "+contact.get_topBot());
        mTextViewavailability.setText("AVAILABILITY: "+contact.get_available());
        mTextViewlastWorn.setText("LAST WORN: "+contact.get_lastWorn());
        mTextViewcomment.setText("COMMENT: "+contact.get_comment());
        Bitmap bmp = BitmapFactory.decodeByteArray(contact.get_photograph(), 0, contact.get_photograph().length);
        mImageView.setImageBitmap(bmp);
        openDatabase();
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog diaBox = askOption();
                diaBox.show();
            }
        });
        return view;
    }

    private AlertDialog askOption()
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(mContext)
                //set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
                .setIcon(R.drawable.deletered)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if(handler.deleteItem(contact.get_id())){
                            Intent intent = new Intent(mContext,GalleryActivity.class);
                            mContext.startActivity(intent);
                        }
                        else {
                            Toast.makeText(mContext,"Something went wrong.",Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;

    }private void openDatabase() {
        Cursor cursor;
        SQLiteDatabase myDb;
        int count = mDotsLayout.getChildCount();
        View v = null;
        for(int i=0; i<count; i++) {
            v = mDotsLayout.getChildAt(i);
            v.setVisibility(View.GONE);
        }

        myDb = mContext.openOrCreateDatabase("fcolors.db",Context.MODE_PRIVATE, null);
        String tableName="fcolor_table";
        String query1=" SELECT * from "+tableName;
        //+" WHERE "+tableName+".Color_id="+idColor;
        cursor = myDb.rawQuery(query1, null);
        if (cursor.moveToFirst()) {
            do {
                if(cursor.getInt(0)==idColor) break;
                System.out.println("FGD"+cursor.getInt(0)+idColor);
            } while (cursor.moveToNext());
        }
        for(int iter=3;iter<15;iter++){
            if(cursor.getInt(iter)==1){
                v=mDotsLayout.getChildAt(iter-3);
                v.setVisibility(View.VISIBLE);
            }
        }
        System.out.println("f");
        cursor.close();
    }
}
