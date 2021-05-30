package com.example.dgdg.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;


import com.example.dgdg.R;

public class CalendarFragment extends Fragment {

    private CalendarViewModel calendarViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendarViewModel = new ViewModelProvider(this).get(CalendarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);
        //final CalendarView calendarView = root.findViewById(R.id.customCalendarView);
        final TextView dateTextView = root.findViewById(R.id.dateTextView);
        final EditText contextEditText = root.findViewById(R.id.contextEditText);
        /*
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                dateTextView.setVisibility(View.VISIBLE);
                contextEditText.setVisibility(View.VISIBLE);
                dateTextView.setText(String.format("%d월 %d일",month+1,dayOfMonth));
                contextEditText.setText("");
            }
        });
        // TODO: CalendarView -> Custom Calender
        */
        return root;
    }
}