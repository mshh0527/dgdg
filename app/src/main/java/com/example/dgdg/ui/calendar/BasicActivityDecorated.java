package com.example.dgdg.ui.calendar;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dgdg.R;
import com.example.dgdg.ui.calendar.decorators.EventDecorator;
import com.example.dgdg.ui.calendar.decorators.MySelectorDecorator;
import com.example.dgdg.ui.calendar.decorators.OneDayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.threeten.bp.LocalDate;
import org.threeten.bp.Month;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BasicActivityDecorated extends AppCompatActivity implements OnDateSelectedListener {

  private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy MMM d (EEE)");

  @BindView(R.id.customCalendarView)
  MaterialCalendarView widget;

  @BindView(R.id.dateTextView)
  TextView textView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_calendar);
    ButterKnife.bind(this);

    textView.setText("No Selection");

    widget.setOnDateChangedListener(this);
    widget.setShowOtherDates(MaterialCalendarView.SHOW_ALL);

    final LocalDate instance = LocalDate.now();
    widget.setSelectedDate(instance);

    final LocalDate min = LocalDate.of(instance.getYear(), Month.JANUARY, 1);
    final LocalDate max = LocalDate.of(instance.getYear(), Month.DECEMBER, 31);

    widget.state().edit().setMinimumDate(min).setMaximumDate(max).commit();

    widget.addDecorators(
            new MySelectorDecorator(this),
            oneDayDecorator
    );

    new ApiSimulator().executeOnExecutor(Executors.newSingleThreadExecutor());
  }

  @Override
  public void onDateSelected(
          @NonNull MaterialCalendarView widget,
          @NonNull CalendarDay date,
          boolean selected) {
    //If you change a decorate, you need to invalidate decorators
    oneDayDecorator.setDate(date.getDate());
    widget.invalidateDecorators();
    textView.setText(selected ? FORMATTER.format(date.getDate()) : "No Selection");
  }

  /**
   * Simulate an API call to show how to add decorators
   */
  private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {

    @Override
    protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      LocalDate temp = LocalDate.now().minusMonths(2);
      final ArrayList<CalendarDay> dates = new ArrayList<>();
      for (int i = 0; i < 30; i++) {
        final CalendarDay day = CalendarDay.from(temp);
        dates.add(day);
        temp = temp.plusDays(5);
      }

      return dates;
    }

    @Override
    protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
      super.onPostExecute(calendarDays);

      if (isFinishing()) {
        return;
      }

      widget.addDecorator(new EventDecorator(Color.RED, calendarDays));
    }
  }
}
