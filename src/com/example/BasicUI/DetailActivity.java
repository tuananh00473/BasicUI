package com.example.BasicUI;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.BasicUI.model.Contact;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * User: anhnt
 * Date: 10/9/13
 * Time: 4:14 PM
 */
public class DetailActivity extends Activity
{
    private TextView tvAddress;
    private TextView tvBirthday;
    private TextView tvOnline;
    private TextView tvGender;

    private CheckBox cbShowFriendList;
    private ListView lvFriendList;

    private static final int DATE_DIALOG_ID = 0;
    private static final int TIME_DIALOG_ID = 1;

    Calendar c = Calendar.getInstance();
    int mYear = c.get(Calendar.YEAR);
    int mMonth = c.get(Calendar.MONTH);
    int mDay = c.get(Calendar.DAY_OF_MONTH);
    int mHour = c.get(Calendar.HOUR_OF_DAY);
    int mMinute = c.get(Calendar.MINUTE);

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        TextView tvNameUser = (TextView) findViewById(R.id.detail_tvHiUsername);

        Bundle bundle = getIntent().getExtras();
        tvNameUser.setText("Hi, " + bundle.getString("username"));

        TextView tvLogout = (TextView) findViewById(R.id.detail_tvLogout);
        findViewById(R.id.detail_rbGroup).setVisibility(View.VISIBLE);
        findViewById(R.id.detail_rbGroup).setVisibility(View.GONE);
        tvLogout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                doLogout();
            }
        });

        tvAddress = (TextView) findViewById(R.id.detail_tvAddress);
        showAddress("Ha Noi");
        tvAddress.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                setAddress();
            }
        });

        tvBirthday = (TextView) findViewById(R.id.detail_tvBirthday);
        showDate(mYear, mMonth, mDay);
        tvBirthday.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                setBirthday();
            }
        });

        tvOnline = (TextView) findViewById(R.id.detail_tvOnline);
        showTime(mHour, mMinute);
        tvOnline.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                setOnline();
            }
        });

        RadioButton rbMale = (RadioButton) findViewById(R.id.detail_rbMale);
        rbMale.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showGender("Male");
                findViewById(R.id.detail_rbGroup).setVisibility(View.GONE);
            }
        });
        RadioButton rbFemale = (RadioButton) findViewById(R.id.detail_rbFemale);
        rbFemale.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showGender("Female");
                findViewById(R.id.detail_rbGroup).setVisibility(View.GONE);
            }
        });
        tvGender = (TextView) findViewById(R.id.detail_tvGender);
        showGender("Male");
        tvGender.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showGenderChooser();
            }
        });

        lvFriendList = (ListView) findViewById(R.id.detail_lvFriendList);
        initListViewFriendList();

        cbShowFriendList = (CheckBox) findViewById(R.id.detail_cbShowFriendList);
        cbShowFriendList.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showFriendList();
            }
        });

        if (savedInstanceState != null)
        {
            tvAddress.setText(savedInstanceState.getString("address"));
            tvBirthday.setText(savedInstanceState.getString("birthday"));
            tvOnline.setText(savedInstanceState.getString("online"));
            tvGender.setText(savedInstanceState.getString("gender"));
            cbShowFriendList.setChecked(savedInstanceState.getBoolean("check"));
            showFriendList();
        }
    }

    private void showGender(String gender)
    {
        tvGender.setText("Gender: " + gender);
    }

    private void initListViewFriendList()
    {
        final ArrayList<Contact> list = new ArrayList<Contact>();
        Contact[] contacts = new Contact[4];
        for (int i = 0; i < 4; i++)
        {
            contacts[i] = new Contact();
            contacts[i].setName("Perter");
            contacts[i].setPhone("09123456789");
            list.add(contacts[i]);
        }

        lvFriendList.setAdapter(new ListArrayAdapter(this, list));
    }

    private void showGenderChooser()
    {
        findViewById(R.id.detail_rbGroup).setVisibility(View.VISIBLE);
    }

    private void setOnline()
    {
        showDialog(TIME_DIALOG_ID);
    }

    private void setBirthday()
    {
        showDialog(DATE_DIALOG_ID);
    }

    private void setAddress()
    {
        Spinner spAddress = (Spinner) findViewById(R.id.detail_spAddress);
        spAddress.performClick();
        spAddress.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                showAddress(adapterView.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
            }
        });
    }

    private void showAddress(String address)
    {
        tvAddress.setText("Address: " + address);
    }

    private void doLogout()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        DetailActivity.this.finish();
        startActivity(intent);
    }

    private void showFriendList()
    {
        if (cbShowFriendList.isChecked())
        {
            lvFriendList.setVisibility(View.VISIBLE);
        }
        else
        {
            lvFriendList.setVisibility(View.GONE);
        }
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener()
    {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
        {
            showDate(year, monthOfYear, dayOfMonth);
        }
    };

    private void showDate(int year, int monthOfYear, int dayOfMonth)
    {
        tvBirthday.setText("Birthday: " + dayOfMonth + "/" + ++monthOfYear + "/" + year);
    }

    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener()
    {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute)
        {
            showTime(hour, minute);
        }
    };

    private void showTime(int hour, int minute)
    {
        if (hour > 12)
        {
            hour -= 12;
            tvOnline.setText("Online: " + hour + ":" + minute + " PM");
        }
        else if (hour == 12)
        {
            tvOnline.setText("Online: " + hour + ":" + minute + " PM");
        }
        else
        {
            tvOnline.setText("Online: " + hour + ":" + minute + " AM");
        }
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        switch (id)
        {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, mTimeSetListener, mHour, mMinute, false);
        }
        return null;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString("address", tvAddress.getText().toString());
        outState.putString("birthday", tvBirthday.getText().toString());
        outState.putString("online", tvOnline.getText().toString());
        outState.putString("gender", tvGender.getText().toString());
        outState.putBoolean("check", cbShowFriendList.isChecked());
    }
}