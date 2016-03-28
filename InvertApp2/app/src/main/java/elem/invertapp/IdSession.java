package elem.invertapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
    List<String> idList = new ArrayList<String>();
    int[] idImagesTn;
    int[] idImagesFull;
    String defaultTnFile = "defaulttn";
    String defaultFullFile = "defaultfull";
    int defaultTn;
    int defaultFull;
    int questionCount;
    int currentAttribute;
    int hideShowStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_id_session);
        defaultTn = getResources().getIdentifier(defaultTnFile, "drawable", getPackageName());
        defaultFull = getResources().getIdentifier(defaultFullFile, "drawable", getPackageName());
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
        int counter = 0;
        int colonInt;
        int startInt;
        String tempId ="";
        String tempIdFull;
        String tempIdTn;
        while(dataFile.hasNextLine()){

            startInt = 0;
            tempId = dataFile.next();

            if(tempId.contains(":")){

                String tempId2 = "";
                colonInt = tempId.indexOf(':');

                while(tempId.substring(startInt).contains(":")){
                    tempId2 = tempId2 + tempId.substring(startInt, colonInt + 1) + " ";
                    startInt = colonInt + 1;
                    colonInt = startInt + tempId.substring(startInt).indexOf(':');
                }

                tempId2 = tempId2 + tempId.substring(startInt);
                tempId = tempId2;

            }

            idList.add(tempId);

            for(int i=0; i<numAttributes; i++){
                attributeInt[i]=dataFile.nextInt();
            }

            attributeTree.add(attributeInt);
            counter++;

        }

        idImagesTn = new int[counter];
        idImagesFull = new int[counter];
        for(int i = 0; i < counter; i++){
            tempId = idList.get(i);
            tempIdFull = tempId.toLowerCase();
            tempIdFull = tempIdFull.replace(":","");
            tempIdFull = tempIdFull.replace(" ", "");
            tempIdTn = tempIdFull + "tn";
            System.out.println("tempid: " + tempId);
            System.out.println("tempidtn: " + tempIdTn);
            idImagesTn[i] = getResources().getIdentifier(tempIdTn, "drawable", getPackageName());
            idImagesFull[i] = getResources().getIdentifier(tempId, "drawable", getPackageName());
            if(idImagesTn[i] == 0){
                idImagesTn[i] = defaultTn;
            }
            if(idImagesFull[i] == 0){
                idImagesFull[i] = defaultFull;
            }
            idTree.add(tempId, idImagesFull[i], idImagesTn[i]);
            System.out.println(idImagesTn[i]);
        }
        counter=0;
        while(questionFile.hasNextLine()){
            QUESTIONlabels[counter]=questionFile.nextLine();
            System.out.println(QUESTIONlabels[counter]);
            counter++;
        }
        System.out.println("DATA PARSING COMPLETE");
        for(int i = 0; i < idList.size(); i++){
            System.out.println(idList.get(i));
        }
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
            String[] idArray = new String[idTree.size()];
            int[] imageFileArray = new int[idTree.size()];
            int count2 = 1;
            System.out.println("size of idtree: " + idTree.size());
            while (counter < idTree.size()) {
                System.out.println("num of times into while loop: " + count2);
                count2++;
                idArray[counter] = idTree.get(counter);
                imageFileArray[counter] = idTree.getImageTn(counter);
                //System.out.println(idArray[counter - 1]);
                counter++;
            }

            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listview_layout1, idList);
            GridView gridView = (GridView) findViewById(R.id.idGrid);
            gridView.setAdapter(new GridAdapter(this, idArray, imageFileArray));
            //gridView.setAdapter(new ImageAdapter(this, imageFileArray));
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
