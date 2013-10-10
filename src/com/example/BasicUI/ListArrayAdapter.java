package com.example.BasicUI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.BasicUI.model.Contact;

import java.util.ArrayList;

/**
 * User: anhnt
 * Date: 10/10/13
 * Time: 11:41 AM
 */
public class ListArrayAdapter extends ArrayAdapter<Contact>
{
    private Context context;
    private ArrayList<Contact> contacts;

    public ListArrayAdapter(Context context, ArrayList<Contact> contacts)
    {
        super(context, 0, contacts);
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.items, parent, false);
        }

        Contact contact = contacts.get(position);

        TextView tvName = (TextView) convertView.findViewById(R.id.item_tvNameContent);
        tvName.setText(contact.getName());

        TextView tvPhone = (TextView) convertView.findViewById(R.id.item_tvPhoneContent);
        tvPhone.setText(contact.getPhone());

        return convertView;
    }
}
