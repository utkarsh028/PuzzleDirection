package com.example.user.puzzledirection;

/**
 * Created by USER on 12/6/2016.
 */
public class Question {
    public String question,A,B,C,D;
    public int answer;
    public Question(String ques, String a, String b, String c, String d, int ans){
        this.question=ques;
        this.A=a;
        this.B=b;
        this.C=c;
        this.D=d;
        this.answer=ans;
    }
    public String getanswer(){
        if(answer==1){
            return A;
        }
        else if(answer==2){
            return B;
        }
        else if(answer==3){
            return C;
        }
        else {
            return D;
        }
    }
}

