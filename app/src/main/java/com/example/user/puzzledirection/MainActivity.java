package com.example.user.puzzledirection;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AlertDialog alertDialog1;
    public ArrayList<Question> que[];
    int playerScore = 0;
    int playerLocation = 11;
    Button disha[][];
    int grd[][];
    int x=4,y=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        que=new ArrayList[7];
        addQuestion();
        grd=new int[5][5];
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                grd[i][j]=1;
            }
        }
        disha=new Button[5][5];
        disha[0][0]= (Button) findViewById(R.id.a1);
        disha[0][1]= (Button) findViewById(R.id.a2);
        disha[0][2]= (Button) findViewById(R.id.a3);
        disha[0][3]= (Button) findViewById(R.id.a4);
        disha[0][4]= (Button) findViewById(R.id.a5);

        disha[1][0]= (Button) findViewById(R.id.b1);
        disha[1][1]= (Button) findViewById(R.id.b2);
        disha[1][2]= (Button) findViewById(R.id.b3);
        disha[1][3]= (Button) findViewById(R.id.b4);
        disha[1][4]= (Button) findViewById(R.id.b5);

        disha[2][0]= (Button) findViewById(R.id.c1);
        disha[2][1]= (Button) findViewById(R.id.c2);
        disha[2][2]= (Button) findViewById(R.id.c3);
        disha[2][3]= (Button) findViewById(R.id.c4);
        disha[2][4]= (Button) findViewById(R.id.c5);

        disha[3][0]= (Button) findViewById(R.id.d1);
        disha[3][1]= (Button) findViewById(R.id.d2);
        disha[3][2]= (Button) findViewById(R.id.d3);
        disha[3][3]= (Button) findViewById(R.id.d4);
        disha[3][4]= (Button) findViewById(R.id.d5);

        disha[4][0]= (Button) findViewById(R.id.e1);
        disha[4][1]= (Button) findViewById(R.id.e2);
        disha[4][2]= (Button) findViewById(R.id.e3);
        disha[4][3]= (Button) findViewById(R.id.e4);
        disha[4][4]= (Button) findViewById(R.id.e5);
        x=4;
        y=0;
        displayScore(playerScore);
        buttonDisable();
        disha[4][0].setEnabled(true);
    }
    public void buttonDisable(){
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                disha[i][j].setEnabled(false);
            }
        }
    }
    public void displayScore(int score) {
        TextView scoreView = (TextView) findViewById(R.id.playerScore);
        scoreView.setText(String.valueOf(score));
    }
    public void finalScore(int score){
        TextView scoreView = (TextView) findViewById(R.id.playerScore);
        scoreView.setText("Final score is " + String.valueOf(score));
    }
    public void showQuestion(String que,String op1,String op2,String op3,String op4,int ans,View vv){
        final CharSequence[] values = {op1,op2,op3,op4};


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(que);

        final int i=ans;
        final View v=vv;
        builder.setSingleChoiceItems(values, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch (item) {
                    case 0:
                        // Your code when first option seletced
                        if(i==1){
                            st();
                            if(v==disha[0][4]){
                                finalScore(playerScore);
                                break;
                            }
                            Toast.makeText(MainActivity.this, "Correct Answer A", Toast.LENGTH_SHORT).show();
                            playerScore += 5;
                            displayScore(playerScore);
                            for(int i=0;i<5;i++){
                                for(int j=0;j<5;j++){
                                    if(v==disha[i][j]){
                                        buttonDisable();
                                        disha[i][j].setText(".Go.");
                                        if(inRange(i-1,j) && grd[i-1][j]==1){
                                            disha[i-1][j].setEnabled(true);
                                            disha[i-1][j].setText("Come");
                                            //x=i-1;
                                        }if(inRange(i+1,j) && grd[i+1][j]==1){
                                            disha[i+1][j].setEnabled(true);
                                            disha[i+1][j].setText("Come");
                                            //x=i+1;
                                        }if(inRange(i,j-1) && grd[i][j-1]==1){
                                            disha[i][j-1].setEnabled(true);
                                            disha[i][j-1].setText("Come");
                                            //y=j-1;
                                        }if(inRange(i,j+1) && grd[i][j+1]==1){
                                            disha[i][j+1].setEnabled(true);
                                            disha[i][j+1].setText("Come");
                                            //y=j+1;
                                        }
                                    }
                                }
                            }
                        }else {
                            Toast.makeText(MainActivity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
                            playerScore -= 2;
                            displayScore(playerScore);
                            for(int i=0;i<5;i++){
                                for(int j=0;j<5;j++){
                                    if(v==disha[i][j]){
                                        if(!(i==4 && j==0)) {
                                            grd[i][j] = 0;
                                            disha[i][j].setEnabled(false);
                                            disha[i][j].setText(".X.");
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case 1:
                        // Your code when 2nd  option seletced
                        if(i==2){
                            st();
                            if(v==disha[0][4]){
                                finalScore(playerScore);
                                break;
                            }
                            Toast.makeText(MainActivity.this, "Correct Answer B", Toast.LENGTH_SHORT).show();
                            for(int i=0;i<5;i++){
                                for(int j=0;j<5;j++){
                                    if(v==disha[i][j]){
                                        buttonDisable();
                                        disha[i][j].setText(".Go.");
                                        if(inRange(i-1,j) && grd[i-1][j]==1){
                                            disha[i-1][j].setEnabled(true);
                                            disha[i-1][j].setText("Come");
                                            //x=i-1;
                                        }if(inRange(i+1,j) && grd[i+1][j]==1){
                                            disha[i+1][j].setEnabled(true);
                                            disha[i+1][j].setText("Come");
                                            //x=i+1;
                                        }if(inRange(i,j-1) && grd[i][j-1]==1){
                                            disha[i][j-1].setEnabled(true);
                                            disha[i][j-1].setText("Come");
                                           // y=j-1;
                                        }if(inRange(i,j+1) && grd[i][j+1]==1){
                                            disha[i][j+1].setEnabled(true);
                                            disha[i][j+1].setText("Come");
                                            //y=j+1;
                                        }
                                    }
                                }
                            }
                        }else {
                            Toast.makeText(MainActivity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
                            playerScore -= 2;
                            displayScore(playerScore);
                            for(int i=0;i<5;i++){
                                for(int j=0;j<5;j++){
                                    if(v==disha[i][j]){
                                        if(!(i==4 && j==0)) {
                                            grd[i][j] = 0;
                                            disha[i][j].setEnabled(false);
                                            disha[i][j].setText(".X.");
                                        }
                                    }
                                }
                            }
                        }break;
                    case 2:
                        // Your code when 3rd option seletced
                        if(i==3){
                            st();
                            if(v==disha[0][4]){
                                finalScore(playerScore);
                                break;
                            }
                            Toast.makeText(MainActivity.this, "Correct Answer C", Toast.LENGTH_SHORT).show();
                            for(int i=0;i<5;i++){
                                for(int j=0;j<5;j++){
                                    if(v==disha[i][j]){
                                        buttonDisable();
                                        disha[i][j].setText(".Go.");
                                        if(inRange(i-1,j) && grd[i-1][j]==1){
                                            disha[i-1][j].setEnabled(true);
                                            disha[i-1][j].setText("Come");
                                            //x=i-1;
                                        }if(inRange(i+1,j) && grd[i+1][j]==1){
                                            disha[i+1][j].setEnabled(true);
                                            disha[i+1][j].setText("Come");
                                            //x=i+1;
                                        }if(inRange(i,j-1) && grd[i][j-1]==1){
                                            disha[i][j-1].setEnabled(true);
                                            disha[i][j-1].setText("Come");
                                            //y=j-1;
                                        }if(inRange(i,j+1) && grd[i][j+1]==1){
                                            disha[i][j+1].setEnabled(true);
                                            disha[i][j+1].setText("Come");
                                            //y=j+1;
                                        }
                                    }
                                }
                            }
                        }else {
                            Toast.makeText(MainActivity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
                            playerScore -= 2;
                            displayScore(playerScore);
                            for(int i=0;i<5;i++){
                                for(int j=0;j<5;j++){
                                    if(v==disha[i][j]){
                                        if(!(i==4 && j==0)) {
                                            grd[i][j] = 0;
                                            disha[i][j].setEnabled(false);
                                            disha[i][j].setText(".X.");
                                        }
                                    }
                                }
                            }
                        }break;
                    case 3:
                        if(i==4){
                            st();
                            if(v==disha[0][4]){
                                finalScore(playerScore);
                                break;
                            }
                            Toast.makeText(MainActivity.this, "Correct Answer D", Toast.LENGTH_SHORT).show();
                            for(int i=0;i<5;i++){
                                for(int j=0;j<5;j++){
                                    if(v==disha[i][j]){
                                        buttonDisable();
                                        disha[i][j].setText(".Go.");
                                        if(inRange(i-1,j) && grd[i-1][j]==1){
                                            disha[i-1][j].setEnabled(true);
                                            disha[i-1][j].setText("Come");
                                            //x=i-1;
                                        }if(inRange(i+1,j) && grd[i+1][j]==1){
                                            disha[i+1][j].setEnabled(true);
                                            disha[i+1][j].setText("Come");
                                            //x=i+1;
                                        }if(inRange(i,j-1) && grd[i][j-1]==1){
                                            disha[i][j-1].setEnabled(true);
                                            disha[i][j-1].setText("Come");
                                            //y=j-1;
                                        }if(inRange(i,j+1) && grd[i][j+1]==1){
                                            disha[i][j+1].setEnabled(true);
                                            disha[i][j+1].setText("Come");
                                            //y=j+1;
                                        }
                                    }
                                }
                            }
                        }else {
                            Toast.makeText(MainActivity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
                            playerScore -= 2;
                            displayScore(playerScore);
                            for(int i=0;i<5;i++){
                                for(int j=0;j<5;j++){
                                    if(v==disha[i][j]){
                                        if(!(i==4 && j==0)) {
                                            grd[i][j] = 0;
                                            disha[i][j].setEnabled(false);
                                            disha[i][j].setText(".X.");
                                        }
                                    }
                                }
                            }
                        }break;
                }
                disha[4][0].setText("START");
                alertDialog1.show();
                alertDialog1.dismiss();
            }
        });
        alertDialog1 = builder.create();
        alertDialog1.show();
        return ;
    }
    public void st(){
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(!(i==4 && j==0) && grd[i][j]!=0 && !(i==0 && j==4)){
                    disha[i][j].setText("...");
                }
            }
        }
    }
    void addQuestion() {
        Question qobj;
        for (int i = 0; i < 7; i++) {
            que[i] = new ArrayList<>();
        }
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("bio.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = in.readLine()) != null) {
                qobj = new Question(line, in.readLine(), in.readLine(), in.readLine(), in.readLine(), Integer.parseInt(String.valueOf((in.readLine()).charAt(0))));
                que[0].add(qobj);
            }
            inputStream = assetManager.open("chem.txt");
            in = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = in.readLine()) != null) {
                qobj = new Question(line, in.readLine(), in.readLine(), in.readLine(), in.readLine(), Integer.parseInt(String.valueOf((in.readLine()).charAt(0))));

                que[1].add(qobj);
            }
            inputStream = assetManager.open("gk.txt");
            in = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = in.readLine()) != null) {
                qobj = new Question(line, in.readLine(), in.readLine(), in.readLine(), in.readLine(), Integer.parseInt(String.valueOf((in.readLine()).charAt(0))));
                que[2].add(qobj);

            }
            inputStream = assetManager.open("math.txt");
            in = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = in.readLine()) != null) {
                qobj = new Question(line, in.readLine(), in.readLine(), in.readLine(), in.readLine(), Integer.parseInt(String.valueOf((in.readLine()).charAt(0))));
                que[3].add(qobj);

            }
            inputStream = assetManager.open("pg.txt");
            in = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = in.readLine()) != null) {
                qobj = new Question(line, in.readLine(), in.readLine(), in.readLine(), in.readLine(), Integer.parseInt(String.valueOf((in.readLine()).charAt(0))));
                que[4].add(qobj);

            }
            inputStream = assetManager.open("phy.txt");
            in = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = in.readLine()) != null) {
                qobj = new Question(line, in.readLine(), in.readLine(), in.readLine(), in.readLine(), Integer.parseInt(String.valueOf((in.readLine()).charAt(0))));
                que[5].add(qobj);

            }
            inputStream = assetManager.open("politics.txt");
            in = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = in.readLine()) != null) {
                qobj = new Question(line, in.readLine(), in.readLine(), in.readLine(), in.readLine(), Integer.parseInt(String.valueOf((in.readLine()).charAt(0))));
                que[6].add(qobj);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void abcde(View v) throws InterruptedException {
        int p,q;
        p=(int) (Math.random()*7);
        q=(int)(Math.random()*10);
        final String qq=que[p].get(q).question;
        final String a,b,c,d;
        a=que[p].get(q).A;
        b=que[p].get(q).B;
        c=que[p].get(q).C;
        d=que[p].get(q).D;
        final int aaa=que[p].get(q).answer;
        final View w=v;
        final String u;
        if(aaa==1){
            u=a;
        }else if(aaa==2){
            u=b;
        }else if(aaa==3){
            u=c;
        }else{
            u=d;
        }
        final AlertDialog.Builder adb=new AlertDialog.Builder(this);
        adb.setCancelable(true);
        adb.setTitle("Question");
        adb.setMessage(qq);
        adb.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("Utkarsh", "Question!");
                adb.setTitle("Answer");
                adb.setMessage(u);
                adb.show();
                showQuestion(qq, a, b, c, d, aaa, w);
            }
        }, 3000);

    }
    public boolean inRange(int x,int y){
        if(x>=0 && x<5 && y>=0 && y<5){
            return true;
        }else{
            return false;
        }
    }
    public void reset(View v) {
        playerScore = 0;
        displayScore(playerScore);
        buttonDisable();
        disha[4][0].setEnabled(true);
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                grd[i][j]=1;
                if(!(i==4 && j==0 || i==0 && j==4)){
                    disha[i][j].setText("...");
                }
            }
        }
        disha[4][0].setText("START");
        disha[0][4].setText("END");
        Toast toast = Toast.makeText(getApplicationContext(), "Puzzle reset.", Toast.LENGTH_SHORT);
        toast.show();
    }
    public void sss(View v){
        Log.i("Utkarsh",x+" "+y);
        if(x==4 && y==0 && disha[3][0].getText().equals(".X.") && disha[4][1].getText().equals(".X.")){
            finalScore(playerScore);
            Toast.makeText(MainActivity.this, "YOU ARE BLOCKED !!!", Toast.LENGTH_SHORT).show();
            return;
        }
        AStar aa=new AStar();
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(disha[i][j].getText().equals(".Go.")){
                    x=i;
                    y=j;
                    break;
                }
            }
        }
        aa.aStarSearch(grd, x, y, 0, 4);
        String s=aa.s;
        int l=s.length();
        if(l==0){
            finalScore(playerScore);
            Toast.makeText(MainActivity.this, "YOU ARE BLOCKED !!!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MainActivity.this, "Shortest path is displayed by -> .P.", Toast.LENGTH_SHORT).show();
        }int p,q,j=-1;
        for(int i=0;i<5;i++){
            for(int k=0;k<5;k++){
                if(disha[i][k].getText().equals(".P."))
                    disha[i][k].setText("...");
            }
        }
        while(j<l-9){
            j+=5;
            p=(int)(s.charAt(j)-'0');
            j+=2;
            q=(int)(s.charAt(j)-'0');
            if(disha[p][q].getText().equals("..."))
                disha[p][q].setText(".P.");
        }
    }
}
