package hamood.malak.malakproject1.MyData;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/**
 * todo target:arrange data source by listview
 */
import hamood.malak.malakproject1.MyUtils.MyTask;
import hamood.malak.malakproject1.R;

public class MyTaskAdAdapter extends ArrayAdapter<MyTask> {
    /**
     * constructor
     * @param context the activity of(app) that this adopter belong to.
     * @param resource XML design of items
     */
    //2 fix error
    public MyTaskAdAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    /**
     * building the single item view
     * @param position index item in the listview
     * @param convertView item view
     * @param parent listview
     * @return
     */
    @NonNull
    @Override
    //3. overriding getView
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        //3.1
        View v= LayoutInflater.from(getContext()).inflate(R.layout.item_tasl_view,parent,false);

        //3.2 find view by id
        TextView tvTitle=v.findViewById(R.id.itmTvTittle);
        TextView tvImportant=v.findViewById(R.id.itemTvImportant);
        TextView tvNecessary=v.findViewById(R.id.itemTvNecessary);
        TextView tvSubject=v.findViewById(R.id.itemTvSubject);

        ImageButton btnDelete=v.findViewById(R.id.imageDelete);
        ImageButton btnCall=v.findViewById(R.id.imageCall);
        ImageButton btnEdit=v.findViewById(R.id.imageEdit);

        //3.3 get the soutable task object
        MyTask task=getItem(position);

        //3.4 connect the dat to the view (view the data using item views)
        tvTitle.setText(task.getTitle());
        tvSubject.setText(task.getSub());

        switch (task.getImportant()){
            case 5:tvImportant.setBackgroundColor(Color.YELLOW);break;
            case 4:tvImportant.setBackgroundColor(Color.BLUE);break;
            case 3:tvImportant.setBackgroundColor(Color.CYAN);break;
            case 2:tvImportant.setBackgroundColor(Color.MAGENTA);break;
            case 1:tvImportant.setBackgroundColor(Color.rgb(0,200,200));break;
        }
        switch (task.getNeccessary()){
            case 5:tvNecessary.setBackgroundColor(Color.YELLOW);break;
            case 4:tvNecessary.setBackgroundColor(Color.BLUE);break;
            case 3:tvNecessary.setBackgroundColor(Color.CYAN);break;
            case 2:tvNecessary.setBackgroundColor(Color.MAGENTA);break;
            case 1:tvNecessary.setBackgroundColor(Color.rgb(0,200,200));break;
        }
        return v;
    }
}
