package com.birget.xdrjve;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Reserve_adapter  extends ArrayAdapter<ReserveModel> {
    private Activity context;
    private List<ReserveModel> artistList;

    public Reserve_adapter(Activity context, List<ReserveModel> artistList) {
        super(context, R.layout.reserve_layout, artistList);
        this.context = context;
        this.artistList = artistList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewitem = inflater.inflate(R.layout.reserve_layout, null, true);
        final ReserveModel model = artistList.get(position);
        final TextView Title1 = (TextView)listViewitem.findViewById(R.id.title1);
        final TextView FromPlace = (TextView)listViewitem.findViewById(R.id.fromplace);
        final TextView ToPlace = (TextView)listViewitem.findViewById(R.id.toplace);
        String time = model.getTime();
        String month1 = model.getMonth();
        String day = model.getDay();
        String fromplace = model.getFromplace();
        String toplace = model.getToplace();
        if(month1.matches("1")){
            final String month = "Yanvar";
            String title1 =day + " "+month+" "+"-"+" "+time;
            Title1.setText(title1);
            FromPlace.setText(fromplace);
            ToPlace.setText(toplace);
        }
        if(month1.matches("2")){
            final String month = "Fevral";
            String title1 =day + " "+month+" "+"-"+" "+time;
            Title1.setText(title1);
            FromPlace.setText(fromplace);
            ToPlace.setText(toplace);
        }
        if(month1.matches("3")){
            final String month = "Mart";
            String title1 =day + " "+month+" "+"-"+" "+time;
            Title1.setText(title1);
            FromPlace.setText(fromplace);
            ToPlace.setText(toplace);
        }
        if(month1.matches("4")){
            final String month = "Aprel";
            String title1 =day + " "+month+" "+"-"+" "+time;
            Title1.setText(title1);
            FromPlace.setText(fromplace);
            ToPlace.setText(toplace);
        }
        if(month1.matches("5")){
            final String month = "May";
            String title1 =day + " "+month+" "+"-"+" "+time;
            Title1.setText(title1);
            FromPlace.setText(fromplace);
            ToPlace.setText(toplace);
        }
        if(month1.matches("6")){
            final String month = "Iyun";
            String title1 =day + " "+month+" "+"-"+" "+time;
            Title1.setText(title1);
            FromPlace.setText(fromplace);
            ToPlace.setText(toplace);
        }
        if(month1.matches("7")){
            final String month = "Iyul";
            String title1 =day + " "+month+" "+"-"+" "+time;
            Title1.setText(title1);
            FromPlace.setText(fromplace);
            ToPlace.setText(toplace);
        }
        if(month1.matches("8")){
            final String month = "Avqust";
            String title1 =day + " "+month+" "+"-"+" "+time;
            Title1.setText(title1);
            FromPlace.setText(fromplace);
            ToPlace.setText(toplace);
        }
        if(month1.matches("9")){
            final String month = "Sentyabr";
            String title1 =day + " "+month+" "+"-"+" "+time;
            Title1.setText(title1);
            FromPlace.setText(fromplace);
            ToPlace.setText(toplace);
        }
        if(month1.matches("10")){
            final String month = "Oktyabr";
            String title1 =day + " "+month+" "+"-"+" "+time;
            Title1.setText(title1);
            FromPlace.setText(fromplace);
            ToPlace.setText(toplace);
        }
        if(month1.matches("11")){
            final String month = "Noyabr";
            String title1 =day + " "+month+" "+"-"+" "+time;
            Title1.setText(title1);
            FromPlace.setText(fromplace);
            ToPlace.setText(toplace);
        }
        if(month1.matches("12")){
            final String month = "Dekabr";
            String title1 =day + " "+month+" "+"-"+" "+time;
            Title1.setText(title1);
            FromPlace.setText(fromplace);
            ToPlace.setText(toplace);
        }
        return listViewitem;
    }
}