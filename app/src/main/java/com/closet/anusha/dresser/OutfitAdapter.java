package com.closet.anusha.dresser;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

public class OutfitAdapter extends BaseAdapter {
    Outfits contact;
    private Context mContext;
    private List<Outfits> outfitRows;
    private LayoutInflater inflater;
    private OutfitDatabase handler;
    private LinearLayout llt;
    public OutfitAdapter(Context mContext, List<Outfits> galleryRows, OutfitDatabase handler) {
        inflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.outfitRows = galleryRows;
        this.handler = handler;
    }
    @Override
    public int getCount() {
        return outfitRows.size();
    }

    @Override
    public Object getItem(int i) {
        return outfitRows.get(i);
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        contact = outfitRows.get(position);
        View view = convertView;
        if(view == null)
            view = inflater.inflate(R.layout.single_last_worn, null);
        ImageView mImageView1 = (ImageView) view.findViewById(R.id.lw1);
        llt = (LinearLayout) view.findViewById(R.id.lastworn_lt1);
        ImageView mImageView2 = (ImageView) view.findViewById(R.id.lw2);
        ImageView mImageView3 = (ImageView) view.findViewById(R.id.lw3);
        ImageView mImageView4 = (ImageView) view.findViewById(R.id.lw4);
        ImageView mImageView5 = (ImageView) view.findViewById(R.id.lw5);
        ImageView mImageView6 = (ImageView) view.findViewById(R.id.lw6);
        ImageView mImageView7 = (ImageView) view.findViewById(R.id.lw7);
        ImageView mImageView8 = (ImageView) view.findViewById(R.id.lw8);
        ImageView del = (ImageView) view.findViewById(R.id.img_del_lw);
        TextView dateTv = (TextView) view.findViewById(R.id.dateText);
        dateTv.setText("Date:"+contact.getDate());
        System.out.println("DATE"+contact.getDate());
        int count = llt.getChildCount();
        ImageView v = null;
        byte img1[] = openDatabase("faccessories.db","facc_table",contact.getAccId1());
        if(img1!=null) {
            Bitmap bmp1 = BitmapFactory.decodeByteArray(img1, 0, img1.length);
            mImageView1.setImageBitmap(bmp1);
            v = (ImageView) llt.getChildAt(0);
            v.setVisibility(View.VISIBLE);
        }
        else {
            mImageView1.setVisibility(View.GONE);
        }
        byte img2[] = openDatabase("faccessories.db","facc_table",contact.getAccId2());
        if(img2!=null) {
            Bitmap bmp2 = BitmapFactory.decodeByteArray(img2, 0, img2.length);
            mImageView2.setImageBitmap(bmp2);
            v = (ImageView) llt.getChildAt(1);
            v.setVisibility(View.VISIBLE);
        }
        else {
            mImageView2.setVisibility(View.GONE);
        }
        byte img3[] = openDatabase("faccessories.db","facc_table",contact.getAccId3());
        if(img3!=null) {
            Bitmap bmp3 = BitmapFactory.decodeByteArray(img3, 0, img3.length);
            mImageView3.setImageBitmap(bmp3);

            v = (ImageView) llt.getChildAt(2);
            v.setVisibility(View.VISIBLE);
        }
        else {
            mImageView3.setVisibility(View.GONE);
        }
        byte img5[] = openDatabase("faccessories.db", "facc_table", contact.getAccId4());
        if(img5!=null) {
            Bitmap bmp5 = BitmapFactory.decodeByteArray(img5, 0, img5.length);
            mImageView5.setImageBitmap(bmp5);
            v = (ImageView) llt.getChildAt(4);
            v.setVisibility(View.VISIBLE);
        }
        else {
            mImageView5.setVisibility(View.GONE);
        }
        byte img4[] = openDatabase("fshoebag.db","fshoebag_table",contact.getShoeId1());
        if (img4!=null) {
            Bitmap bmp4 = BitmapFactory.decodeByteArray(img4, 0, img4.length);
            mImageView4.setImageBitmap(bmp4);

            v = (ImageView) llt.getChildAt(3);
            v.setVisibility(View.VISIBLE);
        }
        else {
            mImageView4.setVisibility(View.GONE);
        }
        byte img6[] = openDatabase("fshoebag.db","fshoebag_table",contact.getShoeId2());
        if (img6!=null) {
            Bitmap bmp6 = BitmapFactory.decodeByteArray(img6, 0, img6.length);
            mImageView6.setImageBitmap(bmp6);

            v = (ImageView) llt.getChildAt(5);
            v.setVisibility(View.VISIBLE);
        }
        else {
            mImageView6.setVisibility(View.GONE);
        }
        byte img7[] = openDatabase("fclothesdress.db","fclothesdtable",contact.getClothId1());
        if (img7!=null) {
            Bitmap bmp7 = BitmapFactory.decodeByteArray(img7, 0, img7.length);
            mImageView7.setImageBitmap(bmp7);

            v = (ImageView) llt.getChildAt(6);
            v.setVisibility(View.VISIBLE);
        }
        else {
            mImageView7.setVisibility(View.GONE);
        }
        byte img8[] = openDatabase("fclothesdress.db","fclothesdtable",contact.getClothId2());
        if (img8!=null) {
            Bitmap bmp8 = BitmapFactory.decodeByteArray(img8, 0, img8.length);
            mImageView6.setImageBitmap(bmp8);
            v = (ImageView) llt.getChildAt(7);
            v.setVisibility(View.VISIBLE);
        }
        else {
            mImageView8.setVisibility(View.GONE);
        }

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
                        if(handler.deleteItem(contact.getId())){
                            Intent intent = new Intent(mContext,LastWorn.class);
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
    }

    private byte [] openDatabase(String dbName,String tableName,int idPic) {
        Cursor cursor;
        SQLiteDatabase myDb;
        if(idPic==-1)   return null;
        if(!doesDatabaseExist(mContext,dbName)) return null;
        int count = llt.getChildCount();
        ImageView v = null;
        for(int i=0; i<count; i++) {
            v = (ImageView) llt.getChildAt(i);
            v.setVisibility(View.GONE);
        }
        myDb = mContext.openOrCreateDatabase(dbName,Context.MODE_PRIVATE, null);
        String query1=" SELECT * from "+tableName;
        cursor = myDb.rawQuery(query1, null);
        if (cursor.moveToFirst()) {
            do {
                if(cursor.getInt(0)==idPic) break;
                System.out.println("FGD"+cursor.getInt(0)+idPic);
            } while (cursor.moveToNext());
        }
        byte returnVal[]= cursor.getBlob(cursor.getColumnIndex("Image_id"));
        System.out.println("f");
        cursor.close();
        return returnVal;
    }
    private static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }

}