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
    private TextView tvAddressContent;
    private TextView tvBirthdayContent;
    private TextView tvOnlineContent;
    private TextView tvGenderContent;

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
        TextView tvNameUser = (TextView) findViewById(R.id.detail_tvUsername);

        Bundle bundle = getIntent().getExtras();
        tvNameUser.setText(bundle.getString("username"));

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

        tvAddressContent = (TextView) findViewById(R.id.detail_tvAddressContent);
        TextView tvAddress = (TextView) findViewById(R.id.detail_tvAddress);
        tvAddress.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                setAddress();
            }
        });

        tvBirthdayContent = (TextView) findViewById(R.id.detail_tvBirthdayContent);
        showDate(mYear, mMonth, mDay);
        TextView tvBirthday = (TextView) findViewById(R.id.detail_tvBirthday);
        tvBirthday.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                setBirthday();
            }
        });

        tvOnlineContent = (TextView) findViewById(R.id.detail_tvOnlineContent);
        showTime(mHour, mMinute);
        TextView tvOnline = (TextView) findViewById(R.id.detail_tvOnline);
        tvOnline.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                setOnline();
            }
        });

        tvGenderContent = (TextView) findViewById(R.id.detail_tvGenderContent);
        RadioButton rbMale = (RadioButton) findViewById(R.id.detail_rbMale);
        rbMale.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                tvGenderContent.setText(R.string.male);
                findViewById(R.id.detail_rbGroup).setVisibility(View.GONE);
            }
        });
        RadioButton rbFemale = (RadioButton) findViewById(R.id.detail_rbFemale);
        rbFemale.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                tvGenderContent.setText(R.string.female);
                findViewById(R.id.detail_rbGroup).setVisibility(View.GONE);
            }
        });
        TextView tvGender = (TextView) findViewById(R.id.detail_tvGender);
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
            tvAddressContent.setText(savedInstanceState.getString("address"));
            tvBirthdayContent.setText(savedInstanceState.getString("birthday"));
            tvOnlineContent.setText(savedInstanceState.getString("online"));
            tvGenderContent.setText(savedInstanceState.getString("gender"));
            cbShowFriendList.setChecked(savedInstanceState.getBoolean("check"));
            showFriendList();
        }
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
                tvAddressContent.setText(adapterView.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
            }
        });
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
        tvBirthdayContent.setText(dayOfMonth + "/" + ++monthOfYear + "/" + year);
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
            tvOnlineContent.setText(hour + " : " + minute + " PM");
        }
        else if (hour == 12)
        {
            tvOnlineContent.setText(hour + " : " + minute + " PM");
        }
        else
        {
            tvOnlineContent.setText(hour + " : " + minute + " AM");
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
        outState.putString("address", tvAddressContent.getText().toString());
        outState.putString("birthday", tvBirthdayContent.getText().toString());
        outState.putString("online", tvOnlineContent.getText().toString());
        outState.putString("gender", tvGenderContent.getText().toString());
        outState.putBoolean("check", cbShowFriendList.isChecked());
    }
}