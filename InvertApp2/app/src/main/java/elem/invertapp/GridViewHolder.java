package elem.invertapp;

import android.media.Image;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by elliot on 3/28/2016.
 */
public class GridViewHolder {

    ImageView mImageButton;
    TextView mTextView;

    GridViewHolder(View v){
        mImageButton = (ImageView) v.findViewById(R.id.gridIdImageTn);
        mTextView = (TextView) v.findViewById(R.id.gridIdTextView);

    }
}
