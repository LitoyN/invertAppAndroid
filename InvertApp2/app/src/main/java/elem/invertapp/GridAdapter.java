package elem.invertapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by elliot on 3/28/2016.
 */
public class GridAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private int[] imageArray;
    private String[] idArray;
    //private int[] mThumbIds;
    //private String[] idArray;

    public GridAdapter(Context c, String[] idArray, int[] imageArray) {

        super(c, R.layout.gridcelllayout, R.id.gridIdTextView, idArray);
        mContext = c;
        this.imageArray = imageArray;
        this.idArray = idArray;
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

        holder.mTextView.setText(idArray[position]);
        holder.mImageButton.setImageResource(imageArray[position]);

        return cell;
    }


}
