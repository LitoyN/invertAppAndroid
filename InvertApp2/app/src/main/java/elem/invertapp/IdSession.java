package elem.invertapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IdSession extends AppCompatActivity implements AdapterView.OnItemClickListener{


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
            "jointedappendages",
            "lobed",
            "graspingantennae",
            "mouthbrushes",
            "lobedabdomen",
            "shortbroadthorax",
            "wingpads",
            "defaultimg",
            "defaultimg",
            "distincthead",
            "antennae",
            "visibleeyes",
            "elongatesucker",
            "modifiedlabium",
            "cases",
            "lateralfilaments",
            "abdominalgills",
            "thoracicgills",
            "abdominalprocesses",
            "abdominalhooks",
            "oneprocess",
            "twoprocesses",
            "threeprocesses",
            "defaultimg",
            "streamlined",
            "patterned",
            "raptoriallegs"
    };

    public static final int numAttributes = 27;
    public static final String[] QUESTIONlabels = new String[numAttributes];
    public static final String questionsPath = "invertquestions04.txt";
    public static final String idsPath = "invertresponses04.txt";

    InputStream dataFileStream;
    InputStream questionFileStream;
    Scanner dataFile;
    Scanner questionFile;

    ArrayList<Identification> identificationList;
    List<String> idList;

    String defaultTnFile = "defaulttn";
    String defaultFullFile = "defaultfull";
    int defaultTn;
    int defaultFull;

    int questionCount;
    int currentAttribute;
    int hideShowStatus;
    boolean sessionEnd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_session);
        defaultTn = getResources().getIdentifier(defaultTnFile, "drawable", getPackageName());
        defaultFull = getResources().getIdentifier(defaultFullFile, "drawable", getPackageName());
        initTrees();
        checkValidQuestion();
    }

    private void initTrees(){
        identificationList = new ArrayList<>();
        idList = new ArrayList<>();
        questionCount = 0;
        hideShowStatus = -1;
        sessionEnd = false;
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

    private void parseData() {

        int counter = 0;
        int colonInt;
        int startInt;

        String tempId;
        String tempIdFull;
        String tempIdTn;
        int imageFullInt;
        int imageTnInt;

        while (dataFile.hasNextLine()) {
            System.out.println("Entered while loop");
            int[] attributeArray = new int[numAttributes];
            startInt = 0;
            tempId = dataFile.next();
            System.out.println("Parsing: " + tempId);

            if (tempId.contains(":")) {

                String tempId2 = "";
                colonInt = tempId.indexOf(':');

                while (tempId.substring(startInt).contains(":")) {
                    tempId2 = tempId2 + tempId.substring(startInt, colonInt + 1) + " ";
                    startInt = colonInt + 1;
                    colonInt = startInt + tempId.substring(startInt).indexOf(':');
                }

                tempId2 = tempId2 + tempId.substring(startInt);
                tempId = tempId2;

            }
            //*****tempId set for idName*****
            tempIdFull = tempId.toLowerCase();
            tempIdFull = tempIdFull.replace(":", "");
            tempIdFull = tempIdFull.replace(" ", "");
            System.out.println(tempIdFull);
            tempIdTn = tempIdFull + "tn";
            //*****tempIdFull set for searchging for imageFull*****
            //*****tempIdTn set for searching for imageTn*****
            imageFullInt = getResources().getIdentifier(tempIdFull, "drawable", getPackageName());
            imageTnInt = getResources().getIdentifier(tempIdTn, "drawable", getPackageName());
            if (imageFullInt == 0) {
                imageFullInt = defaultFull;
            }
            if (imageTnInt == 0) {
                imageTnInt = defaultTn;
            }
            //*****imageFullInt set for imageFull*****
            //*****imageTnInt set for imageTn*****
            for (int i = 0; i < numAttributes; i++) {
                attributeArray[i] = dataFile.nextInt();
            }
            //*****attributeArray set for attributeResponses*****
            Identification identification = new Identification(tempId, imageFullInt, imageTnInt, attributeArray);
            identificationList.add(identification);
        }
        counter = 0;
        while (questionFile.hasNextLine()) {
            QUESTIONlabels[counter] = questionFile.nextLine();
            System.out.println("Question Number: " + counter);
            System.out.println(QUESTIONlabels[counter]);
            counter++;
        }
        System.out.println("DATA PARSING COMPLETE");
        for (int i = 0; i < identificationList.size(); i++) {
            System.out.println(identificationList.get(i).getIdName());
        }
    }

    public void yesButtonClick(View view){
        if(!sessionEnd) {
            for (int j = 0; j < identificationList.size(); j++) {
                if ((identificationList.get(j).getAttributeResponses()[questionCount - 1] == 0)) {
                    System.out.println("Removing: " + identificationList.get(j).getIdName());
                    identificationList.remove(j);
                    j--;
                }//end if attribute false then remove
            }//end for whole idTree
            checkValidQuestion();
            ImageView helpImageView = (ImageView) findViewById(R.id.helpImageView);
            helpImageView.setVisibility(View.INVISIBLE);
        }
    }

    public void noButtonClick(View view){
        if(!sessionEnd) {
            for (int j = 0; j < identificationList.size(); j++) {
                if ((identificationList.get(j).getAttributeResponses()[questionCount - 1] == 1)) {
                    System.out.println("Removing: " + identificationList.get(j).getIdName());
                    identificationList.remove(j);
                    j--;
                }//end if attribute false then remove
            }//end for whole idTree
            checkValidQuestion();
            ImageView helpImageView = (ImageView) findViewById(R.id.helpImageView);
            helpImageView.setVisibility(View.INVISIBLE);
        }
    }

    public void nsButtonClick(View view){
        if(!sessionEnd) {
            questionCount++;
            checkValidQuestion();
            ImageView helpImageView = (ImageView) findViewById(R.id.helpImageView);
            helpImageView.setVisibility(View.INVISIBLE);
        }
    }

    public void showButtonClick(View view){
        hideShowStatus = hideShowStatus*-1;
        showMe();

    }

    public void helpButtonClick(View view){
        if(!sessionEnd) {
            int imageResource = getResources().getIdentifier(imageFiles2[questionCount - 1], "drawable", this.getPackageName());
            System.out.println(imageFiles2[questionCount - 1]);
            System.out.println(questionCount);
            ImageView helpImageView = (ImageView) findViewById(R.id.helpImageView);
            helpImageView.setImageResource(imageResource);
            hideShowStatus = -1;
            showMe();
            if (helpImageView.getVisibility() == View.VISIBLE) {
                helpImageView.setVisibility(View.INVISIBLE);
            } else if (helpImageView.getVisibility() == View.INVISIBLE) {
                helpImageView.setVisibility(View.VISIBLE);
            }
        }
    }

    public void startOverButtonClick(View view){
        initTrees();
        checkValidQuestion();
        ImageView helpImageView = (ImageView) findViewById(R.id.helpImageView);
        helpImageView.setVisibility(View.INVISIBLE);
    }

    public void endSession(){
        sessionEnd = true;
        ((TextView) findViewById(R.id.questionsText)).setText(R.string.endOfId);
        ((Button) findViewById(R.id.showButton)).setText(R.string.hideButtonText);
        hideShowStatus = 1;
        showMe();
    }

    public void checkAnswerFound(){
        //System.out.println("Question Count: " + questionCount);
        if(identificationList.size()<=1 || questionCount>=numAttributes){
            endSession();
        }
    }

    public void showMe(){
        if(hideShowStatus == 1) {
            ((Button) findViewById(R.id.showButton)).setText(R.string.hideButtonText);
            int counter = 0;
            String[] idArray = new String[identificationList.size()];
            int[] imageFileArray = new int[identificationList.size()];
            int[] imageFileArrayFull = new int[identificationList.size()];
            System.out.println("size of idtree: " + identificationList.size());
            while (counter < identificationList.size()) {
                idArray[counter] = identificationList.get(counter).getIdName();
                imageFileArrayFull[counter] = identificationList.get(counter).getImageFull();
                imageFileArray[counter] = identificationList.get(counter).getImageTn();
                counter++;
            }

            GridView gridView = (GridView) findViewById(R.id.idGrid);
            gridView.setAdapter(new GridAdapter(this, identificationList));
            gridView.setOnItemClickListener(this);

            ImageView helpImageView = (ImageView) findViewById(R.id.helpImageView);
            helpImageView.setVisibility(View.INVISIBLE);
        }
        else{
            ((Button) findViewById(R.id.showButton)).setText(R.string.showButtonText);
            GridView gridView = (GridView) findViewById(R.id.idGrid);
            gridView.setAdapter(null);
        }
    }

    public void checkValidQuestion(){
        checkAnswerFound();

        boolean validQuestion=false;

        while(!validQuestion && questionCount<numAttributes){

            for(int i = 0; i < numAttributes; i++) {
                System.out.print(identificationList.get(0).getAttributeResponses()[i] + " ");
            }
            System.out.println();
            currentAttribute= identificationList.get(0).getAttributeResponses()[questionCount];

            //check if question is useful to ask (if it reduces entropy)
            int i=1;
            while(i < identificationList.size() && !validQuestion){
                if(identificationList.get(i).getAttributeResponses()[questionCount]!= currentAttribute){
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this, ResultDialog.class);
        GridViewHolder holder = (GridViewHolder) view.getTag();
        TextView tempId = (TextView) holder.mTextView.getTag();
        System.out.println("name: " + identificationList.get(position).getIdName());
        intent.putExtra("resultImage",identificationList.get(position).getImageFull());
        intent.putExtra("resultID", identificationList.get(position).getIdName());
        startActivity(intent);
    }


}
