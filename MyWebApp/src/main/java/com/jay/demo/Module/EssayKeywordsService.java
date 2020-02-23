package com.jay.demo.Module;


import com.jay.demo.Objects.EssayKeywords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EssayKeywordsService {
    @Autowired
    EssayKeywords ek = new EssayKeywords();

    public int totalKeywords(EssayKeywords data){
        String text = data.getText();
        String[] keywords= data.getKeywords();
        int count =0;
        for(int i=0;i<keywords.length;i++){
            if((text.toLowerCase()).contains((keywords[i]).toLowerCase())){
                count++;
            }
        }
        return count;
    }

}
