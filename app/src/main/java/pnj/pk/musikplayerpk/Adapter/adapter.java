package pnj.pk.musikplayerpk.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import pnj.pk.musikplayerpk.Model.user;
import pnj.pk.musikplayerpk.R;

public class adapter extends ArrayAdapter<user> {
    Context context;
    int resource;

    public adapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context =context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Holder holder;

        if(convertView==null){
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(resource, parent,
                    false);

            holder.editNama = convertView.findViewById(R.id.editNama);
            holder.editPass = convertView.findViewById(R.id.editpass);
            holder.editUser = convertView.findViewById(R.id.editUser);
            holder.editPassres = convertView.findViewById(R.id.editPassRes);
            holder.editUserres = convertView.findViewById(R.id.editUserRes);


            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }


        holder.editNama.setText(getItem(position).getNama());
        holder.editUser.setText(getItem(position).getUser());
        holder.editPass.setText(getItem(position).getPass());
        holder.editUserres.setText(getItem(position).getUser());
        holder.editPassres.setText(getItem(position).getPass());


        return convertView;
    }

    class Holder {
        EditText editNama,editUser,editPass,editUserres,editPassres;
    }
}
