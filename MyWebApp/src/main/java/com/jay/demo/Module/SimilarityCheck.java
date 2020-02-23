package com.jay.demo.Module;


import com.jay.demo.Utility.Send_HTTP_Request;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;

@Service
public class SimilarityCheck {
    @Autowired
    Send_HTTP_Request s = new Send_HTTP_Request();

    public double similar(String text1, String text2){
//        ArrayList<String> sentencesOfText1 = splitToSentence(text1);
//        ArrayList<String> sentencesOfText2 = splitToSentence(text2);
//        for(int i=0;i<sentencesOfText1.size();i++){
//            for(int j=0;i<sentencesOfText2.size();j++){
//                text1 = removeCommonWords(text1);
//                text2 = removeCommonWords(text2);
//
//            }
//        }
//        try {
//            System.out.println(text1);
//            JSONArray synonyms = (request.call_me(text1)).getJSONArray(0);
//            ArrayList<String> mylist = new ArrayList<String>();
//            if (synonyms == null) {
//                System.out.println("json is empty");
//            }
//            else
//            {
//                int length = synonyms.length();
//                for (int i=0;i<length;i++){
//                    mylist.add(synonyms.get(i).toString());
//                }
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
        return simCheck(text1,text2);
    }

    public ArrayList<String> splitToSentence(String text){
        ArrayList<String> sentences = new ArrayList<String>();
        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
        iterator.setText(text);
        int start = iterator.first();
        for (int end = iterator.next();
             end != BreakIterator.DONE;
             start = end, end = iterator.next()) {
             sentences.add(text.substring(start,end));
        }
        System.out.println("The sentences are");
        System.out.println(sentences.size());

        return sentences;
    }

    public double simCheck(String sentence1, String sentence2){
        ArrayList<String> firstSentence = splitToWords(sentence1);
        ArrayList<String> secondSentence = splitToWords(sentence2);
        int totalWords= firstSentence.size()<secondSentence.size()?firstSentence.size():secondSentence.size();
        ArrayList<String> smallSentence= firstSentence.size()<secondSentence.size()?firstSentence:secondSentence;
        String biggerSentence= firstSentence.size()>=secondSentence.size()?sentence1:sentence2;
        int totalMatch=0;
        for(int i=0;i<totalWords;i++){
            if(biggerSentence.contains(smallSentence.get(i))){
                totalMatch++;
            }
            else{
                try {
                    JSONArray simWords = s.call_me(smallSentence.get(i));
                    for(int j=0;j<simWords.length();j++){
//                        if(biggerSentence.contains())){
//                            totalMatch++;
//                        }
                    }
                }
                catch (Exception e){
                    System.out.print(e.toString());
                }
            }
        }
        System.out.println("Total Match are "+totalMatch+" Total Words are "+totalWords+" similarity "+totalMatch/totalWords);
        return (totalMatch/(double)totalWords);

    }

    //Takes a String of sentence
    //Returns all the Words of that sentence.
    public ArrayList<String> splitToWords(String sentence){
        String[] words=sentence.split("\\s");//splits the string based on whitespace
        ArrayList<String> words1 = new ArrayList<>();
        for(String w:words) {
            words1.add(w.toLowerCase());
        }
        return words1;
    }


    //Takes a String
    //Returns a String with all the common words removed
    //like "I an happy to be in USA" will return "happy USA"
    public ArrayList<String> removeCommonWords(ArrayList<String> sentence){
        String toRemove[] = {"the", "at", "there", "of", "and","a","to","in","is","you","that" , "it" , "he", "was", "for" ,"on"};
        for (int i = 0; i < toRemove.length; ++i)
        {
            if(sentence.contains(toRemove[i])){
                int temp = sentence.indexOf(toRemove[i]);
                sentence.remove(temp);
            }
        }
        System.out.println(sentence);
        return sentence;
    }
}
