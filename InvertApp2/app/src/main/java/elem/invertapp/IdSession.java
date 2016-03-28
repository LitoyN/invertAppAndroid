package elem.invertapp;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

public class IdSession extends AppCompatActivity {

    public static final String[] imageFiles = {
            "wingpad.jpg",
            "slenderbody.jpg",
            "stoutbody.jpg",
            "longbody.jpg",
            "defaultimg.jpg",
            "defaultimg.jpg",
            "defaultimg.jpg",
            "externalgills.jpg",
            "abdominalgills.jpg",
            "thoracicgills.jpg",
            "filamentousgills.jpg",
            "plategills.jpg",
            "distincthead.jpg",
            "antennae.jpg",
            "jointedappendages.jpg",
            "abdominalprocesses.jpg",
            "terminalhairs.jpg",
            "oneprocess.jpg",
            "twoprocesses.jpg",
            "threeprocesses.jpg",
            "plusprocesses.jpg",
            "tailprocesses.jpg",
            "defaultimg.jpg",
            "leafprocesses.jpg",
            "defaultimg.jpg",
            "patterned.jpg",
            "distincteye.jpg",
            "fanhead.jpg",
            "softbody.jpg",
            "hardbody.jpg",
            "suckingmouth.jpg",
            "labium.jpg",
            "pairedclaws.jpg",
            "singleclaw.jpg",
            "cases.jpg",
            "deeplylobed.jpg",
            "broadthorax.jpg",
            "graspingantennae.jpg",
            "defaultimg.jpg"

    };
    public static final String[] imageFiles2 = {
            "wingpad",
            "slenderbody",
            "stoutbody",
            "longbody",
            "defaultimg",
            "defaultimg",
            "defaultimg",
            "externalgills",
            "abdominalgills",
            "thoracicgills",
            "filamentousgills",
            "plategills",
            "distincthead",
            "antennae",
            "jointedappendages",
            "abdominalprocesses",
            "terminalhairs",
            "oneprocess",
            "twoprocesses",
            "threeprocesses",
            "plusprocesses",
            "tailprocesses",
            "defaultimg",
            "leafprocesses",
            "defaultimg",
            "patterned",
            "distincteye",
            "fanhead",
            "softbody",
            "hardbody",
            "suckingmouth",
            "labium",
            "pairedclaws",
            "singleclaw",
            "cases",
            "deeplylobed",
            "broadthorax",
            "graspingantennae",
            "defaultimg"

    };

    private Integer[] resultImages = {
            R.drawable.tn0, R.drawable.tn1,R.drawable.tn2,
            R.drawable.tn3, R.drawable.tn4,
            R.drawable.tn5, R.drawable.tn6,
            R.drawable.tn7, R.drawable.tn8,
            R.drawable.tn9, R.drawable.tn10,
            R.drawable.tn11, R.drawable.tn12,
            R.drawable.tn13, R.drawable.tn14,
            R.drawable.tn15, R.drawable.tn16,
            R.drawable.tn6, R.drawable.tn16,
            R.drawable.tn7, R.drawable.tn8,
            R.drawable.tn9, R.drawable.tn10,
            R.drawable.tn11, R.drawable.tn12,
            R.drawable.tn13, R.drawable.tn14,
            R.drawable.tn15, R.drawable.tn16
    };
    public static final int numAttributes = 38;
    public static final String[] QUESTIONlabels = new String[numAttributes];
    public static final String filePath = "HelpImages2/";
    public static final String questionsPath = "questions.txt";
    public static final String idsPath = "invertAppInfoMSDOS3.txt";
    InputStream dataFileStream;
    InputStream questionFileStream;
    Scanner dataFile;
    Scanner questionFile;
    IdList idTree;
    AttributeList attributeTree;
    int questionCount;
    int currentAttribute;
    int hideShowStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_id_session);
        questionCount = 0;
        hideShowStatus = -1;
        initTrees();
        checkValidQuestion();
    }

    private void initTrees(){
        idTree = new IdList();
        attributeTree = new AttributeList();
        try{
            dataFileStream = getAssets().open(idsPath);

        }
        catch(Exception e){
            System.out.println("DATA FILE NOT FOUND");
        }
        try{
            questionFileStream = getAssets().open(questionsPath);
        }
        catch (Exception e){
            System.out.println("QUESTIONS FILE NOT FOUND");
        }
        dataFile = new Scanner(dataFileStream);
        questionFile= new Scanner(questionFileStream);
        parseData();
    }

    private void parseData(){
        int[] attributeInt= new int[numAttributes];
        int q = 0;
        while(dataFile.hasNextLine()){
            idTree.add(dataFile.next(), resultImages[q]); //instad, lets do dataFile.next() again, coding the image file into the data file
            //System.out.println(idTree.get(q));
            for(int i=0; i<numAttributes; i++){
                attributeInt[i]=dataFile.nextInt();
            }
            attributeTree.add(attributeInt);
            q++;
        }
        q=0;
        while(questionFile.hasNextLine()){
            QUESTIONlabels[q]=questionFile.nextLine();
            System.out.println(QUESTIONlabels[q]);
            q++;
        }
        System.out.println("DATA PARSING COMPLETE");
    }

    public void yesButtonClick(View view){
        for(int j=0;j<idTree.size();j++){
            if((attributeTree.get(j)[questionCount-1]==0)){
                System.out.println("Removing: " + idTree.get(j));
                attributeTree.remove(j);
                idTree.remove(j);
                j--;
            }//end if attribute false then remove
        }//end for whole idTree
        checkValidQuestion();
        ImageView helpImageView = (ImageView) findViewById(R.id.helpImageView);
        helpImageView.setVisibility(View.INVISIBLE);
    }

    public void noButtonClick(View view){
        for(int j=0;j<idTree.size();j++){
            if((attributeTree.get(j)[questionCount-1]==1)){
                System.out.println("Removing: " + idTree.get(j));
                attributeTree.remove(j);
                idTree.remove(j);
                j--;
            }//end if attribute false then remove
        }//end for whole idTree
        checkValidQuestion();
        ImageView helpImageView = (ImageView) findViewById(R.id.helpImageView);
        helpImageView.setVisibility(View.INVISIBLE);
    }

    public void nsButtonClick(View view){
        questionCount++;
        checkValidQuestion();
        ImageView helpImageView = (ImageView) findViewById(R.id.helpImageView);
        helpImageView.setVisibility(View.INVISIBLE);
    }

    public void showButtonClick(View view){
        hideShowStatus = hideShowStatus*-1;
        showMe();

    }

    public void helpButtonClick(View view){

        int imageResource = getResources().getIdentifier(imageFiles2[questionCount-1],"drawable",this.getPackageName());
        System.out.println(imageFiles2[questionCount-1]);
        System.out.println(questionCount);
        ImageView helpImageView = (ImageView) findViewById(R.id.helpImageView);
        helpImageView.setImageResource(imageResource);
        hideShowStatus = -1;
        showMe();
        if(helpImageView.getVisibility() == View.VISIBLE) {
            helpImageView.setVisibility(View.INVISIBLE);
        }
        else if(helpImageView.getVisibility() == View.INVISIBLE){
            helpImageView.setVisibility(View.VISIBLE);
        }

    }

    public void startOverButtonClick(View view){
        questionCount=0;
        initTrees();
        checkValidQuestion();
        ImageView helpImageView = (ImageView) findViewById(R.id.helpImageView);
        helpImageView.setVisibility(View.INVISIBLE);
    }

    public void endSession(){
        ((TextView) findViewById(R.id.questionsText)).setText(R.string.endOfId);
        ((Button) findViewById(R.id.showButton)).setText(R.string.hideButtonText);
        hideShowStatus = 1;
        showMe();
    }

    public void checkAnswerFound(){
        //System.out.println("Question Count: " + questionCount);
        if(idTree.size()<=1 || questionCount>=numAttributes){
            endSession();
        }
    }

    public void showMe(){
        if(hideShowStatus == 1) {
            ((Button) findViewById(R.id.showButton)).setText(R.string.hideButtonText);
            int counter = 0;
            String[] idArray = new String[idTree.size() + 1];
            Integer[] imageFileArray = new Integer[idTree.size() + 1];
            //idArray[counter] = "Possible Identifications: ";
            //imageFileArray[counter] = 1;
            //counter++;
            while (counter < idTree.size() + 1) {
                idArray[counter] = idTree.get(counter - 1);
                imageFileArray[counter] = idTree.getImage(counter - 1);
                //System.out.println(idArray[counter - 1]);
                counter++;
            }

            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listview_layout1, idArray);
            GridView gridView = (GridView) findViewById(R.id.idGrid);
            gridView.setAdapter(new ImageAdapter(this, imageFileArray));
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    Toast.makeText(IdSession.this, "" + position,
                            Toast.LENGTH_SHORT).show();
                }
            });
            //ListView listView = (ListView) findViewById(R.id.idList);
            //gridView.setAdapter(adapter);
            //listView.setAdapter(adapter);
            ImageView helpImageView = (ImageView) findViewById(R.id.helpImageView);
            helpImageView.setVisibility(View.INVISIBLE);
        }
        else{
            ((Button) findViewById(R.id.showButton)).setText(R.string.showButtonText);
            GridView gridView = (GridView) findViewById(R.id.idGrid);
            //ListView listView = (ListView) findViewById(R.id.idList);
            gridView.setAdapter(null);
            //listView.setAdapter(null);
            //listView.setOnItemClickListener();
        }
    }

    public void updateIdList(){

    }

    public void checkValidQuestion(){
        checkAnswerFound();

        boolean validQuestion=false;

        while(!validQuestion && questionCount<numAttributes){

            currentAttribute= attributeTree.get(0)[questionCount];

            //check if question is useful to ask (if it reduces entropy)
            int i=0;
            while(i<idTree.size() && !validQuestion){
                if(attributeTree.get(i)[questionCount]!=currentAttribute){
                    validQuestion=true;
                }
                i++;
            }//end check if question useful

            if(validQuestion){
                ((TextView) findViewById(R.id.questionsText)).setText(QUESTIONlabels[questionCount]);
            }
            questionCount++;
        }//while answer not found and question count less than number of attributes

        //System.out.println("Question Number: " + questionCount);
        showMe();

        if(!validQuestion){
            checkAnswerFound();
        } // may not be needed... if checkAnswerFound is working correctly
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_id_session, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
