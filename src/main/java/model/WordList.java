package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class WordList {
    private int id;
    private String new_word;
    private String meaningOfWord;
    private String  sinhala_Meaning;
    private int created_date;

    public WordList(int id, String new_word, String meaningOfWord, String sinhala_Meaning) {
        this.id = id;
        this.new_word = new_word;
        this.meaningOfWord = meaningOfWord;
        this.sinhala_Meaning = sinhala_Meaning;
    }
}
