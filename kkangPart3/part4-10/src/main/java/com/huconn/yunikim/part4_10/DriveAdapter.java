package com.huconn.yunikim.part4_10;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DriveAdapter extends ArrayAdapter<DriveVO>
{
    Context context;
    int resId;
    ArrayList<DriveVO> datas;

    public DriveAdapter(Context context, int resId, ArrayList<DriveVO> datas)
    {
        super(context, resId);
        this.context = context;
        this.resId = resId;
        this.datas = datas;
    }

    @Override
    public int getCount()
    {
        return datas.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            //LayoutInflater inflater = LayoutInflater.from(context);
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, null);
            DriveHolder holder = new DriveHolder(convertView);
            convertView.setTag(holder);
        }

        DriveHolder holder = (DriveHolder)convertView.getTag();

        ImageView typeImageView = holder.typeImageView;
        TextView titleView = holder.titleView;
        TextView dateView = holder.dateView;
        ImageView menuImageView = holder.menuImageView;

        final DriveVO vo = datas.get(position);

        titleView.setText(vo.title);
        dateView.setText(vo.date);

        typeImageView.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),
                (vo.type.equals("doc"))?R.drawable.ic_type_doc:(vo.type.equals("file"))?
                        R.drawable.ic_type_file:R.drawable.ic_type_image, null));

        menuImageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast toast = Toast.makeText(context, vo.title+" menu click", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        return convertView;
    }
}
