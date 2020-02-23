package com.jay.demo.Module;



import org.springframework.beans.factory.annotation.Autowired;

public class EssayKeywords {
    @Autowired
    String text;
    String[] keywords;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public int totalKeywords(String text, String[] keywords){
        int count =0;
        for(int i=0;i<keywords.length;i++){
            if(text.contains(keywords[i])){
                count++;
            }
        }
        return count;
    }
}

