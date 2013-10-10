package com.example.BasicUI;

import android.app.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.Date;
import java.util.List;

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
    private RadioButton rbMale;
    private RadioButton rbFemale;

    private CheckBox cbShowFriendList;
    private TextView tvName1;
    private TextView tvPhone1;
    private TextView tvName2;
    private TextView tvPhone2;
    private TextView tvName3;
    private TextView tvPhone3;
    private TextView tvName4;
    private TextView tvPhone4;

    private static final int DATE_DIALOG_ID = 0;
    private static final int TIME_DIALOG_ID = 1;
    private static final int ADDRESS_DIALOG_ID = 2;

    private List addressList;

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
        rbMale = (RadioButton) findViewById(R.id.detail_rbMale);
        rbMale.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                tvGenderContent.setText(R.string.male);
                findViewById(R.id.detail_rbGroup).setVisibility(View.GONE);
                //rbMale.setVisibility(-1);
                //rbFemale.setVisibility(-1);
            }
        });
        rbFemale = (RadioButton) findViewById(R.id.detail_rbFemale);
        rbFemale.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                tvGenderContent.setText(R.string.female);
                findViewById(R.id.detail_rbGroup).setVisibility(View.GONE);
                //rbMale.setVisibility(-1);
                //rbFemale.setVisibility(-1);
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

        tvName1 = (TextView) findViewById(R.id.detail_name1);
        tvPhone1 = (TextView) findViewById(R.id.detail_phone1);
        tvName2 = (TextView) findViewById(R.id.detail_name2);
        tvPhone2 = (TextView) findViewById(R.id.detail_phone2);
        tvName3 = (TextView) findViewById(R.id.detail_name3);
        tvPhone3 = (TextView) findViewById(R.id.detail_phone3);
        tvName4 = (TextView) findViewById(R.id.detail_name4);
        tvPhone4 = (TextView) findViewById(R.id.detail_phone4);

        cbShowFriendList = (CheckBox) findViewById(R.id.detail_cbShowFriendList);
        cbShowFriendList.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showFriendList();
            }
        });
    }

    private void showGenderChooser()
    {
        findViewById(R.id.detail_rbGroup).setVisibility(View.VISIBLE);
        //rbMale.setVisibility(0);
        //rbFemale.setVisibility(0);
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
        showDialog(ADDRESS_DIALOG_ID);
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
            tvName1.setVisibility(0);
            tvPhone1.setVisibility(0);
            tvName2.setVisibility(0);
            tvPhone2.setVisibility(0);
            tvName3.setVisibility(0);
            tvPhone3.setVisibility(0);
            tvName4.setVisibility(0);
            tvPhone4.setVisibility(0);
        }
        else
        {
            tvName1.setVisibility(-1);
            tvPhone1.setVisibility(-1);
            tvName2.setVisibility(-1);
            tvPhone2.setVisibility(-1);
            tvName3.setVisibility(-1);
            tvPhone3.setVisibility(-1);
            tvName4.setVisibility(-1);
            tvPhone4.setVisibility(-1);
        }
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener()
    {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
        {
            tvBirthdayContent.setText(dayOfMonth + "/" + ++monthOfYear + "/" + year);
        }
    };
    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener()
    {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int second)
        {
            if (hour > 12)
            {
                hour -= 12;
                tvOnlineContent.setText(hour + " : " + second + " PM");
            }
            else if (hour == 12)
            {
                tvOnlineContent.setText(hour + " : " + second + " PM");
            }
            else
            {
                tvOnlineContent.setText(hour + " : " + second + " AM");
            }
        }
    };

    private Dialog addressDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
        final CharSequence[] items = {"Ha Noi", "Ha Nam", "Ha Tay", "Quang Ninh", "Bac Ninh"};
        builder.setItems(items, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int index)
            {
                tvAddressContent.setText(items[index]);
            }
        });
        return builder.create();
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        Date date = new Date();
        switch (id)
        {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, date.getYear(), date.getMonth(), date.getDay());
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, mTimeSetListener, date.getHours(), date.getSeconds(), false);
            case ADDRESS_DIALOG_ID:
                return addressDialog();
        }
        return null;
    }
}