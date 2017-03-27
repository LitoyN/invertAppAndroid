package elem.invertapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.ArrayList;

/**
 * Created by elliot on 3/28/2016.
 */
public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Identification> idList;


    public GridAdapter(Context c, ArrayList<Identification> identificationList) {

        //super(c, R.layout.gridcelllayout, R.id.gridIdTextView, idArray);
        mContext = c;
        idList = identificationList;
    }

    @Override
    public int getCount() {
        return idList.size();
    }

    @Override
    public Object getItem(int position) {
        return idList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View cell = convertView;
        GridViewHolder holder = null;
        if(cell == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cell = inflater.inflate(R.layout.gridcelllayout, parent, false);
            holder = new GridViewHolder(cell);
            cell.setTag(holder);
        }
        else{
            holder = (GridViewHolder) cell.getTag();
        }

        Identification temp = idList.get(position);
        holder.mTextView.setText(temp.getIdName());
        holder.mImageButton.setImageResource(temp.getImageTn());

        return cell;
    }


}
