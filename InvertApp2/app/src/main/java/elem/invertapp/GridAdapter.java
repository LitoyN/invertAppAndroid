package elem.invertapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by elliot on 3/28/2016.
 */

class Identification{
    int imageTn;
    String idName;
    int imageFull;

    Identification(String idName, int imageTn, int imageFull){
        this.idName = idName;
        this.imageTn = imageTn;
        this.imageFull = imageFull;
    }
}

public class GridAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private int[] imageArray;
    private String[] idArray;
    private int[]imageArrayFull;
    private ArrayList<Identification> idList;


    public GridAdapter(Context c, String[] idArray, int[] imageArray, int[] imageArrayFull) {

        super(c, R.layout.gridcelllayout, R.id.gridIdTextView, idArray);
        mContext = c;
        this.imageArray = imageArray;
        this.idArray = idArray;
        this.imageArrayFull = imageArrayFull;
        idList = new ArrayList<>();
        for(int i = 0; i < idArray.length; i++){
            Identification tempId = new Identification(idArray[i], imageArray[i], imageArrayFull[i]);
            idList.add(tempId);
        }
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
        holder.mTextView.setText(temp.idName);
        holder.mImageButton.setImageResource(temp.imageTn);

        return cell;
    }


}
